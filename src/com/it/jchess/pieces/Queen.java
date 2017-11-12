package com.it.jchess.pieces;

import com.it.jchess.ui.Tile;

import java.util.ArrayList;

public class Queen extends Bishop {

    public Queen(String id, String path, int color) {
        super(id,path,color);

    }

    @Override
    public ArrayList<Tile> movePiece(Tile[][] pos, int locX, int locY) {
        possibleMoves.clear();
        Bishop b= new Bishop(getPath(),getId(),color);
        possibleMoves.addAll(b.movePiece(pos, locX, locY));
        possibleMoves.addAll(b.getRook(pos,locX,locY));
        return possibleMoves;
    }
}
