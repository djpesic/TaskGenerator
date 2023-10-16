/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitors;

import syntaxelements.nonterminals.*;

/**
 *
 * @author djordje
 */
public class SimpleNesterVisitor extends SyntaxTreeVisitorAdapter {

    private StatementSequence toNest;

    public SimpleNesterVisitor(StatementSequence toNest) {
        this.toNest = toNest;
    }

    @Override
    public void visit(For stat) {
        stat.nestStatementSequence(toNest);
    }

    @Override
    public void visit(While stat) {
        stat.nestStatementSequence(toNest);
    }

    @Override
    public void visit(Repeat stat) {
        stat.nestStatementSequence(toNest);
    }
}
