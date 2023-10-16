/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectors;

import syntaxelements.nonterminals.StatementSequence;
import visitors.SimpleNesterVisitor;

/**
 *
 * @author djordje
 */
public class SimpleNester extends LoopConnector {

    //Nest tree2 into tree1. 
    @Override
    public StatementSequence connect(StatementSequence tree1, StatementSequence tree2) {
        if (tree1 == null) {
            return tree2;
        }
        if (tree2 == null) {
            return tree1;
        }
        SimpleNesterVisitor nesterVisitor = new SimpleNesterVisitor(tree2);
        tree1.accept(nesterVisitor);
        return tree1;
    }

}
