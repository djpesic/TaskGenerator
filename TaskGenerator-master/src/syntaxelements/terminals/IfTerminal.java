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
public class IfTerminal {

    private String tabs = "";

    public void setPrecedingTabs(int tabNumber) {
        for (int i = 0; i < tabNumber; i++) {
            tabs += "\t";
        }
    }

    @Override
    public String toString() {
        return tabs + "if ";
    }

    public String toCString() {
        return tabs + "if ";
    }

    public void accept(SyntaxTreeVisitor visitor) {
        visitor.visit(this);
    }
}
