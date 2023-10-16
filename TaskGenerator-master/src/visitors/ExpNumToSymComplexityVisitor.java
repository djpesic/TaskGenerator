/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitors;

import complexity.ComplexityNode;
import complexity.ExpNode;
import complexity.NumNode;
import complexity.VarNode;

/**
 *
 * @author djordje
 * converts exponent base to symbolic constant.
 */
public class ExpNumToSymComplexityVisitor extends ComplexityVisitor {

    @Override
    public void visit(ExpNode node) {
        ComplexityNode base = node.getBase();
        if(base instanceof NumNode){
            VarNode vNode = new VarNode("o");
            node.setBase(vNode);
        }
    }
}
