// Generated from MultiEqBuilder.g4 by ANTLR 4.4

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
public class MultiEqBuilderParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, LPAREN=2, RPAREN=3, COMMA=4, WHILE=5, IDENT=6;
	public static final String[] tokenNames = {
		"<INVALID>", "WS", "'('", "')'", "','", "'while'", "IDENT"
	};
	public static final int
		RULE_sequence = 0, RULE_formula = 1, RULE_argList = 2, RULE_arg = 3, RULE_variable = 4;
	public static final String[] ruleNames = {
		"sequence", "formula", "argList", "arg", "variable"
	};

	@Override
	public String getGrammarFileName() { return "MultiEqBuilder.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    public Set<String> multiEqVars;
	    private <T> List<List<T>> transpose(List<List<T>> matrix) {
	        //all rows has the same length
	        List<List<T>> result = new ArrayList<>();
	        if(matrix.isEmpty())return result;
	        int rowSize = matrix.get(0).size();
	        for(int i=0;i<rowSize;i++){
	            List<T> resultRow = new ArrayList<>();
	            for(int j=0;j<matrix.size();j++){
	                resultRow.add(matrix.get(j).get(i));
	            }
	            result.add(resultRow);
	        }   
	        return result;
	    }

	    private void processFunction(List<List<String>> transposedFormulas,
	    StringBuilder multiEquationSystem) throws UnifyException {
	        if (transposedFormulas.isEmpty()) {
	            return;
	        }
	        if (transposedFormulas.get(0).isEmpty()) {
	            return;
	        }
	        String funcName = transposedFormulas.get(0).get(0);
	        for (int i = 1; i < transposedFormulas.get(0).size(); i++) {
	            String name = transposedFormulas.get(0).get(i);
	            if (!funcName.equals(name)) {
	                throw new UnifyException(UnifyException.CLASH, funcName, name);
	            }
	        }
	        multiEquationSystem.append(funcName).append("(");
	        transposedFormulas.remove(0);
	        for (int i = 0; i < argNumber(funcName); i++) {
	            processArgument(transposedFormulas,multiEquationSystem);
	        }
	        deleteLastChar(multiEquationSystem, ',');
	        multiEquationSystem.append(")");
	    }

	    private void deleteLastChar(StringBuilder s, char c) {
	        if (s.charAt(s.length() - 1) == c) {
	            s.deleteCharAt(s.length() - 1);
	        }
	    }

	    private void processArgument(List<List<String>> transposedFormulas,
	    StringBuilder multiEquationSystem) throws UnifyException {
	        if (transposedFormulas.isEmpty()) {
	            return;
	        }
	        List<String> arg = transposedFormulas.get(0);
	        Set<String> vars = new HashSet<>();
	        Set<String> funcs = new HashSet<>();
	        String func = "";
	        List<String> consts = new LinkedList<>();
	        for (int i = 0; i < arg.size();) {
	            String s = arg.get(i);
	            if (multiEqVars.contains(s)) {
	                vars.add(s);
	            } else if (isFunction(s)) {
	                funcs.add(s);
	                func = s;
	                if (funcs.size()>1) {
	                    String s1 = (String)funcs.toArray()[0];
	                    String s2 = (String)funcs.toArray()[1];
	                    throw new UnifyException(UnifyException.CLASH, s1, s2);
	                }
	            } else {
	                consts.add(s);
	            }
	            arg.remove(0);
	        }
	        transposedFormulas.remove(0);
	        multiEquationSystem.append("<");
	        if (!func.isEmpty()) {
	            multiEquationSystem.append("{");
	            vars.forEach((var) -> multiEquationSystem.append(var).append(","));
	            consts.forEach((cnst) -> multiEquationSystem.append(cnst).append(","));
	            deleteLastChar(multiEquationSystem, ',');
	            multiEquationSystem.append("},");
	            List<String> l = new ArrayList<>();
	            l.add(func);
	            transposedFormulas.add(0, l);
	            processFunction(transposedFormulas,multiEquationSystem);
	        } else {
	            if (consts.size() > 1) {
	                throw new UnifyException(UnifyException.IMPROPER_VAR_DECL);
	            }
	            multiEquationSystem.append("{");
	            vars.forEach((var) -> multiEquationSystem.append(var).append(","));
	            deleteLastChar(multiEquationSystem, ',');
	            multiEquationSystem.append("},");
	            consts.forEach((cnst) -> multiEquationSystem.append(cnst).append(","));
	            if (!consts.isEmpty()) {
	                deleteLastChar(multiEquationSystem, ',');
	            }
	        }

	        multiEquationSystem.append(">,");
	    }
	    private boolean isFunction(String s) {
	        switch (s) {
	            case "add":
	                return true;
	            case "sub":
	                return true;
	            case "mul":
	                return true;
	            case "divI":
	                return true;
	            case "and":
	                return true;
	            case "or":
	                return true;
	            case "mod":
	                return true;
	            case "not":
	                return true;
	            case "divF":
	                return true;
	            case "index":
	                return true;
	            case "expr":
	                return true;
	            default:
	                return false;
	        }
	    }
	    private int argNumber(String s) {
	        switch (s) {
	            case "add":
	                return 2;
	            case "sub":
	                return 2;
	            case "mul":
	                return 2;
	            case "divI":
	                return 2;
	            case "and":
	                return 2;
	            case "or":
	                return 2;
	            case "mod":
	                return 2;
	            case "not":
	                return 1;
	            case "divF":
	                return 2;
	            case "index":
	                return 1;
	            case "expr":
	                return 2;
	            default:
	                return 0;
	        }
	    }

	    private void expand(List<String> l1, List<String> l2) throws UnifyException{
	        List<String> longer, shorter;
	        if(l1.size() > l2.size()){
	            longer = l1;
	            shorter = l2;
	        }else{
	            longer = l2;
	            shorter = l1;
	        }
	        for(int i = 0; i < longer.size(); i++){
	            String s1 = longer.get(i);
	            String s2 = shorter.get(i);
	            if(isFunction(s1) && isFunction(s2)){
	                if(!s1.equals(s2)){
	                    throw new UnifyException(UnifyException.CLASH, s1, s2);
	                }
	            }else if(!isFunction(s1) &&  !isFunction(s2)){
	                //both vars, do nothing
	            }else{
	                int num;
	                if(isFunction(s1)){
	                    num = argNumber(s1);
	                    for(int j = 0; j < num; j++){
	                        shorter.add(i+1, "_");
	                    }
	                }
	                else if(isFunction(s2)){
	                    num = argNumber(s2);
	                    for(int j = 0; j < num; j++){
	                        longer.add(i+1, "_");
	                    }
	                }
	            }
	        }   
	    }

	public MultiEqBuilderParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class SequenceContext extends ParserRuleContext {
		public StringBuilder multiEquationSystem;
		public int diff;
		public List<String> args;
		public List<String> args1;
		public List<List<String>> splitted;
		public int maxLen;
		public List<String> l;
		public List<List<String>> transposedSplitted;
		public String s;
		public FormulaContext f1;
		public FormulaContext f;
		public List<FormulaContext> formula() {
			return getRuleContexts(FormulaContext.class);
		}
		public FormulaContext formula(int i) {
			return getRuleContext(FormulaContext.class,i);
		}
		public SequenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sequence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MultiEqBuilderListener ) ((MultiEqBuilderListener)listener).enterSequence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MultiEqBuilderListener ) ((MultiEqBuilderListener)listener).exitSequence(this);
		}
	}

	public final SequenceContext sequence() throws RecognitionException {
		SequenceContext _localctx = new SequenceContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_sequence);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((SequenceContext)_localctx).multiEquationSystem =  new StringBuilder();
			setState(11); ((SequenceContext)_localctx).f1 = formula();

			        ((SequenceContext)_localctx).args1 =  ((SequenceContext)_localctx).f1.args;
			        ((SequenceContext)_localctx).splitted =  new ArrayList<>();
			        _localctx.splitted.add(_localctx.args1);
			        ((SequenceContext)_localctx).maxLen =  _localctx.args1.size();
			    
			setState(18);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IDENT) {
				{
				{
				setState(13); ((SequenceContext)_localctx).f = formula();

				        try{
				            ((SequenceContext)_localctx).args1 =  _localctx.splitted.get(_localctx.splitted.size()-1);
				            ((SequenceContext)_localctx).args =  ((SequenceContext)_localctx).f.args;
				            _localctx.splitted.add(_localctx.args);
				            if(_localctx.args.size()>_localctx.maxLen){
				                ((SequenceContext)_localctx).maxLen =  _localctx.args.size();
				                for(int i=0;i<_localctx.splitted.size();i++){
				                    ((SequenceContext)_localctx).l =  _localctx.splitted.get(i);
				                    expand(_localctx.l, _localctx.args);
				                }
				            }else if(_localctx.args.size()<_localctx.args1.size()){
				                expand(_localctx.args, _localctx.args1);
				            }   

				        }catch(UnifyException ex){
				            throw new ParseCancellationException(ex);
				        }
				    
				}
				}
				setState(20);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}

			        ((SequenceContext)_localctx).transposedSplitted =  transpose(_localctx.splitted);
			        try{
			            processFunction(_localctx.transposedSplitted, _localctx.multiEquationSystem);
			        }
			        catch(UnifyException ex){
			            throw new ParseCancellationException(ex);
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

	public static class FormulaContext extends ParserRuleContext {
		public List<String> args;
		public String s;
		public VariableContext v;
		public ArgListContext al;
		public ArgListContext argList() {
			return getRuleContext(ArgListContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(MultiEqBuilderParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(MultiEqBuilderParser.RPAREN, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public FormulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formula; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MultiEqBuilderListener ) ((MultiEqBuilderListener)listener).enterFormula(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MultiEqBuilderListener ) ((MultiEqBuilderListener)listener).exitFormula(this);
		}
	}

	public final FormulaContext formula() throws RecognitionException {
		FormulaContext _localctx = new FormulaContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_formula);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(23); ((FormulaContext)_localctx).v = variable();
			setState(24); match(LPAREN);
			setState(25); ((FormulaContext)_localctx).al = argList();
			setState(26); match(RPAREN);

			        ((FormulaContext)_localctx).args = ((FormulaContext)_localctx).al.args;
			        _localctx.args.add(0,((FormulaContext)_localctx).v.var);
			    
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
		public List<String> args;
		public ArgContext a;
		public ArgContext a1;
		public ArgContext arg(int i) {
			return getRuleContext(ArgContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MultiEqBuilderParser.COMMA); }
		public List<ArgContext> arg() {
			return getRuleContexts(ArgContext.class);
		}
		public TerminalNode COMMA(int i) {
			return getToken(MultiEqBuilderParser.COMMA, i);
		}
		public ArgListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MultiEqBuilderListener ) ((MultiEqBuilderListener)listener).enterArgList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MultiEqBuilderListener ) ((MultiEqBuilderListener)listener).exitArgList(this);
		}
	}

	public final ArgListContext argList() throws RecognitionException {
		ArgListContext _localctx = new ArgListContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_argList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29); ((ArgListContext)_localctx).a = arg();

			        ((ArgListContext)_localctx).args =  ((ArgListContext)_localctx).a.argumentList;
			    
			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(31); match(COMMA);
				setState(32); ((ArgListContext)_localctx).a1 = arg();

				        _localctx.args.addAll(((ArgListContext)_localctx).a1.argumentList);
				    
				}
				}
				setState(39);
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
		public List<String> argumentList;
		public String s;
		public FormulaContext f;
		public VariableContext v;
		public FormulaContext formula() {
			return getRuleContext(FormulaContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public ArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MultiEqBuilderListener ) ((MultiEqBuilderListener)listener).enterArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MultiEqBuilderListener ) ((MultiEqBuilderListener)listener).exitArg(this);
		}
	}

	public final ArgContext arg() throws RecognitionException {
		ArgContext _localctx = new ArgContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_arg);
		try {
			setState(46);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(40); ((ArgContext)_localctx).f = formula();

				        ((ArgContext)_localctx).argumentList =  ((ArgContext)_localctx).f.args;
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(43); ((ArgContext)_localctx).v = variable();

				        ((ArgContext)_localctx).argumentList =  new ArrayList<>();
				        _localctx.argumentList.add(((ArgContext)_localctx).v.var);
				    
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

	public static class VariableContext extends ParserRuleContext {
		public String var;
		public Token i;
		public TerminalNode IDENT() { return getToken(MultiEqBuilderParser.IDENT, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MultiEqBuilderListener ) ((MultiEqBuilderListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MultiEqBuilderListener ) ((MultiEqBuilderListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48); ((VariableContext)_localctx).i = match(IDENT);

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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\b\66\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\3\2\3\2\3\2\7\2\23\n\2\f\2\16"+
		"\2\26\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\7\4"+
		"&\n\4\f\4\16\4)\13\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5\61\n\5\3\6\3\6\3\6\3"+
		"\6\2\2\7\2\4\6\b\n\2\2\63\2\f\3\2\2\2\4\31\3\2\2\2\6\37\3\2\2\2\b\60\3"+
		"\2\2\2\n\62\3\2\2\2\f\r\b\2\1\2\r\16\5\4\3\2\16\24\b\2\1\2\17\20\5\4\3"+
		"\2\20\21\b\2\1\2\21\23\3\2\2\2\22\17\3\2\2\2\23\26\3\2\2\2\24\22\3\2\2"+
		"\2\24\25\3\2\2\2\25\27\3\2\2\2\26\24\3\2\2\2\27\30\b\2\1\2\30\3\3\2\2"+
		"\2\31\32\5\n\6\2\32\33\7\4\2\2\33\34\5\6\4\2\34\35\7\5\2\2\35\36\b\3\1"+
		"\2\36\5\3\2\2\2\37 \5\b\5\2 \'\b\4\1\2!\"\7\6\2\2\"#\5\b\5\2#$\b\4\1\2"+
		"$&\3\2\2\2%!\3\2\2\2&)\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2(\7\3\2\2\2)\'\3\2"+
		"\2\2*+\5\4\3\2+,\b\5\1\2,\61\3\2\2\2-.\5\n\6\2./\b\5\1\2/\61\3\2\2\2\60"+
		"*\3\2\2\2\60-\3\2\2\2\61\t\3\2\2\2\62\63\7\b\2\2\63\64\b\6\1\2\64\13\3"+
		"\2\2\2\5\24\'\60";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}