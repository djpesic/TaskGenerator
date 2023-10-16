// Generated from SimpleExpression.g4 by ANTLR 4.4

package expression;
import common.*;
import programSegment.*;
import java.util.*;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SimpleExpressionParser}.
 */
public interface SimpleExpressionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SimpleExpressionParser#entireVar}.
	 * @param ctx the parse tree
	 */
	void enterEntireVar(@NotNull SimpleExpressionParser.EntireVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleExpressionParser#entireVar}.
	 * @param ctx the parse tree
	 */
	void exitEntireVar(@NotNull SimpleExpressionParser.EntireVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleExpressionParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(@NotNull SimpleExpressionParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleExpressionParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(@NotNull SimpleExpressionParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleExpressionParser#mulop}.
	 * @param ctx the parse tree
	 */
	void enterMulop(@NotNull SimpleExpressionParser.MulopContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleExpressionParser#mulop}.
	 * @param ctx the parse tree
	 */
	void exitMulop(@NotNull SimpleExpressionParser.MulopContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleExpressionParser#indexedVar}.
	 * @param ctx the parse tree
	 */
	void enterIndexedVar(@NotNull SimpleExpressionParser.IndexedVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleExpressionParser#indexedVar}.
	 * @param ctx the parse tree
	 */
	void exitIndexedVar(@NotNull SimpleExpressionParser.IndexedVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleExpressionParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(@NotNull SimpleExpressionParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleExpressionParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(@NotNull SimpleExpressionParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleExpressionParser#signedFactor}.
	 * @param ctx the parse tree
	 */
	void enterSignedFactor(@NotNull SimpleExpressionParser.SignedFactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleExpressionParser#signedFactor}.
	 * @param ctx the parse tree
	 */
	void exitSignedFactor(@NotNull SimpleExpressionParser.SignedFactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleExpressionParser#addop}.
	 * @param ctx the parse tree
	 */
	void enterAddop(@NotNull SimpleExpressionParser.AddopContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleExpressionParser#addop}.
	 * @param ctx the parse tree
	 */
	void exitAddop(@NotNull SimpleExpressionParser.AddopContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleExpressionParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(@NotNull SimpleExpressionParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleExpressionParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(@NotNull SimpleExpressionParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleExpressionParser#simpleExpression}.
	 * @param ctx the parse tree
	 */
	void enterSimpleExpression(@NotNull SimpleExpressionParser.SimpleExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleExpressionParser#simpleExpression}.
	 * @param ctx the parse tree
	 */
	void exitSimpleExpression(@NotNull SimpleExpressionParser.SimpleExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleExpressionParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(@NotNull SimpleExpressionParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleExpressionParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(@NotNull SimpleExpressionParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleExpressionParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(@NotNull SimpleExpressionParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleExpressionParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(@NotNull SimpleExpressionParser.FactorContext ctx);
}