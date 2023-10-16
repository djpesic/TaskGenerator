// Generated from MultiEquationSystem.g4 by ANTLR 4.4

package unif;
import java.util.*;
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
public class MultiEquationSystemParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__8=1, T__7=2, T__6=3, T__5=4, T__4=5, T__3=6, T__2=7, T__1=8, T__0=9, 
		WS=10, REAL=11, SCALE=12, IDENT=13, INTEGER=14;
	public static final String[] tokenNames = {
		"<INVALID>", "'('", "')'", "'{'", "';'", "'<'", "','", "'}'", "'='", "'>'", 
		"WS", "REAL", "SCALE", "IDENT", "INTEGER"
	};
	public static final int
		RULE_multiEquationSystem = 0, RULE_multiEquation = 1, RULE_vars = 2, RULE_multiTerm = 3, 
		RULE_multiTermArgs = 4, RULE_multiTermArg = 5, RULE_variable = 6, RULE_number = 7;
	public static final String[] ruleNames = {
		"multiEquationSystem", "multiEquation", "vars", "multiTerm", "multiTermArgs", 
		"multiTermArg", "variable", "number"
	};

	@Override
	public String getGrammarFileName() { return "MultiEquationSystem.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MultiEquationSystemParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class MultiEquationSystemContext extends ParserRuleContext {
		public MultiEqSystem multiEqSystem;
		public MultiEquationContext multeq;
		public List<MultiEquationContext> multiEquation() {
			return getRuleContexts(MultiEquationContext.class);
		}
		public MultiEquationContext multiEquation(int i) {
			return getRuleContext(MultiEquationContext.class,i);
		}
		public MultiEquationSystemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiEquationSystem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MultiEquationSystemListener ) ((MultiEquationSystemListener)listener).enterMultiEquationSystem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MultiEquationSystemListener ) ((MultiEquationSystemListener)listener).exitMultiEquationSystem(this);
		}
	}

	public final MultiEquationSystemContext multiEquationSystem() throws RecognitionException {
		MultiEquationSystemContext _localctx = new MultiEquationSystemContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_multiEquationSystem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{

			        ((MultiEquationSystemContext)_localctx).multiEqSystem =  new MultiEqSystem();
			        _localctx.multiEqSystem.u = new UPart();
			        _localctx.multiEqSystem.u.ZeroCounterMultiEq = new LinkedList<>();
			        _localctx.multiEqSystem.u.Equations = new LinkedList<>();
			        _localctx.multiEqSystem.t = new LinkedList<>();
			    
			setState(21); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(17); ((MultiEquationSystemContext)_localctx).multeq = multiEquation();

				        _localctx.multiEqSystem.u.Equations.add(((MultiEquationSystemContext)_localctx).multeq.multEq);
				    
				setState(19); match(T__5);
				}
				}
				setState(23); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__6 );
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

	public static class MultiEquationContext extends ParserRuleContext {
		public MultiEquation multEq;
		public VarsContext v;
		public MultiTermContext mTerm;
		public VarsContext vars() {
			return getRuleContext(VarsContext.class,0);
		}
		public MultiTermContext multiTerm() {
			return getRuleContext(MultiTermContext.class,0);
		}
		public MultiEquationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiEquation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MultiEquationSystemListener ) ((MultiEquationSystemListener)listener).enterMultiEquation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MultiEquationSystemListener ) ((MultiEquationSystemListener)listener).exitMultiEquation(this);
		}
	}

	public final MultiEquationContext multiEquation() throws RecognitionException {
		MultiEquationContext _localctx = new MultiEquationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_multiEquation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25); ((MultiEquationContext)_localctx).v = vars();
			setState(26); match(T__1);
			setState(27); ((MultiEquationContext)_localctx).mTerm = multiTerm();

			        ((MultiEquationContext)_localctx).multEq =  new MultiEquation();
			        _localctx.multEq.s = ((MultiEquationContext)_localctx).v.variables;
			        _localctx.multEq.m = ((MultiEquationContext)_localctx).mTerm.mTerm;
			    
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

	public static class VarsContext extends ParserRuleContext {
		public List<Variable> variables;
		public Variable var;
		public Token i1;
		public Token i2;
		public TerminalNode IDENT(int i) {
			return getToken(MultiEquationSystemParser.IDENT, i);
		}
		public List<TerminalNode> IDENT() { return getTokens(MultiEquationSystemParser.IDENT); }
		public VarsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vars; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MultiEquationSystemListener ) ((MultiEquationSystemListener)listener).enterVars(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MultiEquationSystemListener ) ((MultiEquationSystemListener)listener).exitVars(this);
		}
	}

	public final VarsContext vars() throws RecognitionException {
		VarsContext _localctx = new VarsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_vars);
		int _la;
		try {
			setState(46);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				((VarsContext)_localctx).variables =  new LinkedList<>();
				setState(31); match(T__6);
				setState(32); ((VarsContext)_localctx).i1 = match(IDENT);

				        ((VarsContext)_localctx).var =  new Variable();
				        _localctx.var.varName = (((VarsContext)_localctx).i1!=null?((VarsContext)_localctx).i1.getText():null);
				        _localctx.variables.add(_localctx.var);
				        
				    
				setState(39);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__3) {
					{
					{
					setState(34); match(T__3);
					setState(35); ((VarsContext)_localctx).i2 = match(IDENT);

					        ((VarsContext)_localctx).var =  new Variable();
					        _localctx.var.varName = (((VarsContext)_localctx).i2!=null?((VarsContext)_localctx).i2.getText():null);
					        _localctx.variables.add(_localctx.var);
					    
					}
					}
					setState(41);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(42); match(T__2);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(43); match(T__6);
				setState(44); match(T__2);
				((VarsContext)_localctx).var =  new Variable();
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

	public static class MultiTermContext extends ParserRuleContext {
		public MultiTerm mTerm;
		public VariableContext name;
		public MultiTermArgsContext args;
		public MultiTermArgsContext multiTermArgs(int i) {
			return getRuleContext(MultiTermArgsContext.class,i);
		}
		public List<MultiTermArgsContext> multiTermArgs() {
			return getRuleContexts(MultiTermArgsContext.class);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public MultiTermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiTerm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MultiEquationSystemListener ) ((MultiEquationSystemListener)listener).enterMultiTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MultiEquationSystemListener ) ((MultiEquationSystemListener)listener).exitMultiTerm(this);
		}
	}

	public final MultiTermContext multiTerm() throws RecognitionException {
		MultiTermContext _localctx = new MultiTermContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_multiTerm);
		int _la;
		try {
			setState(62);
			switch (_input.LA(1)) {
			case REAL:
			case IDENT:
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				((MultiTermContext)_localctx).mTerm =  new MultiTerm();
				setState(49); ((MultiTermContext)_localctx).name = variable();
				_localctx.mTerm.fSymb = (((MultiTermContext)_localctx).name!=null?_input.getText(((MultiTermContext)_localctx).name.start,((MultiTermContext)_localctx).name.stop):null);
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__8) {
					{
					{
					setState(51); match(T__8);
					setState(52); ((MultiTermContext)_localctx).args = multiTermArgs();
					_localctx.mTerm.args = ((MultiTermContext)_localctx).args.mTermArgs;
					setState(54); match(T__7);
					}
					}
					setState(60);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case T__5:
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
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

	public static class MultiTermArgsContext extends ParserRuleContext {
		public List<TempMultiEq> mTermArgs;
		public MultiTermArgContext arg1;
		public MultiTermArgContext arg2;
		public MultiTermArgContext multiTermArg(int i) {
			return getRuleContext(MultiTermArgContext.class,i);
		}
		public List<MultiTermArgContext> multiTermArg() {
			return getRuleContexts(MultiTermArgContext.class);
		}
		public MultiTermArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiTermArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MultiEquationSystemListener ) ((MultiEquationSystemListener)listener).enterMultiTermArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MultiEquationSystemListener ) ((MultiEquationSystemListener)listener).exitMultiTermArgs(this);
		}
	}

	public final MultiTermArgsContext multiTermArgs() throws RecognitionException {
		MultiTermArgsContext _localctx = new MultiTermArgsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_multiTermArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((MultiTermArgsContext)_localctx).mTermArgs =  new LinkedList<>();
			setState(65); ((MultiTermArgsContext)_localctx).arg1 = multiTermArg();
			_localctx.mTermArgs.add(((MultiTermArgsContext)_localctx).arg1.mTermArg);
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(67); match(T__3);
				setState(68); ((MultiTermArgsContext)_localctx).arg2 = multiTermArg();
				_localctx.mTermArgs.add(((MultiTermArgsContext)_localctx).arg2.mTermArg);
				}
				}
				setState(75);
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

	public static class MultiTermArgContext extends ParserRuleContext {
		public TempMultiEq mTermArg;
		public Variable tmpVar;
		public VarsContext v;
		public MultiTermContext mterm;
		public VariableContext v1;
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public VarsContext vars() {
			return getRuleContext(VarsContext.class,0);
		}
		public MultiTermContext multiTerm() {
			return getRuleContext(MultiTermContext.class,0);
		}
		public MultiTermArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiTermArg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MultiEquationSystemListener ) ((MultiEquationSystemListener)listener).enterMultiTermArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MultiEquationSystemListener ) ((MultiEquationSystemListener)listener).exitMultiTermArg(this);
		}
	}

	public final MultiTermArgContext multiTermArg() throws RecognitionException {
		MultiTermArgContext _localctx = new MultiTermArgContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_multiTermArg);
		try {
			setState(86);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(76); match(T__4);
				setState(77); ((MultiTermArgContext)_localctx).v = vars();
				setState(78); match(T__3);
				setState(79); ((MultiTermArgContext)_localctx).mterm = multiTerm();

				        ((MultiTermArgContext)_localctx).mTermArg =  new TempMultiEq();
				        _localctx.mTermArg.m = ((MultiTermArgContext)_localctx).mterm.mTerm;
				        _localctx.mTermArg.s = ((MultiTermArgContext)_localctx).v.variables;
				    
				setState(81); match(T__0);
				}
				break;
			case REAL:
			case IDENT:
			case INTEGER:
				enterOuterAlt(_localctx, 2);
				{
				setState(83); ((MultiTermArgContext)_localctx).v1 = variable();

				        ((MultiTermArgContext)_localctx).mTermArg =  new TempMultiEq();
				        _localctx.mTermArg.s = new LinkedList<>();
				        ((MultiTermArgContext)_localctx).tmpVar =  new Variable();
				        _localctx.tmpVar.varName = ((MultiTermArgContext)_localctx).v1.var;
				        _localctx.mTermArg.s.add(_localctx.tmpVar);
				    
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
		public String var;
		public Token i;
		public NumberContext num;
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(MultiEquationSystemParser.IDENT, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MultiEquationSystemListener ) ((MultiEquationSystemListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MultiEquationSystemListener ) ((MultiEquationSystemListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_variable);
		try {
			setState(93);
			switch (_input.LA(1)) {
			case IDENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(88); ((VariableContext)_localctx).i = match(IDENT);
				((VariableContext)_localctx).var =  (((VariableContext)_localctx).i!=null?((VariableContext)_localctx).i.getText():null);
				}
				break;
			case REAL:
			case INTEGER:
				enterOuterAlt(_localctx, 2);
				{
				setState(90); ((VariableContext)_localctx).num = number();
				((VariableContext)_localctx).var =  ((VariableContext)_localctx).num.num;
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

	public static class NumberContext extends ParserRuleContext {
		public String num;
		public Token integ;
		public Token real;
		public TerminalNode REAL() { return getToken(MultiEquationSystemParser.REAL, 0); }
		public TerminalNode INTEGER() { return getToken(MultiEquationSystemParser.INTEGER, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MultiEquationSystemListener ) ((MultiEquationSystemListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MultiEquationSystemListener ) ((MultiEquationSystemListener)listener).exitNumber(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_number);
		try {
			setState(99);
			switch (_input.LA(1)) {
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(95); ((NumberContext)_localctx).integ = match(INTEGER);
				((NumberContext)_localctx).num = (((NumberContext)_localctx).integ!=null?((NumberContext)_localctx).integ.getText():null);
				}
				break;
			case REAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(97); ((NumberContext)_localctx).real = match(REAL);
				((NumberContext)_localctx).num = (((NumberContext)_localctx).real!=null?((NumberContext)_localctx).real.getText():null);
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\20h\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3\2\3\2"+
		"\6\2\30\n\2\r\2\16\2\31\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\7\4(\n\4\f\4\16\4+\13\4\3\4\3\4\3\4\3\4\5\4\61\n\4\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\7\5;\n\5\f\5\16\5>\13\5\3\5\5\5A\n\5\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\7\6J\n\6\f\6\16\6M\13\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\5\7Y\n\7\3\b\3\b\3\b\3\b\3\b\5\b`\n\b\3\t\3\t\3\t\3\t\5\tf\n\t"+
		"\3\t\2\2\n\2\4\6\b\n\f\16\20\2\2h\2\22\3\2\2\2\4\33\3\2\2\2\6\60\3\2\2"+
		"\2\b@\3\2\2\2\nB\3\2\2\2\fX\3\2\2\2\16_\3\2\2\2\20e\3\2\2\2\22\27\b\2"+
		"\1\2\23\24\5\4\3\2\24\25\b\2\1\2\25\26\7\6\2\2\26\30\3\2\2\2\27\23\3\2"+
		"\2\2\30\31\3\2\2\2\31\27\3\2\2\2\31\32\3\2\2\2\32\3\3\2\2\2\33\34\5\6"+
		"\4\2\34\35\7\n\2\2\35\36\5\b\5\2\36\37\b\3\1\2\37\5\3\2\2\2 !\b\4\1\2"+
		"!\"\7\5\2\2\"#\7\17\2\2#)\b\4\1\2$%\7\b\2\2%&\7\17\2\2&(\b\4\1\2\'$\3"+
		"\2\2\2(+\3\2\2\2)\'\3\2\2\2)*\3\2\2\2*,\3\2\2\2+)\3\2\2\2,\61\7\t\2\2"+
		"-.\7\5\2\2./\7\t\2\2/\61\b\4\1\2\60 \3\2\2\2\60-\3\2\2\2\61\7\3\2\2\2"+
		"\62\63\b\5\1\2\63\64\5\16\b\2\64<\b\5\1\2\65\66\7\3\2\2\66\67\5\n\6\2"+
		"\678\b\5\1\289\7\4\2\29;\3\2\2\2:\65\3\2\2\2;>\3\2\2\2<:\3\2\2\2<=\3\2"+
		"\2\2=A\3\2\2\2><\3\2\2\2?A\3\2\2\2@\62\3\2\2\2@?\3\2\2\2A\t\3\2\2\2BC"+
		"\b\6\1\2CD\5\f\7\2DK\b\6\1\2EF\7\b\2\2FG\5\f\7\2GH\b\6\1\2HJ\3\2\2\2I"+
		"E\3\2\2\2JM\3\2\2\2KI\3\2\2\2KL\3\2\2\2L\13\3\2\2\2MK\3\2\2\2NO\7\7\2"+
		"\2OP\5\6\4\2PQ\7\b\2\2QR\5\b\5\2RS\b\7\1\2ST\7\13\2\2TY\3\2\2\2UV\5\16"+
		"\b\2VW\b\7\1\2WY\3\2\2\2XN\3\2\2\2XU\3\2\2\2Y\r\3\2\2\2Z[\7\17\2\2[`\b"+
		"\b\1\2\\]\5\20\t\2]^\b\b\1\2^`\3\2\2\2_Z\3\2\2\2_\\\3\2\2\2`\17\3\2\2"+
		"\2ab\7\20\2\2bf\b\t\1\2cd\7\r\2\2df\b\t\1\2ea\3\2\2\2ec\3\2\2\2f\21\3"+
		"\2\2\2\13\31)\60<@KX_e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}