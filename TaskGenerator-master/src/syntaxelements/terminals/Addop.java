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
public class Addop {

    private String op;

    public Addop(String op) {
        this.op = op;
    }

    @Override
    public String toString() {
        return " " + op + " ";
    }

    public void accept(SyntaxTreeVisitor visitor) {
        visitor.visit(this);
    }

    public String toFormula() {
        switch (op) {
            case "+":
                return "add";
            case "-":
                return "sub";
            case "or":
                return "or";
        }
        return null;
    }

    public String toCString() {
        switch (op) {
            case "+":
                return "+";
            case "-":
                return "-";
            case "or":
                return "||";
        }
        return null;
    }
}
