/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.HashSet;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *
 * @author Nest
 */
public class CodePresenter extends Presenter {

    HashSet<String> reservedWords = new HashSet<>();

    public CodePresenter(TextFlow textFlow) {
        super(textFlow);

        reservedWords.add("and");
        reservedWords.add("array");
        reservedWords.add("asm");
        reservedWords.add("begin");
        reservedWords.add("break");
        reservedWords.add("case");
        reservedWords.add("const");
        reservedWords.add("constructor");
        reservedWords.add("continue");
        reservedWords.add("destructor");
        reservedWords.add("div");
        reservedWords.add("do");
        reservedWords.add("downto");
        reservedWords.add("else");
        reservedWords.add("end");
        reservedWords.add("false");
        reservedWords.add("file");
        reservedWords.add("for");
        reservedWords.add("function");
        reservedWords.add("goto");
        reservedWords.add("if");
        reservedWords.add("implementation");
        reservedWords.add("in");
        reservedWords.add("inline");
        reservedWords.add("interface");
        reservedWords.add("label");
        reservedWords.add("mod");
        reservedWords.add("nil");
        reservedWords.add("not");
        reservedWords.add("object");
        reservedWords.add("of");
        reservedWords.add("on");
        reservedWords.add("operator");
        reservedWords.add("or");
        reservedWords.add("packed");
        reservedWords.add("procedure");
        reservedWords.add("program");
        reservedWords.add("record");
        reservedWords.add("repeat");
        reservedWords.add("set");
        reservedWords.add("shl");
        reservedWords.add("shr");
        reservedWords.add("string");
        reservedWords.add("then");
        reservedWords.add("to");
        reservedWords.add("true");
        reservedWords.add("type");
        reservedWords.add("unit");
        reservedWords.add("until");
        reservedWords.add("uses");
        reservedWords.add("var");
        reservedWords.add("while");
        reservedWords.add("with");
        reservedWords.add("xor");
    }

    @Override
    public void setText(String inputString) {
        textFlow.getChildren().clear();
        output = new StringBuilder();

        String lines[] = inputString.split("\n");
        lineNumber = lines.length;

        for (int i = 0; i < lineNumber; i++) {
            Text text;
            addLineNumber(i, "", lines[i], true);
            String words[] = lines[i].split(" ");

            // Index where white spaces are ending
            int contentIndex = lines[i].indexOf(words[0]);

            if (contentIndex > 0) {
                text = new Text(lines[i].substring(0, contentIndex));
                text.setFill(SIMPLE_CODE_COLOR);
                addText(text);
            }

            for (int j = 0; j < words.length; j++) {
                String tail;
                if (j == words.length - 1) {
                    tail = NEW_LINE;
                } else {
                    tail = WHITE_SPACE;
                }

                text = new Text(words[j] + tail);
                text.setFill(SIMPLE_CODE_COLOR);
                String word = words[j].trim();
                if (reservedWords.contains(word)) {
                    text.setFill(CODE_COLOR);
                    /* if last character is ';' or '.' and string without last character
                     * is a reserved word
                     * Looks like: if((A || B) && C){ ... }
                     */
                } else if ((word.charAt(word.length() - 1) == ';'
                        || word.charAt(word.length() - 1) == '.')
                        && ((reservedWords.contains(word.substring(0, word.length() - 1))))) {
                    text = new Text(words[j].substring(0, words[j].length() - 1));
                    text.setFill(CODE_COLOR);
                    addText(text);

                    text = new Text(word.charAt(word.length() - 1) + tail);
                    text.setFill(SIMPLE_CODE_COLOR);
                }

                addText(text);
            }
        }
    }
}
