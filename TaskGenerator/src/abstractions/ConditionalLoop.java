/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstractions;

import javax.naming.OperationNotSupportedException;

/**
 *
 * @author djordje
 */
public abstract class ConditionalLoop extends SimpleLoop {

    private String condition;
    private String operation;
    private String operand;

    @Override
    public abstract String getLoopType();

    @Override
    public String getIteratorVariable() {
        return loopVars[0];
    }

    @Override
    public void setIteratorVariable(String iterVarName) {
        loopVars[0] = iterVarName;
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
        throw new OperationNotSupportedException("Cant combine a simple loop with anything");
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperand() {
        return operand;
    }

    public void setOperand(String operand) {
        this.operand = operand;
    }

}
