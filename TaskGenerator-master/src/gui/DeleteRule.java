/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author djordje
 */
public class DeleteRule extends OpenXmlFile {

    public DeleteRule(FXMLDocumentController controller) {
        super("src/xml/rules/", controller);
    }

    @Override
    public void setCallbacks() {
        cancelButton.setOnMouseClicked((MouseEvent event) -> {
            mainGuiController.deleteRuleCallback(null);
            stage.close();
        });

        okButton.setOnMouseClicked((MouseEvent event) -> {
            mainGuiController.deleteRuleCallback(xmlFolder + getSelectedFile());
            stage.close();
        }
        );

        pane.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                mainGuiController.deleteRuleCallback(null);
                stage.close();
            }
        });
    }

    @Override
    public void setFileFilter() {
        filter = (File file, String string) -> string.endsWith("xml") && !string.contains("Output");
    }

}
