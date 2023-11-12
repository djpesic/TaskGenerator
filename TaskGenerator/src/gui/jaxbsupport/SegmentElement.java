/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.jaxbsupport;

import Localization.Language;
import complexity.Complexity;
import complexity.ComplexityFunctionsLexer;
import complexity.ComplexityFunctionsParser;
import programSegment.*;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import gui.FXMLDocumentController;
import gui.treedata.TGException;
import gui.treedata.ViewControllMethodsArchive;
import static gui.treedata.ViewControllMethodsArchive.UPPER_LABEL_FONT_SIZE;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;

/**
 *
 * @author Nest
 */
public class SegmentElement extends ProgramHolder {

    ScrollPane scrollPane;
    protected Label labelComplexity
            = new Label(FXMLDocumentController.getLanguage().programSegmentComplexity());
    protected Label labelComplexityRefVar
            = new Label(FXMLDocumentController.getLanguage().variable());
    protected Label labelComplexityFunc
            = new Label(FXMLDocumentController.getLanguage().complexityFunction());
    protected TextField textFieldComplexityFunc = new TextField();
    protected GridPane gridPaneComplexity = null;
    protected boolean complexityPaneAdded = false;

    public TextField getTextFieldComplexityFunc() {
        return textFieldComplexityFunc;
    }

    public void clear() {
        //remove complexity panel
        vBox.getChildren().remove(gridPaneComplexity);
        gridPaneComplexity = null;
        complexityPaneAdded = false;
        //remove program elements from root pane
        programList.clear();
        programVBox.getChildren().clear();
    }

    @Override
    public void loadLanguage(Language language) {
        super.loadLanguage(language);
        labelComplexity.setText(FXMLDocumentController.getLanguage().programSegmentComplexity());
        labelComplexityFunc.setText(FXMLDocumentController.getLanguage().complexityFunction());
        labelComplexityRefVar.setText(FXMLDocumentController.getLanguage().variables());

    }

    public SegmentElement(TitledPane titledPane, VBox vBox, ScrollPane scrollPane) {
        super(titledPane, vBox);

        this.scrollPane = scrollPane;

        scrollPane.setContent(titledPane);
        titledPane.setPadding(new Insets(ViewControllMethodsArchive.PANE_PADDING));

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        /*this.titledPane.setPrefWidth(   //Fill left pane when 0.618 golden proportion is set
         (bounds.getWidth() - ViewControllMethodsArchive.PANE_PADDING * 2) * 0.55); 
         */
        this.titledPane.prefWidthProperty().bind(scrollPane.widthProperty());
        this.titledPane.setPrefHeight(
                (bounds.getHeight() - ViewControllMethodsArchive.PANE_PADDING) * 0.8);

        titledPane.setStyle("-fx-background: #"
                + Integer.toHexString(ViewControllMethodsArchive.SEGMENT_COLOR) + ";");
        vBox.setOnScroll(FXMLDocumentController.getScrollEventHelper());

    }

    public void removeComplexityElement() {
        vBox.getChildren().remove(gridPaneComplexity);
        complexityPaneAdded = false;
    }

    private void addComplexityElement(VBox vBox) {
        if (gridPaneComplexity == null) {
            gridPaneComplexity = new GridPane();

            labelComplexity.setFont(Font.font(null, FontWeight.BOLD, UPPER_LABEL_FONT_SIZE));
            addGridPaneRow(labelComplexity, labelComplexityFunc, textFieldComplexityFunc,
                    labelComplexityRefVar, new Label(), gridPaneComplexity, true);
        }
        if (!complexityPaneAdded) {
            vBox.getChildren().add(0, gridPaneComplexity);
            complexityPaneAdded = true;
        }
    }

    public void generateOutput(Segment segment) throws TGException {
        if (programList.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(FXMLDocumentController.getLanguage()
                    .noContentError());
            alert.showAndWait();
            throw new TGException(FXMLDocumentController.getLanguage()
                    .noContentError());
        }

        segment.setProgram(getProgramType());
        ComplType complType = new ComplType();
        String complStr = ViewControllMethodsArchive.getTextAndRepair(textFieldComplexityFunc);

        try {
            ComplexityFunctionsLexer lexer = new ComplexityFunctionsLexer(
                    new ANTLRInputStream(complStr));

            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ComplexityFunctionsParser parser = new ComplexityFunctionsParser(tokens);
            ComplexityFunctionsParser.ComplexityContext complexityCtx = parser.complexity();
            Complexity compl = complexityCtx.compl;
            if (compl != null) {
                if (!compl.isNone()) {
                    complType.setFunc(compl.toString());
                    segment.setComplexity(complType);
                }
            }
        } catch (ParseCancellationException ex) {

        }

    }

    @Override
    protected CustomProgramType getCustomProgramType() {

        if (programList.size() > 1) {
            return CustomProgramType.SEQUENCE;
        }

        if (programList.size() == 1) {
            if (programList.get(0).programList.size() > 0) {
                return CustomProgramType.NESTED;
            } else {
                if (programList.get(0) instanceof ConstExpression) {
                    return CustomProgramType.CONST;
                } else if (programList.get(0) instanceof IfElement) {
                    return CustomProgramType._IF;
                }
                return CustomProgramType.LOOP;
            }
        }

        return CustomProgramType.NONE;
    }

    @Override
    protected ProgramType getProgramType() throws TGException {
        ProgramType progType = new ProgramType();

        LoopType loopType = new LoopType();
        switch (getCustomProgramType()) {
            case NESTED:
                Nested nested = new Nested();
                progType.setLoop(loopType);
                loopType.setNested(nested);
                programList.get(0).generateOutput(nested);

                break;
            case SEQUENCE:
                Sequence sequence = new Sequence();
                progType.setLoop(loopType);
                loopType.setSequence(sequence);

                for (ProgramElement listElement : programList) {
                    listElement.generateOutput(sequence);
                }
                break;
            case LOOP:
                SimpleLoop simpleLoop = new SimpleLoop();
                progType.setLoop(loopType);
                loopType.setSimpleLoop(simpleLoop);
                programList.get(0).generateOutput(simpleLoop);
                break;

            case CONST: {
                progType.setConst(getConstType());
                break;
            }
            case _IF: {
                progType.setIf(getIfType());
                break;
            }
            default:
                return null;
        }

        return progType;
    }

    @Override
    protected IfType getIfType() throws TGException {
        IfType ifType = new IfType();
        programList.get(0).generateOutput(ifType);
        return ifType;
    }

    @Override
    protected ConstType getConstType() throws TGException {
        ConstType constType = new ConstType();
        programList.get(0).generateOutput(constType);
        return constType;
    }

    @Override
    protected void adjustPadding() {
        //TODO: implement padding
    }

    @Override
    protected void myAction(MouseEvent event, double positionY) {
        super.myAction(event, positionY);
        ViewControllMethodsArchive.InputAction inputAction
                = ViewControllMethodsArchive.getInputAction();

        switch (inputAction) {
            case ADD_FOR_LOOP:
            case ADD_WHILE_LOOP:
            case ADD_REPEAT_LOOP:
            case ADD_CONST_EXPRESSION:
            case ADD_IF_SEGMENT:
                addComplexityElement(vBox);
                break;
        }
    }

    public void addComplexityPane() {
        addComplexityElement(vBox);
    }
}
