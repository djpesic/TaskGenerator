/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unification;

import java.util.List;

/**
 *
 * @author djordje
 */
public class UPart {

    /*
        * Multiequations with zero counter
     */
    public List<MultiEquation> ZeroCounterMultiEq;
    /*
        * All initial multiequations
     */
    public List<MultiEquation> Equations;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Equations:\n");
        Equations.forEach((m) -> builder.append(m.toString()).append(";\n"));
        builder.append("Zero counter multiEqations:\n");
        ZeroCounterMultiEq.forEach((m) -> builder.append(m.toString()).append(";\n"));
        return builder.toString();
    }

    /*
        * Init counter variables in multiequations and find multieqations
        * with the zero counter value.
     */
    public void findZeroCounterMultiEquation() {
        for (int i = 0; i < Equations.size(); i++) {
            for (int j = 0; j < Equations.size(); j++) {
                if (i != j) {
                    MultiEquation first = Equations.get(i);
                    MultiEquation second = Equations.get(j);
                    //calcualte counter for the first, respected to the second
                    for (Variable v : first.s) {
                        first.counter += second.countVariable(v);
                    }
                }
            }
        }
        for (MultiEquation multiEq : Equations) {
            if (multiEq.counter == 0) {
                ZeroCounterMultiEq.add(multiEq);
            }
        }
    }

    public void connectVariablesToEquations() {
        for (int i = 0; i < Equations.size(); i++) {
            MultiEquation m1 = Equations.get(i);
            m1.connectLeftSideVariables();
            for (Variable v : m1.s) {
                for (int j = 0; j < Equations.size(); j++) {
                    MultiEquation m2 = Equations.get(j);
                    m2.connectRightSideVariables(v, m1);
                }
            }
        }
    }
}
