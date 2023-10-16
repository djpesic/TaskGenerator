/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxelements.nonterminals;

import visitors.SyntaxTreeVisitor;

/**
 *
 * @author djordje variable=entireVar | indexedVar
 */
public class Variable {

    private EntireVar entVar;
    private IndexedVar indexed;

    public Variable(EntireVar var) {
        entVar = var;
    }

    public Variable(IndexedVar var) {
        indexed = var;
    }

    protected Variable() {
    }

    @Override
    public String toString() {
        if (indexed != null) {
            return indexed.toString();
        }
        return entVar.toString();
    }

    public void accept(SyntaxTreeVisitor visitor) {
        if (indexed != null) {
            indexed.accept(visitor);
        } else {
            entVar.accept(visitor);
        }
        visitor.visit(this);
    }

    public String toFormula() {
        if (entVar != null) {
            return entVar.toFormula();
        }
        return indexed.toFormula();
    }

    public String toCString() {
        if (indexed != null) {
            return indexed.toCString();
        }
        return entVar.toString();
    }
}
