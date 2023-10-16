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
public abstract class ComplexityNode {

    @Override
    public abstract String toString();

    public abstract void applySubFunc(GroupNode subFunc, String varName);

    public abstract void accept(ComplexityVisitor visitor);
}
