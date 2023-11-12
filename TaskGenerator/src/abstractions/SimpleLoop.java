/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstractions;

import syntaxelements.nonterminals.StatementSequence;
import java.util.*;

/**
 *
 * @author djordje
 */
public abstract class SimpleLoop extends Loop {

    protected StatementSequence syntaxTree;
    protected String loopVarNum;
    protected String[] loopVars;
    protected String initStr1;
    protected String initStr2;

    public abstract String getIteratorVariable();

    public abstract void setIteratorVariable(String iterVarName);

    public abstract String getLoopType();

    public void removeSecondInitStat() {
        syntaxTree.getSemiColonStatementList().remove(0);
    }

    @Override
    public StatementSequence getSyntaxTree() {
        return syntaxTree;
    }

    public void setSyntaxTree(StatementSequence syntaxTree) {
        this.syntaxTree = syntaxTree;
    }

    public String getLoopVarNum() {
        return loopVarNum;
    }

    public void setLoopVarNum(String loopVarNum) {
        this.loopVarNum = loopVarNum;
    }

    public String[] getLoopVars() {
        return loopVars;
    }

    public void setLoopVars(String[] loopVars) {
        this.loopVars = loopVars;
    }

    public String getInitStr1() {
        return initStr1;
    }

    public void setInitStr1(String initStr1) {
        this.initStr1 = initStr1;
    }

    public String getInitStr2() {
        return initStr2;
    }

    public void setInitStr2(String initStr2) {
        this.initStr2 = initStr2;
    }


}
