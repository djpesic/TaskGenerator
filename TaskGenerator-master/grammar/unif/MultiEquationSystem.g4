/******************************************************
 * A multi-line Javadoc-like comment about my grammar *
 ******************************************************/
grammar MultiEquationSystem;
@header {
package unif;
import java.util.*;
import unification.*;
}
multiEquationSystem returns [MultiEqSystem multiEqSystem]:
    {
        $multiEqSystem = new MultiEqSystem();
        $multiEqSystem.u = new UPart();
        $multiEqSystem.u.ZeroCounterMultiEq = new LinkedList<>();
        $multiEqSystem.u.Equations = new LinkedList<>();
        $multiEqSystem.t = new LinkedList<>();
    }
    (
    multeq=multiEquation
    {
        $multiEqSystem.u.Equations.add($multeq.multEq);
    }
    ';'
    )
    +
    ;

multiEquation returns[MultiEquation multEq]:
    v=vars 
    '=' 
    mTerm=multiTerm
    {
        $multEq = new MultiEquation();
        $multEq.s = $v.variables;
        $multEq.m = $mTerm.mTerm;
    }
    ;

vars returns [List<Variable> variables] locals[Variable var]:
    {$variables = new LinkedList<>();}
    '{' i1=IDENT
    {
        $var = new Variable();
        $var.varName = $i1.text;
        $variables.add($var);
        
    }
    (
    ',' i2=IDENT
    {
        $var = new Variable();
        $var.varName = $i2.text;
        $variables.add($var);
    }
    )
    *
    '}'
    | '{' '}' {$var = new Variable();}
    ;

multiTerm returns[MultiTerm mTerm]:
    {$mTerm = new MultiTerm();}
    name=variable
    {$mTerm.fSymb = $name.text;}
    (
    '('
    args=multiTermArgs
    {$mTerm.args = $args.mTermArgs;}
    ')'
    )*
    | /*empty*/
    ;

multiTermArgs returns [List<TempMultiEq> mTermArgs]:
    {$mTermArgs = new LinkedList<>();}
    arg1=multiTermArg
    {$mTermArgs.add($arg1.mTermArg);}
    (
    ','
    arg2=multiTermArg
    {$mTermArgs.add($arg2.mTermArg);}
    )
    *
    ;

multiTermArg returns[TempMultiEq mTermArg] locals[Variable tmpVar]:
    '<'
    v=vars
    ','
    mterm=multiTerm
    {
        $mTermArg = new TempMultiEq();
        $mTermArg.m = $mterm.mTerm;
        $mTermArg.s = $v.variables;
    }
    '>'
    |
    v1=variable
    {
        $mTermArg = new TempMultiEq();
        $mTermArg.s = new LinkedList<>();
        $tmpVar = new Variable();
        $tmpVar.varName = $v1.var;
        $mTermArg.s.add($tmpVar);
    }
    ;

variable returns [String var]: i=IDENT{$var = $i.text;} | num=number {$var = $num.num;};
    
number returns[String num] : integ=INTEGER{$num=$integ.text;} | real=REAL{$num=$real.text;} ;

//lexer rules
WS: [ \t\r\n] -> skip;
REAL : INTEGER '.' INTEGER? SCALE | INTEGER SCALE;
SCALE : ('e' | 'E') ('+' | '-') INTEGER;
IDENT : ('a' .. 'z' | 'A' .. 'Z') ('a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_')*;
INTEGER : ('0' .. '9')+ ;