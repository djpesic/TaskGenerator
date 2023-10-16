/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxelements.terminals;

import visitors.SyntaxTreeVisitor;

/**
 *
 * @author djordje
 */
public class Downto {

    @Override
    public String toString() {
        return " downto ";
    }

    public void accept(SyntaxTreeVisitor visitor) {
        visitor.visit(this);
    }
}
