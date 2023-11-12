package complexity;

import java.util.List;
import java.util.Set;
import visitors.ComplexityVisitor;

/**
 *
 */
public class Complexity {

    private ComplexityNode root;
    
    public void accept(ComplexityVisitor visitor){
        root.accept(visitor);
    }

    @Override
    public String toString() {
        return root.toString();
    }

    public static Complexity createConstant() {
        ComplexityNode node = new ConstNode();
        Complexity compl = new Complexity(node);
        return compl;
    }

    public static Complexity createLinear(String var) {
        VarNode node = new VarNode(var);
        return new Complexity(node);
    }

    public static Complexity createLog(String var) {
        VarNode v = new VarNode(var);
        LogNode l = new LogNode(v);
        return new Complexity(l);
    }

    public static Complexity createSqrt(String var) {
        return new Complexity(new SqrtNode(new VarNode(var)));
    }

    public static Complexity createExp(int base, String exp) {
        ExpNode root = new ExpNode(new NumNode(base), new VarNode(exp));
        return new Complexity(root);
    }

    public static Complexity createPoly(int degree, String base) {
        ExpNode root = new ExpNode(new VarNode(base), new NumNode(degree));
        return new Complexity(root);
    }

    public static Complexity createFact(String var) {
        FactNode root = new FactNode(new VarNode(var));
        return new Complexity(root);
    }

    public static Complexity multiply(Complexity compl1, Complexity compl2) {
        MulNode m = new MulNode(new GroupNode(compl1.getRoot()), new GroupNode(compl2.getRoot()));
        return new Complexity(m);
    }

    public void applySubfunction(GroupNode subFunc, String varName) {
        try {
            if (root instanceof VarNode) {
                VarNode v = (VarNode) root;
                if (v.getVar().equals(varName)) {
                    root = subFunc;
                    return;
                }
            }
            root.applySubFunc(subFunc, varName);
        } catch (StackOverflowError err) {
            System.err.println("ukebano");
        }
    }

    public boolean isConstant() {
        return (root instanceof ConstNode);
    }

    public boolean isNone() {
        return (root instanceof NoneNode);
    }

    public Complexity(ComplexityNode root) {
        this.root = root;
    }

    public ComplexityNode getRoot() {
        return root;
    }

    public void setRoot(ComplexityNode root) {
        this.root = root;
    }

}
