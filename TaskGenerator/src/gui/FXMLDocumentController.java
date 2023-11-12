/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Localization.English;
import Localization.Language;
import Localization.Serbian;
import rules.ConnectionType;
import rules.OperationType;
import rules.Rule;
import rules.TemplateType;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import gui.treedata.TGException;
import gui.treedata.TreeManager;
import gui.treedata.ViewControllMethodsArchive.InputAction;
import gui.treedata.ViewControllMethodsArchive.LanguageOption;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import utils.XmlMarshaller;

/**
 *
 * @author Nest
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextFlow xmlTextFlow;
    private static XMLPresenter xmlPresenter;

    @FXML
    private TextFlow codeTextFlow;
    private static CodePresenter codePresenter;

    private final static LinkedList<ToggleButton> toggleButtonControlList
            = new LinkedList<>();

    @FXML
    private ToggleButton forButton;

    @FXML
    private ToggleButton whileButton;

    @FXML
    private ToggleButton repeatButton;

    @FXML
    private ToggleButton expressionButton;

    @FXML
    private ToggleButton removeButton;

    @FXML
    private ToggleButton ifButton;

    public ToggleGroup toggleGroupControl = new ToggleGroup();
    public static ToggleGroup toggleGroupControlStatic;

    @FXML
    private Button generateButton;

    @FXML
    private Button exportButton;

    @FXML
    private Button copyXMLButton;

    @FXML
    private Button copyCodeButton;

    private final static LinkedList<ToggleButton> toggleButtonLanguageList
            = new LinkedList<>();

    @FXML
    private ToggleButton serbianLanguageButton;

    @FXML
    private ToggleButton englishLanguageButton;

    public ToggleGroup toggleGroupLanguage = new ToggleGroup();
    public static ToggleGroup toggleGroupLanguageStatic;
    @FXML
    private ToolBar toolBarRule;
    @FXML
    private BorderPane toolBarRuleBorderPane;
    @FXML
    private Button generateRuleButton;
    @FXML
    private Button loadRuleButton;
    @FXML
    private Button saveRuleButton;
    @FXML
    private Button deleteTemplateButton;
    @FXML
    private Button deleteRuleButton;

    @FXML
    private ScrollPane rootProgramPane;

    @FXML
    private ToolBar toolBar;

    @FXML
    private BorderPane toolBarBorderPane;

    private static TreeManager treeManager = null;
    private static Language language;
    private LanguageOption oldLanguage;
    private static InputAction inputAction = InputAction.NONE;
    private static boolean holdingControl = false;
    @FXML
    private AnchorPane XMLAnchorPane;
    @FXML
    private Button loadTemplateButton;
    @FXML
    private Button saveTemplateButton;
    @FXML
    private TabPane mainTabPane;
    @FXML
    private Tab templateTab;
    @FXML
    private Tab ruleTab;
    @FXML
    private SplitPane splitHorizontal;
    @FXML
    private SplitPane splitVerticalTop;
    @FXML
    private SplitPane splitVerticalBottom;
    @FXML
    private AnchorPane anchorTopLeft;
    @FXML
    private AnchorPane anchorBottomLeft;
    @FXML
    private AnchorPane anchorTop;
    @FXML
    private AnchorPane anchorTopRight;
    @FXML
    private AnchorPane anchorBottom;
    @FXML
    private AnchorPane anchorBottomRight;
    private OperandGui topLeftGui;
    private OperandGui topRightGui;
    private OperationGui bottomLeftGui;
    private ConnectionsGui bottomRightGui;
    private String generatedRule;

    private static EventHandler<ScrollEvent> scrollEventHelper = new EventHandler<ScrollEvent>() {
        @Override
        public void handle(ScrollEvent event) {
            if (FXMLDocumentController.getHoldingControl()) {
                if (event.getDeltaY() < 0) {
                    nextAction();
                } else {
                    previousAction();
                }
                event.consume();
            }
        }
    };

    public static EventHandler<ScrollEvent> getScrollEventHelper() {
        return scrollEventHelper;
    }

    public static InputAction getInputAction() {
        return inputAction;
    }

    public static Language getLanguage() {
        if (language == null) {
            language = new Serbian();
        }
        return language;
    }

    public static boolean getHoldingControl() {
        return holdingControl;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        initializeToggleElements();
        initializeTreeData();
        loadLanguage();
        adjustToolbarWidth();
        moveButtonsToFront();
        fixSplitPaneSeparators();
        createTopLeft();
        createTopRight();
        createBottomLeft();
        createBottomRight();
    }

    private void createTopLeft() {
        topLeftGui = new OperandGui(1, language);
        anchorTopLeft.getChildren().add(topLeftGui.getGui());
        topLeftGui.adjustWidth(anchorTopLeft);
    }

    private void createTopRight() {
        topRightGui = new OperandGui(2, language);
        anchorTopRight.getChildren().add(topRightGui.getGui());
        topRightGui.adjustWidth(anchorTopRight);
    }

    private void createBottomLeft() {
        bottomLeftGui = new OperationGui(language);
        anchorBottomLeft.getChildren().add(bottomLeftGui.getGui());
        bottomLeftGui.adjustWidth(anchorBottomLeft);
    }

    private void createBottomRight() {
        bottomRightGui = new ConnectionsGui(language);
        anchorBottomRight.getChildren().add(bottomRightGui.getGui());
        bottomRightGui.adjustWidth(anchorBottomRight);
    }

    private void fixSplitPaneSeparators() {
        anchorTopLeft.maxWidthProperty().bind(splitVerticalTop.widthProperty().multiply(0.5));
        anchorTopLeft.minWidthProperty().bind(splitVerticalTop.widthProperty().multiply(0.5));
        anchorTopRight.maxWidthProperty().bind(splitVerticalTop.widthProperty().multiply(0.5));
        anchorTopRight.minWidthProperty().bind(splitVerticalTop.widthProperty().multiply(0.5));
        anchorBottomLeft.maxWidthProperty().bind(splitVerticalTop.widthProperty().multiply(0.5));
        anchorBottomLeft.minWidthProperty().bind(splitVerticalTop.widthProperty().multiply(0.5));
        anchorBottomRight.maxWidthProperty().bind(splitVerticalTop.widthProperty().multiply(0.5));
        anchorBottomRight.minWidthProperty().bind(splitVerticalTop.widthProperty().multiply(0.5));
        anchorTop.maxHeightProperty().bind(splitHorizontal.heightProperty().multiply(0.5));
        anchorTop.minHeightProperty().bind(splitHorizontal.heightProperty().multiply(0.5));
    }

    public void initializeToggleElements() {
        initializeToggleControlElements();
        initializeToggleLanguagelElements();
    }

    public void initializeToggleControlElements() {
        forButton.setUserData(InputAction.ADD_FOR_LOOP);
        whileButton.setUserData(InputAction.ADD_WHILE_LOOP);
        repeatButton.setUserData(InputAction.ADD_REPEAT_LOOP);
        expressionButton.setUserData(InputAction.ADD_CONST_EXPRESSION);
        removeButton.setUserData(InputAction.DELETE);
        ifButton.setUserData(InputAction.ADD_IF_SEGMENT);

        toggleButtonControlList.add(forButton);
        toggleButtonControlList.add(whileButton);
        toggleButtonControlList.add(repeatButton);
        toggleButtonControlList.add(expressionButton);
        toggleButtonControlList.add(ifButton);
        toggleButtonControlList.add(removeButton);

        for (ToggleButton toggleButton : toggleButtonControlList) {
            toggleButton.setToggleGroup(toggleGroupControl);
        }

        toggleGroupControl.selectedToggleProperty()
                .addListener(new ChangeListener<Toggle>() {
                    @Override
                    public void changed(ObservableValue<? extends Toggle> ov,
                            Toggle toggle, Toggle new_toggle) {
                        if (new_toggle == null) {
                            inputAction = InputAction.NONE;
                        } else {
                            inputAction = (InputAction) toggleGroupControl
                                    .getSelectedToggle().getUserData();
                        }
                    }
                });

        xmlPresenter = new XMLPresenter(xmlTextFlow);
        codePresenter = new CodePresenter(codeTextFlow);
        toggleGroupControlStatic = toggleGroupControl;
    }

    public void initializeToggleLanguagelElements() {
        serbianLanguageButton.setUserData(LanguageOption.SERBIAN);
        englishLanguageButton.setUserData(LanguageOption.ENGLISH);

        toggleButtonLanguageList.add(serbianLanguageButton);
        toggleButtonLanguageList.add(englishLanguageButton);

        for (ToggleButton toggleButton : toggleButtonLanguageList) {
            toggleButton.setToggleGroup(toggleGroupLanguage);
        }

        toggleGroupLanguage.selectedToggleProperty()
                .addListener(new ChangeListener<Toggle>() {
                    @Override
                    public void changed(ObservableValue<? extends Toggle> ov,
                            Toggle toggle, Toggle new_toggle) {
                        LanguageOption newLanguage;

                        if (new_toggle == null) {
                            newLanguage = oldLanguage;
                        } else {
                            newLanguage = (LanguageOption) toggleGroupLanguage
                                    .getSelectedToggle().getUserData();
                            oldLanguage = newLanguage;
                        }

                        switch (newLanguage) {
                            case SERBIAN:
                                language = new Serbian();
                                if (new_toggle == null) {
                                    serbianLanguageButton.setSelected(true);
                                }
                                break;
                            case ENGLISH:
                                language = new English();
                                if (new_toggle == null) {
                                    englishLanguageButton.setSelected(true);
                                }
                                break;
                        }

                        loadLanguage();
                    }
                });

        // Set Serbian as default language
        oldLanguage = LanguageOption.SERBIAN;
        serbianLanguageButton.setSelected(true);
    }

    public static void nextAction() {
        switch (inputAction) {
            case ADD_FOR_LOOP:
                toggleButtonControlList.getFirst().fire();
                break;
            case NONE:
                toggleButtonControlList.getLast().fire();
                break;
            default:
                Toggle toggle = toggleGroupControlStatic.getSelectedToggle();
                for (ToggleButton toggleButton : toggleButtonControlList) {
                    if (toggle == toggleButton) {
                        int index = toggleButtonControlList.indexOf(toggleButton);
                        toggleButtonControlList.get(index - 1).fire();
                    }
                }
        }
    }

    public static void previousAction() {
        switch (inputAction) {
            case NONE:
                toggleButtonControlList.getFirst().fire();
                break;
            case DELETE:
                toggleButtonControlList.getLast().fire();
                break;
            default:
                Toggle toggle = toggleGroupControlStatic.getSelectedToggle();
                for (ToggleButton toggleButton : toggleButtonControlList) {
                    if (toggle == toggleButton) {
                        int index = toggleButtonControlList.indexOf(toggleButton);
                        toggleButtonControlList.get(index + 1).fire();
                    }
                }
        }
    }

    public void initializeTreeData() {
        VBox vBox = new VBox();
        TitledPane titledPane = new TitledPane(language.program(), vBox);
        treeManager = new TreeManager(titledPane, vBox, rootProgramPane);
    }

    public static void generateOutputWrapper() {
        if (treeManager != null) {
            try {
                treeManager.generateOutput();
            } catch (TGException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    public void generateOutput() {
        try {
            treeManager.generateOutput();
        } catch (TGException exception) {
            showErrorDialog(language.generationError(), exception.toString());
        }
    }

    public static void setProgramOutput(String text) {
        codePresenter.setText(text);
    }

    public static void setXMLOutput(String text) {
        xmlPresenter.setText(text);
    }

    @FXML
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().toString().equals("CONTROL")) {
            holdingControl = true;
        }

        if (holdingControl && keyEvent.getCode().toString().equals("L")) {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("FXMLProgramLanguageDialog.fxml"));
                Stage stage = new Stage();
                stage.setTitle(language.programLanguage());
                stage.setScene(new Scene(root, 300, 155));
                stage.getIcons().add(new Image("file:src/gui/images/app_icon.png"));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode().toString().equals("CONTROL")) {
            holdingControl = false;
        }
    }

    private void showErrorDialog(String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(language.error());
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void loadLanguage() {
        // load Control buttons text
        forButton.setTooltip(new Tooltip(language.forLoop()));
        whileButton.setTooltip(new Tooltip(language.whileLoop()));
        repeatButton.setTooltip(new Tooltip(language.repeatLoop()));
        expressionButton.setTooltip(new Tooltip(language.expression()));
        removeButton.setTooltip(new Tooltip(language.remove()));
        generateButton.setTooltip(new Tooltip(language.generate()));
        exportButton.setTooltip(new Tooltip(language.exportGeneratedCode()));
        ifButton.setTooltip(new Tooltip(language.If()));
        loadTemplateButton.setTooltip((new Tooltip(language.loadTemplate())));
        saveTemplateButton.setTooltip((new Tooltip(language.saveTemplate())));
        deleteTemplateButton.setTooltip(new Tooltip(language.deleteTemplate()));

        englishLanguageButton.setTooltip(new Tooltip(language.english()));
        serbianLanguageButton.setTooltip(new Tooltip(language.serbian()));

        copyXMLButton.setTooltip(new Tooltip(language.copy()));
        copyCodeButton.setTooltip(new Tooltip(language.copy()));

        templateTab.setText(language.templateCreation());
        ruleTab.setText(language.ruleCreation());
        generateRuleButton.setTooltip(new Tooltip(language.generate()));
        loadRuleButton.setTooltip(new Tooltip(language.loadRule()));
        saveRuleButton.setTooltip(new Tooltip(language.saveRule()));
        deleteRuleButton.setTooltip(new Tooltip(language.deleteRule()));

        if (topLeftGui != null) {
            topLeftGui.changeLanguage(language);
        }

        if (topRightGui != null) {
            topRightGui.changeLanguage(language);
        }

        if (bottomLeftGui != null) {
            bottomLeftGui.changeLanguage(language);
        }

        if (bottomRightGui != null) {
            bottomRightGui.changeLanguage(language);
        }

        if (treeManager != null) {
            treeManager.loadLanguage(language);
        }
    }

    public void adjustToolbarWidth() {
        toolBarBorderPane.prefWidthProperty().bind(toolBar.widthProperty());
    }

    @FXML
    public void copyXML() {
        String XMLoutput = xmlPresenter.toString();
        if (XMLoutput != null) {
            copyToClipboard(XMLoutput);
        }
    }

    @FXML
    public void copyCode() {
        String codeOutput = codePresenter.toString();
        if (codeOutput != null) {
            copyToClipboard(codeOutput);
        }
    }

    @FXML
    public void copyCodeIn() {
        copyCodeButton.setOpacity(1);
    }

    @FXML
    public void copyCodeOut() {
        copyCodeButton.setOpacity(0.2);
    }

    @FXML
    public void copyXMLIn() {
        copyXMLButton.setOpacity(1);
    }

    @FXML
    public void copyXMLOut() {
        copyXMLButton.setOpacity(0.2);
    }

    private void copyToClipboard(String text) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(text);
        clipboard.setContent(content);
    }

    private void moveButtonsToFront() {
        copyXMLButton.toFront();
        copyCodeButton.toFront();
    }

    @FXML
    public void exportDocument() {
        TaskGeneratorGUI.exportDocument(codePresenter.toString());
    }

    @FXML
    private void loadOutput(MouseEvent event) {
        TaskGeneratorGUI.openTemplateFile(this);
    }

    public void loadOutputCallback(String templateName) {
        if (templateName != null) {
            treeManager.loadTemplate(templateName);
        }
    }

    @FXML
    private void saveOutput(MouseEvent event) {
        TaskGeneratorGUI.saveTemplateFile(this);
    }

    public void saveOutputCallback(String templateName) {
        String xmlText = xmlPresenter.getRawText();
        if (templateName != null && xmlText != null) {
            boolean exists = treeManager.isTemplateExists(templateName);
            boolean overwrite = false;
            if (exists) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setContentText(language.overwrite());
                Optional<ButtonType> result = alert.showAndWait();
                if (result != null && result.isPresent()) {
                    if (result.get() == ButtonType.OK) {
                        overwrite = true;
                    }
                }
            }
            if (overwrite || !exists) {
                treeManager.saveTemplate(templateName, xmlText);
                topLeftGui.reloadTemplates();
                topRightGui.reloadTemplates();
                bottomLeftGui.reloadTemplates();
            }
        }

    }

    @FXML
    private void generateRule(MouseEvent event) {
        Rule rule = new Rule();
        TemplateType template1 = topLeftGui.generateXmlObjectTree();
        TemplateType template2 = topRightGui.generateXmlObjectTree();
        OperationType operation = bottomLeftGui.generateXmlObjectTree();
        List<ConnectionType> connections = bottomRightGui.generateXmlObjectTree();
        rule.getTemplate().add(template1);
        rule.getTemplate().add(template2);
        rule.setOperation(operation);
        operation.getConnection().addAll(connections);
        generatedRule = XmlMarshaller.marshallRule(rule);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText("Rule generated");
        alert.showAndWait();
    }

    @FXML
    private void loadRule(MouseEvent event) {
        TaskGeneratorGUI.openRuleFile(this);
    }

    @FXML
    private void saveRule(MouseEvent event) {
        TaskGeneratorGUI.saveRuleFile(this);
    }

    public void loadRuleCallback(String ruleName) {
        if (ruleName != null) {

            Rule rule = treeManager.loadRule(ruleName);
            topLeftGui.setTemplate(rule.getTemplate().get(0));
            topRightGui.setTemplate(rule.getTemplate().get(1));
            bottomLeftGui.setOperation(rule.getOperation());
            if (rule.getOperation().getConnection() != null) {
                bottomRightGui.setConnections(rule.getOperation().getConnection());
            }
        }
    }

    public void saveRuleCallback(String ruleName) {
        if (ruleName != null && generatedRule != null) {
            boolean exists = treeManager.isRuleExists(ruleName);
            boolean overwrite = false;
            if (exists) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setContentText(language.overwrite());
                Optional<ButtonType> result = alert.showAndWait();
                if (result != null && result.isPresent()) {
                    if (result.get() == ButtonType.OK) {
                        overwrite = true;
                    }
                }
            }
            if (overwrite || !exists) {
                treeManager.saveRule(ruleName, generatedRule);
                topRightGui.reloadRules();
                topLeftGui.reloadRules();
            }
        }

    }

    @FXML
    private void deleteOutput(MouseEvent event) {
        TaskGeneratorGUI.deleteTemplateFile(this);
    }

    public void deleteOutputCallback(String templateName) {
        if (templateName != null) {
            treeManager.deleteTemplate(templateName);
            topLeftGui.reloadTemplates();
            topRightGui.reloadTemplates();
            bottomLeftGui.reloadTemplates();
        }
    }

    @FXML
    private void deleteRule(MouseEvent event) {
        TaskGeneratorGUI.deleteRuleFile(this);
    }

    public void deleteRuleCallback(String ruleName) {
        if (ruleName != null) {
            treeManager.deleteRule(ruleName);
            topRightGui.reloadRules();
            topLeftGui.reloadRules();
        }
    }

}
