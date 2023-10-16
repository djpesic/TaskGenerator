package unification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.javatuples.Pair;
import unif.MultiEqBuilderLexer;
import unif.MultiEqBuilderParser;
import unif.MultiEquationSystemLexer;
import unif.MultiEquationSystemParser;

/**
 *
 * @author djordje This class implements an efficient unification algorithm,
 * presented by Martelli and Montanary. Algorithm details can be fond in:
 * Martelli, A. and Montanari, U., 1982. An efficient unification algorithm. ACM
 * Transactions on Programming Languages and Systems (TOPLAS), 4(2), pp.258-282.
 * This paper also presents a Pascal implementation, reimplemented here, in
 * Java.
 */
public class Unification {

    private MultiEqSystem system;
    private boolean error = false;
    private StringBuilder multiEquationSystem = new StringBuilder();
    private Set<String> multiEqVars;
    private List<String> solution;
    
    public List<String> getSolution() {
        return solution;
    }

    private void generateMultiEquationSystem(String formulas) throws UnifyException {

        generateMultiEquation(formulas, 0);
        generateEmptyEquations();
    }

    private void generateMultiEquation(String formulas, int cnt) throws UnifyException {
        multiEquationSystem.append("{eq").append(cnt).append("}=");
        MultiEqBuilderLexer lexer = new MultiEqBuilderLexer(new ANTLRInputStream(formulas));
        MultiEqBuilderParser parser = new MultiEqBuilderParser(new CommonTokenStream(lexer));
        parser.multiEqVars = multiEqVars;
        MultiEqBuilderParser.SequenceContext context = parser.sequence();
        multiEquationSystem.append(context.multiEquationSystem);
        multiEquationSystem.append(";");
    }

    private void generateEmptyEquations() {
        for (String v : multiEqVars) {
            multiEquationSystem.append("{").append(v).append("}=;");
        }
    }

    public void startUnification(String formulas, Set<String> vars) throws UnifyException {
        multiEqVars = vars;
        multiEquationSystem.delete(0, multiEquationSystem.length());
        //convert segment formula to multiequation. If multiequation can not be
        // formed, unification is not possible, and algorithm stops.

        //generate multiequation system
        generateMultiEquationSystem(formulas);
        for (int i = 0; i < multiEquationSystem.length();) {
            if (multiEquationSystem.charAt(i) == '_') {
                multiEquationSystem.deleteCharAt(i);
            } else {
                i++;
            }
        }
//        System.out.println(multiEquationSystem);
        // parses generated multiequation string
        MultiEquationSystemLexer lexer = new MultiEquationSystemLexer(new ANTLRInputStream(multiEquationSystem.toString()));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MultiEquationSystemParser parser = new MultiEquationSystemParser(tokens);
        parser.addErrorListener(new ANTLRErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                error = true;
            }

            @Override
            public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {
                error = true;
            }

            @Override
            public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet conflictingAlts, ATNConfigSet configs) {
                error = true;
            }

            @Override
            public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction, ATNConfigSet configs) {
                error = true;
            }

        });
        MultiEquationSystemParser.MultiEquationSystemContext tree = parser.multiEquationSystem();
        if (error) {
            throw new UnifyException(UnifyException.PARSE_ERROR);
        }
        system = tree.multiEqSystem;
        system.u.connectVariablesToEquations();
        system.u.findZeroCounterMultiEquation();
//        System.out.println(system);
        unify();
//        System.out.println(system);
        solution = system.t.stream().map((mult)->mult.toString()).collect(Collectors.toList());
        solution.remove(0);
    }

    public static void main(String[] args) {
        Unification unif = new Unification();
        StringBuilder formulas = new StringBuilder();
        formulas.append("add(x,y)");
        formulas.append("add(a,b)");
        formulas.append("add(c,d)");
        try {
            Set<String> vars = new HashSet<>();
//            vars.add("a");
            vars.add("y");
//            vars.add("b");
            vars.add("x");
            unif.startUnification(formulas.toString(), vars);
        } catch (UnifyException ex) {
            // System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void unify() throws UnifyException {
        MultiEquation mult;
        List<TempMultiEq> frontier = new LinkedList<>();
        do {
            mult = selectMultiEquation(system.u);

            if (mult.m != null) {
                reduce(mult.m, frontier);
                compact(frontier, system.u);
            }
            system.t.add(mult);
            system.u.Equations.remove(mult);
        } while (!system.u.Equations.isEmpty());
    }

    private MultiEquation selectMultiEquation(UPart u) throws UnifyException {
        if (u.ZeroCounterMultiEq.isEmpty()) {
            throw new UnifyException(UnifyException.CYCLE);
        }
        MultiEquation mult = u.ZeroCounterMultiEq.get(0);
        u.ZeroCounterMultiEq.remove(0);
        u.Equations.remove(mult);
        return mult;
    }

    private void reduce(MultiTerm m, List<TempMultiEq> frontier) {
        List<TempMultiEq> arg;

        arg = m.args;
        if (arg == null) {
            return;
        }
        for (int i = 0; i < arg.size(); i++) {
            TempMultiEq tmp = arg.get(i);
            if (tmp != null) {
                if (tmp.s == null || tmp.s.isEmpty()) {
                    reduce(tmp.m, frontier);
                } else {
                    TempMultiEq newArg = new TempMultiEq();
                    TempMultiEq.copy(newArg, arg.get(i));
                    frontier.add(newArg);
                    tmp.s = new LinkedList<>(newArg.s);
                    tmp.m = null;
                }
            }
        }
    }

    private void compact(List<TempMultiEq> frontier, UPart u) throws UnifyException {
        List<Variable> vars;
        Variable v;
        MultiEquation mult;
        MultiEquation mult1;

        while (!frontier.isEmpty()) {
            vars = frontier.get(0).s;
            v = vars.get(0);
            vars.remove(0);
            mult = v.m;
            if (mult == null) {
                throw new UnifyException(UnifyException.HANGING_VAR, v.toString());
            }
            mult.counter--;
            while (!vars.isEmpty()) {
                v = vars.get(0);
                vars.remove(0);
                mult1 = v.m;
                mult1.counter--;
                mergeMultiEq(mult, mult1, u);
            }
            mult.m = mergeMultiTerms(mult.m, frontier.get(0).m);
            if (mult.counter == 0) {
                u.ZeroCounterMultiEq.add(mult);
            }
            frontier.remove(0);
        }
    }

    private void mergeMultiEq(MultiEquation mult, MultiEquation mult1, UPart u) throws UnifyException {
        MultiEquation multTmp = new MultiEquation();
        Variable v;
        List<Variable> vars;

        if (mult != mult1) {
            if (mult.s.size() < mult1.s.size()) {
                MultiEquation.copy(multTmp, mult);
                MultiEquation.copy(mult, mult1);
                MultiEquation.copy(mult1, multTmp);
            }
            mult.counter += mult1.counter;
            vars = mult1.s;
            for (int i = 0; i < vars.size(); i++) {
                v = vars.get(i);
                v.m = mult;
                Variable newV = new Variable();
                Variable.copy(newV, v);
                mult.s.add(newV);
            }
            mult.m = mergeMultiTerms(mult.m, mult1.m);
            u.Equations.remove(mult1);
        }
    }

    private MultiTerm mergeMultiTerms(MultiTerm m, MultiTerm m1) throws UnifyException {
        List<TempMultiEq> arg, arg1;
        if (m == null && m1 != null) {
            return m1;
        }
        if (m != null && m1 == null) {
            return m;
        }
        if (m != null && m1 != null) {
            if (m1.fSymb.compareTo(m.fSymb) != 0) {
                throw new UnifyException(UnifyException.CLASH, m1.fSymb, m.fSymb);
            }
            arg = m.args;
            arg1 = m1.args;
            if (arg != null && arg1 != null) {
                for (int i = 0; i < arg.size(); i++) {
                    if (arg1.size() > 0) {
                        TempMultiEq tmp = arg.get(i);
                        TempMultiEq tmp1 = arg1.get(i);
                        tmp.s.addAll(tmp1.s);
                        tmp.m = mergeMultiTerms(tmp.m, tmp1.m);
                    }
                }
            }
            return m;
        }
        return null;
    }
}
