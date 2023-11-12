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
public class OpNode extends Node {

    private String operation;

    public OpNode(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(operation);
        builder.append("(").append(id).append(")");
        return builder.toString();
    }

    @Override
    public void setFuncGen() {
        funcGen = false;
    }

}
