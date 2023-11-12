// Generated from TwoLoops.g4 by ANTLR 4.4

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
 * {@link TwoLoopsParser}.
 */
public interface TwoLoopsListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TwoLoopsParser#argList}.
	 * @param ctx the parse tree
	 */
	void enterArgList(@NotNull TwoLoopsParser.ArgListContext ctx);
	/**
	 * Exit a parse tree produced by {@link TwoLoopsParser#argList}.
	 * @param ctx the parse tree
	 */
	void exitArgList(@NotNull TwoLoopsParser.ArgListContext ctx);
	/**
	 * Enter a parse tree produced by {@link TwoLoopsParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull TwoLoopsParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TwoLoopsParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull TwoLoopsParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TwoLoopsParser#whileSegment1}.
	 * @param ctx the parse tree
	 */
	void enterWhileSegment1(@NotNull TwoLoopsParser.WhileSegment1Context ctx);
	/**
	 * Exit a parse tree produced by {@link TwoLoopsParser#whileSegment1}.
	 * @param ctx the parse tree
	 */
	void exitWhileSegment1(@NotNull TwoLoopsParser.WhileSegment1Context ctx);
	/**
	 * Enter a parse tree produced by {@link TwoLoopsParser#rightSide}.
	 * @param ctx the parse tree
	 */
	void enterRightSide(@NotNull TwoLoopsParser.RightSideContext ctx);
	/**
	 * Exit a parse tree produced by {@link TwoLoopsParser#rightSide}.
	 * @param ctx the parse tree
	 */
	void exitRightSide(@NotNull TwoLoopsParser.RightSideContext ctx);
	/**
	 * Enter a parse tree produced by {@link TwoLoopsParser#whileSegment}.
	 * @param ctx the parse tree
	 */
	void enterWhileSegment(@NotNull TwoLoopsParser.WhileSegmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link TwoLoopsParser#whileSegment}.
	 * @param ctx the parse tree
	 */
	void exitWhileSegment(@NotNull TwoLoopsParser.WhileSegmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link TwoLoopsParser#rightVar}.
	 * @param ctx the parse tree
	 */
	void enterRightVar(@NotNull TwoLoopsParser.RightVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link TwoLoopsParser#rightVar}.
	 * @param ctx the parse tree
	 */
	void exitRightVar(@NotNull TwoLoopsParser.RightVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link TwoLoopsParser#segmentSequence1}.
	 * @param ctx the parse tree
	 */
	void enterSegmentSequence1(@NotNull TwoLoopsParser.SegmentSequence1Context ctx);
	/**
	 * Exit a parse tree produced by {@link TwoLoopsParser#segmentSequence1}.
	 * @param ctx the parse tree
	 */
	void exitSegmentSequence1(@NotNull TwoLoopsParser.SegmentSequence1Context ctx);
	/**
	 * Enter a parse tree produced by {@link TwoLoopsParser#leftVar}.
	 * @param ctx the parse tree
	 */
	void enterLeftVar(@NotNull TwoLoopsParser.LeftVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link TwoLoopsParser#leftVar}.
	 * @param ctx the parse tree
	 */
	void exitLeftVar(@NotNull TwoLoopsParser.LeftVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link TwoLoopsParser#ifSegment}.
	 * @param ctx the parse tree
	 */
	void enterIfSegment(@NotNull TwoLoopsParser.IfSegmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link TwoLoopsParser#ifSegment}.
	 * @param ctx the parse tree
	 */
	void exitIfSegment(@NotNull TwoLoopsParser.IfSegmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link TwoLoopsParser#leftSide}.
	 * @param ctx the parse tree
	 */
	void enterLeftSide(@NotNull TwoLoopsParser.LeftSideContext ctx);
	/**
	 * Exit a parse tree produced by {@link TwoLoopsParser#leftSide}.
	 * @param ctx the parse tree
	 */
	void exitLeftSide(@NotNull TwoLoopsParser.LeftSideContext ctx);
	/**
	 * Enter a parse tree produced by {@link TwoLoopsParser#segmentSequence}.
	 * @param ctx the parse tree
	 */
	void enterSegmentSequence(@NotNull TwoLoopsParser.SegmentSequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link TwoLoopsParser#segmentSequence}.
	 * @param ctx the parse tree
	 */
	void exitSegmentSequence(@NotNull TwoLoopsParser.SegmentSequenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link TwoLoopsParser#segment}.
	 * @param ctx the parse tree
	 */
	void enterSegment(@NotNull TwoLoopsParser.SegmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link TwoLoopsParser#segment}.
	 * @param ctx the parse tree
	 */
	void exitSegment(@NotNull TwoLoopsParser.SegmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link TwoLoopsParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(@NotNull TwoLoopsParser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link TwoLoopsParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(@NotNull TwoLoopsParser.ArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link TwoLoopsParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(@NotNull TwoLoopsParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link TwoLoopsParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(@NotNull TwoLoopsParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link TwoLoopsParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterFormula(@NotNull TwoLoopsParser.FormulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link TwoLoopsParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitFormula(@NotNull TwoLoopsParser.FormulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link TwoLoopsParser#exprSequence}.
	 * @param ctx the parse tree
	 */
	void enterExprSequence(@NotNull TwoLoopsParser.ExprSequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link TwoLoopsParser#exprSequence}.
	 * @param ctx the parse tree
	 */
	void exitExprSequence(@NotNull TwoLoopsParser.ExprSequenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link TwoLoopsParser#logicalFunction}.
	 * @param ctx the parse tree
	 */
	void enterLogicalFunction(@NotNull TwoLoopsParser.LogicalFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TwoLoopsParser#logicalFunction}.
	 * @param ctx the parse tree
	 */
	void exitLogicalFunction(@NotNull TwoLoopsParser.LogicalFunctionContext ctx);
}