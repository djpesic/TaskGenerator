/******************************************************
 * A multi-line Javadoc-like comment about my grammar *
 ******************************************************/
grammar MultiEqBuilder;

@header {
package unif;
import java.util.*;
import unification.*;
}
@parser::members{
    public Set<String> multiEqVars;
    private <T> List<List<T>> transpose(List<List<T>> matrix) {
        //all rows has the same length
        List<List<T>> result = new ArrayList<>();
        if(matrix.isEmpty())return result;
        int rowSize = matrix.get(0).size();
        for(int i=0;i<rowSize;i++){
            List<T> resultRow = new ArrayList<>();
            for(int j=0;j<matrix.size();j++){
                resultRow.add(matrix.get(j).get(i));
            }
            result.add(resultRow);
        }   
        return result;
    }

    private void processFunction(List<List<String>> transposedFormulas,
    StringBuilder multiEquationSystem) throws UnifyException {
        if (transposedFormulas.isEmpty()) {
            return;
        }
        if (transposedFormulas.get(0).isEmpty()) {
            return;
        }
        String funcName = transposedFormulas.get(0).get(0);
        for (int i = 1; i < transposedFormulas.get(0).size(); i++) {
            String name = transposedFormulas.get(0).get(i);
            if (!funcName.equals(name)) {
                throw new UnifyException(UnifyException.CLASH, funcName, name);
            }
        }
        multiEquationSystem.append(funcName).append("(");
        transposedFormulas.remove(0);
        for (int i = 0; i < argNumber(funcName); i++) {
            processArgument(transposedFormulas,multiEquationSystem);
        }
        deleteLastChar(multiEquationSystem, ',');
        multiEquationSystem.append(")");
    }

    private void deleteLastChar(StringBuilder s, char c) {
        if (s.charAt(s.length() - 1) == c) {
            s.deleteCharAt(s.length() - 1);
        }
    }

    private void processArgument(List<List<String>> transposedFormulas,
    StringBuilder multiEquationSystem) throws UnifyException {
        if (transposedFormulas.isEmpty()) {
            return;
        }
        List<String> arg = transposedFormulas.get(0);
        Set<String> vars = new HashSet<>();
        Set<String> funcs = new HashSet<>();
        String func = "";
        List<String> consts = new LinkedList<>();
        for (int i = 0; i < arg.size();) {
            String s = arg.get(i);
            if (multiEqVars.contains(s)) {
                vars.add(s);
            } else if (isFunction(s)) {
                funcs.add(s);
                func = s;
                if (funcs.size()>1) {
                    String s1 = (String)funcs.toArray()[0];
                    String s2 = (String)funcs.toArray()[1];
                    throw new UnifyException(UnifyException.CLASH, s1, s2);
                }
            } else {
                consts.add(s);
            }
            arg.remove(0);
        }
        transposedFormulas.remove(0);
        multiEquationSystem.append("<");
        if (!func.isEmpty()) {
            multiEquationSystem.append("{");
            vars.forEach((var) -> multiEquationSystem.append(var).append(","));
            consts.forEach((cnst) -> multiEquationSystem.append(cnst).append(","));
            deleteLastChar(multiEquationSystem, ',');
            multiEquationSystem.append("},");
            List<String> l = new ArrayList<>();
            l.add(func);
            transposedFormulas.add(0, l);
            processFunction(transposedFormulas,multiEquationSystem);
        } else {
            if (consts.size() > 1) {
                throw new UnifyException(UnifyException.IMPROPER_VAR_DECL);
            }
            multiEquationSystem.append("{");
            vars.forEach((var) -> multiEquationSystem.append(var).append(","));
            deleteLastChar(multiEquationSystem, ',');
            multiEquationSystem.append("},");
            consts.forEach((cnst) -> multiEquationSystem.append(cnst).append(","));
            if (!consts.isEmpty()) {
                deleteLastChar(multiEquationSystem, ',');
            }
        }

        multiEquationSystem.append(">,");
    }
    private boolean isFunction(String s) {
        switch (s) {
            case "add":
                return true;
            case "sub":
                return true;
            case "mul":
                return true;
            case "divI":
                return true;
            case "and":
                return true;
            case "or":
                return true;
            case "mod":
                return true;
            case "not":
                return true;
            case "divF":
                return true;
            case "index":
                return true;
            case "expr":
                return true;
            default:
                return false;
        }
    }
    private int argNumber(String s) {
        switch (s) {
            case "add":
                return 2;
            case "sub":
                return 2;
            case "mul":
                return 2;
            case "divI":
                return 2;
            case "and":
                return 2;
            case "or":
                return 2;
            case "mod":
                return 2;
            case "not":
                return 1;
            case "divF":
                return 2;
            case "index":
                return 1;
            case "expr":
                return 2;
            default:
                return 0;
        }
    }

    private void expand(List<String> l1, List<String> l2) throws UnifyException{
        List<String> longer, shorter;
        if(l1.size() > l2.size()){
            longer = l1;
            shorter = l2;
        }else{
            longer = l2;
            shorter = l1;
        }
        for(int i = 0; i < longer.size(); i++){
            String s1 = longer.get(i);
            String s2 = shorter.get(i);
            if(isFunction(s1) && isFunction(s2)){
                if(!s1.equals(s2)){
                    throw new UnifyException(UnifyException.CLASH, s1, s2);
                }
            }else if(!isFunction(s1) &&  !isFunction(s2)){
                //both vars, do nothing
            }else{
                int num;
                if(isFunction(s1)){
                    num = argNumber(s1);
                    for(int j = 0; j < num; j++){
                        shorter.add(i+1, "_");
                    }
                }
                else if(isFunction(s2)){
                    num = argNumber(s2);
                    for(int j = 0; j < num; j++){
                        longer.add(i+1, "_");
                    }
                }
            }
        }   
    }
}

// parser rules
sequence returns [StringBuilder multiEquationSystem] locals[int diff,
List<String> args,List<String> args1, List<List<String>> splitted,int maxLen,
List<String> l,List<List<String>> transposedSplitted, String s]:
    {$multiEquationSystem = new StringBuilder();}
    f1=formula
    {
        $args1 = $f1.args;
        $splitted = new ArrayList<>();
        $splitted.add($args1);
        $maxLen = $args1.size();
    }
    (
    f=formula
    {
        try{
            $args1 = $splitted.get($splitted.size()-1);
            $args = $f.args;
            $splitted.add($args);
            if($args.size()>$maxLen){
                $maxLen = $args.size();
                for(int i=0;i<$splitted.size();i++){
                    $l = $splitted.get(i);
                    expand($l, $args);
                }
            }else if($args.size()<$args1.size()){
                expand($args, $args1);
            }   

        }catch(UnifyException ex){
            throw new ParseCancellationException(ex);
        }
    }
    )*
    {
        $transposedSplitted = transpose($splitted);
        try{
            processFunction($transposedSplitted, $multiEquationSystem);
        }
        catch(UnifyException ex){
            throw new ParseCancellationException(ex);
        }
    }
    ;

formula returns [List<String> args] locals[String s]:
    v=variable
    LPAREN al=argList RPAREN
    {
        $args=$al.args;
        $args.add(0,$v.var);
    }
    ;


argList returns [List<String> args] :
    a=arg
    {
        $args = $a.argumentList;
    }
    (
    COMMA a1=arg
    {
        $args.addAll($a1.argumentList);
    }
    )*
    ;

arg returns [List<String> argumentList] locals[String s]: 
    f=formula
    {
        $argumentList = $f.args;
    }
    | v=variable
    {
        $argumentList = new ArrayList<>();
        $argumentList.add($v.var);
    }
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
LPAREN : '(';
RPAREN : ')';
COMMA : ',';
WHILE : 'while';
IDENT : ('a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_')+;