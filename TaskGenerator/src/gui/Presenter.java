/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *
 * @author Nest
 */
public abstract class Presenter {

    protected TextFlow textFlow;

    protected static final String ONE_TAB = "\t";
    protected static final String TWO_TABS = "\t\t";
    protected static final String NEW_LINE = "\n";
    protected static final String WHITE_SPACE = " ";

    protected static final Color LINE_NUMBER_COLOR = Color.web("#f4f4f4");

    protected static final Color TAG_COLOR = Color.web("#f92672");
    protected static final Color ATTRIBUTE_COLOR = Color.web("#a6e22e");
    protected static final Color VALUE_COLOR = Color.web("#e6db74");
    protected static final Color DATA_COLOR = Color.web("#e91e63");
    protected static final Color COLLAPSE_SIGN_COLOR = Color.web("#ff0000");
    protected static final Color LG_SIGN_COLOR = Color.web("#f8f8f2");

    protected static final Color CODE_COLOR = Color.web("#f92672");
    protected static final Color SIMPLE_CODE_COLOR = Color.web("#f8f8f2");

    protected StringBuilder output = null;
    protected int lineNumber = 0;

    private final int LINE_LIMIT = 10000;

    protected Presenter(TextFlow textFlow) {
        this.textFlow = textFlow;
    }

    public abstract void setText(String inputString);

    protected void addText(Text text) {
        text.setFont(Font.font("Verdana", 14));
        textFlow.getChildren().add(text);
    }

    public void addLineNumber(int number, String sign, String line, boolean pipe) {
        String tabs;
        String offset = "";

        if (number >= 1000) {
            tabs = ONE_TAB;
        } else {
            tabs = TWO_TABS;
        }

        number += 1;
        int digits = (int) Math.log10(number);
        int maxDigits = (int) Math.log10(LINE_LIMIT);

        // Add whitespaces in front of collapse signs
        for (int i = digits; i < maxDigits; i++) {
            offset += "0";
        }

        /*
         * Add invisible "000" type string.
         * Aligns smoother than adding whitespace based string in front.
         */
        Text text = new Text(offset);
        text.setOpacity(0);
        addText(text);

        text = new Text(+number + ":");
        text.setFill(LINE_NUMBER_COLOR);
        addText(text);

        text = new Text(sign + tabs);
        text.setFill(COLLAPSE_SIGN_COLOR);
        addText(text);

        if (pipe) {
            output.append(line);
            output.append(NEW_LINE);
        }
    }

    public String toString() {
        if (output != null) {
            return output.toString();
        }

        return null;
    }
}
