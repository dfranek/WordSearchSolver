/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dfranek.wordsearch;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danfr_000
 */
public class WordThread implements Runnable {

    private final String word;
    private final WordSearchSolver solver;
    private final char[][] grid;

    private final List<Position> coords = new ArrayList<>();

    public WordThread(String word, WordSearchSolver solver) {
        this.word = word;
        this.solver = solver;
        grid = solver.getLetterGrid();
    }

    @Override
    public void run() {
        findStartLetter(word.charAt(0));
        solver.addCoords(word, coords);
    }

    private void findStartLetter(char startChar) {
        for (int i = 0; i < grid.length; i++) {
            char[] row = grid[i];
            for (int k = 0; k < row.length; k++) {
                char gridChar = row[k];
                if (gridChar == startChar) {
                    for (Offsets offset : Offsets.values()) {
                        coords.clear();
                        coords.add(new Position(i, k));
                        if (findNextLetter(word.charAt(1), coords.get(0), 1, offset)) {
                            return;
                        }
                    }
                }
            }
        }
    }

    private boolean findNextLetter(char c, Position p, int pos, Offsets offset) {
        int newX = p.getX() + offset.getX();
        int newY = p.getY() + offset.getY();
        if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[newX].length) {
            if (grid[newX][newY] == c) {
                Position currentCoords = new Position(newX, newY);
                coords.add(currentCoords);
                if (pos + 1 < word.length()) {
                    return findNextLetter(word.charAt(pos + 1), currentCoords, pos + 1, offset);
                } else {
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    private static enum Offsets {

        DIAGONAL_LEFT_UP(-1, -1),
        UP(-1, 0),
        DIAGONAL_RIGHT_UP(-1, 1),
        LEFT(0, -1),
        RIGHT(0, 1),
        DIAGONAL_LEFT_DOWN(1, -1),
        DOWN(1, 0),
        DIAGONAL_RIGHT_DOWN(1, 1);

        private final int x;
        private final int y;

        private Offsets(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * @return the x
         */
        public int getX() {
            return x;
        }

        /**
         * @return the y
         */
        public int getY() {
            return y;
        }

    }
}
