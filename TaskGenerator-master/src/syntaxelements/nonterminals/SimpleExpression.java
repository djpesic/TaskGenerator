/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxelements.nonterminals;

import visitors.SyntaxTreeVisitor;
import java.util.*;
import org.javatuples.Pair;
import syntaxelements.terminals.*;

/**
 *
 * @author djordje simpleExpression= term { addition-operator term }
 */
public class SimpleExpression {

    private Term term;
    private List<Pair<Addop, Term>> addopTermList = new LinkedList<>();

    public SimpleExpression(Term t) {
        term = t;
    }

    public void addTerm(Addop op, Term t) {
        addopTermList.add(new Pair<>(op, t));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(term);
        for (Pair<Addop, Term> p : addopTermList) {
            result.append(p.getValue0()).append(p.getValue1());
        }
        return result.toString();
    }

    protected SimpleExpression() {
    }

    public void accept(SyntaxTreeVisitor visitor) {

        term.accept(visitor);
        for (Pair<Addop, Term> p : addopTermList) {
            p.getValue0().accept(visitor);
            p.getValue1().accept(visitor);
        }
        visitor.visit(this);
    }

    public String toFormula() {
        StringBuilder result = new StringBuilder();
        if (addopTermList.isEmpty()) {
            result.append(term.toFormula());
        } else {
            result.append(createOperation(
                    addopTermList.get(0).getValue0().toFormula(),
                    term.toFormula(),
                    addopTermList.get(0).getValue1().toFormula()));
        }
        return result.toString();
    }

    private String createOperation(String op, String operand1, String operand2) {
        StringBuilder result = new StringBuilder();
        result.append(op).append("(").append(operand1);
        result.append(",").append(operand2).append(")");
        addopTermList.remove(0);
        if (addopTermList.isEmpty()) {
            return result.toString();
        }
        return createOperation(
                addopTermList.get(0).getValue0().toFormula(),
                result.toString(),
                addopTermList.get(0).getValue1().toFormula());
    }

    public String toCString() {
        StringBuilder result = new StringBuilder();
        result.append(term.toCString());
        for (Pair<Addop, Term> p : addopTermList) {
            result.append(p.getValue0().toCString()).append(p.getValue1().toCString());
        }
        return result.toString();
    }
}
