/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxelements.nonterminals;

import visitors.SyntaxTreeVisitor;

/**
 *
 * @author djordje conditional-statement = if-statement
 */
public class ConditionalStatement {

    private IfStatement ifStatement;

    public ConditionalStatement(IfStatement ifStatement) {
        this.ifStatement = ifStatement;
    }

    public IfStatement getIfStatement() {
        return ifStatement;
    }

    @Override
    public String toString() {
        if (ifStatement != null) {
            return ifStatement.toString();
        }
        return null;
    }

    public void accept(SyntaxTreeVisitor visitor) {
        if (ifStatement != null) {
            ifStatement.accept(visitor);
        }
        visitor.visit(this);
    }

    public String toFormula() {
        if (ifStatement != null) {
            return ifStatement.toFormula();
        }
        return "";
    }

    public String toCString() {
        if (ifStatement != null) {
            return ifStatement.toCString();
        }
        return null;
    }

}
