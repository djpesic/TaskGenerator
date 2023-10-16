/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unification;

import java.util.List;

/*
    * Multiterm is build from the multiset of consistent terms. The two terms are consistent
    * if the least one of them is a variable, or both have the same root function 
    * and their pairwise arguments are consistent
 */
public class MultiTerm {

    /*
        * Function symbol
     */
    public String fSymb;
    /*
        * Multiterm argments
     */
    public List<TempMultiEq> args;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(fSymb);
        if (args != null) {
            builder.append("(");
            args.forEach((TempMultiEq arg) -> {
                if (arg != null) {
                    builder.append(arg.toString()).append(",");
                }
            });
            builder.deleteCharAt(builder.length() - 1);
            builder.append(")");
        }
        return builder.toString();
    }

    public static void copy(MultiTerm dst, MultiTerm src) {
        dst.fSymb = src.fSymb;
        dst.args = src.args;
    }

    /*
        * Add reference to the multieqation to all variables inside the multiterm
     */
    public void connectToEquation(Variable v, MultiEquation multEQ) {
        if (args != null) {
            for (TempMultiEq tmp : args) {
                if (tmp != null) {
                    if (tmp.s != null) {
                        for (Variable var : tmp.s) {
                            if (var.varName.equals(v.varName)) {
                                var.m = multEQ;
                            }
                        }
                    }
                    if (tmp.m != null) {
                        tmp.m.connectToEquation(v, multEQ);
                    }
                }
            }
        }
    }

    public long countVariable(Variable v) {
        long ret = 0;
        if (args != null) {
            for (TempMultiEq tmp : args) {
                ret += tmp.countVariable(v);
            }
        }
        return ret;
    }
}
