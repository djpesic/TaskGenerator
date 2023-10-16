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
 * @author djordje
 */
public class DeleteTemplate extends OpenXmlFile {

    public DeleteTemplate(FXMLDocumentController controller) {
        super("src/xml/templates/", controller);
    }

    @Override
    public void setCallbacks() {
        cancelButton.setOnMouseClicked((MouseEvent event) -> {
            mainGuiController.deleteOutputCallback(null);
            stage.close();
        });

        okButton.setOnMouseClicked((MouseEvent event) -> {
            mainGuiController.deleteOutputCallback(xmlFolder + getSelectedFile());
            stage.close();
        }
        );

        pane.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                mainGuiController.deleteOutputCallback(null);
                stage.close();
            }
        });
    }

}
