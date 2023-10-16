// Generated from ExpressionFormula.g4 by ANTLR 4.4

package formula;
import java.util.*;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExpressionFormulaParser}.
 */
public interface ExpressionFormulaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExpressionFormulaParser#argList}.
	 * @param ctx the parse tree
	 */
	void enterArgList(@NotNull ExpressionFormulaParser.ArgListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionFormulaParser#argList}.
	 * @param ctx the parse tree
	 */
	void exitArgList(@NotNull ExpressionFormulaParser.ArgListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionFormulaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull ExpressionFormulaParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionFormulaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull ExpressionFormulaParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionFormulaParser#rightSide}.
	 * @param ctx the parse tree
	 */
	void enterRightSide(@NotNull ExpressionFormulaParser.RightSideContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionFormulaParser#rightSide}.
	 * @param ctx the parse tree
	 */
	void exitRightSide(@NotNull ExpressionFormulaParser.RightSideContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionFormulaParser#whileSegment}.
	 * @param ctx the parse tree
	 */
	void enterWhileSegment(@NotNull ExpressionFormulaParser.WhileSegmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionFormulaParser#whileSegment}.
	 * @param ctx the parse tree
	 */
	void exitWhileSegment(@NotNull ExpressionFormulaParser.WhileSegmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionFormulaParser#rightVar}.
	 * @param ctx the parse tree
	 */
	void enterRightVar(@NotNull ExpressionFormulaParser.RightVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionFormulaParser#rightVar}.
	 * @param ctx the parse tree
	 */
	void exitRightVar(@NotNull ExpressionFormulaParser.RightVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionFormulaParser#leftVar}.
	 * @param ctx the parse tree
	 */
	void enterLeftVar(@NotNull ExpressionFormulaParser.LeftVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionFormulaParser#leftVar}.
	 * @param ctx the parse tree
	 */
	void exitLeftVar(@NotNull ExpressionFormulaParser.LeftVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionFormulaParser#ifSegment}.
	 * @param ctx the parse tree
	 */
	void enterIfSegment(@NotNull ExpressionFormulaParser.IfSegmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionFormulaParser#ifSegment}.
	 * @param ctx the parse tree
	 */
	void exitIfSegment(@NotNull ExpressionFormulaParser.IfSegmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionFormulaParser#leftSide}.
	 * @param ctx the parse tree
	 */
	void enterLeftSide(@NotNull ExpressionFormulaParser.LeftSideContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionFormulaParser#leftSide}.
	 * @param ctx the parse tree
	 */
	void exitLeftSide(@NotNull ExpressionFormulaParser.LeftSideContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionFormulaParser#segmentSequence}.
	 * @param ctx the parse tree
	 */
	void enterSegmentSequence(@NotNull ExpressionFormulaParser.SegmentSequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionFormulaParser#segmentSequence}.
	 * @param ctx the parse tree
	 */
	void exitSegmentSequence(@NotNull ExpressionFormulaParser.SegmentSequenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionFormulaParser#segment}.
	 * @param ctx the parse tree
	 */
	void enterSegment(@NotNull ExpressionFormulaParser.SegmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionFormulaParser#segment}.
	 * @param ctx the parse tree
	 */
	void exitSegment(@NotNull ExpressionFormulaParser.SegmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionFormulaParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(@NotNull ExpressionFormulaParser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionFormulaParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(@NotNull ExpressionFormulaParser.ArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionFormulaParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(@NotNull ExpressionFormulaParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionFormulaParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(@NotNull ExpressionFormulaParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionFormulaParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterFormula(@NotNull ExpressionFormulaParser.FormulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionFormulaParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitFormula(@NotNull ExpressionFormulaParser.FormulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionFormulaParser#logicalFunction}.
	 * @param ctx the parse tree
	 */
	void enterLogicalFunction(@NotNull ExpressionFormulaParser.LogicalFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionFormulaParser#logicalFunction}.
	 * @param ctx the parse tree
	 */
	void exitLogicalFunction(@NotNull ExpressionFormulaParser.LogicalFunctionContext ctx);
}