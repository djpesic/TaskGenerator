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
public class SqrtNode extends ComplexityNode {

    private ComplexityNode arg;

    public SqrtNode(ComplexityNode arg) {
        this.arg = arg;
    }

    @Override
    public String toString() {
        return "Sqrt(" + arg + ")";
    }

    public ComplexityNode getArg() {
        return arg;
    }

    public void setArg(ComplexityNode arg) {
        this.arg = arg;
    }

    @Override
    public void applySubFunc(GroupNode subFunc, String varName) {
        if (arg instanceof VarNode) {
            VarNode v = (VarNode) arg;
            if (v.getVar().equals(varName)) {
                arg = subFunc;
            }
        } else {
            arg.applySubFunc(subFunc, varName);
        }
    }

    @Override
    public void accept(ComplexityVisitor visitor) {
        arg.accept(visitor);
        visitor.visit(this);
    }

}
