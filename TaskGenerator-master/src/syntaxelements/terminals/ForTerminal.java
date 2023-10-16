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
public class ForTerminal {

    private String tabs = "";

    public void setPrecedingTabs(int tabNumber) {
        for (int i = 0; i < tabNumber; i++) {
            tabs += "\t";
        }
    }

    @Override
    public String toString() {
        return tabs + "for ";
    }

    public String toCString() {
        return tabs + "for ";
    }

    public void accept(SyntaxTreeVisitor visitor) {
        visitor.visit(this);
    }
}
