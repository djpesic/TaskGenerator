/******************************************************
 * A multi-line Javadoc-like comment about my grammar *
 ******************************************************/
grammar Linear;

@header {
package dependency.rules.sequencing;
import java.util.*;
import complexity.*;
import org.javatuples.Pair;
import org.apache.commons.lang3.math.NumberUtils;
import unification.*;
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

    private List<Pair<String,String>> solution = new ArrayList<>();    
}

// parser rules
formula returns [Complexity resultComplexity]:
    {
        //System.out.println("Starting linear sequencing rule");
    }
    s=segmentSequence1
    {
        if($s.valid){
            $resultComplexity = Complexity.createLinear("n");
        }
    }
    ;

segmentSequence1 returns [boolean valid] locals[
Set<String> vars, int numA, int numB, Pair<String,String> exprA, Pair<String,String> exprB,
Unification unification, StringBuilder formulas, Set<String> vars, Pair<String,String> solK,
Pair<String,String> solL, int numK, int numL, Pair<String,String> exprK, Pair<String,String> exprL,
Pair<String,String> exprKInit, Pair<String,String> exprLInit, int numKInit, int numLInit
]:
    {
        $valid = true;
    }
    ex=exprSequence
    wh=whileSegment
    {
        // count a=... and b=...
        for(Pair<String,String> e : $wh.exprs){
            if(e.getValue0().equals($wh.a)){
                $exprA = e;
                $numA++;
            }else if(e.getValue0().equals($wh.b)){
                $exprB = e;
                $numB++;
            }
        }
        
        if($numA==1 && $numB==1){
            try{
                //a=k*k
                $unification = new Unification();
                $formulas = new StringBuilder();
                $formulas.append("mul(k,k)");
                $formulas.append($exprA.getValue1());
                $vars = new HashSet<>();
                $vars.add("k");
                $unification.startUnification($formulas.toString(), $vars);
                solution = parseSolution($unification.getSolution());
                //b=l*l
                $formulas = new StringBuilder();
                $formulas.append("mul(l,l)");
                $formulas.append($exprB.getValue1());
                $vars.clear();
                $vars.add("l");
                $unification.startUnification($formulas.toString(), $vars);
                solution.addAll(parseSolution($unification.getSolution()));

                for(Pair<String, String> sol : solution){
                    if(sol.getValue0().equals("k")){
                        $solK = sol;
                    }else if(sol.getValue0().equals("l")){
                        $solL = sol;
                    }
                }

                for(Pair<String,String> e : $wh.exprs){
                    if(e.getValue0().equals($solK.getValue1())){
                        $exprK = e;
                        $numK++;
                    }else if(e.getValue0().equals($solL.getValue1())){
                        $exprL = e;
                        $numL++;
                    }
                }
                if($numK==1 && $numL==1){
                    //k=k+c
                    $formulas = new StringBuilder();
                    $formulas.append("add(k,c)");
                    $formulas.append($exprK.getValue1());
                    $vars.clear();
                    $vars.add("k");
                    $vars.add("c");
                    $unification.startUnification($formulas.toString(), $vars);
                    solution = parseSolution($unification.getSolution());
                    //l=l-c
                    $formulas = new StringBuilder();
                    $formulas.append("sub(l,c)");
                    $formulas.append($exprL.getValue1());
                    $vars.clear();
                    $vars.add("l");
                    $vars.add("c");
                    $unification.startUnification($formulas.toString(), $vars);
                    solution.addAll(parseSolution($unification.getSolution()));
                    
                    
                    for(Pair<String, String> sol : solution){
                        if(sol.getValue0().equals("k")){
                            if(!sol.getValue1().equals($exprK.getValue0())) $valid = false;
                        }else if(sol.getValue0().equals("l")){
                            if(!sol.getValue1().equals($exprL.getValue0())) $valid = false;
                        }else if(sol.getValue0().equals("c")){
                            //c>0
                            try{
                                double dbl = Double.parseDouble(sol.getValue1());
                                if(dbl<=0) $valid = false;
                            }catch(NumberFormatException ex){
                                $valid = false;
                            }
                        }
                    }   

                    for(Pair<String,String> e : $ex.exprs){
                        if(e.getValue0().equals("k")){
                            $numKInit++;
                            $exprKInit=e;
                        }else if(e.getValue0().equals("l")){
                            $numLInit++;
                            $exprLInit = e;
                        }
                    }
                    if($numKInit==1 && $numLInit==1){
                        //l=N (init)
                        if(!$exprLInit.getValue1().toLowerCase().equals("n")){
                            $valid = false;
                        }
                        //k=1 (init)
                        if(!$exprKInit.getValue1().equals("1")){
                            $valid = false;
                        }
                    }else{
                        $valid = false;
                    }
                    
                }else{
                    $valid = false;
                }

            }catch(Exception ex){
                ex.printStackTrace();
                $valid = false;
            }
        }else{
            $valid = false;
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