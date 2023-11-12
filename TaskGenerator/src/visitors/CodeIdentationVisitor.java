/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitors;

import syntaxelements.nonterminals.AssignmentStatement;
import syntaxelements.terminals.*;

/**
 *
 * @author djordje
 */
public class CodeIdentationVisitor extends SyntaxTreeVisitorAdapter {

    public int tabLevel = 0;

    @Override
    public void visit(AssignmentStatement assignStat) {
        assignStat.setPrecedingTabs(tabLevel);
    }

    @Override
    public void visit(Begin begin) {
        begin.setPrecedingTabs(tabLevel);
        tabLevel++;
    }

    @Override
    public void visit(End end) {
        tabLevel--;
        end.setPrecedingTabs(tabLevel);
    }

    @Override
    public void visit(ForTerminal ft) {
        ft.setPrecedingTabs(tabLevel);
    }

    @Override
    public void visit(RepeatTerminal rt) {
        rt.setPrecedingTabs(tabLevel);
    }

    @Override
    public void visit(Until until) {
        until.setPrecedingTabs(tabLevel);
    }

    @Override
    public void visit(WhileTerminal wTerm) {
        wTerm.setPrecedingTabs(tabLevel);
    }

    @Override
    public void visit(IfTerminal ifTerm) {
        ifTerm.setPrecedingTabs(tabLevel);
    }

    @Override
    public void visit(ElseTerminal elseTerm) {
        elseTerm.setPrecedingTabs(tabLevel);
    }

}
