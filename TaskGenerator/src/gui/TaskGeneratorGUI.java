/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Localization.Language;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Nest
 */
public class TaskGeneratorGUI extends Application {

    private static Stage stage;
    private static File lastPath = null;
    private static String lastPathTemplate = null;

    public static void openTemplateFile(FXMLDocumentController controller) {
        OpenXmlFile loadTemplate = new OpenTemplate(controller);
        loadTemplate.show();
    }

    public static void openRuleFile(FXMLDocumentController controller) {
        OpenXmlFile loadRule = new OpenRule(controller);
        loadRule.show();
    }

    public static void saveTemplateFile(FXMLDocumentController controller) {
        SaveXmlFile saveTemplate = new SaveTemplate(controller);
        saveTemplate.show();
    }

    public static void saveRuleFile(FXMLDocumentController controller) {
        SaveXmlFile saveRule = new SaveRule(controller);
        saveRule.show();
    }

    public static void deleteTemplateFile(FXMLDocumentController controller) {
        DeleteTemplate deleteTemplate = new DeleteTemplate(controller);
        deleteTemplate.show();
    }

    public static void deleteRuleFile(FXMLDocumentController controller) {
        DeleteRule deleteRule = new DeleteRule(controller);
        deleteRule.show();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        this.stage = stage;

        Scene scene = new Scene(root);
        stage.setScene(scene);

        scene.setOnScroll(FXMLDocumentController.getScrollEventHelper());

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());

        stage.setTitle("Task Generator");
        stage.getIcons().add(new Image("file:src/gui/images/app_icon.png"));

        stage.show();
    }

    public static void exportDocument(String content) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export generated code");
        FileChooser.ExtensionFilter extFilter
                = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        String currentDir = System.getProperty("user.home");
        File path;

        if (lastPath == null) {
            path = new File(currentDir);
        } else {
            path = lastPath;
        }

        fileChooser.setInitialDirectory(path);
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            lastPath = file.getParentFile();
            try {
                FileWriter fileWriter;
                fileWriter = null;
                fileWriter = new FileWriter(file);
                fileWriter.write(content);
                fileWriter.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
