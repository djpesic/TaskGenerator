/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlprocessors;

import abstractions.ConstSegment;
import abstractions.IfSegment;
import abstractions.Loop;
import abstractions.ProgramSegment;
import abstractions.ProgramSequence;
import gui.jaxbsupport.CondLoopElement;
import gui.jaxbsupport.ConstExpression;
import gui.jaxbsupport.ForLoopElement;
import gui.jaxbsupport.IfElement;
import gui.jaxbsupport.ProgramElement;
import gui.jaxbsupport.RepeatLoopElement;
import gui.jaxbsupport.SegmentElement;
import gui.jaxbsupport.WhileLoopElement;
import gui.treedata.ViewControllMethodsArchive;
import java.util.List;
import syntaxelements.nonterminals.Expression;
import syntaxelements.nonterminals.Factor;
import syntaxelements.nonterminals.SignedFactor;
import syntaxelements.nonterminals.SimpleExpression;
import syntaxelements.nonterminals.Term;
import syntaxelements.nonterminals.Variable;
import programSegment.CondType;
import programSegment.ConstType;
import programSegment.FactorType;
import programSegment.ForType;
import programSegment.IfType;
import programSegment.InPortType;
import programSegment.LoopType;
import programSegment.Nested;
import programSegment.OutPortType;
import programSegment.ProgramType;
import programSegment.Segment;
import programSegment.Sequence;
import programSegment.SignedFactorType;
import programSegment.SimpleExprType;
import programSegment.SimpleLoop;
import programSegment.TermType;

/**
 *
 * @author djordje
 */
public class GuiBuilder implements XmlProcessor {

    private SegmentElement guiRootElement;
    private ProgramElement guiCurrentElement;
    private ProgramElement guiLastAddedElement;
    private Segment rootSegment;
    String var = "";
    String expression = "";
    private int level = -1;

    public GuiBuilder(SegmentElement guiRootElement, Segment rootSegment) {
        this.guiRootElement = guiRootElement;
        this.rootSegment = rootSegment;
    }

    @Override
    public LoopType processLoopType(LoopType loopType) {
        if (loopType.getNested() != null) {
            processNested(loopType.getNested());
        } else if (loopType.getSequence() != null) {
            processSequence(loopType.getSequence());
        } else if (loopType.getSimpleLoop() != null) {
            processSimpleLoop(loopType.getSimpleLoop());
        }
        return null;
    }

    @Override
    public ProgramSegment processProgramSegment(ProgramType programType) {
        if (programType.getConst() != null) {
            processConst(programType.getConst());
        } else if (programType.getIf() != null) {
            processIf(programType.getIf());
        } else if (programType.getLoop() != null) {
            processLoopType(programType.getLoop());
        }
        return null;
    }

    @Override
    public IfSegment processIf(IfType ifType) {
        if (level == 0) {
            guiLastAddedElement = guiRootElement.addProgramAction(ViewControllMethodsArchive.InputAction.ADD_IF_SEGMENT);
        } else {
            guiLastAddedElement = guiCurrentElement.addProgramAction(ViewControllMethodsArchive.InputAction.ADD_IF_SEGMENT);
        }
        IfElement ifElement = (IfElement) guiLastAddedElement;
        SimpleExprType leftSimpleExpr = ifType.getLeft();
        level++;
        processXmlSimpleExpr(leftSimpleExpr);
        level--;
        ifElement.setTextFieldLeftSimpleExpr(expression);
        expression = "";
        SimpleExprType rightSimpleExpr = ifType.getRight();
        level++;
        processXmlSimpleExpr(rightSimpleExpr);
        level--;
        ifElement.setTextFieldRightSimpleExpr(expression);
        expression = "";
        ifElement.setTextFieldRelop(ifType.getRelop());

        if (ifType.getThenBranch() != null) {
            ifElement.setThenClicked(true);
            ifElement.holdThenClick();
            level++;
            ProgramElement oldProgramElement = guiCurrentElement;
            guiCurrentElement = ifElement;
            processProgramSegment(ifType.getThenBranch());
            level--;
            guiCurrentElement = oldProgramElement;
            ifElement.setThenClicked(false);
            ifElement.unholdThenClick();
        }
        expression = "";
        if (ifType.getElseBranch() != null) {
            ifElement.setElseClicked(true);
            ifElement.holdElseClick();
            level++;
            ProgramElement oldProgramElement = guiCurrentElement;
            guiCurrentElement = ifElement;
            processProgramSegment(ifType.getElseBranch());
            level--;
            guiCurrentElement = oldProgramElement;
            ifElement.setElseClicked(false);
            ifElement.unholdElseClick();
        }
        return null;
    }

    @Override
    public Loop processCondLoop(CondType c, boolean isWhile) {
        if (isWhile) {
            if (level == 0) {
                guiLastAddedElement = guiRootElement.addProgramAction(ViewControllMethodsArchive.InputAction.ADD_WHILE_LOOP);
            } else {
                guiLastAddedElement = guiCurrentElement.addProgramAction(ViewControllMethodsArchive.InputAction.ADD_WHILE_LOOP);
            }
            WhileLoopElement whileElement = (WhileLoopElement) guiLastAddedElement;
            if (c.getDirection().equals("to")) {
                whileElement.setDirection(CondLoopElement.Direction.TO);
            } else if (c.getDirection().equals("downto")) {
                whileElement.setDirection(CondLoopElement.Direction.DOWNTO);
            }
            if (c.getPorts() != null) {
                if (c.getPorts().getInPorts() != null) {
                    InPortType inPortType = c.getPorts().getInPorts();
                    if (inPortType != null) {
                        InPortType.Lower lower = inPortType.getLower();
                        InPortType.Upper upper = inPortType.getUpper();
                        if (lower != null) {
                            whileElement.setInLower(lower.getName());
                        }
                        if (upper != null) {
                            whileElement.setInUpper(upper.getName());
                        }
                    }
                }
            }
        } else {
            if (level == 0) {
                guiLastAddedElement = guiRootElement.addProgramAction(ViewControllMethodsArchive.InputAction.ADD_REPEAT_LOOP);
            } else {
                guiLastAddedElement = guiCurrentElement.addProgramAction(ViewControllMethodsArchive.InputAction.ADD_REPEAT_LOOP);
            }
            RepeatLoopElement repeatElement = (RepeatLoopElement) guiLastAddedElement;
            if (c.getDirection().equals("to")) {
                repeatElement.setDirection(CondLoopElement.Direction.TO);
            } else if (c.getDirection().equals("downto")) {
                repeatElement.setDirection(CondLoopElement.Direction.DOWNTO);
            }
            if (c.getPorts() != null) {
                if (c.getPorts().getInPorts() != null) {
                    InPortType inPortType = c.getPorts().getInPorts();
                    if (inPortType != null) {
                        InPortType.Lower lower = inPortType.getLower();
                        InPortType.Upper upper = inPortType.getUpper();
                        if (lower != null) {
                            repeatElement.setInLower(lower.getName());
                        }
                        if (upper != null) {
                            repeatElement.setInUpper(upper.getName());
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public ConstSegment processConst(ConstType xmlConst) {
        if (level == 0) {
            guiLastAddedElement = guiRootElement.addProgramAction(ViewControllMethodsArchive.InputAction.ADD_CONST_EXPRESSION);
        } else {
            guiLastAddedElement = guiCurrentElement.addProgramAction(ViewControllMethodsArchive.InputAction.ADD_CONST_EXPRESSION);
        }
        ConstExpression constElement = (ConstExpression) guiLastAddedElement;
        var = xmlConst.getExpr().getVarName();
        expression = "";
        SimpleExprType simplExprType = xmlConst.getExpr().getSimpleExpr();
        level++;
        processXmlSimpleExpr(simplExprType);
        level--;
        constElement.setVar(var);
        constElement.setExpression(expression);
        return null;
    }

    @Override
    public Loop processForLoop(ForType f) {
        if (level == 0) {
            guiLastAddedElement = guiRootElement.addProgramAction(ViewControllMethodsArchive.InputAction.ADD_FOR_LOOP);
        } else {
            guiLastAddedElement = guiCurrentElement.addProgramAction(ViewControllMethodsArchive.InputAction.ADD_FOR_LOOP);
        }
        ForLoopElement forElement = (ForLoopElement) guiLastAddedElement;
        OutPortType outPortType = f.getOutPorts();
        InPortType inPortType = f.getInPorts();
        if (inPortType != null) {
            InPortType.Lower lower = inPortType.getLower();
            InPortType.Upper upper = inPortType.getUpper();
            if (lower != null) {
                forElement.setInLower(lower.getName());
            }
            if (upper != null) {
                forElement.setInUpper(upper.getName());
            }
        }
        if (outPortType != null) {
            if (outPortType.getIter() != null) {
                forElement.setIterator(outPortType.getIter().getName());
            }
        }
        return null;
    }

    @Override
    public ProgramSegment processNested(Nested nested) {
        LoopType outer = nested.getOuter();
        processLoopType(outer);
        ProgramType inner = nested.getInner();
        ProgramElement oldCurrentElement = guiCurrentElement;
        guiCurrentElement = guiLastAddedElement;
        level++;
        processProgramSegment(inner);
        level--;
        guiCurrentElement = oldCurrentElement;
        return null;
    }

    @Override
    public ProgramSequence processSequence(Sequence sequence) {
        for (ProgramType program : sequence.getProgram()) {
            processProgramSegment(program);
        }
        return null;
    }

    @Override
    public Loop processSimpleLoop(SimpleLoop simpleLoop) {
        if (simpleLoop.getFor() != null) {
            processForLoop(simpleLoop.getFor());
        } else if (simpleLoop.getWhile() != null) {
            processCondLoop(simpleLoop.getWhile(), true);
        } else if (simpleLoop.getRepeat() != null) {
            processCondLoop(simpleLoop.getRepeat(), false);
        }
        return null;
    }

    @Override
    public Factor processXmlFactor(FactorType xmlFactor) {
        if (xmlFactor.getConstant() != null) {
            expression += xmlFactor.getConstant();
        } else if (xmlFactor.getNotFactor() != null) {
            expression += "not ";
            level++;
            processXmlFactor(xmlFactor.getNotFactor());
            level--;
        } else if (xmlFactor.getSubExpr() != null) {
            expression += "(";
            level++;
            processXmlSimpleExpr(xmlFactor.getSubExpr().getSimpleExpr());
            level--;
            expression += ")";
        } else if (xmlFactor.getVar() != null) {
            level++;
            processXmlVar(xmlFactor.getVar());
            level--;
        }
        return null;
    }

    @Override
    public SignedFactor processXmlSignedFactor(SignedFactorType xmlSignedFactor) {
        if (xmlSignedFactor.getSign().equals("-")) {
            expression += "-";
        }

        level++;
        processXmlFactor(xmlSignedFactor.getFactor());
        level--;

        return null;
    }

    @Override
    public Variable processXmlIndexed(FactorType.Var.Indexed xmlIndexed) {
        expression += xmlIndexed.getName() + "[";
        level++;
        processXmlSimpleExpr(xmlIndexed.getSimpleExpr());
        if (xmlIndexed.getCommaExpr() != null) {
            for (FactorType.Var.Indexed.CommaExpr commaExpr : xmlIndexed.getCommaExpr()) {
                expression += ",";
                level++;
                processXmlSimpleExpr(commaExpr.getSimpleExpr());
                level--;
            }
        }
        level--;
        expression += "]";
        return null;
    }

    @Override
    public SimpleExpression processXmlSimpleExpr(SimpleExprType xmlSimpleExpr) {
        level++;
        processXmlTerm(xmlSimpleExpr.getTerm());
        level--;
        if (xmlSimpleExpr.getAddopTerm() != null) {
            for (SimpleExprType.AddopTerm addopTerm : xmlSimpleExpr.getAddopTerm()) {

                if (addopTerm.getAddop().equals("or")) {
                    expression += " " + addopTerm.getAddop() + " ";
                } else {
                    expression += addopTerm.getAddop();
                }
                level++;
                processXmlTerm(addopTerm.getTerm());
                level--;
            }
        }
        return null;
    }

    @Override
    public Expression processXmlSubExpr(FactorType.SubExpr xmlSubExpr) {
        return null;
    }

    @Override
    public Term processXmlTerm(TermType xmlTerm) {
        level++;
        processXmlSignedFactor(xmlTerm.getSignedFactor());
        level--;
        if (xmlTerm.getMulopSignedFactor() != null) {
            for (TermType.MulopSignedFactor mulopSignedFactor : xmlTerm.getMulopSignedFactor()) {
                String mulop = mulopSignedFactor.getMulop();
                if (mulop.equals("div") || mulop.equals("mod") || mulop.equals("and")) {
                    expression += " " + mulopSignedFactor.getMulop() + " ";
                } else {
                    expression += mulopSignedFactor.getMulop();
                }
                level++;
                processXmlSignedFactor(mulopSignedFactor.getSignedFactor());
                level--;
            }
        }
        return null;
    }

    @Override
    public Variable processXmlVar(FactorType.Var xmlVar) {
        if (xmlVar.getName() != null) {
            expression += xmlVar.getName();
        } else if (xmlVar.getIndexed() != null) {
            level++;
            processXmlIndexed(xmlVar.getIndexed());
            level--;
        }
        return null;
    }

    @Override
    public void startProcessing() {
        guiRootElement.clear();
        guiRootElement.addComplexityPane();
        level++;
        processProgramSegment(rootSegment.getProgram());
        guiRootElement.getTextFieldComplexityFunc().setText(rootSegment.getComplexity().getFunc());
        level--;
    }

}
