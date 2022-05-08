package ru.nsu.fit.minesweeper.GameModel;

import java.util.Objects;

public class Dot {
    public int x;
    public int y;
    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Dot(String coordinates) throws IllegalArgumentException {
        String[] coords = coordinates.split(" ");
        if (coords.length != 2) {
            throw new IllegalArgumentException("Incorrect format");
        }
        try {
            this.x = Integer.parseInt(coords[0]);
            this.y = Integer.parseInt(coords[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Dot() {
        x = 0;
        y = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dot dot = (Dot) o;
        return x == dot.x && y == dot.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
