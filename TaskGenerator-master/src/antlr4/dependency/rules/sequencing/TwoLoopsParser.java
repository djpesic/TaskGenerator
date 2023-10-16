// Generated from TwoLoops.g4 by ANTLR 4.4

package dependency.rules.sequencing;
import java.util.*;
import complexity.*;
import org.javatuples.Pair;
import org.apache.commons.lang3.math.NumberUtils;
import unification.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TwoLoopsParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, EXPR=2, IF=3, THEN=4, ELSE=5, LPAREN=6, RPAREN=7, COMMA=8, WHILE=9, 
		IDENT=10;
	public static final String[] tokenNames = {
		"<INVALID>", "WS", "'expr'", "'if'", "'then'", "'else'", "'('", "')'", 
		"','", "'while'", "IDENT"
	};
	public static final int
		RULE_formula = 0, RULE_segmentSequence1 = 1, RULE_segmentSequence = 2, 
		RULE_segment = 3, RULE_expression = 4, RULE_leftSide = 5, RULE_rightSide = 6, 
		RULE_logicalFunction = 7, RULE_argList = 8, RULE_arg = 9, RULE_whileSegment = 10, 
		RULE_whileSegment1 = 11, RULE_exprSequence = 12, RULE_ifSegment = 13, 
		RULE_leftVar = 14, RULE_rightVar = 15, RULE_variable = 16;
	public static final String[] ruleNames = {
		"formula", "segmentSequence1", "segmentSequence", "segment", "expression", 
		"leftSide", "rightSide", "logicalFunction", "argList", "arg", "whileSegment", 
		"whileSegment1", "exprSequence", "ifSegment", "leftVar", "rightVar", "variable"
	};

	@Override
	public String getGrammarFileName() { return "TwoLoops.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	 
	    public List<Pair<String,String>> parseSolution(List<String> solution1){
	        //solution in form {formalVal}=actualVal
	        List<Pair<String,String>> solution = new ArrayList<>();
	        for(int i = 0;i<solution1.size();i++){
	            String s = solution1.get(i);
	            String formal = s.substring(s.indexOf("{")+1, s.lastIndexOf("}"));
	            String actual = s.substring(s.indexOf("=")+1, s.lastIndexOf(";"));
	            if(actual.equals(""))actual = formal;
	            Pair<String,String> sol = new Pair<>(formal, actual);
	            solution.add(sol);
	        }
	        return solution;

	    }

	    private List<Pair<String,String>> solution = new ArrayList<>(); 
	    public Complexity compl1;
	    public Complexity compl2;   

	public TwoLoopsParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FormulaContext extends ParserRuleContext {
		public Complexity resultComplexity;
		public Set<String> refVars;
		public SegmentSequence1Context s;
		public SegmentSequence1Context segmentSequence1() {
			return getRuleContext(SegmentSequence1Context.class,0);
		}
		public FormulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formula; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).enterFormula(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).exitFormula(this);
		}
	}

	public final FormulaContext formula() throws RecognitionException {
		FormulaContext _localctx = new FormulaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_formula);
		try {
			enterOuterAlt(_localctx, 1);
			{

			        //System.out.println("Starting twoLoops rule");
			    
			setState(35); ((FormulaContext)_localctx).s = segmentSequence1();

			        if(((FormulaContext)_localctx).s.valid == true){
			            ((FormulaContext)_localctx).resultComplexity =  ComplexityOperations.sequence(compl1, compl2);
			        }
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SegmentSequence1Context extends ParserRuleContext {
		public boolean valid;
		public Pair<String,String> exprC;
		public Pair<String,String> exprD;
		public int numAInit;
		public int numBInit;
		public int numCInit;
		public int numDInit;
		public Pair<String,String> exprAInit;
		public Pair<String,String> exprBInit;
		public Pair<String,String> exprCInit;
		public Pair<String,String> exprDInit;
		public ExprSequenceContext e1;
		public WhileSegment1Context s1;
		public ExprSequenceContext e2;
		public WhileSegmentContext s2;
		public WhileSegment1Context whileSegment1() {
			return getRuleContext(WhileSegment1Context.class,0);
		}
		public ExprSequenceContext exprSequence(int i) {
			return getRuleContext(ExprSequenceContext.class,i);
		}
		public List<ExprSequenceContext> exprSequence() {
			return getRuleContexts(ExprSequenceContext.class);
		}
		public WhileSegmentContext whileSegment() {
			return getRuleContext(WhileSegmentContext.class,0);
		}
		public SegmentSequence1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_segmentSequence1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).enterSegmentSequence1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).exitSegmentSequence1(this);
		}
	}

	public final SegmentSequence1Context segmentSequence1() throws RecognitionException {
		SegmentSequence1Context _localctx = new SegmentSequence1Context(_ctx, getState());
		enterRule(_localctx, 2, RULE_segmentSequence1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((SegmentSequence1Context)_localctx).valid =  true;
			setState(40);
			_la = _input.LA(1);
			if (_la==EXPR) {
				{
				setState(39); ((SegmentSequence1Context)_localctx).e1 = exprSequence();
				}
			}

			setState(42); ((SegmentSequence1Context)_localctx).s1 = whileSegment1();
			setState(44);
			_la = _input.LA(1);
			if (_la==EXPR) {
				{
				setState(43); ((SegmentSequence1Context)_localctx).e2 = exprSequence();
				}
			}

			setState(46); ((SegmentSequence1Context)_localctx).s2 = whileSegment();

			        try{
			            if(((SegmentSequence1Context)_localctx).s1.a.equals(((SegmentSequence1Context)_localctx).s2.c) || ((SegmentSequence1Context)_localctx).s1.a.equals(((SegmentSequence1Context)_localctx).s2.d) || ((SegmentSequence1Context)_localctx).s1.b.equals(((SegmentSequence1Context)_localctx).s2.c) || ((SegmentSequence1Context)_localctx).s1.b.equals(((SegmentSequence1Context)_localctx).s2.d)){
			                //a!=b, a!=c, b!=c, b!=d
			                ((SegmentSequence1Context)_localctx).valid =  false;
			                //System.out.println("valid5 = false");
			            }else{
			                //first loop must not change c and d
			                for(Pair<String,String> e : ((SegmentSequence1Context)_localctx).s1.exprs){
			                    if(e.getValue0().equals(((SegmentSequence1Context)_localctx).s2.c)){
			                        ((SegmentSequence1Context)_localctx).exprC =  e;
			                        ((SegmentSequence1Context)_localctx).valid =  false;
			                        // System.out.println("valid1 = false");
			                    }
			                    if(e.getValue0().equals(((SegmentSequence1Context)_localctx).s2.d)){
			                        ((SegmentSequence1Context)_localctx).valid =  false;
			                       // System.out.println("valid2 = false");
			                        ((SegmentSequence1Context)_localctx).exprD =  e;
			                    }
			                }
			                if(!_localctx.valid){
			                    if(_localctx.exprC!=null && _localctx.exprD!=null){
			                        // in the first loop: c=const1, d=const2
			                        try{
			                            Double.parseDouble(_localctx.exprC.getValue1());
			                            Double.parseDouble(_localctx.exprD.getValue1());
			                            compl2 = Complexity.createConstant();
			                            ((SegmentSequence1Context)_localctx).valid =  true;
			                        }
			                        catch(Exception ex){
			                           // System.out.println("valid3 = false");
			                        }
			                    }
			                }
			                if(_localctx.valid){
			                    ((SegmentSequence1Context)_localctx).valid =  false;
			                    if((((SegmentSequence1Context)_localctx).e1!=null?_input.getText(((SegmentSequence1Context)_localctx).e1.start,((SegmentSequence1Context)_localctx).e1.stop):null)!=null){
			                        //a=1, b=N init expressions present
			                        for(Pair<String,String> e : ((SegmentSequence1Context)_localctx).e1.exprs){
			                            if(e.getValue0().equals("a")){
			                                _localctx.numAInit++;
			                                ((SegmentSequence1Context)_localctx).exprAInit = e;
			                            }else if(e.getValue0().equals("b")){
			                                _localctx.numBInit++;
			                                ((SegmentSequence1Context)_localctx).exprBInit =  e;
			                            }
			                        }
			                        if(_localctx.numAInit==1 && _localctx.numBInit==1){
			                            if(_localctx.exprAInit.getValue1().equals("0")){
			                                ((SegmentSequence1Context)_localctx).valid =  true;
			                            }
			                            if(_localctx.exprBInit.getValue1().toLowerCase().equals("n")){
			                                ((SegmentSequence1Context)_localctx).valid =  true;
			                            }
			                        }
			                    }
			                    if((((SegmentSequence1Context)_localctx).e2!=null?_input.getText(((SegmentSequence1Context)_localctx).e2.start,((SegmentSequence1Context)_localctx).e2.stop):null)!=null && _localctx.valid){
			                        //c=1, d=N init expressions present
			                        ((SegmentSequence1Context)_localctx).valid =  false;
			                        for(Pair<String,String> e : ((SegmentSequence1Context)_localctx).e2.exprs){
			                            if(e.getValue0().equals("c")){
			                                _localctx.numCInit++;
			                                ((SegmentSequence1Context)_localctx).exprCInit = e;
			                            }else if(e.getValue0().equals("d")){
			                                _localctx.numDInit++;
			                                ((SegmentSequence1Context)_localctx).exprDInit =  e;
			                            }
			                        }
			                        if(_localctx.numCInit==1 && _localctx.numDInit==1){
			                            if(_localctx.exprCInit.getValue1().equals("0")){
			                                ((SegmentSequence1Context)_localctx).valid =  true;
			                            }
			                            if(_localctx.exprDInit.getValue1().toLowerCase().equals("n")){
			                                ((SegmentSequence1Context)_localctx).valid =  true;
			                            }
			                        }
			                    }
			                    // if(!_localctx.valid)System.out.println("valid6 false");
			                }
			            }
			        }catch(Exception ex){
			            ((SegmentSequence1Context)_localctx).valid =  false;
			           // System.out.println("valid7 false");
			        }
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SegmentSequenceContext extends ParserRuleContext {
		public SegmentContext s1;
		public SegmentContext s2;
		public SegmentContext segment(int i) {
			return getRuleContext(SegmentContext.class,i);
		}
		public List<SegmentContext> segment() {
			return getRuleContexts(SegmentContext.class);
		}
		public SegmentSequenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_segmentSequence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).enterSegmentSequence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).exitSegmentSequence(this);
		}
	}

	public final SegmentSequenceContext segmentSequence() throws RecognitionException {
		SegmentSequenceContext _localctx = new SegmentSequenceContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_segmentSequence);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49); ((SegmentSequenceContext)_localctx).s1 = segment();
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXPR) | (1L << IF) | (1L << WHILE))) != 0)) {
				{
				{
				setState(50); ((SegmentSequenceContext)_localctx).s2 = segment();
				}
				}
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SegmentContext extends ParserRuleContext {
		public ExpressionContext e;
		public IfSegmentContext ifSeg;
		public IfSegmentContext ifSegment() {
			return getRuleContext(IfSegmentContext.class,0);
		}
		public WhileSegmentContext whileSegment() {
			return getRuleContext(WhileSegmentContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SegmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_segment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).enterSegment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).exitSegment(this);
		}
	}

	public final SegmentContext segment() throws RecognitionException {
		SegmentContext _localctx = new SegmentContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_segment);
		try {
			setState(59);
			switch (_input.LA(1)) {
			case WHILE:
				enterOuterAlt(_localctx, 1);
				{
				setState(56); whileSegment();
				}
				break;
			case EXPR:
				enterOuterAlt(_localctx, 2);
				{
				setState(57); ((SegmentContext)_localctx).e = expression();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 3);
				{
				setState(58); ((SegmentContext)_localctx).ifSeg = ifSegment();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public String ls;
		public String rs;
		public LeftSideContext ls1;
		public RightSideContext rs1;
		public TerminalNode LPAREN() { return getToken(TwoLoopsParser.LPAREN, 0); }
		public TerminalNode COMMA() { return getToken(TwoLoopsParser.COMMA, 0); }
		public RightSideContext rightSide() {
			return getRuleContext(RightSideContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(TwoLoopsParser.RPAREN, 0); }
		public TerminalNode EXPR() { return getToken(TwoLoopsParser.EXPR, 0); }
		public LeftSideContext leftSide() {
			return getRuleContext(LeftSideContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61); match(EXPR);
			setState(62); match(LPAREN);
			setState(63); ((ExpressionContext)_localctx).ls1 = leftSide();
			((ExpressionContext)_localctx).ls =  (((ExpressionContext)_localctx).ls1!=null?_input.getText(((ExpressionContext)_localctx).ls1.start,((ExpressionContext)_localctx).ls1.stop):null);
			setState(65); match(COMMA);
			setState(66); ((ExpressionContext)_localctx).rs1 = rightSide();

			        ((ExpressionContext)_localctx).rs =  (((ExpressionContext)_localctx).rs1!=null?_input.getText(((ExpressionContext)_localctx).rs1.start,((ExpressionContext)_localctx).rs1.stop):null);
			    
			setState(68); match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LeftSideContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public LeftSideContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leftSide; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).enterLeftSide(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).exitLeftSide(this);
		}
	}

	public final LeftSideContext leftSide() throws RecognitionException {
		LeftSideContext _localctx = new LeftSideContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_leftSide);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70); variable();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RightSideContext extends ParserRuleContext {
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(TwoLoopsParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(TwoLoopsParser.RPAREN, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public RightSideContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rightSide; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).enterRightSide(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).exitRightSide(this);
		}
	}

	public final RightSideContext rightSide() throws RecognitionException {
		RightSideContext _localctx = new RightSideContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_rightSide);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72); variable();
			setState(77);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(73); match(LPAREN);
				setState(74); argList();
				setState(75); match(RPAREN);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LogicalFunctionContext extends ParserRuleContext {
		public VariableContext v;
		public ArgListContext al;
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(TwoLoopsParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(TwoLoopsParser.RPAREN, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public LogicalFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalFunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).enterLogicalFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).exitLogicalFunction(this);
		}
	}

	public final LogicalFunctionContext logicalFunction() throws RecognitionException {
		LogicalFunctionContext _localctx = new LogicalFunctionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_logicalFunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79); ((LogicalFunctionContext)_localctx).v = variable();
			setState(84);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(80); match(LPAREN);
				setState(81); ((LogicalFunctionContext)_localctx).al = argList();
				setState(82); match(RPAREN);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgListContext extends ParserRuleContext {
		public ArgContext a;
		public ArgContext a1;
		public ArgContext arg(int i) {
			return getRuleContext(ArgContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(TwoLoopsParser.COMMA); }
		public List<ArgContext> arg() {
			return getRuleContexts(ArgContext.class);
		}
		public TerminalNode COMMA(int i) {
			return getToken(TwoLoopsParser.COMMA, i);
		}
		public ArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).enterArgList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).exitArgList(this);
		}
	}

	public final ArgListContext argList() throws RecognitionException {
		ArgListContext _localctx = new ArgListContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_argList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86); ((ArgListContext)_localctx).a = arg();
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(87); match(COMMA);
				setState(88); ((ArgListContext)_localctx).a1 = arg();
				}
				}
				setState(93);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgContext extends ParserRuleContext {
		public RightSideContext rs;
		public RightSideContext rightSide() {
			return getRuleContext(RightSideContext.class,0);
		}
		public ArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).enterArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).exitArg(this);
		}
	}

	public final ArgContext arg() throws RecognitionException {
		ArgContext _localctx = new ArgContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_arg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94); ((ArgContext)_localctx).rs = rightSide();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhileSegmentContext extends ParserRuleContext {
		public String c;
		public String d;
		public LeftVarContext e1;
		public RightVarContext e2;
		public SegmentSequenceContext s;
		public LeftVarContext leftVar() {
			return getRuleContext(LeftVarContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(TwoLoopsParser.LPAREN, 0); }
		public RightVarContext rightVar() {
			return getRuleContext(RightVarContext.class,0);
		}
		public SegmentSequenceContext segmentSequence() {
			return getRuleContext(SegmentSequenceContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(TwoLoopsParser.COMMA); }
		public TerminalNode RPAREN() { return getToken(TwoLoopsParser.RPAREN, 0); }
		public TerminalNode WHILE() { return getToken(TwoLoopsParser.WHILE, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(TwoLoopsParser.COMMA, i);
		}
		public WhileSegmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileSegment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).enterWhileSegment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).exitWhileSegment(this);
		}
	}

	public final WhileSegmentContext whileSegment() throws RecognitionException {
		WhileSegmentContext _localctx = new WhileSegmentContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_whileSegment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96); match(WHILE);
			setState(97); match(LPAREN);
			setState(98); ((WhileSegmentContext)_localctx).e1 = leftVar();
			setState(99); match(COMMA);
			setState(100); ((WhileSegmentContext)_localctx).e2 = rightVar();
			setState(103);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(101); match(COMMA);
				setState(102); ((WhileSegmentContext)_localctx).s = segmentSequence();
				}
			}

			setState(105); match(RPAREN);
			 ((WhileSegmentContext)_localctx).c =  (((WhileSegmentContext)_localctx).e1!=null?_input.getText(((WhileSegmentContext)_localctx).e1.start,((WhileSegmentContext)_localctx).e1.stop):null); ((WhileSegmentContext)_localctx).d =  (((WhileSegmentContext)_localctx).e2!=null?_input.getText(((WhileSegmentContext)_localctx).e2.start,((WhileSegmentContext)_localctx).e2.stop):null); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhileSegment1Context extends ParserRuleContext {
		public String a;
		public String b;
		public List<Pair<String,String>> exprs;
		public LeftVarContext e1;
		public RightVarContext e2;
		public ExprSequenceContext s;
		public LeftVarContext leftVar() {
			return getRuleContext(LeftVarContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(TwoLoopsParser.LPAREN, 0); }
		public RightVarContext rightVar() {
			return getRuleContext(RightVarContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(TwoLoopsParser.COMMA); }
		public TerminalNode RPAREN() { return getToken(TwoLoopsParser.RPAREN, 0); }
		public ExprSequenceContext exprSequence() {
			return getRuleContext(ExprSequenceContext.class,0);
		}
		public TerminalNode WHILE() { return getToken(TwoLoopsParser.WHILE, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(TwoLoopsParser.COMMA, i);
		}
		public WhileSegment1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileSegment1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).enterWhileSegment1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).exitWhileSegment1(this);
		}
	}

	public final WhileSegment1Context whileSegment1() throws RecognitionException {
		WhileSegment1Context _localctx = new WhileSegment1Context(_ctx, getState());
		enterRule(_localctx, 22, RULE_whileSegment1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108); match(WHILE);
			setState(109); match(LPAREN);
			setState(110); ((WhileSegment1Context)_localctx).e1 = leftVar();
			setState(111); match(COMMA);
			setState(112); ((WhileSegment1Context)_localctx).e2 = rightVar();
			setState(113); match(COMMA);
			setState(114); ((WhileSegment1Context)_localctx).s = exprSequence();
			setState(115); match(RPAREN);
			 
			        ((WhileSegment1Context)_localctx).exprs =  ((WhileSegment1Context)_localctx).s.exprs;
			        ((WhileSegment1Context)_localctx).a =  (((WhileSegment1Context)_localctx).e1!=null?_input.getText(((WhileSegment1Context)_localctx).e1.start,((WhileSegment1Context)_localctx).e1.stop):null);
			        ((WhileSegment1Context)_localctx).b =  (((WhileSegment1Context)_localctx).e2!=null?_input.getText(((WhileSegment1Context)_localctx).e2.start,((WhileSegment1Context)_localctx).e2.stop):null);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprSequenceContext extends ParserRuleContext {
		public List<Pair<String,String>> exprs;
		public Pair<String,String> p;
		public ExpressionContext e1;
		public ExpressionContext e2;
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExprSequenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprSequence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).enterExprSequence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).exitExprSequence(this);
		}
	}

	public final ExprSequenceContext exprSequence() throws RecognitionException {
		ExprSequenceContext _localctx = new ExprSequenceContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_exprSequence);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118); ((ExprSequenceContext)_localctx).e1 = expression();

			        ((ExprSequenceContext)_localctx).exprs =  new ArrayList<>();
			        Pair<String,String> p = new Pair(((ExprSequenceContext)_localctx).e1.ls,((ExprSequenceContext)_localctx).e1.rs);
			        if(((ExprSequenceContext)_localctx).e1.ls != null && ((ExprSequenceContext)_localctx).e1.rs !=null)
			            _localctx.exprs.add(p);
			    
			setState(125);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EXPR) {
				{
				{
				setState(120); ((ExprSequenceContext)_localctx).e2 = expression();

				        p = new Pair(((ExprSequenceContext)_localctx).e2.ls,((ExprSequenceContext)_localctx).e2.rs);
				        if(((ExprSequenceContext)_localctx).e1.ls != null && ((ExprSequenceContext)_localctx).e1.rs !=null)
				            _localctx.exprs.add(p);
				    
				}
				}
				setState(127);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfSegmentContext extends ParserRuleContext {
		public LogicalFunctionContext cond;
		public SegmentSequenceContext seq;
		public SegmentSequenceContext seq1;
		public TerminalNode ELSE() { return getToken(TwoLoopsParser.ELSE, 0); }
		public TerminalNode IF() { return getToken(TwoLoopsParser.IF, 0); }
		public TerminalNode RPAREN(int i) {
			return getToken(TwoLoopsParser.RPAREN, i);
		}
		public List<TerminalNode> LPAREN() { return getTokens(TwoLoopsParser.LPAREN); }
		public List<SegmentSequenceContext> segmentSequence() {
			return getRuleContexts(SegmentSequenceContext.class);
		}
		public List<TerminalNode> COMMA() { return getTokens(TwoLoopsParser.COMMA); }
		public TerminalNode THEN() { return getToken(TwoLoopsParser.THEN, 0); }
		public SegmentSequenceContext segmentSequence(int i) {
			return getRuleContext(SegmentSequenceContext.class,i);
		}
		public List<TerminalNode> RPAREN() { return getTokens(TwoLoopsParser.RPAREN); }
		public LogicalFunctionContext logicalFunction() {
			return getRuleContext(LogicalFunctionContext.class,0);
		}
		public TerminalNode LPAREN(int i) {
			return getToken(TwoLoopsParser.LPAREN, i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(TwoLoopsParser.COMMA, i);
		}
		public IfSegmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifSegment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).enterIfSegment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).exitIfSegment(this);
		}
	}

	public final IfSegmentContext ifSegment() throws RecognitionException {
		IfSegmentContext _localctx = new IfSegmentContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_ifSegment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128); match(IF);
			setState(129); match(LPAREN);
			setState(130); ((IfSegmentContext)_localctx).cond = logicalFunction();
			setState(131); match(COMMA);
			setState(132); match(THEN);
			setState(133); match(LPAREN);
			setState(135);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXPR) | (1L << IF) | (1L << WHILE))) != 0)) {
				{
				setState(134); ((IfSegmentContext)_localctx).seq = segmentSequence();
				}
			}

			setState(137); match(RPAREN);
			setState(144);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(138); match(COMMA);
				setState(139); match(ELSE);
				setState(140); match(LPAREN);
				setState(141); ((IfSegmentContext)_localctx).seq1 = segmentSequence();
				setState(142); match(RPAREN);
				}
			}

			setState(146); match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LeftVarContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public LeftVarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leftVar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).enterLeftVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).exitLeftVar(this);
		}
	}

	public final LeftVarContext leftVar() throws RecognitionException {
		LeftVarContext _localctx = new LeftVarContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_leftVar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148); variable();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RightVarContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public RightVarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rightVar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).enterRightVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).exitRightVar(this);
		}
	}

	public final RightVarContext rightVar() throws RecognitionException {
		RightVarContext _localctx = new RightVarContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_rightVar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150); variable();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public String var;
		public Token i;
		public TerminalNode IDENT() { return getToken(TwoLoopsParser.IDENT, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TwoLoopsListener ) ((TwoLoopsListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152); ((VariableContext)_localctx).i = match(IDENT);

			        ((VariableContext)_localctx).var =  (((VariableContext)_localctx).i!=null?((VariableContext)_localctx).i.getText():null);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\f\u009e\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\2\3\3\3\3\5\3+\n\3\3\3\3\3\5\3/\n\3\3\3\3\3\3\3\3\4\3\4"+
		"\7\4\66\n\4\f\4\16\49\13\4\3\5\3\5\3\5\5\5>\n\5\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\5\bP\n\b\3\t\3\t\3\t\3\t\3"+
		"\t\5\tW\n\t\3\n\3\n\3\n\7\n\\\n\n\f\n\16\n_\13\n\3\13\3\13\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\5\fj\n\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\16\3\16\3\16\3\16\3\16\7\16~\n\16\f\16\16\16\u0081\13\16\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u008a\n\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\5\17\u0093\n\17\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22"+
		"\3\22\3\22\2\2\23\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"\2\2\u0098"+
		"\2$\3\2\2\2\4(\3\2\2\2\6\63\3\2\2\2\b=\3\2\2\2\n?\3\2\2\2\fH\3\2\2\2\16"+
		"J\3\2\2\2\20Q\3\2\2\2\22X\3\2\2\2\24`\3\2\2\2\26b\3\2\2\2\30n\3\2\2\2"+
		"\32x\3\2\2\2\34\u0082\3\2\2\2\36\u0096\3\2\2\2 \u0098\3\2\2\2\"\u009a"+
		"\3\2\2\2$%\b\2\1\2%&\5\4\3\2&\'\b\2\1\2\'\3\3\2\2\2(*\b\3\1\2)+\5\32\16"+
		"\2*)\3\2\2\2*+\3\2\2\2+,\3\2\2\2,.\5\30\r\2-/\5\32\16\2.-\3\2\2\2./\3"+
		"\2\2\2/\60\3\2\2\2\60\61\5\26\f\2\61\62\b\3\1\2\62\5\3\2\2\2\63\67\5\b"+
		"\5\2\64\66\5\b\5\2\65\64\3\2\2\2\669\3\2\2\2\67\65\3\2\2\2\678\3\2\2\2"+
		"8\7\3\2\2\29\67\3\2\2\2:>\5\26\f\2;>\5\n\6\2<>\5\34\17\2=:\3\2\2\2=;\3"+
		"\2\2\2=<\3\2\2\2>\t\3\2\2\2?@\7\4\2\2@A\7\b\2\2AB\5\f\7\2BC\b\6\1\2CD"+
		"\7\n\2\2DE\5\16\b\2EF\b\6\1\2FG\7\t\2\2G\13\3\2\2\2HI\5\"\22\2I\r\3\2"+
		"\2\2JO\5\"\22\2KL\7\b\2\2LM\5\22\n\2MN\7\t\2\2NP\3\2\2\2OK\3\2\2\2OP\3"+
		"\2\2\2P\17\3\2\2\2QV\5\"\22\2RS\7\b\2\2ST\5\22\n\2TU\7\t\2\2UW\3\2\2\2"+
		"VR\3\2\2\2VW\3\2\2\2W\21\3\2\2\2X]\5\24\13\2YZ\7\n\2\2Z\\\5\24\13\2[Y"+
		"\3\2\2\2\\_\3\2\2\2][\3\2\2\2]^\3\2\2\2^\23\3\2\2\2_]\3\2\2\2`a\5\16\b"+
		"\2a\25\3\2\2\2bc\7\13\2\2cd\7\b\2\2de\5\36\20\2ef\7\n\2\2fi\5 \21\2gh"+
		"\7\n\2\2hj\5\6\4\2ig\3\2\2\2ij\3\2\2\2jk\3\2\2\2kl\7\t\2\2lm\b\f\1\2m"+
		"\27\3\2\2\2no\7\13\2\2op\7\b\2\2pq\5\36\20\2qr\7\n\2\2rs\5 \21\2st\7\n"+
		"\2\2tu\5\32\16\2uv\7\t\2\2vw\b\r\1\2w\31\3\2\2\2xy\5\n\6\2y\177\b\16\1"+
		"\2z{\5\n\6\2{|\b\16\1\2|~\3\2\2\2}z\3\2\2\2~\u0081\3\2\2\2\177}\3\2\2"+
		"\2\177\u0080\3\2\2\2\u0080\33\3\2\2\2\u0081\177\3\2\2\2\u0082\u0083\7"+
		"\5\2\2\u0083\u0084\7\b\2\2\u0084\u0085\5\20\t\2\u0085\u0086\7\n\2\2\u0086"+
		"\u0087\7\6\2\2\u0087\u0089\7\b\2\2\u0088\u008a\5\6\4\2\u0089\u0088\3\2"+
		"\2\2\u0089\u008a\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u0092\7\t\2\2\u008c"+
		"\u008d\7\n\2\2\u008d\u008e\7\7\2\2\u008e\u008f\7\b\2\2\u008f\u0090\5\6"+
		"\4\2\u0090\u0091\7\t\2\2\u0091\u0093\3\2\2\2\u0092\u008c\3\2\2\2\u0092"+
		"\u0093\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0095\7\t\2\2\u0095\35\3\2\2"+
		"\2\u0096\u0097\5\"\22\2\u0097\37\3\2\2\2\u0098\u0099\5\"\22\2\u0099!\3"+
		"\2\2\2\u009a\u009b\7\f\2\2\u009b\u009c\b\22\1\2\u009c#\3\2\2\2\r*.\67"+
		"=OV]i\177\u0089\u0092";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}