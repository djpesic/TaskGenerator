// Generated from NestingRulesSums.g4 by ANTLR 4.4

package dependency.rules.nesting;
import java.util.*;
import complexity.*;
import org.javatuples.Pair;
import org.apache.commons.lang3.math.NumberUtils;
import unification.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class NestingRulesSumsLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, EXPR=2, IF=3, THEN=4, ELSE=5, LPAREN=6, RPAREN=7, COMMA=8, WHILE=9, 
		IDENT=10;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'"
	};
	public static final String[] ruleNames = {
		"WS", "EXPR", "IF", "THEN", "ELSE", "LPAREN", "RPAREN", "COMMA", "WHILE", 
		"IDENT"
	};


	public NestingRulesSumsLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "NestingRulesSums.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\f>\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3"+
		"\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\13\6\13;\n\13\r\13\16\13<\2\2\f\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n"+
		"\23\13\25\f\3\2\4\5\2\13\f\17\17\"\"\6\2\62;C\\aac|>\2\3\3\2\2\2\2\5\3"+
		"\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2"+
		"\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\3\27\3\2\2\2\5\33\3\2\2\2\7 \3\2"+
		"\2\2\t#\3\2\2\2\13(\3\2\2\2\r-\3\2\2\2\17/\3\2\2\2\21\61\3\2\2\2\23\63"+
		"\3\2\2\2\25:\3\2\2\2\27\30\t\2\2\2\30\31\3\2\2\2\31\32\b\2\2\2\32\4\3"+
		"\2\2\2\33\34\7g\2\2\34\35\7z\2\2\35\36\7r\2\2\36\37\7t\2\2\37\6\3\2\2"+
		"\2 !\7k\2\2!\"\7h\2\2\"\b\3\2\2\2#$\7v\2\2$%\7j\2\2%&\7g\2\2&\'\7p\2\2"+
		"\'\n\3\2\2\2()\7g\2\2)*\7n\2\2*+\7u\2\2+,\7g\2\2,\f\3\2\2\2-.\7*\2\2."+
		"\16\3\2\2\2/\60\7+\2\2\60\20\3\2\2\2\61\62\7.\2\2\62\22\3\2\2\2\63\64"+
		"\7y\2\2\64\65\7j\2\2\65\66\7k\2\2\66\67\7n\2\2\678\7g\2\28\24\3\2\2\2"+
		"9;\t\3\2\2:9\3\2\2\2;<\3\2\2\2<:\3\2\2\2<=\3\2\2\2=\26\3\2\2\2\4\2<\3"+
		"\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}