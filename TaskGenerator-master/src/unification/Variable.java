/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unification;

public class Variable {

    /*
        * Variable name
     */
    public String varName;
    /*
        *   Multiequation containing varName on its left side.
     */
    public MultiEquation m;

    public static void copy(Variable dst, Variable src) {
        dst.varName = src.varName;
        dst.m = src.m;
    }

    @Override
    public String toString() {
        return varName;
    }
}
