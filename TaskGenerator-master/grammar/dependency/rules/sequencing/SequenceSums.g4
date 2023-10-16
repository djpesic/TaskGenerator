/******************************************************
 * A multi-line Javadoc-like comment about my grammar *
 ******************************************************/
grammar SequenceSums;

@header {
package dependency.rules.sequencing;
import java.util.*;
import complexity.*;
import org.javatuples.Pair;
import org.apache.commons.lang3.math.NumberUtils;
import unification.*;
import utils.YacasExecutor;
import com.rits.cloning.Cloner;
}

@parser::members{
 
    public List<Pair<String,String>> parseSolution(List<String> solution1){
        //solution in form {formalVal}=actualVal
        List<Pair<String,String>> solution = new ArrayList<>();
        for(int i = 0;i<solution1.size();i++){
            String s = solution1.get(i);
            String formal = s.substring(s.indexOf("{")+1, s.lastIndexOf("}"));
            String actual = s.substring(s.indexOf("=")+1, s.lastIndexOf(";"));
            if(actual.equals(""))actual = formal;
            Pair<String,String> sol = new Pair<>(formal, actual);
            solution.add(sol);
        }
        return solution;

    }
    
    public boolean checkExpression(StringBuilder formulas, Set<String> vars){
        try{
            Unification unification = new Unification();
            unification.startUnification(formulas.toString(), vars);
            solution = parseSolution(unification.getSolution());
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    public String getSolution(String formal){
        for(Pair<String,String> s : solution){
            if(s.getValue0().equals(formal)){
                return s.getValue1(); // actual
            }
        }
        return null;
    }

    public boolean checkK(int val){
        String solK = getSolution("k");
        try{
            if(Double.parseDouble(solK) > val){
                return true;
            }
            return false;

        }catch(NumberFormatException ex){
        }
        return false;
    }
    
    public Complexity calculateResultComplexity(String f, String g){
        if(f.equals("n")){
            if(g.equals("n^2")){
                VarNode v = new VarNode("n");
                NumNode n = new NumNode(2);
                ExpNode exp = new ExpNode(v,n);
                return new Complexity(exp);//n^2
            }else if(g.equals("log(n^2)")){
                return Complexity.createLinear("n");//n
            }else if(g.equals("Sqrt(n^2)")){
                return Complexity.createLinear("n");//n
            }else if(g.equals("n!")){
                return Complexity.createFact("n");//n!
            }else if(g.equals("log(n!)")){
                VarNode v = new VarNode("n");
                LogNode l = new LogNode(new VarNode("n"));
                MulNode m = new MulNode(v,l);
                return new Complexity(m);//nlog(n)
            }else if(g.equals("Sqrt(n!)")){
                FactNode fct = new FactNode(new VarNode("n"));
                SqrtNode s = new SqrtNode(fct);
                return new Complexity(s);//Sqrt(n!)
            }else if(g.equals("n")){
                return Complexity.createLinear("n");//n
            }else if(g.equals("log(n)")){
                return Complexity.createLinear("n");//n
            }else if(g.equals("Sqrt(n)")){
                return Complexity.createLinear("n");//n
            }else if(g.matches("[0-9]+\\^n")){
                String baseS = g.substring(0, g.indexOf("^"));
                int base = Integer.parseInt(baseS);
                NumNode n = new NumNode(base);
                VarNode v = new VarNode("n");
                return new Complexity(new ExpNode(n,v));// o^n
            }else if(g.matches("log\\([0-9]+\\^n\\)")){
                return Complexity.createLinear("n");//n
            }else if(g.matches("Sqrt\\([0-9]+\\^n\\)")){
                String baseS = g.substring(5, g.indexOf("^"));
                int base = Integer.parseInt(baseS);
                NumNode n = new NumNode(base);
                VarNode v = new VarNode("n");
                ExpNode e = new ExpNode(n,v);
                return new Complexity(new SqrtNode(new GroupNode(e)));// Sqrt(o^n)
            }
            else{
                System.out.println("Wrong g(s) complexity");
                return null;
            }
        }else if(f.equals("log(n)")){
            if(g.equals("log(n)^2")){
                VarNode v = new VarNode("n");
                LogNode l = new LogNode(v);
                NumNode n = new NumNode(2);
                ExpNode exp = new ExpNode(l,n);
                return new Complexity(exp);//log(n)^2
            }else if(g.equals("log(log(n)^2)")){
                return Complexity.createLog("n");//log(n)
            }else if(g.equals("Sqrt(log(n)^2)")){
                return Complexity.createLog("n");//log(n)
            }else if(g.equals("log(n)!")){
                FactNode fct = new FactNode(new LogNode(new VarNode("n")));
                return new Complexity(fct);//log(n)!
            }else if(g.equals("log(log(n)!)")){
                VarNode v = new VarNode("n");
                LogNode l = new LogNode(new VarNode("n"));
                LogNode ll = new LogNode(l);
                MulNode m = new MulNode(l,ll);
                return new Complexity(m);//log(n)loglog(n)
            }else if(g.equals("Sqrt(log(n)!)")){
                FactNode fct = new FactNode(new VarNode("n"));
                SqrtNode s = new SqrtNode(new LogNode(fct));
                return new Complexity(s);//Sqrt(log(n)!)
            }else if(g.equals("log(n)")){
                return Complexity.createLog("n");//log(n)
            }else if(g.equals("log(log(n))")){
                return Complexity.createLog("n");//log(n)
            }else if(g.equals("Sqrt(log(n))")){
                return Complexity.createLog("n");//log(n)
            }else if(g.matches("[0-9]+\\^log\\(n\\)")){
                String baseS = g.substring(0, g.indexOf("^"));
                int base = Integer.parseInt(baseS);
                NumNode n = new NumNode(base);
                VarNode v = new VarNode("n");
                LogNode l = new LogNode(v);
                return new Complexity(new ExpNode(n,l));// o^log(n)
            }else if(g.matches("log\\([0-9]+\\^log\\(n\\)\\)")){
                return Complexity.createLog("n");//log(n)
            }else if(g.matches("Sqrt\\([0-9]+\\^log\\(n\\)\\)")){
                String baseS = g.substring(5, g.indexOf("^"));
                int base = Integer.parseInt(baseS);
                NumNode n = new NumNode(base);
                VarNode v = new VarNode("n");
                ExpNode e = new ExpNode(n,new LogNode(v));
                return new Complexity(new SqrtNode(new GroupNode(e)));// Sqrt(o^log(n))
            }
            else{
                System.out.println("Wrong g(s) complexity 1");
                return null;
            }
        }else if(f.equals("Sqrt(n)")){
            if(g.equals("Sqrt(n)^2") || g.equals("n")){
                return Complexity.createLinear("n");//n
            }else if(g.equals("log(Sqrt(n)^2)")){
                return Complexity.createSqrt("n");//Sqrt(n)
            }else if(g.equals("Sqrt(Sqrt(n)^2)")){
                return Complexity.createSqrt("n");//Sqrt(n)
            }else if(g.equals("Sqrt(n)!")){
                FactNode fct = new FactNode(new SqrtNode(new VarNode("n")));
                return new Complexity(fct);//Sqrt(n)!
            }else if(g.equals("log(Sqrt(n)!)")){
                return Complexity.createSqrt("n");// Sqrt(n)
            }else if(g.equals("Sqrt(Sqrt(n)!)")){
                FactNode fct = new FactNode(new VarNode("n"));
                SqrtNode s = new SqrtNode(new SqrtNode(fct));
                return new Complexity(s);//Sqrt(Sqrt(n)!)
            }else if(g.equals("Sqrt(n)")){
                return Complexity.createSqrt("n");//Sqrt(n)
            }else if(g.equals("log(Sqrt(n))")){
                return Complexity.createSqrt("n");//Sqrt(n)
            }else if(g.equals("Sqrt(Sqrt(n))")){
                return Complexity.createSqrt("n");//Sqrt(n)
            }else if(g.matches("[0-9]+\\^Sqrt\\(n\\)")){
                return Complexity.createSqrt("n");// Sqrt(n)
            }else if(g.matches("log\\([0-9]+\\^Sqrt\\(n\\)\\)")){
                return Complexity.createSqrt("n");// Sqrt(n)
            }else if(g.matches("Sqrt\\([0-9]+\\^Sqrt\\(n\\)\\)")){
                String baseS = g.substring(5, g.indexOf("^"));
                int base = Integer.parseInt(baseS);
                NumNode n = new NumNode(base);
                VarNode v = new VarNode("n");
                ExpNode e = new ExpNode(n,new SqrtNode(v));
                return new Complexity(new SqrtNode(new GroupNode(e)));// Sqrt(o^Sqrt(n))
            }
            else{
                System.out.println("Wrong g(s) complexity 2");
                return null;
            }
        }else{
            System.out.println("Sequence sum complexity can't be calculated");
            return null;
        }
    }

    private List<Pair<String,String>> solution = new ArrayList<>(); 
    public Complexity compl;   
    public Complexity compl1; //first in the sequence
    public Complexity compl2; // second in the sequence
    Complexity sCompl = null;   
    
}

formula returns [Complexity resultComplexity] locals [Set<String> refVars]:
    {
       // System.out.println("Starting SequenceSums rule");
    }
    s=segmentSequence
    {
        if($s.valid == true){
            //System.out.println("compl1: "+compl1);
            //System.out.println("compl2: "+compl2);
            //System.out.println("scompl: "+sCompl);
            String compl1Str = YacasExecutor.simplify(compl1.toString());
            String compl2Str = YacasExecutor.simplify(compl2.toString());
            $resultComplexity = calculateResultComplexity(compl1Str, compl2Str);
            //System.out.println("result: "+$resultComplexity.toString());
        }
    }
    ;

segmentSequence returns [boolean valid] locals[
int numA, int numB, int numC, int numD, int numAInit, int numBInit,
int numCInit, int numDInit, Pair<String,String> exprAInit,
Pair<String,String> exprBInit, Pair<String,String> exprCInit,
Pair<String,String> exprDInit, StringBuilder formulas,
Set<String> vars
]:
    {$valid = true;}
    e1=exprSequence
    s1=segment1
    e2=exprSequence
    s2=segment1
    {
        // e1 must not change a, b, c and d bounds, except initializations of a,b and c
        for(Pair<String,String> e:$e1.exprs){
            if(e.getValue0().equals($s1.a)){
                $numA++;
                $exprAInit = e;
            }
            if(e.getValue0().equals($s1.b)){
                $numB++;
                $exprBInit = e;
            }
            if(e.getValue0().equals($s2.a)){
                $numC++;
                $exprCInit = e;
            }
            if(e.getValue0().equals($s2.b)){
                $numD++;
                $exprDInit = e;
            }
        }
        if($numA==1){
            if($exprAInit.getValue1().equals("1")){
                $valid = true;
            }else if(!$exprAInit.getValue1().contains("expr")){
                String var = $exprAInit.getValue1();
                for(Pair<String,String> e:$e1.exprs){
                    if(e.getValue0().equals(var) && 
                        (e.getValue1().equals("1") || e.getValue1().toLowerCase().equals("n"))){
                        $valid = true;
                        break;
                    }else{
                        $valid = false;
                        //System.out.println("valid16 false");
                    }
                }
            }
            else{
                $valid = false;
              //  System.out.println("valid1 false");
            }
        }else{
            $valid = false;
           // System.out.println("valid2 false");
        }
        if($numB==1){
            if($exprBInit.getValue1().toLowerCase().equals("n")){
                $valid = $valid && true;
            }else if(!$exprBInit.getValue1().contains("expr")){
                String var = $exprBInit.getValue1();
                for(Pair<String,String> e:$e1.exprs){
                    if(e.getValue0().equals(var) && 
                        (e.getValue1().equals("1") || e.getValue1().toLowerCase().equals("n"))){
                        $valid = true;
                        break;
                    }else{
                        $valid = false;
                        //System.out.println("valid17 false");
                    }
                }
            }
            else{
                $valid = false;
                //System.out.println("valid3 false");
            }
        }else{
            $valid = false;
           // System.out.println("valid4 false");
        }
        if($numC>0){
            $valid = false;
           // System.out.println("valid5 false");
        }
        if($numD > 0){
            $valid = false;
           // System.out.println("valid6 false");
        }

        // if e2 changes c, it must be init.
        $numC = 0; $numD = 0;
        for(Pair<String,String> e:$e2.exprs){
            if(e.getValue0().equals($s2.a)){
                $numC++;
                $exprCInit = e;
            }
            if(e.getValue0().equals($s2.b)){
                $numD++;
                $exprDInit = e;
            }
            
        }
        if($numC == 1){
            if(!$exprCInit.getValue1().equals("1")){
                $valid = false;
              //  System.out.println("valid7 false");
            }
        }else {
            $valid = false;
           // System.out.println("valid8 false");
        }
        // check for s expression in segment s1
        if($valid){
            $valid = false;
            $vars = new HashSet<>();
            Cloner cloner = new Cloner();
      
            for(Pair<String,String> e : $s1.exprs){
                $formulas = new StringBuilder();
                // s=s+k*i
                if(!$valid){
                    //System.out.println("valid18 false");
                    $formulas.append("expr(s,add(s,mul(k,i)))");
                    $formulas.append("expr(").append(e.getValue0()).append(",");
                    $formulas.append(e.getValue1()).append(")");
                    $vars.add("s"); $vars.add("k"); $vars.add("i");
                    $valid = checkExpression($formulas, $vars);

                    $valid = $valid && checkK(1);
                    if($valid){
                        //f(n)^2
                        ExpNode ex = new ExpNode(new GroupNode(compl1.getRoot()), new NumNode(2));
                        sCompl = new Complexity(ex);
                    }
                }
                
                if(!$valid){ //s=s*k*i
                   // System.out.println("valid9 false");
                    $formulas = new StringBuilder();
                    $formulas.append("expr(s,mul(mul(s,k),i))");
                    $formulas.append("expr(").append(e.getValue0()).append(",");
                    $formulas.append(e.getValue1()).append(")");
                    $vars.clear();
                    $vars.add("s"); $vars.add("k"); $vars.add("i");
                    $valid = checkExpression($formulas, $vars);
                    
                    $valid = $valid && checkK(1);
                    
                    if($valid){
                        // f(n)!
                        FactNode f = new FactNode(new GroupNode(compl1.getRoot()));
                        sCompl = new Complexity(f);
                    }
                }
                if(!$valid){//s=s+k
                   // System.out.println("valid10 false");
                    $formulas = new StringBuilder();
                    $formulas.append("expr(s,add(s,k))");
                    $formulas.append("expr(").append(e.getValue0()).append(",");
                    $formulas.append(e.getValue1()).append(")");
                    $vars.clear();
                    $vars.add("s"); $vars.add("k");
                    $valid = checkExpression($formulas, $vars);

                    $valid = $valid && checkK(0);
                    if($valid){
                        // f(n)
                        sCompl = compl1;
                    }
                }
                if(!$valid){//s=s*k
                   // System.out.println("valid11 false");
                    $formulas = new StringBuilder();
                    $formulas.append("expr(s,mul(s,k))");
                    $formulas.append("expr(").append(e.getValue0()).append(",");
                    $formulas.append(e.getValue1()).append(")");
                    $vars.clear();
                    $vars.add("s"); $vars.add("k");
                    $valid = checkExpression($formulas, $vars);

                    $valid = $valid && checkK(1);
                    if($valid){//k^f(n)
                        int k = Integer.parseInt(getSolution("k"));
                        ExpNode ex = new ExpNode(new NumNode(k), new GroupNode(compl1.getRoot()));
                        sCompl = new Complexity(ex);
                    }
                }
                if($valid){
                    // s must not be a,b and c
                    String solS = getSolution("s");
                    if(solS.equals($s1.a) || solS.equals($s1.b) || solS.equals($s2.a)){
                      // System.out.println("valid12 false");
                        $valid = false;
                    }else {
                    // if e2 changes d, it must be d=s
                        if($numD==1){
                            if(!$exprDInit.getValue1().equals(solS)){
                                $valid = false;
                               //System.out.println("valid13 false");
                            }
                        }else if($numD==0){
                            if(!solS.equals($s2.b)){
                                $valid = false;
                                //System.out.println("valid14 false");
                            }
                        }else{
                            $valid = false;
                           // System.out.println("valid15 false");
                        }
                    }
                }
                if($valid){
                    //g(s)
                    GroupNode g = new GroupNode(sCompl.getRoot());
                    g = cloner.deepClone(g);
                    
                    //System.out.println("compl2 old "+compl2);
                    compl2.applySubfunction (g, "n");
                    break;
                }
            }

        }
        
    }
    ;

segment1 returns [List<Pair<String,String>> exprs, String a, String b]:
    w=whileSegment 
    {
        $exprs = $w.exprs;
        $a = $w.l;
        $b = $w.r;
    }
    ;

expression returns [String ls, String rs]:
    EXPR LPAREN 
    ls1=leftSide  {$ls = $ls1.text;}
    COMMA 
    rs1=rightSide
    {
        $rs = $rs1.text;
    }
    RPAREN
    ;
leftSide : variable;
rightSide : variable (LPAREN argList RPAREN)?;
argList : a=arg (COMMA a1=arg)*;

arg : rs=rightSide;

whileSegment returns [List<Pair<String,String>> exprs, String l, String r]:
    WHILE LPAREN
    ls=leftVar
    COMMA rs=rightVar
    {
        $l = $ls.text;
        $r = $rs.text;
    }
    COMMA s=exprSequence { $exprs = $s.exprs; }
    RPAREN;

exprSequence returns[List<Pair<String,String>> exprs] locals[Pair<String,String> p]: 
    e1=expression
    {
        $exprs = new ArrayList<>();
        Pair<String,String> p = new Pair($e1.ls,$e1.rs);
        if($e1.ls != null && $e1.rs !=null)
            $exprs.add(p);
    }
    (
    e2=expression
    {
        p = new Pair($e2.ls,$e2.rs);
        $exprs.add(p);
    }
    )*
    ;

leftVar : variable;
rightVar : variable;
variable returns [String var] :
    i=IDENT
    {
        $var = $i.text;
    }
    ;

//lexer rules
WS
   : [ \t\r\n] -> skip
   ;
EXPR : 'expr';
IF : 'if';
THEN : 'then';
ELSE : 'else';
LPAREN : '(';
RPAREN : ')';
COMMA : ',';
WHILE : 'while';
IDENT : ('a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_')+;