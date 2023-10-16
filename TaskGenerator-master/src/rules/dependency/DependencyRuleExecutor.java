/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rules.dependency;

import complexity.Complexity;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.TokenStream;

/**
 *
 * @author djordje This class loads dependency rules from their xml files and
 * then executes them. Execution steps: filter the formula with regex and
 * extract useful part, send extracted part to unification, evaluate dependency
 * rule conditions.
 */
public class DependencyRuleExecutor {

    private Complexity resultComplexity;
    private Complexity compl1;
    private Complexity compl2;
    private String segmentFormula;

    public enum GrammarType {
        BASIC, NESTING, SEQ
    };
    private static List<String> basicGrammars, nestingGrammars, seqGrammars;

    static {
        try {
            basicGrammars = Files.walk(Paths.get("./grammar/dependency/rules/basic"), FileVisitOption.FOLLOW_LINKS)
                    .filter((path) -> path.toString().endsWith(".g4"))
                    .map((path) -> path.toString())
                    .collect(Collectors.toList());
            Collections.sort(basicGrammars);
            nestingGrammars = Files.walk(Paths.get("./grammar/dependency/rules/nesting"), FileVisitOption.FOLLOW_LINKS)
                    .filter((path) -> path.toString().endsWith(".g4"))
                    .map((path) -> path.toString())
                    .collect(Collectors.toList());;
            Collections.sort(nestingGrammars);
            seqGrammars = Files.walk(Paths.get("./grammar/dependency/rules/sequencing"), FileVisitOption.FOLLOW_LINKS)
                    .filter((path) -> path.toString().endsWith(".g4"))
                    .map((path) -> path.toString())
                    .collect(Collectors.toList());
            Collections.sort(seqGrammars);
        } catch (Exception ex) {
            System.out.println("Grammars not found!!!!");
            System.exit(-1);
        }
    }

    public DependencyRuleExecutor(Complexity compl1, Complexity compl2,
            String segmentFormula) {
        this.compl1 = compl1;
        this.compl2 = compl2;
        this.segmentFormula = segmentFormula;
    }

    public Complexity getResultComplexity() {
        return resultComplexity;
    }

    void executeRules(GrammarType type, String classBase) {
        resultComplexity = null;
        List<String> grammarFiles;

        switch (type) {
            case BASIC:
                grammarFiles = basicGrammars;
                break;
            case NESTING:
                grammarFiles = nestingGrammars;
                break;
            case SEQ:
                grammarFiles = seqGrammars;
                break;
            default:
                grammarFiles = null;
        }

        try {
            // set of antlr generated parsers will evaluate rule dependencies
            // they will do unification, if necessary

            for (String grammarFile : grammarFiles) {
//                System.out.println(grammarFile);
                StringBuilder builder = new StringBuilder(grammarFile);
                builder.delete(0, builder.lastIndexOf(System.getProperty("file.separator")) + 1);
                builder.delete(builder.indexOf("."), builder.length());
                //create lexer 
                String lexer = classBase + builder.toString() + "Lexer";
                Class lexerClass = Class.forName(lexer);
                ANTLRInputStream input = new ANTLRInputStream(segmentFormula);
                Lexer lexerObj = (Lexer) lexerClass.getDeclaredConstructor(CharStream.class).newInstance(input);

                CommonTokenStream tokens = new CommonTokenStream(lexerObj);

                //create parser
                String parser = classBase + builder.toString() + "Parser";
                Class parserClass = Class.forName(parser);
                Parser parserObj = (Parser) parserClass.getDeclaredConstructor(TokenStream.class).newInstance(tokens);
                //setting compl1 and compl2 to the parser, if possible
                try {
                    Field field = parserObj.getClass().getField("compl1");
                    field.set(parserObj, compl1);
                    field = parserObj.getClass().getField("compl2");
                    field.set(parserObj, compl2);
                } catch (NoSuchFieldException ex) {

                }

                //invoke formula() method
                Method formulaMethod = parserObj.getClass().getMethod("formula", new Class[0]);
                Object context = formulaMethod.invoke(parserObj, new Object[0]);
                // get valid field
                Field resultComplexityField = context.getClass().getField("resultComplexity");
                resultComplexity = (Complexity) resultComplexityField.get(context);
                if (resultComplexity != null) {
//                    System.out.println("end success");
                    break;
                }

            }
            if (resultComplexity == null) {
                return;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void executeBasicRules() {
        executeRules(GrammarType.BASIC, "dependency.rules.basic.");
    }

    public void executeNestingRules() {
        executeRules(GrammarType.NESTING, "dependency.rules.nesting.");
    }

    public void executeSequenceRules() {
        executeRules(GrammarType.SEQ, "dependency.rules.sequencing.");
    }
}
