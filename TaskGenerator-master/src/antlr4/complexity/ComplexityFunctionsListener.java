// Generated from ComplexityFunctions.g4 by ANTLR 4.4

package complexity;
import java.util.*;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ComplexityFunctionsParser}.
 */
public interface ComplexityFunctionsListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ComplexityFunctionsParser#complexity}.
	 * @param ctx the parse tree
	 */
	void enterComplexity(@NotNull ComplexityFunctionsParser.ComplexityContext ctx);
	/**
	 * Exit a parse tree produced by {@link ComplexityFunctionsParser#complexity}.
	 * @param ctx the parse tree
	 */
	void exitComplexity(@NotNull ComplexityFunctionsParser.ComplexityContext ctx);
	/**
	 * Enter a parse tree produced by {@link ComplexityFunctionsParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull ComplexityFunctionsParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ComplexityFunctionsParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull ComplexityFunctionsParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ComplexityFunctionsParser#log}.
	 * @param ctx the parse tree
	 */
	void enterLog(@NotNull ComplexityFunctionsParser.LogContext ctx);
	/**
	 * Exit a parse tree produced by {@link ComplexityFunctionsParser#log}.
	 * @param ctx the parse tree
	 */
	void exitLog(@NotNull ComplexityFunctionsParser.LogContext ctx);
	/**
	 * Enter a parse tree produced by {@link ComplexityFunctionsParser#sqrt}.
	 * @param ctx the parse tree
	 */
	void enterSqrt(@NotNull ComplexityFunctionsParser.SqrtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ComplexityFunctionsParser#sqrt}.
	 * @param ctx the parse tree
	 */
	void exitSqrt(@NotNull ComplexityFunctionsParser.SqrtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ComplexityFunctionsParser#powExpression}.
	 * @param ctx the parse tree
	 */
	void enterPowExpression(@NotNull ComplexityFunctionsParser.PowExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ComplexityFunctionsParser#powExpression}.
	 * @param ctx the parse tree
	 */
	void exitPowExpression(@NotNull ComplexityFunctionsParser.PowExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ComplexityFunctionsParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(@NotNull ComplexityFunctionsParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link ComplexityFunctionsParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(@NotNull ComplexityFunctionsParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link ComplexityFunctionsParser#complExpression}.
	 * @param ctx the parse tree
	 */
	void enterComplExpression(@NotNull ComplexityFunctionsParser.ComplExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ComplexityFunctionsParser#complExpression}.
	 * @param ctx the parse tree
	 */
	void exitComplExpression(@NotNull ComplexityFunctionsParser.ComplExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ComplexityFunctionsParser#number1}.
	 * @param ctx the parse tree
	 */
	void enterNumber1(@NotNull ComplexityFunctionsParser.Number1Context ctx);
	/**
	 * Exit a parse tree produced by {@link ComplexityFunctionsParser#number1}.
	 * @param ctx the parse tree
	 */
	void exitNumber1(@NotNull ComplexityFunctionsParser.Number1Context ctx);
	/**
	 * Enter a parse tree produced by {@link ComplexityFunctionsParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(@NotNull ComplexityFunctionsParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link ComplexityFunctionsParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(@NotNull ComplexityFunctionsParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link ComplexityFunctionsParser#subFunc}.
	 * @param ctx the parse tree
	 */
	void enterSubFunc(@NotNull ComplexityFunctionsParser.SubFuncContext ctx);
	/**
	 * Exit a parse tree produced by {@link ComplexityFunctionsParser#subFunc}.
	 * @param ctx the parse tree
	 */
	void exitSubFunc(@NotNull ComplexityFunctionsParser.SubFuncContext ctx);
	/**
	 * Enter a parse tree produced by {@link ComplexityFunctionsParser#multiplyingExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplyingExpression(@NotNull ComplexityFunctionsParser.MultiplyingExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ComplexityFunctionsParser#multiplyingExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplyingExpression(@NotNull ComplexityFunctionsParser.MultiplyingExpressionContext ctx);
}