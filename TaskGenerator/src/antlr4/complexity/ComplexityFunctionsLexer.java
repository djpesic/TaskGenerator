// Generated from ComplexityFunctions.g4 by ANTLR 4.4

package complexity;
import java.util.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ComplexityFunctionsLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__2=1, T__1=2, T__0=3, LOG=4, SQRT=5, LPAREN=6, RPAREN=7, TIMES=8, POW=9, 
		LETTER=10, DIGIT=11, WS=12;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'"
	};
	public static final String[] ruleNames = {
		"T__2", "T__1", "T__0", "LOG", "SQRT", "LPAREN", "RPAREN", "TIMES", "POW", 
		"LETTER", "DIGIT", "WS"
	};


	public ComplexityFunctionsLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ComplexityFunctions.g4"; }

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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\16?\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3"+
		"\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\5\138"+
		"\n\13\3\f\3\f\3\r\3\r\3\r\3\r\2\2\16\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\f\27\r\31\16\3\2\4\4\2C\\c|\5\2\13\f\17\17\"\">\2\3\3\2\2"+
		"\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3"+
		"\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2"+
		"\2\3\33\3\2\2\2\5\35\3\2\2\2\7\37\3\2\2\2\t%\3\2\2\2\13)\3\2\2\2\r.\3"+
		"\2\2\2\17\60\3\2\2\2\21\62\3\2\2\2\23\64\3\2\2\2\25\67\3\2\2\2\279\3\2"+
		"\2\2\31;\3\2\2\2\33\34\7\61\2\2\34\4\3\2\2\2\35\36\7#\2\2\36\6\3\2\2\2"+
		"\37 \7e\2\2 !\7q\2\2!\"\7p\2\2\"#\7u\2\2#$\7v\2\2$\b\3\2\2\2%&\7n\2\2"+
		"&\'\7q\2\2\'(\7i\2\2(\n\3\2\2\2)*\7U\2\2*+\7s\2\2+,\7t\2\2,-\7v\2\2-\f"+
		"\3\2\2\2./\7*\2\2/\16\3\2\2\2\60\61\7+\2\2\61\20\3\2\2\2\62\63\7,\2\2"+
		"\63\22\3\2\2\2\64\65\7`\2\2\65\24\3\2\2\2\668\t\2\2\2\67\66\3\2\2\28\26"+
		"\3\2\2\29:\4\62;\2:\30\3\2\2\2;<\t\3\2\2<=\3\2\2\2=>\b\r\2\2>\32\3\2\2"+
		"\2\4\2\67\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}