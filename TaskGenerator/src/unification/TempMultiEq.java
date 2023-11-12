/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unification;

import java.util.List;

/*
    * s and m must not be both empty
 */
public class TempMultiEq {

    /*
        * Variable list. Can be empty.
     */
    public List<Variable> s;
    /*
        Innter multiterm, containing variables from s. Can be empty
     */
    public MultiTerm m;

    public static void copy(TempMultiEq dst, TempMultiEq src) {
        dst.m = src.m;
        dst.s = src.s;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("<{");
        if (s != null) {
            s.forEach((var) -> builder.append(var.varName).append(","));
            builder.deleteCharAt(builder.length() - 1);
        }
        
        builder.append("},");
        builder.append(m).append(">");
        return builder.toString();
    }

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
