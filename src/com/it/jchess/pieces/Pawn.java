package com.it.jchess.pieces;

import com.it.jchess.Piece;
import com.it.jchess.ui.Tile;

import java.util.ArrayList;
public class Pawn extends Piece {

    public Pawn(String id, String path, int color) {
        setId(id);
        setPath(path);
        setColor(color);
    }

    public ArrayList<Tile> movePiece(Tile[][] pos, int locX, int locY) {
        //If piece is white
        possibleMoves.clear();
        if (getColor() == 0) {
            if (locX == 0)
                return possibleMoves;
            if (pos[locX - 1][locY].getPiece() == null) {
                possibleMoves.add(pos[locX - 1][locY]);
                if (locX == 6) {
                    if (pos[4][locY].getPiece() == null)
                        possibleMoves.add(pos[4][locY]);
                }
            }
            if ((locY > 0) && (pos[locX - 1][locY - 1].getPiece() != null) && (pos[locX - 1][locY - 1].getPiece().getColor() != this.getColor()))
                possibleMoves.add(pos[locX - 1][locY - 1]);
            if ((locY < 7) && (pos[locX - 1][locY + 1].getPiece() != null) && (pos[locX - 1][locY + 1].getPiece().getColor() != this.getColor()))
                possibleMoves.add(pos[locX - 1][locY + 1]);
        } else {
            if (locX == 7)
                return possibleMoves;
            if (pos[locX + 1][locY].getPiece() == null) {
                possibleMoves.add(pos[locX + 1][locY]);
                if (locX == 1) {
                    if (pos[3][locY].getPiece() == null)
                        possibleMoves.add(pos[3][locY]);
                }
            }
            if ((locY > 0) && (pos[locX + 1][locY - 1].getPiece() != null) && (pos[locX + 1][locY - 1].getPiece().getColor() != this.getColor()))
                possibleMoves.add(pos[locX + 1][locY - 1]);
            if ((locY < 7) && (pos[locX + 1][locY + 1].getPiece() != null) && (pos[locX + 1][locY + 1].getPiece().getColor() != this.getColor()))
                possibleMoves.add(pos[locX + 1][locY + 1]);
        }
        return possibleMoves;
    }
}
