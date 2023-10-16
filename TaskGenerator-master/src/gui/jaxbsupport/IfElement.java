/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.jaxbsupport;

import Localization.Language;
import programSegment.*;
import java.util.LinkedList;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import gui.FXMLDocumentController;
import utils.ProgramPort;
import gui.treedata.TGException;
import gui.treedata.ViewControllMethodsArchive;
import static gui.treedata.ViewControllMethodsArchive.getTextAndRepair;
import utils.ProgramPortSet;

/**
 *
 * @author djordje
 */
public class IfElement extends ProgramElement {

    private Label labelLeftSimpleExpr = new Label(FXMLDocumentController.getLanguage().leftSimpleExpr());
    private TextField textFieldLeftSimpleExpr = new TextField();
    private Label labelRightSimpleExpr = new Label(FXMLDocumentController.getLanguage().rightSimpleExpr());
    private TextField textFieldRightSimpleExpr = new TextField();
    private Label labelRelop = new Label(FXMLDocumentController.getLanguage().relop());
    private TextField textFieldRelop = new TextField();
    private GridPane conditionGridPane = new GridPane();
    private Label labelThen = new Label(FXMLDocumentController.getLanguage().Then());
    private GridPane thenGridPane = new GridPane();
    private Label labelElse = new Label(FXMLDocumentController.getLanguage().Else());
    private GridPane elseGridPane = new GridPane();
    private LinkedList<ProgramElement> thenProgramList = new LinkedList<>(), elseProgramList = new LinkedList<>();
    private boolean thenClicked = false, elseClicked = false;
    private boolean thenHold = false;
    private boolean elseHold = false;

    public void setTextFieldLeftSimpleExpr(String textFieldLeftSimpleExpr) {
        this.textFieldLeftSimpleExpr.setText(textFieldLeftSimpleExpr);
    }

    public void setTextFieldRightSimpleExpr(String textFieldRightSimpleExpr) {
        this.textFieldRightSimpleExpr.setText(textFieldRightSimpleExpr);
    }

    public void setTextFieldRelop(String textFieldRelop) {
        this.textFieldRelop.setText(textFieldRelop);
    }

    public void setThenClicked(boolean thenClicked) {
        this.thenClicked = thenClicked;
    }

    public void setElseClicked(boolean elseClicked) {
        this.elseClicked = elseClicked;
    }

    public boolean isProperlyClicked() {
        return thenClicked == true || elseClicked == true;
    }

    public IfElement(TitledPane titledPane, VBox vBox) {
        super(titledPane, vBox, false);
        addLoopSpecificElements();
    }

    @Override
    protected void addLoopSpecificElements() {
        addGridPaneRow(labelLeftSimpleExpr, textFieldLeftSimpleExpr, labelRightSimpleExpr,
                textFieldRightSimpleExpr, 1, conditionGridPane, true);
        vBox.getChildren().add(vboxIndex++, conditionGridPane);

        addGridPaneRow(labelRelop, textFieldRelop, new Label(), new Label(), 2, conditionGridPane, false);
        vBox.getChildren().add(vboxIndex++, new GridPane());

        addGridPaneRow(labelThen, new Label(), new Label(), new Label(), 3, thenGridPane, false);
        thenGridPane.setStyle("-fx-background-color:gray");
        vBox.getChildren().add(vboxIndex++, thenGridPane);

        addGridPaneRow(labelElse, new Label(), new Label(), new Label(), 4, elseGridPane, false);
        vBox.getChildren().add(vboxIndex++, elseGridPane);
        elseGridPane.setStyle("-fx-background-color:#BA9CBB");

        thenGridPane.setOnMouseClicked((MouseEvent event) -> {
            thenClicked = true;
        });
        elseGridPane.setOnMouseClicked((MouseEvent event) -> {
            elseClicked = true;
        });

        thenGridPane.setOnDragDropped((DragEvent event) -> {
            thenClicked = true;
        });
        elseGridPane.setOnDragDropped((DragEvent event) -> {
            elseClicked = true;
        });
    }

    @Override
    public void setColor() {
        super.setColor();
        titledPane.setStyle("-fx-background: #"
                + getAdjustedColor(ViewControllMethodsArchive.IF_SEGMENT_COLOR) + ";");
    }

    @Override
    public void loadLanguage(Language language) {
        super.loadLanguage(language);

        labelElse.setText(FXMLDocumentController.getLanguage().Else());
        labelLeftSimpleExpr.setText(FXMLDocumentController.getLanguage().leftSimpleExpr());
        labelRelop.setText(FXMLDocumentController.getLanguage().relop());
        labelRightSimpleExpr.setText(FXMLDocumentController.getLanguage().rightSimpleExpr());
        labelThen.setText(FXMLDocumentController.getLanguage().Then());

    }

    @Override
    protected void addProgram(ProgramElement program, Double positionY) {
        /* Add loop at the calculated position.
         * Offset 50 is required in order to enable
         * proper first element placement.
         */
        if (elseClicked == false && thenClicked == false) {
            return;
        }
        positionY = positionY - programVBox.getLayoutY() - 50;
        if (program != null) {
            int programIndex;

            for (programIndex = 0; programIndex < programList.size(); programIndex++) {
                if (positionY < programVBox.getChildren().get(programIndex).getLayoutY()) {
                    break;
                }
            }

            programVBox.getChildren().add(programIndex, program.titledPane);
            program.setParent(this);
            program.setColor();
//            programList.add(programIndex, program);

            String[] tokens = program.titledPane.getText().split("\t");
            program.titledPane.setText(tokens[0]);

            if (thenClicked) {
                System.out.println("Then added");
                if (!thenHold) {
                    thenClicked = false;
                }
                thenProgramList.add(program);
                program.titledPane.setText(program.titledPane.getText() + "\t\tIf");
            }
            if (elseClicked) {
                System.out.println("else added");
                if (!elseHold) {
                    elseClicked = false;
                }
                elseProgramList.add(program);
                program.titledPane.setText(program.titledPane.getText() + "\t\tElse");
            }
            adjustPadding();
        }
    }

    @Override
    protected void addProgram(ProgramElement program) {
        /* Add loop.
         * Offset 50 is required in order to enable
         * proper first element placement.
         */
        if (elseClicked == false && thenClicked == false) {
            return;
        }
        if (program != null) {

            programVBox.getChildren().add(program.titledPane);
            program.setParent(this);
            program.setColor();

            String[] tokens = program.titledPane.getText().split("\t");
            program.titledPane.setText(tokens[0]);

            if (thenClicked) {
                System.out.println("Then added");
                if (!thenHold) {
                    thenClicked = false;
                }
                thenProgramList.add(program);
                program.titledPane.setText(program.titledPane.getText() + "\t\tIf");
            }
            if (elseClicked) {
                System.out.println("else added");
                if (!elseHold) {
                    elseClicked = false;
                }
                elseProgramList.add(program);
                program.titledPane.setText(program.titledPane.getText() + "\t\tElse");
            }
            adjustPadding();
        }
    }

    @Override
    public void generateOutput(IfType ifType) throws TGException {
        String relop = ViewControllMethodsArchive.getTextAndRepair(textFieldRelop);
        String leftSimpleExpr = ViewControllMethodsArchive.getTextAndRepair(textFieldLeftSimpleExpr);
        String rightSimpleExpr = ViewControllMethodsArchive.getTextAndRepair(textFieldRightSimpleExpr);
        ifType.setLeft(getSimpleExprType(leftSimpleExpr));
        ifType.setRight(getSimpleExprType(rightSimpleExpr));

        ifType.setRelop(relop);

        LinkedList<ProgramElement> tempProgList = programList;

        programList = thenProgramList;
        if (!programList.isEmpty()) {
            if (programList.size() == 1) {
                ifType.setThenBranch(programList.get(0).getProgramType());
            } else {
                ProgramType progType = new ProgramType();
                LoopType loopType = new LoopType();
                Sequence sequence = new Sequence();
                progType.setLoop(loopType);
                loopType.setSequence(sequence);
                for (ProgramElement listElement : programList) {
                    listElement.generateOutput(sequence);
                }
                ifType.setThenBranch(progType);
            }
        }
        programList = elseProgramList;
        if (!programList.isEmpty()) {
            if (programList.size() == 1) {
                ifType.setElseBranch(programList.get(0).getProgramType());
            } else {
                ProgramType progType = new ProgramType();
                LoopType loopType = new LoopType();
                Sequence sequence = new Sequence();
                progType.setLoop(loopType);
                loopType.setSequence(sequence);
                for (ProgramElement listElement : programList) {
                    listElement.generateOutput(sequence);
                }
                ifType.setElseBranch(progType);
            }
        }
        programList = tempProgList;

        InPortType inPortType = new InPortType();
        //input ports are variables from the left and right expression plus all input ports
        ProgramPortSet inPorts = collectInputPorts();
        inPortType.getPort().addAll(inPorts.toList());

        //output port is variable from the left side plus all input ports
        OutPortType outPortType = super.getOutPortType();

        ProgramPortSet outPorts = outPorts();
        for (InPortType.Port p : outPortType.getPort()) {
            outPorts.add((ProgramPort) p);
        }
        outPortType.getPort().clear();
        outPortType.getPort().addAll(outPorts.toList());
        ifType.setInPorts(inPortType);
        ifType.setOutPorts(outPortType);

    }

    public void removeFromThenElse(ProgramHolder toRemove) {
        thenProgramList.remove(toRemove);
        elseProgramList.remove(toRemove);
    }

    private ProgramPortSet collectInputPortsFromExpressions() throws TGException {
        ProgramPortSet ret = new ProgramPortSet();
        String expression = getTextAndRepair(textFieldLeftSimpleExpr);
        if (!expression.equals("")) {
            SimpleExprType simpleExprType = getSimpleExprType(expression);
            ret.addAll(getPortsFromSimpleExprType(simpleExprType));
        }
        expression = getTextAndRepair(textFieldRightSimpleExpr);
        if (!expression.equals("")) {
            SimpleExprType simpleExprType = getSimpleExprType(expression);
            ret.addAll(getPortsFromSimpleExprType(simpleExprType));
        }
        return ret;
    }

    public ProgramPortSet collectInputPorts() throws TGException {
        ProgramPortSet ret = collectInputPortsFromExpressions();
        ret.addAll(allInPorts());
        return ret;
    }

    @Override
    protected ProgramPortSet outPorts() {
        try {
            return collectInputPortsFromExpressions();
        } catch (TGException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected ProgramPortSet allInPortsFullDepth() {
        ProgramPortSet ret = new ProgramPortSet();
        ProgramPort p;

        LinkedList<ProgramElement> tempProgList = programList;
        programList = thenProgramList;
        for (ProgramElement childLoop : programList) {
            ProgramPortSet childPorts = childLoop.allInPortsFullDepth();
            if (childPorts != null) {
                ret.addAll(childPorts);
            }
        }

        programList = elseProgramList;
        for (ProgramElement childLoop : programList) {
            ProgramPortSet childPorts = childLoop.allInPortsFullDepth();
            if (childPorts != null) {
                ret.addAll(childPorts);
            }
        }
        programList = tempProgList;

        // Add out ports to the list
        ProgramPortSet outPorts = outPorts();
        if (outPorts != null) {
            ret.addAll(outPorts);
        }

        return ret;
    }

    public void holdThenClick() {
        thenHold = true;
    }

    public void unholdThenClick() {
        thenHold = false;
    }

    public void holdElseClick() {
        elseHold = true;
    }

    public void unholdElseClick() {
        elseHold = false;
    }
}
