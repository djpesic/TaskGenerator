/******************************************************
 * A multi-line Javadoc-like comment about my grammar *
 ******************************************************/
grammar ExpressionFormula;

@header {
package formula;
import java.util.*;
}

// parser rules
formula returns [boolean valid]:
    s=segmentSequence { $valid = $s.valid;}
    ;

segmentSequence returns [boolean valid]:
    {$valid = true;}
    s1=segment { if(!($s1.valid)) $valid=false; }
    (
    s2=segment { if(!($s2.valid)) $valid=false; }
    )*
    ;

segment returns [boolean valid]:
    w=whileSegment { $valid = $w.valid;}
    | e=expression  { $valid = $e.valid;}
    | ifSeg=ifSegment { $valid = $ifSeg.valid;}
    ;

expression returns [boolean valid]:
    EXPR LPAREN 
    leftSide  
    COMMA 
    rs=rightSide { $valid = $rs.valid;}
    RPAREN
    ;
leftSide : variable;
rightSide returns [boolean valid] locals[boolean func]:
    {$func=false;}
    v=variable
    (
    LPAREN al=argList {$func=true;}RPAREN
    )?
    {
        
        if($func){
            if(!($al.valid)) $valid=false;
            else
            switch($v.text){
                case "add":
                case "sub":
                case "mul":
                case "divI":
                case "and":
                case "or":
                case "mod":
                case "not":
                case "divF":
                case "index":
                    $valid=true;
                    break;
                default: $valid = false; break;
            }
        }else {$valid = true;}
        
    }
    ;

logicalFunction returns [boolean valid] locals [boolean func] :
    {$func=false;}
    v=variable
    (
    LPAREN al=argList {$func=true;}RPAREN
    )?
    {
        
        if($func){
            if(!($al.valid)) $valid=false;
            else
            switch($v.text){
                case "gt":
                case "gte":
                case "lt":
                case "lte":
                case "neq":
                case "eq":
                    $valid=true;
                    break;
                default: $valid = false; break;
            }
        }else {$valid = true;}
        
    }
    ;

argList returns [boolean valid] :
    {$valid = true;}
    a=arg { if(!($a.valid))$valid=false;}
    (
    COMMA a1=arg { if(!($a1.valid))$valid=false;}
    )*
    ;

arg returns [boolean valid] : rs=rightSide {$valid=$rs.valid;};

whileSegment returns [boolean valid]:
    {$valid = true;}
    WHILE LPAREN
    leftVar
    COMMA rightVar
    (COMMA s=segmentSequence { $valid=$s.valid;})?
    RPAREN;

ifSegment returns [boolean valid]:
    {$valid = true;}
    IF LPAREN
    cond=logicalFunction { $valid = $valid && $cond.valid;}
    COMMA THEN LPAREN
    (
    seq=segmentSequence  { $valid = $valid && $seq.valid;}
    )?
    RPAREN
    (
    COMMA ELSE LPAREN
    seq1=segmentSequence { $valid = $valid && $seq1.valid;}
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