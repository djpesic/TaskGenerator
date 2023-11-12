/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxelements.nonterminals;

import visitors.SyntaxTreeVisitor;
import syntaxelements.terminals.Relop;
import utils.InvertCondition;

/**
 *
 * @author djordje expression=simple-expression [ relational-operator
 * simple-expression ]
 */
public class Expression {

    private SimpleExpression simpleExpression;
    private Relop relop;
    private SimpleExpression optionalSimpleExpression;

    public Expression(SimpleExpression sExpr) {
        this.simpleExpression = sExpr;
    }

    public Expression(SimpleExpression sExpr, Relop op, SimpleExpression optSExpr) {
        simpleExpression = sExpr;
        relop = op;
        optionalSimpleExpression = optSExpr;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(simpleExpression.toString());
        if (relop != null) {
            result.append(relop.toString()).append(optionalSimpleExpression.toString());
        }
//        result.append(" ");
        return result.toString();
    }

    public String toCString() {
        StringBuilder result = new StringBuilder(simpleExpression.toCString());
        if (relop != null) {
            result.append(relop.toCString()).append(optionalSimpleExpression.toCString());
        }
//        result.append(" ");
        return result.toString();
    }

    public String toCStringInvertCond() {
        StringBuilder result = new StringBuilder(simpleExpression.toCString());
        if (relop != null) {
            String invertedRelop = InvertCondition.invertCCondition(relop.toCString());
            result.append(invertedRelop).append(optionalSimpleExpression.toCString());
        }
//        result.append(" ");
        return result.toString();
    }

    protected Expression() {
    }

    public void accept(SyntaxTreeVisitor visitor) {
        simpleExpression.accept(visitor);
        if (relop != null) {
            relop.accept(visitor);
        }
        if (optionalSimpleExpression != null) {
            optionalSimpleExpression.accept(visitor);
        }
        visitor.visit(this);
    }

    public String toFormulaForWhileLoop() {
        StringBuilder result = new StringBuilder(simpleExpression.toFormula());
        result.append(",").append(optionalSimpleExpression.toFormula());
        return result.toString();
    }

    public String toFormula() {
        StringBuilder result = new StringBuilder();
        if (relop == null) {
            return simpleExpression.toFormula();
        }
        result.append(relop.toFormula()).append("(").append(simpleExpression.toFormula());
        if (optionalSimpleExpression != null) {
            result.append(",").append(optionalSimpleExpression.toFormula());
        }
        result.append(")");
        return result.toString();
    }
}
