/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author rtrk
 */
public class InvertCondition {

    public static String invertCondition(String condition) {
        switch (condition) {
            case "=":
                return "<>";
            case "<>":
                return "=";
            case ">=":
                return "<";
            case "<=":
                return ">";
            case ">":
                return "<=";
            case "<":
                return ">=";
            default:
                return null;

        }
    }
//for C lang

    public static String invertCCondition(String condition) {
        switch (condition) {
            case "==":
                return "!>";
            case "!=":
                return "==";
            case ">=":
                return "<";
            case "<=":
                return ">";
            case ">":
                return "<=";
            case "<":
                return ">=";
            default:
                return null;

        }
    }
}
