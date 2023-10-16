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
public class SaveRule extends SaveXmlFile {

    public SaveRule(FXMLDocumentController controller) {
        super("src/xml/rules/", controller);
        label.setText(FXMLDocumentController.getLanguage().ruleName());
        stage.setTitle(FXMLDocumentController.getLanguage().saveRule());
    }

    @Override
    public void setCallbacks() {
        cancelButton.setOnMouseClicked((MouseEvent event) -> {
            mainGuiController.saveRuleCallback(null);
            stage.close();
        });

        okButton.setOnMouseClicked((MouseEvent event) -> {
            mainGuiController.saveRuleCallback(xmlFolder + getTemplateName());
            stage.close();
        }
        );

        pane.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                mainGuiController.saveRuleCallback(null);
                stage.close();
            }
        });
    }

}
