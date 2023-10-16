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
public class Mulop {

    private String op;

    public Mulop(String op) {
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
            case "*":
                return "mul";
            case "/":
                return "divF";
            case "div":
                return "divI";
            case "mod":
                return "mod";
            case "and":
                return "and";
        }
        return null;
    }

    public String toCString() {
        switch (op) {
            case "*":
                return "*";
            case "/":
                return "/";
            case "div":
                return "/";
            case "mod":
                return "%";
            case "and":
                return "&&";
        }
        return null;
    }
}
