/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstractions;

import complexity.Complexity;
import javax.naming.OperationNotSupportedException;
import syntaxelements.nonterminals.StatementSequence;

/**
 *
 * @author djordje
 */
public class ConstSegment extends ProgramSegment {

    private StatementSequence syntaxTree;

    public ConstSegment() {
        complexity = Complexity.createConstant();
    }

    @Override
    public String toString() {
        return syntaxTree.toString();
    }

    @Override
    public String toCString() {
        return syntaxTree.toCString();
    }

    @Override
    public void combineWith(ProgramSegment prog) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Cant combine a loop with constant segment");
    }

    @Override
    public StatementSequence getSyntaxTree() {
        return syntaxTree;
    }

    public void setSyntaxTree(StatementSequence statSeq) {
        syntaxTree = statSeq;
    }
}
