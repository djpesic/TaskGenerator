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
 * @author djordje function-designator=ident [actual-param-list]
 */
public class FunctionDesignator {

    private Ident ident;
    private ActualParamList actualParamList;

    public FunctionDesignator(Ident ident, ActualParamList actParList) {
        this.ident = ident;
        actualParamList = actParList;
    }

    @Override
    public String toString() {
        return ident.toString() + actualParamList;
    }

    public void accept(SyntaxTreeVisitor visitor) {
        ident.accept(visitor);
        actualParamList.accept(visitor);
        visitor.visit(this);
    }

    public String toCString() {
        return ident.toString() + actualParamList.toCString();
    }
}
