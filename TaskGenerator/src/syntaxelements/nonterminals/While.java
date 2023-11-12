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
 * @author djordje while expression do statement
 */
public class While {

    private WhileTerminal whileTerm;
    private Expression expression;
    private Do doTerm;
    private Statement statement;

    public While(Expression expr, Statement stat) {
        whileTerm = new WhileTerminal();
        expression = expr;
        statement = stat;
        doTerm = new Do();
    }

    @Override
    public String toString() {
        return whileTerm.toString() + expression + doTerm + statement;
    }

    public void accept(SyntaxTreeVisitor visitor) {
        whileTerm.accept(visitor);
        expression.accept(visitor);
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

    public String toFormula() {
        StringBuilder result = new StringBuilder("while(");
        result.append(expression.toFormulaForWhileLoop()).append(",").append(statement.toFormula());
        if (result.charAt(result.length() - 1) == ',') {
            result.deleteCharAt(result.length() - 1);
        }
        result.append(")");
        return result.toString();
    }

    public String toCString() {
        return whileTerm.toCString() +"("+ expression.toCString()+")" + "\n" + statement.toCString();
    }
}
