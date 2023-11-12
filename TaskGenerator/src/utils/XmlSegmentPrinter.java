/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import abstractions.ProgramSegment;
import gui.treedata.TGException;
import java.io.ByteArrayOutputStream;
import java.util.LinkedList;
import java.util.List;
import programSegment.Segment;
import rules.RuleExecutor;
import xmlprocessors.generators.XmlBasedGenerator;

/**
 *
 * @author djordje
 */
public class XmlSegmentPrinter {

    public static void main(String[] args) throws TGException {
        List<String> ruleFiles = new LinkedList<>();
        ruleFiles.add("src/xml/rules/rule1.xml");
        for (String rule : ruleFiles) {
            RuleExecutor ruleExec = new RuleExecutor(rule);
            if (ruleExec.executeRule() != null) {
                ProgramSegment seg = ruleExec.generateCode();
                System.out.println(seg.toString());
            } else {
                System.out.println("Comlexity can't be calculated, invalid rule");
            }
        }

    }

    public static String generateOutput(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String fileName : args) {
            XmlBasedGenerator gen = new XmlBasedGenerator(fileName);
            ProgramSegment l = gen.generate();
            stringBuilder.append(l.getSyntaxTree().toString());
            System.out.println("" + fileName + ":");
            System.out.println(stringBuilder.toString());
        }

        return stringBuilder.toString();
    }

    public static String generateOutput(ByteArrayOutputStream outputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        XmlBasedGenerator gen = new XmlBasedGenerator(outputStream);
        ProgramSegment l = gen.generate();
        stringBuilder.append(l.getSyntaxTree().toCString());
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    public static String generateOutput(Segment s) {
        XmlBasedGenerator gen = new XmlBasedGenerator(s);
        return gen.generate().getSyntaxTree().toString();
    }
}
