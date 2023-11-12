/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Localization.Language;
import rules.ComplType;
import rules.OperationType;
import rules.OperationType.Selection;
import rules.TemplateType;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author djordje
 */
public class OperationGui {

    private GridPane container = new GridPane();
    private RadioButton sequenceRadioButton = new RadioButton();
    private RadioButton nestingRadioButton = new RadioButton();
    private RadioButton selectionRadioButton = new RadioButton();
    private ToggleGroup toggleGroup = new ToggleGroup();
    private CheckBox elseCheckBox = new CheckBox();
    private TemplateGui selectionTemplate;

    public OperationGui(Language language) {
        selectionTemplate = new TemplateGui(language);
        GridPane pane = new GridPane();
        pane.addRow(0, sequenceRadioButton, nestingRadioButton, selectionRadioButton, elseCheckBox);
        pane.setHgap(20);
        container.addRow(0, pane);
        container.addRow(1, selectionTemplate.getGui());
        container.setPadding(new Insets(10));
        sequenceRadioButton.setToggleGroup(toggleGroup);
        sequenceRadioButton.setSelected(true);
        nestingRadioButton.setToggleGroup(toggleGroup);
        selectionRadioButton.setToggleGroup(toggleGroup);
        selectionTemplate.setDisable(true);
        container.setHgap(20);
        container.setVgap(10);
        elseCheckBox.setDisable(true);
        selectionRadioButton.setOnAction((event) -> {
            elseCheckBox.setDisable(false);
            selectionTemplate.setDisable(false);
        });
        sequenceRadioButton.setOnAction((event) -> {
            elseCheckBox.setDisable(true);
            selectionTemplate.setDisable(true);
        });
        nestingRadioButton.setOnAction((event) -> {
            elseCheckBox.setDisable(true);
            selectionTemplate.setDisable(true);
        });
   

        changeLanguage(language);
    }

    public void changeLanguage(Language language) {
        sequenceRadioButton.setText(language.sequence());
        nestingRadioButton.setText(language.nesting());
        selectionRadioButton.setText(language.selection());
        elseCheckBox.setText(language.Else());
        selectionTemplate.changeLanguage(language);
    }

    public GridPane getGui() {
        return container;
    }

    public void adjustWidth(AnchorPane pane) {
        container.minWidthProperty().bind(pane.minWidthProperty());
        selectionTemplate.adjustWidth(pane);
    }

    private void showGridLines() {
        container.setStyle("-fx-grid-lines-visible: true");
    }

    public void reloadTemplates() {
        selectionTemplate.reloadTemplates();
    }

    public OperationType generateXmlObjectTree() {
        OperationType operation = new OperationType();
        if (nestingRadioButton.isSelected()) {
            operation.setNesting(true);
        } else if (sequenceRadioButton.isSelected()) {
            operation.setSequence(true);
        } else if (selectionRadioButton.isSelected()) {
            Selection selection = new OperationType.Selection();
            TemplateType template = new TemplateType();
            template.setFileName(selectionTemplate.getFileName());
            ComplType complexity = selectionTemplate.getComplexity();
            template.setInComplexity(complexity);
            List<TemplateType.VarMap> varMap = selectionTemplate.getVarMap();
            template.getVarMap().addAll(varMap);
            selection.setSelTemplate(template);
            selection.setUseElseBranch(elseCheckBox.isSelected());
            operation.setSelection(selection);
        }
        return operation;

    }

    public void setOperation(OperationType operation) {
        if (operation.isNesting() != null) {
            nestingRadioButton.setSelected(true);
            sequenceRadioButton.setSelected(false);
            selectionRadioButton.setSelected(false);
            selectionTemplate.setDisable(true);
            elseCheckBox.setDisable(true);
            elseCheckBox.setSelected(false);
        } else if (operation.isSequence() != null) {
            nestingRadioButton.setSelected(false);
            sequenceRadioButton.setSelected(true);
            selectionRadioButton.setSelected(false);
            selectionTemplate.setDisable(true);
            elseCheckBox.setDisable(true);
            elseCheckBox.setSelected(false);
        } else if (operation.getSelection() != null) {
            nestingRadioButton.setSelected(false);
            sequenceRadioButton.setSelected(false);
            selectionRadioButton.setSelected(true);
            selectionTemplate.setDisable(false);
            elseCheckBox.setDisable(false);
            Selection sel = operation.getSelection();
            if (sel.isUseElseBranch()) {
                elseCheckBox.setSelected(true);
            } else {
                elseCheckBox.setSelected(false);
            }
            selectionTemplate.setTemplate(sel.getSelTemplate());
        }
    }

}
