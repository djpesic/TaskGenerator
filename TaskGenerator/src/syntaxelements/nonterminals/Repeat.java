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
 * @author djordje repeat statement-sequence until expression
 */
public class Repeat {

    private RepeatTerminal repeat;
    private StatementSequence statSequence;
    private Until until;
    private Expression expression;

    public Repeat(StatementSequence statSeq, Expression expr) {
        repeat = new RepeatTerminal();
        statSequence = statSeq;
        until = new Until();
        expression = expr;
    }

    @Override
    public String toString() {
        return repeat.toString() + statSequence + until + expression;
    }

    public void accept(SyntaxTreeVisitor visitor) {
        repeat.accept(visitor);
        statSequence.accept(visitor);
        until.accept(visitor);
        expression.accept(visitor);
        visitor.visit(this);
    }

    public void nestStatementSequence(StatementSequence toNest) {
        try {
            if (statSequence.getStatement().getStructuredStatement() == null) {
                StructuredStatement structStat = new StructuredStatement(new CompoundStatement(new StatementSequence(
                        new Statement(statSequence.getStatement().getSimpleStatement()))));
                statSequence = new StatementSequence(new Statement(structStat));
                statSequence.getStatement().getStructuredStatement().getCompound().getStatementSequence().addToOptionalList(toNest);
                return;
            }
            if (statSequence.getStatement().getStructuredStatement().getCompound().isEmpty()) {
                statSequence = new StatementSequence(new Statement(new StructuredStatement(new CompoundStatement(toNest))));
                return;
            }
        } catch (NullPointerException ex) {
        }

    }

    public String toFormula() {
        StringBuilder result = new StringBuilder("while(");
        result.append(expression.toFormulaForWhileLoop()).append(",").append(statSequence.toFormula());
        if (result.charAt(result.length() - 1) == ',') {
            result.deleteCharAt(result.length() - 1);
        }
        result.append(")");
        return result.toString();
    }

    public String toCString() {
        return repeat.toCString() + statSequence.toCString()
                + until.toCString() + "(" + expression.toCString() + ");";
    }
}
