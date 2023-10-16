// Generated from Logarithmic.g4 by ANTLR 4.4

package dependency.rules.basic;
import java.util.*;
import complexity.*;
import org.javatuples.Pair;
import unification.*;
import org.apache.commons.lang3.math.NumberUtils;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LogarithmicParser}.
 */
public interface LogarithmicListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LogarithmicParser#argList}.
	 * @param ctx the parse tree
	 */
	void enterArgList(@NotNull LogarithmicParser.ArgListContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogarithmicParser#argList}.
	 * @param ctx the parse tree
	 */
	void exitArgList(@NotNull LogarithmicParser.ArgListContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogarithmicParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull LogarithmicParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogarithmicParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull LogarithmicParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogarithmicParser#rightSide}.
	 * @param ctx the parse tree
	 */
	void enterRightSide(@NotNull LogarithmicParser.RightSideContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogarithmicParser#rightSide}.
	 * @param ctx the parse tree
	 */
	void exitRightSide(@NotNull LogarithmicParser.RightSideContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogarithmicParser#whileSegment}.
	 * @param ctx the parse tree
	 */
	void enterWhileSegment(@NotNull LogarithmicParser.WhileSegmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogarithmicParser#whileSegment}.
	 * @param ctx the parse tree
	 */
	void exitWhileSegment(@NotNull LogarithmicParser.WhileSegmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogarithmicParser#rightVar}.
	 * @param ctx the parse tree
	 */
	void enterRightVar(@NotNull LogarithmicParser.RightVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogarithmicParser#rightVar}.
	 * @param ctx the parse tree
	 */
	void exitRightVar(@NotNull LogarithmicParser.RightVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogarithmicParser#leftVar}.
	 * @param ctx the parse tree
	 */
	void enterLeftVar(@NotNull LogarithmicParser.LeftVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogarithmicParser#leftVar}.
	 * @param ctx the parse tree
	 */
	void exitLeftVar(@NotNull LogarithmicParser.LeftVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogarithmicParser#leftSide}.
	 * @param ctx the parse tree
	 */
	void enterLeftSide(@NotNull LogarithmicParser.LeftSideContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogarithmicParser#leftSide}.
	 * @param ctx the parse tree
	 */
	void exitLeftSide(@NotNull LogarithmicParser.LeftSideContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogarithmicParser#segmentSequence}.
	 * @param ctx the parse tree
	 */
	void enterSegmentSequence(@NotNull LogarithmicParser.SegmentSequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogarithmicParser#segmentSequence}.
	 * @param ctx the parse tree
	 */
	void exitSegmentSequence(@NotNull LogarithmicParser.SegmentSequenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogarithmicParser#segment}.
	 * @param ctx the parse tree
	 */
	void enterSegment(@NotNull LogarithmicParser.SegmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogarithmicParser#segment}.
	 * @param ctx the parse tree
	 */
	void exitSegment(@NotNull LogarithmicParser.SegmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogarithmicParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(@NotNull LogarithmicParser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogarithmicParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(@NotNull LogarithmicParser.ArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogarithmicParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(@NotNull LogarithmicParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogarithmicParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(@NotNull LogarithmicParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogarithmicParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterFormula(@NotNull LogarithmicParser.FormulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogarithmicParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitFormula(@NotNull LogarithmicParser.FormulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link LogarithmicParser#exprSequence}.
	 * @param ctx the parse tree
	 */
	void enterExprSequence(@NotNull LogarithmicParser.ExprSequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link LogarithmicParser#exprSequence}.
	 * @param ctx the parse tree
	 */
	void exitExprSequence(@NotNull LogarithmicParser.ExprSequenceContext ctx);
}