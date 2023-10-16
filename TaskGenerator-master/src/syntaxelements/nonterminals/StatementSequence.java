/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxelements.nonterminals;

import visitors.SyntaxTreeVisitor;
import java.util.*;
import org.javatuples.Pair;
import syntaxelements.terminals.Semicolon;

/**
 *
 * @author djordje statementSequence=statement {";" statement}
 */
public class StatementSequence {

    private Statement statement;
    private List<Pair<Semicolon, Statement>> semiColonStatementList = new LinkedList<>();

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (statement != null) {
            result.append(statement).append("\n");
        }
        semiColonStatementList.forEach((st) -> result.append(
                st.getValue0().toString()).append("\n").append(st.getValue1().toString()).append("\n"));

        int index = result.indexOf("\n;");
        while (index != -1) {
            result.replace(index, index + 2, ";");
            index = result.indexOf("\n;");
        }
        index = result.indexOf("\n\n");
        while (index != -1) {
            result.replace(index, index + 2, "\n");
            index = result.indexOf("\n\nS;");
        }
        index = result.indexOf(";;");
        while (index != -1) {
            result.replace(index, index + 2, ";");
            index = result.indexOf(";;");
        }
        return result.toString();
    }

    public String toCString() {
        StringBuilder result = new StringBuilder();
        if (statement != null) {
            result.append(statement.toCString());
            if (semiColonStatementList.isEmpty()) {
                result.append(";");
            }
            result.append("\n");
        }
        semiColonStatementList.forEach((st) -> result.append(
                st.getValue0().toCString()).append("\n").append(st.getValue1().toCString()).append("\n"));

        int index = result.indexOf("\n;");
        while (index != -1) {
            result.replace(index, index + 2, "");
            index = result.indexOf("\n;");
        }
        index = result.indexOf("\n\n");
        while (index != -1) {
            result.replace(index, index + 2, "\n");
            index = result.indexOf("\n\nS;");
        }
        index = result.indexOf(";;");
        while (index != -1) {
            result.replace(index, index + 2, ";");
            index = result.indexOf(";;");
        }
        return result.toString();
    }

    public StatementSequence(Statement st) {
        statement = st;
    }

    public void addToOptionalList(Statement stat) {
        semiColonStatementList.add(new Pair<>(new Semicolon(), stat));
    }

    public void addToOptionalList(Statement stat, int index) {
        semiColonStatementList.add(index, new Pair<>(new Semicolon(), stat));
    }

    public void addToOptionalList(StatementSequence statSeq) {
        if (statSeq.statement != null) {
            semiColonStatementList.add(new Pair<>(new Semicolon(), statSeq.statement));
        }
        if (!statSeq.semiColonStatementList.isEmpty()) {
            semiColonStatementList.addAll(statSeq.semiColonStatementList);
        }

    }

    public void accept(SyntaxTreeVisitor visitor) {
        statement.accept(visitor);
        semiColonStatementList.forEach((el) -> {
            el.getValue0().accept(visitor);
            el.getValue1().accept(visitor);
        });
        visitor.visit(this);
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public Statement getStatement() {
        return statement;
    }

    public List<Pair<Semicolon, Statement>> getSemiColonStatementList() {
        return semiColonStatementList;
    }

    public String toFormula() {
        StringBuilder result = new StringBuilder(statement.toFormula());
        semiColonStatementList.forEach((stat) -> result.append(stat.getValue1().toFormula()));
        return result.toString();
    }

}
