/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complexity;

import visitors.ComplexityVisitor;

/**
 *
 * @author djordje
 */
public class MulNode extends ComplexityNode {

    private ComplexityNode op1, op2;

    public MulNode(ComplexityNode op1, ComplexityNode op2) {
        this.op1 = op1;
        this.op2 = op2;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(op1).append("*").append(op2);
        return builder.toString();
    }

    public ComplexityNode getOp1() {
        return op1;
    }

    public void setOp1(ComplexityNode op1) {
        this.op1 = op1;
    }

    public ComplexityNode getOp2() {
        return op2;
    }

    public void setOp2(ComplexityNode op2) {
        this.op2 = op2;
    }

    @Override
    public void applySubFunc(GroupNode subFunc, String varName) {
        if (op1 instanceof VarNode) {
            VarNode v = (VarNode) op1;
            if (v.getVar().equals(varName)) {
                op1 = subFunc;
            }
        } else {
            op1.applySubFunc(subFunc, varName);
        }

        if (op2 instanceof VarNode) {
            VarNode v = (VarNode) op2;
            if (v.getVar().equals(varName)) {
                op2 = subFunc;
            }
        } else {
            op2.applySubFunc(subFunc, varName);
        }
    }

    @Override
    public void accept(ComplexityVisitor visitor) {
        op1.accept(visitor);
        op2.accept(visitor);
        visitor.visit(this);
    }

}
