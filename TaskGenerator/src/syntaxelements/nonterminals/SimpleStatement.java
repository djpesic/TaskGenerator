/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxelements.nonterminals;

import visitors.SyntaxTreeVisitor;

/**
 *
 * @author djordje simpleStatement=assignment-statement
 */
public class SimpleStatement {

    private AssignmentStatement assignment;

    @Override
    public String toString() {
        return assignment.toString();
    }

    public SimpleStatement(AssignmentStatement st) {
        assignment = st;
    }

    protected SimpleStatement() {
    }

    public void accept(SyntaxTreeVisitor visitor) {
        assignment.accept(visitor);
        visitor.visit(this);
    }

    public AssignmentStatement getAssignment() {
        return assignment;
    }

    public String toFormula() {
        return assignment.toFormula();
    }

    public String toCString() {
        return assignment.toCString();
    }

}
