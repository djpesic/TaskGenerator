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
public class StringTerminal {

    private String terminalValue;

    public StringTerminal(String val) {
        terminalValue = val;
    }

    @Override
    public String toString() {
        return terminalValue;
    }

    public void accept(SyntaxTreeVisitor visitor) {
        visitor.visit(this);
    }
}
