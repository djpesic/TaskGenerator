/******************************************************
 * A multi-line Javadoc-like comment about my grammar *

Single variables are initially treated as linear complexities.
If this is not true, appropriate complexity function is created
 ******************************************************/
grammar ComplexityFunctions;
@header {
package complexity;
import java.util.*;
}

complexity returns [Complexity compl] :
    'const'
    {
        $compl = new Complexity(new ConstNode());
    }
    | func=complExpression
    {
        $compl = new Complexity($func.compl);
    }
    ;

complExpression returns [ComplexityNode compl]: 
    expr=expression
    {
        $compl = $expr.compl;
    }
    ;

expression returns [ComplexityNode compl]:
    expr1=multiplyingExpression
    {
        $compl = $expr1.compl;
    }
   ;

multiplyingExpression returns [ComplexityNode compl]
    locals[MulNode m, GroupNode left, GroupNode right]:
    expr1=powExpression
    {
        $compl = $expr1.compl;
    }
    (mul=TIMES expr2=powExpression
    {
       $left = new GroupNode($compl);
       $right = new GroupNode($expr2.compl);
       $m = new MulNode($left, $right);
       $compl = $m;
    }
    )*
    ;

powExpression returns[ComplexityNode compl] :
    a=atom 
    (pw=POW b=atom)?
    {
        //no exponents: left atom can be single var or subfunction
        if($pw==null){
            if($a.var!=null){
                $compl = new VarNode($a.text);
            }
            else if($a.compl!=null){
                $compl = $a.compl;
            }else{
                $compl = new NoneNode();
            }
        }else{
            // exponents
            // valid cases: number^var, var^number and var^(number/number)
            //              number^subfunc, subfunc^number and subfunc^(number/number)
            if($a.number!=0 && $b.var!=null){//number^var
                VarNode var = new VarNode($b.var);
                NumNode num = new NumNode($a.number);
                ExpNode exp = new ExpNode(num, var);
                $compl = exp;
            }else if($a.var!=null && $b.number!=0){//var^number
                VarNode var = new VarNode($a.var);
                NumNode num = new NumNode($b.number);
                ExpNode exp = new ExpNode(var, num);
                $compl = exp;
            }else if($a.var!=null && $b.fracNumber!=null){//var^(number/number)
                VarNode var = new VarNode($a.var);
                VarNode frac = new VarNode($b.fracNumber);
                ExpNode exp = new ExpNode(var, frac);
                $compl = exp;
            }else if($a.number!=0 && $b.compl!=null){//number^subfunc
                NumNode num = new NumNode($a.number);
                GroupNode g = new GroupNode($b.compl);
                ExpNode exp = new ExpNode(num, g);
                $compl = exp;
            }else if($a.compl!=null && $b.number!=0){//subfunc^number
                NumNode num = new NumNode($b.number);
                GroupNode g = new GroupNode($a.compl);
                ExpNode exp = new ExpNode(g, num);
                $compl = exp;
            }else if($a.compl!=null && $b.fracNumber!=null){//compl^(number/number)
                GroupNode g = new GroupNode($a.compl);
                VarNode var = new VarNode($b.fracNumber);
                ExpNode exp = new ExpNode(g, var);
                $compl = exp;
            }else{
                $compl = new NoneNode();
            }
        }
    }
    ;

atom returns [String var, int number, String fracNumber, ComplexityNode compl]: 
    var1=variable fact1='!'?
    {
        if($fact1!=null){
            VarNode v = new VarNode($var1.text);
            FactNode fact = new FactNode(v);
            $compl = fact;
        }else{
            $var = $var1.text;
        }
    }
    | '(' num1=number1 '/' num2=number1 ')'
    {
        $fracNumber = "("+$num1.text+"/"+$num2.text+")";
    }
    | num=number1
    {$number = Integer.parseInt($num.text);}
    | subfunc=subFunc fact2='!'?
    {
        if($fact2!=null){
            GroupNode g = new GroupNode($subfunc.compl);
            FactNode fact = new FactNode(g);
            $compl = fact;
        }else{
            $compl = $subfunc.compl;
        }
    }
    | logfunc=log fact3='!'?
    {
        if($fact3!=null){
            GroupNode g = new GroupNode($logfunc.logCompl);
            FactNode fact = new FactNode(g);
            $compl = fact;
        }else{
            $compl = $logfunc.logCompl;
        }
    }
    | sqrtfunc=sqrt fact3='!'?
    {
        if($fact3!=null){
            GroupNode g = new GroupNode($sqrtfunc.sqrtCompl);
            FactNode fact = new FactNode(g);
            $compl = fact;
        }else{
            $compl = $sqrtfunc.sqrtCompl;
        }
    }
    ;

log returns [ComplexityNode logCompl] :
    LOG num=number1? subfunc=subFunc
    {
        GroupNode g = new GroupNode($subfunc.compl);
        $logCompl = new LogNode(g);
    }
    ;
sqrt returns [ComplexityNode sqrtCompl] :
    SQRT subfunc=subFunc
    {
        GroupNode g = new GroupNode($subfunc.compl);
        $sqrtCompl = new SqrtNode(g);
    }
    ;
subFunc returns [ComplexityNode compl]: 
    LPAREN subfunc=complExpression RPAREN
    {
        $compl = $subfunc.compl;
    }
    ;

number1:
    DIGIT + ;

variable :
    LETTER (LETTER | DIGIT)*
    ;

LOG: 'log' ;

SQRT: 'Sqrt';

LPAREN : '(' ;

RPAREN: ')';

TIMES: '*' ;

POW : '^' ;

LETTER : ('a' .. 'z') | ('A' .. 'Z') ;

DIGIT : ('0' .. '9') ;

WS
   : [ \t\r\n] -> skip
   ;