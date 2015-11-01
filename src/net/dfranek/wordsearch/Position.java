/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dfranek.wordsearch;

/**
 *
 * @author danfr_000
 */
public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Position) {
            Position p = (Position) obj;
            return p.getX() == x && p.getY() == y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.x;
        hash = 79 * hash + this.y;
        return hash;
    }
    
}
