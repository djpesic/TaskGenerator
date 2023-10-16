// Generated from NestingRulesSums.g4 by ANTLR 4.4

package dependency.rules.nesting;
import java.util.*;
import complexity.*;
import org.javatuples.Pair;
import org.apache.commons.lang3.math.NumberUtils;
import unification.*;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link NestingRulesSumsParser}.
 */
public interface NestingRulesSumsListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link NestingRulesSumsParser#argList}.
	 * @param ctx the parse tree
	 */
	void enterArgList(@NotNull NestingRulesSumsParser.ArgListContext ctx);
	/**
	 * Exit a parse tree produced by {@link NestingRulesSumsParser#argList}.
	 * @param ctx the parse tree
	 */
	void exitArgList(@NotNull NestingRulesSumsParser.ArgListContext ctx);
	/**
	 * Enter a parse tree produced by {@link NestingRulesSumsParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull NestingRulesSumsParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link NestingRulesSumsParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull NestingRulesSumsParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link NestingRulesSumsParser#whileSegment1}.
	 * @param ctx the parse tree
	 */
	void enterWhileSegment1(@NotNull NestingRulesSumsParser.WhileSegment1Context ctx);
	/**
	 * Exit a parse tree produced by {@link NestingRulesSumsParser#whileSegment1}.
	 * @param ctx the parse tree
	 */
	void exitWhileSegment1(@NotNull NestingRulesSumsParser.WhileSegment1Context ctx);
	/**
	 * Enter a parse tree produced by {@link NestingRulesSumsParser#rightSide}.
	 * @param ctx the parse tree
	 */
	void enterRightSide(@NotNull NestingRulesSumsParser.RightSideContext ctx);
	/**
	 * Exit a parse tree produced by {@link NestingRulesSumsParser#rightSide}.
	 * @param ctx the parse tree
	 */
	void exitRightSide(@NotNull NestingRulesSumsParser.RightSideContext ctx);
	/**
	 * Enter a parse tree produced by {@link NestingRulesSumsParser#whileSegment}.
	 * @param ctx the parse tree
	 */
	void enterWhileSegment(@NotNull NestingRulesSumsParser.WhileSegmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link NestingRulesSumsParser#whileSegment}.
	 * @param ctx the parse tree
	 */
	void exitWhileSegment(@NotNull NestingRulesSumsParser.WhileSegmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link NestingRulesSumsParser#rightVar}.
	 * @param ctx the parse tree
	 */
	void enterRightVar(@NotNull NestingRulesSumsParser.RightVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link NestingRulesSumsParser#rightVar}.
	 * @param ctx the parse tree
	 */
	void exitRightVar(@NotNull NestingRulesSumsParser.RightVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link NestingRulesSumsParser#segmentSequence1}.
	 * @param ctx the parse tree
	 */
	void enterSegmentSequence1(@NotNull NestingRulesSumsParser.SegmentSequence1Context ctx);
	/**
	 * Exit a parse tree produced by {@link NestingRulesSumsParser#segmentSequence1}.
	 * @param ctx the parse tree
	 */
	void exitSegmentSequence1(@NotNull NestingRulesSumsParser.SegmentSequence1Context ctx);
	/**
	 * Enter a parse tree produced by {@link NestingRulesSumsParser#leftVar}.
	 * @param ctx the parse tree
	 */
	void enterLeftVar(@NotNull NestingRulesSumsParser.LeftVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link NestingRulesSumsParser#leftVar}.
	 * @param ctx the parse tree
	 */
	void exitLeftVar(@NotNull NestingRulesSumsParser.LeftVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link NestingRulesSumsParser#leftSide}.
	 * @param ctx the parse tree
	 */
	void enterLeftSide(@NotNull NestingRulesSumsParser.LeftSideContext ctx);
	/**
	 * Exit a parse tree produced by {@link NestingRulesSumsParser#leftSide}.
	 * @param ctx the parse tree
	 */
	void exitLeftSide(@NotNull NestingRulesSumsParser.LeftSideContext ctx);
	/**
	 * Enter a parse tree produced by {@link NestingRulesSumsParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(@NotNull NestingRulesSumsParser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link NestingRulesSumsParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(@NotNull NestingRulesSumsParser.ArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link NestingRulesSumsParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(@NotNull NestingRulesSumsParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link NestingRulesSumsParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(@NotNull NestingRulesSumsParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link NestingRulesSumsParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterFormula(@NotNull NestingRulesSumsParser.FormulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link NestingRulesSumsParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitFormula(@NotNull NestingRulesSumsParser.FormulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link NestingRulesSumsParser#exprSequence}.
	 * @param ctx the parse tree
	 */
	void enterExprSequence(@NotNull NestingRulesSumsParser.ExprSequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link NestingRulesSumsParser#exprSequence}.
	 * @param ctx the parse tree
	 */
	void exitExprSequence(@NotNull NestingRulesSumsParser.ExprSequenceContext ctx);
}