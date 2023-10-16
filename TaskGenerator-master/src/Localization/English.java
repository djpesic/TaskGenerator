
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Localization;

/**
 *
 * @author Lake
 */
public class English implements Language {

    @Override
    public String english() {
        return "English";
    }

    @Override
    public String serbian() {
        return "Serbian";
    }

    @Override
    public String copy() {
        return "Copy to clipboard";
    }

    @Override
    public String forLoop() {
        return "For loop";
    }

    @Override
    public String whileLoop() {
        return "While loop";
    }

    @Override
    public String repeatLoop() {
        return "Repeat loop";
    }

    @Override
    public String expression() {
        return "Expression";
    }

    @Override
    public String remove() {
        return "Remove";
    }

    @Override
    public String generate() {
        return "Generate";
    }

    @Override
    public String exportGeneratedCode() {
        return "Export generated code";
    }

    @Override
    public String direction() {
        return "\tDirection: ";
    }

    @Override
    public String variable() {
        return "\tVariable: ";
    }

    @Override
    public String iterator() {
        return "\tIterator: ";
    }

    @Override
    public String coreElements() {
        return "Core elements: ";
    }

    @Override
    public String lower() {
        return "\tLower: ";
    }

    @Override
    public String upper() {
        return "\tUpper: ";
    }

    @Override
    public String program() {
        return "Program";
    }

    @Override
    public String complexityFunction() {
        return "\tFunction: ";
    }

    @Override
    public String programSegmentComplexity() {
        return "Complexity: ";
    }

    @Override
    public String sideExpressionSingle() {
        return "Side expression: ";
    }

    @Override
    public String sideExpressionMultiple() {
        return "Side expressions: ";
    }

    @Override
    public String variableError() {
        return "\nVariable name in the loop expression is not filled correctly.";
    }

    @Override
    public String expressionError(String varTypeText) {
        return "\nExpression which is being assigned to variable \""
                + varTypeText + "\" is not filled correctly.";
    }

    @Override
    public String directionError() {
        return "\nLoop direction has not been selected."
                + " Select \"To\" or \"Downto\".";
    }

    @Override
    public String indexingError() {
        return "\nIndex access error detected.";
    }

    @Override
    public String generationError() {
        return "Error during XML code generation!";
    }

    @Override
    public String noContentError() {
        return "Enter input content needed to generate output data.";
    }

    @Override
    public String programLanguage() {
        return "Program Language";
    }

    @Override
    public String ok() {
        return "OK";
    }

    @Override
    public String cancel() {
        return "Cancel";
    }

    @Override
    public String notAlphanumericWithUnderscoreError() {
        return "Not alphanumeric with underscore characters";
    }

    @Override
    public String notDecimalError() {
        return "Not a decimal number";
    }

    @Override
    public String leftSimpleExpr() {
        return "Left simple expression:";
    }

    @Override
    public String rightSimpleExpr() {
        return "\tRight simple expression:";
    }

    @Override
    public String relop() {
        return "Relational operator";
    }

    @Override
    public String Then() {
        return "then";
    }

    @Override
    public String Else() {
        return "else";
    }

    @Override
    public String If() {
        return "if";
    }

    @Override
    public String inIfBranch() {
        return "\tIf branch";
    }

    @Override
    public String inElseBranch() {
        return "\tElse branch";
    }

    @Override
    public String loadTemplate() {
        return "Load template";
    }

    @Override
    public String loadRule() {
        return "Load rule";
    }

    @Override
    public String saveTemplate() {
        return "Save template";
    }

    @Override
    public String saveRule() {
        return "Save rule";
    }

    @Override
    public String templateName() {
        return "Template name";
    }

    @Override
    public String templateCreation() {
        return "Template creation";
    }

    @Override
    public String ruleCreation() {
        return "Rule creation";
    }

    @Override
    public String rule() {
        return "Rule";
    }

    @Override
    public String template() {
        return "Template";
    }

    @Override
    public String ruleName() {
        return "Rule name";
    }

    @Override
    public String currentVariables() {
        return "Current variable values";
    }

    @Override
    public String newVariables() {
        return "New variable values";
    }

    @Override
    public String operation() {
        return "Operation";
    }

    @Override
    public String sequence() {
        return "Sequence";
    }

    @Override
    public String nesting() {
        return "Nesting";
    }

    @Override
    public String selection() {
        return "Selection";
    }

    @Override
    public String template1OutPorts() {
        return "First template input ports";
    }

    @Override
    public String template2InPorts() {
        return "Second template out ports";
    }

    @Override
    public String deleteTemplate() {
        return "Delete template";
    }

    @Override
    public String deleteRule() {
        return "Delete rule";
    }

    @Override
    public String rowCount() {
        return "Row count";
    }

    @Override
    public String variables() {
        return "Variables";
    }

    @Override
    public String error() {
        return "Error";
    }

    @Override
    public String overwrite() {
        return "Overwrite";
    }

    @Override
    public String noIteratorError() {
        return "For loop iterator must be defined, during conversion to while loop";
    }

    @Override
    public String noLowerBoundError() {
        return "For loop iterator must have lower bound, during conversion to while loop";
    }

    @Override
    public String noUpperBoundError() {
        return "For loop iterator must have upper bound, during conversion to while loop";
    }
}
