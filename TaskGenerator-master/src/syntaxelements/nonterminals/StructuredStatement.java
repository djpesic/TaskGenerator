/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxelements.nonterminals;

import visitors.SyntaxTreeVisitor;

/**
 *
 * @author djordje structuredStatement=compound-statement | repetitive-statement
 * | conditional-statement
 */
public class StructuredStatement {

    private CompoundStatement compound;
    private RepetitiveStatement repetitive;
    private ConditionalStatement conditional;

    public StructuredStatement(CompoundStatement stat) {
        compound = stat;
    }

    public StructuredStatement(RepetitiveStatement stat) {
        repetitive = stat;
    }

    public StructuredStatement(ConditionalStatement conditional) {
        this.conditional = conditional;
    }

    protected StructuredStatement() {
    }

    @Override
    public String toString() {
        if (compound != null) {
            return compound.toString();
        }
        if (conditional != null) {
            return conditional.toString();
        }
        return repetitive.toString();
    }

    public void accept(SyntaxTreeVisitor visitor) {
        if (compound != null) {
            compound.accept(visitor);

        } else if (conditional != null) {
            conditional.accept(visitor);
        } else {
            repetitive.accept(visitor);
        }
        visitor.visit(this);
    }

    public CompoundStatement getCompound() {
        return compound;
    }

    public RepetitiveStatement getRepetitive() {
        return repetitive;
    }

    public ConditionalStatement getConditional() {
        return conditional;
    }

    public String toFormula() {
        if (compound != null) {
            return compound.toFormula();
        }
        if (repetitive != null) {
            return repetitive.toFormula();
        }
        return conditional.toFormula();
    }

    public String toCString() {
        if (compound != null) {
            return compound.toCString();
        }
        if (conditional != null) {
            return conditional.toCString();
        }
        return repetitive.toCString();
    }

}
