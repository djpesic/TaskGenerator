/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Localization.Language;
import rules.ConnectionType;
import java.util.LinkedList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;

/**
 *
 * @author djordje
 */
public class PortMappingTable {

    private TableView<PortMappingData> table;
    private ObservableList<PortMappingData> tableData = FXCollections.observableArrayList();
    TableColumn<PortMappingData, String> t1Outs, t2Ins;

    public PortMappingTable(Language language) {
        table = new TableView<>(tableData);
        t1Outs = new TableColumn<>(language.currentVariables());
        t1Outs.setCellValueFactory(new PropertyValueFactory<>("template1Out"));
        t2Ins = new TableColumn<>(language.newVariables());
        table.getColumns().setAll(t1Outs, t2Ins);
        t2Ins.setCellValueFactory(new PropertyValueFactory<>("template2In"));
//        ObservableList<String> test = FXCollections.observableArrayList();
//        t2Ins.setCellFactory(ComboBoxTableCell.<PortMappingData, String>forTableColumn(test));
        t2Ins.setCellFactory(TextFieldTableCell.<PortMappingData>forTableColumn());
        t1Outs.setCellFactory(TextFieldTableCell.<PortMappingData>forTableColumn());
        table.setEditable(true);
        changeLanguage(language);
    }

    public TableView getTableGui() {
        return table;
    }

    public void changeLanguage(Language language) {
        t1Outs.setText(language.template1OutPorts());
        t2Ins.setText(language.template2InPorts());
    }

    public void adjustWidth(Pane forAdjust) {
        table.minWidthProperty().bind(forAdjust.minWidthProperty().divide(2));
        t1Outs.minWidthProperty().bind(table.minWidthProperty().divide(2));
        t2Ins.minWidthProperty().bind(table.minWidthProperty().divide(2));
        t1Outs.maxWidthProperty().bind(table.maxWidthProperty().divide(2));
        t2Ins.maxWidthProperty().bind(table.maxWidthProperty().divide(2));
    }

    public void setDisable(boolean enabled) {
        table.setDisable(enabled);
    }

    public void addRow(Label rowCnt) {
        tableData.add(new PortMappingData());
        String text = rowCnt.getText().substring(0, rowCnt.getText().indexOf(":") + 2);
        rowCnt.setText(text + tableData.size());
    }

    public void removeRow(Label rowCnt) {
        tableData.remove(tableData.size() - 1);
        String text = rowCnt.getText().substring(0, rowCnt.getText().indexOf(":") + 2);
        rowCnt.setText(text + tableData.size());
    }

    public List<ConnectionType> getConnections() {
        List<ConnectionType> connections = new LinkedList<>();
        tableData.forEach((data) -> {
            if (data.getTemplate1Out() != null && data.getTemplate2In() != null) {
                if (!data.getTemplate1Out().isEmpty() && !data.getTemplate2In().isEmpty()) {
                    ConnectionType connection = new ConnectionType();
                    connection.setInPort(data.getTemplate2In());
                    connection.setOutPort(data.getTemplate1Out());
                    connections.add(connection);
                }
            }

        });
        return connections;
    }

    public void setConnections(List<ConnectionType> connection) {
        tableData.clear();
        connection.forEach((conn) -> {
            PortMappingData data = new PortMappingData();
            data.setTemplate1Out(conn.getOutPort());
            data.setTemplate2In(conn.getInPort());
            tableData.add(data);
        });
    }

}
