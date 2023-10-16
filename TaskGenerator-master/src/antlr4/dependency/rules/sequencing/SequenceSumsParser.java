// Generated from SequenceSums.g4 by ANTLR 4.4

package dependency.rules.sequencing;
import java.util.*;
import complexity.*;
import org.javatuples.Pair;
import org.apache.commons.lang3.math.NumberUtils;
import unification.*;
import utils.YacasExecutor;
import com.rits.cloning.Cloner;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SequenceSumsParser extends Parser {
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
		RULE_formula = 0, RULE_segmentSequence = 1, RULE_segment1 = 2, RULE_expression = 3, 
		RULE_leftSide = 4, RULE_rightSide = 5, RULE_argList = 6, RULE_arg = 7, 
		RULE_whileSegment = 8, RULE_exprSequence = 9, RULE_leftVar = 10, RULE_rightVar = 11, 
		RULE_variable = 12;
	public static final String[] ruleNames = {
		"formula", "segmentSequence", "segment1", "expression", "leftSide", "rightSide", 
		"argList", "arg", "whileSegment", "exprSequence", "leftVar", "rightVar", 
		"variable"
	};

	@Override
	public String getGrammarFileName() { return "SequenceSums.g4"; }

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
	    
	    public boolean checkExpression(StringBuilder formulas, Set<String> vars){
	        try{
	            Unification unification = new Unification();
	            unification.startUnification(formulas.toString(), vars);
	            solution = parseSolution(unification.getSolution());
	        }catch(Exception ex){
	            ex.printStackTrace();
	            return false;
	        }
	        return true;
	    }
	    
	    public String getSolution(String formal){
	        for(Pair<String,String> s : solution){
	            if(s.getValue0().equals(formal)){
	                return s.getValue1(); // actual
	            }
	        }
	        return null;
	    }

	    public boolean checkK(int val){
	        String solK = getSolution("k");
	        try{
	            if(Double.parseDouble(solK) > val){
	                return true;
	            }
	            return false;

	        }catch(NumberFormatException ex){
	        }
	        return false;
	    }
	    
	    public Complexity calculateResultComplexity(String f, String g){
	        if(f.equals("n")){
	            if(g.equals("n^2")){
	                VarNode v = new VarNode("n");
	                NumNode n = new NumNode(2);
	                ExpNode exp = new ExpNode(v,n);
	                return new Complexity(exp);//n^2
	            }else if(g.equals("log(n^2)")){
	                return Complexity.createLinear("n");//n
	            }else if(g.equals("Sqrt(n^2)")){
	                return Complexity.createLinear("n");//n
	            }else if(g.equals("n!")){
	                return Complexity.createFact("n");//n!
	            }else if(g.equals("log(n!)")){
	                VarNode v = new VarNode("n");
	                LogNode l = new LogNode(new VarNode("n"));
	                MulNode m = new MulNode(v,l);
	                return new Complexity(m);//nlog(n)
	            }else if(g.equals("Sqrt(n!)")){
	                FactNode fct = new FactNode(new VarNode("n"));
	                SqrtNode s = new SqrtNode(fct);
	                return new Complexity(s);//Sqrt(n!)
	            }else if(g.equals("n")){
	                return Complexity.createLinear("n");//n
	            }else if(g.equals("log(n)")){
	                return Complexity.createLinear("n");//n
	            }else if(g.equals("Sqrt(n)")){
	                return Complexity.createLinear("n");//n
	            }else if(g.matches("[0-9]+\\^n")){
	                String baseS = g.substring(0, g.indexOf("^"));
	                int base = Integer.parseInt(baseS);
	                NumNode n = new NumNode(base);
	                VarNode v = new VarNode("n");
	                return new Complexity(new ExpNode(n,v));// o^n
	            }else if(g.matches("log\\([0-9]+\\^n\\)")){
	                return Complexity.createLinear("n");//n
	            }else if(g.matches("Sqrt\\([0-9]+\\^n\\)")){
	                String baseS = g.substring(5, g.indexOf("^"));
	                int base = Integer.parseInt(baseS);
	                NumNode n = new NumNode(base);
	                VarNode v = new VarNode("n");
	                ExpNode e = new ExpNode(n,v);
	                return new Complexity(new SqrtNode(new GroupNode(e)));// Sqrt(o^n)
	            }
	            else{
	                System.out.println("Wrong g(s) complexity");
	                return null;
	            }
	        }else if(f.equals("log(n)")){
	            if(g.equals("log(n)^2")){
	                VarNode v = new VarNode("n");
	                LogNode l = new LogNode(v);
	                NumNode n = new NumNode(2);
	                ExpNode exp = new ExpNode(l,n);
	                return new Complexity(exp);//log(n)^2
	            }else if(g.equals("log(log(n)^2)")){
	                return Complexity.createLog("n");//log(n)
	            }else if(g.equals("Sqrt(log(n)^2)")){
	                return Complexity.createLog("n");//log(n)
	            }else if(g.equals("log(n)!")){
	                FactNode fct = new FactNode(new LogNode(new VarNode("n")));
	                return new Complexity(fct);//log(n)!
	            }else if(g.equals("log(log(n)!)")){
	                VarNode v = new VarNode("n");
	                LogNode l = new LogNode(new VarNode("n"));
	                LogNode ll = new LogNode(l);
	                MulNode m = new MulNode(l,ll);
	                return new Complexity(m);//log(n)loglog(n)
	            }else if(g.equals("Sqrt(log(n)!)")){
	                FactNode fct = new FactNode(new VarNode("n"));
	                SqrtNode s = new SqrtNode(new LogNode(fct));
	                return new Complexity(s);//Sqrt(log(n)!)
	            }else if(g.equals("log(n)")){
	                return Complexity.createLog("n");//log(n)
	            }else if(g.equals("log(log(n))")){
	                return Complexity.createLog("n");//log(n)
	            }else if(g.equals("Sqrt(log(n))")){
	                return Complexity.createLog("n");//log(n)
	            }else if(g.matches("[0-9]+\\^log\\(n\\)")){
	                String baseS = g.substring(0, g.indexOf("^"));
	                int base = Integer.parseInt(baseS);
	                NumNode n = new NumNode(base);
	                VarNode v = new VarNode("n");
	                LogNode l = new LogNode(v);
	                return new Complexity(new ExpNode(n,l));// o^log(n)
	            }else if(g.matches("log\\([0-9]+\\^log\\(n\\)\\)")){
	                return Complexity.createLog("n");//log(n)
	            }else if(g.matches("Sqrt\\([0-9]+\\^log\\(n\\)\\)")){
	                String baseS = g.substring(5, g.indexOf("^"));
	                int base = Integer.parseInt(baseS);
	                NumNode n = new NumNode(base);
	                VarNode v = new VarNode("n");
	                ExpNode e = new ExpNode(n,new LogNode(v));
	                return new Complexity(new SqrtNode(new GroupNode(e)));// Sqrt(o^log(n))
	            }
	            else{
	                System.out.println("Wrong g(s) complexity 1");
	                return null;
	            }
	        }else if(f.equals("Sqrt(n)")){
	            if(g.equals("Sqrt(n)^2") || g.equals("n")){
	                return Complexity.createLinear("n");//n
	            }else if(g.equals("log(Sqrt(n)^2)")){
	                return Complexity.createSqrt("n");//Sqrt(n)
	            }else if(g.equals("Sqrt(Sqrt(n)^2)")){
	                return Complexity.createSqrt("n");//Sqrt(n)
	            }else if(g.equals("Sqrt(n)!")){
	                FactNode fct = new FactNode(new SqrtNode(new VarNode("n")));
	                return new Complexity(fct);//Sqrt(n)!
	            }else if(g.equals("log(Sqrt(n)!)")){
	                return Complexity.createSqrt("n");// Sqrt(n)
	            }else if(g.equals("Sqrt(Sqrt(n)!)")){
	                FactNode fct = new FactNode(new VarNode("n"));
	                SqrtNode s = new SqrtNode(new SqrtNode(fct));
	                return new Complexity(s);//Sqrt(Sqrt(n)!)
	            }else if(g.equals("Sqrt(n)")){
	                return Complexity.createSqrt("n");//Sqrt(n)
	            }else if(g.equals("log(Sqrt(n))")){
	                return Complexity.createSqrt("n");//Sqrt(n)
	            }else if(g.equals("Sqrt(Sqrt(n))")){
	                return Complexity.createSqrt("n");//Sqrt(n)
	            }else if(g.matches("[0-9]+\\^Sqrt\\(n\\)")){
	                return Complexity.createSqrt("n");// Sqrt(n)
	            }else if(g.matches("log\\([0-9]+\\^Sqrt\\(n\\)\\)")){
	                return Complexity.createSqrt("n");// Sqrt(n)
	            }else if(g.matches("Sqrt\\([0-9]+\\^Sqrt\\(n\\)\\)")){
	                String baseS = g.substring(5, g.indexOf("^"));
	                int base = Integer.parseInt(baseS);
	                NumNode n = new NumNode(base);
	                VarNode v = new VarNode("n");
	                ExpNode e = new ExpNode(n,new SqrtNode(v));
	                return new Complexity(new SqrtNode(new GroupNode(e)));// Sqrt(o^Sqrt(n))
	            }
	            else{
	                System.out.println("Wrong g(s) complexity 2");
	                return null;
	            }
	        }else{
	            System.out.println("Sequence sum complexity can't be calculated");
	            return null;
	        }
	    }

	    private List<Pair<String,String>> solution = new ArrayList<>(); 
	    public Complexity compl;   
	    public Complexity compl1; //first in the sequence
	    public Complexity compl2; // second in the sequence
	    Complexity sCompl = null;   
	    

	public SequenceSumsParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FormulaContext extends ParserRuleContext {
		public Complexity resultComplexity;
		public Set<String> refVars;
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
			if ( listener instanceof SequenceSumsListener ) ((SequenceSumsListener)listener).enterFormula(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SequenceSumsListener ) ((SequenceSumsListener)listener).exitFormula(this);
		}
	}

	public final FormulaContext formula() throws RecognitionException {
		FormulaContext _localctx = new FormulaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_formula);
		try {
			enterOuterAlt(_localctx, 1);
			{

			       // System.out.println("Starting SequenceSums rule");
			    
			setState(27); ((FormulaContext)_localctx).s = segmentSequence();

			        if(((FormulaContext)_localctx).s.valid == true){
			            //System.out.println("compl1: "+compl1);
			            //System.out.println("compl2: "+compl2);
			            //System.out.println("scompl: "+sCompl);
			            String compl1Str = YacasExecutor.simplify(compl1.toString());
			            String compl2Str = YacasExecutor.simplify(compl2.toString());
			            ((FormulaContext)_localctx).resultComplexity =  calculateResultComplexity(compl1Str, compl2Str);
			            //System.out.println("result: "+_localctx.resultComplexity.toString());
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
		public int numA;
		public int numB;
		public int numC;
		public int numD;
		public int numAInit;
		public int numBInit;
		public int numCInit;
		public int numDInit;
		public Pair<String,String> exprAInit;
		public Pair<String,String> exprBInit;
		public Pair<String,String> exprCInit;
		public Pair<String,String> exprDInit;
		public StringBuilder formulas;
		public Set<String> vars;
		public ExprSequenceContext e1;
		public Segment1Context s1;
		public ExprSequenceContext e2;
		public Segment1Context s2;
		public ExprSequenceContext exprSequence(int i) {
			return getRuleContext(ExprSequenceContext.class,i);
		}
		public List<ExprSequenceContext> exprSequence() {
			return getRuleContexts(ExprSequenceContext.class);
		}
		public List<Segment1Context> segment1() {
			return getRuleContexts(Segment1Context.class);
		}
		public Segment1Context segment1(int i) {
			return getRuleContext(Segment1Context.class,i);
		}
		public SegmentSequenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_segmentSequence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SequenceSumsListener ) ((SequenceSumsListener)listener).enterSegmentSequence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SequenceSumsListener ) ((SequenceSumsListener)listener).exitSegmentSequence(this);
		}
	}

	public final SegmentSequenceContext segmentSequence() throws RecognitionException {
		SegmentSequenceContext _localctx = new SegmentSequenceContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_segmentSequence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			((SegmentSequenceContext)_localctx).valid =  true;
			setState(31); ((SegmentSequenceContext)_localctx).e1 = exprSequence();
			setState(32); ((SegmentSequenceContext)_localctx).s1 = segment1();
			setState(33); ((SegmentSequenceContext)_localctx).e2 = exprSequence();
			setState(34); ((SegmentSequenceContext)_localctx).s2 = segment1();

			        // e1 must not change a, b, c and d bounds, except initializations of a,b and c
			        for(Pair<String,String> e:((SegmentSequenceContext)_localctx).e1.exprs){
			            if(e.getValue0().equals(((SegmentSequenceContext)_localctx).s1.a)){
			                _localctx.numA++;
			                ((SegmentSequenceContext)_localctx).exprAInit =  e;
			            }
			            if(e.getValue0().equals(((SegmentSequenceContext)_localctx).s1.b)){
			                _localctx.numB++;
			                ((SegmentSequenceContext)_localctx).exprBInit =  e;
			            }
			            if(e.getValue0().equals(((SegmentSequenceContext)_localctx).s2.a)){
			                _localctx.numC++;
			                ((SegmentSequenceContext)_localctx).exprCInit =  e;
			            }
			            if(e.getValue0().equals(((SegmentSequenceContext)_localctx).s2.b)){
			                _localctx.numD++;
			                ((SegmentSequenceContext)_localctx).exprDInit =  e;
			            }
			        }
			        if(_localctx.numA==1){
			            if(_localctx.exprAInit.getValue1().equals("1")){
			                ((SegmentSequenceContext)_localctx).valid =  true;
			            }else if(!_localctx.exprAInit.getValue1().contains("expr")){
			                String var = _localctx.exprAInit.getValue1();
			                for(Pair<String,String> e:((SegmentSequenceContext)_localctx).e1.exprs){
			                    if(e.getValue0().equals(var) && 
			                        (e.getValue1().equals("1") || e.getValue1().toLowerCase().equals("n"))){
			                        ((SegmentSequenceContext)_localctx).valid =  true;
			                        break;
			                    }else{
			                        ((SegmentSequenceContext)_localctx).valid =  false;
			                        //System.out.println("valid16 false");
			                    }
			                }
			            }
			            else{
			                ((SegmentSequenceContext)_localctx).valid =  false;
			              //  System.out.println("valid1 false");
			            }
			        }else{
			            ((SegmentSequenceContext)_localctx).valid =  false;
			           // System.out.println("valid2 false");
			        }
			        if(_localctx.numB==1){
			            if(_localctx.exprBInit.getValue1().toLowerCase().equals("n")){
			                ((SegmentSequenceContext)_localctx).valid =  _localctx.valid && true;
			            }else if(!_localctx.exprBInit.getValue1().contains("expr")){
			                String var = _localctx.exprBInit.getValue1();
			                for(Pair<String,String> e:((SegmentSequenceContext)_localctx).e1.exprs){
			                    if(e.getValue0().equals(var) && 
			                        (e.getValue1().equals("1") || e.getValue1().toLowerCase().equals("n"))){
			                        ((SegmentSequenceContext)_localctx).valid =  true;
			                        break;
			                    }else{
			                        ((SegmentSequenceContext)_localctx).valid =  false;
			                        //System.out.println("valid17 false");
			                    }
			                }
			            }
			            else{
			                ((SegmentSequenceContext)_localctx).valid =  false;
			                //System.out.println("valid3 false");
			            }
			        }else{
			            ((SegmentSequenceContext)_localctx).valid =  false;
			           // System.out.println("valid4 false");
			        }
			        if(_localctx.numC>0){
			            ((SegmentSequenceContext)_localctx).valid =  false;
			           // System.out.println("valid5 false");
			        }
			        if(_localctx.numD > 0){
			            ((SegmentSequenceContext)_localctx).valid =  false;
			           // System.out.println("valid6 false");
			        }

			        // if e2 changes c, it must be init.
			        ((SegmentSequenceContext)_localctx).numC =  0; ((SegmentSequenceContext)_localctx).numD =  0;
			        for(Pair<String,String> e:((SegmentSequenceContext)_localctx).e2.exprs){
			            if(e.getValue0().equals(((SegmentSequenceContext)_localctx).s2.a)){
			                _localctx.numC++;
			                ((SegmentSequenceContext)_localctx).exprCInit =  e;
			            }
			            if(e.getValue0().equals(((SegmentSequenceContext)_localctx).s2.b)){
			                _localctx.numD++;
			                ((SegmentSequenceContext)_localctx).exprDInit =  e;
			            }
			            
			        }
			        if(_localctx.numC == 1){
			            if(!_localctx.exprCInit.getValue1().equals("1")){
			                ((SegmentSequenceContext)_localctx).valid =  false;
			              //  System.out.println("valid7 false");
			            }
			        }else {
			            ((SegmentSequenceContext)_localctx).valid =  false;
			           // System.out.println("valid8 false");
			        }
			        // check for s expression in segment s1
			        if(_localctx.valid){
			            ((SegmentSequenceContext)_localctx).valid =  false;
			            ((SegmentSequenceContext)_localctx).vars =  new HashSet<>();
			            Cloner cloner = new Cloner();
			      
			            for(Pair<String,String> e : ((SegmentSequenceContext)_localctx).s1.exprs){
			                ((SegmentSequenceContext)_localctx).formulas =  new StringBuilder();
			                // s=s+k*i
			                if(!_localctx.valid){
			                    //System.out.println("valid18 false");
			                    _localctx.formulas.append("expr(s,add(s,mul(k,i)))");
			                    _localctx.formulas.append("expr(").append(e.getValue0()).append(",");
			                    _localctx.formulas.append(e.getValue1()).append(")");
			                    _localctx.vars.add("s"); _localctx.vars.add("k"); _localctx.vars.add("i");
			                    ((SegmentSequenceContext)_localctx).valid =  checkExpression(_localctx.formulas, _localctx.vars);

			                    ((SegmentSequenceContext)_localctx).valid =  _localctx.valid && checkK(1);
			                    if(_localctx.valid){
			                        //f(n)^2
			                        ExpNode ex = new ExpNode(new GroupNode(compl1.getRoot()), new NumNode(2));
			                        sCompl = new Complexity(ex);
			                    }
			                }
			                
			                if(!_localctx.valid){ //s=s*k*i
			                   // System.out.println("valid9 false");
			                    ((SegmentSequenceContext)_localctx).formulas =  new StringBuilder();
			                    _localctx.formulas.append("expr(s,mul(mul(s,k),i))");
			                    _localctx.formulas.append("expr(").append(e.getValue0()).append(",");
			                    _localctx.formulas.append(e.getValue1()).append(")");
			                    _localctx.vars.clear();
			                    _localctx.vars.add("s"); _localctx.vars.add("k"); _localctx.vars.add("i");
			                    ((SegmentSequenceContext)_localctx).valid =  checkExpression(_localctx.formulas, _localctx.vars);
			                    
			                    ((SegmentSequenceContext)_localctx).valid =  _localctx.valid && checkK(1);
			                    
			                    if(_localctx.valid){
			                        // f(n)!
			                        FactNode f = new FactNode(new GroupNode(compl1.getRoot()));
			                        sCompl = new Complexity(f);
			                    }
			                }
			                if(!_localctx.valid){//s=s+k
			                   // System.out.println("valid10 false");
			                    ((SegmentSequenceContext)_localctx).formulas =  new StringBuilder();
			                    _localctx.formulas.append("expr(s,add(s,k))");
			                    _localctx.formulas.append("expr(").append(e.getValue0()).append(",");
			                    _localctx.formulas.append(e.getValue1()).append(")");
			                    _localctx.vars.clear();
			                    _localctx.vars.add("s"); _localctx.vars.add("k");
			                    ((SegmentSequenceContext)_localctx).valid =  checkExpression(_localctx.formulas, _localctx.vars);

			                    ((SegmentSequenceContext)_localctx).valid =  _localctx.valid && checkK(0);
			                    if(_localctx.valid){
			                        // f(n)
			                        sCompl = compl1;
			                    }
			                }
			                if(!_localctx.valid){//s=s*k
			                   // System.out.println("valid11 false");
			                    ((SegmentSequenceContext)_localctx).formulas =  new StringBuilder();
			                    _localctx.formulas.append("expr(s,mul(s,k))");
			                    _localctx.formulas.append("expr(").append(e.getValue0()).append(",");
			                    _localctx.formulas.append(e.getValue1()).append(")");
			                    _localctx.vars.clear();
			                    _localctx.vars.add("s"); _localctx.vars.add("k");
			                    ((SegmentSequenceContext)_localctx).valid =  checkExpression(_localctx.formulas, _localctx.vars);

			                    ((SegmentSequenceContext)_localctx).valid =  _localctx.valid && checkK(1);
			                    if(_localctx.valid){//k^f(n)
			                        int k = Integer.parseInt(getSolution("k"));
			                        ExpNode ex = new ExpNode(new NumNode(k), new GroupNode(compl1.getRoot()));
			                        sCompl = new Complexity(ex);
			                    }
			                }
			                if(_localctx.valid){
			                    // s must not be a,b and c
			                    String solS = getSolution("s");
			                    if(solS.equals(((SegmentSequenceContext)_localctx).s1.a) || solS.equals(((SegmentSequenceContext)_localctx).s1.b) || solS.equals(((SegmentSequenceContext)_localctx).s2.a)){
			                      // System.out.println("valid12 false");
			                        ((SegmentSequenceContext)_localctx).valid =  false;
			                    }else {
			                    // if e2 changes d, it must be d=s
			                        if(_localctx.numD==1){
			                            if(!_localctx.exprDInit.getValue1().equals(solS)){
			                                ((SegmentSequenceContext)_localctx).valid =  false;
			                               //System.out.println("valid13 false");
			                            }
			                        }else if(_localctx.numD==0){
			                            if(!solS.equals(((SegmentSequenceContext)_localctx).s2.b)){
			                                ((SegmentSequenceContext)_localctx).valid =  false;
			                                //System.out.println("valid14 false");
			                            }
			                        }else{
			                            ((SegmentSequenceContext)_localctx).valid =  false;
			                           // System.out.println("valid15 false");
			                        }
			                    }
			                }
			                if(_localctx.valid){
			                    //g(s)
			                    GroupNode g = new GroupNode(sCompl.getRoot());
			                    g = cloner.deepClone(g);
			                    
			                    //System.out.println("compl2 old "+compl2);
			                    compl2.applySubfunction (g, "n");
			                    break;
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

	public static class Segment1Context extends ParserRuleContext {
		public List<Pair<String,String>> exprs;
		public String a;
		public String b;
		public WhileSegmentContext w;
		public WhileSegmentContext whileSegment() {
			return getRuleContext(WhileSegmentContext.class,0);
		}
		public Segment1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_segment1; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SequenceSumsListener ) ((SequenceSumsListener)listener).enterSegment1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SequenceSumsListener ) ((SequenceSumsListener)listener).exitSegment1(this);
		}
	}

	public final Segment1Context segment1() throws RecognitionException {
		Segment1Context _localctx = new Segment1Context(_ctx, getState());
		enterRule(_localctx, 4, RULE_segment1);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37); ((Segment1Context)_localctx).w = whileSegment();

			        ((Segment1Context)_localctx).exprs =  ((Segment1Context)_localctx).w.exprs;
			        ((Segment1Context)_localctx).a =  ((Segment1Context)_localctx).w.l;
			        ((Segment1Context)_localctx).b =  ((Segment1Context)_localctx).w.r;
			    
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
		public TerminalNode LPAREN() { return getToken(SequenceSumsParser.LPAREN, 0); }
		public TerminalNode COMMA() { return getToken(SequenceSumsParser.COMMA, 0); }
		public RightSideContext rightSide() {
			return getRuleContext(RightSideContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(SequenceSumsParser.RPAREN, 0); }
		public TerminalNode EXPR() { return getToken(SequenceSumsParser.EXPR, 0); }
		public LeftSideContext leftSide() {
			return getRuleContext(LeftSideContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SequenceSumsListener ) ((SequenceSumsListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SequenceSumsListener ) ((SequenceSumsListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40); match(EXPR);
			setState(41); match(LPAREN);
			setState(42); ((ExpressionContext)_localctx).ls1 = leftSide();
			((ExpressionContext)_localctx).ls =  (((ExpressionContext)_localctx).ls1!=null?_input.getText(((ExpressionContext)_localctx).ls1.start,((ExpressionContext)_localctx).ls1.stop):null);
			setState(44); match(COMMA);
			setState(45); ((ExpressionContext)_localctx).rs1 = rightSide();

			        ((ExpressionContext)_localctx).rs =  (((ExpressionContext)_localctx).rs1!=null?_input.getText(((ExpressionContext)_localctx).rs1.start,((ExpressionContext)_localctx).rs1.stop):null);
			    
			setState(47); match(RPAREN);
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
			if ( listener instanceof SequenceSumsListener ) ((SequenceSumsListener)listener).enterLeftSide(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SequenceSumsListener ) ((SequenceSumsListener)listener).exitLeftSide(this);
		}
	}

	public final LeftSideContext leftSide() throws RecognitionException {
		LeftSideContext _localctx = new LeftSideContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_leftSide);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49); variable();
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
		public TerminalNode LPAREN() { return getToken(SequenceSumsParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(SequenceSumsParser.RPAREN, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public RightSideContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rightSide; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SequenceSumsListener ) ((SequenceSumsListener)listener).enterRightSide(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SequenceSumsListener ) ((SequenceSumsListener)listener).exitRightSide(this);
		}
	}

	public final RightSideContext rightSide() throws RecognitionException {
		RightSideContext _localctx = new RightSideContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_rightSide);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51); variable();
			setState(56);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(52); match(LPAREN);
				setState(53); argList();
				setState(54); match(RPAREN);
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
		public List<TerminalNode> COMMA() { return getTokens(SequenceSumsParser.COMMA); }
		public List<ArgContext> arg() {
			return getRuleContexts(ArgContext.class);
		}
		public TerminalNode COMMA(int i) {
			return getToken(SequenceSumsParser.COMMA, i);
		}
		public ArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SequenceSumsListener ) ((SequenceSumsListener)listener).enterArgList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SequenceSumsListener ) ((SequenceSumsListener)listener).exitArgList(this);
		}
	}

	public final ArgListContext argList() throws RecognitionException {
		ArgListContext _localctx = new ArgListContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_argList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58); ((ArgListContext)_localctx).a = arg();
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(59); match(COMMA);
				setState(60); ((ArgListContext)_localctx).a1 = arg();
				}
				}
				setState(65);
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
			if ( listener instanceof SequenceSumsListener ) ((SequenceSumsListener)listener).enterArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SequenceSumsListener ) ((SequenceSumsListener)listener).exitArg(this);
		}
	}

	public final ArgContext arg() throws RecognitionException {
		ArgContext _localctx = new ArgContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_arg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66); ((ArgContext)_localctx).rs = rightSide();
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
		public List<Pair<String,String>> exprs;
		public String l;
		public String r;
		public LeftVarContext ls;
		public RightVarContext rs;
		public ExprSequenceContext s;
		public LeftVarContext leftVar() {
			return getRuleContext(LeftVarContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(SequenceSumsParser.LPAREN, 0); }
		public RightVarContext rightVar() {
			return getRuleContext(RightVarContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(SequenceSumsParser.COMMA); }
		public TerminalNode RPAREN() { return getToken(SequenceSumsParser.RPAREN, 0); }
		public ExprSequenceContext exprSequence() {
			return getRuleContext(ExprSequenceContext.class,0);
		}
		public TerminalNode WHILE() { return getToken(SequenceSumsParser.WHILE, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(SequenceSumsParser.COMMA, i);
		}
		public WhileSegmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileSegment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SequenceSumsListener ) ((SequenceSumsListener)listener).enterWhileSegment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SequenceSumsListener ) ((SequenceSumsListener)listener).exitWhileSegment(this);
		}
	}

	public final WhileSegmentContext whileSegment() throws RecognitionException {
		WhileSegmentContext _localctx = new WhileSegmentContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_whileSegment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68); match(WHILE);
			setState(69); match(LPAREN);
			setState(70); ((WhileSegmentContext)_localctx).ls = leftVar();
			setState(71); match(COMMA);
			setState(72); ((WhileSegmentContext)_localctx).rs = rightVar();

			        ((WhileSegmentContext)_localctx).l =  (((WhileSegmentContext)_localctx).ls!=null?_input.getText(((WhileSegmentContext)_localctx).ls.start,((WhileSegmentContext)_localctx).ls.stop):null);
			        ((WhileSegmentContext)_localctx).r =  (((WhileSegmentContext)_localctx).rs!=null?_input.getText(((WhileSegmentContext)_localctx).rs.start,((WhileSegmentContext)_localctx).rs.stop):null);
			    
			setState(74); match(COMMA);
			setState(75); ((WhileSegmentContext)_localctx).s = exprSequence();
			 ((WhileSegmentContext)_localctx).exprs =  ((WhileSegmentContext)_localctx).s.exprs; 
			setState(77); match(RPAREN);
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
			if ( listener instanceof SequenceSumsListener ) ((SequenceSumsListener)listener).enterExprSequence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SequenceSumsListener ) ((SequenceSumsListener)listener).exitExprSequence(this);
		}
	}

	public final ExprSequenceContext exprSequence() throws RecognitionException {
		ExprSequenceContext _localctx = new ExprSequenceContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_exprSequence);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79); ((ExprSequenceContext)_localctx).e1 = expression();

			        ((ExprSequenceContext)_localctx).exprs =  new ArrayList<>();
			        Pair<String,String> p = new Pair(((ExprSequenceContext)_localctx).e1.ls,((ExprSequenceContext)_localctx).e1.rs);
			        if(((ExprSequenceContext)_localctx).e1.ls != null && ((ExprSequenceContext)_localctx).e1.rs !=null)
			            _localctx.exprs.add(p);
			    
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EXPR) {
				{
				{
				setState(81); ((ExprSequenceContext)_localctx).e2 = expression();

				        p = new Pair(((ExprSequenceContext)_localctx).e2.ls,((ExprSequenceContext)_localctx).e2.rs);
				        _localctx.exprs.add(p);
				    
				}
				}
				setState(88);
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
			if ( listener instanceof SequenceSumsListener ) ((SequenceSumsListener)listener).enterLeftVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SequenceSumsListener ) ((SequenceSumsListener)listener).exitLeftVar(this);
		}
	}

	public final LeftVarContext leftVar() throws RecognitionException {
		LeftVarContext _localctx = new LeftVarContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_leftVar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89); variable();
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
			if ( listener instanceof SequenceSumsListener ) ((SequenceSumsListener)listener).enterRightVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SequenceSumsListener ) ((SequenceSumsListener)listener).exitRightVar(this);
		}
	}

	public final RightVarContext rightVar() throws RecognitionException {
		RightVarContext _localctx = new RightVarContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_rightVar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91); variable();
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
		public TerminalNode IDENT() { return getToken(SequenceSumsParser.IDENT, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SequenceSumsListener ) ((SequenceSumsListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SequenceSumsListener ) ((SequenceSumsListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93); ((VariableContext)_localctx).i = match(IDENT);

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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\fc\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7"+
		"\3\7\5\7;\n\7\3\b\3\b\3\b\7\b@\n\b\f\b\16\bC\13\b\3\t\3\t\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\7\13W\n\13\f"+
		"\13\16\13Z\13\13\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\16\2\2\17\2\4\6\b\n"+
		"\f\16\20\22\24\26\30\32\2\2X\2\34\3\2\2\2\4 \3\2\2\2\6\'\3\2\2\2\b*\3"+
		"\2\2\2\n\63\3\2\2\2\f\65\3\2\2\2\16<\3\2\2\2\20D\3\2\2\2\22F\3\2\2\2\24"+
		"Q\3\2\2\2\26[\3\2\2\2\30]\3\2\2\2\32_\3\2\2\2\34\35\b\2\1\2\35\36\5\4"+
		"\3\2\36\37\b\2\1\2\37\3\3\2\2\2 !\b\3\1\2!\"\5\24\13\2\"#\5\6\4\2#$\5"+
		"\24\13\2$%\5\6\4\2%&\b\3\1\2&\5\3\2\2\2\'(\5\22\n\2()\b\4\1\2)\7\3\2\2"+
		"\2*+\7\4\2\2+,\7\b\2\2,-\5\n\6\2-.\b\5\1\2./\7\n\2\2/\60\5\f\7\2\60\61"+
		"\b\5\1\2\61\62\7\t\2\2\62\t\3\2\2\2\63\64\5\32\16\2\64\13\3\2\2\2\65:"+
		"\5\32\16\2\66\67\7\b\2\2\678\5\16\b\289\7\t\2\29;\3\2\2\2:\66\3\2\2\2"+
		":;\3\2\2\2;\r\3\2\2\2<A\5\20\t\2=>\7\n\2\2>@\5\20\t\2?=\3\2\2\2@C\3\2"+
		"\2\2A?\3\2\2\2AB\3\2\2\2B\17\3\2\2\2CA\3\2\2\2DE\5\f\7\2E\21\3\2\2\2F"+
		"G\7\13\2\2GH\7\b\2\2HI\5\26\f\2IJ\7\n\2\2JK\5\30\r\2KL\b\n\1\2LM\7\n\2"+
		"\2MN\5\24\13\2NO\b\n\1\2OP\7\t\2\2P\23\3\2\2\2QR\5\b\5\2RX\b\13\1\2ST"+
		"\5\b\5\2TU\b\13\1\2UW\3\2\2\2VS\3\2\2\2WZ\3\2\2\2XV\3\2\2\2XY\3\2\2\2"+
		"Y\25\3\2\2\2ZX\3\2\2\2[\\\5\32\16\2\\\27\3\2\2\2]^\5\32\16\2^\31\3\2\2"+
		"\2_`\7\f\2\2`a\b\16\1\2a\33\3\2\2\2\5:AX";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}