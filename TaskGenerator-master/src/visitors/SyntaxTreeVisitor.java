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
public interface SyntaxTreeVisitor {

    public void visit(Empty empty);

    public void visit(ActualParam param);

    public void visit(ActualParamList paramList);

    public void visit(AssignmentStatement assignStat);

    public void visit(CompoundStatement compStat);

    public void visit(EntireVar entVar);

    public void visit(Expression expr);

    public void visit(Factor factor);

    public void visit(For forLoop);

    public void visit(FunctionDesignator funDesignator);

    public void visit(IndexedVar indexVar);

    public void visit(Repeat repeat);

    public void visit(RepetitiveStatement repStat);

    public void visit(SimpleExpression simpleExpr);

    public void visit(SimpleStatement simpleStat);

    public void visit(Statement stat);

    public void visit(StatementSequence statSeq);

    public void visit(StructuredStatement strucStat);

    public void visit(Term term);

    public void visit(Variable var);

    public void visit(While whileLoop);

    public void visit(Addop addop);

    public void visit(Begin begin);

    public void visit(BracketClose bracketClose);

    public void visit(BracketOpen bracketOpen);

    public void visit(Comma comma);

    public void visit(Do doTerminal);

    public void visit(DoublecolonEquals dce);

    public void visit(Downto dt);

    public void visit(End end);

    public void visit(ForTerminal ft);

    public void visit(Ident ident);

    public void visit(Mulop mulop);

    public void visit(Not not);

    public void visit(NumberTerminal numTerm);

    public void visit(ParenthesisClose parClose);

    public void visit(ParenthesisOpen parOpen);

    public void visit(Relop relop);

    public void visit(RepeatTerminal rt);

    public void visit(Semicolon semi);

    public void visit(Sign sgn);

    public void visit(StringTerminal str);

    public void visit(To to);

    public void visit(Until until);

    public void visit(WhileTerminal wTerm);

    public void visit(ConditionalStatement condStat);

    public void visit(IfStatement ifStat);

    public void visit(IfTerminal ifStat);

    public void visit(ThenTerminal ifStat);

    public void visit(ElseTerminal ifStat);

    public void visit(SignedFactor signedFactor);

}
