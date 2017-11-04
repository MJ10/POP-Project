package com.it.jchess.pieces;

import com.it.jchess.Piece;
import com.it.jchess.ui.Tile;

import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(String id, String path, int color) {
        setId(id);
        setPath(path);
        setColor(color);
    }

    @Override
    public ArrayList<Tile> movePiece(Tile[][] pos, int locX, int locY) {
        return null;
    }
}
