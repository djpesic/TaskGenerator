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
 * @author rtrk
 */
public class XmlProcessorAdapter implements XmlProcessor {

    @Override
    public ProgramSegment processProgramSegment(ProgramType programType) throws TGException {
        throw new UnsupportedOperationException("Do not use adapter directly.");
    }

    @Override
    public IfSegment processIf(IfType ifType) throws TGException {
        throw new UnsupportedOperationException("Do not use adapter directly.");
    }

    @Override
    public Loop processCondLoop(CondType c, boolean isWhile) {
        throw new UnsupportedOperationException("Do not use adapter directly.");
    }

    @Override
    public ConstSegment processConst(ConstType xmlConst) {
        throw new UnsupportedOperationException("Do not use adapter directly.");
    }

    @Override
    public Loop processForLoop(ForType f) {
        throw new UnsupportedOperationException("Do not use adapter directly.");
    }

    @Override
    public ProgramSegment processNested(Nested nested) throws TGException {
        throw new UnsupportedOperationException("Do not use adapter directly.");
    }

    @Override
    public ProgramSequence processSequence(Sequence sequence) throws TGException {
        throw new UnsupportedOperationException("Do not use adapter directly.");
    }

    @Override
    public Loop processSimpleLoop(SimpleLoop xmlLoop) {
        throw new UnsupportedOperationException("Do not use adapter directly.");
    }

    @Override
    public Factor processXmlFactor(FactorType xmlFactor) {
        throw new UnsupportedOperationException("Do not use adapter directly.");
    }

    @Override
    public Variable processXmlIndexed(FactorType.Var.Indexed xmlIndexed) {
        throw new UnsupportedOperationException("Do not use adapter directly.");
    }

    @Override
    public SimpleExpression processXmlSimpleExpr(SimpleExprType xmlSimpleExpr) {
        throw new UnsupportedOperationException("Do not use adapter directly.");
    }

    @Override
    public Expression processXmlSubExpr(FactorType.SubExpr xmlSubExpr) {
        throw new UnsupportedOperationException("Do not use adapter directly.");
    }

    @Override
    public Term processXmlTerm(TermType xmlTerm) {
        throw new UnsupportedOperationException("Do not use adapter directly.");
    }

    @Override
    public Variable processXmlVar(FactorType.Var xmlVar) {
        throw new UnsupportedOperationException("Do not use adapter directly.");
    }

    @Override
    public void startProcessing() throws TGException {
    }

    @Override
    public SignedFactor processXmlSignedFactor(SignedFactorType xmlSignedFactor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoopType processLoopType(LoopType loopType) throws TGException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
