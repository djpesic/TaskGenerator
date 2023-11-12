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
public class MultiEqSystem {

    /*
        * Solved part
     */
    public List<MultiEquation> t;
    /*
        * Unsolved part
     */
    public UPart u;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Unsolved\n");
        builder.append(u.toString()).append("\n");
        builder.append("Solved:\n");
        t.forEach((m) -> builder.append(m.toString()).append("\n"));
        return builder.toString();
    }
}
