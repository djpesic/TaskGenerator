/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectors;

import syntaxelements.nonterminals.*;

/**
 *
 * @author djordje
 */
public class SimpleSequencer extends LoopConnector {

    //Place tree2 after tree1
    @Override
    public StatementSequence connect(StatementSequence tree1, StatementSequence tree2) {
        if (tree1 == null) {
            return tree2;
        }
        if (tree2 == null) {
            return tree1;
        }
        tree1.addToOptionalList(tree2);

        return tree1;
    }
}
