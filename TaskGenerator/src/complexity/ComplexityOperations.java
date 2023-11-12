/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complexity;

import formula.ExpressionFormulaLexer;
import formula.ExpressionFormulaParser;
import gui.treedata.TGException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.javatuples.Pair;
import rules.dependency.DependencyRuleExecutor;
import utils.YacasExecutor;
import programSegment.Segment;
import rules.OperationType;
import xmlprocessors.XmlSegmentTypeDeterminator;
import xmlprocessors.generators.XmlBasedGenerator;

/**
 *
 * @author djordje
 */
public class ComplexityOperations {

    private static int compare(Complexity compl1, Complexity compl2) {
        //calculate limit of compl1/compl2 when refVars->inf. If limit==0 compl1<compl2
        //if limit==inf, compl1>compl2. if limit is const, compl1==compl2
        //if compl1 is O(1), compl2>compl1
        // if compl2 is O(1), compl2<compl1
        // returns -1 if compl1<compl2, 0 if compl1==compl2 and 1 if compl1>compl2
        if (compl1.isConstant()) {
            return -1;
        }
        if (compl2.isConstant()) {
            return 1;
        }
        String function = "(" + compl1.toString() + ")/(" + compl2.toString() + ")";
        String var = "n";
        String command = "Limit(" + var + ",Infinity)" + "Simplify(" + function + ");";
        command = command.replace("log", "Ln"); // yacas works only with Ln function, we use log
        String result = YacasExecutor.executeYacasCommand(command);

        if (result.contains("1")) {
            return 0;
        }
        if (result.contains("0")) {
            return -1;
        }
        if (result.contains("Infinity")) {
            return 1;
        }

        //try inverse function limit.
        String inverseFunction = "1/(" + function + ")";
        command = "Limit(" + var + ",Infinity)" + "Simplify(" + inverseFunction + ");";
        result = YacasExecutor.executeYacasCommand(command);

        if (result.contains("1")) {
            return 0;
        }
        if (result.contains("0")) {
            return 1;
        }
        if (result.contains("Infinity")) {
            return -1;
        }
        System.out.println("Yacas calculation failed on command: " + command);
        return -2;
    }

    public static Complexity createComplexity(String function) {
        if (function.equals("1")) {
            return Complexity.createConstant();
        }
        try {
            ComplexityFunctionsLexer lexer = new ComplexityFunctionsLexer(new ANTLRInputStream(function));

            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ComplexityFunctionsParser parser = new ComplexityFunctionsParser(tokens);
            ComplexityFunctionsParser.ComplexityContext complexityCtx = parser.complexity();
            Complexity compl = complexityCtx.compl;
            if (compl == null) {
                return null;
            }
            if (compl.isNone()) {
                return null;
            }
            return compl;
        } catch (ParseCancellationException ex) {

        }
        return null;
    }

    // no data dependency
    public static Complexity sequence(Complexity compl1, Complexity compl2) {
        if (compl1 == null) {
            System.out.println("Complexity from template1 not defined!!");
            return null;
        }
        if (compl2 == null) {
            System.out.println("Complexity from template2 not defined!!");
            return null;
        }
        int cmpResult = compare(compl1, compl2);
        if (cmpResult == -2) {
            return null;
        }
        if (cmpResult == -1) {
            return compl2;
        } else {
            return compl1;
        }
    }

    // no data dependency
    public static Complexity multiply(Complexity compl1, Complexity compl2) {
        if (compl1 == null) {
            System.out.println("Complexity from template1 not defined!!");
            return null;
        }
        if (compl2 == null) {
            System.out.println("Complexity from template2 not defined!!");
            return null;
        }
        return Complexity.multiply(compl1, compl2);
    }

    public static Complexity calculateWithDataDependencies(Complexity compl1, Complexity compl2,
            boolean seg1ExprSeq, boolean seg2ExprSeq, boolean seg2EmptyWhile,
            Segment segResult, OperationType opType) throws TGException {

        // in some cases, assembly manager requires to turn off complexity calculation with dependencies.
        Complexity result = null;
        //if both segments are sequence of the expressions, result is O(1)
        if (seg1ExprSeq && seg2ExprSeq) {
            return Complexity.createConstant();
        }

        //convert result segment to formula
        XmlBasedGenerator gen = new XmlBasedGenerator(segResult);
        String formula = gen.generate().getSyntaxTree().toFormula();
//        System.out.println("Formula:");
//        System.out.println(formula);
//        System.out.println();
        //check if the generated formula is correct
        ExpressionFormulaLexer lexer = new ExpressionFormulaLexer(new ANTLRInputStream(formula));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExpressionFormulaParser parser = new ExpressionFormulaParser(tokens);
        ExpressionFormulaParser.FormulaContext ctx = parser.formula();
        if (!ctx.valid) {
            System.out.println("Segment is not correctly converted to formula");
            return null;
        }

        // execute dependency rules
        DependencyRuleExecutor depRulesExecutor = new DependencyRuleExecutor(compl1, compl2, formula);
        if (seg1ExprSeq && seg2EmptyWhile && opType.isNesting() != null) {
            depRulesExecutor.executeBasicRules();
            result = depRulesExecutor.getResultComplexity();
        } else if (opType.isNesting() != null) {
            depRulesExecutor.executeNestingRules();
            result = depRulesExecutor.getResultComplexity();
        } else if (opType.isSequence() != null) {
            if (seg2ExprSeq) {
                result = compl1;
            } else {
                depRulesExecutor.executeSequenceRules();
                result = depRulesExecutor.getResultComplexity();
            }
        } else if (opType.getSelection() != null) {
            if (!opType.getSelection().isUseElseBranch()) {
                result = compl1;
            } else {
                result = sequence(compl1, compl2);
            }
        }
        return result;
    }

}
