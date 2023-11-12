// Generated from Linear.g4 by ANTLR 4.4

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
public class LinearParser extends Parser {
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
		RULE_formula = 0, RULE_segmentSequence1 = 1, RULE_exprSequence = 2, RULE_expression = 3, 
		RULE_leftSide = 4, RULE_rightSide = 5, RULE_argList = 6, RULE_arg = 7, 
		RULE_whileSegment = 8, RULE_leftVar = 9, RULE_rightVar = 10, RULE_variable = 11;
	public static final String[] ruleNames = {
		"formula", "segmentSequence1", "exprSequence", "expression", "leftSide", 
		"rightSide", "argList", "arg", "whileSegment", "leftVar", "rightVar", 
		"variable"
	};

	@Override
	public String getGrammarFileName() { return "Linear.g4"; }

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

	public LinearParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FormulaContext extends ParserRuleContext {
		public Complexity resultComplexity;
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
			if ( listener instanceof LinearListener ) ((LinearListener)listener).enterFormula(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LinearListener ) ((LinearListener)listener).exitFormula(this);
		}
	}

	public final FormulaContext formula() throws RecognitionException {
		FormulaContext _localctx = new FormulaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_formula);
		try {
			enterOuterAlt(_localctx, 1);
			{

			        //System.out.println("Starting linear sequencing rule");
			    
			setState(25); ((FormulaContext)_localctx).s = segmentSequence1();

			        if(((FormulaContext)_localctx).s.valid){
			            ((FormulaContext)_localctx).resultComplexity =  Complexity.createLinear("n");
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
		public Set<String> vars;
		public int numA;
		public int numB;
		public Pair<String,String> exprA;
		public Pair<String,String> exprB;
		public Unification unification;
		public StringBuilder formulas;
		public Pair<String,String> solK;
		public Pair<String,String> solL;
		public int numK;
		public int numL;
		public Pair<String,String> exprK;
		public Pair<String,String> exprL;
		public Pair<String,String> exprKInit;
		public Pair<String,String> exprLInit;
		public int numKInit;
		public int numLInit;
		public ExprSequenceContext ex;
		public WhileSegmentContext wh;
		public ExprSequenceContext exprSequence() {
			return getRuleContext(ExprSequenceContext.class,0);
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
			if ( listener instanceof LinearListener ) ((LinearListener)listener).enterSegmentSequence1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LinearListener ) ((LinearListener)listener).exitSegmentSequence1(this);
		}
	}

	public final SegmentSequence1Context segmentSequence1() throws RecognitionException {
		SegmentSequence1Context _localctx = new SegmentSequence1Context(_ctx, getState());
		enterRule(_localctx, 2, RULE_segmentSequence1);
		try {
			enterOuterAlt(_localctx, 1);
			{

			        ((SegmentSequence1Context)_localctx).valid =  true;
			    
			setState(29); ((SegmentSequence1Context)_localctx).ex = exprSequence();
			setState(30); ((SegmentSequence1Context)_localctx).wh = whileSegment();

			        // count a=... and b=...
			        for(Pair<String,String> e : ((SegmentSequence1Context)_localctx).wh.exprs){
			            if(e.getValue0().equals(((SegmentSequence1Context)_localctx).wh.a)){
			                ((SegmentSequence1Context)_localctx).exprA =  e;
			                _localctx.numA++;
			            }else if(e.getValue0().equals(((SegmentSequence1Context)_localctx).wh.b)){
			                ((SegmentSequence1Context)_localctx).exprB =  e;
			                _localctx.numB++;
			            }
			        }
			        
			        if(_localctx.numA==1 && _localctx.numB==1){
			            try{
			                //a=k*k
			                ((SegmentSequence1Context)_localctx).unification =  new Unification();
			                ((SegmentSequence1Context)_localctx).formulas =  new StringBuilder();
			                _localctx.formulas.append("mul(k,k)");
			                _localctx.formulas.append(_localctx.exprA.getValue1());
			                ((SegmentSequence1Context)_localctx).vars =  new HashSet<>();
			                _localctx.vars.add("k");
			                _localctx.unification.startUnification(_localctx.formulas.toString(), _localctx.vars);
			                solution = parseSolution(_localctx.unification.getSolution());
			                //b=l*l
			                ((SegmentSequence1Context)_localctx).formulas =  new StringBuilder();
			                _localctx.formulas.append("mul(l,l)");
			                _localctx.formulas.append(_localctx.exprB.getValue1());
			                _localctx.vars.clear();
			                _localctx.vars.add("l");
			                _localctx.unification.startUnification(_localctx.formulas.toString(), _localctx.vars);
			                solution.addAll(parseSolution(_localctx.unification.getSolution()));

			                for(Pair<String, String> sol : solution){
			                    if(sol.getValue0().equals("k")){
			                        ((SegmentSequence1Context)_localctx).solK =  sol;
			                    }else if(sol.getValue0().equals("l")){
			                        ((SegmentSequence1Context)_localctx).solL =  sol;
			                    }
			                }

			                for(Pair<String,String> e : ((SegmentSequence1Context)_localctx).wh.exprs){
			                    if(e.getValue0().equals(_localctx.solK.getValue1())){
			                        ((SegmentSequence1Context)_localctx).exprK =  e;
			                        _localctx.numK++;
			                    }else if(e.getValue0().equals(_localctx.solL.getValue1())){
			                        ((SegmentSequence1Context)_localctx).exprL =  e;
			                        _localctx.numL++;
			                    }
			                }
			                if(_localctx.numK==1 && _localctx.numL==1){
			                    //k=k+c
			                    ((SegmentSequence1Context)_localctx).formulas =  new StringBuilder();
			                    _localctx.formulas.append("add(k,c)");
			                    _localctx.formulas.append(_localctx.exprK.getValue1());
			                    _localctx.vars.clear();
			                    _localctx.vars.add("k");
			                    _localctx.vars.add("c");
			                    _localctx.unification.startUnification(_localctx.formulas.toString(), _localctx.vars);
			                    solution = parseSolution(_localctx.unification.getSolution());
			                    //l=l-c
			                    ((SegmentSequence1Context)_localctx).formulas =  new StringBuilder();
			                    _localctx.formulas.append("sub(l,c)");
			                    _localctx.formulas.append(_localctx.exprL.getValue1());
			                    _localctx.vars.clear();
			                    _localctx.vars.add("l");
			                    _localctx.vars.add("c");
			                    _localctx.unification.startUnification(_localctx.formulas.toString(), _localctx.vars);
			                    solution.addAll(parseSolution(_localctx.unification.getSolution()));
			                    
			                    
			                    for(Pair<String, String> sol : solution){
			                        if(sol.getValue0().equals("k")){
			                            if(!sol.getValue1().equals(_localctx.exprK.getValue0())) ((SegmentSequence1Context)_localctx).valid =  false;
			                        }else if(sol.getValue0().equals("l")){
			                            if(!sol.getValue1().equals(_localctx.exprL.getValue0())) ((SegmentSequence1Context)_localctx).valid =  false;
			                        }else if(sol.getValue0().equals("c")){
			                            //c>0
			                            try{
			                                double dbl = Double.parseDouble(sol.getValue1());
			                                if(dbl<=0) ((SegmentSequence1Context)_localctx).valid =  false;
			                            }catch(NumberFormatException ex){
			                                ((SegmentSequence1Context)_localctx).valid =  false;
			                            }
			                        }
			                    }   

			                    for(Pair<String,String> e : ((SegmentSequence1Context)_localctx).ex.exprs){
			                        if(e.getValue0().equals("k")){
			                            _localctx.numKInit++;
			                            ((SegmentSequence1Context)_localctx).exprKInit = e;
			                        }else if(e.getValue0().equals("l")){
			                            _localctx.numLInit++;
			                            ((SegmentSequence1Context)_localctx).exprLInit =  e;
			                        }
			                    }
			                    if(_localctx.numKInit==1 && _localctx.numLInit==1){
			                        //l=N (init)
			                        if(!_localctx.exprLInit.getValue1().toLowerCase().equals("n")){
			                            ((SegmentSequence1Context)_localctx).valid =  false;
			                        }
			                        //k=1 (init)
			                        if(!_localctx.exprKInit.getValue1().equals("1")){
			                            ((SegmentSequence1Context)_localctx).valid =  false;
			                        }
			                    }else{
			                        ((SegmentSequence1Context)_localctx).valid =  false;
			                    }
			                    
			                }else{
			                    ((SegmentSequence1Context)_localctx).valid =  false;
			                }

			            }catch(Exception ex){
			                ex.printStackTrace();
			                ((SegmentSequence1Context)_localctx).valid =  false;
			            }
			        }else{
			            ((SegmentSequence1Context)_localctx).valid =  false;
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

	public static class ExprSequenceContext extends ParserRuleContext {
		public List<Pair<String,String>> exprs;
		public ExpressionContext e;
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
			if ( listener instanceof LinearListener ) ((LinearListener)listener).enterExprSequence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LinearListener ) ((LinearListener)listener).exitExprSequence(this);
		}
	}

	public final ExprSequenceContext exprSequence() throws RecognitionException {
		ExprSequenceContext _localctx = new ExprSequenceContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_exprSequence);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33); ((ExprSequenceContext)_localctx).e = expression();

			        ((ExprSequenceContext)_localctx).exprs =  new ArrayList<>();
			        Pair<String,String> p = new Pair(((ExprSequenceContext)_localctx).e.ls,((ExprSequenceContext)_localctx).e.rs);
			        if(((ExprSequenceContext)_localctx).e.ls != null && ((ExprSequenceContext)_localctx).e.rs !=null)
			            _localctx.exprs.add(p);
			    
			setState(40);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EXPR) {
				{
				{
				setState(35); ((ExprSequenceContext)_localctx).e2 = expression();

				        p = new Pair(((ExprSequenceContext)_localctx).e2.ls,((ExprSequenceContext)_localctx).e2.rs);
				        _localctx.exprs.add(p);
				    
				}
				}
				setState(42);
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

	public static class ExpressionContext extends ParserRuleContext {
		public String ls;
		public String rs;
		public LeftSideContext ls1;
		public RightSideContext rs1;
		public TerminalNode LPAREN() { return getToken(LinearParser.LPAREN, 0); }
		public TerminalNode COMMA() { return getToken(LinearParser.COMMA, 0); }
		public RightSideContext rightSide() {
			return getRuleContext(RightSideContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(LinearParser.RPAREN, 0); }
		public TerminalNode EXPR() { return getToken(LinearParser.EXPR, 0); }
		public LeftSideContext leftSide() {
			return getRuleContext(LeftSideContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LinearListener ) ((LinearListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LinearListener ) ((LinearListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43); match(EXPR);
			setState(44); match(LPAREN);
			setState(45); ((ExpressionContext)_localctx).ls1 = leftSide();
			((ExpressionContext)_localctx).ls =  (((ExpressionContext)_localctx).ls1!=null?_input.getText(((ExpressionContext)_localctx).ls1.start,((ExpressionContext)_localctx).ls1.stop):null);
			setState(47); match(COMMA);
			setState(48); ((ExpressionContext)_localctx).rs1 = rightSide();

			        ((ExpressionContext)_localctx).rs =  (((ExpressionContext)_localctx).rs1!=null?_input.getText(((ExpressionContext)_localctx).rs1.start,((ExpressionContext)_localctx).rs1.stop):null);
			    
			setState(50); match(RPAREN);
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
			if ( listener instanceof LinearListener ) ((LinearListener)listener).enterLeftSide(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LinearListener ) ((LinearListener)listener).exitLeftSide(this);
		}
	}

	public final LeftSideContext leftSide() throws RecognitionException {
		LeftSideContext _localctx = new LeftSideContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_leftSide);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52); variable();
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
		public TerminalNode LPAREN() { return getToken(LinearParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(LinearParser.RPAREN, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public RightSideContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rightSide; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LinearListener ) ((LinearListener)listener).enterRightSide(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LinearListener ) ((LinearListener)listener).exitRightSide(this);
		}
	}

	public final RightSideContext rightSide() throws RecognitionException {
		RightSideContext _localctx = new RightSideContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_rightSide);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54); variable();
			setState(59);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(55); match(LPAREN);
				setState(56); argList();
				setState(57); match(RPAREN);
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
		public List<TerminalNode> COMMA() { return getTokens(LinearParser.COMMA); }
		public List<ArgContext> arg() {
			return getRuleContexts(ArgContext.class);
		}
		public TerminalNode COMMA(int i) {
			return getToken(LinearParser.COMMA, i);
		}
		public ArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LinearListener ) ((LinearListener)listener).enterArgList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LinearListener ) ((LinearListener)listener).exitArgList(this);
		}
	}

	public final ArgListContext argList() throws RecognitionException {
		ArgListContext _localctx = new ArgListContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_argList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61); ((ArgListContext)_localctx).a = arg();
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(62); match(COMMA);
				setState(63); ((ArgListContext)_localctx).a1 = arg();
				}
				}
				setState(68);
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
			if ( listener instanceof LinearListener ) ((LinearListener)listener).enterArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LinearListener ) ((LinearListener)listener).exitArg(this);
		}
	}

	public final ArgContext arg() throws RecognitionException {
		ArgContext _localctx = new ArgContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_arg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69); ((ArgContext)_localctx).rs = rightSide();
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
		public String a;
		public String b;
		public List<Pair<String,String>> exprs;
		public LeftVarContext l;
		public RightVarContext r;
		public ExprSequenceContext seq;
		public LeftVarContext leftVar() {
			return getRuleContext(LeftVarContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(LinearParser.LPAREN, 0); }
		public RightVarContext rightVar() {
			return getRuleContext(RightVarContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(LinearParser.COMMA); }
		public TerminalNode RPAREN() { return getToken(LinearParser.RPAREN, 0); }
		public ExprSequenceContext exprSequence() {
			return getRuleContext(ExprSequenceContext.class,0);
		}
		public TerminalNode WHILE() { return getToken(LinearParser.WHILE, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(LinearParser.COMMA, i);
		}
		public WhileSegmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileSegment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LinearListener ) ((LinearListener)listener).enterWhileSegment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LinearListener ) ((LinearListener)listener).exitWhileSegment(this);
		}
	}

	public final WhileSegmentContext whileSegment() throws RecognitionException {
		WhileSegmentContext _localctx = new WhileSegmentContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_whileSegment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71); match(WHILE);
			setState(72); match(LPAREN);
			setState(73); ((WhileSegmentContext)_localctx).l = leftVar();
			setState(74); match(COMMA);
			setState(75); ((WhileSegmentContext)_localctx).r = rightVar();

			        ((WhileSegmentContext)_localctx).a =  ((WhileSegmentContext)_localctx).l.var; ((WhileSegmentContext)_localctx).b =  ((WhileSegmentContext)_localctx).r.var;
			        ((WhileSegmentContext)_localctx).exprs =  new ArrayList<>();
			    
			setState(81);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(77); match(COMMA);
				setState(78); ((WhileSegmentContext)_localctx).seq = exprSequence();

				        ((WhileSegmentContext)_localctx).exprs =  ((WhileSegmentContext)_localctx).seq.exprs;
				    
				}
			}

			setState(83); match(RPAREN);
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
			if ( listener instanceof LinearListener ) ((LinearListener)listener).enterLeftVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LinearListener ) ((LinearListener)listener).exitLeftVar(this);
		}
	}

	public final LeftVarContext leftVar() throws RecognitionException {
		LeftVarContext _localctx = new LeftVarContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_leftVar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85); ((LeftVarContext)_localctx).v = variable();
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
			if ( listener instanceof LinearListener ) ((LinearListener)listener).enterRightVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LinearListener ) ((LinearListener)listener).exitRightVar(this);
		}
	}

	public final RightVarContext rightVar() throws RecognitionException {
		RightVarContext _localctx = new RightVarContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_rightVar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88); ((RightVarContext)_localctx).v = variable();
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
		public TerminalNode IDENT() { return getToken(LinearParser.IDENT, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LinearListener ) ((LinearListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LinearListener ) ((LinearListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91); ((VariableContext)_localctx).i = match(IDENT);

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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\fa\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4"+
		"\7\4)\n\4\f\4\16\4,\13\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3"+
		"\7\3\7\3\7\3\7\3\7\5\7>\n\7\3\b\3\b\3\b\7\bC\n\b\f\b\16\bF\13\b\3\t\3"+
		"\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\nT\n\n\3\n\3\n\3\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\2\2\16\2\4\6\b\n\f\16\20\22\24\26\30"+
		"\2\2X\2\32\3\2\2\2\4\36\3\2\2\2\6#\3\2\2\2\b-\3\2\2\2\n\66\3\2\2\2\f8"+
		"\3\2\2\2\16?\3\2\2\2\20G\3\2\2\2\22I\3\2\2\2\24W\3\2\2\2\26Z\3\2\2\2\30"+
		"]\3\2\2\2\32\33\b\2\1\2\33\34\5\4\3\2\34\35\b\2\1\2\35\3\3\2\2\2\36\37"+
		"\b\3\1\2\37 \5\6\4\2 !\5\22\n\2!\"\b\3\1\2\"\5\3\2\2\2#$\5\b\5\2$*\b\4"+
		"\1\2%&\5\b\5\2&\'\b\4\1\2\')\3\2\2\2(%\3\2\2\2),\3\2\2\2*(\3\2\2\2*+\3"+
		"\2\2\2+\7\3\2\2\2,*\3\2\2\2-.\7\4\2\2./\7\b\2\2/\60\5\n\6\2\60\61\b\5"+
		"\1\2\61\62\7\n\2\2\62\63\5\f\7\2\63\64\b\5\1\2\64\65\7\t\2\2\65\t\3\2"+
		"\2\2\66\67\5\30\r\2\67\13\3\2\2\28=\5\30\r\29:\7\b\2\2:;\5\16\b\2;<\7"+
		"\t\2\2<>\3\2\2\2=9\3\2\2\2=>\3\2\2\2>\r\3\2\2\2?D\5\20\t\2@A\7\n\2\2A"+
		"C\5\20\t\2B@\3\2\2\2CF\3\2\2\2DB\3\2\2\2DE\3\2\2\2E\17\3\2\2\2FD\3\2\2"+
		"\2GH\5\f\7\2H\21\3\2\2\2IJ\7\13\2\2JK\7\b\2\2KL\5\24\13\2LM\7\n\2\2MN"+
		"\5\26\f\2NS\b\n\1\2OP\7\n\2\2PQ\5\6\4\2QR\b\n\1\2RT\3\2\2\2SO\3\2\2\2"+
		"ST\3\2\2\2TU\3\2\2\2UV\7\t\2\2V\23\3\2\2\2WX\5\30\r\2XY\b\13\1\2Y\25\3"+
		"\2\2\2Z[\5\30\r\2[\\\b\f\1\2\\\27\3\2\2\2]^\7\f\2\2^_\b\r\1\2_\31\3\2"+
		"\2\2\6*=DS";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}