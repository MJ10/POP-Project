package com.it.jchess.ui;

import com.it.jchess.Piece;

import javax.swing.*;
import java.awt.*;

/**
 * This class defines each of the 64 sites on the chess board
 */
public class Tile extends JPanel implements Cloneable {
    private boolean possibleDestination = false;
    private boolean isSelected = false;
    private boolean isCheck = false;
    private JLabel content;
    private Piece piece;
    public int locX, locY;

    /**
     * Returns a new Cell object
     *
     * @param locX:  X co-ordinate of the cell on the chess board.
     * @param locY:  Y co-ordinate of the cell on the chess board.
     * @param piece: Piece present on the cell
     */
    public Tile(int locY, int locX, Piece piece) {
        this.locX = locX;
        this.locY = locY;

        if (isBlackTile(locX, locY))
            setBackground(new Color(113, 198, 113));
        else
            setBackground(Color.white);

        if (piece != null)
            setPiece(piece);
    }

    /**
     * Returns a clone of the given tile
     *
     * @param tile: Tile to create a complete copy of.
     */
    public Tile(Tile tile) throws CloneNotSupportedException {
        this.locX = tile.locX;
        this.locY = tile.locY;
        if (isBlackTile(locX, locY)) {
            setBackground(new Color(113, 198, 113));
        } else
            setBackground(Color.white);

        if (tile.getPiece() != null) {
            setPiece(tile.getPiece().getCopy());
        } else
            this.piece = null;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        ImageIcon image = new ImageIcon(this.getClass().getResource(piece.getPath()));
        this.content = new JLabel(image);
        this.add(content);
    }

    public void removePiece() {
        piece = null;
        this.removeAll();
    }

    public Piece getPiece() {
        return piece;
    }

    public void select() {
        this.isSelected = true;
        setBorder(BorderFactory.createLineBorder(Color.red, 6));
    }

    public void deselect() {
        this.isSelected = false;
        setBorder(null);
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setPossibleDestination() {
        setBorder(BorderFactory.createLineBorder(Color.blue, 4));
        this.possibleDestination = true;
    }

    public void removePossibleDestination() {
        this.setBorder(null);
        this.possibleDestination = false;
    }

    public boolean getPossibleDestination() {
        return possibleDestination;
    }

    public void setCheck() {
        this.isCheck = true;
        setBackground(Color.red);
    }

    public void removeCheck() {
        setBorder(null);
        this.isCheck = false;

        if (isBlackTile(locX, locY)) {
            setBackground(new Color(113, 198, 113));
        } else {
            setBackground(Color.white);
        }
    }

    private boolean isBlackTile(int locX, int locY) {
        return (locX + locY) % 2 == 0;
    }

    public boolean isCheck() {
        return this.isCheck;
    }
}
