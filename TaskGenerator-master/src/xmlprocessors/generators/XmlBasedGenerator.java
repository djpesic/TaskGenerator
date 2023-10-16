/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlprocessors.generators;

import abstractions.ConditionalLoop;
import abstractions.ConstSegment;
import abstractions.ForLoop;
import abstractions.IfSegment;
import abstractions.Loop;
import abstractions.LoopNested;
import abstractions.ProgramSegment;
import abstractions.ProgramSequence;
import abstractions.RepeatLoop;
import abstractions.WhileLoop;
import complexity.Complexity;
import complexity.ComplexityOperations;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.nio.file.Files;
import utils.Randomizator;
import java.util.*;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import syntaxelements.nonterminals.*;
import syntaxelements.terminals.*;
import utils.XmlMarshaller;
import visitors.CodeIdentationVisitor;
import programSegment.*;
import programSegment.FactorType.*;
import programSegment.FactorType.Var.Indexed;
import programSegment.InPortType.*;
import utils.InvertCondition;
import xmlprocessors.PortNamesCollector;
import xmlprocessors.XmlProcessor;

/**
 *
 * @author djordje
 */
public class XmlBasedGenerator extends Generator implements XmlProcessor {

    private String fileName;
    private Segment xmlSegment;
    public static final String OPERAND_STUB = "#";
    public static final String NUMBER_STUB = "@";
    public static final int NUMBER_STUB_BOTTOM = 1;
    public static final int NUMBER_STUB_BOTTOM_Z = 0;
    public static final int NUMBER_STUB_TOP = 100;
    public static final String PROGRAM_SEGMENT_SCHEMA = "xml-resources/jaxb/programSegment/programSegment.xsd";
    public static final String RULE_SCHEMA = "xml-resources/jaxb/rules/rules.xsd";
    public static final String EMPTY_FOR = "src/xml/emptyFor.xml";
    public static final String EMPTY_WHILE = "src/xml/managerDB/emptyWhile.xml";
    public static final String EMPTY_REPEAT = "src/xml/managerDB/emptyRepeat.xml";
    public static final String MGR_DB = "src/xml/managerDB";
    public static final String SPECLIN_INITS = "src/xml/specialLinear/inits";
    public static final String SPECLIN_BODY = "src/xml/specialLinear/body";
    public static final String SQRT_INITS = "src/xml/sqrt/inits";
    public static final String SQRT_BODY = "src/xml/sqrt/body";
    public static final String A_IS_B = "src/xml/aisb.xml";
    public static final String A_IS_B_DIV_C = "src/xml/aisbdivc.xml";
    public static final String A_IS_B_MUL_C = "src/xml/aisbmulc.xml";
    public static final String A_IS_B_ADD_C = "src/xml/aisbaddc.xml";
    public static final String A_IS_B_SUB_C = "src/xml/aisbsubc.xml";
    public static final String A_IS_B_ADD_C_MUL_D = "src/xml/templates/aisbaddcmuld.xml";
    public static final String A_IS_B_MUL_C_MUL_D = "src/xml/templates/aisbmulcmuld.xml";
    private ByteArrayOutputStream xmlOutputStream;

    public XmlBasedGenerator(String fileName) {
        this.fileName = fileName;
    }

    public XmlBasedGenerator() {
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public XmlBasedGenerator(ByteArrayOutputStream xmlOutputStream) {
        this.xmlOutputStream = xmlOutputStream;
    }

    public XmlBasedGenerator(Segment xmlSegment) {
        this.xmlSegment = xmlSegment;
    }

    public void setXmlSegment(Segment xmlSegment) {
        this.xmlSegment = xmlSegment;
    }

    public Segment unmarshall() {
        if (fileName == null) {
            return XmlMarshaller.unmarshallSegment(PROGRAM_SEGMENT_SCHEMA, xmlOutputStream);
        }
        return XmlMarshaller.unmarshallSegment(PROGRAM_SEGMENT_SCHEMA, fileName);
    }

    @Override
    public Variable processXmlIndexed(Indexed xmlIndexed) {

        String xmlName = xmlIndexed.getName();
        if (xmlName.equals(OPERAND_STUB)) {
            xmlName = Randomizator.getVarName();
        }
        IndexedVar indexedVar = new IndexedVar(new Ident(xmlName));
        List<Expression> indexExprs = new LinkedList<>();
        indexExprs.add(new Expression(processXmlSimpleExpr(xmlIndexed.getSimpleExpr())));
        for (Indexed.CommaExpr commaExpr : xmlIndexed.getCommaExpr()) {
            indexExprs.add(new Expression(processXmlSimpleExpr(commaExpr.getSimpleExpr())));
        }
        indexedVar.addIndexExpression(indexExprs);
        Variable var = new Variable(indexedVar);
        return var;
    }

    @Override
    public Expression processXmlSubExpr(SubExpr xmlSubExpr) {
        return new Expression(processXmlSimpleExpr(xmlSubExpr.getSimpleExpr()));
    }

    @Override
    public Variable processXmlVar(Var xmlVar) {
        String xmlName = xmlVar.getName();
        Indexed indexed = xmlVar.getIndexed();
        Variable variable = null;
        if (xmlName != null) {
            if (xmlName.equals(OPERAND_STUB)) {
                variable = new Variable(new EntireVar(new Ident(Randomizator.getVarName())));
            } else {
                variable = new Variable(new EntireVar(new Ident(xmlName)));
            }
        } else if (indexed != null) {
            variable = processXmlIndexed(indexed);
        }
        return variable;
    }

    @Override
    public SignedFactor processXmlSignedFactor(SignedFactorType xmlSignedFactor) {
        SignedFactor signedFactor = new SignedFactor();
        signedFactor.setSign(new Sign(xmlSignedFactor.getSign()));
        signedFactor.setFactor(processXmlFactor(xmlSignedFactor.getFactor()));
        return signedFactor;
    }

    @Override
    public Factor processXmlFactor(FactorType xmlFactor) {
        String xmlConst = xmlFactor.getConstant();
        Var xmlVar = xmlFactor.getVar();
        SubExpr xmlSubExpr = xmlFactor.getSubExpr();
        FactorType notFactor = xmlFactor.getNotFactor();

        Factor factor = null;

        if (xmlConst != null) {
            if (xmlConst.equals(NUMBER_STUB)) {
                xmlConst = "" + Randomizator.getRandomInt(NUMBER_STUB_BOTTOM, NUMBER_STUB_TOP);
            }
            factor = new Factor(new Variable(new EntireVar(new Ident(xmlConst))));
        } else if (xmlVar != null) {
            factor = new Factor(processXmlVar(xmlVar));
        } else if (xmlSubExpr != null) {
            factor = new Factor(processXmlSubExpr(xmlSubExpr));
        } else if (notFactor != null) {
            factor = new Factor(processXmlFactor(notFactor));
        }

        return factor;
    }

    @Override
    public Term processXmlTerm(TermType xmlTerm) {
        SignedFactorType xmlSignedFactor = xmlTerm.getSignedFactor();
        List<TermType.MulopSignedFactor> xmlMulopSignedFactor = xmlTerm.getMulopSignedFactor();

        Term term = new Term(processXmlSignedFactor(xmlSignedFactor));
        for (TermType.MulopSignedFactor mulopFactor : xmlMulopSignedFactor) {
            String xmlMulop = mulopFactor.getMulop();

            Mulop mulop = null;
            switch (xmlMulop) {
                case "*":
                    mulop = new Mulop(Constants.MUL);
                    break;
                case "/":
                    mulop = new Mulop(Constants.FLOAT_DIV);
                    break;
                case "div":
                    mulop = new Mulop(Constants.INT_DIV);
                    break;
                case "mod":
                    mulop = new Mulop(Constants.MOD);
                    break;
                case "and":
                    mulop = new Mulop(Constants.AND);
                    break;
            }
            term.addSignedFactor(mulop, processXmlSignedFactor(mulopFactor.getSignedFactor()));
        }
        return term;
    }

    @Override
    public SimpleExpression processXmlSimpleExpr(SimpleExprType xmlSimpleExpr) {
        TermType xmlTerm = xmlSimpleExpr.getTerm();
        List<SimpleExprType.AddopTerm> xmlAddopTerm = xmlSimpleExpr.getAddopTerm();

        SimpleExpression simpleExpr = new SimpleExpression(processXmlTerm(xmlTerm));
        for (SimpleExprType.AddopTerm xmlAddopTerm1 : xmlAddopTerm) {
            String xmlAddop = xmlAddopTerm1.getAddop();
            Addop addop = null;

            switch (xmlAddop) {
                case "+":
                    addop = new Addop(Constants.PLUS);
                    break;
                case "-":
                    addop = new Addop(Constants.MINUS);
                    break;
                case "or":
                    addop = new Addop(Constants.OR);
                    break;
            }
            simpleExpr.addTerm(addop, processXmlTerm(xmlAddopTerm1.getTerm()));
        }
        return simpleExpr;
    }

    @Override
    public ConstSegment processConst(ConstType xmlConst) {

        StatementSequence statSeq = null;
        VarType xmlVarType = xmlConst.getExpr();
        Expression expr = new Expression(processXmlSimpleExpr(xmlVarType.getSimpleExpr()));
        Variable var = new Variable(new EntireVar(new Ident(xmlVarType.getVarName())));
        Statement stat = new Statement(new SimpleStatement(new AssignmentStatement(var, expr)));
        if (statSeq == null) {
            statSeq = new StatementSequence(stat);
        } else {
            statSeq.addToOptionalList(stat);
        }
        ConstSegment seg = new ConstSegment();
        seg.setSyntaxTree(statSeq);
        return seg;

    }

    @Override
    public Loop processSimpleLoop(SimpleLoop simpleLoop) {
        SimpleLoop sl = simpleLoop;
        if (sl == null) {
            return null;
        }
        CondType wh = sl.getWhile(), rep = sl.getRepeat();
        ForType f = sl.getFor();
        Loop loop = null;
        if (f != null) {
            loop = processForLoop(f);
        } else if (rep != null) {
            loop = processCondLoop(rep, false);
        } else if (wh != null) {
            loop = processCondLoop(wh, true);
        }
        return loop;
    }

    @Override
    public Loop processCondLoop(CondType c, boolean isWhile) {
        //extract while loop from xml;
        String lower = null, upper = null;
        int loopVarNumFixed = 0;
        StatementSequence body = new StatementSequence(new Statement(new StructuredStatement(new CompoundStatement(new Empty()))));

        //extract repeat loop from xml;
        //extract complexity
        InPortType inP = null;
        OutPortType outP;
        String[] loopVars = new String[2];
        String loopVarNum = null;

        //extract loop ports
        List<String> allInPorts = new LinkedList<>();
        CondType.Ports xmlPorts = c.getPorts();
        if (xmlPorts != null) {
            inP = xmlPorts.getInPorts();
            outP = xmlPorts.getOutPorts();

            if (inP != null) {
                allInPorts = inP.getPort().stream().filter((port) -> port.isInherited()).
                        map((port) -> port.getName()).collect(Collectors.toList());
            }
            if (inP != null) {//extract input ports
                Lower l = inP.getLower();
                Upper u = inP.getUpper();
                if (l != null) {
                    lower = l.getName();
                    loopVarNumFixed++;
                } else {
                    lower = Randomizator.getVarName();
                }
                if (u != null) {
                    upper = u.getName();
                    loopVarNumFixed++;
                } else {
                    upper = Randomizator.getVarName();
                }
            }
        } else {
            loopVarNum = Randomizator.getLoopVarNum();
        }

        String direction = c.getDirection();
        String condition;
        if (direction.equals("to")) {
            condition = Randomizator.getLtCondition();
        } else {
            condition = Randomizator.getGtCondition();
        }

        //create loop abstraction
        if (xmlPorts != null) {
            if (loopVarNumFixed == 2) {
                loopVarNum = "2";
            } else {
                loopVarNum = Randomizator.getLoopVarNum();
                if (loopVarNumFixed == 1) {
                    if (inP.getLower() == null && inP.getUpper() != null) {
                        lower = Randomizator.getVarName();
                        loopVarNum = "2";
                    }
                    if (inP.getUpper() == null && inP.getLower() != null) {
                        if (loopVarNum.equals("2")) {
                            upper = Randomizator.getVarName();
                        } else {
                            if (direction.equals("to")) {
                                upper = "n";
                            } else {
                                upper = "1";
                            }
                        }
                    }
                } else {
                    lower = Randomizator.getVarName();
                    if (loopVarNum.equals("2")) {
                        upper = Randomizator.getVarName();
                    } else {
                        if (direction.equals("to")) {
                            upper = "n";
                        } else {
                            upper = "1";
                        }
                    }
                }
            }
        }
        loopVars[0] = lower;
        loopVars[1] = upper;
        String initStr1 = "1", initStr2 = "n";
        if (direction.equals("downto")) {
            initStr1 = "n";
            initStr2 = "1";
        }
        ConditionalLoop loopAbstraction;
        if (isWhile) {
            WhileLoop whileAbstraction = new WhileLoop();
            whileAbstraction.setCondition(condition);
            whileAbstraction.setInitStr1(initStr1);
            whileAbstraction.setInitStr2(initStr2);
            whileAbstraction.setLoopVars(loopVars);
            whileAbstraction.setIteratorVariable(loopVars[0]);
            whileAbstraction.setLoopVarNum(loopVarNum);
            loopAbstraction = whileAbstraction;
        } else {
            RepeatLoop repeatAbstraction = new RepeatLoop();
            repeatAbstraction.setCondition(condition);
            repeatAbstraction.setInitStr1(initStr1);
            repeatAbstraction.setInitStr2(initStr2);
            repeatAbstraction.setLoopVars(loopVars);
            repeatAbstraction.setIteratorVariable(loopVars[0]);
            repeatAbstraction.setLoopVarNum(loopVarNum);
            loopAbstraction = repeatAbstraction;
        }

        //code generation
        {
            Ident ident1 = new Ident(loopAbstraction.getLoopVars()[0]);
            Ident ident2 = new Ident(loopAbstraction.getLoopVars()[1]);
            EntireVar entVar1 = new EntireVar(ident1);
            EntireVar entVar2 = new EntireVar(ident2);
            Variable var1 = new Variable(entVar1);
            Variable var2 = new Variable(entVar2);

            Relop relop = null;

            //loop body, first var in the loop condition is altered by default
            Variable toChange = var1, other = var2;

            String condType = ((ConditionalLoop) loopAbstraction).getCondition();

            if (loopAbstraction.getLoopType().equals("repeat")) {
                condType = InvertCondition.invertCondition(condType);
            }
            switch (condType) {
                case ">":
                    relop = new Relop(Constants.GREATHER_THAN);
                    break;
                case ">=":
                    relop = new Relop(Constants.GREATHER_THAN_EQ);
                    break;
                case "<":
                    relop = new Relop(Constants.LESS_THAN);
                    break;
                case "<=":
                    relop = new Relop(Constants.LESS_THAN_EQ);
                    break;

            }

            //init values for the altered loop condition var
            Factor factor1 = new Factor(var1);
            SignedFactor signedFactor1 = new SignedFactor(new Sign(""), factor1);
            Term term1 = new Term(signedFactor1);
            SimpleExpression simpleExpr1 = new SimpleExpression(term1);
            Factor factor2 = new Factor(var2);
            SignedFactor signedFactor2 = new SignedFactor(new Sign(""), factor2);
            Term term2 = new Term(signedFactor2);
            SimpleExpression simpleExpr2 = new SimpleExpression(term2);
            Expression expr = new Expression(simpleExpr1, relop, simpleExpr2);

            RepetitiveStatement repetitiveStat = null;
            if (loopAbstraction.getLoopType().equals("while")) {
                While wh = new While(expr, new Statement(new StructuredStatement(new CompoundStatement(body))));
                repetitiveStat = new RepetitiveStatement(wh);
            } else if (loopAbstraction.getLoopType().equals("repeat")) {
                Repeat rep = new Repeat(body, expr);
                repetitiveStat = new RepetitiveStatement(rep);
            }

            //initialization of the altered loop condition var
            AssignmentStatement assignInit = new AssignmentStatement(toChange, new Expression(new SimpleExpression(
                    new Term(new SignedFactor(new Sign(""),
                            new Factor(new Variable(new EntireVar(new Ident(loopAbstraction.getInitStr1())))))))));

            SimpleStatement simpleStatInit = new SimpleStatement(assignInit);
            Statement initStat1 = new Statement(simpleStatInit);
            StatementSequence result;
            Statement initStat2 = null;
            boolean initStat2Exists = false;

            if (loopAbstraction.getLoopVarNum().equals("2")) {

                AssignmentStatement assignInitSecond = new AssignmentStatement(other, new Expression(new SimpleExpression(
                        new Term(new SignedFactor(new Sign(""),
                                new Factor(new Variable(new EntireVar(new Ident(loopAbstraction.getInitStr2())))))))));

                SimpleStatement simpleStatInitSecond = new SimpleStatement(assignInitSecond);
                initStat2 = new Statement(simpleStatInitSecond);
                initStat2Exists = true;
            }

            boolean var1InPort = allInPorts.contains(var1.toString());
            boolean var2InPort = allInPorts.contains(var2.toString());
            if (var1InPort && var2InPort) {
                result = new StatementSequence(new Statement(new StructuredStatement(repetitiveStat)));
            } else if (var1InPort && !var2InPort) {
                if (initStat2Exists) {
                    result = new StatementSequence(initStat2);
                    result.addToOptionalList(new Statement(new StructuredStatement(repetitiveStat)));
                } else {
                    result = new StatementSequence(new Statement(new StructuredStatement(repetitiveStat)));
                }
            } else if (!var1InPort && var2InPort) {
                result = new StatementSequence(initStat1);
                result.addToOptionalList(new Statement(new StructuredStatement(repetitiveStat)));
            } else {
                result = new StatementSequence(initStat1);
                if (initStat2Exists) {
                    result.addToOptionalList(initStat2);
                }
                result.addToOptionalList(new Statement(new StructuredStatement(repetitiveStat)));
            }

            loopAbstraction.setSyntaxTree(result);
        }
        return loopAbstraction;
    }

    @Override
    public Loop processForLoop(ForType f) {
        Loop loop;
        //extract for loop from xml
        InPortType inP = f.getInPorts();
        OutPortType outP = f.getOutPorts();
        List<String> allInPorts = new LinkedList<>();
        if (inP != null) {
            allInPorts = inP.getPort().stream().filter((port) -> port.isInherited()).
                    map((port) -> port.getName()).collect(Collectors.toList());
        }

        String lower = null, upper = null, iter = null;
        int loopVarNumFixed = 0;
        if (inP != null) {//extract input ports
            Lower l = inP.getLower();
            Upper u = inP.getUpper();
            if (l != null) {
                lower = l.getName();
                loopVarNumFixed++;
            } else {
                lower = Randomizator.getVarName();
            }
            if (u != null) {
                upper = u.getName();
                loopVarNumFixed++;
            } else {
                upper = Randomizator.getVarName();
            }
        }
        StatementSequence body = null;
        if (outP != null) {//extract output ports
            if (outP.getIter() != null) {
                iter = outP.getIter().getName();
            } else {
                iter = Randomizator.getIterName();
            }
        } else {
            iter = Randomizator.getIterName();
        }
        String direction = Randomizator.getLoopDirection();
        String initStr1 = "1", initStr2 = "n";
        // loop bounds and loopVarNum
        String loopVarNum;
        String[] loopVars = new String[2];
        if (loopVarNumFixed == 2) {
            loopVarNum = "2";
        } else {
            loopVarNum = Randomizator.getLoopVarNum();
            if (loopVarNumFixed == 1) {
                if (inP.getLower() == null && inP.getUpper() != null) {
                    lower = Randomizator.getVarName();
                    loopVarNum = "2";
                }
                if (inP.getUpper() == null && inP.getLower() != null) {
                    if (loopVarNum.equals("2")) {
                        upper = Randomizator.getVarName();
                    } else {
                        upper = "n";
                    }
                }
            } else {
                lower = Randomizator.getVarName();
                if (loopVarNum.equals("2")) {
                    upper = Randomizator.getVarName();
                } else {
                    upper = "n";
                }
            }
        }
        loopVars[0] = lower;
        loopVars[1] = upper;
        ForLoop forLoopAbstraction = new ForLoop();
        forLoopAbstraction.setDirection(direction);
        forLoopAbstraction.setInitStr1(initStr1);
        forLoopAbstraction.setInitStr2(initStr2);
        forLoopAbstraction.setIteratorVariable(iter);
        forLoopAbstraction.setLoopVarNum(loopVarNum);
        forLoopAbstraction.setLoopVars(loopVars);
        //code generation
        RepetitiveStatement repetitiveStat;

        Variable var1 = new Variable(new EntireVar(new Ident(forLoopAbstraction.getLoopVars()[0])));
        Variable var2 = new Variable(new EntireVar(new Ident(forLoopAbstraction.getLoopVars()[1])));
        boolean toDownto = false;
        Variable first = var1, second = var2;
        if (((ForLoop) forLoopAbstraction).getDirection().equals("downto")) {
            first = var2;
            second = var1;
        } else if (((ForLoop) forLoopAbstraction).getDirection().equals("to")) {
            toDownto = true;
        }
        Variable iterator = new Variable(new EntireVar(new Ident(iter)));
        Expression initExpr = new Expression(new SimpleExpression(new Term(new SignedFactor(new Sign(""), new Factor(first)))));
        Expression finalExpr = new Expression(new SimpleExpression(new Term(new SignedFactor(new Sign(""), new Factor(second)))));
        Statement stat;
        if (body == null) {
            stat = new Statement(new StructuredStatement(new CompoundStatement(new Empty())));
        } else {
            stat = new Statement(new StructuredStatement(new CompoundStatement(body)));
        }
        For forLoop = new For(iterator, initExpr, toDownto, finalExpr, stat);
        repetitiveStat = new RepetitiveStatement(forLoop);
        Expression expr1 = new Expression(new SimpleExpression(new Term(
                new SignedFactor(new Sign(""), new Factor(new Variable(new EntireVar(new Ident(initStr1))))))));
        AssignmentStatement initAssign1 = new AssignmentStatement(var1, expr1);
        Statement initStat1 = new Statement(new SimpleStatement(initAssign1));
        StatementSequence result;
        boolean initStat2Exists = false;
        Statement initStat2 = null;
        if (forLoopAbstraction.getLoopVarNum().equals("2")) {
            Expression expr2 = new Expression(new SimpleExpression(new Term(
                    new SignedFactor(new Sign(""), new Factor(new Variable(new EntireVar(new Ident(initStr2))))))));
            AssignmentStatement initAssign2 = new AssignmentStatement(var2, expr2);
            initStat2 = new Statement(new SimpleStatement(initAssign2));
            initStat2Exists = true;
        }

        boolean var1InPort = allInPorts.contains(var1.toString());
        boolean var2InPort = allInPorts.contains(var2.toString());
        // no init stats if bounds are numbers and some of the refVars
        if ("n".equals(var1.toString())) {
            var1InPort = true;
        } else {
            try {
                Double.parseDouble(var1.toString());
                var1InPort = true;
            } catch (NumberFormatException ex) {

            }
        }
        if ("n".equals(var2.toString())) {
            var2InPort = true;
        } else {
            try {
                Double.parseDouble(var2.toString());
                var2InPort = true;
            } catch (NumberFormatException ex) {

            }
        }
        if (var1InPort && var2InPort) {
            result = new StatementSequence(new Statement(new StructuredStatement(repetitiveStat)));
        } else if (var1InPort && !var2InPort) {
            if (initStat2Exists) {
                result = new StatementSequence(initStat2);
                result.addToOptionalList(new Statement(new StructuredStatement(repetitiveStat)));
            } else {
                result = new StatementSequence(new Statement(new StructuredStatement(repetitiveStat)));
            }
        } else if (!var1InPort && var2InPort) {
            result = new StatementSequence(initStat1);
            result.addToOptionalList(new Statement(new StructuredStatement(repetitiveStat)));
        } else {
            result = new StatementSequence(initStat1);
            if (initStat2Exists) {
                result.addToOptionalList(initStat2);
            }
            result.addToOptionalList(new Statement(new StructuredStatement(repetitiveStat)));
        }
        forLoopAbstraction.setSyntaxTree(result);
        loop = forLoopAbstraction;
        return loop;
    }

    @Override
    public ProgramSequence processSequence(Sequence sequence) {

        //process loop sequence. Sequence contains arbitraru complex loops.
        List<ProgramType> xmlSequenceCode = sequence.getProgram();
        ProgramSequence progSeq = new ProgramSequence();
        for (ProgramType xmlCode : xmlSequenceCode) {
            LoopType xmlLoop = xmlCode.getLoop();
            ConstType xmlConst = xmlCode.getConst();
            IfType xmlIf = xmlCode.getIf();
            ProgramSegment l;
            ConstSegment c;
            ProgramSequence ps;
            IfSegment ifSeg;
            if (xmlLoop != null) {
                if (xmlLoop.getNested() != null) {
                    l = processNested(xmlLoop.getNested());
                    progSeq.combineWith(l);
                } else if (xmlLoop.getSequence() != null) {
                    ps = processSequence(xmlLoop.getSequence());
                    progSeq.combineWith(ps);
                } else if (xmlLoop.getSimpleLoop() != null) {
                    l = processSimpleLoop(xmlLoop.getSimpleLoop());
                    progSeq.combineWith(l);
                }
            } else if (xmlConst != null) {
                c = processConst(xmlConst);
                progSeq.combineWith(c);
            } else if (xmlIf != null) {
                ifSeg = processIf(xmlIf);
                progSeq.combineWith(ifSeg);
            }

        }
        return progSeq;
    }

    @Override
    public ProgramSegment processNested(Nested nested) {
//        extract desired complexity.

        ProgramType xmlInner = nested.getInner();
        LoopType xmlOuter = nested.getOuter();
        ProgramSegment inner = null;

        if (xmlInner.getLoop() != null) {
            if (xmlInner.getLoop().getSequence() != null) {
                inner = processSequence(xmlInner.getLoop().getSequence());
            } else if (xmlInner.getLoop().getNested() != null) {
                inner = processNested(xmlInner.getLoop().getNested());
            } else if (xmlInner.getLoop() != null) {
                inner = processSimpleLoop(xmlInner.getLoop().getSimpleLoop());
            }
        }
        if (xmlInner.getConst() != null) {
            inner = processConst(xmlInner.getConst());
        }
        if (xmlInner.getIf() != null) {
            inner = processIf(xmlInner.getIf());
        }

        ProgramSegment outer = null;
        if (xmlOuter.getSequence() != null) {
            outer = processSequence(xmlOuter.getSequence());
        } else if (xmlOuter.getNested() != null) {
            outer = processNested(xmlOuter.getNested());
        } else if (xmlOuter.getSimpleLoop() != null) {
            outer = processSimpleLoop(xmlOuter.getSimpleLoop());
        }

        LoopNested nestedLoop = new LoopNested();
        nestedLoop.combineWith(outer);
        nestedLoop.combineWith(inner);
        return nestedLoop;
    }

    @Override
    public ProgramSegment generate() {
        //Process input xml
        if (xmlSegment == null) {
            xmlSegment = unmarshall();
        }
        ProgramType topLevelProgram = xmlSegment.getProgram();

        //process xml object tree with port name collection
        PortNamesCollector portNamesCollector;
        if (xmlSegment == null) {
            portNamesCollector = new PortNamesCollector(fileName);
        } else {
            portNamesCollector = new PortNamesCollector(xmlSegment);
        }
        portNamesCollector.startProcessing();
        Randomizator.banNames(portNamesCollector.getPortNames());
        ProgramSegment prog = processProgramSegment(topLevelProgram);

        ComplType complType = xmlSegment.getComplexity();
        Complexity compl = ComplexityOperations.createComplexity(complType.getFunc());
        prog.setComplexity(compl);
        Randomizator.clearIndexes();
        // add tabulation
        CodeIdentationVisitor identVisitor = new CodeIdentationVisitor();
        prog.getSyntaxTree().accept(identVisitor);
        return prog;
    }

    @Override
    public ProgramSegment processProgramSegment(ProgramType programType) {
        //process xml object tree with code generation
        ProgramSegment prog = null;
        if (programType.getLoop() != null) {
            if (programType.getLoop().getSimpleLoop() != null) {
                prog = processSimpleLoop(programType.getLoop().getSimpleLoop());
            } else if (programType.getLoop().getNested() != null) {
                prog = processNested(programType.getLoop().getNested());
            } else if (programType.getLoop().getSequence() != null) {
                prog = processSequence(programType.getLoop().getSequence());
            } else {
                prog = null;
            }
        }
        if (programType.getConst() != null) {
            prog = processConst(programType.getConst());
        }
        if (programType.getIf() != null) {
            prog = processIf(programType.getIf());
        }
        return prog;
    }

    @Override
    public IfSegment processIf(IfType ifType) {
        IfSegment ifSeg = new IfSegment();
        StatementSequence thenSyntaxTree = null, elseSyntaxTree = null;
        ProgramSegment thenProgSeg, elseProgSeg;
        if (ifType.getThenBranch() != null) {
            thenProgSeg = processProgramSegment(ifType.getThenBranch());
            thenSyntaxTree = thenProgSeg.getSyntaxTree();
        }
        if (ifType.getElseBranch() != null) {
            elseProgSeg = processProgramSegment(ifType.getElseBranch());
            elseSyntaxTree = elseProgSeg.getSyntaxTree();
        }
        SimpleExpression leftExpr = processXmlSimpleExpr(ifType.getLeft());
        SimpleExpression rightExpr = processXmlSimpleExpr(ifType.getRight());
        Relop relop = new Relop(ifType.getRelop());

        //code generation
        Expression expr = new Expression(leftExpr, relop, rightExpr);
        IfStatement ifStat = new IfStatement(expr, thenSyntaxTree == null
                ? new Statement(new StructuredStatement(new CompoundStatement(new Empty())))
                : new Statement(new StructuredStatement(new CompoundStatement(thenSyntaxTree))));

        if (elseSyntaxTree != null) {
            ifStat.addStatement(new Statement(new StructuredStatement(new CompoundStatement(elseSyntaxTree))));
        }
        ifSeg.setSyntaxTree(new StatementSequence(new Statement(new StructuredStatement(new ConditionalStatement(ifStat)))));
        return ifSeg;
    }

    @Override
    public void startProcessing() {

    }

    @Override
    public LoopType processLoopType(LoopType loopType) {
        return null;
    }

}
