/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxelements.nonterminals;

import visitors.SyntaxTreeVisitor;

/**
 *
 * @author djordje statement = simple-statement | structured-statement
 */
public class Statement {

    private SimpleStatement simpleStatement;
    private StructuredStatement structuredStatement;

    public Statement(SimpleStatement st) {
        simpleStatement = st;
    }

    public Statement(StructuredStatement st) {
        structuredStatement = st;
    }

    @Override
    public String toString() {
        if (simpleStatement != null) {
            return simpleStatement.toString();
        }
        return structuredStatement.toString();
    }

    public String toCString() {
        if (simpleStatement != null) {
            return simpleStatement.toCString();
        }
        return structuredStatement.toCString();
    }

    protected Statement() {
    }

    public void accept(SyntaxTreeVisitor visitor) {
        if (simpleStatement != null) {
            simpleStatement.accept(visitor);
        }
        if (structuredStatement != null) {
            structuredStatement.accept(visitor);
        }
        visitor.visit(this);
    }

    public SimpleStatement getSimpleStatement() {
        return simpleStatement;
    }

    public StructuredStatement getStructuredStatement() {
        return structuredStatement;
    }

    public String toFormula() {
        if (simpleStatement != null) {
            return simpleStatement.toFormula();
        }
        return structuredStatement.toFormula();
    }

}
