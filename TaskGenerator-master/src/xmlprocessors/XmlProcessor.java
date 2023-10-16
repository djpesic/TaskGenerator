/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlprocessors;

import abstractions.ConstSegment;
import abstractions.IfSegment;
import abstractions.Loop;
import abstractions.ProgramSegment;
import abstractions.ProgramSequence;
import gui.treedata.TGException;
import syntaxelements.nonterminals.Expression;
import syntaxelements.nonterminals.Factor;
import syntaxelements.nonterminals.SignedFactor;
import syntaxelements.nonterminals.SimpleExpression;
import syntaxelements.nonterminals.Term;
import syntaxelements.nonterminals.Variable;
import programSegment.CondType;
import programSegment.ConstType;
import programSegment.FactorType;
import programSegment.ForType;
import programSegment.IfType;
import programSegment.LoopType;
import programSegment.Nested;
import programSegment.ProgramType;
import programSegment.Sequence;
import programSegment.SignedFactorType;
import programSegment.SimpleExprType;
import programSegment.SimpleLoop;
import programSegment.TermType;

/**
 *
 * @author djordje
 */
public interface XmlProcessor {

    ProgramSegment processProgramSegment(ProgramType programType) throws TGException;

    IfSegment processIf(IfType ifType) throws TGException;

    Loop processCondLoop(CondType c, boolean isWhile);

    ConstSegment processConst(ConstType xmlConst);

    Loop processForLoop(ForType f);

    ProgramSegment processNested(Nested nested) throws TGException;

    ProgramSequence processSequence(Sequence sequence) throws TGException;

    Loop processSimpleLoop(SimpleLoop xmlLoop);

    Factor processXmlFactor(FactorType xmlFactor);

    LoopType processLoopType(LoopType loopType) throws TGException;

    SignedFactor processXmlSignedFactor(SignedFactorType xmlSignedFactor);

    Variable processXmlIndexed(FactorType.Var.Indexed xmlIndexed);

    SimpleExpression processXmlSimpleExpr(SimpleExprType xmlSimpleExpr);

    Expression processXmlSubExpr(FactorType.SubExpr xmlSubExpr);

    Term processXmlTerm(TermType xmlTerm);

    Variable processXmlVar(FactorType.Var xmlVar);

    public void startProcessing() throws TGException;
}
