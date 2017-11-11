package com.it.jchess.pieces;

import com.it.jchess.Piece;
import com.it.jchess.ui.Tile;

import java.util.ArrayList;

public class Knight extends Piece {

    public Knight(String id, String path, int color) {
        setId(id);
        setPath(path);
        setColor(color);
    }

    @Override
    public ArrayList<Tile> movePiece(Tile[][] pos, int x, int y) {
	possibleMoves.clear();
	//Each Possible Movement will be (posx[i],posy[i])
	int posx[]={x+1,x+1,x+2,x+2,x-1,x-1,x-2,x-2}; //Possible movements in x-direction of knight 
	int posy[]={y-2,y+2,y-1,y+1,y-2,y+2,y-1,y+1}; //Possible movements in y-direction of knight 
	for(int i=0;i<8;i++) //Now we will check if each move we just specified results in an (x,y) co-ordinate of chess board
		if((posx[i]>=0&&posx[i]<8&&posy[i]>=0&&posy[i]<8)) { 
		//Next,add position if there is no piece there or is a piece of opposite color
			if((pos[posx[i]][posy[i]].getPiece()==null||pos[posx[i]][posy[i]].getPiece().getColor()!=this.getColor()))
				{
					possibleMoves.add(pos[posx[i]][posy[i]]);
				}
			}
	return possibleMoves;
    }
}
