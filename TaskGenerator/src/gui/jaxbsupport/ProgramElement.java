/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.jaxbsupport;

import Localization.Language;
import expression.SimpleExpressionLexer;
import expression.SimpleExpressionParser;
import programSegment.ConstType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import programSegment.*;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import gui.FXMLDocumentController;
import utils.ProgramPort;
import gui.treedata.TGException;
import static gui.treedata.ViewControllMethodsArchive.*;
import java.util.Iterator;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.event.EventHandler;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.jxpath.JXPathContext;
import gui.treedata.ViewControllMethodsArchive;
import org.antlr.v4.runtime.ANTLRInputStream;
import utils.ProgramPortSet;
import programSegment.InPortType.Lower;
import programSegment.InPortType.Port;
import programSegment.InPortType.Upper;

/**
 *
 * @author Nest
 */
public abstract class ProgramElement extends ProgramHolder {

    private Label labelCoreElements
            = new Label(FXMLDocumentController.getLanguage().coreElements());

    private Label labelInLower
            = new Label(FXMLDocumentController.getLanguage().lower());
    private Label labelInUpper
            = new Label(FXMLDocumentController.getLanguage().upper());
    private TextField textFieldInLower = new TextField();
    private TextField textFieldInUpper = new TextField();
    protected GridPane gridPaneTop = new GridPane();

    protected int vboxIndex = 0;
    protected ArrayList<String> inPortList;

    public void setInLower(String inLower) {
        textFieldInLower.setText(inLower);
    }

    public void setInUpper(String inUpper) {
        textFieldInUpper.setText(inUpper);
    }

    public ProgramElement(TitledPane titledPane, VBox vBox, boolean includeLoopBounds) {
        super(titledPane, vBox);

        inPortList = new ArrayList<>();

        labelCoreElements.setFont(Font.font(null, FontWeight.BOLD, UPPER_LABEL_FONT_SIZE));
        if (includeLoopBounds) {
            addGridPaneRow(labelCoreElements, labelInLower, textFieldInLower,
                    labelInUpper, textFieldInUpper, gridPaneTop, true);
        }
        vBox.getChildren().add(vboxIndex++, gridPaneTop);
    }

    public ProgramElement(TitledPane titledPane, VBox vBox) {
        this(titledPane, vBox, true);
    }

    protected abstract void addLoopSpecificElements();

    protected InPortType getInPortType() throws TGException {
        InPortType inPortType = new InPortType();

        String lowerName = getTextAndRepair(textFieldInLower);
        if (!lowerName.equals("")) {
            Lower lower = new Lower();
            lower.setName(lowerName);
            inPortType.setLower(lower);
        }

        String upperName = getTextAndRepair(textFieldInUpper);
        if (!upperName.equals("")) {
            Upper upper = new Upper();
            upper.setName(upperName);
            inPortType.setUpper(upper);
        }

        inPortType.getPort().addAll(allInPorts().toList());
        return inPortType;
    }

    protected OutPortType getOutPortType() throws TGException {
        OutPortType outPortType = new OutPortType();

        for (Port port : allInPorts()) {
            outPortType.getPort().add(port);
        }

        return outPortType;
    }

    protected SimpleExprType getSimpleExprType(String expression) throws TGException {
        SimpleExpressionLexer lexer = new SimpleExpressionLexer(new ANTLRInputStream(expression));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SimpleExpressionParser parser = new SimpleExpressionParser(tokens);
        SimpleExpressionParser.SimpleExpressionContext simpleExprTree = parser.simpleExpression();
        SimpleExprType simpleExprType = simpleExprTree.simpleExprType;
        return simpleExprType;
    }

    public void generateOutput(SimpleLoop simpleLoop) throws TGException {
    }

    public void generateOutput(Sequence sequence2)
            throws TGException {
        if (programList.size() > 1) {
            Nested nested = new Nested();
            generateOutput(nested);
            ProgramType progType = new ProgramType();
            LoopType loopType = new LoopType();
            loopType.setNested(nested);
            progType.setLoop(loopType);
            sequence2.getProgram().add(progType);
        } else {
            sequence2.getProgram().add(getProgramType());
        }
    }

    public void generateOutput(Nested nested) throws TGException {
        LoopType outerLoopType = new LoopType();
        LoopType innerLoopType = new LoopType();
        SimpleLoop simpleLoop = new SimpleLoop();

        nested.setOuter(outerLoopType);
        outerLoopType.setSimpleLoop(simpleLoop);
        generateOutput(simpleLoop);

        //checking outside getProgramType and getLoopType to avoid infinite recursions
        if (programList.size() <= 1) {//not a sequence
            nested.setInner(programList.get(0).getProgramType());
        } else {

            ProgramType progType = new ProgramType();
            Sequence sequence = new Sequence();
            progType.setLoop(innerLoopType);
            innerLoopType.setSequence(sequence);
            nested.setInner(progType);
            for (ProgramElement listElement : programList) {
                listElement.generateOutput(sequence);
            }
        }
    }

    @Override
    protected CustomProgramType getCustomProgramType() {
        if (programList.size() > 1) {
            return CustomProgramType.SEQUENCE;
        }

        if (programList.size() == 1) {
            return CustomProgramType.NESTED;
        }
        if (this instanceof ConstExpression) {
            return CustomProgramType.CONST;
        }
        if (this instanceof IfElement) {
            return CustomProgramType._IF;
        }
        return CustomProgramType.LOOP;
    }

    @Override
    protected ProgramType getProgramType() throws TGException {
        ProgramType progType = new ProgramType();
        LoopType loopType = new LoopType();

        switch (getCustomProgramType()) {
            case NESTED:
                Nested nested = new Nested();
                progType.setLoop(loopType);
                loopType.setNested(nested);
                generateOutput(nested);

                break;
            case SEQUENCE:
                ProgramType pt1 = new ProgramType();
                Sequence sequence = new Sequence();
                pt1.setLoop(loopType);
                loopType.setSequence(sequence);
                for (ProgramElement listElement : programList) {
                    listElement.generateOutput(sequence);
                }
                if (parent != null) {
                    nested = new Nested();
                    nested.setInner(pt1);
                    SimpleLoop simpleLoop = new SimpleLoop();
                    generateOutput(simpleLoop);
                    LoopType lt = new LoopType();
                    lt.setSimpleLoop(simpleLoop);
                    nested.setOuter(lt);
                    LoopType lt1 = new LoopType();
                    lt1.setNested(nested);
                    progType.setLoop(lt1);
                }else{
                    progType = pt1;
                }
                break;
            case LOOP:
                SimpleLoop simpleLoop = new SimpleLoop();
                progType.setLoop(loopType);
                loopType.setSimpleLoop(simpleLoop);
                generateOutput(simpleLoop);
                break;

            case CONST: {
                ConstType constType = new ConstType();
                generateOutput(constType);
                progType.setConst(constType);
                break;
            }
            case _IF: {
                IfType ifType = new IfType();
                generateOutput(ifType);
                progType.setIf(ifType);
                break;
            }
            default:
                return null;
        }
        return progType;
    }

    public void setColor() {
        for (ProgramElement program : programList) {
            program.setColor();
        }
    }

    @Override
    protected void adjustPadding() {
        if (vBox.getChildren().indexOf(gridPaneTop) == 0) {
            gridPaneTop.setPadding(new Insets(GRID_PANE_PADDING));
        } else {
            gridPaneTop.setPadding(new Insets(PANE_PADDING_TOP, GRID_PANE_PADDING,
                    GRID_PANE_PADDING, GRID_PANE_PADDING));
        }
    }

    protected ProgramPortSet outPorts() {
        ProgramPortSet ret = new ProgramPortSet();
        return ret;
    }

    protected ProgramPortSet allInPorts() {
        ProgramPortSet ret = new ProgramPortSet();
        ProgramPort p;
        // Include inPorts specific for this loop
        String lowerName = getTextAndRepair(textFieldInLower);
        if (!lowerName.equals("")) {
            p = new ProgramPort();
            p.setName(lowerName);
            ret.add(p);
        }

        String upperName = getTextAndRepair(textFieldInUpper);
        if (!upperName.equals("")) {
            p = new ProgramPort();
            p.setName(upperName);
            ret.add(p);
        }

        ProgramPortSet inheritedPorts = parent.allInPortsRange(this);

        inheritedPorts.forEach((port) -> {
            port.setInherited(true);
        });

        ret.addAll(inheritedPorts);

        return ret;
    }

    @Override
    protected ProgramPortSet allInPortsRange(ProgramHolder limit) {
        ProgramPortSet ret = new ProgramPortSet();
        ProgramPort p;
        String lowerName = getTextAndRepair(textFieldInLower);
        if (!lowerName.equals("")) {
            p = new ProgramPort();
            p.setName(lowerName);
            ret.add(p);
        }

        String upperName = getTextAndRepair(textFieldInUpper);
        if (!upperName.equals("")) {
            p = new ProgramPort();
            p.setName(upperName);
            ret.add(p);
        }

        // Go through all loop parents up to the limit
        ProgramPortSet baseInPorts = super.allInPortsRange(limit);
        if (baseInPorts != null) {
            ret.addAll(baseInPorts);
        }

        // Add out ports to the list
        ProgramPortSet outPorts = outPorts();
        if (outPorts != null) {
            ret.addAll(outPorts);
        }

        return ret;
    }

    protected ProgramPortSet allInPortsFullDepth() {
        ProgramPortSet ret = new ProgramPortSet();
        ProgramPort p;
        String lowerName = getTextAndRepair(textFieldInLower);
        if (!lowerName.equals("")) {
            p = new ProgramPort();
            p.setName(lowerName);
            ret.add(p);
        }

        String upperName = getTextAndRepair(textFieldInUpper);
        if (!upperName.equals("")) {
            p = new ProgramPort();
            p.setName(upperName);
            ret.add(p);
        }

        // Go through all loop children
        for (ProgramElement childLoop : programList) {
            ProgramPortSet childPorts = childLoop.allInPortsFullDepth();
            if (childPorts != null) {
                ret.addAll(childPorts);
            }
        }

        // Add out ports to the list
        ProgramPortSet outPorts = outPorts();
        if (outPorts != null) {
            ret.addAll(outPorts);
        }

        return ret;
    }

    @Override
    public void loadLanguage(Language language) {
        super.loadLanguage(language);

        labelCoreElements.setText(FXMLDocumentController
                .getLanguage().coreElements());
        labelInLower.setText(FXMLDocumentController.getLanguage().lower());
        labelInUpper.setText(FXMLDocumentController.getLanguage().upper());
    }

    @Override
    protected void initializeDragAndDrop() {
        super.initializeDragAndDrop();
        ProgramHolder thisProgramHolder = this;

        titledPane.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard db = titledPane.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putString("transfer");
                db.setContent(content);
                draggedProgram = thisProgramHolder;
                event.consume();
            }
        });
    }

    protected ProgramPortSet getPortsFromSimpleExprType(SimpleExprType simpleExprType) {
        ProgramPortSet ret = new ProgramPortSet();

        try {
            JXPathContext context;

            context = JXPathContext.newContext(simpleExprType);

            context.setLenient(true);
            for (Iterator<String> it = context.iterate("//name | //constant"); it.hasNext();) {
                String portName = it.next();
                ProgramPort pp = new ProgramPort();
                pp.setName(portName);
                if (portName != null) {
                    if (!portName.equals(ViewControllMethodsArchive.OPERAND_STUB)) {
                        try {
                            Double.parseDouble(portName);
                        } catch (NumberFormatException ex) {
                            ret.add(pp);
                        }

                    }
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ret;
    }
}
