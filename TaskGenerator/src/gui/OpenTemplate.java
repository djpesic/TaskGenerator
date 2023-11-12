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
public class OpenTemplate extends OpenXmlFile {

    public OpenTemplate(FXMLDocumentController controller) {
        super("src/xml/templates/", controller);
        label.setText(FXMLDocumentController.getLanguage().templateName());
        stage.setTitle(FXMLDocumentController.getLanguage().loadTemplate());
    }

    @Override
    public void setCallbacks() {
        cancelButton.setOnMouseClicked((MouseEvent event) -> {
            mainGuiController.loadOutputCallback(null);
            stage.close();
        });

        okButton.setOnMouseClicked((MouseEvent event) -> {
            mainGuiController.loadOutputCallback(xmlFolder + getSelectedFile());
            stage.close();
        }
        );

        pane.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                mainGuiController.loadOutputCallback(null);
                stage.close();
            }
        });
    }

}
