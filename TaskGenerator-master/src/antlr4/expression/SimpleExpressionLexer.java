// Generated from SimpleExpression.g4 by ANTLR 4.4

package expression;
import common.*;
import programSegment.*;
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
public class SimpleExpressionLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__4=1, T__3=2, T__2=3, T__1=4, T__0=5, WS=6, PLUS=7, MINUS=8, OR=9, MUL=10, 
		FLOAT_DIV=11, INT_DIV=12, MOD=13, AND=14, NOT=15, REAL=16, SCALE=17, IDENT=18, 
		STRING_LITERAL=19, INTEGER=20, OPERAND_STUB=21, NUMBER_STUB=22;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'"
	};
	public static final String[] ruleNames = {
		"T__4", "T__3", "T__2", "T__1", "T__0", "WS", "PLUS", "MINUS", "OR", "MUL", 
		"FLOAT_DIV", "INT_DIV", "MOD", "AND", "NOT", "REAL", "SCALE", "IDENT", 
		"STRING_LITERAL", "INTEGER", "OPERAND_STUB", "NUMBER_STUB"
	};


	public SimpleExpressionLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SimpleExpression.g4"; }

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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\30\u0086\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\3\3"+
		"\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n"+
		"\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17"+
		"\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\5\21\\\n\21\3\21\3\21\3\21\3"+
		"\21\3\21\5\21c\n\21\3\22\3\22\3\22\5\22h\n\22\3\22\3\22\3\23\3\23\7\23"+
		"n\n\23\f\23\16\23q\13\23\3\24\3\24\3\24\3\24\7\24w\n\24\f\24\16\24z\13"+
		"\24\3\24\3\24\3\25\6\25\177\n\25\r\25\16\25\u0080\3\26\3\26\3\27\3\27"+
		"\2\2\30\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17"+
		"\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30\3\2\7\5\2\13\f\17\17\"\"\4\2"+
		"GGgg\4\2C\\c|\6\2\62;C\\aac|\3\2))\u008c\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3"+
		"\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2"+
		"\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35"+
		"\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)"+
		"\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\3/\3\2\2\2\5\61\3\2\2\2\7\63\3\2\2\2\t"+
		"\65\3\2\2\2\13\67\3\2\2\2\r9\3\2\2\2\17=\3\2\2\2\21?\3\2\2\2\23A\3\2\2"+
		"\2\25D\3\2\2\2\27F\3\2\2\2\31H\3\2\2\2\33L\3\2\2\2\35P\3\2\2\2\37T\3\2"+
		"\2\2!b\3\2\2\2#d\3\2\2\2%k\3\2\2\2\'r\3\2\2\2)~\3\2\2\2+\u0082\3\2\2\2"+
		"-\u0084\3\2\2\2/\60\7*\2\2\60\4\3\2\2\2\61\62\7+\2\2\62\6\3\2\2\2\63\64"+
		"\7]\2\2\64\b\3\2\2\2\65\66\7.\2\2\66\n\3\2\2\2\678\7_\2\28\f\3\2\2\29"+
		":\t\2\2\2:;\3\2\2\2;<\b\7\2\2<\16\3\2\2\2=>\7-\2\2>\20\3\2\2\2?@\7/\2"+
		"\2@\22\3\2\2\2AB\7q\2\2BC\7t\2\2C\24\3\2\2\2DE\7,\2\2E\26\3\2\2\2FG\7"+
		"\61\2\2G\30\3\2\2\2HI\7f\2\2IJ\7k\2\2JK\7x\2\2K\32\3\2\2\2LM\7o\2\2MN"+
		"\7q\2\2NO\7f\2\2O\34\3\2\2\2PQ\7c\2\2QR\7p\2\2RS\7f\2\2S\36\3\2\2\2TU"+
		"\7p\2\2UV\7q\2\2VW\7v\2\2W \3\2\2\2XY\5)\25\2Y[\7\60\2\2Z\\\5)\25\2[Z"+
		"\3\2\2\2[\\\3\2\2\2\\]\3\2\2\2]^\5#\22\2^c\3\2\2\2_`\5)\25\2`a\5#\22\2"+
		"ac\3\2\2\2bX\3\2\2\2b_\3\2\2\2c\"\3\2\2\2dg\t\3\2\2eh\5\17\b\2fh\5\21"+
		"\t\2ge\3\2\2\2gf\3\2\2\2hi\3\2\2\2ij\5)\25\2j$\3\2\2\2ko\t\4\2\2ln\t\5"+
		"\2\2ml\3\2\2\2nq\3\2\2\2om\3\2\2\2op\3\2\2\2p&\3\2\2\2qo\3\2\2\2rx\7)"+
		"\2\2st\7)\2\2tw\7)\2\2uw\n\6\2\2vs\3\2\2\2vu\3\2\2\2wz\3\2\2\2xv\3\2\2"+
		"\2xy\3\2\2\2y{\3\2\2\2zx\3\2\2\2{|\7)\2\2|(\3\2\2\2}\177\4\62;\2~}\3\2"+
		"\2\2\177\u0080\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081*\3\2\2"+
		"\2\u0082\u0083\7%\2\2\u0083,\3\2\2\2\u0084\u0085\7B\2\2\u0085.\3\2\2\2"+
		"\n\2[bgovx\u0080\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}