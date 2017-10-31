package com.it.jchess;

import com.it.jchess.ui.Tile;

import java.util.ArrayList;

/**
 * This class is the abstract class inherited by all the piece classes.
 * This defines the behaviour of a piece
 */
public abstract class Piece implements Cloneable {

    private int color;
    private String id = null;
    private String path;
    protected ArrayList<Tile> possibleMoves = new ArrayList<>();

    /**
     * Return a list of possible moves
     *
     * @param pos:  Current state of board
     * @param locX: X location of piece
     * @param locY: y location of piece
     * @return list: Arraylist of possible positions to move
     */
    public abstract ArrayList<Tile> movePiece(Tile pos[][], int locX, int locY);


    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Piece getCopy() throws CloneNotSupportedException {
        return (Piece) this.clone();
    }

}
