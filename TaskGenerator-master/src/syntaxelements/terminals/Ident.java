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
public class Ident {

    private String name;

    @Override
    public String toString() {
        return name;
    }

    public Ident(String n) {
        name = n;
    }

    public void accept(SyntaxTreeVisitor visitor) {
        visitor.visit(this);
    }
}
