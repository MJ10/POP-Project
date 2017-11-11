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
        if(this.color==0){
            if (locY > 0) {
                if (pos[locX][locY - 1].getPiece() == null) {
                    possibleMoves.add(pos[locX][locY - 1]);
                }

                if (locX < 7) {
                    if (pos[locX + 1][locY - 1].getPiece() != null)
                        if (pos[locX + 1][locY - 1].getPiece().color == 1)
                            possibleMoves.add(pos[locX + 1][locY - 1]);
                }
                if (locX > 0) {
                    if (pos[locX - 1][locY - 1].getPiece() != null)
                        if (pos[locX + 1][locY - 1].getPiece().color == 1)
                            possibleMoves.add(pos[locX - 1][locY - 1]);
                }
            }
            if (locY == 6) {
                if (pos[locX][locY - 2].getPiece() == null) {
                    possibleMoves.add(pos[locX][locY - 2]);
                }
            }
            if (locY == 1) {

            }
        }
        else {
            if (locY < 7) {
                if (pos[locX][locY + 1].getPiece() == null) {
                    possibleMoves.add(pos[locX][locY + 1]);
                }
                if (locX < 7) {
                    if (pos[locX + 1][locY + 1].getPiece() != null)
                        if (pos[locX + 1][locY + 1].getPiece().color == 0)
                            possibleMoves.add(pos[locX + 1][locY + 1]);
                }
                if (locX > 0) {
                    if (pos[locX - 1][locY + 1].getPiece() != null)
                        if (pos[locX + 1][locY + 1].getPiece().color == 0)
                            possibleMoves.add(pos[locX - 1][locY + 1]);
                }
            }
            if (locY == 1) {
                if (pos[locX][locY + 2].getPiece() == null) {
                    possibleMoves.add(pos[locX][locY + 2]);
                }
            }
            if (locY == 6) {

            }


        }
        return possibleMoves;
    }
}
