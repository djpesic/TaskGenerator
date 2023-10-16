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
 * @author rtrk
 */
public class OpenRule extends OpenXmlFile {

    public OpenRule(FXMLDocumentController controller) {
        super("src/xml/rules/", controller);
        label.setText(FXMLDocumentController.getLanguage().ruleName());
        stage.setTitle(FXMLDocumentController.getLanguage().loadRule());
    }

    @Override
    public void setCallbacks() {
        cancelButton.setOnMouseClicked((MouseEvent event) -> {
            mainGuiController.loadRuleCallback(null);
            stage.close();
        });

        okButton.setOnMouseClicked((MouseEvent event) -> {
            mainGuiController.loadRuleCallback(xmlFolder + getSelectedFile());
            stage.close();
        }
        );
        pane.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                mainGuiController.loadRuleCallback(null);
                stage.close();
            }
        });
    }

    @Override
    public void setFileFilter() {
        filter = (File file, String string) -> string.endsWith("xml") && !string.contains("Output");
    }

}
