// Generated from ComplexityFunctions.g4 by ANTLR 4.4

package complexity;
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
public class ComplexityFunctionsParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__2=1, T__1=2, T__0=3, LOG=4, SQRT=5, LPAREN=6, RPAREN=7, TIMES=8, POW=9, 
		LETTER=10, DIGIT=11, WS=12;
	public static final String[] tokenNames = {
		"<INVALID>", "'/'", "'!'", "'const'", "'log'", "'Sqrt'", "'('", "')'", 
		"'*'", "'^'", "LETTER", "DIGIT", "WS"
	};
	public static final int
		RULE_complexity = 0, RULE_complExpression = 1, RULE_expression = 2, RULE_multiplyingExpression = 3, 
		RULE_powExpression = 4, RULE_atom = 5, RULE_log = 6, RULE_sqrt = 7, RULE_subFunc = 8, 
		RULE_number1 = 9, RULE_variable = 10;
	public static final String[] ruleNames = {
		"complexity", "complExpression", "expression", "multiplyingExpression", 
		"powExpression", "atom", "log", "sqrt", "subFunc", "number1", "variable"
	};

	@Override
	public String getGrammarFileName() { return "ComplexityFunctions.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ComplexityFunctionsParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ComplexityContext extends ParserRuleContext {
		public Complexity compl;
		public ComplExpressionContext func;
		public ComplExpressionContext complExpression() {
			return getRuleContext(ComplExpressionContext.class,0);
		}
		public ComplexityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_complexity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexityFunctionsListener ) ((ComplexityFunctionsListener)listener).enterComplexity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexityFunctionsListener ) ((ComplexityFunctionsListener)listener).exitComplexity(this);
		}
	}

	public final ComplexityContext complexity() throws RecognitionException {
		ComplexityContext _localctx = new ComplexityContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_complexity);
		try {
			setState(27);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(22); match(T__0);

				        ((ComplexityContext)_localctx).compl =  new Complexity(new ConstNode());
				    
				}
				break;
			case LOG:
			case SQRT:
			case LPAREN:
			case LETTER:
			case DIGIT:
				enterOuterAlt(_localctx, 2);
				{
				setState(24); ((ComplexityContext)_localctx).func = complExpression();

				        ((ComplexityContext)_localctx).compl =  new Complexity(((ComplexityContext)_localctx).func.compl);
				    
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

	public static class ComplExpressionContext extends ParserRuleContext {
		public ComplexityNode compl;
		public ExpressionContext expr;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ComplExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_complExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexityFunctionsListener ) ((ComplexityFunctionsListener)listener).enterComplExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexityFunctionsListener ) ((ComplexityFunctionsListener)listener).exitComplExpression(this);
		}
	}

	public final ComplExpressionContext complExpression() throws RecognitionException {
		ComplExpressionContext _localctx = new ComplExpressionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_complExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29); ((ComplExpressionContext)_localctx).expr = expression();

			        ((ComplExpressionContext)_localctx).compl =  ((ComplExpressionContext)_localctx).expr.compl;
			    
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
		public ComplexityNode compl;
		public MultiplyingExpressionContext expr1;
		public MultiplyingExpressionContext multiplyingExpression() {
			return getRuleContext(MultiplyingExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexityFunctionsListener ) ((ComplexityFunctionsListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexityFunctionsListener ) ((ComplexityFunctionsListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32); ((ExpressionContext)_localctx).expr1 = multiplyingExpression();

			        ((ExpressionContext)_localctx).compl =  ((ExpressionContext)_localctx).expr1.compl;
			    
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

	public static class MultiplyingExpressionContext extends ParserRuleContext {
		public ComplexityNode compl;
		public MulNode m;
		public GroupNode left;
		public GroupNode right;
		public PowExpressionContext expr1;
		public Token mul;
		public PowExpressionContext expr2;
		public TerminalNode TIMES(int i) {
			return getToken(ComplexityFunctionsParser.TIMES, i);
		}
		public List<TerminalNode> TIMES() { return getTokens(ComplexityFunctionsParser.TIMES); }
		public List<PowExpressionContext> powExpression() {
			return getRuleContexts(PowExpressionContext.class);
		}
		public PowExpressionContext powExpression(int i) {
			return getRuleContext(PowExpressionContext.class,i);
		}
		public MultiplyingExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplyingExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexityFunctionsListener ) ((ComplexityFunctionsListener)listener).enterMultiplyingExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexityFunctionsListener ) ((ComplexityFunctionsListener)listener).exitMultiplyingExpression(this);
		}
	}

	public final MultiplyingExpressionContext multiplyingExpression() throws RecognitionException {
		MultiplyingExpressionContext _localctx = new MultiplyingExpressionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_multiplyingExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35); ((MultiplyingExpressionContext)_localctx).expr1 = powExpression();

			        ((MultiplyingExpressionContext)_localctx).compl =  ((MultiplyingExpressionContext)_localctx).expr1.compl;
			    
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TIMES) {
				{
				{
				setState(37); ((MultiplyingExpressionContext)_localctx).mul = match(TIMES);
				setState(38); ((MultiplyingExpressionContext)_localctx).expr2 = powExpression();

				       ((MultiplyingExpressionContext)_localctx).left =  new GroupNode(_localctx.compl);
				       ((MultiplyingExpressionContext)_localctx).right =  new GroupNode(((MultiplyingExpressionContext)_localctx).expr2.compl);
				       ((MultiplyingExpressionContext)_localctx).m =  new MulNode(_localctx.left, _localctx.right);
				       ((MultiplyingExpressionContext)_localctx).compl =  _localctx.m;
				    
				}
				}
				setState(45);
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

	public static class PowExpressionContext extends ParserRuleContext {
		public ComplexityNode compl;
		public AtomContext a;
		public Token pw;
		public AtomContext b;
		public AtomContext atom(int i) {
			return getRuleContext(AtomContext.class,i);
		}
		public List<AtomContext> atom() {
			return getRuleContexts(AtomContext.class);
		}
		public TerminalNode POW() { return getToken(ComplexityFunctionsParser.POW, 0); }
		public PowExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_powExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexityFunctionsListener ) ((ComplexityFunctionsListener)listener).enterPowExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexityFunctionsListener ) ((ComplexityFunctionsListener)listener).exitPowExpression(this);
		}
	}

	public final PowExpressionContext powExpression() throws RecognitionException {
		PowExpressionContext _localctx = new PowExpressionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_powExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46); ((PowExpressionContext)_localctx).a = atom();
			setState(49);
			_la = _input.LA(1);
			if (_la==POW) {
				{
				setState(47); ((PowExpressionContext)_localctx).pw = match(POW);
				setState(48); ((PowExpressionContext)_localctx).b = atom();
				}
			}


			        //no exponents: left atom can be single var or subfunction
			        if(((PowExpressionContext)_localctx).pw==null){
			            if(((PowExpressionContext)_localctx).a.var!=null){
			                ((PowExpressionContext)_localctx).compl =  new VarNode((((PowExpressionContext)_localctx).a!=null?_input.getText(((PowExpressionContext)_localctx).a.start,((PowExpressionContext)_localctx).a.stop):null));
			            }
			            else if(((PowExpressionContext)_localctx).a.compl!=null){
			                ((PowExpressionContext)_localctx).compl =  ((PowExpressionContext)_localctx).a.compl;
			            }else{
			                ((PowExpressionContext)_localctx).compl =  new NoneNode();
			            }
			        }else{
			            // exponents
			            // valid cases: number^var, var^number and var^(number/number)
			            //              number^subfunc, subfunc^number and subfunc^(number/number)
			            if(((PowExpressionContext)_localctx).a.number!=0 && ((PowExpressionContext)_localctx).b.var!=null){//number^var
			                VarNode var = new VarNode(((PowExpressionContext)_localctx).b.var);
			                NumNode num = new NumNode(((PowExpressionContext)_localctx).a.number);
			                ExpNode exp = new ExpNode(num, var);
			                ((PowExpressionContext)_localctx).compl =  exp;
			            }else if(((PowExpressionContext)_localctx).a.var!=null && ((PowExpressionContext)_localctx).b.number!=0){//var^number
			                VarNode var = new VarNode(((PowExpressionContext)_localctx).a.var);
			                NumNode num = new NumNode(((PowExpressionContext)_localctx).b.number);
			                ExpNode exp = new ExpNode(var, num);
			                ((PowExpressionContext)_localctx).compl =  exp;
			            }else if(((PowExpressionContext)_localctx).a.var!=null && ((PowExpressionContext)_localctx).b.fracNumber!=null){//var^(number/number)
			                VarNode var = new VarNode(((PowExpressionContext)_localctx).a.var);
			                VarNode frac = new VarNode(((PowExpressionContext)_localctx).b.fracNumber);
			                ExpNode exp = new ExpNode(var, frac);
			                ((PowExpressionContext)_localctx).compl =  exp;
			            }else if(((PowExpressionContext)_localctx).a.number!=0 && ((PowExpressionContext)_localctx).b.compl!=null){//number^subfunc
			                NumNode num = new NumNode(((PowExpressionContext)_localctx).a.number);
			                GroupNode g = new GroupNode(((PowExpressionContext)_localctx).b.compl);
			                ExpNode exp = new ExpNode(num, g);
			                ((PowExpressionContext)_localctx).compl =  exp;
			            }else if(((PowExpressionContext)_localctx).a.compl!=null && ((PowExpressionContext)_localctx).b.number!=0){//subfunc^number
			                NumNode num = new NumNode(((PowExpressionContext)_localctx).b.number);
			                GroupNode g = new GroupNode(((PowExpressionContext)_localctx).a.compl);
			                ExpNode exp = new ExpNode(g, num);
			                ((PowExpressionContext)_localctx).compl =  exp;
			            }else if(((PowExpressionContext)_localctx).a.compl!=null && ((PowExpressionContext)_localctx).b.fracNumber!=null){//compl^(number/number)
			                GroupNode g = new GroupNode(((PowExpressionContext)_localctx).a.compl);
			                VarNode var = new VarNode(((PowExpressionContext)_localctx).b.fracNumber);
			                ExpNode exp = new ExpNode(g, var);
			                ((PowExpressionContext)_localctx).compl =  exp;
			            }else{
			                ((PowExpressionContext)_localctx).compl =  new NoneNode();
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

	public static class AtomContext extends ParserRuleContext {
		public String var;
		public int number;
		public String fracNumber;
		public ComplexityNode compl;
		public VariableContext var1;
		public Token fact1;
		public Number1Context num1;
		public Number1Context num2;
		public Number1Context num;
		public SubFuncContext subfunc;
		public Token fact2;
		public LogContext logfunc;
		public Token fact3;
		public SqrtContext sqrtfunc;
		public SqrtContext sqrt() {
			return getRuleContext(SqrtContext.class,0);
		}
		public LogContext log() {
			return getRuleContext(LogContext.class,0);
		}
		public SubFuncContext subFunc() {
			return getRuleContext(SubFuncContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public List<Number1Context> number1() {
			return getRuleContexts(Number1Context.class);
		}
		public Number1Context number1(int i) {
			return getRuleContext(Number1Context.class,i);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexityFunctionsListener ) ((ComplexityFunctionsListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexityFunctionsListener ) ((ComplexityFunctionsListener)listener).exitAtom(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_atom);
		int _la;
		try {
			setState(87);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(53); ((AtomContext)_localctx).var1 = variable();
				setState(55);
				_la = _input.LA(1);
				if (_la==T__1) {
					{
					setState(54); ((AtomContext)_localctx).fact1 = match(T__1);
					}
				}


				        if(((AtomContext)_localctx).fact1!=null){
				            VarNode v = new VarNode((((AtomContext)_localctx).var1!=null?_input.getText(((AtomContext)_localctx).var1.start,((AtomContext)_localctx).var1.stop):null));
				            FactNode fact = new FactNode(v);
				            ((AtomContext)_localctx).compl =  fact;
				        }else{
				            ((AtomContext)_localctx).var =  (((AtomContext)_localctx).var1!=null?_input.getText(((AtomContext)_localctx).var1.start,((AtomContext)_localctx).var1.stop):null);
				        }
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(59); match(LPAREN);
				setState(60); ((AtomContext)_localctx).num1 = number1();
				setState(61); match(T__2);
				setState(62); ((AtomContext)_localctx).num2 = number1();
				setState(63); match(RPAREN);

				        ((AtomContext)_localctx).fracNumber =  "("+(((AtomContext)_localctx).num1!=null?_input.getText(((AtomContext)_localctx).num1.start,((AtomContext)_localctx).num1.stop):null)+"/"+(((AtomContext)_localctx).num2!=null?_input.getText(((AtomContext)_localctx).num2.start,((AtomContext)_localctx).num2.stop):null)+")";
				    
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(66); ((AtomContext)_localctx).num = number1();
				((AtomContext)_localctx).number =  Integer.parseInt((((AtomContext)_localctx).num!=null?_input.getText(((AtomContext)_localctx).num.start,((AtomContext)_localctx).num.stop):null));
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(69); ((AtomContext)_localctx).subfunc = subFunc();
				setState(71);
				_la = _input.LA(1);
				if (_la==T__1) {
					{
					setState(70); ((AtomContext)_localctx).fact2 = match(T__1);
					}
				}


				        if(((AtomContext)_localctx).fact2!=null){
				            GroupNode g = new GroupNode(((AtomContext)_localctx).subfunc.compl);
				            FactNode fact = new FactNode(g);
				            ((AtomContext)_localctx).compl =  fact;
				        }else{
				            ((AtomContext)_localctx).compl =  ((AtomContext)_localctx).subfunc.compl;
				        }
				    
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(75); ((AtomContext)_localctx).logfunc = log();
				setState(77);
				_la = _input.LA(1);
				if (_la==T__1) {
					{
					setState(76); ((AtomContext)_localctx).fact3 = match(T__1);
					}
				}


				        if(((AtomContext)_localctx).fact3!=null){
				            GroupNode g = new GroupNode(((AtomContext)_localctx).logfunc.logCompl);
				            FactNode fact = new FactNode(g);
				            ((AtomContext)_localctx).compl =  fact;
				        }else{
				            ((AtomContext)_localctx).compl =  ((AtomContext)_localctx).logfunc.logCompl;
				        }
				    
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(81); ((AtomContext)_localctx).sqrtfunc = sqrt();
				setState(83);
				_la = _input.LA(1);
				if (_la==T__1) {
					{
					setState(82); ((AtomContext)_localctx).fact3 = match(T__1);
					}
				}


				        if(((AtomContext)_localctx).fact3!=null){
				            GroupNode g = new GroupNode(((AtomContext)_localctx).sqrtfunc.sqrtCompl);
				            FactNode fact = new FactNode(g);
				            ((AtomContext)_localctx).compl =  fact;
				        }else{
				            ((AtomContext)_localctx).compl =  ((AtomContext)_localctx).sqrtfunc.sqrtCompl;
				        }
				    
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

	public static class LogContext extends ParserRuleContext {
		public ComplexityNode logCompl;
		public Number1Context num;
		public SubFuncContext subfunc;
		public TerminalNode LOG() { return getToken(ComplexityFunctionsParser.LOG, 0); }
		public SubFuncContext subFunc() {
			return getRuleContext(SubFuncContext.class,0);
		}
		public Number1Context number1() {
			return getRuleContext(Number1Context.class,0);
		}
		public LogContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_log; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexityFunctionsListener ) ((ComplexityFunctionsListener)listener).enterLog(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexityFunctionsListener ) ((ComplexityFunctionsListener)listener).exitLog(this);
		}
	}

	public final LogContext log() throws RecognitionException {
		LogContext _localctx = new LogContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_log);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89); match(LOG);
			setState(91);
			_la = _input.LA(1);
			if (_la==DIGIT) {
				{
				setState(90); ((LogContext)_localctx).num = number1();
				}
			}

			setState(93); ((LogContext)_localctx).subfunc = subFunc();

			        GroupNode g = new GroupNode(((LogContext)_localctx).subfunc.compl);
			        ((LogContext)_localctx).logCompl =  new LogNode(g);
			    
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

	public static class SqrtContext extends ParserRuleContext {
		public ComplexityNode sqrtCompl;
		public SubFuncContext subfunc;
		public TerminalNode SQRT() { return getToken(ComplexityFunctionsParser.SQRT, 0); }
		public SubFuncContext subFunc() {
			return getRuleContext(SubFuncContext.class,0);
		}
		public SqrtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sqrt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexityFunctionsListener ) ((ComplexityFunctionsListener)listener).enterSqrt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexityFunctionsListener ) ((ComplexityFunctionsListener)listener).exitSqrt(this);
		}
	}

	public final SqrtContext sqrt() throws RecognitionException {
		SqrtContext _localctx = new SqrtContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_sqrt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96); match(SQRT);
			setState(97); ((SqrtContext)_localctx).subfunc = subFunc();

			        GroupNode g = new GroupNode(((SqrtContext)_localctx).subfunc.compl);
			        ((SqrtContext)_localctx).sqrtCompl =  new SqrtNode(g);
			    
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

	public static class SubFuncContext extends ParserRuleContext {
		public ComplexityNode compl;
		public ComplExpressionContext subfunc;
		public TerminalNode LPAREN() { return getToken(ComplexityFunctionsParser.LPAREN, 0); }
		public ComplExpressionContext complExpression() {
			return getRuleContext(ComplExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ComplexityFunctionsParser.RPAREN, 0); }
		public SubFuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subFunc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexityFunctionsListener ) ((ComplexityFunctionsListener)listener).enterSubFunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexityFunctionsListener ) ((ComplexityFunctionsListener)listener).exitSubFunc(this);
		}
	}

	public final SubFuncContext subFunc() throws RecognitionException {
		SubFuncContext _localctx = new SubFuncContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_subFunc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100); match(LPAREN);
			setState(101); ((SubFuncContext)_localctx).subfunc = complExpression();
			setState(102); match(RPAREN);

			        ((SubFuncContext)_localctx).compl =  ((SubFuncContext)_localctx).subfunc.compl;
			    
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

	public static class Number1Context extends ParserRuleContext {
		public TerminalNode DIGIT(int i) {
			return getToken(ComplexityFunctionsParser.DIGIT, i);
		}
		public List<TerminalNode> DIGIT() { return getTokens(ComplexityFunctionsParser.DIGIT); }
		public Number1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexityFunctionsListener ) ((ComplexityFunctionsListener)listener).enterNumber1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexityFunctionsListener ) ((ComplexityFunctionsListener)listener).exitNumber1(this);
		}
	}

	public final Number1Context number1() throws RecognitionException {
		Number1Context _localctx = new Number1Context(_ctx, getState());
		enterRule(_localctx, 18, RULE_number1);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(105); match(DIGIT);
				}
				}
				setState(108); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==DIGIT );
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
		public List<TerminalNode> LETTER() { return getTokens(ComplexityFunctionsParser.LETTER); }
		public TerminalNode DIGIT(int i) {
			return getToken(ComplexityFunctionsParser.DIGIT, i);
		}
		public TerminalNode LETTER(int i) {
			return getToken(ComplexityFunctionsParser.LETTER, i);
		}
		public List<TerminalNode> DIGIT() { return getTokens(ComplexityFunctionsParser.DIGIT); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexityFunctionsListener ) ((ComplexityFunctionsListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ComplexityFunctionsListener ) ((ComplexityFunctionsListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110); match(LETTER);
			setState(114);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LETTER || _la==DIGIT) {
				{
				{
				setState(111);
				_la = _input.LA(1);
				if ( !(_la==LETTER || _la==DIGIT) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				}
				setState(116);
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\16x\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\3\2\3\2\3\2\3\2\3\2\5\2\36\n\2\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\7\5,\n\5\f\5\16\5/\13\5\3\6\3\6\3\6\5\6\64\n\6\3\6\3"+
		"\6\3\7\3\7\5\7:\n\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\5\7J\n\7\3\7\3\7\3\7\3\7\5\7P\n\7\3\7\3\7\3\7\3\7\5\7V\n\7\3\7"+
		"\3\7\5\7Z\n\7\3\b\3\b\5\b^\n\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n"+
		"\3\n\3\n\3\13\6\13m\n\13\r\13\16\13n\3\f\3\f\7\fs\n\f\f\f\16\fv\13\f\3"+
		"\f\2\2\r\2\4\6\b\n\f\16\20\22\24\26\2\3\3\2\f\r{\2\35\3\2\2\2\4\37\3\2"+
		"\2\2\6\"\3\2\2\2\b%\3\2\2\2\n\60\3\2\2\2\fY\3\2\2\2\16[\3\2\2\2\20b\3"+
		"\2\2\2\22f\3\2\2\2\24l\3\2\2\2\26p\3\2\2\2\30\31\7\5\2\2\31\36\b\2\1\2"+
		"\32\33\5\4\3\2\33\34\b\2\1\2\34\36\3\2\2\2\35\30\3\2\2\2\35\32\3\2\2\2"+
		"\36\3\3\2\2\2\37 \5\6\4\2 !\b\3\1\2!\5\3\2\2\2\"#\5\b\5\2#$\b\4\1\2$\7"+
		"\3\2\2\2%&\5\n\6\2&-\b\5\1\2\'(\7\n\2\2()\5\n\6\2)*\b\5\1\2*,\3\2\2\2"+
		"+\'\3\2\2\2,/\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\t\3\2\2\2/-\3\2\2\2\60\63\5"+
		"\f\7\2\61\62\7\13\2\2\62\64\5\f\7\2\63\61\3\2\2\2\63\64\3\2\2\2\64\65"+
		"\3\2\2\2\65\66\b\6\1\2\66\13\3\2\2\2\679\5\26\f\28:\7\4\2\298\3\2\2\2"+
		"9:\3\2\2\2:;\3\2\2\2;<\b\7\1\2<Z\3\2\2\2=>\7\b\2\2>?\5\24\13\2?@\7\3\2"+
		"\2@A\5\24\13\2AB\7\t\2\2BC\b\7\1\2CZ\3\2\2\2DE\5\24\13\2EF\b\7\1\2FZ\3"+
		"\2\2\2GI\5\22\n\2HJ\7\4\2\2IH\3\2\2\2IJ\3\2\2\2JK\3\2\2\2KL\b\7\1\2LZ"+
		"\3\2\2\2MO\5\16\b\2NP\7\4\2\2ON\3\2\2\2OP\3\2\2\2PQ\3\2\2\2QR\b\7\1\2"+
		"RZ\3\2\2\2SU\5\20\t\2TV\7\4\2\2UT\3\2\2\2UV\3\2\2\2VW\3\2\2\2WX\b\7\1"+
		"\2XZ\3\2\2\2Y\67\3\2\2\2Y=\3\2\2\2YD\3\2\2\2YG\3\2\2\2YM\3\2\2\2YS\3\2"+
		"\2\2Z\r\3\2\2\2[]\7\6\2\2\\^\5\24\13\2]\\\3\2\2\2]^\3\2\2\2^_\3\2\2\2"+
		"_`\5\22\n\2`a\b\b\1\2a\17\3\2\2\2bc\7\7\2\2cd\5\22\n\2de\b\t\1\2e\21\3"+
		"\2\2\2fg\7\b\2\2gh\5\4\3\2hi\7\t\2\2ij\b\n\1\2j\23\3\2\2\2km\7\r\2\2l"+
		"k\3\2\2\2mn\3\2\2\2nl\3\2\2\2no\3\2\2\2o\25\3\2\2\2pt\7\f\2\2qs\t\2\2"+
		"\2rq\3\2\2\2sv\3\2\2\2tr\3\2\2\2tu\3\2\2\2u\27\3\2\2\2vt\3\2\2\2\r\35"+
		"-\639IOUY]nt";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}