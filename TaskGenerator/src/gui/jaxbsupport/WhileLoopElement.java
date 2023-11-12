/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.jaxbsupport;

import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import programSegment.SimpleLoop;
import gui.treedata.TGException;
import gui.treedata.ViewControllMethodsArchive;

/**
 *
 * @author Nest
 */
public class WhileLoopElement extends CondLoopElement {

    public WhileLoopElement(TitledPane titledPane, VBox vBox) {
        super(titledPane, vBox);
    }

    @Override
    public void generateOutput(SimpleLoop simpleLoop) throws TGException {
        simpleLoop.setWhile(getCondType());
    }

    @Override
    public void setColor() {
        super.setColor();
        titledPane.setStyle("-fx-background: #" +
                getAdjustedColor(ViewControllMethodsArchive.WHILE_LOOP_COLOR) + ";");
    }
}
