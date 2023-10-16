/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxelements.nonterminals;

import visitors.SyntaxTreeVisitor;
import syntaxelements.terminals.*;

/**
 *
 * @author djordje actual-parameter-list="(" actual-parameter ")"
 */
public class ActualParamList {

    private ParenthesisOpen pOpen;
    private ActualParam actualParam;
    private ParenthesisClose pClose;

    public ActualParamList(ActualParam param) {
        pOpen = new ParenthesisOpen();
        pClose = new ParenthesisClose();
        actualParam = param;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(pOpen).append(actualParam).append(pClose);
        return result.toString();
    }

    public void accept(SyntaxTreeVisitor visitor) {
        pOpen.accept(visitor);
        actualParam.accept(visitor);
        pClose.accept(visitor);
        visitor.visit(this);
    }

    public String toCString() {
        StringBuilder result = new StringBuilder();
        result.append(pOpen).append(actualParam.toCString()).append(pClose);
        return result.toString();
    }
}
