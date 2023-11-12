/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complexity;

import visitors.ComplexityVisitor;

/**
 *
 * This class is placeholder for invalid value, when parser for complexity
 * functions can't create valid complexity from string
 */
public class NoneNode extends ComplexityNode {

    @Override
    public String toString() {
        return "none";
    }

    @Override
    public void applySubFunc(GroupNode subFunc, String varName) {
    }

    @Override
    public void accept(ComplexityVisitor visitor) {
        visitor.visit(this);
    }

}
