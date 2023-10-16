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
 * @author djordje factor=variable | number | string | not factor | "("
 * expression ") | function_designator"
 */
public class Factor {

    private Variable variable;
    private NumberTerminal number;
    private StringTerminal string;
    private Not not;
    private Factor factor;
    private ParenthesisOpen pOpen;
    private ParenthesisClose pClose;
    private Expression expression;
    private FunctionDesignator fDesignator;

    public Factor(Variable var) {
        variable = var;
    }

    public Factor(NumberTerminal num) {
        number = num;
    }

    public Factor(StringTerminal str) {
        string = str;
    }

    public Factor(Factor f) {
        not = new Not();
        factor = f;
    }

    public Factor(Expression expr) {
        pOpen = new ParenthesisOpen();
        expression = expr;
        pClose = new ParenthesisClose();
    }

    public Factor(FunctionDesignator designator) {
        fDesignator = designator;
    }

    protected Factor() {
    }

    @Override
    public String toString() {
        if (variable != null) {
            return variable.toString();
        }
        if (number != null) {
            return number.toString();
        }
        if (string != null) {
            return string.toString();
        }
        if (factor != null) {
            return not.toString() + " " + factor.toString();
        }
        if (expression != null) {
            return pOpen.toString() + expression.toString() + pClose.toString();
        }
        if (fDesignator != null) {
            return fDesignator.toString();
        }
        return null;
    }

    public void accept(SyntaxTreeVisitor visitor) {
        if (variable != null) {
            variable.accept(visitor);
        }
        if (number != null) {
            number.accept(visitor);
        }
        if (string != null) {
            string.accept(visitor);
        }
        if (factor != null) {
            not.accept(visitor);
            factor.accept(visitor);
        }
        if (expression != null) {
            pOpen.accept(visitor);
            expression.accept(visitor);
            pClose.accept(visitor);
        }
        if (fDesignator != null) {
            fDesignator.toString();
        }
        visitor.visit(this);
    }

    public String toFormula() {
        if (variable != null) {
            return variable.toFormula();
        }
        if (string != null) {
            return string.toString();
        }
        if (expression != null) {
            return expression.toFormula();
        }
        if (fDesignator != null) {
            return fDesignator.toString();
        }
        if (factor != null) {
            return "not(" + factor.toFormula() + ")";
        }
        if (number != null) {
            return number.toString();
        }
        return null;
    }

    public String toCString() {
        if (variable != null) {
            return variable.toCString();
        }
        if (number != null) {
            return number.toString();
        }
        if (string != null) {
            return string.toString();
        }
        if (factor != null) {
            return not.toCString() + " " + factor.toCString();
        }
        if (expression != null) {
            return pOpen.toString() + expression.toCString() + pClose.toString();
        }
        if (fDesignator != null) {
            return fDesignator.toCString();
        }
        return null;
    }
}
