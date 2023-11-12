/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.jaxbsupport;

import Localization.Language;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import programSegment.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import gui.FXMLDocumentController;
import gui.treedata.TGException;
import gui.treedata.ViewControllMethodsArchive;
import static gui.treedata.ViewControllMethodsArchive.getTextAndRepair;
import programSegment.InPortType.Port;
import utils.ProgramPortSet;
import utils.ProgramPort;

/**
 *
 * @author Nest
 */
public class ForLoopElement extends ProgramElement {

    private Label labelIter
            = new Label(FXMLDocumentController.getLanguage().iterator());
    private TextField textFieldIterator = new TextField();
    protected GridPane gridPaneIterator = new GridPane();
    
    public void setIterator(String iter){
        textFieldIterator.setText(iter);
    }

    public ForLoopElement(TitledPane titledPane, VBox vBox) {
        super(titledPane, vBox);
        addLoopSpecificElements();
    }

    @Override
    public void generateOutput(SimpleLoop simpleLoop) throws TGException {
        simpleLoop.setFor(getForType());
    }

    private ForType getForType() throws TGException {
        ForType forType = new ForType();

        forType.setInPorts(getInPortType());
        
        String iterName = getTextAndRepair(textFieldIterator);
               
        forType.setOutPorts(getOutPortType());
        if (!iterName.equals("")) {
            Port p  = new InPortType.Port();
            p.setName(iterName);
            p.setInherited(false);
            forType.getInPorts().getPort().add(p);
        }

        return forType;
    }

    @Override
    public void setColor() {
        super.setColor();
        titledPane.setStyle("-fx-background: #"
                + getAdjustedColor(ViewControllMethodsArchive.FOR_LOOP_COLOR) + ";");
    }

    @Override
    protected void addLoopSpecificElements() {
        Label dummy1 = new Label();
        Label dummy2 = new Label();
        addGridPaneRow(labelIter, textFieldIterator, dummy1, dummy2,
                1, gridPaneIterator, true);

        vBox.getChildren().add(vboxIndex++, gridPaneIterator);
    }

    @Override
    protected OutPortType getOutPortType() throws TGException {
        OutPortType outPortType = super.getOutPortType();

        String iterator = getTextAndRepair(textFieldIterator);
        if (!iterator.equals("")) {
            OutPortType.Iter iter = new OutPortType.Iter();
            iter.setName(iterator);
            outPortType.setIter(iter);
        }

        return outPortType;
    }

    @Override
    protected ProgramPortSet outPorts() {
        ProgramPortSet ret = new ProgramPortSet();

        String iterator = getTextAndRepair(textFieldIterator);
        if (!iterator.equals("")) {
            ProgramPort p = new ProgramPort();
            p.setName(iterator);
            ret.add(p);
        }

        ProgramPortSet basePorts = super.outPorts();
        if (basePorts != null) {
            ret.addAll(basePorts);
        }

        return ret;
    }

    @Override
    public void loadLanguage(Language language) {
        super.loadLanguage(language);

        labelIter.setText(FXMLDocumentController.getLanguage().iterator());
    }
}
