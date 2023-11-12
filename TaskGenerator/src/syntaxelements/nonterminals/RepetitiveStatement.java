/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxelements.nonterminals;

import visitors.SyntaxTreeVisitor;

/**
 *
 * @author djordje repetitive-statement = while-statement | repeat-statement |
 * for-statement
 */
public class RepetitiveStatement {

    private While whileStatement;
    private Repeat repeatStatement;
    private For forStatement;

    @Override
    public String toString() {
        if (whileStatement != null) {
            return whileStatement.toString();
        }
        if (repeatStatement != null) {
            return repeatStatement.toString();
        }
        if (forStatement != null) {
            return forStatement.toString();
        }
        return null;
    }

    public RepetitiveStatement(While whileStatement) {
        this.whileStatement = whileStatement;
    }

    public RepetitiveStatement(Repeat repeatStatement) {
        this.repeatStatement = repeatStatement;
    }

    public RepetitiveStatement(For forStatement) {
        this.forStatement = forStatement;
    }

    public void accept(SyntaxTreeVisitor visitor) {
        if (whileStatement != null) {
            whileStatement.accept(visitor);
        }
        if (repeatStatement != null) {
            repeatStatement.accept(visitor);
        }
        if (forStatement != null) {
            forStatement.accept(visitor);
        }
        visitor.visit(this);
    }

    public String toFormula() {
        if (whileStatement != null) {
            return whileStatement.toFormula();
        }
        if (repeatStatement != null) {
            return repeatStatement.toFormula();
        }
        if (forStatement != null) {
            return forStatement.toFormula();
        }
        return null;
    }

    public String toCString() {
        if (whileStatement != null) {
            return whileStatement.toCString();
        }
        if (repeatStatement != null) {
            return repeatStatement.toCString();
        }
        if (forStatement != null) {
            return forStatement.toCString();
        }
        return null;
    }

}
