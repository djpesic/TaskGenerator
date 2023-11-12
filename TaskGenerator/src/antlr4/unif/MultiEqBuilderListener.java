// Generated from MultiEqBuilder.g4 by ANTLR 4.4

package unif;
import java.util.*;
import unification.*;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MultiEqBuilderParser}.
 */
public interface MultiEqBuilderListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MultiEqBuilderParser#argList}.
	 * @param ctx the parse tree
	 */
	void enterArgList(@NotNull MultiEqBuilderParser.ArgListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MultiEqBuilderParser#argList}.
	 * @param ctx the parse tree
	 */
	void exitArgList(@NotNull MultiEqBuilderParser.ArgListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MultiEqBuilderParser#sequence}.
	 * @param ctx the parse tree
	 */
	void enterSequence(@NotNull MultiEqBuilderParser.SequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link MultiEqBuilderParser#sequence}.
	 * @param ctx the parse tree
	 */
	void exitSequence(@NotNull MultiEqBuilderParser.SequenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link MultiEqBuilderParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(@NotNull MultiEqBuilderParser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link MultiEqBuilderParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(@NotNull MultiEqBuilderParser.ArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link MultiEqBuilderParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(@NotNull MultiEqBuilderParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link MultiEqBuilderParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(@NotNull MultiEqBuilderParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link MultiEqBuilderParser#formula}.
	 * @param ctx the parse tree
	 */
	void enterFormula(@NotNull MultiEqBuilderParser.FormulaContext ctx);
	/**
	 * Exit a parse tree produced by {@link MultiEqBuilderParser#formula}.
	 * @param ctx the parse tree
	 */
	void exitFormula(@NotNull MultiEqBuilderParser.FormulaContext ctx);
}