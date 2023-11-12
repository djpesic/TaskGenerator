/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author rtrk
 */
public class SaveTemplate extends SaveXmlFile {

    public SaveTemplate(FXMLDocumentController controller) {
        super("src/xml/templates/", controller);
        label.setText(FXMLDocumentController.getLanguage().templateName());
        stage.setTitle(FXMLDocumentController.getLanguage().saveTemplate());
    }

    @Override
    public void setCallbacks() {
        cancelButton.setOnMouseClicked((MouseEvent event) -> {
            mainGuiController.saveOutputCallback(null);
            stage.close();
        });

        okButton.setOnMouseClicked((MouseEvent event) -> {
            mainGuiController.saveOutputCallback(xmlFolder + getTemplateName());
            stage.close();
        }
        );

        pane.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                mainGuiController.saveOutputCallback(null);
                stage.close();
            }
        });
    }

}
