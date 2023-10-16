/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxelements.nonterminals;

import visitors.SyntaxTreeVisitor;
import java.util.*;
import org.javatuples.Pair;
import syntaxelements.terminals.Mulop;

/**
 *
 * @author djordje term=signedFactor { multiplication-operator signedFactor }
 */
public class Term {

    private SignedFactor signedFactor;

    private List<Pair<Mulop, SignedFactor>> mulopSignedFactor = new LinkedList<>();

    public Term(SignedFactor f) {
        signedFactor = f;
    }

    protected Term() {
    }

    public void addSignedFactor(Mulop m, SignedFactor f) {
        mulopSignedFactor.add(new Pair<>(m, f));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(signedFactor);
        mulopSignedFactor.stream().forEach((elem) -> result.append(elem.getValue0().toString()).append(elem.getValue1().toString()));
        return result.toString();
    }

    public void accept(SyntaxTreeVisitor visitor) {
        signedFactor.accept(visitor);
        for (Pair<Mulop, SignedFactor> p : mulopSignedFactor) {
            p.getValue0().accept(visitor);
            p.getValue1().accept(visitor);
        }
        visitor.visit(this);
    }

    public String toFormula() {
        StringBuilder result = new StringBuilder();
        if (mulopSignedFactor.isEmpty()) {
            result.append(signedFactor.toFormula());
        } else {
            result.append(createOperation(
                    mulopSignedFactor.get(0).getValue0().toFormula(),
                    signedFactor.toFormula(),
                    mulopSignedFactor.get(0).getValue1().toFormula()));
        }
        return result.toString();
    }

    private String createOperation(String op, String operand1, String operand2) {
        StringBuilder result = new StringBuilder();
        result.append(op).append("(").append(operand1);
        result.append(",").append(operand2).append(")");
        mulopSignedFactor.remove(0);
        if (mulopSignedFactor.isEmpty()) {
            return result.toString();
        }
        return createOperation(
                mulopSignedFactor.get(0).getValue0().toFormula(),
                result.toString(),
                mulopSignedFactor.get(0).getValue1().toFormula());
    }

    public String toCString() {
        StringBuilder result = new StringBuilder();
        result.append(signedFactor.toCString());
        mulopSignedFactor.stream().forEach((elem) -> result.append(elem.getValue0().toCString()).append(elem.getValue1().toCString()));
        return result.toString();
    }
}
