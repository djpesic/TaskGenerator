// Generated from NestingRules.g4 by ANTLR 4.4

package dependency.rules.nesting;
import java.util.*;
import complexity.*;
import org.javatuples.Pair;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link NestingRulesParser}.
 */
public interface NestingRulesListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link NestingRulesParser#argList}.
	 * @param ctx the parse tree
	 */
	void enterArgList(@NotNull NestingRulesParser.ArgListContext ctx);
	/**
	 * Exit a parse tree produced by {@link NestingRulesParser#argList}.
	 * @param ctx the parse tree
	 */
	void exitArgList(@NotNull NestingRulesParser.ArgListContext ctx);
	/**
	 * Enter a parse tree produced by {@link NestingRulesParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull NestingRulesParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link NestingRulesParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull NestingRulesParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link NestingRulesParser#whileSegment1}.
	 * @param ctx the parse tree
	 */
	void enterWhileSegment1(@NotNull NestingRulesParser.WhileSegment1Context ctx);
	/**
	 * Exit a parse tree produced by {@link NestingRulesParser#whileSegment1}.
	 * @param ctx the parse tree
	 */
	void exitWhileSegment1(@NotNull NestingRulesParser.WhileSegment1Context ctx);
	/**
	 * Enter a parse tree produced by {@link NestingRulesParser#rightSide}.
	 * @param ctx the parse tree
	 */
	void enterRightSide(@NotNull NestingRulesParser.RightSideContext ctx);
	/**
	 * Exit a parse tree produced by {@link NestingRulesParser#rightSide}.
	 * @param ctx the parse tree
	 */
	void exitRightSide(@NotNull NestingRulesParser.RightSideContext ctx);
	/**
	 * Enter a parse tree produced by {@link NestingRulesParser#whileSegment}.
	 * @param ctx the parse tree
	 */
	void enterWhileSegment(@NotNull NestingRulesParser.WhileSegmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link NestingRulesParser#whileSegment}.
	 * @param ctx the parse tree
	 */
	void exitWhileSegment(@NotNull NestingRulesParser.WhileSegmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link NestingRulesParser#rightVar}.
	 * @param ctx the parse tree
	 */
	void enterRightVar(@NotNull NestingRulesParser.RightVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link NestingRulesParser#rightVar}.
	 * @param ctx the parse tree
	 */
	void exitRightVar(@NotNull NestingRulesParser.RightVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link NestingRulesParser#segmentSequence1}.
	 * @param ctx the parse tree
	 */
	void enterSegmentSequence1(@NotNull NestingRulesParser.SegmentSequence1Context ctx);
	/**
	 * Exit a parse tree produced by {@link NestingRulesParser#segmentSequence1}.
	 * @param ctx the parse tree
	 */
	void exitSegmentSequence1(@NotNull NestingRulesParser.SegmentSequence1Context ctx);
	/**
	 * Enter a parse tree produced by {@link NestingRulesParser#leftVar}.
	 * @param ctx the parse tree
	 */
	void enterLeftVar(@NotNull NestingRulesParser.LeftVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link NestingRulesParser#leftVar}.
	 * @param ctx the parse tree
	 */
	void exitLeftVar(@NotNull NestingRulesParser.LeftVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link NestingRulesParser#ifSegment}.
	 * @param ctx the parse tree
	 */
	void enterIfSegment(@NotNull NestingRulesParser.IfSegmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link NestingRulesParser#ifSegment}.
	 * @param ctx the parse tree
	 */
	void exitIfSegment(@NotNull NestingRulesParser.IfSegmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link NestingRulesParser#segment1}.
	 * @param ctx the parse tree
	 */
	void enterSegment1(@NotNull NestingRulesParser.Segment1Context ctx);
	/**
	 * Exit a parse tree produced by {@link NestingRulesParser#segment1}.
	 * @param ctx the parse tree
	 */
	void exitSegment1(@NotNull NestingRulesParser.Segment1Context ctx);
	/**
	 * Enter a parse tree produced by {@link NestingRulesParser#leftSide}.
	 * @param ctx the parse tree
	 */
	void enterLeftSide(@NotNull NestingRulesParser.LeftSideContext ctx);
	/**
	 * Exit a parse tree produced by {@link NestingRulesParser#leftSide}.
	 * @param ctx the parse tree
	 */
	void exitLeftSide(@NotNull NestingRulesParser.LeftSideContext ctx);
	/**
	 * Enter a parse tree produced by {@link NestingRulesParser#segmentSequence}.
	 * @param ctx the parse tree
	 */
	void enterSegmentSequence(@NotNull NestingRulesParser.SegmentSequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link NestingRulesParser#segmentSequence}.
	 * @param ctx the parse tree
	 */
	void exitSegmentSequence(@NotNull NestingRulesParser.SegmentSequenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link NestingRulesParser#segment}.
	 * @param ctx the parse tree
	 */
	void enterSegment(@NotNull NestingRulesParser.SegmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link NestingRulesParser#segment}.
	 * @param ctx the parse tree
	 */
	void exitSegment(@NotNull NestingRulesParser.SegmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link NestingRulesParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(@NotNull NestingRulesParser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link NestingRulesParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(@NotNull NestingRulesParser.ArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link NestingRulesParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(@NotNull NestingRulesParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link NestingRulesParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(@NotNull NestingRulesParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link NestingRulesParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterFormula(@NotNull NestingRulesParser.FormulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link NestingRulesParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitFormula(@NotNull NestingRulesParser.FormulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link NestingRulesParser#exprSequence}.
	 * @param ctx the parse tree
	 */
	void enterExprSequence(@NotNull NestingRulesParser.ExprSequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link NestingRulesParser#exprSequence}.
	 * @param ctx the parse tree
	 */
	void exitExprSequence(@NotNull NestingRulesParser.ExprSequenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link NestingRulesParser#logicalFunction}.
	 * @param ctx the parse tree
	 */
	void enterLogicalFunction(@NotNull NestingRulesParser.LogicalFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link NestingRulesParser#logicalFunction}.
	 * @param ctx the parse tree
	 */
	void exitLogicalFunction(@NotNull NestingRulesParser.LogicalFunctionContext ctx);
}