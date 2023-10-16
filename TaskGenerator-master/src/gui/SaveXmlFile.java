/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author djordje
 */
public abstract class SaveXmlFile {

    protected Button cancelButton;
    protected Button okButton;
    protected TextField templateNameText = new TextField();
    protected FXMLDocumentController mainGuiController;
    protected Stage stage;
    protected String xmlFolder;
    protected Label label;
    protected AnchorPane pane;

    public abstract void setCallbacks();

    public void show() {
        stage.show();
    }

    public SaveXmlFile(String folder, FXMLDocumentController controller) {
        this.mainGuiController = controller;
        xmlFolder = folder;
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

            templateNameText.setLayoutX(75);
            templateNameText.setLayoutY(57);
            templateNameText.setPrefWidth(150);

            label = new Label(FXMLDocumentController.getLanguage().saveTemplate());
            label.setLayoutX(110);
            label.setLayoutY(27);

            pane.getChildren().addAll(label, templateNameText, okButton, cancelButton);

            Scene scene = new Scene(pane);
            stage.setResizable(false);
            stage.setScene(scene);
            setCallbacks();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getTemplateName() {
        return templateNameText.getText() + ".xml";
    }
}
