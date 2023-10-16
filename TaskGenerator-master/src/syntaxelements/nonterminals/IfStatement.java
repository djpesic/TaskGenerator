/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxelements.nonterminals;

import java.util.LinkedList;
import java.util.List;
import org.javatuples.Pair;
import syntaxelements.terminals.ElseTerminal;
import syntaxelements.terminals.IfTerminal;
import syntaxelements.terminals.ThenTerminal;
import visitors.SyntaxTreeVisitor;

/**
 *
 * @author djordje if-statement = if expression then statement [else statement]
 */
public class IfStatement {

    private IfTerminal ifTerminal;
    private Expression expression;
    private ThenTerminal thenTerminal;
    private Statement statement;
    private List<Pair<ElseTerminal, Statement>> elseStatementList = new LinkedList<>();

    public IfStatement(Expression expression, Statement statement) {
        this.expression = expression;
        this.statement = statement;
        ifTerminal = new IfTerminal();
        thenTerminal = new ThenTerminal();
    }

    public void addStatement(Statement stat) {
        elseStatementList.add(new Pair<>(new ElseTerminal(), stat));
    }

    public IfStatement() {
    }

    public void accept(SyntaxTreeVisitor visitor) {
        ifTerminal.accept(visitor);
        expression.accept(visitor);
        thenTerminal.accept(visitor);
        statement.accept(visitor);
        for (Pair<ElseTerminal, Statement> p : elseStatementList) {
            p.getValue0().accept(visitor);
            p.getValue1().accept(visitor);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(ifTerminal.toString());
        result.append(expression).append(thenTerminal).append(statement);
        for (Pair<ElseTerminal, Statement> p : elseStatementList) {
            result.append(p.getValue0().toString()).append(p.getValue1().toString());
        }
        return result.toString();
    }

    public String toFormula() {
        StringBuilder result = new StringBuilder();
        result.append("if(");
        result.append(expression.toFormula()).append(",");
        result.append("then(").append(statement.toFormula()).append(")");
        for (Pair<ElseTerminal, Statement> p : elseStatementList) {
            result.append(",else(").append(p.getValue1().toFormula()).append(")");
        }
        result.append(")");
        return result.toString();
    }

    public Expression getExpression() {
        return expression;
    }

    public Statement getStatement() {
        return statement;
    }

    public String toCString() {
        StringBuilder result = new StringBuilder(ifTerminal.toCString());
        result.append("(");
        result.append(expression.toCString());
        result.append(")");
        result.append(thenTerminal.toCString()).append(statement.toCString());
        for (Pair<ElseTerminal, Statement> p : elseStatementList) {
            result.append(p.getValue0().toCString()).append(p.getValue1().toCString());
        }
        return result.toString();
    }

}
