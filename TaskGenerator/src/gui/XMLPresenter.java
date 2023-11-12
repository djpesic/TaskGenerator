/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.LinkedList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *
 * @author Nest
 */
public class XMLPresenter extends Presenter {

    enum XmlTagType {

        START,
        END,
        OTHER
    }

    private String rawText;
    private int[] collapseTracker = null;
    private XmlTagType[] xmlTagType = null;

    private final String COLLAPSE_PLUS = " + ";
    private final String COLLAPSE_MINUS = " - ";
    private final String COLLAPSE_EMPTY = "   ";

    public XMLPresenter(TextFlow textFlow) {
        super(textFlow);

        textFlow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (collapseTracker != null) {

                    int index = calculateIndex((int) event.getY());

                    if (xmlTagType[index] == XmlTagType.START) {
                        if (collapseTracker[index] == 0) {
                            collapse(index, false);
                        } else {
                            collapse(index, true);
                        }
                    }
                }
                if (output != null) {
                    setTextInternal(output.toString(), false);
                }
            }
        });
    }

    @Override
    public void setText(String inputString) {
        output = new StringBuilder();
        collapseTracker = null;
        xmlTagType = null;
        setTextInternal(inputString, true);
    }
    
    public String getRawText(){
        return rawText;
    }
    
    private void setTextInternal(String inputString, boolean pipe) {
        textFlow.getChildren().clear();
        rawText = inputString;
        String lines[] = inputString.split("\n");
        if (collapseTracker == null) {
            lineNumber = lines.length;

            collapseTracker = new int[lineNumber];
            for (int i : collapseTracker) {
                i = 0;
            }

            xmlTagType = new XmlTagType[lineNumber];
            for (XmlTagType tagType : xmlTagType) {
                tagType = XmlTagType.OTHER;
            }
        }

        setFirstTagText(lines[0], pipe);

        for (int i = 1; i < lineNumber; i++) {
            /*
             * Hide all elements where collapseTracker index is greater than 0
             * except for XmlTagType.Start which equals 1
             */
            if ((collapseTracker[i] > 1)
                    || (collapseTracker[i] > 0 && !(collapseTracker[i] == 1 && xmlTagType[i] == XmlTagType.START))) {
                continue;
            }

            Text text;

            int firstLT = lines[i].indexOf("<");
            int firstLTS = lines[i].indexOf("</");
            int firstGT = lines[i].indexOf(">");
            int firstSGT = lines[i].indexOf("/>");
            int lastGT = lines[i].lastIndexOf(">");

            String sign;
            if (collapseTracker[i] > 0) {
                sign = COLLAPSE_PLUS;
            } else {
                sign = COLLAPSE_MINUS;
            }

            if (firstLT != -1 && firstLTS != -1 && firstLT != firstLTS
                    && firstGT != -1 && lastGT != -1 && firstGT != lastGT
                    && lastGT == (lines[i].length() - 1)) {
                //both opening and closing tags        example: <name> some text </name>
                addLineNumber(i, COLLAPSE_EMPTY, lines[i], pipe);

                setOtherText(lines[i], 0, firstLT + 1);
                setTagText(lines[i], firstLT + 1, firstGT);
                setDataText(lines[i], firstGT, firstLTS + 2);
                setTagText(lines[i], firstLTS + 2, lastGT);
                setOtherText(lines[i] + NEW_LINE, lastGT, lines[i].length() + 1);
            } else if (firstLTS != -1 && firstGT == (lines[i].length() - 1)) {
                //only closing tag        example: </segment>
                addLineNumber(i, COLLAPSE_EMPTY, lines[i], pipe);

                xmlTagType[i] = XmlTagType.END;
                setOtherText(lines[i], 0, firstLTS + 2);
                setTagText(lines[i], firstLTS + 2, firstGT);
                setOtherText(lines[i] + NEW_LINE, firstGT, lines[i].length() + 1);
            } else if (firstLT != -1 && firstSGT == (lines[i].length() - 2)) {
                //only empty tag        example: <segment/>
                addLineNumber(i, COLLAPSE_EMPTY, lines[i], pipe);

                setOtherText(lines[i], 0, firstLT + 1);
                setTagText(lines[i], firstLT + 1, firstSGT);
                setOtherText(lines[i] + NEW_LINE, firstSGT, lines[i].length() + 1);
            } else if (firstLT != -1 && firstGT == (lines[i].length() - 1)) {
                //only opening tag      example: <segment>
                addLineNumber(i, sign, lines[i], pipe);

                xmlTagType[i] = XmlTagType.START;
                setOtherText(lines[i], 0, firstLT + 1);
                setTagText(lines[i], firstLT + 1, lines[i].length() - 1);
                setOtherText(lines[i] + NEW_LINE, lines[i].length() - 1, lines[i].length() + 1);
            } else {
                /*
                 * TODO: throw an exception
                 * This code shouldn't execute, but in case it gets deleted, there is a chance
                 * that some output won't be displayed, so it's safer to keep it
                 */
                text = new Text(lines[i] + "\n");
                addText(text);
            }
        }
    }

    private void setFirstTagText(String line, boolean pipe) {
        // First tag should be in this format: <?xml version="1.0" encoding="UTF-8"?>
        String lineArray[] = line.split("\"");

        addLineNumber(0, COLLAPSE_EMPTY, line, pipe);
        Text text = new Text("<?");
        text.setFill(LG_SIGN_COLOR);
        addText(text);

        text = new Text("xml ");
        text.setFill(TAG_COLOR);
        addText(text);

        text = new Text("version");
        text.setFill(ATTRIBUTE_COLOR);
        addText(text);

        text = new Text("=");
        text.setFill(LG_SIGN_COLOR);
        addText(text);

        text = new Text('"' + lineArray[1] + '"');
        text.setFill(VALUE_COLOR);
        addText(text);

        text = new Text(" encoding");
        text.setFill(ATTRIBUTE_COLOR);
        addText(text);

        text = new Text("=");
        text.setFill(LG_SIGN_COLOR);
        addText(text);

        text = new Text('"' + lineArray[3] + '"');
        text.setFill(VALUE_COLOR);
        addText(text);

        text = new Text("?>" + NEW_LINE);
        text.setFill(LG_SIGN_COLOR);
        addText(text);
    }

    private void setTagText(String line, int start, int end) {
        line = line.substring(start, end);
        String[] tokens = line.split(" |=");
        Text text, separator;
        List<Text> attrTokens = new LinkedList<>();
        text = new Text(tokens[0]); //tag name
        text.setFill(TAG_COLOR);
        attrTokens.add(text);
        //attributes
        for (int i = 1; i < tokens.length; i += 2) {
            if (i == 1) {
                separator = new Text(" ");
                separator.setFill(TAG_COLOR);
                attrTokens.add(separator);
            }
            text = new Text(tokens[i]);
            text.setFill(ATTRIBUTE_COLOR);
            attrTokens.add(text);//name
            separator = new Text("=");
            separator.setFill(TAG_COLOR);
            attrTokens.add(separator);
            text = new Text(tokens[i + 1]);
            text.setFill(VALUE_COLOR);
            attrTokens.add(text);//value
            separator = new Text(" ");
            attrTokens.add(separator);
        }
        attrTokens.forEach((txt) -> {
            addText(txt);
        });
    }

    private void setDataText(String line, int start, int end) {
        Text text = new Text(line.substring(start, start + 1));
        text.setFill(LG_SIGN_COLOR);
        addText(text);

        if (start + 1 != end - 2) {
            text = new Text(line.substring(start + 1, end - 2));
            text.setFill(LG_SIGN_COLOR);
            addText(text);
        }

        text = new Text(line.substring(end - 2, end));
        text.setFill(LG_SIGN_COLOR);
        addText(text);
    }

    private void setOtherText(String line, int start, int end) {
        Text text = new Text(line.substring(start, end));
        text.setFill(LG_SIGN_COLOR);
        addText(text);
    }

    /*
     * boolean reverse was used to avoid implementation of very similar function
     * which would be likely defined as uncollapse(int index)
     * So setting reverse as false is the same as calling potential collapse(int index)
     * and setting reverse as true is the sane as calling potential uncollapse(int index)
     */
    private void collapse(int index, boolean reverse) {
        int depth = 0;

        // Increase start element for only 1 in order for it to be shown
        if (reverse) {
            collapseTracker[index]++;
        } else {
            collapseTracker[index]--;
        }

        for (int i = index; i < lineNumber; i++) {
            if (xmlTagType[i] == XmlTagType.START) {
                depth++;
            } else if (xmlTagType[i] == XmlTagType.END) {
                depth--;
            }

            if (reverse) {
                collapseTracker[i] -= 2;
            } else {
                collapseTracker[i] += 2;
            }

            if (depth == 0) {
                return;
            }
        }
    }

    private int calculateIndex(int y) {
        /* Each element height is 17.
         * This calculation brings us the index of currently shown tag
         */
        int visible = y / 17;
        int visibleIterator = 0;

        int index = -1;

        do {
            index++;
            if (collapseTracker[index] == 0
                    || (collapseTracker[index] == 1 && xmlTagType[index] == XmlTagType.START)) {
                visibleIterator++;
            }
        } while (visibleIterator <= visible);

        return index;
    }

}
