/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.treedata;

import javafx.scene.control.TextField;
import gui.FXMLDocumentController;

/**
 *
 * @author Nest
 */
public class ViewControllMethodsArchive {

    public static final int FOR_LOOP_COLOR = 0xf48fb1;
    public static final int REPEAT_LOOP_COLOR = 0x9fa8da;
    public static final int WHILE_LOOP_COLOR = 0xe1bee7;
    public static final int SEGMENT_COLOR = 0x80cbc4;
    public static final int CONST_EXPRESSION_COLOR = 0xffa700;
    public static final int IF_SEGMENT_COLOR = 0X268e16;

    public static final int PANE_PADDING = 10;
    public static final int PANE_PADDING_TOP = 20;
    public static final int GRID_PANE_GAP = 10;
    public static final int GRID_PANE_PADDING = 2;

    public static final int UPPER_LABEL_FONT_SIZE = 14;

    public static final double FIRST_ELEMENT_WIDTH = 0.61803398875 * 0.38196601125;
    public static final double SECOND_ELEMENT_WIDTH = 0.38196601125 * 0.38196601125;
    public static final double THIRD_ELEMENT_WIDTH = 0.38196601125 * 0.61803398875;
    public static final double FOURTH_ELEMENT_WIDTH = 1 - FIRST_ELEMENT_WIDTH
            - SECOND_ELEMENT_WIDTH - THIRD_ELEMENT_WIDTH;

    public static final double GRID_PANE_GAP_RELATED_OFFSET = 0.0082;
    public static final double FIFTH_DUMMY_ELEMENT_WIDTH = FOURTH_ELEMENT_WIDTH
            - SECOND_ELEMENT_WIDTH + GRID_PANE_GAP_RELATED_OFFSET;

    public static final String ASSIGNEMENT = "\t:=";

    public static final String OPERAND_STUB = "#";

    public enum InputAction {

        NONE,
        ADD_FOR_LOOP,
        ADD_WHILE_LOOP,
        ADD_REPEAT_LOOP,
        ADD_CONST_EXPRESSION,
        ADD_IF_SEGMENT,
        DELETE
    }

    public enum LanguageOption {

        SERBIAN,
        ENGLISH
    }

    public static InputAction getInputAction() {
        return FXMLDocumentController.getInputAction();
    }

    public static String getTextAndRepair(TextField textField) {
        String ret = textField.getText();

        //remove multiple white spaces
        ret = ret.trim().replaceAll(" +", " ");
        textField.setText(ret);

        return ret;
    }
}
