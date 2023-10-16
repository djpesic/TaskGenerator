/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectors;

import programSegment.SimpleExprType;
import syntaxelements.nonterminals.StatementSequence;


/**
 *
 * @author djordje
 */
public abstract class LoopConnector {

    public StatementSequence connect(StatementSequence tree1, StatementSequence tree2) {
        return null;
    }

    public StatementSequence connect(StatementSequence tree1, StatementSequence tree2,
            String relop, SimpleExprType expr1, SimpleExprType expr2) {
        return null;
    }
}
