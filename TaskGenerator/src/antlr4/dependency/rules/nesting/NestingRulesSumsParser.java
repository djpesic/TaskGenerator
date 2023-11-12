// Generated from NestingRulesSums.g4 by ANTLR 4.4

package dependency.rules.nesting;
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
public class NestingRulesSumsParser extends Parser {
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
		RULE_formula = 0, RULE_segmentSequence1 = 1, RULE_expression = 2, RULE_leftSide = 3, 
		RULE_rightSide = 4, RULE_argList = 5, RULE_arg = 6, RULE_whileSegment = 7, 
		RULE_whileSegment1 = 8, RULE_exprSequence = 9, RULE_leftVar = 10, RULE_rightVar = 11, 
		RULE_variable = 12;
	public static final String[] ruleNames = {
		"formula", "segmentSequence1", "expression", "leftSide", "rightSide", 
		"argList", "arg", "whileSegment", "whileSegment1", "exprSequence", "leftVar", 
		"rightVar", "variable"
	};

	@Override
	public String getGrammarFileName() { return "NestingRulesSums.g4"; }

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
	    public Complexity compl;   
	    public Complexity compl1; //inner loop O(g(n))
	    public Complexity compl2; // outer loop O(f(n))
	    public boolean expSum = false;
	    
	    //sum g(k^i) from 1 to f(b)
	   
	    public Complexity calculateExpSum(int k){
	        String compl1Str = compl1.toString();
	        String compl2Str = compl2.toString();
	        if(compl1Str.equals("n")){
	           
	            if(compl2Str.equals("n")){
	                return Complexity.createExp(k, "n");
	            }
	            else if(compl2Str.contains("log")){
	                return Complexity.createLinear("n");
	            }
	            else if(compl2Str.equals("n^(1/2)") || compl2Str.equals("Sqrt(n)")){
	                VarNode v = new VarNode("1/2");
	                VarNode v1 = new VarNode("n");
	                GroupNode g = new GroupNode(new ExpNode(v1,v)); //n^(1/2)
	                ExpNode exp = new ExpNode(new NumNode(k), g); //k^(n^(1/2))
	                Complexity ret = new Complexity(exp);
	                return ret;
	            }
	        }
	        else if(compl1Str.contains("log")){
	            if(compl2Str.equals("n")){
	                return Complexity.createPoly(2, "n");  // n^2
	            }
	            else if(compl2Str.contains("log")){
	                GroupNode g = new GroupNode(new LogNode(new VarNode("n")));
	                ExpNode exp = new ExpNode(g, new NumNode(2));  // (log(n))^2
	                Complexity ret = new Complexity(exp);
	                return ret;
	            }
	            else if(compl2Str.equals("n^(1/2)") || compl2Str.equals("Sqrt(n)")){
	                return Complexity.createLinear("n");
	            }
	        }
	        else if(compl1Str.equals("n^(1/2)") || compl2Str.equals("Sqrt(n)")){
	            if(compl2Str.equals("n")){
	                // (k^n)^(1/2)
	                VarNode v = new VarNode("1/2");
	                ExpNode e1 = new ExpNode(new NumNode(k), new VarNode("n"));
	                GroupNode g = new GroupNode(e1);
	                ExpNode e2 = new ExpNode(g,v);
	                Complexity compl = new Complexity(e2);
	                return compl;
	            }
	            else if(compl2Str.contains("log")){
	                VarNode v = new VarNode("1/2");
	                VarNode v1 = new VarNode("n");
	                ExpNode e = new ExpNode(v1,v);       //n^(1/2)
	                Complexity compl = new Complexity(e);
	                return compl;
	            }
	            else if(compl2Str.equals("n^(1/2)") || compl2Str.equals("Sqrt(n)")){
	               // (  k^(n^(1/2))  )^(1/2)
	               VarNode v1 = new VarNode("1/2");
	               VarNode v2 = new VarNode("n");
	               GroupNode g1 = new GroupNode(new ExpNode(v2, v1));
	               NumNode n = new NumNode(k);
	               ExpNode e = new ExpNode(n, g1);
	               GroupNode g2 = new GroupNode(e);
	               ExpNode e1 = new ExpNode(g2, new VarNode("1/2"));
	               Complexity compl = new Complexity(e1);
	               return compl;
	            }
	        }
	        //System.out.println("Complexity can't be found in the exp sum lookup table");
	        return null;
	    }
	    // sum g(i*k) from 1 to f(b)
	    public Complexity calculateSum(int k){
	        
	        String compl1Str = compl1.toString();
	        String compl2Str = compl2.toString();
	        if(compl1Str.equals("n")){
	           
	            if(compl2Str.equals("n")){
	                return Complexity.createPoly(2, "n"); //n^2
	            }
	            else if(compl2Str.contains("log")){
	                GroupNode g = new GroupNode(new LogNode(new VarNode("n")));
	                ExpNode exp = new ExpNode(g, new NumNode(2));  // (log(n))^2
	                Complexity ret = new Complexity(exp);
	                return ret;
	            }
	            else if(compl2Str.equals("n^(1/2)")  || compl2Str.equals("Sqrt(n)")){
	                    return Complexity.createLinear("n");
	            }
	        }
	        else if(compl1Str.contains("log")){
	            String base = compl1Str.substring(3,4);
	            if(compl2Str.equals("n")){
	                return Complexity.createLinear("n");
	            }
	            else if(compl2Str.contains("log")){
	                // log(n)*log(log(n))
	                LogNode l1 = new LogNode(new VarNode("n"));
	                LogNode l2 = new LogNode(new VarNode("n"));
	                GroupNode g1 = new GroupNode(l2);
	                LogNode l3 = new LogNode(g1); //loglog
	                MulNode m = new MulNode(l1, l3);
	                Complexity compl = new Complexity(m);
	                return compl;
	            }
	            else if(compl2Str.equals("n^(1/2)") || compl2Str.equals("Sqrt(n)")){
	               //  (n^(1/2))  *  log(n^(1/2))
	               ExpNode e1 = new ExpNode(new VarNode("n"), new VarNode("1/2"));

	               ExpNode e2 = new ExpNode(new VarNode("n"), new VarNode("1/2"));
	               LogNode l = new LogNode(new GroupNode(e2));
	               MulNode m = new MulNode(new GroupNode(e1), l);
	               Complexity compl = new Complexity(m);
	               return compl;
	            }
	        }
	        else if(compl1Str.equals("n^(1/2)")  || compl2Str.equals("Sqrt(n)")){
	           // System.out.println("Can not be calculated");
	            return null;
	        }
	        //System.out.println("Complexity can't be fout in the sum lookup table");
	        return null;
	    }

	public NestingRulesSumsParser(TokenStream input) {
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
			if ( listener instanceof NestingRulesSumsListener ) ((NestingRulesSumsListener)listener).enterFormula(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesSumsListener ) ((NestingRulesSumsListener)listener).exitFormula(this);
		}
	}

	public final FormulaContext formula() throws RecognitionException {
		FormulaContext _localctx = new FormulaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_formula);
		try {
			enterOuterAlt(_localctx, 1);
			{

			       // System.out.println("Starting NestingRulesSums rule");
			    
			setState(27); ((FormulaContext)_localctx).s = segmentSequence1();

			        if(((FormulaContext)_localctx).s.valid == true){
			            if(expSum)
			                ((FormulaContext)_localctx).resultComplexity =  calculateExpSum(((FormulaContext)_localctx).s.k);
			            else
			                ((FormulaContext)_localctx).resultComplexity =  calculateSum(((FormulaContext)_localctx).s.k);
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
		public int k;
		public int numC;
		public int numD;
		public double d;
		public ExprSequenceContext ex;
		public WhileSegment1Context wh;
		public WhileSegment1Context whileSegment1() {
			return getRuleContext(WhileSegment1Context.class,0);
		}
		public ExprSequenceContext exprSequence() {
			return getRuleContext(ExprSequenceContext.class,0);
		}
		public SegmentSequence1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_segmentSequence1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesSumsListener ) ((NestingRulesSumsListener)listener).enterSegmentSequence1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesSumsListener ) ((NestingRulesSumsListener)listener).exitSegmentSequence1(this);
		}
	}

	public final SegmentSequence1Context segmentSequence1() throws RecognitionException {
		SegmentSequence1Context _localctx = new SegmentSequence1Context(_ctx, getState());
		enterRule(_localctx, 2, RULE_segmentSequence1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30); ((SegmentSequence1Context)_localctx).ex = exprSequence();
			setState(31); ((SegmentSequence1Context)_localctx).wh = whileSegment1();

			        //c=1  
			        //d=n
			        ((SegmentSequence1Context)_localctx).valid =  ((SegmentSequence1Context)_localctx).wh.valid;
			        if(_localctx.valid){
			            ((SegmentSequence1Context)_localctx).numC =  0; ((SegmentSequence1Context)_localctx).numD = 0;
			            for(Pair<String,String> e : ((SegmentSequence1Context)_localctx).ex.exprs){
			                if(e.getValue0().equals(((SegmentSequence1Context)_localctx).wh.c)){
			                    try{
			                        ((SegmentSequence1Context)_localctx).d =  Double.parseDouble(e.getValue1());
			                        if(_localctx.d==1)
			                            ((SegmentSequence1Context)_localctx).valid =  true;
			                        else{
			                           //System.out.println("valid1 false");
			                            ((SegmentSequence1Context)_localctx).valid =  false;
			                        }
			                        _localctx.numC++;
			                    }catch(Exception ex){
			                        //System.out.println("valid2 false");
			                        ((SegmentSequence1Context)_localctx).valid =  false;
			                    }
			                }
			                if(e.getValue0().equals(((SegmentSequence1Context)_localctx).wh.d)){
			                    if(e.getValue1().toLowerCase().equals("n")){
			                        ((SegmentSequence1Context)_localctx).valid =  true;
			                    }else{
			                        ((SegmentSequence1Context)_localctx).valid =  false;
			                        //System.out.println("valid3 false");
			                    }
			                    _localctx.numD++;
			                }
			            }
			            if(_localctx.numC==0 && ((SegmentSequence1Context)_localctx).wh.initC){
			                ((SegmentSequence1Context)_localctx).valid =  false;
			                //System.out.println("valid17 false");
			            }else if(_localctx.numD==0 && ((SegmentSequence1Context)_localctx).wh.initD){
			                ((SegmentSequence1Context)_localctx).valid =  false;
			                //System.out.println("valid18 false");
			            }
			            if(_localctx.valid){
			                if(_localctx.numC==1 && _localctx.numD==0){
			                    ((SegmentSequence1Context)_localctx).valid =  true;
			                }
			                else if(_localctx.numC==0 && _localctx.numD==1){
			                    ((SegmentSequence1Context)_localctx).valid =  true;
			                }
			                else if(_localctx.numC==1 && _localctx.numD==1){
			                    ((SegmentSequence1Context)_localctx).valid =  true;
			                }else{
			                    ((SegmentSequence1Context)_localctx).valid =  false;
			                    //System.out.println("valid4 false"+" numC= "+_localctx.numC+" numD= "+_localctx.numD);
			                }
			            }
			            if(_localctx.valid){
			                ((SegmentSequence1Context)_localctx).k =  ((SegmentSequence1Context)_localctx).wh.k1;
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

	public static class ExpressionContext extends ParserRuleContext {
		public String ls;
		public String rs;
		public LeftSideContext ls1;
		public RightSideContext rs1;
		public TerminalNode LPAREN() { return getToken(NestingRulesSumsParser.LPAREN, 0); }
		public TerminalNode COMMA() { return getToken(NestingRulesSumsParser.COMMA, 0); }
		public RightSideContext rightSide() {
			return getRuleContext(RightSideContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(NestingRulesSumsParser.RPAREN, 0); }
		public TerminalNode EXPR() { return getToken(NestingRulesSumsParser.EXPR, 0); }
		public LeftSideContext leftSide() {
			return getRuleContext(LeftSideContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesSumsListener ) ((NestingRulesSumsListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesSumsListener ) ((NestingRulesSumsListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34); match(EXPR);
			setState(35); match(LPAREN);
			setState(36); ((ExpressionContext)_localctx).ls1 = leftSide();
			((ExpressionContext)_localctx).ls =  (((ExpressionContext)_localctx).ls1!=null?_input.getText(((ExpressionContext)_localctx).ls1.start,((ExpressionContext)_localctx).ls1.stop):null);
			setState(38); match(COMMA);
			setState(39); ((ExpressionContext)_localctx).rs1 = rightSide();

			        ((ExpressionContext)_localctx).rs =  (((ExpressionContext)_localctx).rs1!=null?_input.getText(((ExpressionContext)_localctx).rs1.start,((ExpressionContext)_localctx).rs1.stop):null);
			    
			setState(41); match(RPAREN);
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
			if ( listener instanceof NestingRulesSumsListener ) ((NestingRulesSumsListener)listener).enterLeftSide(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesSumsListener ) ((NestingRulesSumsListener)listener).exitLeftSide(this);
		}
	}

	public final LeftSideContext leftSide() throws RecognitionException {
		LeftSideContext _localctx = new LeftSideContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_leftSide);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43); variable();
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
		public TerminalNode LPAREN() { return getToken(NestingRulesSumsParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(NestingRulesSumsParser.RPAREN, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public RightSideContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rightSide; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesSumsListener ) ((NestingRulesSumsListener)listener).enterRightSide(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesSumsListener ) ((NestingRulesSumsListener)listener).exitRightSide(this);
		}
	}

	public final RightSideContext rightSide() throws RecognitionException {
		RightSideContext _localctx = new RightSideContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_rightSide);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45); variable();
			setState(50);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(46); match(LPAREN);
				setState(47); argList();
				setState(48); match(RPAREN);
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
		public List<TerminalNode> COMMA() { return getTokens(NestingRulesSumsParser.COMMA); }
		public List<ArgContext> arg() {
			return getRuleContexts(ArgContext.class);
		}
		public TerminalNode COMMA(int i) {
			return getToken(NestingRulesSumsParser.COMMA, i);
		}
		public ArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesSumsListener ) ((NestingRulesSumsListener)listener).enterArgList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesSumsListener ) ((NestingRulesSumsListener)listener).exitArgList(this);
		}
	}

	public final ArgListContext argList() throws RecognitionException {
		ArgListContext _localctx = new ArgListContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_argList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52); ((ArgListContext)_localctx).a = arg();
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(53); match(COMMA);
				setState(54); ((ArgListContext)_localctx).a1 = arg();
				}
				}
				setState(59);
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
			if ( listener instanceof NestingRulesSumsListener ) ((NestingRulesSumsListener)listener).enterArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesSumsListener ) ((NestingRulesSumsListener)listener).exitArg(this);
		}
	}

	public final ArgContext arg() throws RecognitionException {
		ArgContext _localctx = new ArgContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_arg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60); ((ArgContext)_localctx).rs = rightSide();
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
		public List<Pair<String, String>> exprs;
		public LeftVarContext e1;
		public RightVarContext e2;
		public ExprSequenceContext s;
		public LeftVarContext leftVar() {
			return getRuleContext(LeftVarContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(NestingRulesSumsParser.LPAREN, 0); }
		public RightVarContext rightVar() {
			return getRuleContext(RightVarContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(NestingRulesSumsParser.COMMA); }
		public TerminalNode RPAREN() { return getToken(NestingRulesSumsParser.RPAREN, 0); }
		public ExprSequenceContext exprSequence() {
			return getRuleContext(ExprSequenceContext.class,0);
		}
		public TerminalNode WHILE() { return getToken(NestingRulesSumsParser.WHILE, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(NestingRulesSumsParser.COMMA, i);
		}
		public WhileSegmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileSegment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesSumsListener ) ((NestingRulesSumsListener)listener).enterWhileSegment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesSumsListener ) ((NestingRulesSumsListener)listener).exitWhileSegment(this);
		}
	}

	public final WhileSegmentContext whileSegment() throws RecognitionException {
		WhileSegmentContext _localctx = new WhileSegmentContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_whileSegment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62); match(WHILE);
			setState(63); match(LPAREN);
			setState(64); ((WhileSegmentContext)_localctx).e1 = leftVar();
			setState(65); match(COMMA);
			setState(66); ((WhileSegmentContext)_localctx).e2 = rightVar();
			setState(69);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(67); match(COMMA);
				setState(68); ((WhileSegmentContext)_localctx).s = exprSequence();
				}
			}

			setState(71); match(RPAREN);

			        ((WhileSegmentContext)_localctx).c =  (((WhileSegmentContext)_localctx).e1!=null?_input.getText(((WhileSegmentContext)_localctx).e1.start,((WhileSegmentContext)_localctx).e1.stop):null); ((WhileSegmentContext)_localctx).d =  (((WhileSegmentContext)_localctx).e2!=null?_input.getText(((WhileSegmentContext)_localctx).e2.start,((WhileSegmentContext)_localctx).e2.stop):null);
			        if((((WhileSegmentContext)_localctx).s!=null?_input.getText(((WhileSegmentContext)_localctx).s.start,((WhileSegmentContext)_localctx).s.stop):null) == null){
			            ((WhileSegmentContext)_localctx).exprs =  new ArrayList<>();
			        }
			        else{
			            ((WhileSegmentContext)_localctx).exprs =  ((WhileSegmentContext)_localctx).s.exprs;
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

	public static class WhileSegment1Context extends ParserRuleContext {
		public boolean valid;
		public String c;
		public String d;
		public boolean initC;
		public boolean initD;
		public int k1;
		public int numC;
		public int numD;
		public Pair<String,String> exprC;
		public Pair<String,String> exprD;
		public Unification unification;
		public StringBuilder formulas;
		public Set<String> vars;
		public Pair<String,String> solC;
		public Pair<String,String> solD;
		public LeftVarContext e1;
		public RightVarContext e2;
		public ExprSequenceContext ex;
		public WhileSegmentContext wh;
		public LeftVarContext leftVar() {
			return getRuleContext(LeftVarContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(NestingRulesSumsParser.LPAREN, 0); }
		public RightVarContext rightVar() {
			return getRuleContext(RightVarContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(NestingRulesSumsParser.COMMA); }
		public TerminalNode RPAREN() { return getToken(NestingRulesSumsParser.RPAREN, 0); }
		public ExprSequenceContext exprSequence() {
			return getRuleContext(ExprSequenceContext.class,0);
		}
		public TerminalNode WHILE() { return getToken(NestingRulesSumsParser.WHILE, 0); }
		public WhileSegmentContext whileSegment() {
			return getRuleContext(WhileSegmentContext.class,0);
		}
		public TerminalNode COMMA(int i) {
			return getToken(NestingRulesSumsParser.COMMA, i);
		}
		public WhileSegment1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileSegment1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesSumsListener ) ((NestingRulesSumsListener)listener).enterWhileSegment1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesSumsListener ) ((NestingRulesSumsListener)listener).exitWhileSegment1(this);
		}
	}

	public final WhileSegment1Context whileSegment1() throws RecognitionException {
		WhileSegment1Context _localctx = new WhileSegment1Context(_ctx, getState());
		enterRule(_localctx, 16, RULE_whileSegment1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			((WhileSegment1Context)_localctx).valid =  true; ((WhileSegment1Context)_localctx).initC =  false; ((WhileSegment1Context)_localctx).initD =  false;
			setState(75); match(WHILE);
			setState(76); match(LPAREN);
			setState(77); ((WhileSegment1Context)_localctx).e1 = leftVar();
			setState(78); match(COMMA);
			setState(79); ((WhileSegment1Context)_localctx).e2 = rightVar();
			setState(80); match(COMMA);
			setState(81); ((WhileSegment1Context)_localctx).ex = exprSequence();
			setState(82); ((WhileSegment1Context)_localctx).wh = whileSegment();

			        ((WhileSegment1Context)_localctx).c =  ((WhileSegment1Context)_localctx).wh.c;
			        ((WhileSegment1Context)_localctx).d =  ((WhileSegment1Context)_localctx).wh.d;
			    
			setState(84); match(RPAREN);

			        for(Pair<String,String> e : ((WhileSegment1Context)_localctx).ex.exprs){
			            if(e.getValue0().equals(((WhileSegment1Context)_localctx).wh.c)){
			                _localctx.numC++;
			                ((WhileSegment1Context)_localctx).exprC =  e;
			            }
			            if(e.getValue0().equals(((WhileSegment1Context)_localctx).wh.d)){
			                _localctx.numD++;
			                ((WhileSegment1Context)_localctx).exprD =  e;
			            }
			        }
			        if(_localctx.numC==0 && _localctx.numD==0){
			            ((WhileSegment1Context)_localctx).valid =  false; 
			            //System.out.println("valid6 false");
			        }
			        else if(_localctx.numC>1 && _localctx.numD>1){
			            ((WhileSegment1Context)_localctx).valid =  false;
			            //System.out.println("valid7 false");
			        }
			        else{

			            String[] ops = {"mul", "add"};
			            String[] opsCompl = {"divF", "sub"};
			            for(int i = 0;i<ops.length;i++){
			                String op = ops[i];
			                String opCompl = opsCompl[i];
			                ((WhileSegment1Context)_localctx).valid =  true;
			                try{
			                    if(_localctx.numC==1 && _localctx.numD==1){
			                        //d=N 
			                        if(!_localctx.exprD.getValue1().toLowerCase().equals("n")){
			                            
			                        }else{
			                            //c=c*k or c+k
			                            ((WhileSegment1Context)_localctx).unification =  new Unification();
			                            ((WhileSegment1Context)_localctx).formulas =  new StringBuilder();
			                            _localctx.formulas.append(op).append("(c,k)");
			                            _localctx.formulas.append(_localctx.exprC.getValue1());
			                            ((WhileSegment1Context)_localctx).vars =  new HashSet<>();
			                            _localctx.vars.add("k");
			                            _localctx.vars.add("c");
			                            _localctx.unification.startUnification(_localctx.formulas.toString(), _localctx.vars);
			                            solution = parseSolution(_localctx.unification.getSolution());
			                            for(Pair<String,String> sol:solution){
			                                if(!_localctx.valid)break;
			                                if(sol.getValue0().equals("c")){
			                                    if(sol.getValue1().equals(_localctx.exprC.getValue0())){
			                                        ((WhileSegment1Context)_localctx).valid =  true;
			                                        ((WhileSegment1Context)_localctx).solC =  sol;
			                                    }else{
			                                        ((WhileSegment1Context)_localctx).valid =  false;
			                                        //System.out.println("valid8 false");
			                                    }
			                                    if(_localctx.valid){
			                                        for(Pair<String,String> e:((WhileSegment1Context)_localctx).wh.exprs){
			                                            if(e.getValue0().equals(_localctx.solC.getValue0())){
			                                                ((WhileSegment1Context)_localctx).valid =  false;
			                                               // System.out.println("valid11 false");
			                                            }
			                                        }
			                                    }
			                                }else if(sol.getValue0().equals("k")){
			                                    try{
			                                        ((WhileSegment1Context)_localctx).k1 =  Integer.parseInt(sol.getValue1());
			                                        if(_localctx.k1>1){((WhileSegment1Context)_localctx).valid =  true;}
			                                        else{
			                                            ((WhileSegment1Context)_localctx).valid =  false;
			                                           // System.out.println("valid9 false");
			                                        }
			                                    }
			                                    catch(Exception ex){
			                                        ((WhileSegment1Context)_localctx).valid =  false;
			                                       // System.out.println("valid10 false");
			                                    }
			                                }
			                            }
			                            if(op.equals("mul") || op.equals("divF")){
			                                expSum = true;
			                            }else{
			                                expSum = false;
			                            }
			                        }
			                        if(!_localctx.valid){
			                            //c=number
			                            if(!NumberUtils.isCreatable(_localctx.exprC.getValue1())){
			                                
			                                //c=c*k or c+k
			                                ((WhileSegment1Context)_localctx).unification =  new Unification();
			                                ((WhileSegment1Context)_localctx).formulas =  new StringBuilder();
			                                _localctx.formulas.append(op).append("(c,k)");
			                                _localctx.formulas.append(_localctx.exprC.getValue1());
			                                ((WhileSegment1Context)_localctx).vars =  new HashSet<>();
			                                _localctx.vars.add("k");
			                                _localctx.vars.add("c");
			                                _localctx.unification.startUnification(_localctx.formulas.toString(), _localctx.vars);
			                                solution = parseSolution(_localctx.unification.getSolution());
			                                for(Pair<String,String> sol:solution){
			                                    if(!_localctx.valid)break;
			                                    if(sol.getValue0().equals("c")){
			                                        if(sol.getValue1().equals(_localctx.exprC.getValue0())){
			                                            ((WhileSegment1Context)_localctx).valid =  true;
			                                            ((WhileSegment1Context)_localctx).solC =  sol;
			                                        }else{
			                                            ((WhileSegment1Context)_localctx).valid =  false;
			                                           // System.out.println("valid20 false");
			                                        }
			                                        if(_localctx.valid){
			                                            for(Pair<String,String> e:((WhileSegment1Context)_localctx).wh.exprs){
			                                                if(e.getValue0().equals(_localctx.solC.getValue0())){
			                                                    ((WhileSegment1Context)_localctx).valid =  false;
			                                                  //  System.out.println("valid22 false");
			                                                }
			                                            }
			                                        }
			                                    }else if(sol.getValue0().equals("k")){
			                                        try{
			                                            ((WhileSegment1Context)_localctx).k1 =  Integer.parseInt(sol.getValue1());
			                                            if(_localctx.k1>1){((WhileSegment1Context)_localctx).valid =  true;}
			                                            else{
			                                                ((WhileSegment1Context)_localctx).valid =  false;
			                                              //  System.out.println("valid23 false");
			                                            }
			                                        }
			                                        catch(NumberFormatException ex){
			                                            ((WhileSegment1Context)_localctx).valid =  false;
			                                            //System.out.println("valid24 false");
			                                        }
			                                    }
			                                }
			                                if(op.equals("mul") || op.equals("divF")){
			                                    expSum = true;
			                                }else{
			                                    expSum = false;
			                                }

			                            }
			                            else{
			                                // d=d/k or d-k
			                               ((WhileSegment1Context)_localctx).unification =  new Unification();
			                               ((WhileSegment1Context)_localctx).formulas =  new StringBuilder();
			                               _localctx.formulas.append(opCompl).append("(d,k)");
			                               _localctx.formulas.append(_localctx.exprD.getValue1());
			                               ((WhileSegment1Context)_localctx).vars =  new HashSet<>();
			                               _localctx.vars.add("k");
			                               _localctx.vars.add("d");
			                               ((WhileSegment1Context)_localctx).valid =  true;
			                               _localctx.unification.startUnification(_localctx.formulas.toString(), _localctx.vars);
			                               solution = parseSolution(_localctx.unification.getSolution());
			                               for(Pair<String,String> sol:solution){
			                                   if(!_localctx.valid)break;
			                                   if(sol.getValue0().equals("d")){
			                                       if(sol.getValue1().equals(_localctx.exprD.getValue0())){
			                                           ((WhileSegment1Context)_localctx).valid =  true;
			                                           ((WhileSegment1Context)_localctx).solD =  sol;
			                                       }else{
			                                           ((WhileSegment1Context)_localctx).valid =  false;
			                                          // System.out.println("valid12 false");
			                                       }
			                                       if(_localctx.valid){
			                                           for(Pair<String,String> e:((WhileSegment1Context)_localctx).wh.exprs){
			                                               if(!e.getValue0().equals(_localctx.solD.getValue0())){
			                                                   ((WhileSegment1Context)_localctx).valid =  false;
			                                                 //  System.out.println("valid13 false");
			                                               }
			                                           }
			                                       }
			                                   }else if(sol.getValue0().equals("k")){
			                                       try{
			                                           ((WhileSegment1Context)_localctx).k1 =  Integer.parseInt(sol.getValue1());
			                                           if(_localctx.k1>1){((WhileSegment1Context)_localctx).valid =  true;}
			                                           else{
			                                               ((WhileSegment1Context)_localctx).valid =  false;
			                                              // System.out.println("valid14 false");
			                                           }
			                                       }
			                                       catch(Exception ex){
			                                           ((WhileSegment1Context)_localctx).valid =  false;
			                                           //System.out.println("valid15 false");
			                                       }
			                                   }
			                               }
			                            expSum = true;
			                            }
			                        }
			                    }else{
			                        ((WhileSegment1Context)_localctx).valid =  false;
			                       // System.out.println("valid21 false");
			                    }

			                    if(_localctx.valid){
			                        if(_localctx.numC==1 && _localctx.numD==0){
			                            ((WhileSegment1Context)_localctx).initC =  true;
			                        }else if(_localctx.numC==0 && _localctx.numD==1){
			                            ((WhileSegment1Context)_localctx).initD =  true;
			                        }else if(_localctx.numC==1 && _localctx.numD==1){
			                            ((WhileSegment1Context)_localctx).initC =  true;
			                            ((WhileSegment1Context)_localctx).initD =  true;
			                        }
			                    }

			                }catch(Exception ex){
			                    ((WhileSegment1Context)_localctx).valid =  false;
			                    //System.out.println("valid16 false");
			                    ex.printStackTrace();
			                }
			                if(_localctx.valid)break;
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
			if ( listener instanceof NestingRulesSumsListener ) ((NestingRulesSumsListener)listener).enterExprSequence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesSumsListener ) ((NestingRulesSumsListener)listener).exitExprSequence(this);
		}
	}

	public final ExprSequenceContext exprSequence() throws RecognitionException {
		ExprSequenceContext _localctx = new ExprSequenceContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_exprSequence);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87); ((ExprSequenceContext)_localctx).e1 = expression();

			        ((ExprSequenceContext)_localctx).exprs =  new ArrayList<>();
			        Pair<String,String> p = new Pair(((ExprSequenceContext)_localctx).e1.ls,((ExprSequenceContext)_localctx).e1.rs);
			        if(((ExprSequenceContext)_localctx).e1.ls != null && ((ExprSequenceContext)_localctx).e1.rs !=null)
			            _localctx.exprs.add(p);
			    
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EXPR) {
				{
				{
				setState(89); ((ExprSequenceContext)_localctx).e2 = expression();

				        p = new Pair(((ExprSequenceContext)_localctx).e2.ls,((ExprSequenceContext)_localctx).e2.rs);
				        _localctx.exprs.add(p);
				    
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
			if ( listener instanceof NestingRulesSumsListener ) ((NestingRulesSumsListener)listener).enterLeftVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesSumsListener ) ((NestingRulesSumsListener)listener).exitLeftVar(this);
		}
	}

	public final LeftVarContext leftVar() throws RecognitionException {
		LeftVarContext _localctx = new LeftVarContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_leftVar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97); variable();
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
			if ( listener instanceof NestingRulesSumsListener ) ((NestingRulesSumsListener)listener).enterRightVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesSumsListener ) ((NestingRulesSumsListener)listener).exitRightVar(this);
		}
	}

	public final RightVarContext rightVar() throws RecognitionException {
		RightVarContext _localctx = new RightVarContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_rightVar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99); variable();
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
		public TerminalNode IDENT() { return getToken(NestingRulesSumsParser.IDENT, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesSumsListener ) ((NestingRulesSumsListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NestingRulesSumsListener ) ((NestingRulesSumsListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101); ((VariableContext)_localctx).i = match(IDENT);

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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\fk\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\5\6\65\n\6\3\7\3\7"+
		"\3\7\7\7:\n\7\f\7\16\7=\13\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tH"+
		"\n\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\13\3\13\3\13\3\13\3\13\7\13_\n\13\f\13\16\13b\13\13\3\f\3\f\3\r\3\r\3"+
		"\16\3\16\3\16\3\16\2\2\17\2\4\6\b\n\f\16\20\22\24\26\30\32\2\2a\2\34\3"+
		"\2\2\2\4 \3\2\2\2\6$\3\2\2\2\b-\3\2\2\2\n/\3\2\2\2\f\66\3\2\2\2\16>\3"+
		"\2\2\2\20@\3\2\2\2\22L\3\2\2\2\24Y\3\2\2\2\26c\3\2\2\2\30e\3\2\2\2\32"+
		"g\3\2\2\2\34\35\b\2\1\2\35\36\5\4\3\2\36\37\b\2\1\2\37\3\3\2\2\2 !\5\24"+
		"\13\2!\"\5\22\n\2\"#\b\3\1\2#\5\3\2\2\2$%\7\4\2\2%&\7\b\2\2&\'\5\b\5\2"+
		"\'(\b\4\1\2()\7\n\2\2)*\5\n\6\2*+\b\4\1\2+,\7\t\2\2,\7\3\2\2\2-.\5\32"+
		"\16\2.\t\3\2\2\2/\64\5\32\16\2\60\61\7\b\2\2\61\62\5\f\7\2\62\63\7\t\2"+
		"\2\63\65\3\2\2\2\64\60\3\2\2\2\64\65\3\2\2\2\65\13\3\2\2\2\66;\5\16\b"+
		"\2\678\7\n\2\28:\5\16\b\29\67\3\2\2\2:=\3\2\2\2;9\3\2\2\2;<\3\2\2\2<\r"+
		"\3\2\2\2=;\3\2\2\2>?\5\n\6\2?\17\3\2\2\2@A\7\13\2\2AB\7\b\2\2BC\5\26\f"+
		"\2CD\7\n\2\2DG\5\30\r\2EF\7\n\2\2FH\5\24\13\2GE\3\2\2\2GH\3\2\2\2HI\3"+
		"\2\2\2IJ\7\t\2\2JK\b\t\1\2K\21\3\2\2\2LM\b\n\1\2MN\7\13\2\2NO\7\b\2\2"+
		"OP\5\26\f\2PQ\7\n\2\2QR\5\30\r\2RS\7\n\2\2ST\5\24\13\2TU\5\20\t\2UV\b"+
		"\n\1\2VW\7\t\2\2WX\b\n\1\2X\23\3\2\2\2YZ\5\6\4\2Z`\b\13\1\2[\\\5\6\4\2"+
		"\\]\b\13\1\2]_\3\2\2\2^[\3\2\2\2_b\3\2\2\2`^\3\2\2\2`a\3\2\2\2a\25\3\2"+
		"\2\2b`\3\2\2\2cd\5\32\16\2d\27\3\2\2\2ef\5\32\16\2f\31\3\2\2\2gh\7\f\2"+
		"\2hi\b\16\1\2i\33\3\2\2\2\6\64;G`";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}