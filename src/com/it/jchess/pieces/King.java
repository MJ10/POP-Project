package com.it.jchess.pieces;

import com.it.jchess.Piece;
import com.it.jchess.ui.Tile;

import java.util.ArrayList;

public class King extends Piece {

    private int kX, kY;

    public King(String id, String path, int color, int locKX, int locKY) {
        setId(id);
        setPath(path);
        setColor(color);

        setLocX(locKX);
        setLocY(locKY);
    }

    @Override
    public ArrayList<Tile> movePiece(Tile[][] pos, int locX, int locY) {
        possibleMoves.clear();
        if (locX > 0) {
            if (pos[locX - 1][locY].getPiece() == null)
                possibleMoves.add(pos[locX - 1][locY]);

            if (locY > 0) {
                if (pos[locX][locY - 1].getPiece() == null)
                    possibleMoves.add(pos[locX][locY - 1]);
                else {
                    if (pos[locX][locY - 1].getPiece().color != this.color)
                        possibleMoves.add(pos[locX][locY - 1]);
                }
                if (pos[locX - 1][locY - 1].getPiece() == null)
                    possibleMoves.add(pos[locX - 1][locY - 1]);
                else {
                    if (pos[locX - 1][locY - 1].getPiece().color != this.color)
                        possibleMoves.add(pos[locX - 1][locY - 1]);
                }
            }
            if (locY < 7) {
                if (pos[locX][locY + 1].getPiece() == null)
                    possibleMoves.add(pos[locX][locY + 1]);
                else {
                    if (pos[locX][locY + 1].getPiece().color != this.color)
                        possibleMoves.add(pos[locX][locY + 1]);
                }
                if (pos[locX - 1][locY + 1].getPiece() == null)
                    possibleMoves.add(pos[locX - 1][locY + 1]);
                else {
                    if (pos[locX - 1][locY + 1].getPiece().color != this.color)
                        possibleMoves.add(pos[locX - 1][locY + 1]);

                }
            }
            if (locX < 7) {
                if (pos[locX + 1][locY].getPiece() == null)
                    possibleMoves.add(pos[locX + 1][locY]);

                if (locY > 0) {
                    if (pos[locX][locY - 1].getPiece() == null)
                        possibleMoves.add(pos[locX][locY - 1]);
                    else {
                        if (pos[locX][locY - 1].getPiece().color != this.color)
                            possibleMoves.add(pos[locX][locY - 1]);
                    }
                    if (pos[locX + 1][locY - 1].getPiece() == null)
                        possibleMoves.add(pos[locX + 1][locY - 1]);
                    else {
                        if (pos[locX + 1][locY - 1].getPiece().color != this.color)
                            possibleMoves.add(pos[locX + 1][locY - 1]);
                    }
                }
                if (locY < 7) {
                    if (pos[locX][locY + 1].getPiece() == null)
                        possibleMoves.add(pos[locX][locY - 1]);
                    else {
                        if (pos[locX][locY + 1].getPiece().color != this.color)
                            possibleMoves.add(pos[locX][locY + 1]);
                    }
                    if (pos[locX + 1][locY + 1].getPiece() == null)
                        possibleMoves.add(pos[locX + 1][locY + 1]);
                    else {
                        if (pos[locX + 1][locY + 1].getPiece().color != this.color)
                            possibleMoves.add(pos[locX + 1][locY + 1]);
                    }
                }
            }
        }
            return possibleMoves;

    }


    public boolean isInDanger(Tile boardState[][]) {
        return false;
    }

    public int getLocY() {
        return kY;
    }

    public void setLocY(int kY) {
        this.kY = kY;
    }

    public int getLocX() {
        return kX;
    }

    public void setLocX(int kX) {
        this.kX = kX;
    }
}
