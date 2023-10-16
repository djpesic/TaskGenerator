/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlprocessors;

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
 * @author djordje
 */
public class PortNamesCollector extends XmlProcessorAdapter {

    private List<String> portNames = new LinkedList<>();
    private String xmlProgramSegment;
    private Segment xmlSegment;

    public PortNamesCollector(Segment xmlSegment) {
        this.xmlSegment = xmlSegment;
    }

    public PortNamesCollector(String xmlProgramSegment) {
        this.xmlProgramSegment = xmlProgramSegment;
    }

    public List<String> getPortNames() {
        return portNames;
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
            for (Iterator<String> it = context.iterate("//port/@name"); it.hasNext();) {
                portNames.add(it.next());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
