/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxelements.nonterminals;

import visitors.SyntaxTreeVisitor;
import syntaxelements.terminals.DoublecolonEquals;

/**
 *
 * @author djordje assignmentStatement = variable ":=" expression
 */
public class AssignmentStatement {

    private Variable variable;
    private DoublecolonEquals dcl;
    private Expression expr;
    private String tabs = "";

    public void setPrecedingTabs(int tabNumber) {
        for (int i = 0; i < tabNumber; i++) {
            tabs += "\t";
        }
    }

    public AssignmentStatement(Variable var, Expression exp) {
        variable = var;
        expr = exp;
        dcl = new DoublecolonEquals();
    }

    @Override
    public String toString() {
        return tabs + variable + dcl + expr;
    }

    protected AssignmentStatement() {
    }

    public void accept(SyntaxTreeVisitor visitor) {
        variable.accept(visitor);
        dcl.accept(visitor);
        expr.accept(visitor);
        visitor.visit(this);
    }

    public Variable getVariable() {
        return variable;
    }

    public DoublecolonEquals getDcl() {
        return dcl;
    }

    public Expression getExpr() {
        return expr;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
    }

    public void setExpr(Expression expr) {
        this.expr = expr;
    }

    public String toFormula() {
        StringBuilder result = new StringBuilder("expr(");
        result.append(variable.toFormula()).append(",").append(expr.toFormula()).append(")");
        return result.toString();
    }

    public String toCString() {
        return tabs + variable.toCString() + dcl.toCString() + expr.toCString()+";";
    }

}
