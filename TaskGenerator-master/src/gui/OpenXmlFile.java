/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import java.io.FilenameFilter;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.FileNameLoader;

/**
 * FXML Controller class
 *
 * @author djordje
 */
public abstract class OpenXmlFile {

    protected Button cancelButton = new Button("Cancel");
    protected Button okButton = new Button("Ok");
    protected ComboBox<String> fileList = new ComboBox<>();
    protected FXMLDocumentController mainGuiController;
    protected String xmlFolder;
    protected Stage stage;
    protected Label label;
    protected AnchorPane pane;
    protected FilenameFilter filter;

    public abstract void setCallbacks();

    public void show() {
        stage.show();
    }

    public OpenXmlFile(String folder, FXMLDocumentController controller) {
        xmlFolder = folder;
        mainGuiController = controller;
        try {
            stage = new Stage();

            pane = new AnchorPane();
            pane.setMaxSize(330, 330);
            pane.setMinSize(330, 330);
            pane.setPrefSize(330, 330);

            cancelButton = new Button(FXMLDocumentController.getLanguage().cancel());
            cancelButton.setLayoutX(190);
            cancelButton.setLayoutY(150);
            cancelButton.setMnemonicParsing(false);
            cancelButton.setPrefSize(100, 30);

            okButton = new Button(FXMLDocumentController.getLanguage().ok());
            okButton.setLayoutX(62);
            okButton.setLayoutY(150);
            okButton.setMnemonicParsing(false);
            okButton.setPrefSize(70, 30);

            fileList.setLayoutX(75);
            fileList.setLayoutY(57);
            fileList.setPrefWidth(150);
            setFileFilter();
            fileList.getItems().addAll(FileNameLoader.loadNames(xmlFolder, filter));
            fileList.getSelectionModel().selectFirst();

            label = new Label(FXMLDocumentController.getLanguage().loadTemplate());
            label.setLayoutX(110);
            label.setLayoutY(27);

            pane.getChildren().addAll(label, fileList, okButton, cancelButton);

            Scene scene = new Scene(pane);
            stage.setResizable(false);
            stage.setScene(scene);
            setCallbacks();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getSelectedFile() {
        return fileList.getSelectionModel().getSelectedItem() + ".xml";
    }

    public void setFileFilter() {
        filter = (File file, String string) -> string.endsWith("xml");
    }
}
