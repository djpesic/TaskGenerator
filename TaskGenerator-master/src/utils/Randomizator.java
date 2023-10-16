/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import rules.TemplateType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.naming.OperationNotSupportedException;

/**
 *
 * @author djordje
 */
public class Randomizator {

    //randomInt = rnd()*(end-start+1)+start
    private static Random random = new Random();
    private static List<Integer> iterNamesIndexes = new LinkedList<>();
    private static List<Integer> varNamesIndexes = new LinkedList<>();
    private static String[] iterNames = {
        "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u"
    };
    private static String[] varNames = {
        "a", "b", "c", "d", "e", "f", "g", "h", "v", "w", "x", "y", "z"
    };
    private static String[] ltConditions = {"<", "<="};
    private static String[] gtConditions = {">", ">="};
    private static String[] addops = {"+", "-", "or"};
    private static long toAppend = -1;

    private static List<String> bannedNames = new LinkedList<>();

    public static int getRandomInt(int start, int end) {
        int num = (int) (random.nextDouble() * (end - start + 1) + start);
        return num;
    }

    public static boolean getRandomBoolean() {
        int num = getRandomInt(0, 1);
        return num == 0;
    }

    public static void main(String[] args) {
        System.out.println(getRandomInt(0, 2));
    }

    public static void banNames(List<String> bannedNames) {
        Randomizator.bannedNames.addAll(bannedNames);
        List<String> list = Arrays.stream(varNames).filter((name) -> !bannedNames.contains(name)).collect(Collectors.toList());
        varNames = new String[list.size()];
        int i = 0;
        for (String name : list) {
            varNames[i++] = name;
        }
        list = Arrays.stream(iterNames).filter((name) -> !bannedNames.contains(name)).collect(Collectors.toList());
        i = 0;
        for (String name : list) {
            iterNames[i++] = name;
        }
    }

    public static void clearIndexes() {
        iterNamesIndexes.clear();
        varNamesIndexes.clear();
        toAppend = -1;
    }

    public static String getAddop() {
        int index = (int) (random.nextDouble() * addops.length);
        return addops[index];
    }

    public static String getLoopVarNum() {
        int num = (int) (random.nextDouble() * 2 + 1);
        return "" + num;
    }

    public static String getLoopDirection() {
        int num = (int) (random.nextDouble() * 2);
        if (num == 0) {
            return "to";
        }
        return "downto";
    }

    public static String getGtCondition() {
        int index = (int) (random.nextDouble() * 2);
        return gtConditions[index];
    }

    public static String getLtCondition() {
        int index = (int) (random.nextDouble() * 2);
        return ltConditions[index];
    }

    private static String getName(String[] names, List<Integer> indexes) {
        int index = (int) (random.nextDouble() * (names.length));
        if (indexes.size() == names.length) {
            //running out of names, append next number to name
            toAppend++;
            if (toAppend < 0) {
                throw new RuntimeException("Randomizator long range overflow not implemented");
                //all numbers in long scope are used. If this ever occurs, implement solution for this
            }
            indexes.clear();
        }
        while (indexes.contains(index)) {
            index = (int) (random.nextDouble() * (names.length));
        }
        indexes.add(index);
        if (toAppend >= 0) {
            return names[index] + toAppend;
        }
        return names[index];
    }

    public static String getIterName() {
        return getName(iterNames, iterNamesIndexes);
    }

    public static String getVarName() {
        return getName(varNames, varNamesIndexes);
    }

    public static List<Integer> getSegmentIndexes(int max) {
        int numToMultiply = (int) (random.nextDouble() * (max) + 1);
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < numToMultiply; i++) {
            int newIndex;
            do {
                newIndex = (int) (random.nextDouble() * (numToMultiply));
            } while (indexes.contains(newIndex));
            indexes.add(newIndex);
        }
        return indexes;
    }
}
