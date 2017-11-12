package com.it.jchess.pieces;

import com.it.jchess.Piece;
import com.it.jchess.ui.Tile;

import java.util.ArrayList;

public class Rook extends Piece {

    public Rook(String id, String path, int color) {
        setId(id);
        setPath(path);
        setColor(color);
    }

    @Override
    public ArrayList<Tile> movePiece(Tile[][] pos, int locX, int locY) {
        possibleMoves.clear();
        for(int i=locX-1;i>=0;i--)
        {
            if (pos[i][locY].getPiece()==null)
                possibleMoves.add(pos[i][locY]);
            else
            {
                if(pos[i][locY].getPiece().color != this.color)
                    possibleMoves.add(pos[i][locY]);
                break;
            }
        }

        for(int i=locX+1;i<=7;i++)
        {
            if (pos[i][locY].getPiece() == null)
                possibleMoves.add(pos[i][locY]);
            else
            {
                if(pos[i][locY].getPiece().color != this.color)
                    possibleMoves.add(pos[i][locY]);
                break;
            }
        }

        for(int j=locY-1;j>=0;j--)
        {
            if(pos[locX][j].getPiece()==null)
                possibleMoves.add(pos[locX][j]);
            else
            {
                if(pos[locX][j].getPiece().color != this.color)
                    possibleMoves.add(pos[locX][j]);
                break;
            }
        }

        for(int j=locY+1;j<8;j++)
        {
            if(pos[locX][j].getPiece()==null)
                possibleMoves.add(pos[locX][j]);
            else
            {
                if(pos[locX][j].getPiece().color != this.color)
                    possibleMoves.add(pos[locX][j]);
                break;
            }
        }


        return possibleMoves;
    }
}