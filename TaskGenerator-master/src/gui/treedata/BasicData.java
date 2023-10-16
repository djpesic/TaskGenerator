/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.treedata;

import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Nest
 */
public class BasicData {
    private TitledPane titledPane;
    private VBox vBox;
    
    public TitledPane getTitledPane() {
        return titledPane;
    }
    
    public VBox getVBox() {
        return vBox;
    }
    
    public BasicData(TitledPane titledPane, VBox vBox) {
        this.titledPane = titledPane;
        this.vBox = vBox;
    }
}
