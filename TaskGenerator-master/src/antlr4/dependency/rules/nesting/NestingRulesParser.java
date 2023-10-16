// Generated from NestingRules.g4 by ANTLR 4.4

package dependency.rules.nesting;
import java.util.*;
import complexity.*;
import org.javatuples.Pair;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class NestingRulesParser extends Parser {
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
		RULE_formula = 0, RULE_segmentSequence = 1, RULE_segmentSequence1 = 2, 
		RULE_segment = 3, RULE_segment1 = 4, RULE_expression = 5, RULE_leftSide = 6, 
		RULE_rightSide = 7, RULE_argList = 8, RULE_arg = 9, RULE_exprSequence = 10, 
		RULE_logicalFunction = 11, RULE_whileSegment = 12, RULE_whileSegment1 = 13, 
		RULE_ifSegment = 14, RULE_leftVar = 15, RULE_rightVar = 16, RULE_variable = 17;
	public static final String[] ruleNames = {
		"formula", "segmentSequence", "segmentSequence1", "segment", "segment1", 
		"expression", "leftSide", "rightSide", "argList", "arg", "exprSequence", 
		"logicalFunction", "whileSegment", "whileSegment1", "ifSegment", "leftVar", 
		"rightVar", "variable"
	};

	@Override
	public String getGrammarFileName() { return "NestingRules.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    public Complexity compl1;
	    public Complexity compl2;

	public NestingRulesParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FormulaContext extends ParserRuleContext {
		public Complexity resultComplexity;
		public SegmentSequenceContext s;
		public SegmentSequenceContext segmentSequence() {
			return getRuleContext(SegmentSequenceContext.class,0);
		}
		public FormulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formula; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).enterFormula(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).exitFormula(this);
		}
	}

	public final FormulaContext formula() throws RecognitionException {
		FormulaContext _localctx = new FormulaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_formula);
		try {
			enterOuterAlt(_localctx, 1);
			{

			       // System.out.println("Starting NestingRules rule");
			    
			setState(37); ((FormulaContext)_localctx).s = segmentSequence();

			        if(((FormulaContext)_localctx).s.valid == true){
			            ((FormulaContext)_localctx).resultComplexity =  ComplexityOperations.multiply(compl1, compl2);
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
		public boolean valid;
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
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).enterSegmentSequence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).exitSegmentSequence(this);
		}
	}

	public final SegmentSequenceContext segmentSequence() throws RecognitionException {
		SegmentSequenceContext _localctx = new SegmentSequenceContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_segmentSequence);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((SegmentSequenceContext)_localctx).valid =  true;
			setState(41); ((SegmentSequenceContext)_localctx).s1 = segment();
			 if(!(((SegmentSequenceContext)_localctx).s1.valid)) ((SegmentSequenceContext)_localctx).valid = false; 
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXPR) | (1L << IF) | (1L << WHILE))) != 0)) {
				{
				{
				setState(43); ((SegmentSequenceContext)_localctx).s2 = segment();
				 if(!(((SegmentSequenceContext)_localctx).s2.valid)) ((SegmentSequenceContext)_localctx).valid = false; 
				}
				}
				setState(50);
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

	public static class SegmentSequence1Context extends ParserRuleContext {
		public List<Pair<String,String>> exprs;
		public Segment1Context s1;
		public Segment1Context s2;
		public List<Segment1Context> segment1() {
			return getRuleContexts(Segment1Context.class);
		}
		public Segment1Context segment1(int i) {
			return getRuleContext(Segment1Context.class,i);
		}
		public SegmentSequence1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_segmentSequence1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).enterSegmentSequence1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).exitSegmentSequence1(this);
		}
	}

	public final SegmentSequence1Context segmentSequence1() throws RecognitionException {
		SegmentSequence1Context _localctx = new SegmentSequence1Context(_ctx, getState());
		enterRule(_localctx, 4, RULE_segmentSequence1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51); ((SegmentSequence1Context)_localctx).s1 = segment1();

			        ((SegmentSequence1Context)_localctx).exprs =  new ArrayList<>();
			        _localctx.exprs.addAll(((SegmentSequence1Context)_localctx).s1.exprs);
			    
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXPR) | (1L << IF) | (1L << WHILE))) != 0)) {
				{
				{
				setState(53); ((SegmentSequence1Context)_localctx).s2 = segment1();
				 _localctx.exprs.addAll(((SegmentSequence1Context)_localctx).s2.exprs);
				}
				}
				setState(60);
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
		public boolean valid;
		public WhileSegment1Context w;
		public ExpressionContext e;
		public IfSegmentContext ifSeg;
		public WhileSegment1Context whileSegment1() {
			return getRuleContext(WhileSegment1Context.class,0);
		}
		public IfSegmentContext ifSegment() {
			return getRuleContext(IfSegmentContext.class,0);
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
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).enterSegment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).exitSegment(this);
		}
	}

	public final SegmentContext segment() throws RecognitionException {
		SegmentContext _localctx = new SegmentContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_segment);
		try {
			setState(70);
			switch (_input.LA(1)) {
			case WHILE:
				enterOuterAlt(_localctx, 1);
				{
				setState(61); ((SegmentContext)_localctx).w = whileSegment1();
				 ((SegmentContext)_localctx).valid =  ((SegmentContext)_localctx).w.valid;
				}
				break;
			case EXPR:
				enterOuterAlt(_localctx, 2);
				{
				setState(64); ((SegmentContext)_localctx).e = expression();
				 ((SegmentContext)_localctx).valid =  true;
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 3);
				{
				setState(67); ((SegmentContext)_localctx).ifSeg = ifSegment();
				 ((SegmentContext)_localctx).valid =  true;
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

	public static class Segment1Context extends ParserRuleContext {
		public List<Pair<String,String>> exprs;
		public Pair<String,String> p;
		public WhileSegmentContext w;
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
		public Segment1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_segment1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).enterSegment1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).exitSegment1(this);
		}
	}

	public final Segment1Context segment1() throws RecognitionException {
		Segment1Context _localctx = new Segment1Context(_ctx, getState());
		enterRule(_localctx, 8, RULE_segment1);
		try {
			setState(82);
			switch (_input.LA(1)) {
			case WHILE:
				enterOuterAlt(_localctx, 1);
				{
				((Segment1Context)_localctx).exprs =  new ArrayList<>();
				setState(73); ((Segment1Context)_localctx).w = whileSegment();
				 ((Segment1Context)_localctx).exprs =  ((Segment1Context)_localctx).w.exprs;
				}
				break;
			case EXPR:
				enterOuterAlt(_localctx, 2);
				{
				setState(76); ((Segment1Context)_localctx).e = expression();

				        ((Segment1Context)_localctx).exprs =  new ArrayList<>();
				        Pair<String,String> p = new Pair(((Segment1Context)_localctx).e.ls,((Segment1Context)_localctx).e.rs);
				        _localctx.exprs.add(p);
				    
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 3);
				{
				setState(79); ((Segment1Context)_localctx).ifSeg = ifSegment();
				((Segment1Context)_localctx).exprs =  ((Segment1Context)_localctx).ifSeg.exprs;
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
		public TerminalNode LPAREN() { return getToken(NestingRulesParser.LPAREN, 0); }
		public TerminalNode COMMA() { return getToken(NestingRulesParser.COMMA, 0); }
		public RightSideContext rightSide() {
			return getRuleContext(RightSideContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(NestingRulesParser.RPAREN, 0); }
		public TerminalNode EXPR() { return getToken(NestingRulesParser.EXPR, 0); }
		public LeftSideContext leftSide() {
			return getRuleContext(LeftSideContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84); match(EXPR);
			setState(85); match(LPAREN);
			setState(86); ((ExpressionContext)_localctx).ls1 = leftSide();
			((ExpressionContext)_localctx).ls =  (((ExpressionContext)_localctx).ls1!=null?_input.getText(((ExpressionContext)_localctx).ls1.start,((ExpressionContext)_localctx).ls1.stop):null);
			setState(88); match(COMMA);
			setState(89); ((ExpressionContext)_localctx).rs1 = rightSide();

			        ((ExpressionContext)_localctx).rs =  (((ExpressionContext)_localctx).rs1!=null?_input.getText(((ExpressionContext)_localctx).rs1.start,((ExpressionContext)_localctx).rs1.stop):null);
			    
			setState(91); match(RPAREN);
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
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).enterLeftSide(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).exitLeftSide(this);
		}
	}

	public final LeftSideContext leftSide() throws RecognitionException {
		LeftSideContext _localctx = new LeftSideContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_leftSide);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93); variable();
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
		public TerminalNode LPAREN() { return getToken(NestingRulesParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(NestingRulesParser.RPAREN, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public RightSideContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rightSide; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).enterRightSide(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).exitRightSide(this);
		}
	}

	public final RightSideContext rightSide() throws RecognitionException {
		RightSideContext _localctx = new RightSideContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_rightSide);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95); variable();
			setState(100);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(96); match(LPAREN);
				setState(97); argList();
				setState(98); match(RPAREN);
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
		public List<TerminalNode> COMMA() { return getTokens(NestingRulesParser.COMMA); }
		public List<ArgContext> arg() {
			return getRuleContexts(ArgContext.class);
		}
		public TerminalNode COMMA(int i) {
			return getToken(NestingRulesParser.COMMA, i);
		}
		public ArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).enterArgList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).exitArgList(this);
		}
	}

	public final ArgListContext argList() throws RecognitionException {
		ArgListContext _localctx = new ArgListContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_argList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102); ((ArgListContext)_localctx).a = arg();
			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(103); match(COMMA);
				setState(104); ((ArgListContext)_localctx).a1 = arg();
				}
				}
				setState(109);
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
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).enterArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).exitArg(this);
		}
	}

	public final ArgContext arg() throws RecognitionException {
		ArgContext _localctx = new ArgContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_arg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110); ((ArgContext)_localctx).rs = rightSide();
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
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).enterExprSequence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).exitExprSequence(this);
		}
	}

	public final ExprSequenceContext exprSequence() throws RecognitionException {
		ExprSequenceContext _localctx = new ExprSequenceContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_exprSequence);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112); ((ExprSequenceContext)_localctx).e1 = expression();

			        ((ExprSequenceContext)_localctx).exprs =  new ArrayList<>();
			        Pair<String,String> p = new Pair(((ExprSequenceContext)_localctx).e1.ls,((ExprSequenceContext)_localctx).e1.rs);
			        if(((ExprSequenceContext)_localctx).e1.ls != null && ((ExprSequenceContext)_localctx).e1.rs !=null)
			            _localctx.exprs.add(p);
			    
			setState(119);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EXPR) {
				{
				{
				setState(114); ((ExprSequenceContext)_localctx).e2 = expression();

				        p = new Pair(((ExprSequenceContext)_localctx).e2.ls,((ExprSequenceContext)_localctx).e2.rs);
				        _localctx.exprs.add(p);
				    
				}
				}
				setState(121);
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

	public static class LogicalFunctionContext extends ParserRuleContext {
		public VariableContext v;
		public ArgListContext al;
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(NestingRulesParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(NestingRulesParser.RPAREN, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public LogicalFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalFunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).enterLogicalFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).exitLogicalFunction(this);
		}
	}

	public final LogicalFunctionContext logicalFunction() throws RecognitionException {
		LogicalFunctionContext _localctx = new LogicalFunctionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_logicalFunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122); ((LogicalFunctionContext)_localctx).v = variable();
			setState(127);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(123); match(LPAREN);
				setState(124); ((LogicalFunctionContext)_localctx).al = argList();
				setState(125); match(RPAREN);
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

	public static class WhileSegmentContext extends ParserRuleContext {
		public String c;
		public String d;
		public List<Pair<String,String>> exprs;
		public LeftVarContext c1;
		public RightVarContext d1;
		public SegmentSequence1Context s;
		public LeftVarContext leftVar() {
			return getRuleContext(LeftVarContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(NestingRulesParser.LPAREN, 0); }
		public RightVarContext rightVar() {
			return getRuleContext(RightVarContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(NestingRulesParser.COMMA); }
		public SegmentSequence1Context segmentSequence1() {
			return getRuleContext(SegmentSequence1Context.class,0);
		}
		public TerminalNode RPAREN() { return getToken(NestingRulesParser.RPAREN, 0); }
		public TerminalNode WHILE() { return getToken(NestingRulesParser.WHILE, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(NestingRulesParser.COMMA, i);
		}
		public WhileSegmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileSegment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).enterWhileSegment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).exitWhileSegment(this);
		}
	}

	public final WhileSegmentContext whileSegment() throws RecognitionException {
		WhileSegmentContext _localctx = new WhileSegmentContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_whileSegment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129); match(WHILE);
			setState(130); match(LPAREN);
			setState(131); ((WhileSegmentContext)_localctx).c1 = leftVar();
			setState(132); match(COMMA);
			setState(133); ((WhileSegmentContext)_localctx).d1 = rightVar();
			setState(136);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(134); match(COMMA);
				setState(135); ((WhileSegmentContext)_localctx).s = segmentSequence1();
				}
			}

			setState(138); match(RPAREN);

			        ((WhileSegmentContext)_localctx).c =  ((WhileSegmentContext)_localctx).c1.var;
			        ((WhileSegmentContext)_localctx).d =  ((WhileSegmentContext)_localctx).d1.var;
			        if((((WhileSegmentContext)_localctx).s!=null?_input.getText(((WhileSegmentContext)_localctx).s.start,((WhileSegmentContext)_localctx).s.stop):null)!=null)
			            ((WhileSegmentContext)_localctx).exprs =  ((WhileSegmentContext)_localctx).s.exprs;
			    
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
		public boolean valid;
		public List<Pair<String,String>> outerExprs;
		public List<Pair<String,String>> changeLowerExprs;
		public List<Pair<String,String>> changeUpperExprs;
		public LeftVarContext a;
		public RightVarContext b;
		public ExprSequenceContext exprs1;
		public WhileSegmentContext inner;
		public ExprSequenceContext exprs2;
		public LeftVarContext leftVar() {
			return getRuleContext(LeftVarContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(NestingRulesParser.LPAREN, 0); }
		public RightVarContext rightVar() {
			return getRuleContext(RightVarContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(NestingRulesParser.COMMA); }
		public ExprSequenceContext exprSequence(int i) {
			return getRuleContext(ExprSequenceContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(NestingRulesParser.RPAREN, 0); }
		public List<ExprSequenceContext> exprSequence() {
			return getRuleContexts(ExprSequenceContext.class);
		}
		public TerminalNode WHILE() { return getToken(NestingRulesParser.WHILE, 0); }
		public WhileSegmentContext whileSegment() {
			return getRuleContext(WhileSegmentContext.class,0);
		}
		public TerminalNode COMMA(int i) {
			return getToken(NestingRulesParser.COMMA, i);
		}
		public WhileSegment1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileSegment1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).enterWhileSegment1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).exitWhileSegment1(this);
		}
	}

	public final WhileSegment1Context whileSegment1() throws RecognitionException {
		WhileSegment1Context _localctx = new WhileSegment1Context(_ctx, getState());
		enterRule(_localctx, 26, RULE_whileSegment1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((WhileSegment1Context)_localctx).valid =  true;
			setState(142); match(WHILE);
			setState(143); match(LPAREN);
			setState(144); ((WhileSegment1Context)_localctx).a = leftVar();
			setState(145); match(COMMA);
			setState(146); ((WhileSegment1Context)_localctx).b = rightVar();
			setState(147); match(COMMA);
			setState(149);
			_la = _input.LA(1);
			if (_la==EXPR) {
				{
				setState(148); ((WhileSegment1Context)_localctx).exprs1 = exprSequence();
				}
			}

			setState(151); ((WhileSegment1Context)_localctx).inner = whileSegment();
			setState(153);
			_la = _input.LA(1);
			if (_la==EXPR) {
				{
				setState(152); ((WhileSegment1Context)_localctx).exprs2 = exprSequence();
				}
			}

			setState(155); match(RPAREN);

			        if(((WhileSegment1Context)_localctx).a.var.equals(((WhileSegment1Context)_localctx).inner.d) || ((WhileSegment1Context)_localctx).a.var.equals(((WhileSegment1Context)_localctx).inner.c)){
			            ((WhileSegment1Context)_localctx).valid =  false;
			            //System.out.println("valid1 false");
			        }else if((((WhileSegment1Context)_localctx).exprs1!=null?_input.getText(((WhileSegment1Context)_localctx).exprs1.start,((WhileSegment1Context)_localctx).exprs1.stop):null)==null && (((WhileSegment1Context)_localctx).exprs2!=null?_input.getText(((WhileSegment1Context)_localctx).exprs2.start,((WhileSegment1Context)_localctx).exprs2.stop):null)==null){
			            ((WhileSegment1Context)_localctx).valid =  false;
			           // System.out.println("valid2 false");
			        }else{
			            ((WhileSegment1Context)_localctx).outerExprs =  new ArrayList<>();

			            if((((WhileSegment1Context)_localctx).exprs1!=null?_input.getText(((WhileSegment1Context)_localctx).exprs1.start,((WhileSegment1Context)_localctx).exprs1.stop):null)!=null && ((WhileSegment1Context)_localctx).exprs1.exprs != null)
			                _localctx.outerExprs.addAll(((WhileSegment1Context)_localctx).exprs1.exprs);

			            if((((WhileSegment1Context)_localctx).exprs2!=null?_input.getText(((WhileSegment1Context)_localctx).exprs2.start,((WhileSegment1Context)_localctx).exprs2.stop):null)!=null && ((WhileSegment1Context)_localctx).exprs1.exprs != null)
			                _localctx.outerExprs.addAll(((WhileSegment1Context)_localctx).exprs2.exprs);
			            // outer loop bounds not changed by inner loop
			            if(((WhileSegment1Context)_localctx).inner.exprs!=null){
			                for(Pair<String,String> e : ((WhileSegment1Context)_localctx).inner.exprs){
			                    if(e.getValue0().equals(((WhileSegment1Context)_localctx).a.var) || e.getValue0().equals(((WhileSegment1Context)_localctx).b.var)){
			                        ((WhileSegment1Context)_localctx).valid =  false;
			                      //  System.out.println("valid4 false");
			                    }
			                }
			            }
			            
			            List<Pair<String,String>> changeLowerExprs = new ArrayList<>();
			            List<Pair<String,String>> changeUpperExprs = new ArrayList<>();
			            if(_localctx.valid){
			                // inner loop bounds not changed by outer loop
			                for(Pair<String,String> e : _localctx.outerExprs){
			                    if(e.getValue0().equals(((WhileSegment1Context)_localctx).inner.c)){
			                        ((WhileSegment1Context)_localctx).valid =  false;
			                      //  System.out.println("valid3 false");
			                        changeLowerExprs.add(e);
			                    }
			                    if(e.getValue0().equals(((WhileSegment1Context)_localctx).inner.d)){
			                        ((WhileSegment1Context)_localctx).valid =  false;
			                       // System.out.println("valid5 false");
			                        changeUpperExprs.add(e);
			                    }
			                }
			            
			                if(!_localctx.valid){
			                    // c = const1 && d=const2, const1>0, const2>0, const1>const2
			                    if(!changeLowerExprs.isEmpty() && !changeUpperExprs.isEmpty()){
			                        if((changeLowerExprs.size()==1) && (changeLowerExprs.size()==1)){
			                            try{
			                                double const1 = Double.parseDouble(changeLowerExprs.get(0).getValue1());
			                                double const2 = Double.parseDouble(changeUpperExprs.get(0).getValue1());
			                                if(const1>0 && const2>0 && const1>const2)
			                                {
			                                    ((WhileSegment1Context)_localctx).valid = true;
			                                }
			                                else{
			                                    ((WhileSegment1Context)_localctx).valid =  false;
			                                   // System.out.println("valid8 false");
			                                }
			                            }
			                            catch(Exception ex){
			                                ((WhileSegment1Context)_localctx).valid =  false;
			                               // System.out.println("valid7 false");
			                            }
			                        }
			                    }else{
			                        ((WhileSegment1Context)_localctx).valid =  false;
			                      //  System.out.println("valid6 false");
			                    }
			                }
			                
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

	public static class IfSegmentContext extends ParserRuleContext {
		public List<Pair<String,String>> exprs;
		public LogicalFunctionContext cond;
		public SegmentSequence1Context seq;
		public SegmentSequence1Context seq1;
		public TerminalNode ELSE() { return getToken(NestingRulesParser.ELSE, 0); }
		public TerminalNode IF() { return getToken(NestingRulesParser.IF, 0); }
		public TerminalNode RPAREN(int i) {
			return getToken(NestingRulesParser.RPAREN, i);
		}
		public SegmentSequence1Context segmentSequence1(int i) {
			return getRuleContext(SegmentSequence1Context.class,i);
		}
		public List<TerminalNode> LPAREN() { return getTokens(NestingRulesParser.LPAREN); }
		public List<TerminalNode> COMMA() { return getTokens(NestingRulesParser.COMMA); }
		public TerminalNode THEN() { return getToken(NestingRulesParser.THEN, 0); }
		public List<SegmentSequence1Context> segmentSequence1() {
			return getRuleContexts(SegmentSequence1Context.class);
		}
		public List<TerminalNode> RPAREN() { return getTokens(NestingRulesParser.RPAREN); }
		public LogicalFunctionContext logicalFunction() {
			return getRuleContext(LogicalFunctionContext.class,0);
		}
		public TerminalNode LPAREN(int i) {
			return getToken(NestingRulesParser.LPAREN, i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(NestingRulesParser.COMMA, i);
		}
		public IfSegmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifSegment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).enterIfSegment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).exitIfSegment(this);
		}
	}

	public final IfSegmentContext ifSegment() throws RecognitionException {
		IfSegmentContext _localctx = new IfSegmentContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_ifSegment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158); match(IF);
			setState(159); match(LPAREN);
			setState(160); ((IfSegmentContext)_localctx).cond = logicalFunction();
			setState(161); match(COMMA);
			setState(162); match(THEN);
			setState(163); match(LPAREN);
			setState(167);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXPR) | (1L << IF) | (1L << WHILE))) != 0)) {
				{
				setState(164); ((IfSegmentContext)_localctx).seq = segmentSequence1();
				 ((IfSegmentContext)_localctx).exprs =  ((IfSegmentContext)_localctx).seq.exprs; 
				}
			}

			setState(169); match(RPAREN);
			setState(177);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(170); match(COMMA);
				setState(171); match(ELSE);
				setState(172); match(LPAREN);
				setState(173); ((IfSegmentContext)_localctx).seq1 = segmentSequence1();
				 _localctx.exprs.addAll(((IfSegmentContext)_localctx).seq.exprs);
				setState(175); match(RPAREN);
				}
			}

			setState(179); match(RPAREN);
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
		public String var;
		public VariableContext v;
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public LeftVarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leftVar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).enterLeftVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).exitLeftVar(this);
		}
	}

	public final LeftVarContext leftVar() throws RecognitionException {
		LeftVarContext _localctx = new LeftVarContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_leftVar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181); ((LeftVarContext)_localctx).v = variable();
			((LeftVarContext)_localctx).var =  ((LeftVarContext)_localctx).v.var;
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
		public String var;
		public VariableContext v;
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public RightVarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rightVar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).enterRightVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).exitRightVar(this);
		}
	}

	public final RightVarContext rightVar() throws RecognitionException {
		RightVarContext _localctx = new RightVarContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_rightVar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184); ((RightVarContext)_localctx).v = variable();
			((RightVarContext)_localctx).var =  ((RightVarContext)_localctx).v.var;
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
		public TerminalNode IDENT() { return getToken(NestingRulesParser.IDENT, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesListener ) ((NestingRulesListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187); ((VariableContext)_localctx).i = match(IDENT);

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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\f\u00c1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\7\3\61\n\3\f\3\16\3"+
		"\64\13\3\3\4\3\4\3\4\3\4\3\4\7\4;\n\4\f\4\16\4>\13\4\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\5\5I\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5"+
		"\6U\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3"+
		"\t\5\tg\n\t\3\n\3\n\3\n\7\nl\n\n\f\n\16\no\13\n\3\13\3\13\3\f\3\f\3\f"+
		"\3\f\3\f\7\fx\n\f\f\f\16\f{\13\f\3\r\3\r\3\r\3\r\3\r\5\r\u0082\n\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u008b\n\16\3\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u0098\n\17\3\17\3\17\5\17\u009c\n"+
		"\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00aa"+
		"\n\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00b4\n\20\3\20\3\20"+
		"\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\23\2\2\24\2\4\6\b\n\f"+
		"\16\20\22\24\26\30\32\34\36 \"$\2\2\u00bd\2&\3\2\2\2\4*\3\2\2\2\6\65\3"+
		"\2\2\2\bH\3\2\2\2\nT\3\2\2\2\fV\3\2\2\2\16_\3\2\2\2\20a\3\2\2\2\22h\3"+
		"\2\2\2\24p\3\2\2\2\26r\3\2\2\2\30|\3\2\2\2\32\u0083\3\2\2\2\34\u008f\3"+
		"\2\2\2\36\u00a0\3\2\2\2 \u00b7\3\2\2\2\"\u00ba\3\2\2\2$\u00bd\3\2\2\2"+
		"&\'\b\2\1\2\'(\5\4\3\2()\b\2\1\2)\3\3\2\2\2*+\b\3\1\2+,\5\b\5\2,\62\b"+
		"\3\1\2-.\5\b\5\2./\b\3\1\2/\61\3\2\2\2\60-\3\2\2\2\61\64\3\2\2\2\62\60"+
		"\3\2\2\2\62\63\3\2\2\2\63\5\3\2\2\2\64\62\3\2\2\2\65\66\5\n\6\2\66<\b"+
		"\4\1\2\678\5\n\6\289\b\4\1\29;\3\2\2\2:\67\3\2\2\2;>\3\2\2\2<:\3\2\2\2"+
		"<=\3\2\2\2=\7\3\2\2\2><\3\2\2\2?@\5\34\17\2@A\b\5\1\2AI\3\2\2\2BC\5\f"+
		"\7\2CD\b\5\1\2DI\3\2\2\2EF\5\36\20\2FG\b\5\1\2GI\3\2\2\2H?\3\2\2\2HB\3"+
		"\2\2\2HE\3\2\2\2I\t\3\2\2\2JK\b\6\1\2KL\5\32\16\2LM\b\6\1\2MU\3\2\2\2"+
		"NO\5\f\7\2OP\b\6\1\2PU\3\2\2\2QR\5\36\20\2RS\b\6\1\2SU\3\2\2\2TJ\3\2\2"+
		"\2TN\3\2\2\2TQ\3\2\2\2U\13\3\2\2\2VW\7\4\2\2WX\7\b\2\2XY\5\16\b\2YZ\b"+
		"\7\1\2Z[\7\n\2\2[\\\5\20\t\2\\]\b\7\1\2]^\7\t\2\2^\r\3\2\2\2_`\5$\23\2"+
		"`\17\3\2\2\2af\5$\23\2bc\7\b\2\2cd\5\22\n\2de\7\t\2\2eg\3\2\2\2fb\3\2"+
		"\2\2fg\3\2\2\2g\21\3\2\2\2hm\5\24\13\2ij\7\n\2\2jl\5\24\13\2ki\3\2\2\2"+
		"lo\3\2\2\2mk\3\2\2\2mn\3\2\2\2n\23\3\2\2\2om\3\2\2\2pq\5\20\t\2q\25\3"+
		"\2\2\2rs\5\f\7\2sy\b\f\1\2tu\5\f\7\2uv\b\f\1\2vx\3\2\2\2wt\3\2\2\2x{\3"+
		"\2\2\2yw\3\2\2\2yz\3\2\2\2z\27\3\2\2\2{y\3\2\2\2|\u0081\5$\23\2}~\7\b"+
		"\2\2~\177\5\22\n\2\177\u0080\7\t\2\2\u0080\u0082\3\2\2\2\u0081}\3\2\2"+
		"\2\u0081\u0082\3\2\2\2\u0082\31\3\2\2\2\u0083\u0084\7\13\2\2\u0084\u0085"+
		"\7\b\2\2\u0085\u0086\5 \21\2\u0086\u0087\7\n\2\2\u0087\u008a\5\"\22\2"+
		"\u0088\u0089\7\n\2\2\u0089\u008b\5\6\4\2\u008a\u0088\3\2\2\2\u008a\u008b"+
		"\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008d\7\t\2\2\u008d\u008e\b\16\1\2"+
		"\u008e\33\3\2\2\2\u008f\u0090\b\17\1\2\u0090\u0091\7\13\2\2\u0091\u0092"+
		"\7\b\2\2\u0092\u0093\5 \21\2\u0093\u0094\7\n\2\2\u0094\u0095\5\"\22\2"+
		"\u0095\u0097\7\n\2\2\u0096\u0098\5\26\f\2\u0097\u0096\3\2\2\2\u0097\u0098"+
		"\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009b\5\32\16\2\u009a\u009c\5\26\f"+
		"\2\u009b\u009a\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009e"+
		"\7\t\2\2\u009e\u009f\b\17\1\2\u009f\35\3\2\2\2\u00a0\u00a1\7\5\2\2\u00a1"+
		"\u00a2\7\b\2\2\u00a2\u00a3\5\30\r\2\u00a3\u00a4\7\n\2\2\u00a4\u00a5\7"+
		"\6\2\2\u00a5\u00a9\7\b\2\2\u00a6\u00a7\5\6\4\2\u00a7\u00a8\b\20\1\2\u00a8"+
		"\u00aa\3\2\2\2\u00a9\u00a6\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ab\3\2"+
		"\2\2\u00ab\u00b3\7\t\2\2\u00ac\u00ad\7\n\2\2\u00ad\u00ae\7\7\2\2\u00ae"+
		"\u00af\7\b\2\2\u00af\u00b0\5\6\4\2\u00b0\u00b1\b\20\1\2\u00b1\u00b2\7"+
		"\t\2\2\u00b2\u00b4\3\2\2\2\u00b3\u00ac\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4"+
		"\u00b5\3\2\2\2\u00b5\u00b6\7\t\2\2\u00b6\37\3\2\2\2\u00b7\u00b8\5$\23"+
		"\2\u00b8\u00b9\b\21\1\2\u00b9!\3\2\2\2\u00ba\u00bb\5$\23\2\u00bb\u00bc"+
		"\b\22\1\2\u00bc#\3\2\2\2\u00bd\u00be\7\f\2\2\u00be\u00bf\b\23\1\2\u00bf"+
		"%\3\2\2\2\17\62<HTfmy\u0081\u008a\u0097\u009b\u00a9\u00b3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}