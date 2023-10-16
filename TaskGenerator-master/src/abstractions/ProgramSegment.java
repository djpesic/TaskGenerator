package abstractions;

import complexity.Complexity;
import javax.naming.OperationNotSupportedException;
import syntaxelements.nonterminals.StatementSequence;

/**
 *
 */
public abstract class ProgramSegment {

    protected Complexity complexity;

    public ProgramSegment() {
    }

    @Override
    public abstract String toString();
    
    public abstract String toCString();

    public void combineWith(ProgramSegment program) throws OperationNotSupportedException {
    }

    public abstract StatementSequence getSyntaxTree();

    public Complexity getComplexity() {
        return complexity;
    }

    public void setComplexity(Complexity complexity) {
        this.complexity = complexity;
    }

}
