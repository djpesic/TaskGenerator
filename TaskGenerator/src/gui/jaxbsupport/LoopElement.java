/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.jaxbsupport;

import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author djordje
 */
public abstract class LoopElement extends ProgramElement {

    public LoopElement(TitledPane titledPane, VBox vBox) {
        super(titledPane, vBox);
        addLoopSpecificElements();
    }

    @Override
    protected abstract void addLoopSpecificElements();

}
