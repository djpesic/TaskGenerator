/******************************************************
 * A multi-line Javadoc-like comment about my grammar *
 ******************************************************/
grammar Sqrt;

@header {
package dependency.rules.sequencing;
import java.util.*;
import complexity.*;
import org.javatuples.Pair;
import org.apache.commons.lang3.math.NumberUtils;
import unification.*;
}

@parser::members{
 
    public void parseSolution(List<String> solution1){
        //solution in form {formalVal}=actualVal
        solution = new ArrayList<>();
        for(int i = 0;i<solution1.size();i++){
            String s = solution1.get(i);
            String formal = s.substring(s.indexOf("{")+1, s.lastIndexOf("}"));
            String actual = s.substring(s.indexOf("=")+1, s.lastIndexOf(";"));
            if(actual.equals(""))actual = formal;
            Pair<String,String> sol = new Pair<>(formal, actual);
            solution.add(sol);
        }

    }

    public boolean test(String loopBound, String otherLoopBound, String formulaA, String formulaK, String initK,
        List<Pair<String,String>> exExprs, List<Pair<String,String>> whExprs,
        Set<String> vars, Set<String> vars1){
        
        Unification unification = new Unification();
        int numA = 0;
        StringBuilder formulas = new StringBuilder();
        Pair<String,String> exprA=null, exprK=null, exprKInit=null;
        for(Pair<String,String> e : whExprs){
            if(e.getValue0().equals(loopBound)){
                exprA = e;
                numA++;
            }
            if(e.getValue0().equals(otherLoopBound)){
               return false;
            }
        }
        if(numA!=1) return false;
        
        try{
            formulas.append(formulaA);
            formulas.append(exprA.getValue1());
            unification.startUnification(formulas.toString(), vars);
            parseSolution(unification.getSolution());
            
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
        
        int numK = 0, numKInit=0;
        for(Pair<String,String> e : whExprs){
            if(e.getValue0().equals(solution.get(0).getValue1())){
                exprK = e;
                numK++;
            }
        }
        for(Pair<String,String> e : exExprs){
            if(e.getValue0().equals(solution.get(0).getValue1())){
                exprKInit = e;
                numKInit++;
            }
        }
        if(numK!=1) return false;
        if(numKInit==1){
            try{
                formulas.delete(0, formulas.length());
                formulas.append(formulaK);
                formulas.append(exprK.getValue1());
                unification.startUnification(formulas.toString(), vars1);
                parseSolution(unification.getSolution());

                for(Pair<String,String> s : solution){
                    if(s.getValue0().equals("k")){
                        if(!s.getValue1().equals(exprK.getValue0())){
                            return false;
                        }
                        if(!s.getValue1().equals(exprKInit.getValue0())){
                            return false;
                        }
                        if(!exprKInit.getValue1().toLowerCase().equals(initK.toLowerCase())){
                            return false;
                        }
                    }
                    if(s.getValue0().equals("c")){
                        try{
                            double dbl = Double.parseDouble(s.getValue1());
                            if(dbl<=0) return false;
                        }catch(NumberFormatException ex){
                            return false;
                        }
                    }
                }

            }catch(Exception ex){
                ex.printStackTrace();
                return false;
            }
        }else{
            return false;
        } 
        return true;
    }

    public boolean test1(String loopBound, String otherLoopBound, String formulaA, String formulaK, String initK,
        List<Pair<String,String>> exExprs, List<Pair<String,String>> whExprs,
        Set<String> vars, Set<String> vars1){
        
        Unification unification = new Unification();
        int numA = 0;
        StringBuilder formulas = new StringBuilder();
        Pair<String,String> exprA=null, exprK=null, exprKInit=null;
        for(Pair<String,String> e : whExprs){
            if(e.getValue0().equals(loopBound)){
                exprA = e;
                numA++;
            }
            if(e.getValue0().equals(otherLoopBound)){
               return false;
            }
        }
        if(numA!=1) return false;
        try{
            formulas.append(formulaA);
            formulas.append(exprA.getValue1());
            unification.startUnification(formulas.toString(), vars);
            parseSolution(unification.getSolution());
            for(Pair<String,String> sol : solution){
                if(sol.getValue0().equals("a")){
                    if(!sol.getValue1().equals(exprA.getValue0())) return false;
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
        
        int numK = 0, numKInit=0;
        for(Pair<String,String> e : whExprs){
            for(Pair<String, String> sol : solution){
                if(e.getValue0().equals(sol.getValue1()) && e.getValue0().equals("k")){
                    exprK = e;
                    numK++;
                }
            }
        }
        for(Pair<String,String> e : exExprs){
            for(Pair<String, String> sol : solution){
                if(e.getValue0().equals(sol.getValue1()) && e.getValue0().equals("k")){
                    exprKInit = e;
                    numKInit++;
                }
            }
        }
        if(numK!=1) return false;
        if(numKInit==1){
            try{
                formulas.delete(0, formulas.length());
                formulas.append(formulaK);
                formulas.append(exprK.getValue1());
                unification.startUnification(formulas.toString(), vars1);
                parseSolution(unification.getSolution());

                for(Pair<String,String> s : solution){
                    if(s.getValue0().equals("k")){
                        if(!s.getValue1().equals(exprK.getValue0())){
                            return false;
                        }
                        if(!s.getValue1().equals(exprKInit.getValue0())){
                            return false;
                        }
                        if(!exprKInit.getValue1().toLowerCase().equals(initK.toLowerCase())){
                            return false;
                        }
                    }
                    if(s.getValue0().equals("c")){
                        try{
                            double dbl = Double.parseDouble(s.getValue1());
                            if(dbl<=0) return false;
                        }catch(NumberFormatException ex){
                            return false;
                        }
                    }
                }

            }catch(Exception ex){
                ex.printStackTrace();
                return false;
            }
        }else{
            return false;
        } 
        return true;
    }

    private List<Pair<String,String>> solution = new ArrayList<>();    
}

// parser rules
formula returns [Complexity resultComplexity] locals [Set<String> refVars]:
    {
       // System.out.println("Starting sqrt rule");
    }
    s=segmentSequence1
    {
        if($s.valid){
            ExpNode e = new ExpNode(new VarNode("n"), new VarNode("1/2"));
            $resultComplexity = new Complexity(e);
        }
    }
    ;

segmentSequence1 returns [boolean valid] locals[Set<String> vars, Set<String> vars1]:
    {
        $valid = true;
    }
    ex=exprSequence
    wh=whileSegment
    {
        $vars = new HashSet<>();
        $vars.add("k");
        
        $vars1 = new HashSet<>();
        $vars1.add("k");
        $vars1.add("c");
        //a=k*k, k=k+c, k=0(init), c>0
        $valid = test($wh.a, $wh.b, "mul(k,k)", "add(k,c)", "0", $ex.exprs, $wh.exprs,
                        $vars, $vars1);

        $vars.add("a");
        if(!$valid){
            //a=a+k, k=k+c, k=0(init), c>0
            $valid = test1($wh.a, $wh.b, "add(a,k)", "add(k,c)", "0", $ex.exprs, $wh.exprs,
                        $vars, $vars1);
        }
    }
    ;

exprSequence returns[List<Pair<String,String>> exprs]:
    e=expression
    {
        $exprs = new ArrayList<>();
        Pair<String,String> p = new Pair($e.ls,$e.rs);
        if($e.ls != null && $e.rs !=null)
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

whileSegment returns [String a, String b, List<Pair<String,String>> exprs]:
    WHILE LPAREN
    l=leftVar
    COMMA r=rightVar
    {
        $a = $l.var; $b = $r.var;
        $exprs = new ArrayList<>();
    }
    (
    COMMA seq=exprSequence
    {
        $exprs = $seq.exprs;
    }
    )?
    RPAREN;

leftVar returns [String var] : v=variable {$var = $v.var;};
rightVar returns [String var] : v=variable {$var = $v.var;};
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