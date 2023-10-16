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
public class GroupNode extends ComplexityNode {

    private ComplexityNode group;

    public GroupNode(ComplexityNode group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "(" + group + ")";
    }

    public ComplexityNode getGroup() {
        return group;
    }

    public void setGroup(ComplexityNode group) {
        this.group = group;
    }

    @Override
    public void applySubFunc(GroupNode subFunc, String varName) {
        if (group instanceof VarNode) {
            VarNode v = (VarNode) group;
            if (v.getVar().equals(varName)) {
                group = subFunc;
            }
        } else {
            group.applySubFunc(subFunc, varName);
        }
    }

    @Override
    public void accept(ComplexityVisitor visitor) {
        group.accept(visitor);
        visitor.visit(this);
    }

}
