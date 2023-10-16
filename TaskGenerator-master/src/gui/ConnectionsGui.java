/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Localization.Language;
import rules.ConnectionType;
import java.util.List;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author djordje
 */
public class ConnectionsGui {

    private PortMappingTable connectionsTable;
    private GridPane gridPane;
    private ScrollPane scrollPane;
    private Button incButton = new Button("+");
    private Button decButton = new Button("-");
    private Label rowCnt = new Label();
    private Language language;

    public ConnectionsGui(Language language) {
        this.language = language;
        connectionsTable = new PortMappingTable(language);
        gridPane = new GridPane();
        scrollPane = new ScrollPane();
        scrollPane.setContent(connectionsTable.getTableGui());
        gridPane.add(scrollPane, 0, 0);
        GridPane helper = new GridPane();
        helper.add(rowCnt, 0, 0);
        helper.add(incButton, 0, 1);
        helper.setHgap(20);
        helper.setVgap(20);
        incButton.setPrefSize(50, 30);
        decButton.setPrefSize(50, 30);
        incButton.setOnAction((event) -> {
            connectionsTable.addRow(rowCnt);
        });
        decButton.setOnAction((event) -> {
            connectionsTable.removeRow(rowCnt);
        });
        helper.add(decButton, 0, 2);
        gridPane.add(helper, 1, 0);
        gridPane.setHgap(20);
        gridPane.setVgap(10);
        ColumnConstraints cc = new ColumnConstraints();
        cc.setHalignment(HPos.CENTER);
        gridPane.setPadding(new Insets(10));
        gridPane.getColumnConstraints().addAll(cc, cc);
        RowConstraints rc = new RowConstraints();
        rc.setValignment(VPos.TOP);
        gridPane.getRowConstraints().addAll(new RowConstraints(), rc);

        changeLanguage(language);
    }

    public void changeLanguage(Language language) {
        connectionsTable.changeLanguage(language);
        rowCnt.setText(language.rowCount() + ": 0");
    }

    void adjustWidth(AnchorPane toAdjust) {
        gridPane.minWidthProperty().bind((toAdjust.minWidthProperty().divide(2)));
        connectionsTable.adjustWidth(toAdjust);
    }

    public GridPane getGui() {
        return gridPane;
    }

    public List<ConnectionType> generateXmlObjectTree() {
        return connectionsTable.getConnections();
    }

    public void setConnections(List<ConnectionType> connection) {
        rowCnt.setText(language.rowCount() + ": " + connection.size());
        connectionsTable.setConnections(connection);
    }
}
