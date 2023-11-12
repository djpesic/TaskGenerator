/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.treedata;

import Localization.Language;
import rules.Rule;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import gui.jaxbsupport.SegmentElement;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import programSegment.Segment;
import gui.FXMLDocumentController;
import java.io.File;
import java.io.FileInputStream;
import utils.XmlMarshaller;
import utils.XmlSegmentPrinter;
import xmlprocessors.GuiBuilder;
import xmlprocessors.generators.XmlBasedGenerator;

/**
 *
 * @author Nest
 */
public class TreeManager {

    private SegmentElement rootElement;

    public TreeManager(TitledPane titledPane, VBox vbox, ScrollPane pane) {
        rootElement = new SegmentElement(titledPane, vbox, pane);
    }

    public void generateOutput() throws TGException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Segment.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Segment segment = new Segment();
            rootElement.generateOutput(segment);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PrintWriter printWriter = new PrintWriter(byteArrayOutputStream);
            jaxbMarshaller.marshal(segment, printWriter);
            String outString = byteArrayOutputStream.toString();
            FXMLDocumentController.setXMLOutput(outString);

            // generate output with call to backend generator
            String output = XmlSegmentPrinter.generateOutput(byteArrayOutputStream);

            FXMLDocumentController.setProgramOutput(output);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void loadTemplate(String template) {
        Segment root = XmlMarshaller.unmarshallSegment(XmlBasedGenerator.PROGRAM_SEGMENT_SCHEMA, template);
        GuiBuilder xmlGuiBuilder = new GuiBuilder(rootElement, root);
        xmlGuiBuilder.startProcessing();
    }

    public void saveTemplate(String template, String text) {
        writeFile(template, text);
    }

    public void loadLanguage(Language language) {
        rootElement.loadLanguage(language);
    }

    public Rule loadRule(String ruleName) {
        return XmlMarshaller.unmarshallRule(XmlBasedGenerator.RULE_SCHEMA, ruleName);
    }

    public void saveRule(String ruleName, String xmlText) {
        writeFile(ruleName, xmlText);
    }

    private void writeFile(String name, String text) {
        PrintWriter writer;
        try {
            writer = new PrintWriter(new File(name));
            writer.write(text);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void deleteFile(String templateName) {
        try {
            File f = new File(templateName);
            f.delete();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteTemplate(String templateName) {
        deleteFile(templateName);
    }

    public void deleteRule(String ruleName) {
        deleteFile(ruleName);
    }

    public boolean isRuleExists(String ruleName) {
        return fileExists(ruleName);
    }

    public boolean isTemplateExists(String templateName) {
        return fileExists(templateName);
    }

    private boolean fileExists(String fileName) {
        try {
            FileInputStream input = new FileInputStream(new File(fileName));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
