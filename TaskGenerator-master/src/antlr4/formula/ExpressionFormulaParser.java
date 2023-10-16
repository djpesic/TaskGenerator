// Generated from ExpressionFormula.g4 by ANTLR 4.4

package formula;
import java.util.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExpressionFormulaParser extends Parser {
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
		RULE_formula = 0, RULE_segmentSequence = 1, RULE_segment = 2, RULE_expression = 3, 
		RULE_leftSide = 4, RULE_rightSide = 5, RULE_logicalFunction = 6, RULE_argList = 7, 
		RULE_arg = 8, RULE_whileSegment = 9, RULE_ifSegment = 10, RULE_leftVar = 11, 
		RULE_rightVar = 12, RULE_variable = 13;
	public static final String[] ruleNames = {
		"formula", "segmentSequence", "segment", "expression", "leftSide", "rightSide", 
		"logicalFunction", "argList", "arg", "whileSegment", "ifSegment", "leftVar", 
		"rightVar", "variable"
	};

	@Override
	public String getGrammarFileName() { return "ExpressionFormula.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ExpressionFormulaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FormulaContext extends ParserRuleContext {
		public boolean valid;
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
			if ( listener instanceof ExpressionFormulaListener ) ((ExpressionFormulaListener)listener).enterFormula(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionFormulaListener ) ((ExpressionFormulaListener)listener).exitFormula(this);
		}
	}

	public final FormulaContext formula() throws RecognitionException {
		FormulaContext _localctx = new FormulaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_formula);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28); ((FormulaContext)_localctx).s = segmentSequence();
			 ((FormulaContext)_localctx).valid =  ((FormulaContext)_localctx).s.valid;
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
			if ( listener instanceof ExpressionFormulaListener ) ((ExpressionFormulaListener)listener).enterSegmentSequence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionFormulaListener ) ((ExpressionFormulaListener)listener).exitSegmentSequence(this);
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
			setState(32); ((SegmentSequenceContext)_localctx).s1 = segment();
			 if(!(((SegmentSequenceContext)_localctx).s1.valid)) ((SegmentSequenceContext)_localctx).valid = false; 
			setState(39);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXPR) | (1L << IF) | (1L << WHILE))) != 0)) {
				{
				{
				setState(34); ((SegmentSequenceContext)_localctx).s2 = segment();
				 if(!(((SegmentSequenceContext)_localctx).s2.valid)) ((SegmentSequenceContext)_localctx).valid = false; 
				}
				}
				setState(41);
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
		public SegmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_segment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionFormulaListener ) ((ExpressionFormulaListener)listener).enterSegment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionFormulaListener ) ((ExpressionFormulaListener)listener).exitSegment(this);
		}
	}

	public final SegmentContext segment() throws RecognitionException {
		SegmentContext _localctx = new SegmentContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_segment);
		try {
			setState(51);
			switch (_input.LA(1)) {
			case WHILE:
				enterOuterAlt(_localctx, 1);
				{
				setState(42); ((SegmentContext)_localctx).w = whileSegment();
				 ((SegmentContext)_localctx).valid =  ((SegmentContext)_localctx).w.valid;
				}
				break;
			case EXPR:
				enterOuterAlt(_localctx, 2);
				{
				setState(45); ((SegmentContext)_localctx).e = expression();
				 ((SegmentContext)_localctx).valid =  ((SegmentContext)_localctx).e.valid;
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 3);
				{
				setState(48); ((SegmentContext)_localctx).ifSeg = ifSegment();
				 ((SegmentContext)_localctx).valid =  ((SegmentContext)_localctx).ifSeg.valid;
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
		public boolean valid;
		public RightSideContext rs;
		public TerminalNode LPAREN() { return getToken(ExpressionFormulaParser.LPAREN, 0); }
		public TerminalNode COMMA() { return getToken(ExpressionFormulaParser.COMMA, 0); }
		public RightSideContext rightSide() {
			return getRuleContext(RightSideContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ExpressionFormulaParser.RPAREN, 0); }
		public TerminalNode EXPR() { return getToken(ExpressionFormulaParser.EXPR, 0); }
		public LeftSideContext leftSide() {
			return getRuleContext(LeftSideContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionFormulaListener ) ((ExpressionFormulaListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionFormulaListener ) ((ExpressionFormulaListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53); match(EXPR);
			setState(54); match(LPAREN);
			setState(55); leftSide();
			setState(56); match(COMMA);
			setState(57); ((ExpressionContext)_localctx).rs = rightSide();
			 ((ExpressionContext)_localctx).valid =  ((ExpressionContext)_localctx).rs.valid;
			setState(59); match(RPAREN);
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
			if ( listener instanceof ExpressionFormulaListener ) ((ExpressionFormulaListener)listener).enterLeftSide(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionFormulaListener ) ((ExpressionFormulaListener)listener).exitLeftSide(this);
		}
	}

	public final LeftSideContext leftSide() throws RecognitionException {
		LeftSideContext _localctx = new LeftSideContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_leftSide);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61); variable();
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
		public boolean valid;
		public boolean func;
		public VariableContext v;
		public ArgListContext al;
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(ExpressionFormulaParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ExpressionFormulaParser.RPAREN, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public RightSideContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rightSide; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionFormulaListener ) ((ExpressionFormulaListener)listener).enterRightSide(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionFormulaListener ) ((ExpressionFormulaListener)listener).exitRightSide(this);
		}
	}

	public final RightSideContext rightSide() throws RecognitionException {
		RightSideContext _localctx = new RightSideContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_rightSide);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((RightSideContext)_localctx).func = false;
			setState(64); ((RightSideContext)_localctx).v = variable();
			setState(70);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(65); match(LPAREN);
				setState(66); ((RightSideContext)_localctx).al = argList();
				((RightSideContext)_localctx).func = true;
				setState(68); match(RPAREN);
				}
			}


			        
			        if(_localctx.func){
			            if(!(((RightSideContext)_localctx).al.valid)) ((RightSideContext)_localctx).valid = false;
			            else
			            switch((((RightSideContext)_localctx).v!=null?_input.getText(((RightSideContext)_localctx).v.start,((RightSideContext)_localctx).v.stop):null)){
			                case "add":
			                case "sub":
			                case "mul":
			                case "divI":
			                case "and":
			                case "or":
			                case "mod":
			                case "not":
			                case "divF":
			                case "index":
			                    ((RightSideContext)_localctx).valid = true;
			                    break;
			                default: ((RightSideContext)_localctx).valid =  false; break;
			            }
			        }else {((RightSideContext)_localctx).valid =  true;}
			        
			    
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
		public boolean valid;
		public boolean func;
		public VariableContext v;
		public ArgListContext al;
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(ExpressionFormulaParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ExpressionFormulaParser.RPAREN, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public LogicalFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalFunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionFormulaListener ) ((ExpressionFormulaListener)listener).enterLogicalFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionFormulaListener ) ((ExpressionFormulaListener)listener).exitLogicalFunction(this);
		}
	}

	public final LogicalFunctionContext logicalFunction() throws RecognitionException {
		LogicalFunctionContext _localctx = new LogicalFunctionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_logicalFunction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((LogicalFunctionContext)_localctx).func = false;
			setState(75); ((LogicalFunctionContext)_localctx).v = variable();
			setState(81);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(76); match(LPAREN);
				setState(77); ((LogicalFunctionContext)_localctx).al = argList();
				((LogicalFunctionContext)_localctx).func = true;
				setState(79); match(RPAREN);
				}
			}


			        
			        if(_localctx.func){
			            if(!(((LogicalFunctionContext)_localctx).al.valid)) ((LogicalFunctionContext)_localctx).valid = false;
			            else
			            switch((((LogicalFunctionContext)_localctx).v!=null?_input.getText(((LogicalFunctionContext)_localctx).v.start,((LogicalFunctionContext)_localctx).v.stop):null)){
			                case "gt":
			                case "gte":
			                case "lt":
			                case "lte":
			                case "neq":
			                case "eq":
			                    ((LogicalFunctionContext)_localctx).valid = true;
			                    break;
			                default: ((LogicalFunctionContext)_localctx).valid =  false; break;
			            }
			        }else {((LogicalFunctionContext)_localctx).valid =  true;}
			        
			    
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
		public boolean valid;
		public ArgContext a;
		public ArgContext a1;
		public ArgContext arg(int i) {
			return getRuleContext(ArgContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ExpressionFormulaParser.COMMA); }
		public List<ArgContext> arg() {
			return getRuleContexts(ArgContext.class);
		}
		public TerminalNode COMMA(int i) {
			return getToken(ExpressionFormulaParser.COMMA, i);
		}
		public ArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionFormulaListener ) ((ExpressionFormulaListener)listener).enterArgList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionFormulaListener ) ((ExpressionFormulaListener)listener).exitArgList(this);
		}
	}

	public final ArgListContext argList() throws RecognitionException {
		ArgListContext _localctx = new ArgListContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_argList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((ArgListContext)_localctx).valid =  true;
			setState(86); ((ArgListContext)_localctx).a = arg();
			 if(!(((ArgListContext)_localctx).a.valid))((ArgListContext)_localctx).valid = false;
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(88); match(COMMA);
				setState(89); ((ArgListContext)_localctx).a1 = arg();
				 if(!(((ArgListContext)_localctx).a1.valid))((ArgListContext)_localctx).valid = false;
				}
				}
				setState(96);
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
		public boolean valid;
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
			if ( listener instanceof ExpressionFormulaListener ) ((ExpressionFormulaListener)listener).enterArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionFormulaListener ) ((ExpressionFormulaListener)listener).exitArg(this);
		}
	}

	public final ArgContext arg() throws RecognitionException {
		ArgContext _localctx = new ArgContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_arg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97); ((ArgContext)_localctx).rs = rightSide();
			((ArgContext)_localctx).valid = ((ArgContext)_localctx).rs.valid;
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
		public boolean valid;
		public SegmentSequenceContext s;
		public LeftVarContext leftVar() {
			return getRuleContext(LeftVarContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(ExpressionFormulaParser.LPAREN, 0); }
		public RightVarContext rightVar() {
			return getRuleContext(RightVarContext.class,0);
		}
		public SegmentSequenceContext segmentSequence() {
			return getRuleContext(SegmentSequenceContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(ExpressionFormulaParser.COMMA); }
		public TerminalNode RPAREN() { return getToken(ExpressionFormulaParser.RPAREN, 0); }
		public TerminalNode WHILE() { return getToken(ExpressionFormulaParser.WHILE, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(ExpressionFormulaParser.COMMA, i);
		}
		public WhileSegmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileSegment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionFormulaListener ) ((ExpressionFormulaListener)listener).enterWhileSegment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionFormulaListener ) ((ExpressionFormulaListener)listener).exitWhileSegment(this);
		}
	}

	public final WhileSegmentContext whileSegment() throws RecognitionException {
		WhileSegmentContext _localctx = new WhileSegmentContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_whileSegment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((WhileSegmentContext)_localctx).valid =  true;
			setState(101); match(WHILE);
			setState(102); match(LPAREN);
			setState(103); leftVar();
			setState(104); match(COMMA);
			setState(105); rightVar();
			setState(110);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(106); match(COMMA);
				setState(107); ((WhileSegmentContext)_localctx).s = segmentSequence();
				 ((WhileSegmentContext)_localctx).valid = ((WhileSegmentContext)_localctx).s.valid;
				}
			}

			setState(112); match(RPAREN);
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
		public boolean valid;
		public LogicalFunctionContext cond;
		public SegmentSequenceContext seq;
		public SegmentSequenceContext seq1;
		public TerminalNode ELSE() { return getToken(ExpressionFormulaParser.ELSE, 0); }
		public TerminalNode IF() { return getToken(ExpressionFormulaParser.IF, 0); }
		public TerminalNode RPAREN(int i) {
			return getToken(ExpressionFormulaParser.RPAREN, i);
		}
		public List<TerminalNode> LPAREN() { return getTokens(ExpressionFormulaParser.LPAREN); }
		public List<SegmentSequenceContext> segmentSequence() {
			return getRuleContexts(SegmentSequenceContext.class);
		}
		public List<TerminalNode> COMMA() { return getTokens(ExpressionFormulaParser.COMMA); }
		public TerminalNode THEN() { return getToken(ExpressionFormulaParser.THEN, 0); }
		public SegmentSequenceContext segmentSequence(int i) {
			return getRuleContext(SegmentSequenceContext.class,i);
		}
		public List<TerminalNode> RPAREN() { return getTokens(ExpressionFormulaParser.RPAREN); }
		public LogicalFunctionContext logicalFunction() {
			return getRuleContext(LogicalFunctionContext.class,0);
		}
		public TerminalNode LPAREN(int i) {
			return getToken(ExpressionFormulaParser.LPAREN, i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(ExpressionFormulaParser.COMMA, i);
		}
		public IfSegmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifSegment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionFormulaListener ) ((ExpressionFormulaListener)listener).enterIfSegment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionFormulaListener ) ((ExpressionFormulaListener)listener).exitIfSegment(this);
		}
	}

	public final IfSegmentContext ifSegment() throws RecognitionException {
		IfSegmentContext _localctx = new IfSegmentContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_ifSegment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((IfSegmentContext)_localctx).valid =  true;
			setState(115); match(IF);
			setState(116); match(LPAREN);
			setState(117); ((IfSegmentContext)_localctx).cond = logicalFunction();
			 ((IfSegmentContext)_localctx).valid =  _localctx.valid && ((IfSegmentContext)_localctx).cond.valid;
			setState(119); match(COMMA);
			setState(120); match(THEN);
			setState(121); match(LPAREN);
			setState(125);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXPR) | (1L << IF) | (1L << WHILE))) != 0)) {
				{
				setState(122); ((IfSegmentContext)_localctx).seq = segmentSequence();
				 ((IfSegmentContext)_localctx).valid =  _localctx.valid && ((IfSegmentContext)_localctx).seq.valid;
				}
			}

			setState(127); match(RPAREN);
			setState(135);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(128); match(COMMA);
				setState(129); match(ELSE);
				setState(130); match(LPAREN);
				setState(131); ((IfSegmentContext)_localctx).seq1 = segmentSequence();
				 ((IfSegmentContext)_localctx).valid =  _localctx.valid && ((IfSegmentContext)_localctx).seq1.valid;
				setState(133); match(RPAREN);
				}
			}

			setState(137); match(RPAREN);
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
			if ( listener instanceof ExpressionFormulaListener ) ((ExpressionFormulaListener)listener).enterLeftVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionFormulaListener ) ((ExpressionFormulaListener)listener).exitLeftVar(this);
		}
	}

	public final LeftVarContext leftVar() throws RecognitionException {
		LeftVarContext _localctx = new LeftVarContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_leftVar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139); variable();
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
			if ( listener instanceof ExpressionFormulaListener ) ((ExpressionFormulaListener)listener).enterRightVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionFormulaListener ) ((ExpressionFormulaListener)listener).exitRightVar(this);
		}
	}

	public final RightVarContext rightVar() throws RecognitionException {
		RightVarContext _localctx = new RightVarContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_rightVar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141); variable();
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
		public TerminalNode IDENT() { return getToken(ExpressionFormulaParser.IDENT, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionFormulaListener ) ((ExpressionFormulaListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionFormulaListener ) ((ExpressionFormulaListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143); ((VariableContext)_localctx).i = match(IDENT);

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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\f\u0095\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\7\3(\n\3\f\3\16\3+\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4"+
		"\66\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\5\7I\n\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bT\n\b\3\b\3\b\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t_\n\t\f\t\16\tb\13\t\3\n\3\n\3\n\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13q\n\13\3\13\3\13\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0080\n\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\5\f\u008a\n\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17"+
		"\3\17\2\2\20\2\4\6\b\n\f\16\20\22\24\26\30\32\34\2\2\u008f\2\36\3\2\2"+
		"\2\4!\3\2\2\2\6\65\3\2\2\2\b\67\3\2\2\2\n?\3\2\2\2\fA\3\2\2\2\16L\3\2"+
		"\2\2\20W\3\2\2\2\22c\3\2\2\2\24f\3\2\2\2\26t\3\2\2\2\30\u008d\3\2\2\2"+
		"\32\u008f\3\2\2\2\34\u0091\3\2\2\2\36\37\5\4\3\2\37 \b\2\1\2 \3\3\2\2"+
		"\2!\"\b\3\1\2\"#\5\6\4\2#)\b\3\1\2$%\5\6\4\2%&\b\3\1\2&(\3\2\2\2\'$\3"+
		"\2\2\2(+\3\2\2\2)\'\3\2\2\2)*\3\2\2\2*\5\3\2\2\2+)\3\2\2\2,-\5\24\13\2"+
		"-.\b\4\1\2.\66\3\2\2\2/\60\5\b\5\2\60\61\b\4\1\2\61\66\3\2\2\2\62\63\5"+
		"\26\f\2\63\64\b\4\1\2\64\66\3\2\2\2\65,\3\2\2\2\65/\3\2\2\2\65\62\3\2"+
		"\2\2\66\7\3\2\2\2\678\7\4\2\289\7\b\2\29:\5\n\6\2:;\7\n\2\2;<\5\f\7\2"+
		"<=\b\5\1\2=>\7\t\2\2>\t\3\2\2\2?@\5\34\17\2@\13\3\2\2\2AB\b\7\1\2BH\5"+
		"\34\17\2CD\7\b\2\2DE\5\20\t\2EF\b\7\1\2FG\7\t\2\2GI\3\2\2\2HC\3\2\2\2"+
		"HI\3\2\2\2IJ\3\2\2\2JK\b\7\1\2K\r\3\2\2\2LM\b\b\1\2MS\5\34\17\2NO\7\b"+
		"\2\2OP\5\20\t\2PQ\b\b\1\2QR\7\t\2\2RT\3\2\2\2SN\3\2\2\2ST\3\2\2\2TU\3"+
		"\2\2\2UV\b\b\1\2V\17\3\2\2\2WX\b\t\1\2XY\5\22\n\2Y`\b\t\1\2Z[\7\n\2\2"+
		"[\\\5\22\n\2\\]\b\t\1\2]_\3\2\2\2^Z\3\2\2\2_b\3\2\2\2`^\3\2\2\2`a\3\2"+
		"\2\2a\21\3\2\2\2b`\3\2\2\2cd\5\f\7\2de\b\n\1\2e\23\3\2\2\2fg\b\13\1\2"+
		"gh\7\13\2\2hi\7\b\2\2ij\5\30\r\2jk\7\n\2\2kp\5\32\16\2lm\7\n\2\2mn\5\4"+
		"\3\2no\b\13\1\2oq\3\2\2\2pl\3\2\2\2pq\3\2\2\2qr\3\2\2\2rs\7\t\2\2s\25"+
		"\3\2\2\2tu\b\f\1\2uv\7\5\2\2vw\7\b\2\2wx\5\16\b\2xy\b\f\1\2yz\7\n\2\2"+
		"z{\7\6\2\2{\177\7\b\2\2|}\5\4\3\2}~\b\f\1\2~\u0080\3\2\2\2\177|\3\2\2"+
		"\2\177\u0080\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0089\7\t\2\2\u0082\u0083"+
		"\7\n\2\2\u0083\u0084\7\7\2\2\u0084\u0085\7\b\2\2\u0085\u0086\5\4\3\2\u0086"+
		"\u0087\b\f\1\2\u0087\u0088\7\t\2\2\u0088\u008a\3\2\2\2\u0089\u0082\3\2"+
		"\2\2\u0089\u008a\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008c\7\t\2\2\u008c"+
		"\27\3\2\2\2\u008d\u008e\5\34\17\2\u008e\31\3\2\2\2\u008f\u0090\5\34\17"+
		"\2\u0090\33\3\2\2\2\u0091\u0092\7\f\2\2\u0092\u0093\b\17\1\2\u0093\35"+
		"\3\2\2\2\n)\65HS`p\177\u0089";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}