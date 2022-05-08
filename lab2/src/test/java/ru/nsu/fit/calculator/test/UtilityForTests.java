package ru.nsu.fit.calculator.test;

import java.util.List;

public class UtilityForTests {
    public static String correctValue1 = "5.553";
    public static String correctValue2 = "45";
    public static String correctNegativeValue = "-234.34";
    public static String key1 = "key1";
    public static String key2 = "key2";
    public static String correctFileName = "b.txt";
    public static String incorrectFileName = "/df";
    public static String fictiveCommand = "command";
    public static void removeAndAddArg(List<String> args, int idx, String value) {
        args.remove(idx);
        args.add(value);
    }
}
