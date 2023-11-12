// Generated from Linear.g4 by ANTLR 4.4

package dependency.rules.sequencing;
import java.util.*;
import complexity.*;
import org.javatuples.Pair;
import org.apache.commons.lang3.math.NumberUtils;
import unification.*;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LinearParser}.
 */
public interface LinearListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LinearParser#argList}.
	 * @param ctx the parse tree
	 */
	void enterArgList(@NotNull LinearParser.ArgListContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinearParser#argList}.
	 * @param ctx the parse tree
	 */
	void exitArgList(@NotNull LinearParser.ArgListContext ctx);
	/**
	 * Enter a parse tree produced by {@link LinearParser#leftSide}.
	 * @param ctx the parse tree
	 */
	void enterLeftSide(@NotNull LinearParser.LeftSideContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinearParser#leftSide}.
	 * @param ctx the parse tree
	 */
	void exitLeftSide(@NotNull LinearParser.LeftSideContext ctx);
	/**
	 * Enter a parse tree produced by {@link LinearParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull LinearParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinearParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull LinearParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LinearParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(@NotNull LinearParser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinearParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(@NotNull LinearParser.ArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link LinearParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(@NotNull LinearParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinearParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(@NotNull LinearParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link LinearParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterFormula(@NotNull LinearParser.FormulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinearParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitFormula(@NotNull LinearParser.FormulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link LinearParser#exprSequence}.
	 * @param ctx the parse tree
	 */
	void enterExprSequence(@NotNull LinearParser.ExprSequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinearParser#exprSequence}.
	 * @param ctx the parse tree
	 */
	void exitExprSequence(@NotNull LinearParser.ExprSequenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link LinearParser#rightSide}.
	 * @param ctx the parse tree
	 */
	void enterRightSide(@NotNull LinearParser.RightSideContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinearParser#rightSide}.
	 * @param ctx the parse tree
	 */
	void exitRightSide(@NotNull LinearParser.RightSideContext ctx);
	/**
	 * Enter a parse tree produced by {@link LinearParser#whileSegment}.
	 * @param ctx the parse tree
	 */
	void enterWhileSegment(@NotNull LinearParser.WhileSegmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinearParser#whileSegment}.
	 * @param ctx the parse tree
	 */
	void exitWhileSegment(@NotNull LinearParser.WhileSegmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link LinearParser#rightVar}.
	 * @param ctx the parse tree
	 */
	void enterRightVar(@NotNull LinearParser.RightVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinearParser#rightVar}.
	 * @param ctx the parse tree
	 */
	void exitRightVar(@NotNull LinearParser.RightVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link LinearParser#segmentSequence1}.
	 * @param ctx the parse tree
	 */
	void enterSegmentSequence1(@NotNull LinearParser.SegmentSequence1Context ctx);
	/**
	 * Exit a parse tree produced by {@link LinearParser#segmentSequence1}.
	 * @param ctx the parse tree
	 */
	void exitSegmentSequence1(@NotNull LinearParser.SegmentSequence1Context ctx);
	/**
	 * Enter a parse tree produced by {@link LinearParser#leftVar}.
	 * @param ctx the parse tree
	 */
	void enterLeftVar(@NotNull LinearParser.LeftVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link LinearParser#leftVar}.
	 * @param ctx the parse tree
	 */
	void exitLeftVar(@NotNull LinearParser.LeftVarContext ctx);
}