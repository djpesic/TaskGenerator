/******************************************************
 * A multi-line Javadoc-like comment about my grammar *
 ******************************************************/
grammar NestingRulesSums;

@header {
package dependency.rules.nesting;
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
    public Complexity compl;   
    public Complexity compl1; //inner loop O(g(n))
    public Complexity compl2; // outer loop O(f(n))
    public boolean expSum = false;
    
    //sum g(k^i) from 1 to f(b)
   
    public Complexity calculateExpSum(int k){
        String compl1Str = compl1.toString();
        String compl2Str = compl2.toString();
        if(compl1Str.equals("n")){
           
            if(compl2Str.equals("n")){
                return Complexity.createExp(k, "n");
            }
            else if(compl2Str.contains("log")){
                return Complexity.createLinear("n");
            }
            else if(compl2Str.equals("n^(1/2)") || compl2Str.equals("Sqrt(n)")){
                VarNode v = new VarNode("1/2");
                VarNode v1 = new VarNode("n");
                GroupNode g = new GroupNode(new ExpNode(v1,v)); //n^(1/2)
                ExpNode exp = new ExpNode(new NumNode(k), g); //k^(n^(1/2))
                Complexity ret = new Complexity(exp);
                return ret;
            }
        }
        else if(compl1Str.contains("log")){
            if(compl2Str.equals("n")){
                return Complexity.createPoly(2, "n");  // n^2
            }
            else if(compl2Str.contains("log")){
                GroupNode g = new GroupNode(new LogNode(new VarNode("n")));
                ExpNode exp = new ExpNode(g, new NumNode(2));  // (log(n))^2
                Complexity ret = new Complexity(exp);
                return ret;
            }
            else if(compl2Str.equals("n^(1/2)") || compl2Str.equals("Sqrt(n)")){
                return Complexity.createLinear("n");
            }
        }
        else if(compl1Str.equals("n^(1/2)") || compl2Str.equals("Sqrt(n)")){
            if(compl2Str.equals("n")){
                // (k^n)^(1/2)
                VarNode v = new VarNode("1/2");
                ExpNode e1 = new ExpNode(new NumNode(k), new VarNode("n"));
                GroupNode g = new GroupNode(e1);
                ExpNode e2 = new ExpNode(g,v);
                Complexity compl = new Complexity(e2);
                return compl;
            }
            else if(compl2Str.contains("log")){
                VarNode v = new VarNode("1/2");
                VarNode v1 = new VarNode("n");
                ExpNode e = new ExpNode(v1,v);       //n^(1/2)
                Complexity compl = new Complexity(e);
                return compl;
            }
            else if(compl2Str.equals("n^(1/2)") || compl2Str.equals("Sqrt(n)")){
               // (  k^(n^(1/2))  )^(1/2)
               VarNode v1 = new VarNode("1/2");
               VarNode v2 = new VarNode("n");
               GroupNode g1 = new GroupNode(new ExpNode(v2, v1));
               NumNode n = new NumNode(k);
               ExpNode e = new ExpNode(n, g1);
               GroupNode g2 = new GroupNode(e);
               ExpNode e1 = new ExpNode(g2, new VarNode("1/2"));
               Complexity compl = new Complexity(e1);
               return compl;
            }
        }
        //System.out.println("Complexity can't be found in the exp sum lookup table");
        return null;
    }
    // sum g(i*k) from 1 to f(b)
    public Complexity calculateSum(int k){
        
        String compl1Str = compl1.toString();
        String compl2Str = compl2.toString();
        if(compl1Str.equals("n")){
           
            if(compl2Str.equals("n")){
                return Complexity.createPoly(2, "n"); //n^2
            }
            else if(compl2Str.contains("log")){
                GroupNode g = new GroupNode(new LogNode(new VarNode("n")));
                ExpNode exp = new ExpNode(g, new NumNode(2));  // (log(n))^2
                Complexity ret = new Complexity(exp);
                return ret;
            }
            else if(compl2Str.equals("n^(1/2)")  || compl2Str.equals("Sqrt(n)")){
                    return Complexity.createLinear("n");
            }
        }
        else if(compl1Str.contains("log")){
            String base = compl1Str.substring(3,4);
            if(compl2Str.equals("n")){
                return Complexity.createLinear("n");
            }
            else if(compl2Str.contains("log")){
                // log(n)*log(log(n))
                LogNode l1 = new LogNode(new VarNode("n"));
                LogNode l2 = new LogNode(new VarNode("n"));
                GroupNode g1 = new GroupNode(l2);
                LogNode l3 = new LogNode(g1); //loglog
                MulNode m = new MulNode(l1, l3);
                Complexity compl = new Complexity(m);
                return compl;
            }
            else if(compl2Str.equals("n^(1/2)") || compl2Str.equals("Sqrt(n)")){
               //  (n^(1/2))  *  log(n^(1/2))
               ExpNode e1 = new ExpNode(new VarNode("n"), new VarNode("1/2"));

               ExpNode e2 = new ExpNode(new VarNode("n"), new VarNode("1/2"));
               LogNode l = new LogNode(new GroupNode(e2));
               MulNode m = new MulNode(new GroupNode(e1), l);
               Complexity compl = new Complexity(m);
               return compl;
            }
        }
        else if(compl1Str.equals("n^(1/2)")  || compl2Str.equals("Sqrt(n)")){
           // System.out.println("Can not be calculated");
            return null;
        }
        //System.out.println("Complexity can't be fout in the sum lookup table");
        return null;
    }
}

// parser rules
formula returns [Complexity resultComplexity] locals [Set<String> refVars]:
    {
       // System.out.println("Starting NestingRulesSums rule");
    }
    s=segmentSequence1
    {
        if($s.valid == true){
            if(expSum)
                $resultComplexity = calculateExpSum($s.k);
            else
                $resultComplexity = calculateSum($s.k);
        }
    }
    ;

segmentSequence1 returns [boolean valid,int k] locals[int numC, int numD, double d] :
    ex=exprSequence
    wh=whileSegment1
    {
        //c=1  
        //d=n
        $valid = $wh.valid;
        if($valid){
            $numC = 0; $numD=0;
            for(Pair<String,String> e : $ex.exprs){
                if(e.getValue0().equals($wh.c)){
                    try{
                        $d = Double.parseDouble(e.getValue1());
                        if($d==1)
                            $valid = true;
                        else{
                           //System.out.println("valid1 false");
                            $valid = false;
                        }
                        $numC++;
                    }catch(Exception ex){
                        //System.out.println("valid2 false");
                        $valid = false;
                    }
                }
                if(e.getValue0().equals($wh.d)){
                    if(e.getValue1().toLowerCase().equals("n")){
                        $valid = true;
                    }else{
                        $valid = false;
                        //System.out.println("valid3 false");
                    }
                    $numD++;
                }
            }
            if($numC==0 && $wh.initC){
                $valid = false;
                //System.out.println("valid17 false");
            }else if($numD==0 && $wh.initD){
                $valid = false;
                //System.out.println("valid18 false");
            }
            if($valid){
                if($numC==1 && $numD==0){
                    $valid = true;
                }
                else if($numC==0 && $numD==1){
                    $valid = true;
                }
                else if($numC==1 && $numD==1){
                    $valid = true;
                }else{
                    $valid = false;
                    //System.out.println("valid4 false"+" numC= "+$numC+" numD= "+$numD);
                }
            }
            if($valid){
                $k = $wh.k1;
            }
        }
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

whileSegment returns [String c, String d, List<Pair<String, String>> exprs]:
    WHILE LPAREN
    e1=leftVar
    COMMA e2=rightVar
    (COMMA s=exprSequence)?
    RPAREN
    {
        $c = $e1.text; $d = $e2.text;
        if($s.text == null){
            $exprs = new ArrayList<>();
        }
        else{
            $exprs = $s.exprs;
        }
    }
    ;

whileSegment1 returns [boolean valid, String c, String d, boolean initC, boolean initD,
int k1] locals[int numC, int numD, Pair<String,String> exprC, 
Pair<String,String> exprD, Unification unification, StringBuilder formulas, 
Set<String> vars, Pair<String,String> solC, Pair<String,String> solD]:
    {$valid = true; $initC = false; $initD = false;}
    WHILE LPAREN
    e1=leftVar
    COMMA e2=rightVar
    COMMA ex=exprSequence
    wh=whileSegment
    {
        $c = $wh.c;
        $d = $wh.d;
    }
    RPAREN
    {
        for(Pair<String,String> e : $ex.exprs){
            if(e.getValue0().equals($wh.c)){
                $numC++;
                $exprC = e;
            }
            if(e.getValue0().equals($wh.d)){
                $numD++;
                $exprD = e;
            }
        }
        if($numC==0 && $numD==0){
            $valid = false; 
            //System.out.println("valid6 false");
        }
        else if($numC>1 && $numD>1){
            $valid = false;
            //System.out.println("valid7 false");
        }
        else{

            String[] ops = {"mul", "add"};
            String[] opsCompl = {"divF", "sub"};
            for(int i = 0;i<ops.length;i++){
                String op = ops[i];
                String opCompl = opsCompl[i];
                $valid = true;
                try{
                    if($numC==1 && $numD==1){
                        //d=N 
                        if(!$exprD.getValue1().toLowerCase().equals("n")){
                            
                        }else{
                            //c=c*k or c+k
                            $unification = new Unification();
                            $formulas = new StringBuilder();
                            $formulas.append(op).append("(c,k)");
                            $formulas.append($exprC.getValue1());
                            $vars = new HashSet<>();
                            $vars.add("k");
                            $vars.add("c");
                            $unification.startUnification($formulas.toString(), $vars);
                            solution = parseSolution($unification.getSolution());
                            for(Pair<String,String> sol:solution){
                                if(!$valid)break;
                                if(sol.getValue0().equals("c")){
                                    if(sol.getValue1().equals($exprC.getValue0())){
                                        $valid = true;
                                        $solC = sol;
                                    }else{
                                        $valid = false;
                                        //System.out.println("valid8 false");
                                    }
                                    if($valid){
                                        for(Pair<String,String> e:$wh.exprs){
                                            if(e.getValue0().equals($solC.getValue0())){
                                                $valid = false;
                                               // System.out.println("valid11 false");
                                            }
                                        }
                                    }
                                }else if(sol.getValue0().equals("k")){
                                    try{
                                        $k1 = Integer.parseInt(sol.getValue1());
                                        if($k1>1){$valid = true;}
                                        else{
                                            $valid = false;
                                           // System.out.println("valid9 false");
                                        }
                                    }
                                    catch(Exception ex){
                                        $valid = false;
                                       // System.out.println("valid10 false");
                                    }
                                }
                            }
                            if(op.equals("mul") || op.equals("divF")){
                                expSum = true;
                            }else{
                                expSum = false;
                            }
                        }
                        if(!$valid){
                            //c=number
                            if(!NumberUtils.isCreatable($exprC.getValue1())){
                                
                                //c=c*k or c+k
                                $unification = new Unification();
                                $formulas = new StringBuilder();
                                $formulas.append(op).append("(c,k)");
                                $formulas.append($exprC.getValue1());
                                $vars = new HashSet<>();
                                $vars.add("k");
                                $vars.add("c");
                                $unification.startUnification($formulas.toString(), $vars);
                                solution = parseSolution($unification.getSolution());
                                for(Pair<String,String> sol:solution){
                                    if(!$valid)break;
                                    if(sol.getValue0().equals("c")){
                                        if(sol.getValue1().equals($exprC.getValue0())){
                                            $valid = true;
                                            $solC = sol;
                                        }else{
                                            $valid = false;
                                           // System.out.println("valid20 false");
                                        }
                                        if($valid){
                                            for(Pair<String,String> e:$wh.exprs){
                                                if(e.getValue0().equals($solC.getValue0())){
                                                    $valid = false;
                                                  //  System.out.println("valid22 false");
                                                }
                                            }
                                        }
                                    }else if(sol.getValue0().equals("k")){
                                        try{
                                            $k1 = Integer.parseInt(sol.getValue1());
                                            if($k1>1){$valid = true;}
                                            else{
                                                $valid = false;
                                              //  System.out.println("valid23 false");
                                            }
                                        }
                                        catch(NumberFormatException ex){
                                            $valid = false;
                                            //System.out.println("valid24 false");
                                        }
                                    }
                                }
                                if(op.equals("mul") || op.equals("divF")){
                                    expSum = true;
                                }else{
                                    expSum = false;
                                }

                            }
                            else{
                                // d=d/k or d-k
                               $unification = new Unification();
                               $formulas = new StringBuilder();
                               $formulas.append(opCompl).append("(d,k)");
                               $formulas.append($exprD.getValue1());
                               $vars = new HashSet<>();
                               $vars.add("k");
                               $vars.add("d");
                               $valid = true;
                               $unification.startUnification($formulas.toString(), $vars);
                               solution = parseSolution($unification.getSolution());
                               for(Pair<String,String> sol:solution){
                                   if(!$valid)break;
                                   if(sol.getValue0().equals("d")){
                                       if(sol.getValue1().equals($exprD.getValue0())){
                                           $valid = true;
                                           $solD = sol;
                                       }else{
                                           $valid = false;
                                          // System.out.println("valid12 false");
                                       }
                                       if($valid){
                                           for(Pair<String,String> e:$wh.exprs){
                                               if(!e.getValue0().equals($solD.getValue0())){
                                                   $valid = false;
                                                 //  System.out.println("valid13 false");
                                               }
                                           }
                                       }
                                   }else if(sol.getValue0().equals("k")){
                                       try{
                                           $k1 = Integer.parseInt(sol.getValue1());
                                           if($k1>1){$valid = true;}
                                           else{
                                               $valid = false;
                                              // System.out.println("valid14 false");
                                           }
                                       }
                                       catch(Exception ex){
                                           $valid = false;
                                           //System.out.println("valid15 false");
                                       }
                                   }
                               }
                            expSum = true;
                            }
                        }
                    }else{
                        $valid = false;
                       // System.out.println("valid21 false");
                    }

                    if($valid){
                        if($numC==1 && $numD==0){
                            $initC = true;
                        }else if($numC==0 && $numD==1){
                            $initD = true;
                        }else if($numC==1 && $numD==1){
                            $initC = true;
                            $initD = true;
                        }
                    }

                }catch(Exception ex){
                    $valid = false;
                    //System.out.println("valid16 false");
                    ex.printStackTrace();
                }
                if($valid)break;
            }
        }
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