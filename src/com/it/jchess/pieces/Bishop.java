package com.it.jchess.pieces;

import com.it.jchess.Piece;
import com.it.jchess.ui.Tile;

import java.util.ArrayList;

public class Bishop extends Piece {

    public Bishop(String id, String path, int color) {
        setId(id);
        setPath(path);
        setColor(color);
    }

    public ArrayList<Tile> getRook(Tile[][] pos,int locX,int locY){

        Rook r =new Rook(getPath(),getId(),getColor());
        return r.movePiece(pos,locX,locY);
    }

    @Override
    public ArrayList<Tile> movePiece(Tile[][] pos, int locX, int locY) {
        possibleMoves.clear();

        for(int i=locX+1,j=locY+1;i<8 && j<8;i++,j++)
        {
            if(pos[i][j].getPiece()==null)
                possibleMoves.add(pos[i][j]);
            else
            {
                if(pos[i][j].getPiece().color != this.color)
                    possibleMoves.add(pos[i][j]);
                break;
            }
        }
        for(int i=locX-1,j=locY-1;i>=0 && j>=0;i--,j--)
        {
            if(pos[i][j].getPiece()==null)
                possibleMoves.add(pos[i][j]);
            else
            {
                if(pos[i][j].getPiece().color != this.color)
                    possibleMoves.add(pos[i][j]);
                break;
            }
        }
        for(int i =locX +1,j=locY-1;i<8 && j>=0;i++,j--)
        {
            if(pos[i][j].getPiece()==null)
                possibleMoves.add(pos[i][j]);
            else
            {
                if(pos[i][j].getPiece().color != this.color)
                    possibleMoves.add(pos[i][j]);
                break;
            }
        }
        for(int i=locX-1,j=locY+1;i>=0 && j<8;i--,j++)
        {
            if(pos[i][j].getPiece()==null)
                possibleMoves.add(pos[i][j]);
            else
            {
                if(pos[i][j].getPiece().color != this.color)
                    possibleMoves.add(pos[i][j]);
                break;
            }
        }
        return possibleMoves;
    }
}
