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
public class ExpNode extends ComplexityNode {

    private ComplexityNode base;
    private ComplexityNode exponent;

    public ExpNode(ComplexityNode base, ComplexityNode exponent) {
        this.base = base;
        this.exponent = exponent;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        builder.append(base).append(")").append("^")
                .append("(").append(exponent).append(")");
        return builder.toString();
    }

    public ComplexityNode getBase() {
        return base;
    }

    public void setBase(ComplexityNode base) {
        this.base = base;
    }

    public ComplexityNode getExponent() {
        return exponent;
    }

    public void setExponent(ComplexityNode exponent) {
        this.exponent = exponent;
    }

    @Override
    public void applySubFunc(GroupNode subFunc, String varName) {
        if (base instanceof VarNode) {
            VarNode v = (VarNode) base;
            if (v.getVar().equals(varName)) {
                base = subFunc;
            }
        } else {
            base.applySubFunc(subFunc, varName);
        }
        if (exponent instanceof VarNode) {
            VarNode v = (VarNode) exponent;
            if (v.getVar().equals(varName)) {
                exponent = subFunc;
            }
        } else {
            exponent.applySubFunc(subFunc, varName);
        }
    }

    @Override
    public void accept(ComplexityVisitor visitor) {
        base.accept(visitor);
        exponent.accept(visitor);
        visitor.visit(this);
    }

}
