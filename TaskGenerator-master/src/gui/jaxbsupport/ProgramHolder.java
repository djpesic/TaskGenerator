/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.jaxbsupport;

import Localization.Language;
import gui.FXMLDocumentController;
import java.util.LinkedList;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import programSegment.ConstType;
import programSegment.IfType;
import programSegment.ProgramType;
import javafx.geometry.Insets;
import gui.treedata.TGException;
import gui.treedata.ViewControllMethodsArchive;
import static gui.treedata.ViewControllMethodsArchive.PANE_PADDING;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import utils.ProgramPortSet;

/**
 *
 * @author Nest
 */
public abstract class ProgramHolder extends BaseElement {

    protected LinkedList<ProgramElement> programList = new LinkedList<>();

    protected final VBox programVBox = new VBox();
    protected static ProgramHolder draggedProgram;

    public ProgramHolder(TitledPane titledPane, VBox vBox) {
        super(titledPane, vBox);

        vBox.getChildren().add(programVBox);
        initializeDragAndDrop();
    }

    @Override
    protected void myAction(MouseEvent event, double positionY) {
        if (event.getButton().equals(MouseButton.SECONDARY)) {
            try {
                FXMLDocumentController.generateOutputWrapper();
            }  catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        if (event.getButton().equals(MouseButton.PRIMARY)) {
            ViewControllMethodsArchive.InputAction inputAction
                    = ViewControllMethodsArchive.getInputAction();

            switch (inputAction) {
                case ADD_FOR_LOOP:
                case ADD_WHILE_LOOP:
                case ADD_REPEAT_LOOP:
                case ADD_CONST_EXPRESSION:
                case ADD_IF_SEGMENT:
                    addProgramAction(inputAction, positionY);
                    break;
                case DELETE:
                    removeProgramAction();
                    break;
            }
        }
    }

    protected void removeExpression(ConstExpression sideExpression) {
        programVBox.getChildren().remove(sideExpression.getGridPane());
    }

    public ProgramElement addProgramAction(ViewControllMethodsArchive.InputAction inputAction) {
        ProgramElement program = null;
        String title = null;
        VBox vBox = new VBox();
        TitledPane titledPane = new TitledPane();

        switch (inputAction) {
            case ADD_FOR_LOOP:
                title = "for";
                program = new ForLoopElement(titledPane, vBox);
                break;
            case ADD_WHILE_LOOP:
                title = "while";
                program = new WhileLoopElement(titledPane, vBox);
                break;
            case ADD_REPEAT_LOOP:
                title = "repeat";
                program = new RepeatLoopElement(titledPane, vBox);
                break;
            case ADD_CONST_EXPRESSION:
                title = "expression";
                program = new ConstExpression(titledPane, vBox);
                break;
            case ADD_IF_SEGMENT:
                title = "if";
                program = new IfElement(titledPane, vBox);
        }

        titledPane.setText(title);
        titledPane.setContent(vBox);
        titledPane.setPadding(new Insets(PANE_PADDING));
        addProgram(program);
        return program;
    }

    private void addProgramAction(ViewControllMethodsArchive.InputAction inputAction, double positionY) {
        ProgramElement program = null;
        String title = null;
        VBox vBox = new VBox();
        TitledPane titledPane = new TitledPane();

        switch (inputAction) {
            case ADD_FOR_LOOP:
                title = "for";
                program = new ForLoopElement(titledPane, vBox);
                break;
            case ADD_WHILE_LOOP:
                title = "while";
                program = new WhileLoopElement(titledPane, vBox);
                break;
            case ADD_REPEAT_LOOP:
                title = "repeat";
                program = new RepeatLoopElement(titledPane, vBox);
                break;
            case ADD_CONST_EXPRESSION:
                title = "expression";
                program = new ConstExpression(titledPane, vBox);
                break;
            case ADD_IF_SEGMENT:
                title = "if";
                program = new IfElement(titledPane, vBox);
        }

        titledPane.setText(title);
        titledPane.setContent(vBox);
        titledPane.setPadding(new Insets(PANE_PADDING));
        addProgram(program, positionY);
    }

    protected void addProgram(ProgramElement program, Double positionY) {
        /* Add loop at the calculated position.
         * Offset 50 is required in order to enable
         * proper first element placement.
         */
        if (program != null) {
            String[] tokens = program.titledPane.getText().split("\t");
            program.titledPane.setText(tokens[0]);
            positionY = positionY - programVBox.getLayoutY() - 50;

            int programIndex;

            for (programIndex = 0; programIndex < programList.size(); programIndex++) {
                if (positionY < programVBox.getChildren().get(programIndex).getLayoutY()) {
                    break;
                }
            }

            programVBox.getChildren().add(programIndex, program.titledPane);
            program.setParent(this);
            program.setColor();
            programList.add(programIndex, program);

            adjustPadding();
        }
    }

    protected void addProgram(ProgramElement program) {
        /* Add loop.
         * Offset 50 is required in order to enable
         * proper first element placement.
         */
        if (program != null) {
            String[] tokens = program.titledPane.getText().split("\t");
            program.titledPane.setText(tokens[0]);

            int programIndex;

            programVBox.getChildren().add(program.titledPane);
            program.setParent(this);
            program.setColor();
            programList.add(program);

            adjustPadding();
        }
    }

    protected enum CustomProgramType {

        NONE, LOOP, NESTED, SEQUENCE, CONST, _IF
    }

    protected abstract CustomProgramType getCustomProgramType();

    protected ConstType getConstType() throws TGException {
        return null;
    }

    protected IfType getIfType() throws TGException {
        return null;
    }

    protected abstract ProgramType getProgramType() throws TGException;

    protected abstract void adjustPadding();

    protected void removeProgramAction() {
        if (parent != null) {
            parent.programVBox.getChildren().remove(titledPane);
            parent.programList.remove(this);
            if (parent instanceof IfElement) {
                ((IfElement) parent).removeFromThenElse(this);
            }
            parent.adjustPadding();

            if(parent.parent == null && parent.programList.isEmpty()) {
                SegmentElement segmentElement = (SegmentElement) parent;
                segmentElement.removeComplexityElement();
            }
        }
    }

    protected int parentCount() {
        int parents = 0;
        ProgramHolder iterator = this;

        while (iterator.parent != null) {
            iterator = iterator.parent;
            parents++;
        }

        return parents;
    }

    protected int colorStep() {
        int parentCount = parentCount();
        //starting element always got 1 parent, reduce it to get the choosen color with no editting
        parentCount--;

        int multiplier = parentCount % 5;
        if ((parentCount / 5) % 2 == 1) {
            multiplier = 5 - multiplier;
        }

        int colorStep = (multiplier - 1) * 10;
        return colorStep;
    }

    protected String getAdjustedColor(int color) {
        //Change the background color by this step for each nesting
        int colorStep = colorStep();

        int redColor = (color >> 16) + colorStep;
        int greenColor = ((color >> 8) & 0xff) + colorStep;
        int blueColor = (color & 0xff) + colorStep;

        //don't increment once max is reached
        if (redColor > 0xff) {
            redColor = 0xff;
        }
        if (greenColor > 0xff) {
            greenColor = 0xff;
        }
        if (blueColor > 0xff) {
            blueColor = 0xff;
        }
        if (blueColor < 0) {
            blueColor = 0;
        }
        if (redColor < 0) {
            redColor = 0;
        }
        if (greenColor < 0) {
            greenColor = 0;
        }

        color = (redColor << 16) + (greenColor << 8) + blueColor;
        String ret = Integer.toHexString(color);

        //add zeros in front to keep the RRGGBB format
        while (ret.length() < 6) {
            ret = "0" + ret;
        }

        return ret;
    }

    /*
     Limit determines when will parent stop going through loops
     from the loop list. When it reaches the loop which called the function
     then it will stop.
     allInPortsRange is called recursively setting it self as the limit
     so that it catches all loops "before" the current loop.
     */
    protected ProgramPortSet allInPortsRange(ProgramHolder limit) {
        ProgramPortSet ret = new ProgramPortSet();

        // Go through all loop children up to the limit
        for (ProgramElement childLoop : programList) {
            if (childLoop == limit) {
                break;
            }

            ProgramPortSet childPorts = childLoop.allInPortsFullDepth();
            if (childPorts != null) {
                ret.addAll(childPorts);
            }
        }

        // Go through all loop parents up to the limit
        if (parent != null) {
            ProgramPortSet parentPorts = parent.allInPortsRange(this);
            if (parentPorts != null) {
                ret.addAll(parentPorts);
            }
        }
        return ret;
    }

    @Override
    public void loadLanguage(Language language) {

        programList.stream().forEach((prog) -> {
            prog.loadLanguage(language);
        });

    }

    protected void initializeDragAndDrop() {
        ProgramHolder thisProgramHolder = this;

        initDragOver();

        initDragDropped(thisProgramHolder);

    }

    protected void initDragDropped(ProgramHolder thisProgramHolder) {
        titledPane.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                ProgramHolder target = thisProgramHolder;
                if (parent != null) {
                    final int padding = ViewControllMethodsArchive.PANE_PADDING;

                    double x = event.getX();
                    double y = event.getY();

                    // height and width size where the padding region begins
                    double width = thisProgramHolder.titledPane.getWidth() - padding;
                    double height = thisProgramHolder.titledPane.getHeight() - padding;

                    //when draged to padding area, set parent as primary
                    if (x < padding || x > width || y < padding || y > height) {
                        target = thisProgramHolder.parent;
                    }
                }
                Dragboard db = event.getDragboard();
                event.setDropCompleted(true);

                if (!target.parentDragged() && !(target instanceof ConstExpression)) {

                    if (target instanceof IfElement) {
                        IfElement ifTarget = (IfElement) target;
                        if (ifTarget.isProperlyClicked()) {
                            draggedProgram.removeProgramAction();
                        }
                    } else {
                        draggedProgram.removeProgramAction();
                    }
                    target.addProgram((ProgramElement) draggedProgram, event.getY());
                }

                event.consume();
            }
        });
    }

    protected void initDragOver() {
        titledPane.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                if (event.getGestureSource() != titledPane) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                event.consume();
            }
        });
    }

    private boolean parentDragged() {
        boolean ret = false;
        ProgramHolder programHolder = this;

        while (programHolder != null) {
            if (programHolder == draggedProgram) {
                ret = true;
            }

            programHolder = programHolder.parent;
        }

        return ret;
    }
}
