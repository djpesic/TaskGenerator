/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitors;

import syntaxelements.nonterminals.*;
import syntaxelements.terminals.*;

/**
 *
 * @author djordje
 */
public class SyntaxTreeVisitorAdapter implements SyntaxTreeVisitor {

    @Override
    public void visit(ActualParam param) {
    }

    @Override
    public void visit(ActualParamList paramList) {
    }

    @Override
    public void visit(AssignmentStatement assignStat) {
    }

    @Override
    public void visit(CompoundStatement compStat) {
    }

    @Override
    public void visit(EntireVar entVar) {
    }

    @Override
    public void visit(Expression expr) {
    }

    @Override
    public void visit(Factor factor) {
    }

    @Override
    public void visit(For forLoop) {
    }

    @Override
    public void visit(FunctionDesignator funDesignator) {
    }

    @Override
    public void visit(IndexedVar indexVar) {
    }

    @Override
    public void visit(Repeat repeat) {
    }

    @Override
    public void visit(RepetitiveStatement repStat) {
    }

    @Override
    public void visit(SimpleExpression simpleExpr) {
    }

    @Override
    public void visit(SimpleStatement simpleStat) {
    }

    @Override
    public void visit(Statement stat) {
    }

    @Override
    public void visit(StatementSequence statSeq) {
    }

    @Override
    public void visit(StructuredStatement strucStat) {
    }

    @Override
    public void visit(Term term) {
    }

    @Override
    public void visit(Variable var) {
    }

    @Override
    public void visit(While whileLoop) {
    }

    @Override
    public void visit(Addop addop) {
    }

    @Override
    public void visit(Begin begin) {
    }

    @Override
    public void visit(BracketClose bracketClose) {
    }

    @Override
    public void visit(BracketOpen bracketOpen) {
    }

    @Override
    public void visit(Comma comma) {
    }

    @Override
    public void visit(Do doTerminal) {
    }

    @Override
    public void visit(DoublecolonEquals dce) {
    }

    @Override
    public void visit(Downto dt) {
    }

    @Override
    public void visit(End end) {
    }

    @Override
    public void visit(ForTerminal ft) {
    }

    @Override
    public void visit(Ident ident) {
    }

    @Override
    public void visit(Mulop mulop) {
    }

    @Override
    public void visit(Not not) {
    }

    @Override
    public void visit(NumberTerminal numTerm) {
    }

    @Override
    public void visit(ParenthesisClose parClose) {
    }

    @Override
    public void visit(ParenthesisOpen parOpen) {
    }

    @Override
    public void visit(Relop relop) {
    }

    @Override
    public void visit(RepeatTerminal rt) {
    }

    @Override
    public void visit(Semicolon semi) {
    }

    @Override
    public void visit(Sign sgn) {
    }

    @Override
    public void visit(StringTerminal str) {
    }

    @Override
    public void visit(To to) {
    }

    @Override
    public void visit(Until until) {
    }

    @Override
    public void visit(WhileTerminal wTerm) {
    }

    @Override
    public void visit(Empty empty) {
    }

    @Override
    public void visit(IfStatement empty) {
    }

    @Override
    public void visit(ConditionalStatement empty) {
    }

    @Override
    public void visit(IfTerminal ifStat) {
    }

    @Override
    public void visit(ThenTerminal thenTerminal) {
    }

    @Override
    public void visit(ElseTerminal elseTerminal) {
    }
    
    @Override
    public void visit(SignedFactor signFactor) {
    }
}
