/******************************************************
 * A multi-line Javadoc-like comment about my grammar *
 ******************************************************/
grammar Logarithmic;

@header {
package dependency.rules.basic;
import java.util.*;
import complexity.*;
import org.javatuples.Pair;
import unification.*;
import org.apache.commons.lang3.math.NumberUtils;
}

@parser::members {
    
    private List<Pair<String,String>> solution = new ArrayList<>();
    private Map<String, Double> logBaseMap = new HashMap<>();
    
    public void unifyRightSide(String funcName, Pair<String,String> expr,
        String constName, StringBuilder formulas, Unification unif) throws UnifyException{
        formulas.delete(0, formulas.length());
        formulas.append(funcName+"("+expr.getValue0()+","+constName+")");
        formulas.append(expr.getValue1());
        Set<String>variables = new HashSet<>();
        variables.add(expr.getValue0());
        variables.add(constName);
        unif.startUnification(formulas.toString(), variables);
    }
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

    public boolean validateLeftVar(String leftVarName, Unification unif){
        parseSolution(unif.getSolution());
        for(Pair<String,String> sol : solution){
            if(sol.getValue1().equals(leftVarName)){
                return true;
            }
        }
        return false;
    }

    public boolean validateConst(String constName, Unification unif){
        parseSolution(unif.getSolution());
        for(Pair<String,String> sol : solution){
            if(sol.getValue0().equals(constName) && 
                NumberUtils.isCreatable(sol.getValue1())){
                try{
                    Double d = Double.parseDouble(sol.getValue1());
                    if(d>0) return true;
                    return false;
                }catch(NumberFormatException ex){
                    return false;
                }
            }
        }
        return false;
    }
    
    public boolean validateLogBase(String logBaseName, Unification unif){
        parseSolution(unif.getSolution());
        for(Pair<String,String> sol : solution){
            if(sol.getValue0().equals(logBaseName) && 
                NumberUtils.isCreatable(sol.getValue1())){
                try{
                    Double d = Double.parseDouble(sol.getValue1());
                    logBaseMap.put(logBaseName, d);
                    if(d>1) return true;
                    return false;
                }catch(NumberFormatException ex){
                    return false;
                }
            }
        }
        return false;
    }

    public boolean validateLogBases(boolean gte){
        try{
            if(gte)
                return (logBaseMap.get("k") > logBaseMap.get("k1"));
            return (logBaseMap.get("k") > logBaseMap.get("k1"));
        }catch(Exception ex){
            return false;
        }
    }

    
}

// parser rules
formula returns [Complexity resultComplexity]:
    {
        //System.out.println("Starting logarithmic rule");
    }
    s=segmentSequence 
    {
        if($s.valid == true){
            $resultComplexity = Complexity.createLog("n");
        }
    }
    ;

segmentSequence returns [boolean valid]:
    {$valid = true;}
    s1=segment { if(!($s1.valid)) $valid=false; }
    (
    s2=segment { if(!($s2.valid)) $valid=false; }
    )*
    ;

segment returns [boolean valid]:
    w=whileSegment
    {
        $valid = $w.valid;
    }
    | e=expression
    {
        $valid = true;
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

whileSegment returns [boolean valid] locals [int numA, int numB, int i, 
Pair<String,String> expr, String f1, String f2, Unification unif, StringBuilder formulas,
Set<String> variables, Pair<String,String> exprA, Pair<String,String> exprB,
List<String> solution]: 
WHILE LPAREN a=leftVar COMMA b=rightVar (COMMA exprs=exprSequence )?
    {
        if($a.var.equals($b.var)){
            $valid = false; // a==b
        }else{
            //count number of a=... and b=...
            for($i = 0;$i< $exprs.exprs.size();){
                $expr = $exprs.exprs.get($i);
                if($expr.getValue0().equals($a.var)){
                    $numA++;    
                    $i++;
                    $exprA = $expr;
                }
                else if($expr.getValue0().equals($b.var)){
                    $numB++;
                    $i++;
                    $exprB = $expr;
                }else{
                    $exprs.exprs.remove($i);
                }
            }
            $unif = new Unification();
            $formulas = new StringBuilder();
            if($numA==1 && $numB==0){
                //a=a*k
                try{
                    unifyRightSide("mul", $exprA, "k", $formulas, $unif);
                    $valid = validateLeftVar($exprA.getValue0(),$unif) && 
                            validateLogBase("k", $unif);
                }catch(Exception ex){
                    ex.printStackTrace();
                    $valid = false;
                }
            }else if($numA==0 && $numB==1){
                //b=b/k
                try{
                    unifyRightSide("divF", $exprB, "k", $formulas, $unif);
                    $valid = validateLeftVar($exprB.getValue0(),$unif) && 
                        validateLogBase("k", $unif);
                }catch(Exception ex){
                    ex.printStackTrace();
                    $valid = false;
                }
            }else if($numA==1 && $numB==1){
                //a=a*k, b=b-const
                try{
                    unifyRightSide("mul", $exprA, "k", $formulas, $unif);
                    $valid = validateLeftVar($exprA.getValue0(),$unif) && 
                            validateLogBase("k", $unif);
                
                    unifyRightSide("sub", $exprB, "const", $formulas, $unif);
                    $valid = $valid && validateLeftVar($exprB.getValue0(),$unif) && 
                        validateConst("const", $unif);
                }catch(Exception ex){
                    $valid = false;
                }
                //a=a*k, b=b+const
                try{
                    if(!$valid){
                        unifyRightSide("mul", $exprA, "k", $formulas, $unif);
                        $valid = validateLeftVar($exprA.getValue0(),$unif) && 
                                validateLogBase("k", $unif);

                        unifyRightSide("add", $exprB, "const", $formulas, $unif);
                        $valid = $valid && validateLeftVar($exprB.getValue0(),$unif) && 
                                validateConst("const", $unif);
                    }
                }catch(Exception ex){
                    $valid = false;
                }
                //a=a*k, b=b/k1
                try{
                    if(!$valid){
                        unifyRightSide("mul", $exprA, "k", $formulas, $unif);
                        $valid = validateLeftVar($exprA.getValue0(),$unif) && 
                                validateLogBase("k", $unif);

                        unifyRightSide("divF", $exprB, "k1", $formulas, $unif);
                        $valid = $valid && validateLeftVar($exprB.getValue0(),$unif) && 
                                validateLogBase("k1", $unif);
                        $valid = $valid && validateLogBases(true);
                    }
                }catch(Exception ex){
                    $valid = false;
                }
                // a=a*k, b=b*k1
                try{
                    if(!$valid){
                        unifyRightSide("mul", $exprA, "k", $formulas, $unif);
                        $valid = validateLeftVar($exprA.getValue0(),$unif) && 
                                validateLogBase("k", $unif);

                        unifyRightSide("mul", $exprB, "k1", $formulas, $unif);
                        $valid = $valid && validateLeftVar($exprB.getValue0(),$unif) && 
                                validateLogBase("k1", $unif);
                        $valid = $valid && validateLogBases(false);
                    }
                }catch(Exception ex){
                    $valid = false;
                }
                // b=b/k, a=a-const
                try{
                    if(!$valid){
                        unifyRightSide("divF", $exprB, "k", $formulas, $unif);
                        $valid = validateLeftVar($exprB.getValue0(),$unif) && 
                                validateLogBase("k", $unif);

                        unifyRightSide("sub", $exprA, "const", $formulas, $unif);
                        $valid = $valid && validateLeftVar($exprA.getValue0(),$unif) && 
                                validateConst("const", $unif);
                    }
                }catch(Exception ex){
                    $valid = false;
                }
                //b=b/k, a=a+const
                try{
                    if(!$valid){
                        unifyRightSide("divF", $exprB, "k", $formulas, $unif);
                        $valid = validateLeftVar($exprB.getValue0(),$unif) && 
                                validateLogBase("k", $unif);

                        unifyRightSide("add", $exprA, "const", $formulas, $unif);
                        $valid = $valid && validateLeftVar($exprA.getValue0(),$unif) && 
                                validateConst("const", $unif);
                    }
                }catch(Exception ex){
                    $valid = false;
                }
                //b=/k, a=a*k1
                try{
                    if(!$valid){
                        unifyRightSide("divF", $exprB, "k", $formulas, $unif);
                        $valid = validateLeftVar($exprB.getValue0(),$unif) && 
                                validateLogBase("k", $unif);

                        unifyRightSide("mul", $exprA, "k1", $formulas, $unif);
                        $valid = $valid && validateLeftVar($exprA.getValue0(),$unif) && 
                                validateLogBase("k1", $unif);
                        $valid = $valid && validateLogBases(true);
                    }
                }catch(Exception ex){
                    $valid = false;
                }
                //b=b/k, a=a/k1
                try{
                    if(!$valid){
                        unifyRightSide("divF", $exprB, "k", $formulas, $unif);
                        $valid = validateLeftVar($exprB.getValue0(),$unif) && 
                                validateLogBase("k", $unif);

                        unifyRightSide("divF", $exprA, "k1", $formulas, $unif);
                        $valid = $valid && validateLeftVar($exprA.getValue0(),$unif) && 
                                validateLogBase("k1", $unif);
                        $valid = $valid && validateLogBases(false);
                    }
                }catch(Exception ex){
                    $valid = false;
                }
            }
            else{
                $valid = false;
            }
            
        }
    }
    RPAREN;

leftVar returns[String var] : v=variable {$var=$v.var;};
rightVar returns[String var] : v=variable{$var=$v.var;};

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
LPAREN : '(';
RPAREN : ')';
COMMA : ',';
WHILE : 'while';
IDENT : ('a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_')+;