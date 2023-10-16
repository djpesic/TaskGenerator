/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Localization.Language;
import rules.ComplType;
import rules.TemplateType;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author djordje
 */
public class OperandGui {

    private int number;
    private RadioButton templateRadioButton = new RadioButton();
    private RadioButton ruleRadioButton = new RadioButton();
    private TemplateGui templateGui;
    private RuleGui ruleGui;
    private GridPane gridPane = new GridPane();

    public OperandGui(int opNumber, Language language) {
        number = opNumber;
        templateGui = new TemplateGui(language);
        ruleGui = new RuleGui(language);
        changeLanguage(language);
        gridPane.add(templateRadioButton, 0, 0);
        gridPane.add(ruleRadioButton, 1, 0);
        gridPane.add(templateGui.getGui(), 0, 1);
        gridPane.add(ruleGui.getGui(), 1, 1);
        gridPane.setHgap(20);
        gridPane.setVgap(10);
        ColumnConstraints cc = new ColumnConstraints();
        cc.setHalignment(HPos.CENTER);
        gridPane.setPadding(new Insets(10));
        gridPane.getColumnConstraints().addAll(cc, cc);
        RowConstraints rc = new RowConstraints();
        rc.setValignment(VPos.TOP);
        gridPane.getRowConstraints().addAll(new RowConstraints(), rc);
        ToggleGroup tg = new ToggleGroup();
        templateRadioButton.setToggleGroup(tg);
        ruleRadioButton.setToggleGroup(tg);
        templateRadioButton.setSelected(true);
        ruleRadioButton.setOnAction((ActionEvent event) -> {
            if (ruleRadioButton.isSelected()) {
                templateGui.setDisable(true);
                ruleGui.setDisable(false);
            }
        });
        templateRadioButton.setOnAction((ActionEvent event) -> {
            if (templateRadioButton.isSelected()) {
                templateGui.setDisable(false);
                ruleGui.setDisable(true);
            }
        });
    }

    public void changeLanguage(Language language) {
        templateRadioButton.setText(language.template() + number);
        ruleRadioButton.setText(language.rule() + number);
        templateGui.changeLanguage(language);
        ruleGui.changeLanguage(language);
    }

    public void adjustWidth(AnchorPane anchorPane) {
        gridPane.minWidthProperty().bind((anchorPane.minWidthProperty()));
        templateGui.adjustWidth(anchorPane);
        ruleGui.adjustWidth(anchorPane);
    }

    public GridPane getGui() {
        return gridPane;
    }

    //for debugging purposes
    private void showGridLines() {
        gridPane.setStyle("-fx-grid-lines-visible: true");
    }

    public void reloadRules() {
        ruleGui.reloadRules();
    }

    public void reloadTemplates() {
        templateGui.reloadTemplates();
    }

    public TemplateType generateXmlObjectTree() {
        TemplateType template = new TemplateType();

        if (templateRadioButton.isSelected()) {
            template.setFileName(templateGui.getFileName());

            ComplType complexity = templateGui.getComplexity();
            template.setInComplexity(complexity);

            List<TemplateType.VarMap> varMap = templateGui.getVarMap();
            template.getVarMap().addAll(varMap);
        } else if (ruleRadioButton.isSelected()) {
            template.setRuleName(ruleGui.getFileName());
        }

        return template;
    }

    public void setTemplate(TemplateType template) {
        if (template.getRuleName() != null) {
            ruleGui.setRule(template.getRuleName());
            ruleRadioButton.setSelected(true);
            templateRadioButton.setSelected(false);
            ruleGui.setDisable(false);
            templateGui.setDisable(true);
        } else {
            templateGui.setTemplate(template);
            ruleRadioButton.setSelected(false);
            templateRadioButton.setSelected(true);
            ruleGui.setDisable(true);
            templateGui.setDisable(false);
        }
    }

}
