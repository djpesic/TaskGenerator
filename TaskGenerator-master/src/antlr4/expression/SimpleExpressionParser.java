// Generated from SimpleExpression.g4 by ANTLR 4.4

package expression;
import common.*;
import programSegment.*;
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
public class SimpleExpressionParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__4=1, T__3=2, T__2=3, T__1=4, T__0=5, WS=6, PLUS=7, MINUS=8, OR=9, MUL=10, 
		FLOAT_DIV=11, INT_DIV=12, MOD=13, AND=14, NOT=15, REAL=16, SCALE=17, IDENT=18, 
		STRING_LITERAL=19, INTEGER=20, OPERAND_STUB=21, NUMBER_STUB=22;
	public static final String[] tokenNames = {
		"<INVALID>", "'('", "')'", "'['", "','", "']'", "WS", "'+'", "'-'", "'or'", 
		"'*'", "'/'", "'div'", "'mod'", "'and'", "'not'", "REAL", "SCALE", "IDENT", 
		"STRING_LITERAL", "INTEGER", "'#'", "'@'"
	};
	public static final int
		RULE_simpleExpression = 0, RULE_addop = 1, RULE_mulop = 2, RULE_term = 3, 
		RULE_signedFactor = 4, RULE_factor = 5, RULE_variable = 6, RULE_entireVar = 7, 
		RULE_indexedVar = 8, RULE_number = 9, RULE_string = 10;
	public static final String[] ruleNames = {
		"simpleExpression", "addop", "mulop", "term", "signedFactor", "factor", 
		"variable", "entireVar", "indexedVar", "number", "string"
	};

	@Override
	public String getGrammarFileName() { return "SimpleExpression.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SimpleExpressionParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class SimpleExpressionContext extends ParserRuleContext {
		public SimpleExprType simpleExprType;
		public SimpleExprType.AddopTerm addopTerm;
		public TermContext termType;
		public AddopContext op;
		public TermContext termType1;
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<AddopContext> addop() {
			return getRuleContexts(AddopContext.class);
		}
		public AddopContext addop(int i) {
			return getRuleContext(AddopContext.class,i);
		}
		public SimpleExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleExpressionListener ) ((SimpleExpressionListener)listener).enterSimpleExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleExpressionListener ) ((SimpleExpressionListener)listener).exitSimpleExpression(this);
		}
	}

	public final SimpleExpressionContext simpleExpression() throws RecognitionException {
		SimpleExpressionContext _localctx = new SimpleExpressionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_simpleExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22); ((SimpleExpressionContext)_localctx).termType = term();

			        ((SimpleExpressionContext)_localctx).simpleExprType =  new SimpleExprType();
			        _localctx.simpleExprType.setTerm(((SimpleExpressionContext)_localctx).termType.termType);
			    
			setState(30);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << OR))) != 0)) {
				{
				{
				setState(24); ((SimpleExpressionContext)_localctx).op = addop();
				setState(25); ((SimpleExpressionContext)_localctx).termType1 = term();

				        ((SimpleExpressionContext)_localctx).addopTerm =  new SimpleExprType.AddopTerm();

				        _localctx.addopTerm.setAddop((((SimpleExpressionContext)_localctx).op!=null?_input.getText(((SimpleExpressionContext)_localctx).op.start,((SimpleExpressionContext)_localctx).op.stop):null));
				        _localctx.addopTerm.setTerm(((SimpleExpressionContext)_localctx).termType1.termType);
				        _localctx.simpleExprType.getAddopTerm().add(_localctx.addopTerm);
				    
				}
				}
				setState(32);
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

	public static class AddopContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(SimpleExpressionParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(SimpleExpressionParser.MINUS, 0); }
		public TerminalNode OR() { return getToken(SimpleExpressionParser.OR, 0); }
		public AddopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleExpressionListener ) ((SimpleExpressionListener)listener).enterAddop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleExpressionListener ) ((SimpleExpressionListener)listener).exitAddop(this);
		}
	}

	public final AddopContext addop() throws RecognitionException {
		AddopContext _localctx = new AddopContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_addop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << OR))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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

	public static class MulopContext extends ParserRuleContext {
		public TerminalNode AND() { return getToken(SimpleExpressionParser.AND, 0); }
		public TerminalNode MOD() { return getToken(SimpleExpressionParser.MOD, 0); }
		public TerminalNode MUL() { return getToken(SimpleExpressionParser.MUL, 0); }
		public TerminalNode FLOAT_DIV() { return getToken(SimpleExpressionParser.FLOAT_DIV, 0); }
		public TerminalNode INT_DIV() { return getToken(SimpleExpressionParser.INT_DIV, 0); }
		public MulopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mulop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleExpressionListener ) ((SimpleExpressionListener)listener).enterMulop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleExpressionListener ) ((SimpleExpressionListener)listener).exitMulop(this);
		}
	}

	public final MulopContext mulop() throws RecognitionException {
		MulopContext _localctx = new MulopContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_mulop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << FLOAT_DIV) | (1L << INT_DIV) | (1L << MOD) | (1L << AND))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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

	public static class TermContext extends ParserRuleContext {
		public TermType termType;
		public TermType.MulopSignedFactor mulopSignedFactor;
		public SignedFactorContext sgnFactor;
		public MulopContext op;
		public SignedFactorContext signedFactor1;
		public List<SignedFactorContext> signedFactor() {
			return getRuleContexts(SignedFactorContext.class);
		}
		public SignedFactorContext signedFactor(int i) {
			return getRuleContext(SignedFactorContext.class,i);
		}
		public List<MulopContext> mulop() {
			return getRuleContexts(MulopContext.class);
		}
		public MulopContext mulop(int i) {
			return getRuleContext(MulopContext.class,i);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleExpressionListener ) ((SimpleExpressionListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleExpressionListener ) ((SimpleExpressionListener)listener).exitTerm(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37); ((TermContext)_localctx).sgnFactor = signedFactor();

			        ((TermContext)_localctx).termType =  new TermType();
			        _localctx.termType.setSignedFactor(((TermContext)_localctx).sgnFactor.sgnFactorType);
			    
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << FLOAT_DIV) | (1L << INT_DIV) | (1L << MOD) | (1L << AND))) != 0)) {
				{
				{
				setState(39); ((TermContext)_localctx).op = mulop();
				setState(40); ((TermContext)_localctx).signedFactor1 = signedFactor();

				        ((TermContext)_localctx).mulopSignedFactor =  new TermType.MulopSignedFactor();
				        _localctx.mulopSignedFactor.setMulop((((TermContext)_localctx).op!=null?_input.getText(((TermContext)_localctx).op.start,((TermContext)_localctx).op.stop):null));
				        _localctx.mulopSignedFactor.setSignedFactor(((TermContext)_localctx).signedFactor1.sgnFactorType);
				        _localctx.termType.getMulopSignedFactor().add(_localctx.mulopSignedFactor);
				    
				}
				}
				setState(47);
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

	public static class SignedFactorContext extends ParserRuleContext {
		public SignedFactorType sgnFactorType;
		public Token pl;
		public Token mn;
		public FactorContext fact;
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(SimpleExpressionParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(SimpleExpressionParser.MINUS, 0); }
		public SignedFactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_signedFactor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleExpressionListener ) ((SimpleExpressionListener)listener).enterSignedFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleExpressionListener ) ((SimpleExpressionListener)listener).exitSignedFactor(this);
		}
	}

	public final SignedFactorContext signedFactor() throws RecognitionException {
		SignedFactorContext _localctx = new SignedFactorContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_signedFactor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			switch (_input.LA(1)) {
			case PLUS:
				{
				setState(48); ((SignedFactorContext)_localctx).pl = match(PLUS);
				}
				break;
			case MINUS:
				{
				setState(49); ((SignedFactorContext)_localctx).mn = match(MINUS);
				}
				break;
			case T__4:
			case NOT:
			case REAL:
			case IDENT:
			case STRING_LITERAL:
			case INTEGER:
			case OPERAND_STUB:
			case NUMBER_STUB:
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(52); ((SignedFactorContext)_localctx).fact = factor();

			        ((SignedFactorContext)_localctx).sgnFactorType =  new SignedFactorType();
			        _localctx.sgnFactorType.setFactor(((SignedFactorContext)_localctx).fact.factorType);
			        if(((SignedFactorContext)_localctx).pl!=null){
			            _localctx.sgnFactorType.setSign((((SignedFactorContext)_localctx).pl!=null?((SignedFactorContext)_localctx).pl.getText():null));
			        }else if(((SignedFactorContext)_localctx).mn!=null){
			            _localctx.sgnFactorType.setSign((((SignedFactorContext)_localctx).mn!=null?((SignedFactorContext)_localctx).mn.getText():null));
			        }else{
			            _localctx.sgnFactorType.setSign("");
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

	public static class FactorContext extends ParserRuleContext {
		public FactorType factorType;
		public FactorType.SubExpr subExprType;
		public VariableContext var;
		public NumberContext num;
		public StringContext str;
		public FactorContext fact;
		public SimpleExpressionContext subExpr;
		public TerminalNode NOT() { return getToken(SimpleExpressionParser.NOT, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public SimpleExpressionContext simpleExpression() {
			return getRuleContext(SimpleExpressionContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleExpressionListener ) ((SimpleExpressionListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleExpressionListener ) ((SimpleExpressionListener)listener).exitFactor(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_factor);
		try {
			setState(73);
			switch (_input.LA(1)) {
			case IDENT:
			case OPERAND_STUB:
				enterOuterAlt(_localctx, 1);
				{
				setState(55); ((FactorContext)_localctx).var = variable();

				        ((FactorContext)_localctx).factorType =  new FactorType();
				        _localctx.factorType.setVar(((FactorContext)_localctx).var.varType);
				    
				}
				break;
			case REAL:
			case INTEGER:
			case NUMBER_STUB:
				enterOuterAlt(_localctx, 2);
				{
				setState(58); ((FactorContext)_localctx).num = number();

				        ((FactorContext)_localctx).factorType =  new FactorType();
				        _localctx.factorType.setConstant(((FactorContext)_localctx).num.num);
				    
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(61); ((FactorContext)_localctx).str = string();

				        ((FactorContext)_localctx).factorType =  new FactorType();
				        _localctx.factorType.setConstant(((FactorContext)_localctx).str.str);
				    
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 4);
				{
				setState(64); match(NOT);
				setState(65); ((FactorContext)_localctx).fact = factor();

				        ((FactorContext)_localctx).factorType =  new FactorType();
				        _localctx.factorType.setNotFactor(((FactorContext)_localctx).fact.factorType);
				    
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 5);
				{
				setState(68); match(T__4);
				setState(69); ((FactorContext)_localctx).subExpr = simpleExpression();
				setState(70); match(T__3);

				        ((FactorContext)_localctx).factorType =  new FactorType();
				        ((FactorContext)_localctx).subExprType =  new FactorType.SubExpr();
				        _localctx.subExprType.setLparen(new FactorType.SubExpr.Lparen());
				        _localctx.subExprType.setRparen(new FactorType.SubExpr.Rparen());
				        _localctx.subExprType.setSimpleExpr(((FactorContext)_localctx).subExpr.simpleExprType);
				        _localctx.factorType.setSubExpr(_localctx.subExprType);
				    
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

	public static class VariableContext extends ParserRuleContext {
		public FactorType.Var varType;
		public EntireVarContext entVar;
		public IndexedVarContext indVar;
		public EntireVarContext entireVar() {
			return getRuleContext(EntireVarContext.class,0);
		}
		public IndexedVarContext indexedVar() {
			return getRuleContext(IndexedVarContext.class,0);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleExpressionListener ) ((SimpleExpressionListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleExpressionListener ) ((SimpleExpressionListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_variable);
		try {
			setState(81);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(75); ((VariableContext)_localctx).entVar = entireVar();

				        ((VariableContext)_localctx).varType =  new FactorType.Var();
				        _localctx.varType.setName(((VariableContext)_localctx).entVar.entVarType);
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(78); ((VariableContext)_localctx).indVar = indexedVar();

				        ((VariableContext)_localctx).varType =  new FactorType.Var();
				        _localctx.varType.setIndexed(((VariableContext)_localctx).indVar.indVarType);
				    
				}
				break;
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

	public static class EntireVarContext extends ParserRuleContext {
		public String entVarType;
		public Token IDENT;
		public Token OPERAND_STUB;
		public TerminalNode OPERAND_STUB() { return getToken(SimpleExpressionParser.OPERAND_STUB, 0); }
		public TerminalNode IDENT() { return getToken(SimpleExpressionParser.IDENT, 0); }
		public EntireVarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entireVar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleExpressionListener ) ((SimpleExpressionListener)listener).enterEntireVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleExpressionListener ) ((SimpleExpressionListener)listener).exitEntireVar(this);
		}
	}

	public final EntireVarContext entireVar() throws RecognitionException {
		EntireVarContext _localctx = new EntireVarContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_entireVar);
		try {
			setState(87);
			switch (_input.LA(1)) {
			case IDENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(83); ((EntireVarContext)_localctx).IDENT = match(IDENT);
				((EntireVarContext)_localctx).entVarType =  (((EntireVarContext)_localctx).IDENT!=null?((EntireVarContext)_localctx).IDENT.getText():null);
				}
				break;
			case OPERAND_STUB:
				enterOuterAlt(_localctx, 2);
				{
				setState(85); ((EntireVarContext)_localctx).OPERAND_STUB = match(OPERAND_STUB);
				((EntireVarContext)_localctx).entVarType =  (((EntireVarContext)_localctx).OPERAND_STUB!=null?((EntireVarContext)_localctx).OPERAND_STUB.getText():null);
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

	public static class IndexedVarContext extends ParserRuleContext {
		public FactorType.Var.Indexed indVarType;
		public FactorType.Var.Indexed.CommaExpr commaExprType;
		public String varName;
		public Token ident;
		public Token opStub;
		public SimpleExpressionContext expr;
		public SimpleExpressionContext expr1;
		public SimpleExpressionContext simpleExpression(int i) {
			return getRuleContext(SimpleExpressionContext.class,i);
		}
		public List<SimpleExpressionContext> simpleExpression() {
			return getRuleContexts(SimpleExpressionContext.class);
		}
		public TerminalNode OPERAND_STUB() { return getToken(SimpleExpressionParser.OPERAND_STUB, 0); }
		public TerminalNode IDENT() { return getToken(SimpleExpressionParser.IDENT, 0); }
		public IndexedVarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexedVar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleExpressionListener ) ((SimpleExpressionListener)listener).enterIndexedVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleExpressionListener ) ((SimpleExpressionListener)listener).exitIndexedVar(this);
		}
	}

	public final IndexedVarContext indexedVar() throws RecognitionException {
		IndexedVarContext _localctx = new IndexedVarContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_indexedVar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			switch (_input.LA(1)) {
			case IDENT:
				{
				setState(89); ((IndexedVarContext)_localctx).ident = match(IDENT);
				}
				break;
			case OPERAND_STUB:
				{
				setState(90); ((IndexedVarContext)_localctx).opStub = match(OPERAND_STUB);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(93); match(T__2);
			setState(94); ((IndexedVarContext)_localctx).expr = simpleExpression();

			        if(((IndexedVarContext)_localctx).ident!=null){((IndexedVarContext)_localctx).varName =  (((IndexedVarContext)_localctx).ident!=null?((IndexedVarContext)_localctx).ident.getText():null);}
			        if(((IndexedVarContext)_localctx).opStub!=null){((IndexedVarContext)_localctx).varName =  (((IndexedVarContext)_localctx).opStub!=null?((IndexedVarContext)_localctx).opStub.getText():null);}
			        ((IndexedVarContext)_localctx).indVarType =  new FactorType.Var.Indexed();
			        _localctx.indVarType.setName(_localctx.varName);
			        _localctx.indVarType.setRbracket(new FactorType.Var.Indexed.Rbracket());
			        _localctx.indVarType.setLbracket(new FactorType.Var.Indexed.Lbracket());
			        _localctx.indVarType.setSimpleExpr(((IndexedVarContext)_localctx).expr.simpleExprType);
			    
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(96); match(T__1);
				setState(97); ((IndexedVarContext)_localctx).expr1 = simpleExpression();

				        ((IndexedVarContext)_localctx).commaExprType =  new FactorType.Var.Indexed.CommaExpr();
				        _localctx.commaExprType.setComma(new FactorType.Var.Indexed.CommaExpr.Comma());
				        _localctx.commaExprType.setSimpleExpr(((IndexedVarContext)_localctx).expr1.simpleExprType);
				        _localctx.indVarType.getCommaExpr().add(_localctx.commaExprType);
				    
				}
				}
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(105); match(T__0);
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

	public static class NumberContext extends ParserRuleContext {
		public String num;
		public Token integ;
		public Token real;
		public Token numStub;
		public TerminalNode NUMBER_STUB() { return getToken(SimpleExpressionParser.NUMBER_STUB, 0); }
		public TerminalNode REAL() { return getToken(SimpleExpressionParser.REAL, 0); }
		public TerminalNode INTEGER() { return getToken(SimpleExpressionParser.INTEGER, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleExpressionListener ) ((SimpleExpressionListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleExpressionListener ) ((SimpleExpressionListener)listener).exitNumber(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_number);
		try {
			setState(113);
			switch (_input.LA(1)) {
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(107); ((NumberContext)_localctx).integ = match(INTEGER);
				((NumberContext)_localctx).num = (((NumberContext)_localctx).integ!=null?((NumberContext)_localctx).integ.getText():null);
				}
				break;
			case REAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(109); ((NumberContext)_localctx).real = match(REAL);
				((NumberContext)_localctx).num = (((NumberContext)_localctx).real!=null?((NumberContext)_localctx).real.getText():null);
				}
				break;
			case NUMBER_STUB:
				enterOuterAlt(_localctx, 3);
				{
				setState(111); ((NumberContext)_localctx).numStub = match(NUMBER_STUB);
				((NumberContext)_localctx).num = (((NumberContext)_localctx).numStub!=null?((NumberContext)_localctx).numStub.getText():null);
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

	public static class StringContext extends ParserRuleContext {
		public String str;
		public Token STRING_LITERAL;
		public TerminalNode STRING_LITERAL() { return getToken(SimpleExpressionParser.STRING_LITERAL, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleExpressionListener ) ((SimpleExpressionListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleExpressionListener ) ((SimpleExpressionListener)listener).exitString(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115); ((StringContext)_localctx).STRING_LITERAL = match(STRING_LITERAL);
			((StringContext)_localctx).str = (((StringContext)_localctx).STRING_LITERAL!=null?((StringContext)_localctx).STRING_LITERAL.getText():null);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\30y\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\3\2\3\2\3\2\3\2\3\2\3\2\7\2\37\n\2\f\2\16\2\"\13\2\3\3\3\3\3\4"+
		"\3\4\3\5\3\5\3\5\3\5\3\5\3\5\7\5.\n\5\f\5\16\5\61\13\5\3\6\3\6\5\6\65"+
		"\n\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\5\7L\n\7\3\b\3\b\3\b\3\b\3\b\3\b\5\bT\n\b\3\t\3\t\3"+
		"\t\3\t\5\tZ\n\t\3\n\3\n\5\n^\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\ng\n\n"+
		"\f\n\16\nj\13\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\5\13t\n\13\3\f\3"+
		"\f\3\f\3\f\2\2\r\2\4\6\b\n\f\16\20\22\24\26\2\4\3\2\t\13\3\2\f\20{\2\30"+
		"\3\2\2\2\4#\3\2\2\2\6%\3\2\2\2\b\'\3\2\2\2\n\64\3\2\2\2\fK\3\2\2\2\16"+
		"S\3\2\2\2\20Y\3\2\2\2\22]\3\2\2\2\24s\3\2\2\2\26u\3\2\2\2\30\31\5\b\5"+
		"\2\31 \b\2\1\2\32\33\5\4\3\2\33\34\5\b\5\2\34\35\b\2\1\2\35\37\3\2\2\2"+
		"\36\32\3\2\2\2\37\"\3\2\2\2 \36\3\2\2\2 !\3\2\2\2!\3\3\2\2\2\" \3\2\2"+
		"\2#$\t\2\2\2$\5\3\2\2\2%&\t\3\2\2&\7\3\2\2\2\'(\5\n\6\2(/\b\5\1\2)*\5"+
		"\6\4\2*+\5\n\6\2+,\b\5\1\2,.\3\2\2\2-)\3\2\2\2.\61\3\2\2\2/-\3\2\2\2/"+
		"\60\3\2\2\2\60\t\3\2\2\2\61/\3\2\2\2\62\65\7\t\2\2\63\65\7\n\2\2\64\62"+
		"\3\2\2\2\64\63\3\2\2\2\64\65\3\2\2\2\65\66\3\2\2\2\66\67\5\f\7\2\678\b"+
		"\6\1\28\13\3\2\2\29:\5\16\b\2:;\b\7\1\2;L\3\2\2\2<=\5\24\13\2=>\b\7\1"+
		"\2>L\3\2\2\2?@\5\26\f\2@A\b\7\1\2AL\3\2\2\2BC\7\21\2\2CD\5\f\7\2DE\b\7"+
		"\1\2EL\3\2\2\2FG\7\3\2\2GH\5\2\2\2HI\7\4\2\2IJ\b\7\1\2JL\3\2\2\2K9\3\2"+
		"\2\2K<\3\2\2\2K?\3\2\2\2KB\3\2\2\2KF\3\2\2\2L\r\3\2\2\2MN\5\20\t\2NO\b"+
		"\b\1\2OT\3\2\2\2PQ\5\22\n\2QR\b\b\1\2RT\3\2\2\2SM\3\2\2\2SP\3\2\2\2T\17"+
		"\3\2\2\2UV\7\24\2\2VZ\b\t\1\2WX\7\27\2\2XZ\b\t\1\2YU\3\2\2\2YW\3\2\2\2"+
		"Z\21\3\2\2\2[^\7\24\2\2\\^\7\27\2\2][\3\2\2\2]\\\3\2\2\2^_\3\2\2\2_`\7"+
		"\5\2\2`a\5\2\2\2ah\b\n\1\2bc\7\6\2\2cd\5\2\2\2de\b\n\1\2eg\3\2\2\2fb\3"+
		"\2\2\2gj\3\2\2\2hf\3\2\2\2hi\3\2\2\2ik\3\2\2\2jh\3\2\2\2kl\7\7\2\2l\23"+
		"\3\2\2\2mn\7\26\2\2nt\b\13\1\2op\7\22\2\2pt\b\13\1\2qr\7\30\2\2rt\b\13"+
		"\1\2sm\3\2\2\2so\3\2\2\2sq\3\2\2\2t\25\3\2\2\2uv\7\25\2\2vw\b\f\1\2w\27"+
		"\3\2\2\2\13 /\64KSY]hs";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}