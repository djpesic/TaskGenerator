// Generated from SequenceSums.g4 by ANTLR 4.4

package dependency.rules.sequencing;
import java.util.*;
import complexity.*;
import org.javatuples.Pair;
import org.apache.commons.lang3.math.NumberUtils;
import unification.*;
import utils.YacasExecutor;
import com.rits.cloning.Cloner;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SequenceSumsParser}.
 */
public interface SequenceSumsListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SequenceSumsParser#argList}.
	 * @param ctx the parse tree
	 */
	void enterArgList(@NotNull SequenceSumsParser.ArgListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SequenceSumsParser#argList}.
	 * @param ctx the parse tree
	 */
	void exitArgList(@NotNull SequenceSumsParser.ArgListContext ctx);
	/**
	 * Enter a parse tree produced by {@link SequenceSumsParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull SequenceSumsParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SequenceSumsParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull SequenceSumsParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SequenceSumsParser#rightSide}.
	 * @param ctx the parse tree
	 */
	void enterRightSide(@NotNull SequenceSumsParser.RightSideContext ctx);
	/**
	 * Exit a parse tree produced by {@link SequenceSumsParser#rightSide}.
	 * @param ctx the parse tree
	 */
	void exitRightSide(@NotNull SequenceSumsParser.RightSideContext ctx);
	/**
	 * Enter a parse tree produced by {@link SequenceSumsParser#whileSegment}.
	 * @param ctx the parse tree
	 */
	void enterWhileSegment(@NotNull SequenceSumsParser.WhileSegmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SequenceSumsParser#whileSegment}.
	 * @param ctx the parse tree
	 */
	void exitWhileSegment(@NotNull SequenceSumsParser.WhileSegmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link SequenceSumsParser#rightVar}.
	 * @param ctx the parse tree
	 */
	void enterRightVar(@NotNull SequenceSumsParser.RightVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link SequenceSumsParser#rightVar}.
	 * @param ctx the parse tree
	 */
	void exitRightVar(@NotNull SequenceSumsParser.RightVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link SequenceSumsParser#leftVar}.
	 * @param ctx the parse tree
	 */
	void enterLeftVar(@NotNull SequenceSumsParser.LeftVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link SequenceSumsParser#leftVar}.
	 * @param ctx the parse tree
	 */
	void exitLeftVar(@NotNull SequenceSumsParser.LeftVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link SequenceSumsParser#segment1}.
	 * @param ctx the parse tree
	 */
	void enterSegment1(@NotNull SequenceSumsParser.Segment1Context ctx);
	/**
	 * Exit a parse tree produced by {@link SequenceSumsParser#segment1}.
	 * @param ctx the parse tree
	 */
	void exitSegment1(@NotNull SequenceSumsParser.Segment1Context ctx);
	/**
	 * Enter a parse tree produced by {@link SequenceSumsParser#leftSide}.
	 * @param ctx the parse tree
	 */
	void enterLeftSide(@NotNull SequenceSumsParser.LeftSideContext ctx);
	/**
	 * Exit a parse tree produced by {@link SequenceSumsParser#leftSide}.
	 * @param ctx the parse tree
	 */
	void exitLeftSide(@NotNull SequenceSumsParser.LeftSideContext ctx);
	/**
	 * Enter a parse tree produced by {@link SequenceSumsParser#segmentSequence}.
	 * @param ctx the parse tree
	 */
	void enterSegmentSequence(@NotNull SequenceSumsParser.SegmentSequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link SequenceSumsParser#segmentSequence}.
	 * @param ctx the parse tree
	 */
	void exitSegmentSequence(@NotNull SequenceSumsParser.SegmentSequenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link SequenceSumsParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(@NotNull SequenceSumsParser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link SequenceSumsParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(@NotNull SequenceSumsParser.ArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link SequenceSumsParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(@NotNull SequenceSumsParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link SequenceSumsParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(@NotNull SequenceSumsParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link SequenceSumsParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterFormula(@NotNull SequenceSumsParser.FormulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link SequenceSumsParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitFormula(@NotNull SequenceSumsParser.FormulaContext ctx);
	/**
	 * Enter a parse tree produced by {@link SequenceSumsParser#exprSequence}.
	 * @param ctx the parse tree
	 */
	void enterExprSequence(@NotNull SequenceSumsParser.ExprSequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link SequenceSumsParser#exprSequence}.
	 * @param ctx the parse tree
	 */
	void exitExprSequence(@NotNull SequenceSumsParser.ExprSequenceContext ctx);
}