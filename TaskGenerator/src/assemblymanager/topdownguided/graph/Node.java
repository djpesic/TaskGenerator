/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assemblymanager.topdownguided.graph;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author djordje
 */
public abstract class Node {

    protected boolean funcGen = false;
    protected boolean root = false;
    protected int id = lastId++;
    private static int lastId = 0;

    public int getId() {
        return id;
    }

    public boolean isFuncGen() {
        return funcGen;
    }

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }

    public abstract void setFuncGen();
    
    public boolean isFunc(){
        return false;
    }

    @Override
    public abstract String toString();

}
