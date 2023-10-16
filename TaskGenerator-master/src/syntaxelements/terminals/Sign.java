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
public class Sign {

    private String sign;

    public Sign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        if (sign == null) {
            return "";
        }
        return sign;
    }

    public void accept(SyntaxTreeVisitor visitor) {
        visitor.visit(this);
    }

    public String toFormula() {
        switch (sign) {
            case "+":
                return "add";
            case "-":
                return "sub";
            case "":
                return "";
        }
        return null;
    }

    public String getSign() {
        return sign;
    }
}
