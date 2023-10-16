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
public class NumNode extends ComplexityNode {

    private int number;

    public NumNode(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "" + number;
    }

    @Override
    public void applySubFunc(GroupNode subFunc, String varName) {
    }

    @Override
    public void accept(ComplexityVisitor visitor) {
        visitor.visit(this);
    }
}
