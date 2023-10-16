// Generated from Linear.g4 by ANTLR 4.4

package dependency.rules.basic;
import java.util.*;
import complexity.*;
import org.javatuples.Pair;
import unification.*;
import org.apache.commons.lang3.math.NumberUtils;

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
		WS=1, EXPR=2, LPAREN=3, RPAREN=4, COMMA=5, WHILE=6, IDENT=7;
	public static final String[] tokenNames = {
		"<INVALID>", "WS", "'expr'", "'('", "')'", "','", "'while'", "IDENT"
	};
	public static final int
		RULE_formula = 0, RULE_segmentSequence = 1, RULE_segment = 2, RULE_expression = 3, 
		RULE_leftSide = 4, RULE_rightSide = 5, RULE_argList = 6, RULE_arg = 7, 
		RULE_whileSegment = 8, RULE_leftVar = 9, RULE_rightVar = 10, RULE_exprSequence = 11, 
		RULE_variable = 12;
	public static final String[] ruleNames = {
		"formula", "segmentSequence", "segment", "expression", "leftSide", "rightSide", 
		"argList", "arg", "whileSegment", "leftVar", "rightVar", "exprSequence", 
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


	    public void unifyRightSide(String funcName, Pair<String,String> expr,
	        String constName, StringBuilder formulas, Unification unif) throws UnifyException{
	        formulas.delete(0, formulas.length());
	        formulas.append(funcName+"("+expr.getValue0()+","+constName+")");
	        formulas.append(expr.getValue1());
	        Set<String>variables = new HashSet<>();
	        variables.add(expr.getValue0());
	        variables.add(constName);
	        unif.startUnification(formulas.toString(), variables);
	    }
	    public void parseSolution(List<String> solution1){
	        //solution in form {formalVal}=actualVal
	        solution = new ArrayList<>();
	        for(int i = 0;i<solution1.size();i++){
	            String s = solution1.get(i);
	            String formal = s.substring(s.indexOf("{")+1, s.lastIndexOf("}"));
	            String actual = s.substring(s.indexOf("=")+1, s.lastIndexOf(";"));
	            if(actual.equals(""))actual = formal;
	            Pair<String,String> sol = new Pair<>(formal, actual);
	            solution.add(sol);
	        }

	    }

	    private List<Pair<String,String>> solution = new ArrayList<>();

	    public boolean validateLeftVar(String leftVarName, Unification unif){
	        parseSolution(unif.getSolution());
	        for(Pair<String,String> sol : solution){
	            if(sol.getValue1().equals(leftVarName)){
	                return true;
	            }
	        }
	        return false;
	    }
	    private Map<String, Double> constMap = new HashMap<>();

	    public boolean validateConst(String constName, Unification unif){
	        parseSolution(unif.getSolution());
	        for(Pair<String,String> sol : solution){
	            if(sol.getValue0().equals(constName) && 
	                NumberUtils.isCreatable(sol.getValue1())){
	                try{
	                    Double d = Double.parseDouble(sol.getValue1());
	                    constMap.put(constName, d);
	                    if(d>0) return true;
	                    return false;
	                }catch(NumberFormatException ex){
	                    return false;
	                }
	            }
	        }
	        return false;
	    }

	    public boolean validateConsts(){
	        try{
	            return (constMap.get("const") > constMap.get("const1"));
	        }catch(Exception ex){
	            return false;
	        }
	    }

	    

	public LinearParser(TokenStream input) {
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

			        // System.out.println("Starting linear rule");
			    
			setState(27); ((FormulaContext)_localctx).s = segmentSequence();

			        if(((FormulaContext)_localctx).s.valid == true){
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
			if ( listener instanceof LinearListener ) ((LinearListener)listener).enterSegmentSequence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LinearListener ) ((LinearListener)listener).exitSegmentSequence(this);
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
			setState(31); ((SegmentSequenceContext)_localctx).s1 = segment();
			 if(!(((SegmentSequenceContext)_localctx).s1.valid)) ((SegmentSequenceContext)_localctx).valid = false; 
			setState(38);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EXPR || _la==WHILE) {
				{
				{
				setState(33); ((SegmentSequenceContext)_localctx).s2 = segment();
				 if(!(((SegmentSequenceContext)_localctx).s2.valid)) ((SegmentSequenceContext)_localctx).valid = false; 
				}
				}
				setState(40);
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
			if ( listener instanceof LinearListener ) ((LinearListener)listener).enterSegment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LinearListener ) ((LinearListener)listener).exitSegment(this);
		}
	}

	public final SegmentContext segment() throws RecognitionException {
		SegmentContext _localctx = new SegmentContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_segment);
		try {
			setState(47);
			switch (_input.LA(1)) {
			case WHILE:
				enterOuterAlt(_localctx, 1);
				{
				setState(41); ((SegmentContext)_localctx).w = whileSegment();

				        ((SegmentContext)_localctx).valid =  ((SegmentContext)_localctx).w.valid;
				    
				}
				break;
			case EXPR:
				enterOuterAlt(_localctx, 2);
				{
				setState(44); ((SegmentContext)_localctx).e = expression();

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
			setState(49); match(EXPR);
			setState(50); match(LPAREN);
			setState(51); ((ExpressionContext)_localctx).ls1 = leftSide();
			((ExpressionContext)_localctx).ls =  (((ExpressionContext)_localctx).ls1!=null?_input.getText(((ExpressionContext)_localctx).ls1.start,((ExpressionContext)_localctx).ls1.stop):null);
			setState(53); match(COMMA);
			setState(54); ((ExpressionContext)_localctx).rs1 = rightSide();

			        ((ExpressionContext)_localctx).rs =  (((ExpressionContext)_localctx).rs1!=null?_input.getText(((ExpressionContext)_localctx).rs1.start,((ExpressionContext)_localctx).rs1.stop):null);
			    
			setState(56); match(RPAREN);
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
			setState(58); variable();
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
			setState(60); variable();
			setState(65);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(61); match(LPAREN);
				setState(62); argList();
				setState(63); match(RPAREN);
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
			setState(67); ((ArgListContext)_localctx).a = arg();
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(68); match(COMMA);
				setState(69); ((ArgListContext)_localctx).a1 = arg();
				}
				}
				setState(74);
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
			setState(75); ((ArgContext)_localctx).rs = rightSide();
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
		public int numA;
		public int numB;
		public int i;
		public Pair<String,String> expr;
		public String f1;
		public String f2;
		public Unification unif;
		public StringBuilder formulas;
		public Set<String> variables;
		public Pair<String,String> exprA;
		public Pair<String,String> exprB;
		public List<String> solution;
		public LeftVarContext a;
		public RightVarContext b;
		public ExprSequenceContext exprs;
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
			setState(77); match(WHILE);
			setState(78); match(LPAREN);
			setState(79); ((WhileSegmentContext)_localctx).a = leftVar();
			setState(80); match(COMMA);
			setState(81); ((WhileSegmentContext)_localctx).b = rightVar();
			setState(84);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(82); match(COMMA);
				setState(83); ((WhileSegmentContext)_localctx).exprs = exprSequence();
				}
			}


			        if(((WhileSegmentContext)_localctx).a.var.equals(((WhileSegmentContext)_localctx).b.var)){
			            ((WhileSegmentContext)_localctx).valid =  false; // a == b
			        }else{
			            //count number of a=... and b=...
			            for(((WhileSegmentContext)_localctx).i =  0;_localctx.i< ((WhileSegmentContext)_localctx).exprs.exprs.size();){
			                ((WhileSegmentContext)_localctx).expr =  ((WhileSegmentContext)_localctx).exprs.exprs.get(_localctx.i);
			                if(_localctx.expr.getValue0().equals(((WhileSegmentContext)_localctx).a.var)){
			                    _localctx.numA++;    
			                    _localctx.i++;
			                    ((WhileSegmentContext)_localctx).exprA =  _localctx.expr;
			                }
			                else if(_localctx.expr.getValue0().equals(((WhileSegmentContext)_localctx).b.var)){
			                    _localctx.numB++;
			                    _localctx.i++;
			                    ((WhileSegmentContext)_localctx).exprB =  _localctx.expr;
			                }else{
			                    ((WhileSegmentContext)_localctx).exprs.exprs.remove(_localctx.i);
			                }
			            }
			            ((WhileSegmentContext)_localctx).unif =  new Unification();
			            ((WhileSegmentContext)_localctx).formulas =  new StringBuilder();
			            if(_localctx.numA==1 && _localctx.numB==0){
			                // a=a+const
			                try{
			                    unifyRightSide("add", _localctx.exprA, "const", _localctx.formulas, _localctx.unif);
			                    ((WhileSegmentContext)_localctx).valid =  validateLeftVar(_localctx.exprA.getValue0(),_localctx.unif) && 
			                            validateConst("const", _localctx.unif);
			                }catch(Exception ex){
			                    ex.printStackTrace();
			                    ((WhileSegmentContext)_localctx).valid =  false;
			                }
			            }else if(_localctx.numA==0 && _localctx.numB==1){
			                //b=b-const
			                try{
			                    unifyRightSide("sub", _localctx.exprB, "const", _localctx.formulas, _localctx.unif);
			                    ((WhileSegmentContext)_localctx).valid =  validateLeftVar(_localctx.exprB.getValue0(),_localctx.unif) && 
			                        validateConst("const", _localctx.unif);
			                }catch(Exception ex){
			                    ex.printStackTrace();
			                    ((WhileSegmentContext)_localctx).valid =  false;
			                }
			            }else if(_localctx.numA==1 && _localctx.numB==1){
			                //a=a+const, b=b+const1
			                try{
			                    unifyRightSide("add", _localctx.exprA, "const", _localctx.formulas, _localctx.unif);
			                    ((WhileSegmentContext)_localctx).valid =  validateLeftVar(_localctx.exprA.getValue0(),_localctx.unif) && 
			                            validateConst("const", _localctx.unif);
			                
			                    unifyRightSide("add", _localctx.exprB, "const1", _localctx.formulas, _localctx.unif);
			                    ((WhileSegmentContext)_localctx).valid =  _localctx.valid && validateLeftVar(_localctx.exprB.getValue0(),_localctx.unif) && 
			                        validateConst("const1", _localctx.unif);
			                    ((WhileSegmentContext)_localctx).valid =  _localctx.valid && validateConsts();
			                }catch(Exception ex){
			                    ((WhileSegmentContext)_localctx).valid =  false;
			                }
			                //, b=b+const1, a=a+const
			                try{
			                    if(!_localctx.valid){
			                        unifyRightSide("add", _localctx.exprB, "const1", _localctx.formulas, _localctx.unif);
			                        ((WhileSegmentContext)_localctx).valid =  validateLeftVar(_localctx.exprB.getValue0(),_localctx.unif) && 
			                            validateConst("const1", _localctx.unif);

			                        unifyRightSide("add", _localctx.exprA, "const", _localctx.formulas, _localctx.unif);
			                        ((WhileSegmentContext)_localctx).valid =  _localctx.valid && validateLeftVar(_localctx.exprA.getValue0(),_localctx.unif) && 
			                            validateConst("const", _localctx.unif);

			                        ((WhileSegmentContext)_localctx).valid =  _localctx.valid && validateConsts();
			                    }
			                }catch(Exception ex){
			                    ((WhileSegmentContext)_localctx).valid =  false;
			                }
			                //a=a-const, b=b-const1
			                try{
			                    if(!_localctx.valid){
			                        unifyRightSide("sub", _localctx.exprA, "const1", _localctx.formulas, _localctx.unif);
			                        ((WhileSegmentContext)_localctx).valid =  validateLeftVar(_localctx.exprA.getValue0(),_localctx.unif) && 
			                            validateConst("const1", _localctx.unif);

			                        unifyRightSide("sub", _localctx.exprB, "const", _localctx.formulas, _localctx.unif);
			                        ((WhileSegmentContext)_localctx).valid =  _localctx.valid && validateLeftVar(_localctx.exprB.getValue0(),_localctx.unif) && 
			                            validateConst("const", _localctx.unif);
			                        ((WhileSegmentContext)_localctx).valid =  _localctx.valid && validateConsts();
			                    }
			                }catch(Exception ex){
			                    ((WhileSegmentContext)_localctx).valid =  false;
			                }
			                // b=b-const, a=a-const1
			                try{
			                    if(!_localctx.valid){
			                        unifyRightSide("sub", _localctx.exprB, "const", _localctx.formulas, _localctx.unif);
			                        ((WhileSegmentContext)_localctx).valid =  validateLeftVar(_localctx.exprB.getValue0(),_localctx.unif) && 
			                            validateConst("const", _localctx.unif);
			                        ((WhileSegmentContext)_localctx).valid =  validateConsts();

			                        unifyRightSide("sub", _localctx.exprA, "const1", _localctx.formulas, _localctx.unif);
			                        ((WhileSegmentContext)_localctx).valid =  _localctx.valid && validateLeftVar(_localctx.exprA.getValue0(),_localctx.unif) && 
			                            validateConst("const1", _localctx.unif);
			                        ((WhileSegmentContext)_localctx).valid =  _localctx.valid && validateConsts();
			                    }
			                }catch(Exception ex){
			                    ((WhileSegmentContext)_localctx).valid =  false;
			                }
			                // b=b-const, a=a+const1
			                try{
			                    if(!_localctx.valid){
			                        unifyRightSide("sub", _localctx.exprB, "const", _localctx.formulas, _localctx.unif);
			                        ((WhileSegmentContext)_localctx).valid =  validateLeftVar(_localctx.exprB.getValue0(),_localctx.unif) && 
			                            validateConst("const", _localctx.unif);

			                        unifyRightSide("add", _localctx.exprA, "const1", _localctx.formulas, _localctx.unif);
			                        ((WhileSegmentContext)_localctx).valid =  _localctx.valid && validateLeftVar(_localctx.exprA.getValue0(),_localctx.unif) && 
			                            validateConst("const1", _localctx.unif);
			                        
			                    }
			                }catch(Exception ex){
			                    ((WhileSegmentContext)_localctx).valid =  false;
			                }
			                //a=a+const1, b=b-const
			                try{
			                    if(!_localctx.valid){
			                        unifyRightSide("add", _localctx.exprA, "const", _localctx.formulas, _localctx.unif);
			                        ((WhileSegmentContext)_localctx).valid =  validateLeftVar(_localctx.exprB.getValue0(),_localctx.unif) && 
			                            validateConst("const", _localctx.unif);

			                        unifyRightSide("sub", _localctx.exprB, "const1", _localctx.formulas, _localctx.unif);
			                        ((WhileSegmentContext)_localctx).valid =  _localctx.valid && validateLeftVar(_localctx.exprA.getValue0(),_localctx.unif) && 
			                            validateConst("const1", _localctx.unif);
			                    }
			                }catch(Exception ex){
			                    ((WhileSegmentContext)_localctx).valid =  false;
			                }
			            }
			            else{
			                ((WhileSegmentContext)_localctx).valid =  false;
			            }
			            
			        }
			    
			setState(87); match(RPAREN);
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
			setState(89); ((LeftVarContext)_localctx).v = variable();
			((LeftVarContext)_localctx).var = ((LeftVarContext)_localctx).v.var;
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
			setState(92); ((RightVarContext)_localctx).v = variable();
			((RightVarContext)_localctx).var = ((RightVarContext)_localctx).v.var;
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
			if ( listener instanceof LinearListener ) ((LinearListener)listener).enterExprSequence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LinearListener ) ((LinearListener)listener).exitExprSequence(this);
		}
	}

	public final ExprSequenceContext exprSequence() throws RecognitionException {
		ExprSequenceContext _localctx = new ExprSequenceContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_exprSequence);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95); ((ExprSequenceContext)_localctx).e1 = expression();

			        ((ExprSequenceContext)_localctx).exprs =  new ArrayList<>();
			        Pair<String,String> p = new Pair(((ExprSequenceContext)_localctx).e1.ls,((ExprSequenceContext)_localctx).e1.rs);
			        if(((ExprSequenceContext)_localctx).e1.ls != null && ((ExprSequenceContext)_localctx).e1.rs !=null)
			            _localctx.exprs.add(p);
			    
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EXPR) {
				{
				{
				setState(97); ((ExprSequenceContext)_localctx).e2 = expression();

				        p = new Pair(((ExprSequenceContext)_localctx).e2.ls,((ExprSequenceContext)_localctx).e2.rs);
				        _localctx.exprs.add(p);
				    
				}
				}
				setState(104);
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
		enterRule(_localctx, 24, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105); ((VariableContext)_localctx).i = match(IDENT);

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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\to\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\7\3\'"+
		"\n\3\f\3\16\3*\13\3\3\4\3\4\3\4\3\4\3\4\3\4\5\4\62\n\4\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\5\7D\n\7\3\b\3\b\3\b"+
		"\7\bI\n\b\f\b\16\bL\13\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\nW\n\n"+
		"\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\7\rg\n\r\f"+
		"\r\16\rj\13\r\3\16\3\16\3\16\3\16\2\2\17\2\4\6\b\n\f\16\20\22\24\26\30"+
		"\32\2\2g\2\34\3\2\2\2\4 \3\2\2\2\6\61\3\2\2\2\b\63\3\2\2\2\n<\3\2\2\2"+
		"\f>\3\2\2\2\16E\3\2\2\2\20M\3\2\2\2\22O\3\2\2\2\24[\3\2\2\2\26^\3\2\2"+
		"\2\30a\3\2\2\2\32k\3\2\2\2\34\35\b\2\1\2\35\36\5\4\3\2\36\37\b\2\1\2\37"+
		"\3\3\2\2\2 !\b\3\1\2!\"\5\6\4\2\"(\b\3\1\2#$\5\6\4\2$%\b\3\1\2%\'\3\2"+
		"\2\2&#\3\2\2\2\'*\3\2\2\2(&\3\2\2\2()\3\2\2\2)\5\3\2\2\2*(\3\2\2\2+,\5"+
		"\22\n\2,-\b\4\1\2-\62\3\2\2\2./\5\b\5\2/\60\b\4\1\2\60\62\3\2\2\2\61+"+
		"\3\2\2\2\61.\3\2\2\2\62\7\3\2\2\2\63\64\7\4\2\2\64\65\7\5\2\2\65\66\5"+
		"\n\6\2\66\67\b\5\1\2\678\7\7\2\289\5\f\7\29:\b\5\1\2:;\7\6\2\2;\t\3\2"+
		"\2\2<=\5\32\16\2=\13\3\2\2\2>C\5\32\16\2?@\7\5\2\2@A\5\16\b\2AB\7\6\2"+
		"\2BD\3\2\2\2C?\3\2\2\2CD\3\2\2\2D\r\3\2\2\2EJ\5\20\t\2FG\7\7\2\2GI\5\20"+
		"\t\2HF\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2K\17\3\2\2\2LJ\3\2\2\2MN\5"+
		"\f\7\2N\21\3\2\2\2OP\7\b\2\2PQ\7\5\2\2QR\5\24\13\2RS\7\7\2\2SV\5\26\f"+
		"\2TU\7\7\2\2UW\5\30\r\2VT\3\2\2\2VW\3\2\2\2WX\3\2\2\2XY\b\n\1\2YZ\7\6"+
		"\2\2Z\23\3\2\2\2[\\\5\32\16\2\\]\b\13\1\2]\25\3\2\2\2^_\5\32\16\2_`\b"+
		"\f\1\2`\27\3\2\2\2ab\5\b\5\2bh\b\r\1\2cd\5\b\5\2de\b\r\1\2eg\3\2\2\2f"+
		"c\3\2\2\2gj\3\2\2\2hf\3\2\2\2hi\3\2\2\2i\31\3\2\2\2jh\3\2\2\2kl\7\t\2"+
		"\2lm\b\16\1\2m\33\3\2\2\2\b(\61CJVh";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}