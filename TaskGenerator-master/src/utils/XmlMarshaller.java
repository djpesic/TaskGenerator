/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import rules.Rule;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintWriter;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import programSegment.Segment;

/**
 *
 * @author djordje
 */
public class XmlMarshaller {

    public static Segment unmarshallSegment(String schemaLocation, ByteArrayOutputStream xmlOutputStream) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(new File(schemaLocation)));
            JAXBContext jc = JAXBContext.newInstance(Segment.class);
            javax.xml.bind.Unmarshaller unmarshaller = jc.createUnmarshaller();
            unmarshaller.setSchema(schema);
            Segment ret = (Segment) unmarshaller.unmarshal(new ByteArrayInputStream(xmlOutputStream.toByteArray()));
            return ret;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static Segment unmarshallSegment(String schemaLocation, String xmlLocation) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(new File(schemaLocation)));
            JAXBContext jc = JAXBContext.newInstance(Segment.class);
            javax.xml.bind.Unmarshaller unmarshaller = jc.createUnmarshaller();
            unmarshaller.setSchema(schema);
            Segment ret = (Segment) unmarshaller.unmarshal(new StreamSource(xmlLocation));
            return ret;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static Rule unmarshallRule(String schemaLocation, String xmlLocation) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(new File(schemaLocation)));
            JAXBContext jc = JAXBContext.newInstance(Rule.class);
            javax.xml.bind.Unmarshaller unmarshaller = jc.createUnmarshaller();
            unmarshaller.setSchema(schema);
            Rule ret = (Rule) unmarshaller.unmarshal(new StreamSource(xmlLocation));
            return ret;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void marshallSegment(Segment segment, String output) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Segment.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            PrintWriter printWriter = new PrintWriter(new File(output));
            jaxbMarshaller.marshal(segment, printWriter);
            printWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static String marshallRule(Rule rule) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Rule.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            jaxbMarshaller.marshal(rule, output);
            return output.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
