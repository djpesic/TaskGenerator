/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 *
 * @author Nest
 */
public class FXMLProgramLanguageDialogController implements Initializable {

    @FXML
    private Label topLabel;

    @FXML
    private RadioButton pascalLanguage;

    @FXML
    private RadioButton cLanguage;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    final ToggleGroup languageGroup = new ToggleGroup();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pascalLanguage.setToggleGroup(languageGroup);
        cLanguage.setToggleGroup(languageGroup);

        topLabel.setText(
                FXMLDocumentController.getLanguage().programLanguage() + ":");
        okButton.setText(FXMLDocumentController.getLanguage().ok());
        cancelButton.setText(FXMLDocumentController.getLanguage().cancel());
    }

    @FXML
    public void ok() {
        ((Stage) topLabel.getScene().getWindow()).close();
    }

    @FXML
    public void cancel() {
        ((Stage) topLabel.getScene().getWindow()).close();
    }
}
