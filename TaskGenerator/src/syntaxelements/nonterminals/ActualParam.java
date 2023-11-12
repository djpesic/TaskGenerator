/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxelements.nonterminals;

import visitors.SyntaxTreeVisitor;

/**
 *
 * @author djordje actual-parameter=expression | variable
 */
public class ActualParam {

    private Expression expr;
    private Variable var;

    public ActualParam(Expression expr) {
        this.expr = expr;
    }

    public ActualParam(Variable var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return expr == null ? var.toString() : expr.toString();
    }

    public void accept(SyntaxTreeVisitor visitor) {
        if (expr == null) {
            var.accept(visitor);
        } else {
            expr.accept(visitor);
        }
        visitor.visit(this);
    }

    public String toCString() {
        return expr == null ? var.toCString() : expr.toCString();
    }

}
