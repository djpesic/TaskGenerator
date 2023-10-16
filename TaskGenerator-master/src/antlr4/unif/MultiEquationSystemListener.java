// Generated from MultiEquationSystem.g4 by ANTLR 4.4

package unif;
import java.util.*;
import unification.*;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MultiEquationSystemParser}.
 */
public interface MultiEquationSystemListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MultiEquationSystemParser#multiEquation}.
	 * @param ctx the parse tree
	 */
	void enterMultiEquation(@NotNull MultiEquationSystemParser.MultiEquationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MultiEquationSystemParser#multiEquation}.
	 * @param ctx the parse tree
	 */
	void exitMultiEquation(@NotNull MultiEquationSystemParser.MultiEquationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MultiEquationSystemParser#multiTermArg}.
	 * @param ctx the parse tree
	 */
	void enterMultiTermArg(@NotNull MultiEquationSystemParser.MultiTermArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link MultiEquationSystemParser#multiTermArg}.
	 * @param ctx the parse tree
	 */
	void exitMultiTermArg(@NotNull MultiEquationSystemParser.MultiTermArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link MultiEquationSystemParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(@NotNull MultiEquationSystemParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link MultiEquationSystemParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(@NotNull MultiEquationSystemParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link MultiEquationSystemParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(@NotNull MultiEquationSystemParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link MultiEquationSystemParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(@NotNull MultiEquationSystemParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link MultiEquationSystemParser#multiTerm}.
	 * @param ctx the parse tree
	 */
	void enterMultiTerm(@NotNull MultiEquationSystemParser.MultiTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link MultiEquationSystemParser#multiTerm}.
	 * @param ctx the parse tree
	 */
	void exitMultiTerm(@NotNull MultiEquationSystemParser.MultiTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link MultiEquationSystemParser#vars}.
	 * @param ctx the parse tree
	 */
	void enterVars(@NotNull MultiEquationSystemParser.VarsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MultiEquationSystemParser#vars}.
	 * @param ctx the parse tree
	 */
	void exitVars(@NotNull MultiEquationSystemParser.VarsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MultiEquationSystemParser#multiEquationSystem}.
	 * @param ctx the parse tree
	 */
	void enterMultiEquationSystem(@NotNull MultiEquationSystemParser.MultiEquationSystemContext ctx);
	/**
	 * Exit a parse tree produced by {@link MultiEquationSystemParser#multiEquationSystem}.
	 * @param ctx the parse tree
	 */
	void exitMultiEquationSystem(@NotNull MultiEquationSystemParser.MultiEquationSystemContext ctx);
	/**
	 * Enter a parse tree produced by {@link MultiEquationSystemParser#multiTermArgs}.
	 * @param ctx the parse tree
	 */
	void enterMultiTermArgs(@NotNull MultiEquationSystemParser.MultiTermArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MultiEquationSystemParser#multiTermArgs}.
	 * @param ctx the parse tree
	 */
	void exitMultiTermArgs(@NotNull MultiEquationSystemParser.MultiTermArgsContext ctx);
}