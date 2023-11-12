/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unification;

import java.util.List;


/*
    * Multieqation: S=M. S is the set of variables, M is the set of nonvariable terms.
 */
public class MultiEquation {

    /*
        * The number which shows how many times the variables in the left side 
        * of this multieqation occurs in the other multiEqations
        * (respecting the UPart only).
     */
    public long counter;
    /*
        * Left side of multiEquation.
     */
    public List<Variable> s;
    /*
        * Right side of multiEquation
     */
    public MultiTerm m;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("cnt[").append(counter).append("]");
        builder.append("{");
        if (s != null) {
            s.forEach((var) -> builder.append(var.varName).append(","));
            builder.deleteCharAt(builder.length() - 1);
        }
        builder.append("}=");
        if (m != null) {
            builder.append(m.toString());
        }
        builder.append(";\n");
        return builder.toString();
    }

    public static void copy(MultiEquation dst, MultiEquation src) {
        dst.counter = src.counter;
        dst.m = src.m;
        dst.s = src.s;
    }

    /*
        * Iterate through all variables inside this object and connects its 'm' field to the this object
     */
    public void connectRightSideVariables(Variable v, MultiEquation mult) {
        if (m != null) {
            m.connectToEquation(v, mult);
        }
    }

    public void connectLeftSideVariables() {
        if (s != null) {
            s.forEach((var) -> {
                var.m = this;
            });
        }
    }

    //count the apperances of the v.varName in the multiequation
    public long countVariable(Variable v) {
        long ret = 0;
        if (s != null) {
            ret += s.stream().filter((var) -> var.varName.equals(v.varName)).count();
        }
        if (m != null) {
            ret += m.countVariable(v);
        }
        return ret;
    }
}
