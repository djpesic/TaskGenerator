/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assemblymanager;

import assemblymanager.genalg.GeneticAlgorithm;
import abstractions.ProgramSegment;
import assemblymanager.topdownguided.GuidedAssembling;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import jplag.JPlag;
import org.antlr.v4.runtime.misc.Triple;
import programSegment.Segment;
import xmlprocessors.generators.XmlBasedGenerator;

/**
 *
 * @author djordje
 */
public class AssemblyManager {

    private Population generatedSegments;
    private Algorithm algorithm;

    public void startAssembling() {
        generatedSegments = algorithm.start();
    }

    public void setAlgorithm(Algorithm alg) {
        algorithm = alg;
    }

    public void processResults(boolean useJplag) {
        try {
            ExecutorService service = null;
            Runnable r = () -> {
                printAllSegmentsToFile();
            };
            if (useJplag) {
                service = Executors.newFixedThreadPool(2);
                Runnable r1 = () -> {
                    sendToJPLag();
                };
                service.submit(r1);
            } else {
                service = Executors.newFixedThreadPool(1);
            }
            service.submit(r);
            service.shutdown();
            service.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void sendToJPLag() {
        String nameBase = "jplag/";
        BufferedWriter writer = null;
        Map<String, List<Segment>> complToSegs = new LinkedHashMap<>();
        try {
            File f = new File("jplag");
            Files.walk(f.toPath())
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (Exception ex) {

        }
        File f = new File("jplag");
        f.mkdir();
        for (Segment seg : generatedSegments.getAll()) {
            if (seg.getComplexity().getFunc().equals("log(n)*n")) {
                seg.getComplexity().setFunc("n*log(n)");
            }
            List<Segment> segs = complToSegs.getOrDefault(seg.getComplexity().getFunc(), new ArrayList<>());
            segs.add(seg);
            complToSegs.put(seg.getComplexity().getFunc(), segs);
        }

        int cntDir = 0;
        XmlBasedGenerator gen = new XmlBasedGenerator();
        for (Map.Entry<String, List<Segment>> entry : complToSegs.entrySet()) {
            try {
                File segDir = new File(nameBase + cntDir);
                File segDir1 = new File(segDir, "segs");
                File complInfo = new File(segDir, "info.txt");
                File jplagOutputCpp = new File(segDir, "jplagOutputCpp.txt");
                File jplagOutputTxt = new File(segDir, "jplagOutputTxt.txt");
                File jplagResultCpp = new File(segDir, "jplagResultCpp");
                File jplagResultTxt = new File(segDir, "jplagResultTxt");
                jplagResultCpp.mkdir();
                jplagResultTxt.mkdir();
                segDir.mkdir();
                segDir1.mkdir();

                writer = new BufferedWriter(new PrintWriter(complInfo));
                writer.write(entry.getKey());
                writer.close();
                int cntSeg = 0;
                for (Segment seg : entry.getValue()) {
                    writer = new BufferedWriter(new PrintWriter(segDir1 + "/seg" + cntSeg + ".cpp"));
                    gen.setXmlSegment(seg);
                    ProgramSegment progSeg = gen.generate();
                    writer.write(progSeg.toCString());
                    writer.close();
                    cntSeg++;
                }
                if (entry.getValue().size() > 1) {
                    String[] jplagArgs = {"-l", "c/c++", "-t", "1", "-vl", "-r",
                        jplagResultCpp.getPath(), segDir1.getPath()};

                    System.setOut(new PrintStream(jplagOutputCpp));
                    JPlag.main(jplagArgs);
                    processJPlagOutput(jplagOutputCpp, segDir);

                    String[] jplagArgs1 = {"-l", "text", "-p", ".cpp", "-t", "1", "-vl", "-r",
                        jplagResultTxt.getPath(), segDir1.getPath()};
                    System.setOut(new PrintStream(jplagOutputTxt));
                    JPlag.main(jplagArgs1);
                    processJPlagOutput(jplagOutputTxt, segDir);

                    System.setOut(System.out);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            cntDir++;
        }

    }

    private void processJPlagOutput(File outputFile, File outputFileDir) {
        // creates and prints similarity matrix from jplag output
        BufferedReader reader = null;
        File processedOutput = null;
        PrintWriter writer = null;

        List<Triple<Integer, Integer, Double>> matrixList = new ArrayList<>();

        try {
            // parse jplag output
            reader = new BufferedReader(new FileReader(outputFile));
            processedOutput = new File(outputFileDir,
                    outputFile.getName().substring(0, outputFile.getName().lastIndexOf('.')) + "SimMatrix.txt");
            writer = new PrintWriter(processedOutput);
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                if (line.contains("Comparing")) {
                    String[] tokens = line.split(" |-|: ");
                    String file1 = tokens[1];
                    String file2 = tokens[2];
                    String compareResultS = tokens[3];
                    double compareResult = Double.parseDouble(compareResultS);
                    String index1 = file1.substring(file1.indexOf("seg") + 3, file1.indexOf("."));
                    String index2 = file2.substring(file2.indexOf("seg") + 3, file2.indexOf("."));
                    int i = Integer.parseInt(index1);
                    int j = Integer.parseInt(index2);
                    matrixList.add(new Triple<>(i, j, compareResult));
                }
            }
            //create similarity matrix
            int maxI = -1, maxJ = -1;
            for (Triple<Integer, Integer, Double> t : matrixList) {
                maxI = Math.max(maxI, t.a);
                maxJ = Math.max(maxJ, t.b);
            }
            int max = Math.max(maxI, maxJ);
            double[][] simMatrix = new double[max + 1][max + 1];
            for (int i = 0; i < max; i++) {
                simMatrix[i][i] = 0;
            }
            for (Triple<Integer, Integer, Double> t : matrixList) {
                simMatrix[t.a][t.b] = t.c;
            }
            for (int i = 0; i <= max; i++) {
                for (int j = 0; j < max; j++) {
                    if (simMatrix[i][j] != 0) {
                        writer.format("%3.2f ", simMatrix[i][j]);
                    }
                }
                if (simMatrix[i][max] != 0) {
                    writer.format("%3.2f ", simMatrix[i][max]);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (Exception ex) {
            }
        }
    }

    private void printAllSegmentsToFile() {
        String results = "results.txt";
        String stat = "stat.txt";
        BufferedWriter writerResults = null, writerStat = null;
        Map<String, Integer> complCounts = new LinkedHashMap<>();
        try {
            XmlBasedGenerator gen = new XmlBasedGenerator();;
            int i = 0;
            writerResults = new BufferedWriter(new PrintWriter(results));
            writerStat = new BufferedWriter(new PrintWriter(stat));
            for (Segment seg : generatedSegments.getAll()) {
                gen.setXmlSegment(seg);
                ProgramSegment progSeg = gen.generate();

                writerResults.write("Segment " + (i++) + ", complexity\t" + seg.getComplexity().getFunc());
                writerResults.write("\n");
                writerResults.write(progSeg.toString());
                writerResults.write("\n");
                int cnt = complCounts.getOrDefault(seg.getComplexity().getFunc(), -1);
                if (cnt == -1) {
                    complCounts.put(seg.getComplexity().getFunc(), 1);
                } else {
                    complCounts.put(seg.getComplexity().getFunc(), cnt + 1);
                }
            }
            for (Map.Entry<String, Integer> entry : complCounts.entrySet()) {
                writerStat.write(entry.getKey() + "\t" + entry.getValue());
                writerStat.write("\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                writerResults.close();
                writerStat.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static void useGeneticAlg() {
        AssemblyManager manager = new AssemblyManager();
        GeneticAlgorithm assemblyAlgorithm = new GeneticAlgorithm(2, 2000, Population.ShrinkMode.REALTIME);
        manager.setAlgorithm(assemblyAlgorithm);
        manager.startAssembling();
        manager.processResults(false);
    }

    private static void useTopDownGuidedAlg() {
        AssemblyManager manager = new AssemblyManager();
        List<String> compls = new LinkedList<>();
        compls.add("n*log(n)");
        GuidedAssembling assemblyAlgorithm = new GuidedAssembling(100, 20, compls);
        manager.setAlgorithm(assemblyAlgorithm);
        manager.startAssembling();
//        manager.processResults(false);
    }

    public static void main(String[] args) {
        File f = null;
        try {
        f = new File("err.txt");
        System.setErr(new PrintStream(f));
            useGeneticAlg();
//        useTopDownGuidedAlg();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
