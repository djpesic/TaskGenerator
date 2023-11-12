/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstractions;

import syntaxelements.nonterminals.SimpleExpression;
import syntaxelements.nonterminals.StatementSequence;
import syntaxelements.terminals.Relop;

/**
 *
 * @author djordje
 */
public class IfSegment extends ProgramSegment {

    public IfSegment() {
    }

    private ProgramSegment thenSegment = null;
    private ProgramSegment elseSegment = null;

    private StatementSequence syntaxTree;

    @Override
    public String toString() {
        return syntaxTree.toString();
    }

    @Override
    public String toCString() {
        return syntaxTree.toCString();
    }

    @Override
    public StatementSequence getSyntaxTree() {
        return syntaxTree;
    }

    public void setSyntaxTree(StatementSequence syntaxTree) {
        this.syntaxTree = syntaxTree;
    }

}
