/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxelements.nonterminals;

import syntaxelements.terminals.Sign;
import visitors.SyntaxTreeVisitor;

/**
 * signedFactor = [sign] factor
 *
 * @author djordje
 */
public class SignedFactor {

    private Sign sign;
    private Factor factor;

    public SignedFactor(Sign sign, Factor factor) {
        this.sign = sign;
        this.factor = factor;
    }

    public SignedFactor() {
    }

    public void accept(SyntaxTreeVisitor visitor) {
        if (sign != null) {
            sign.accept(visitor);
        }
        factor.accept(visitor);
        visitor.visit(this);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (sign != null) {
            result.append(sign);
        }
        result.append(factor);
        return result.toString();
    }

    public Sign getSign() {
        return sign;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    public Factor getFactor() {
        return factor;
    }

    public void setFactor(Factor factor) {
        this.factor = factor;
    }

    public String toFormula() {
        StringBuilder result = new StringBuilder();
        if (sign.getSign() != null && !sign.getSign().isEmpty()) {
            result.append(sign.toFormula()).append("(").append(factor.toFormula()).append(")");
        } else {
            result.append(factor.toFormula());
        }
        return result.toString();
    }

    public String toCString() {
        StringBuilder result = new StringBuilder();
        if (sign != null) {
            result.append(sign);
        }
        result.append(factor.toCString());
        return result.toString();
    }

}
