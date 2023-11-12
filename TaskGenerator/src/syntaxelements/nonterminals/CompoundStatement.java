/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxelements.nonterminals;

import visitors.SyntaxTreeVisitor;
import syntaxelements.terminals.*;

/**
 *
 * @author djordje compoundStatement=begin statement-sequence end
 */
public class CompoundStatement {

    private Begin begin;
    private StatementSequence statementSequence;
    private End end;

    public boolean isEmpty() {
        return begin == null && statementSequence == null && end == null;
    }

    public CompoundStatement(Empty empty) {
        begin = null;
        statementSequence = null;
        end = null;
    }

    public CompoundStatement(StatementSequence statSeq) {
        begin = new Begin();
        end = new End();
        statementSequence = statSeq;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return ";";
        }
        StringBuilder result = new StringBuilder();
        result.append(begin).append(statementSequence).append(end);
        return result.toString();
    }

    public void accept(SyntaxTreeVisitor visitor) {
        if (!isEmpty()) {
            begin.accept(visitor);
            statementSequence.accept(visitor);
            end.accept(visitor);
        }
        visitor.visit(this);
    }

    public StatementSequence getStatementSequence() {
        return statementSequence;
    }

    public void insertStatementSequence(StatementSequence statSeq) {
        statementSequence = statSeq;
    }

    public String toFormula() {
        if (isEmpty()) {
            return "";
        }
        return statementSequence.toFormula();
    }

    public String toCString() {
        if (isEmpty()) {
            return ";";
        }
        StringBuilder result = new StringBuilder();
        result.append(begin.toCString()).append(statementSequence.toCString()).append(end.toCString());
        return result.toString();
    }
}
