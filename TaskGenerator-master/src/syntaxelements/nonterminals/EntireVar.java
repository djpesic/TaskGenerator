/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxelements.nonterminals;

import visitors.SyntaxTreeVisitor;
import syntaxelements.terminals.Ident;

/**
 *
 * @author djordje entireVar=IDENT
 */
public class EntireVar {

    private Ident ident;

    public EntireVar(Ident ident) {
        this.ident = ident;
    }

    @Override
    public String toString() {
        return ident.toString();
    }

    public void accept(SyntaxTreeVisitor visitor) {
        ident.accept(visitor);
        visitor.visit(this);
    }

    public String toFormula() {
        return ident.toString();
    }
}
