/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assemblymanager.topdownguided.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import org.javatuples.Pair;
import programSegment.Segment;
import rules.Rule;
import utils.Randomizator;

/**
 * Graf koji predstavlja kompoziciju zeljene slozenosti na elementarne funkcije
 * slozenost koje se 1-1 mapiraju na odgovarajuci segment
 *
 * @author djordje
 */
public class Graph {

    // nodes are distinct
    private Map<Integer, Pair<Node, List<Node>>> nodes = new HashMap<>();
    private List<Node> roots = new ArrayList<>();

    //fields for generating part
    List<Segment> linSegs, logSegs, sqrtSegs;

    //returns newly added node, or existing node, if try to add node with exisiting complexity
    public Node addNode(FuncNode node) {
        Pair<Node, List<Node>> p = findNode(node);
        if (p != null) {
            return p.getValue0();
        }
        p = new Pair(node, new ArrayList<>());
        nodes.put(node.getId(), p);
        if (node.isRoot()) {
            roots.add(node);
        }
        return node;
    }

    public Pair<Node, List<Node>> findNode(Node node) {
        Pair<Node, List<Node>> result = nodes.get(node.getId());
        if (result != null) {
            return result;
        }
        for (Pair<Node, List<Node>> p : nodes.values()) {
            try {
                FuncNode fn = (FuncNode) p.getValue0();
                FuncNode fn1 = (FuncNode) node;
                if (fn.getComplexity().equals(fn1.getComplexity())) {
                    return p;
                }
            } catch (Exception ex) {

            }
        }
        return null;
    }

    public void addNode(OpNode node) {
        Pair<Node, List<Node>> p = new Pair(node, new ArrayList<>());
        nodes.put(node.getId(), p);
    }

    //adds vertice n1 -> n2
    public void addVertice(Node n1, Node n2) {
        Pair<Node, List<Node>> p = findNode(n1);
        if (p != null) {
            p.getValue1().add(n2);
        }
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        for (Pair<Node, List<Node>> pair : nodes.values()) {
            b.append(pair.getValue0()).append("->");
            for (Node n : pair.getValue1()) {
                b.append(n).append(",");
            }
            b.append("\n");
        }
        return b.toString();
    }

    public Graph extractFactorizationTree() {
        Graph factTree = new Graph();
        Stack<Node> stack = new Stack<>();
        for (Node n : roots) {
            stack.push(n);
        }

        while (!stack.isEmpty()) {
            Node n = stack.pop();

            if (n.isFunc()) {
                factTree.addNode((FuncNode) n);
                Pair<Node, List<Node>> pair = factTree.findNode(n);
                if (pair != null) {
                    if (pair.getValue1().isEmpty()) {
                        List<Node> children = nodes.get(n.getId()).getValue1();
                        if (!children.isEmpty()) {
                            int childIndex = Randomizator.getRandomInt(0, children.size() - 1);
                            Node child = children.get(childIndex);
                            factTree.addNode((OpNode) child);
                            factTree.addVertice(n, child);
                            stack.push(child);
                        }
                    }
                }

            } else {
                factTree.addNode((OpNode) n);

                Pair<Node, List<Node>> children = nodes.get(n.getId());
                if (!children.getValue1().isEmpty()) {
                    for (Node child : children.getValue1()) {
                        factTree.addNode((FuncNode) child);
                        factTree.addVertice(n, child);
                        if (((OpNode) n).getOperation().equals("*")) {
                            stack.push(child);
                        }

                    }
                }
            }

        }
        return factTree;
    }

    public boolean generateSegments(List<Segment> linSegs, List<Segment> logSegs, List<Segment> sqrtSegs) {

        if (roots.size() > 1) {
            System.out.println("Graph is not a binary tree. It have more than one root");
            return false;
        }
        this.linSegs = linSegs;
        this.logSegs = logSegs;
        this.sqrtSegs = sqrtSegs;
        return unpackFuncNode(roots.get(0), false);
    }

    private boolean unpackFuncNode(Node n, boolean forceLeaf) {
        FuncNode fn = (FuncNode) n;
        List<Node> children = nodes.get(n.getId()).getValue1();
        if (children.size() > 1) {
            System.out.println("Func node has more than one child");
            return false;
        }
        if (fn.isFuncGen()) {
            boolean stop = Randomizator.getRandomBoolean();
            if (stop) {
                System.out.println(n);
                return true;
            } else {
                if (children.isEmpty()) {
                    System.out.println(n);
                    return true;
                }
                if (forceLeaf) {
                    System.out.println(n);
                    return true;
                }
                boolean res = unpackOpNode(children.get(0));
                if (res) {
                    System.out.println(n);
                }
                return res;
            }
        }
        if (children.isEmpty()) {
            System.out.println("Segments cannot be generated");
            return false;
        }
        if (forceLeaf) {
            System.out.println(n);
            return true;
        }
        boolean res = unpackOpNode(children.get(0));
        if (res) {
            System.out.println(n);
        }
        return res;
    }

    private boolean unpackOpNode(Node n) {
        List<Node> children = nodes.get(n.getId()).getValue1();
        if (children.size() != 2) {
            System.out.println("bad op node " + n);
            return false;
        }
        OpNode opn = (OpNode) n;
        boolean forceLeaf = true;
        if (opn.getOperation().equals("*")) {
            forceLeaf = false;
        }
        boolean res = unpackFuncNode(children.get(0), forceLeaf);
        if (res) {
            System.out.println(n);
            res = unpackFuncNode(children.get(1), forceLeaf);
            return res;
        }
        return false;
    }
}
