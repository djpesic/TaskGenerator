/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxelements.nonterminals;

import visitors.SyntaxTreeVisitor;
import java.util.*;
import org.javatuples.Pair;
import syntaxelements.terminals.*;

/**
 *
 * @author djordje indexedVar=IDENT "[" expression { "," expression } "]"
 */
public class IndexedVar {

    private Ident ident;
    private BracketOpen bOpen;
    private Expression expression;
    private List<Pair<Comma, Expression>> commaExprList = new LinkedList<>();
    private BracketClose bClose;

    public IndexedVar(Ident ident) {
        this.ident = ident;
    }

    public void addExpression(Expression indexExpr) {
        bOpen = new BracketOpen();
        expression = indexExpr;
        bClose = new BracketClose();
    }

    public void addIndexExpression(List<Expression> indexExprs) {
        Pair<Comma, Expression> commaExpr;

        bOpen = new BracketOpen();
        expression = indexExprs.get(0);
        for (int i = 1; i < indexExprs.size(); i++) {
            commaExpr = new Pair<>(new Comma(), indexExprs.get(i));
            commaExprList.add(commaExpr);
        }
        bClose = new BracketClose();
    }

    protected IndexedVar() {
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(ident).append(bOpen).append(expression);
        for (Pair p : commaExprList) {
            result.append(p.getValue0().toString());
            result.append(p.getValue1().toString());
        }
        result.append(bClose);
        return result.toString();
    }

    public void accept(SyntaxTreeVisitor visitor) {
        ident.accept(visitor);
        bOpen.accept(visitor);
        expression.accept(visitor);
        for (Pair<Comma, Expression> p : commaExprList) {
            p.getValue0().accept(visitor);
            p.getValue1().accept(visitor);
        }
        bClose.accept(visitor);
        visitor.visit(this);
    }

    public String toFormula() {
        StringBuilder result = new StringBuilder();
        result.append("index(").append(ident);
        commaExprList.forEach((var) -> result.append(",").append(var.getValue1().toFormula()));
        result.append(")");
        return result.toString();
    }

    public String toCString() {
        StringBuilder result = new StringBuilder();
        result.append(ident).append(bOpen).append(expression.toCString()).append(bClose);
        for (Pair<Comma,Expression> p : commaExprList) {
            result.append(bOpen).append(p.getValue1().toCString()).append(bClose);
        }
        return result.toString();
    }
}
