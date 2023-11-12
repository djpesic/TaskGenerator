/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Localization.Language;
import rules.TemplateType;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;

/**
 *
 * @author djordje
 */
public class VariableMappingTable {

    private TableView<VariableMappingData> table;
    private ObservableList<VariableMappingData> tableData = FXCollections.observableArrayList();
    TableColumn<VariableMappingData, String> currVars, newVars;

    public VariableMappingTable(Language language) {
        table = new TableView<>(tableData);
        currVars = new TableColumn<>(language.currentVariables());
        currVars.setCellValueFactory(new PropertyValueFactory<>("currentName"));
        newVars = new TableColumn<>(language.newVariables());
        newVars.setCellValueFactory(new PropertyValueFactory<>("newName"));
        newVars.setCellFactory(TextFieldTableCell.<VariableMappingData>forTableColumn());
        table.getColumns().setAll(currVars, newVars);
        table.setEditable(true);
        changeLanguage(language);
    }

    public TableView getTableGui() {
        return table;
    }

    public void changeLanguage(Language language) {
        currVars.setText(language.currentVariables());
        newVars.setText(language.newVariables());
    }

    public void adjustWidth(Pane forAdjust) {
        table.minWidthProperty().bind(forAdjust.minWidthProperty().divide(2));
        currVars.minWidthProperty().bind(table.minWidthProperty().divide(2));
        newVars.minWidthProperty().bind(table.minWidthProperty().divide(2));
        currVars.maxWidthProperty().bind(table.maxWidthProperty().divide(2));
        newVars.maxWidthProperty().bind(table.maxWidthProperty().divide(2));
    }

    public void setDisable(boolean enabled) {
        table.setDisable(enabled);
    }

    public void setCurrentNames(List<String> names) {
        tableData.clear();
        names.forEach((name) -> {
            VariableMappingData data = new VariableMappingData();
            data.setCurrentName(name);
            data.setNewName("");
            tableData.add(data);
        });
    }

    public List<TemplateType.VarMap> getVarMap() {
        List<TemplateType.VarMap> varMap = new LinkedList<>();
        tableData.forEach((data) -> {
            if (data.getNewName() != null) {
                if (!data.getNewName().isEmpty()) {
                    TemplateType.VarMap map = new TemplateType.VarMap();
                    map.setCurrent(data.getCurrentName());
                    map.setActual(data.getNewName());
                    varMap.add(map);
                }
            }

        });
        return varMap;
    }

    public void setVarMap(List<TemplateType.VarMap> varMap) {
        tableData.clear();
        varMap.forEach((mapping) -> {
            VariableMappingData data = new VariableMappingData();
            data.setCurrentName(mapping.getCurrent());
            data.setNewName(mapping.getActual());
            tableData.add(data);
        });
    }
}
