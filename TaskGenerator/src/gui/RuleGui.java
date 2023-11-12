/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Localization.Language;
import java.io.File;
import java.io.FilenameFilter;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import utils.FileNameLoader;

/**
 *
 * @author djordje
 */
public class RuleGui {

    private Label ruleLabel = new Label();
    private ComboBox<String> ruleNames = new ComboBox<>();
    private VBox container;

    public RuleGui(Language language) {
        changeLanguage(language);
        container = new VBox(ruleLabel, ruleNames);
        container.setAlignment(Pos.CENTER);
        container.setSpacing(20);
        setDisable(true);
        loadRuleNames();
    }

    private void loadRuleNames() {
        FilenameFilter filter = (File file, String string) -> string.endsWith("xml") && !string.contains("Output");
        ruleNames.getItems().clear();
        ruleNames.getItems().addAll(FileNameLoader.loadNames("src/xml/rules", filter));
        ruleNames.getSelectionModel().selectFirst();
    }

    public VBox getGui() {
        return container;
    }

    public void changeLanguage(Language language) {
        ruleNames.setTooltip(new Tooltip(language.ruleName()));
        ruleLabel.setText(language.rule());
    }

    void adjustWidth(Pane forAdjust) {

    }

    void setDisable(boolean enabled) {
        ruleNames.setDisable(enabled);
    }

    public void reloadRules() {
        loadRuleNames();
    }

    public String getFileName() {
        String fileName = ruleNames.getSelectionModel().getSelectedItem();
        return "src/xml/rules/" + fileName + ".xml";
    }

    public void setRule(String ruleName) {
        ruleName = ruleName.substring(ruleName.lastIndexOf("/") + 1, ruleName.lastIndexOf("."));
        ruleNames.getSelectionModel().select(ruleName);

    }
}
