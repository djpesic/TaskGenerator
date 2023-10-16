/******************************************************
 * A multi-line Javadoc-like comment about my grammar *
 ******************************************************/
grammar TwoLoops;

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
    public Complexity compl1;
    public Complexity compl2;   
}

// parser rules
formula returns [Complexity resultComplexity] locals [Set<String> refVars]:
    {
        //System.out.println("Starting twoLoops rule");
    }
    s=segmentSequence1
    {
        if($s.valid == true){
            $resultComplexity = ComplexityOperations.sequence(compl1, compl2);
        }
    }
    ;

segmentSequence1 returns [boolean valid] locals[Pair<String,String> exprC,
Pair<String,String> exprD, int numAInit, int numBInit, int numCInit, int numDInit,
Pair<String,String> exprAInit, Pair<String,String> exprBInit,
Pair<String,String> exprCInit, Pair<String,String> exprDInit]:
    {$valid = true;}
    (e1=exprSequence)?
    s1=whileSegment1
    (e2=exprSequence)?
    s2=whileSegment
    {
        try{
            if($s1.a.equals($s2.c) || $s1.a.equals($s2.d) || $s1.b.equals($s2.c) || $s1.b.equals($s2.d)){
                //a!=b, a!=c, b!=c, b!=d
                $valid = false;
                //System.out.println("valid5 = false");
            }else{
                //first loop must not change c and d
                for(Pair<String,String> e : $s1.exprs){
                    if(e.getValue0().equals($s2.c)){
                        $exprC = e;
                        $valid = false;
                        // System.out.println("valid1 = false");
                    }
                    if(e.getValue0().equals($s2.d)){
                        $valid = false;
                       // System.out.println("valid2 = false");
                        $exprD = e;
                    }
                }
                if(!$valid){
                    if($exprC!=null && $exprD!=null){
                        // in the first loop: c=const1, d=const2
                        try{
                            Double.parseDouble($exprC.getValue1());
                            Double.parseDouble($exprD.getValue1());
                            compl2 = Complexity.createConstant();
                            $valid = true;
                        }
                        catch(Exception ex){
                           // System.out.println("valid3 = false");
                        }
                    }
                }
                if($valid){
                    $valid = false;
                    if($e1.text!=null){
                        //a=1, b=N init expressions present
                        for(Pair<String,String> e : $e1.exprs){
                            if(e.getValue0().equals("a")){
                                $numAInit++;
                                $exprAInit=e;
                            }else if(e.getValue0().equals("b")){
                                $numBInit++;
                                $exprBInit = e;
                            }
                        }
                        if($numAInit==1 && $numBInit==1){
                            if($exprAInit.getValue1().equals("0")){
                                $valid = true;
                            }
                            if($exprBInit.getValue1().toLowerCase().equals("n")){
                                $valid = true;
                            }
                        }
                    }
                    if($e2.text!=null && $valid){
                        //c=1, d=N init expressions present
                        $valid = false;
                        for(Pair<String,String> e : $e2.exprs){
                            if(e.getValue0().equals("c")){
                                $numCInit++;
                                $exprCInit=e;
                            }else if(e.getValue0().equals("d")){
                                $numDInit++;
                                $exprDInit = e;
                            }
                        }
                        if($numCInit==1 && $numDInit==1){
                            if($exprCInit.getValue1().equals("0")){
                                $valid = true;
                            }
                            if($exprDInit.getValue1().toLowerCase().equals("n")){
                                $valid = true;
                            }
                        }
                    }
                    // if(!$valid)System.out.println("valid6 false");
                }
            }
        }catch(Exception ex){
            $valid = false;
           // System.out.println("valid7 false");
        }
    }
    ;

segmentSequence :
    s1=segment
    (
    s2=segment
    )*
    ;

segment :
    whileSegment 
    | e=expression 
    | ifSeg=ifSegment
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
logicalFunction :
    v=variable
    (
    LPAREN al=argList RPAREN
    )?
    ;

argList : a=arg (COMMA a1=arg)*;

arg : rs=rightSide;

whileSegment returns [String c, String d]:
    WHILE LPAREN
    e1=leftVar
    COMMA e2=rightVar
    (COMMA s=segmentSequence)?
    RPAREN
    { $c = $e1.text; $d = $e2.text; }
    ;

whileSegment1 returns [String a, String b, List<Pair<String,String>> exprs]:
    WHILE LPAREN
    e1=leftVar
    COMMA e2=rightVar
    COMMA s=exprSequence
    RPAREN
    { 
        $exprs = $s.exprs;
        $a = $e1.text;
        $b = $e2.text;
    }
    ;

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
        if($e1.ls != null && $e1.rs !=null)
            $exprs.add(p);
    }
    )*
    ;

ifSegment:
    IF LPAREN
    cond=logicalFunction
    COMMA THEN LPAREN
    (
    seq=segmentSequence
    )?
    RPAREN
    (
    COMMA ELSE LPAREN
    seq1=segmentSequence
    RPAREN
    )?
    RPAREN
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