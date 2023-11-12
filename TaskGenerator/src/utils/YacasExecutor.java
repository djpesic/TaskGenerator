/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import java.net.URISyntaxException;
import net.sf.yacas.YacasInterpreter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author djordje
 */
public class YacasExecutor {

    private static Map<Long, YacasInterpreter> interpreters;

    static {
        try {
            interpreters = new HashMap<>();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(simplify("Sqrt(2^n)*Sqrt(2^n)"));
    }

    private static YacasInterpreter getInterpreter(long threadId) {
        try {
            YacasInterpreter interpreter;
            if (interpreters.containsKey(threadId)) {
                interpreter = interpreters.get(threadId);
            } else {
                interpreter = new YacasInterpreter();
                interpreters.put(threadId, interpreter);
            }

            return interpreter;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String executeYacasCommand(String command) {
        YacasInterpreter interpreter = getInterpreter(Thread.currentThread().getId());

        return interpreter.Evaluate(command);

    }

    public static String simplify(String function) {
        String cmd = "Simplify(" + function + ");";
        YacasInterpreter interpreter = getInterpreter(Thread.currentThread().getId());
        return interpreter.Evaluate(cmd);
    }
    
    public static String div(String func1, String func2){
        String cmd = "("+func1+")"+"/"+"("+func2+")";
        return simplify(cmd);
    }
}
