package abstractions;

import javax.naming.OperationNotSupportedException;

/**
 *
 */
public class ForLoop extends SimpleLoop {

    private String iteratorName;
    private String direction;

    @Override
    public String getLoopType() {
        return "for";
    }

    @Override
    public String getIteratorVariable() {
        return iteratorName;
    }

    @Override
    public void setIteratorVariable(String iterVarName) {
        iteratorName = iterVarName;
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
        throw new OperationNotSupportedException("Cant combine a loop with single for loop");
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

}
