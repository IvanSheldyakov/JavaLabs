package ru.nsu.fit;


import java.io.IOException;
import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class WordsAnalyzer {

    private Map<String,Integer> wordsAndItsCount = new HashMap<>();
    private int totalCount = 0;

    public void readAndShowAnalyzedWordsInfo(String inputFile) throws IOException {
        read(inputFile);
        List<Map.Entry<String,Integer>> list = new ArrayList<>(wordsAndItsCount.entrySet());

        list.sort(new Comparator<Map.Entry<String,Integer>>() {
            @Override
            public int compare(Map.Entry<String,Integer> first, Map.Entry<String,Integer> second) {
                int dif = first.getValue() - second.getValue();
                if (dif > 0) {
                    return -1;
                } else if (dif < 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        for (Map.Entry<String,Integer> pair: list) {
            System.out.printf("%s %d %.2f\n", pair.getKey(), pair.getValue(), (float)pair.getValue() / totalCount);
        }
    }

    private void read(String inputFile) throws IOException {
        String regexOfWord = "[A-Za-z0-9]+";
        Pattern pattern = Pattern.compile(regexOfWord);

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            Matcher matcher = null;

            while ((line = reader.readLine()) != null) {
                matcher = pattern.matcher(line);

                while (matcher.find()) {
                    totalCount++;
                    String word = line.substring(matcher.start(),matcher.end());
                    if (wordsAndItsCount.containsKey(word)) {
                        wordsAndItsCount.put(word,wordsAndItsCount.get(word) + 1);
                    } else {
                        wordsAndItsCount.put(word,1);
                    }
                }
            }
        }
    }
}
