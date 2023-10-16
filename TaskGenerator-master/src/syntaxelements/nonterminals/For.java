/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxelements.nonterminals;

import syntaxelements.terminals.*;
import visitors.SyntaxTreeVisitor;

/**
 *
 * @author djordje for variable-identifier ":=" initial-expression (to | downto)
 * final-expression do statement
 */
public class For {

    private Variable iterator;
    private ForTerminal forTerm;
    private DoublecolonEquals dcl;
    private Expression initExpression;
    private To to;
    private Downto downTo;
    private Expression finalExpression;
    private Do doTerm;
    private Statement statement;

    public String toFormula() {
        StringBuilder result = new StringBuilder();
        result.append("expr(").append(iterator.toFormula()).append(",").
                append(initExpression.toFormula()).append(")");
        if (to != null) {
            result.append("while(").append(iterator.toFormula()).append(",")
                    .append(finalExpression.toFormula()).append(",");
        } else if (downTo != null) {
            result.append("while(").append(finalExpression.toFormula()).append(",")
                    .append(iterator.toFormula()).append(",");
        }
        result.append("expr(").append(iterator.toFormula())
                .append(",");
        if (to != null) {
            result.append("add(");
        } else if (downTo != null) {
            result.append("sub(");
        }
        result.append(iterator.toFormula()).append(",1")
                .append(")").append(")");
        result.append(statement.toFormula());
        if (result.charAt(result.length() - 1) == ',') {
            result.deleteCharAt(result.length() - 1);
        }
        result.append(")");
        return result.toString();
    }

    //toDownto=true => to
    public For(Variable iterator, Expression initExpr, boolean toDownto, Expression finalExpr, Statement stat) {
        forTerm = new ForTerminal();
        this.iterator = iterator;
        dcl = new DoublecolonEquals();
        initExpression = initExpr;
        if (toDownto) {
            to = new To();
        } else {
            downTo = new Downto();
        }
        finalExpression = finalExpr;
        doTerm = new Do();
        statement = stat;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(forTerm).append(iterator).append(dcl).append(initExpression);
        if (to != null) {
            result.append(to);
        } else {
            result.append(downTo);
        }
        result.append(finalExpression);
        result.append(doTerm).append(statement);
        return result.toString();
    }

    public void accept(SyntaxTreeVisitor visitor) {
        forTerm.accept(visitor);
        iterator.accept(visitor);
        dcl.accept(visitor);
        initExpression.accept(visitor);
        if (to != null) {
            to.accept(visitor);
        } else {
            downTo.accept(visitor);
        }
        finalExpression.accept(visitor);
        doTerm.accept(visitor);
        statement.accept(visitor);
        visitor.visit(this);
    }

    public void nestStatementSequence(StatementSequence toNest) {
        try {
            if (statement.getStructuredStatement().getCompound().isEmpty()) {
                statement = new Statement(new StructuredStatement(new CompoundStatement(toNest)));
                return;
            }
        } catch (NullPointerException ex) {
        }
        statement.getStructuredStatement().getCompound().getStatementSequence().addToOptionalList(toNest);
    }

    public String toCString() {
        StringBuilder result = new StringBuilder();
        if (to != null) {
            result.append(forTerm.toCString()).append("(");
            result.append(iterator.toCString()).append("=").append(initExpression.toCString()).append(";");
            result.append(iterator.toCString()).append("<").append(finalExpression.toCString()).append(";");
            result.append(iterator.toCString()).append("++");
            result.append(")");
        } else if (downTo != null) {
            result.append(forTerm.toCString()).append("(");
            result.append(iterator.toCString()).append("=").append(initExpression.toCString()).append(";");
            result.append(iterator.toCString()).append(">").append(finalExpression.toCString()).append(";");
            result.append(iterator.toCString()).append("--");
            result.append(")");
        }

        result.append(statement.toCString());
        return result.toString();
    }

}
