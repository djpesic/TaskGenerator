/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assemblymanager.topdownguided.graph;

/**
 *
 * @author djordje
 */
public class FuncNode extends Node {

    private boolean isLeaf = false;// ako je true, na ovaj node se moze mapirati segment
    protected String complexity;

    public FuncNode(boolean isLeaf, String complexity) {
        this.isLeaf = isLeaf;
        this.complexity = complexity;
    }

    public FuncNode(String complexity) {
        this.complexity = complexity;
    }

    public String getComplexity() {
        return complexity;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("" + complexity);
        if (funcGen) {
            builder.append("FG");
        }
        builder.append("(").append(id).append(")");

        return builder.toString();
    }

    @Override
    public boolean isFunc() {
        return true;
    }

    @Override
    public void setFuncGen() {
        super.funcGen = false;
        switch (complexity) {
            case "log(n)":
                super.funcGen = true;
                break;
            case "Sqrt(n)":
                super.funcGen = true;
                break;
            case "n":
                super.funcGen = true;
                break;

        }

    }
}
