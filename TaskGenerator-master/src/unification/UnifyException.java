/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unification;

import org.antlr.v4.runtime.RecognitionException;

/**
 *
 * @author djordje
 */
public class UnifyException extends Exception {

    public static final int OK = 0;
    public static final int CYCLE = 1;
    public static final int CLASH = 2;
    public static final int PARSE_ERROR = 3;
    public static final int HANGING_VAR = 4;
    public static final int IMPROPER_VAR_DECL = 5;
    private int exceptionCode;
    private String symb1, symb2;

    public UnifyException(int code) {
        exceptionCode = code;
    }

    public UnifyException(int code, String symb1) {
        exceptionCode = code;
        this.symb1 = symb1;
    }

    public UnifyException(int code, String symb1, String symb2) {
        exceptionCode = code;
        this.symb1 = symb1;
        this.symb2 = symb2;
    }

    @Override
    public String getMessage() {
        switch (exceptionCode) {
            case OK:
                return "OK";
            case CLASH:
                return "CLASH between '" + symb1 + "' and '" + symb2 + "'";
            case CYCLE:
                return "CYCLE";
            case PARSE_ERROR:
                return "PARSE ERROR";
            case HANGING_VAR:
                return "Variable " + symb1 + " is hanging";
            case IMPROPER_VAR_DECL:
                return "Variables are improperly declared";
            default:
                return "UNKNOWN ERROR CODE";
        }
    }
}
