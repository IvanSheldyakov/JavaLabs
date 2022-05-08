package ru.nsu.fit.minesweeper.GameModel;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

public class ScoreTableForMinesweeper implements ScoreTable {

    private int rowsCount = 10;
    private int columnsCount = 3;
    private int filled;


    private ArrayList<ScoreTableRow> scoreTable;
    private final Path path = Paths.get("src/ru/nsu/fit/minesweeper/Resources/scoreTable.txt");

    public ScoreTableForMinesweeper() {
        generateDefaultScoreTable();
    }

    @Override
    public String[][] getAsStringsArray() {
        readScoreTable();
        String[][] table = new String[rowsCount][columnsCount];
        for (int i = 0; i < rowsCount; i++) {
            table[i] = scoreTable.get(i).toString().split(" ");
        }
        return table;
    }

    @Override
    public void updateScoreTable(ScoreTableRow row) {
        long newTime = Long.parseLong(row.timeInSeconds);
        long currentTime;
        for (int i = 0; i < filled; i++) {
            currentTime = Long.parseLong(scoreTable.get(i).timeInSeconds);
            if (newTime < currentTime) {
                row.idx = Integer.toString(i + 1);
                scoreTable.add(i, row);
                if (filled < rowsCount) {
                    filled++;
                }
            }
        }
        save();
    }


    private void save() {
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (int i = 0; i < rowsCount; i++) {
                writer.write(scoreTable.get(i).toString());
                writer.newLine();
            }
            writer.write(Integer.toString(filled));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateDefaultScoreTable() {
        scoreTable = new ArrayList<>();
        filled = 0;
        for (int i = 0; i < rowsCount; i++) {
            scoreTable.add(new ScoreTableRow(Integer.toString(i + 1), "-", "-"));
        }
    }

    private void readScoreTable() {
        File file = new File("minesweeper/src/ru/nsu/fit/minesweeper/Resources/scoreTable.txt");
        if (!file.exists()) {
            return;
        }
        String line;
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (count != rowsCount) {
                line = reader.readLine().trim();
                scoreTable.add(count, new ScoreTableRow(line.split(" ")));
                count++;
            }
            filled = Integer.parseInt(reader.readLine().trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
