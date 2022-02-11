package ru.nsu.fit;


import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }
        WordsAnalyzer analyzer = new WordsAnalyzer();
        try {
            analyzer.readAndShowAnalyzedWordsInfo(args[0]);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
