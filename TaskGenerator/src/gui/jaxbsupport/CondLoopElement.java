/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.jaxbsupport;

import Localization.Language;
import rules.ComplType;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import programSegment.CondType;
import programSegment.OutPortType;
import javafx.scene.control.TextField;
import gui.FXMLDocumentController;
import utils.ProgramPort;
import gui.treedata.TGException;
import javafx.scene.control.Alert;
import utils.ProgramPortSet;

/**
 *
 * @author Nest
 */
public abstract class CondLoopElement extends ProgramElement {
    
    private Label labelDirection
            = new Label(FXMLDocumentController.getLanguage().direction());
    private ChoiceBox<String> choiceBoxDirection = new ChoiceBox<>();
    private GridPane gridPaneDirection = new GridPane();
    private GridPane gridPaneBottom = new GridPane();
    
    public enum Direction {
        TO, DOWNTO
    };
    
    public CondLoopElement(TitledPane titledPane, VBox vBox) {
        super(titledPane, vBox);
        addLoopSpecificElements();
    }
    
    public void setDirection(Direction direction) {
        if (direction == direction.TO) {
            choiceBoxDirection.setValue("To");
        } else if (direction == Direction.DOWNTO) {
            choiceBoxDirection.setValue("Downto");
        }
    }
    
    protected CondType getCondType() throws TGException {
        CondType condType = new CondType();
        CondType.Ports ports = new CondType.Ports();
        
        ports.setInPorts(getInPortType());
        ports.setOutPorts(getOutPortType());
        condType.setPorts(ports);
        
        if (choiceBoxDirection.getValue() != null) {
            condType.setDirection(choiceBoxDirection.getValue().toString()
                    .toLowerCase());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(FXMLDocumentController.getLanguage()
                    .directionError());
            alert.showAndWait();
            throw new TGException(FXMLDocumentController.getLanguage()
                    .directionError());
        }
        return condType;
    }
    
    @Override
    protected void addLoopSpecificElements() {
        Label dummy1 = new Label();
        Label dummy2 = new Label();
        
        addGridPaneRow(labelDirection, choiceBoxDirection, dummy1, dummy2, 1,
                gridPaneDirection, false);
        vBox.getChildren().add(vboxIndex++, gridPaneDirection);
        
        choiceBoxDirection.setItems(FXCollections.observableArrayList("To", "Downto"));
        vBox.getChildren().add(vboxIndex++, gridPaneBottom);
    }
    
    @Override
    protected OutPortType getOutPortType() throws TGException {
        OutPortType outPortType = super.getOutPortType();

        /* This element has already been added as LoopVarName
         *  and it is needed to add it again so it can be registered as
         *  out port.
         */
        return outPortType;
    }
    
    @Override
    protected ProgramPortSet outPorts() {
        ProgramPortSet ret = new ProgramPortSet();
        ProgramPort p;
        
        ProgramPortSet basePorts = super.outPorts();
        if (basePorts != null) {
            ret.addAll(basePorts);
        }
        
        return ret;
    }
    
    @Override
    public void loadLanguage(Language language) {
        super.loadLanguage(language);
        
        labelDirection.setText(FXMLDocumentController
                .getLanguage().direction());
        
    }
}
