/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlprocessors;

import rules.ComplType;
import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.xml.DocumentContainer;
import programSegment.Segment;

/**
 *
 * @author rtrk
 */
public class ComplexityCollector extends XmlProcessorAdapter {

    private String xmlProgramSegment;
    private Segment xmlSegment;

    public ComplexityCollector(Segment xmlSegment) {
        this.xmlSegment = xmlSegment;
    }

    public ComplexityCollector(String segment) {
        xmlProgramSegment = segment;
    }

    private ComplType complexity = new ComplType();

    public ComplType getComplexity() {
        return complexity;
    }

    @Override
    public void startProcessing() {
        try {
            JXPathContext context;
            if (xmlProgramSegment != null) {
                File f = new File(xmlProgramSegment);
                URL url = f.toURI().toURL();
                DocumentContainer xmlContainer = new DocumentContainer(url);
                context = JXPathContext.newContext(xmlContainer);
            } else {
                context = JXPathContext.newContext(xmlSegment);
            }
            context.setLenient(true);

            for (Iterator<String> it = context.iterate("//complexity/*"); it.hasNext();) {
                String str = it.next();
                complexity.setFunc(str);
            }
           

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
