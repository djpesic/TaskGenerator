/******************************************************
 * A multi-line Javadoc-like comment about my grammar *
 ******************************************************/
grammar NestingRules;

@header {
package dependency.rules.nesting;
import java.util.*;
import complexity.*;
import org.javatuples.Pair;
}

@parser::members{
    public Complexity compl1;
    public Complexity compl2;
}

// parser rules
formula returns [Complexity resultComplexity]:
    {
       // System.out.println("Starting NestingRules rule");
    }
    s=segmentSequence
    {
        if($s.valid == true){
            $resultComplexity = ComplexityOperations.multiply(compl1, compl2);
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

segmentSequence1 returns [List<Pair<String,String>> exprs]:
    s1=segment1
    {
        $exprs = new ArrayList<>();
        $exprs.addAll($s1.exprs);
    }
    (
    s2=segment1 { $exprs.addAll($s2.exprs);}
    )*
    ;

segment returns [boolean valid]:
    w=whileSegment1 { $valid = $w.valid;}
    | e=expression  { $valid = true;}
    | ifSeg=ifSegment { $valid = true;}
    ;

segment1 returns [List<Pair<String,String>> exprs] locals[Pair<String,String> p]:
    {$exprs = new ArrayList<>();}
    w=whileSegment { $exprs = $w.exprs;}
    | e=expression
    {
        $exprs = new ArrayList<>();
        Pair<String,String> p = new Pair($e.ls,$e.rs);
        $exprs.add(p);
    }
    | ifSeg=ifSegment {$exprs = $ifSeg.exprs;}
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


logicalFunction :
    v=variable
    (
    LPAREN al=argList RPAREN
    )?
    ;

whileSegment returns [String c, String d, List<Pair<String,String>> exprs]:
    WHILE LPAREN
    c1=leftVar
    COMMA d1=rightVar
    (COMMA s=segmentSequence1)?
    RPAREN
    {
        $c = $c1.var;
        $d = $d1.var;
        if($s.text!=null)
            $exprs = $s.exprs;
    }
    ;

whileSegment1 returns [boolean valid] 
    locals [List<Pair<String,String>> outerExprs,
    List<Pair<String,String>> changeLowerExprs,
    List<Pair<String,String>> changeUpperExprs]:

    {$valid = true;}
    WHILE LPAREN
    a=leftVar
    COMMA b=rightVar
    COMMA (exprs1=exprSequence)?
    inner=whileSegment
    (exprs2=exprSequence)?
    RPAREN
    {
        if($a.var.equals($inner.d) || $a.var.equals($inner.c)){
            $valid = false;
            //System.out.println("valid1 false");
        }else if($exprs1.text==null && $exprs2.text==null){
            $valid = false;
           // System.out.println("valid2 false");
        }else{
            $outerExprs = new ArrayList<>();

            if($exprs1.text!=null && $exprs1.exprs != null)
                $outerExprs.addAll($exprs1.exprs);

            if($exprs2.text!=null && $exprs1.exprs != null)
                $outerExprs.addAll($exprs2.exprs);
            // outer loop bounds not changed by inner loop
            if($inner.exprs!=null){
                for(Pair<String,String> e : $inner.exprs){
                    if(e.getValue0().equals($a.var) || e.getValue0().equals($b.var)){
                        $valid = false;
                      //  System.out.println("valid4 false");
                    }
                }
            }
            
            List<Pair<String,String>> changeLowerExprs = new ArrayList<>();
            List<Pair<String,String>> changeUpperExprs = new ArrayList<>();
            if($valid){
                // inner loop bounds not changed by outer loop
                for(Pair<String,String> e : $outerExprs){
                    if(e.getValue0().equals($inner.c)){
                        $valid = false;
                      //  System.out.println("valid3 false");
                        changeLowerExprs.add(e);
                    }
                    if(e.getValue0().equals($inner.d)){
                        $valid = false;
                       // System.out.println("valid5 false");
                        changeUpperExprs.add(e);
                    }
                }
            
                if(!$valid){
                    // c = const1 && d=const2, const1>0, const2>0, const1>const2
                    if(!changeLowerExprs.isEmpty() && !changeUpperExprs.isEmpty()){
                        if((changeLowerExprs.size()==1) && (changeLowerExprs.size()==1)){
                            try{
                                double const1 = Double.parseDouble(changeLowerExprs.get(0).getValue1());
                                double const2 = Double.parseDouble(changeUpperExprs.get(0).getValue1());
                                if(const1>0 && const2>0 && const1>const2)
                                {
                                    $valid=true;
                                }
                                else{
                                    $valid = false;
                                   // System.out.println("valid8 false");
                                }
                            }
                            catch(Exception ex){
                                $valid = false;
                               // System.out.println("valid7 false");
                            }
                        }
                    }else{
                        $valid = false;
                      //  System.out.println("valid6 false");
                    }
                }
                
            }
        }
    }
    ;

ifSegment returns [List<Pair<String,String>> exprs]:
   
    IF LPAREN
    cond=logicalFunction
    COMMA THEN LPAREN
    (
    seq=segmentSequence1  { $exprs = $seq.exprs; }
    )?
    RPAREN
    (
    COMMA ELSE LPAREN
    seq1=segmentSequence1 { $exprs.addAll($seq.exprs);}
    RPAREN
    )?
    RPAREN
    ;

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