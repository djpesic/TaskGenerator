/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.jaxbsupport;

import Localization.Language;
import programSegment.ConstType;
import programSegment.InPortType;
import programSegment.InPortType.Port;
import programSegment.SimpleExprType;
import programSegment.VarType;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import gui.FXMLDocumentController;
import gui.treedata.ViewControllMethodsArchive;
import static gui.treedata.ViewControllMethodsArchive.getTextAndRepair;
import utils.ProgramPort;
import gui.treedata.TGException;
import programSegment.OutPortType;
import utils.ProgramPortSet;

/**
 *
 * @author Nest
 */
public class ConstExpression extends ProgramElement {

    private final Label labelVar
            = new Label(FXMLDocumentController.getLanguage().variable());
    private final Label labelAssignment
            = new Label(ViewControllMethodsArchive.ASSIGNEMENT);

    private final TextField textFieldVar = new TextField();
    private final TextField textFieldExpression = new TextField();

    private final GridPane gridPane = new GridPane();

    public ConstExpression(TitledPane titledPane, VBox vBox) {
        super(titledPane, vBox, false);
        addLoopSpecificElements();
        gridPane.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                myAction(event, event.getY());
                event.consume();
            }
        });
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    @Override
    protected void myAction(MouseEvent event, double positionY) {
        ViewControllMethodsArchive.InputAction inputAction
                = ViewControllMethodsArchive.getInputAction();

        switch (inputAction) {
            case ADD_FOR_LOOP:
            case ADD_WHILE_LOOP:
            case ADD_REPEAT_LOOP:
            case ADD_CONST_EXPRESSION:
            case ADD_IF_SEGMENT:
                parent.myAction(event, positionY);
                break;
            case DELETE:
                super.myAction(event, positionY);
                break;
        }
    }

    public String getVar() {
        return getTextAndRepair(textFieldVar);
    }

    public void setVar(String var) {
        textFieldVar.setText(var);
    }

    public String getExpression() {
        return getTextAndRepair(textFieldExpression);
    }

    public void setExpression(String expression) {
        textFieldExpression.setText(expression);
    }

    public TextField getTextFieldExpression() {
        return textFieldExpression;
    }

    @Override
    public void loadLanguage(Language language) {
        labelVar.setText(FXMLDocumentController.getLanguage().variable());
    }

    @Override
    public void generateOutput(ConstType constType) throws TGException {
        constType.setExpr(getVarType());
        InPortType inPortType = new InPortType();
        //input ports are variables from the right side plus inherited ports
        ProgramPortSet inPorts = collectInputPorts();
        inPortType.getPort().addAll(inPorts.toList());

        //output port is variable from the left side plus all input ports
        OutPortType outPortType = super.getOutPortType();

        ProgramPortSet outPorts = outPorts();
        for (Port p : outPortType.getPort()) {
            outPorts.add((ProgramPort) p);
        }
        outPortType.getPort().clear();
        outPortType.getPort().addAll(outPorts.toList());
        constType.setInPorts(inPortType);
        constType.setOutPorts(outPortType);
    }

    @Override
    protected ProgramPortSet outPorts() {
        ProgramPortSet outPorts = new ProgramPortSet();
        try {
            String rigtSideVar = getVar();
            ProgramPort outPort = new ProgramPort();
            outPort.setName(rigtSideVar);
            outPorts.addAll(collectInputPortsFromExpression());
            outPorts.add(outPort);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return outPorts;
    }

    @Override
    protected void addLoopSpecificElements() {
        addGridPaneRow(labelVar, textFieldVar, labelAssignment,
                textFieldExpression, 2, gridPane, false);

        vBox.getChildren().add(vboxIndex++, gridPane);
    }

    @Override
    public void setColor() {
        super.setColor();
        titledPane.setStyle("-fx-background: #"
                + getAdjustedColor(ViewControllMethodsArchive.CONST_EXPRESSION_COLOR) + ";");
    }

    private ProgramPortSet collectInputPortsFromExpression() throws TGException {
        ProgramPortSet ret = new ProgramPortSet();
        String expression = getTextAndRepair(getTextFieldExpression());
        if (!expression.equals("")) {
            SimpleExprType simpleExprType = getSimpleExprType(expression);
            ret.addAll(getPortsFromSimpleExprType(simpleExprType));
        }
        return ret;
    }

    public ProgramPortSet collectInputPorts() throws TGException {
        ProgramPortSet ret = collectInputPortsFromExpression();
        ret.addAll(allInPorts());
        return ret;
    }

    protected VarType getVarType() throws TGException {
        VarType varType = null;

        String varTypeText = getVar();
        if (!varTypeText.equals("")) {
            varType = new VarType();
            varType.setVarName(varTypeText);

            String expression = getTextAndRepair(getTextFieldExpression());
            if (!expression.equals("")) {
                // Call antlr parser for expressions

                
                varType.setSimpleExpr(getSimpleExprType(expression));
            }
        }

        return varType;
    }

}
