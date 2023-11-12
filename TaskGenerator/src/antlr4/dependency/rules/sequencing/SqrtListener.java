// Generated from Sqrt.g4 by ANTLR 4.4

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
 * {@link SqrtParser}.
 */
public interface SqrtListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SqrtParser#argList}.
	 * @param ctx the parse tree
	 */
	void enterArgList(@NotNull SqrtParser.ArgListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqrtParser#argList}.
	 * @param ctx the parse tree
	 */
	void exitArgList(@NotNull SqrtParser.ArgListContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqrtParser#leftSide}.
	 * @param ctx the parse tree
	 */
	void enterLeftSide(@NotNull SqrtParser.LeftSideContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqrtParser#leftSide}.
	 * @param ctx the parse tree
	 */
	void exitLeftSide(@NotNull SqrtParser.LeftSideContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqrtParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull SqrtParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqrtParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull SqrtParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqrtParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(@NotNull SqrtParser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqrtParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(@NotNull SqrtParser.ArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqrtParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(@NotNull SqrtParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqrtParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(@NotNull SqrtParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqrtParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterFormula(@NotNull SqrtParser.FormulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqrtParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitFormula(@NotNull SqrtParser.FormulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqrtParser#exprSequence}.
	 * @param ctx the parse tree
	 */
	void enterExprSequence(@NotNull SqrtParser.ExprSequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqrtParser#exprSequence}.
	 * @param ctx the parse tree
	 */
	void exitExprSequence(@NotNull SqrtParser.ExprSequenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqrtParser#rightSide}.
	 * @param ctx the parse tree
	 */
	void enterRightSide(@NotNull SqrtParser.RightSideContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqrtParser#rightSide}.
	 * @param ctx the parse tree
	 */
	void exitRightSide(@NotNull SqrtParser.RightSideContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqrtParser#whileSegment}.
	 * @param ctx the parse tree
	 */
	void enterWhileSegment(@NotNull SqrtParser.WhileSegmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqrtParser#whileSegment}.
	 * @param ctx the parse tree
	 */
	void exitWhileSegment(@NotNull SqrtParser.WhileSegmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqrtParser#rightVar}.
	 * @param ctx the parse tree
	 */
	void enterRightVar(@NotNull SqrtParser.RightVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqrtParser#rightVar}.
	 * @param ctx the parse tree
	 */
	void exitRightVar(@NotNull SqrtParser.RightVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link SqrtParser#segmentSequence1}.
	 * @param ctx the parse tree
	 */
	void enterSegmentSequence1(@NotNull SqrtParser.SegmentSequence1Context ctx);
	/**
	 * Exit a parse tree produced by {@link SqrtParser#segmentSequence1}.
	 * @param ctx the parse tree
	 */
	void exitSegmentSequence1(@NotNull SqrtParser.SegmentSequence1Context ctx);
	/**
	 * Enter a parse tree produced by {@link SqrtParser#leftVar}.
	 * @param ctx the parse tree
	 */
	void enterLeftVar(@NotNull SqrtParser.LeftVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link SqrtParser#leftVar}.
	 * @param ctx the parse tree
	 */
	void exitLeftVar(@NotNull SqrtParser.LeftVarContext ctx);
}