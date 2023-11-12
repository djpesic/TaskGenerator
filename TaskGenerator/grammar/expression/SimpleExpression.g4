
grammar SimpleExpression;
@header {
package expression;
import common.*;
import programSegment.*;
import java.util.*;
}

//grammar rules. Functions are not supported yet
simpleExpression returns[SimpleExprType simpleExprType] locals[SimpleExprType.AddopTerm addopTerm] : 
    termType=term 
    {
        $simpleExprType = new SimpleExprType();
        $simpleExprType.setTerm($termType.termType);
    }
    (
    op=addop termType1=term
    {
        $addopTerm = new SimpleExprType.AddopTerm();

        $addopTerm.setAddop($op.text);
        $addopTerm.setTerm($termType1.termType);
        $simpleExprType.getAddopTerm().add($addopTerm);
    }
    )*
    ;
addop : PLUS | MINUS | OR;
mulop : MUL | FLOAT_DIV | INT_DIV | MOD | AND;
term returns[TermType termType] locals[TermType.MulopSignedFactor mulopSignedFactor]:
    sgnFactor=signedFactor
    {
        $termType = new TermType();
        $termType.setSignedFactor($sgnFactor.sgnFactorType);
    }
    (
    op=mulop signedFactor1=signedFactor
    {
        $mulopSignedFactor = new TermType.MulopSignedFactor();
        $mulopSignedFactor.setMulop($op.text);
        $mulopSignedFactor.setSignedFactor($signedFactor1.sgnFactorType);
        $termType.getMulopSignedFactor().add($mulopSignedFactor);
    }
    )*
    ;

signedFactor returns [SignedFactorType sgnFactorType] :
    (pl=PLUS | mn=MINUS)? fact=factor
    {
        $sgnFactorType = new SignedFactorType();
        $sgnFactorType.setFactor($fact.factorType);
        if($pl!=null){
            $sgnFactorType.setSign($pl.text);
        }else if($mn!=null){
            $sgnFactorType.setSign($mn.text);
        }else{
            $sgnFactorType.setSign("");
        }
    }
    ;

factor returns [FactorType factorType] locals [FactorType.SubExpr subExprType]:
    var=variable 
    {
        $factorType = new FactorType();
        $factorType.setVar($var.varType);
    }
    | num=number
    {
        $factorType = new FactorType();
        $factorType.setConstant($num.num);
    }
    | str=string 
    {
        $factorType = new FactorType();
        $factorType.setConstant($str.str);
    }
    | NOT fact=factor  
    {
        $factorType = new FactorType();
        $factorType.setNotFactor($fact.factorType);
    }
    |
    '('
    subExpr=simpleExpression
    ')' 
    {
        $factorType = new FactorType();
        $subExprType = new FactorType.SubExpr();
        $subExprType.setLparen(new FactorType.SubExpr.Lparen());
        $subExprType.setRparen(new FactorType.SubExpr.Rparen());
        $subExprType.setSimpleExpr($subExpr.simpleExprType);
        $factorType.setSubExpr($subExprType);
    }
    ;

variable returns [FactorType.Var varType] :
    entVar=entireVar
    {
        $varType = new FactorType.Var();
        $varType.setName($entVar.entVarType);
    }
    | indVar=indexedVar
    {
        $varType = new FactorType.Var();
        $varType.setIndexed($indVar.indVarType);
    }
    ;

entireVar returns [String entVarType] : IDENT{$entVarType = $IDENT.text;}
    | OPERAND_STUB{$entVarType = $OPERAND_STUB.text;};

indexedVar returns [FactorType.Var.Indexed indVarType] locals [FactorType.Var.Indexed.CommaExpr commaExprType, String varName]:
    (ident=IDENT | opStub=OPERAND_STUB)
    '[' 
    expr=simpleExpression
    {
        if($ident!=null){$varName = $ident.text;}
        if($opStub!=null){$varName = $opStub.text;}
        $indVarType = new FactorType.Var.Indexed();
        $indVarType.setName($varName);
        $indVarType.setRbracket(new FactorType.Var.Indexed.Rbracket());
        $indVarType.setLbracket(new FactorType.Var.Indexed.Lbracket());
        $indVarType.setSimpleExpr($expr.simpleExprType);
    }
    ( 
    ',' expr1=simpleExpression 
    {
        $commaExprType = new FactorType.Var.Indexed.CommaExpr();
        $commaExprType.setComma(new FactorType.Var.Indexed.CommaExpr.Comma());
        $commaExprType.setSimpleExpr($expr1.simpleExprType);
        $indVarType.getCommaExpr().add($commaExprType);
    }
    )* 
    ']'
    ;
number returns[String num] : integ=INTEGER{$num=$integ.text;} | real=REAL{$num=$real.text;}
    | numStub=NUMBER_STUB{$num=$numStub.text;};

string returns[String str]: STRING_LITERAL{$str=$STRING_LITERAL.text;};

//lexer rules
WS
   : [ \t\r\n] -> skip
   ;
PLUS : '+';
MINUS: '-';
OR : 'or';
MUL : '*';
FLOAT_DIV : '/';
INT_DIV : 'div';
MOD : 'mod';
AND : 'and';
NOT : 'not';
REAL : INTEGER '.' INTEGER? SCALE | INTEGER SCALE;
SCALE : ('e' | 'E') (PLUS | MINUS) INTEGER;
IDENT : ('a' .. 'z' | 'A' .. 'Z') ('a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_')*;
STRING_LITERAL : '\'' ('\'\'' | ~ ('\''))* '\'';
INTEGER : ('0' .. '9')+;
OPERAND_STUB : '#';
NUMBER_STUB : '@';
