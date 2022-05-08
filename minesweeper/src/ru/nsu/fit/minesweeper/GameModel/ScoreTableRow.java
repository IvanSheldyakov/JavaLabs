package ru.nsu.fit.minesweeper.GameModel;

public class ScoreTableRow {

    public String playerName;
    public String timeInSeconds;
    public String idx;

    public ScoreTableRow(String idx, String playerName, String timeInSeconds) {
        this.idx = idx;
        this.playerName = playerName;
        this.timeInSeconds = timeInSeconds;
    }

    public ScoreTableRow(String playerName, String timeInSeconds) {
        this("0",playerName,timeInSeconds);
    }

    public ScoreTableRow(String[] row) throws IllegalArgumentException{
        if (row.length != 3) {
            throw new IllegalArgumentException("incorrect length of score table's row");
        }
        this.idx = row[0];
        this.playerName = row[1];
        this.timeInSeconds = row[2];
    }

   @Override
   public String toString() {
        return String.format("%s %s %s",idx,playerName,timeInSeconds);
   }
}
