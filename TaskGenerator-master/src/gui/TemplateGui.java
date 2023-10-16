/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Localization.Language;
import rules.ComplType;
import complexity.Complexity;
import rules.TemplateType;
import java.io.File;
import java.io.FilenameFilter;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import utils.FileNameLoader;
import xmlprocessors.ComplexityCollector;
import xmlprocessors.PortNamesCollector;

/**
 *
 * @author djordje
 */
public class TemplateGui {

    private Label templateLabel = new Label();
    private ComboBox<String> templateNamesComboBox = new ComboBox<>();
    private VariableMappingTable varMapTable;
    private VBox container;
    private ScrollPane scrollPane;
    private ComplType complexity;

    public TemplateGui(Language language) {
        varMapTable = new VariableMappingTable(language);
        changeLanguage(language);
        scrollPane = new ScrollPane(varMapTable.getTableGui());
        container = new VBox(templateLabel, templateNamesComboBox, new Label(), scrollPane);
        container.setAlignment(Pos.CENTER);
        loadTemplateNames();
    }

    private void loadTemplateNames() {
        FilenameFilter filter = (File file, String string) -> string.endsWith("xml");
        templateNamesComboBox.getItems().clear();
        templateNamesComboBox.getItems().addAll(FileNameLoader.loadNames("src/xml/templates", filter));
        templateNamesComboBox.getSelectionModel().selectFirst();
        templateNamesComboBox.setOnAction(((event) -> {
            fillTable();
        }));
        templateNamesComboBox.fireEvent(new ActionEvent());
    }

    public VBox getGui() {
        return container;
    }

    public void changeLanguage(Language language) {
        templateLabel.setText(language.template());
        templateNamesComboBox.setTooltip(new Tooltip(language.templateName()));
        varMapTable.changeLanguage(language);
    }

    public void adjustWidth(Pane forAdjust) {
        scrollPane.minWidthProperty().bind(forAdjust.minWidthProperty().divide(2));
        container.minWidthProperty().bind(forAdjust.minWidthProperty().divide(2));
        varMapTable.adjustWidth(forAdjust);
    }

    public void setDisable(boolean enabled) {
        templateNamesComboBox.setDisable(enabled);
        varMapTable.setDisable(enabled);
    }

    public void fillTable() {
        String templateName = templateNamesComboBox.getSelectionModel().getSelectedItem();
        if (templateName == null) {
            return;
        }
        PortNamesCollector collector = new PortNamesCollector("src/xml/templates/" + templateName + ".xml");
        collector.startProcessing();
        List<String> names = collector.getPortNames();
        names = names.stream().distinct().collect(Collectors.toList());
        varMapTable.setCurrentNames(names);

        //extract complexity
        ComplexityCollector complCollector = new ComplexityCollector("src/xml/templates/" + templateName + ".xml");
        complCollector.startProcessing();
        complexity = complCollector.getComplexity();

    }

    public void reloadTemplates() {
        loadTemplateNames();
    }

    public ComplType getComplexity() {
        return complexity;
    }

    public String getFileName() {
        String templateName = templateNamesComboBox.getSelectionModel().getSelectedItem();
        return "src/xml/templates/" + templateName + ".xml";
    }

    public List<TemplateType.VarMap> getVarMap() {
        return varMapTable.getVarMap();
    }

    public void setTemplate(TemplateType template) {
        String fileName = template.getFileName();
        fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.lastIndexOf("."));
        templateNamesComboBox.getSelectionModel().select(fileName);
        complexity = template.getInComplexity();
        if (template.getVarMap() != null) {
            varMapTable.setVarMap(template.getVarMap());
        }
        if(template.getVarMap().isEmpty()){
            templateNamesComboBox.fireEvent(new ActionEvent());
        }
    }

}
