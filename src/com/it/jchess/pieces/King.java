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
        int posx[] = {locX, locX, locX + 1, locX + 1, locX + 1, locX - 1, locX - 1, locX - 1};
        int posy[] = {locY - 1, locY + 1, locY - 1, locY, locY + 1, locY - 1, locY, locY + 1};
        for (int i = 0; i < 8; i++)
            if ((posx[i] >= 0 && posx[i] < 8 && posy[i] >= 0 && posy[i] < 8))
                if ((pos[posx[i]][posy[i]].getPiece() == null || pos[posx[i]][posy[i]].getPiece().getColor() != this.getColor()))
                    possibleMoves.add(pos[posx[i]][posy[i]]);
        return possibleMoves;

    }


    public boolean isInDanger(Tile boardState[][]) {
        for (int i = kX + 1; i < 8; i++) {
            if (boardState[i][kY].getPiece() == null)
                continue;
            else if (boardState[i][kY].getPiece().getColor() == this.getColor())
                break;
            else {
                if ((boardState[i][kY].getPiece() instanceof Rook) || (boardState[i][kY].getPiece() instanceof Queen))
                    return true;
                else
                    break;
            }
        }
        for (int i = kX - 1; i >= 0; i--) {
            if (boardState[i][kY].getPiece() == null)
                continue;
            else if (boardState[i][kY].getPiece().getColor() == this.getColor())
                break;
            else {
                if ((boardState[i][kY].getPiece() instanceof Rook) || (boardState[i][kY].getPiece() instanceof Queen))
                    return true;
                else
                    break;
            }
        }
        for (int i = kY + 1; i < 8; i++) {
            if (boardState[kX][i].getPiece() == null)
                continue;
            else if (boardState[kX][i].getPiece().getColor() == this.getColor())
                break;
            else {
                if ((boardState[kX][i].getPiece() instanceof Rook) || (boardState[kX][i].getPiece() instanceof Queen))
                    return true;
                else
                    break;
            }
        }
        for (int i = kY - 1; i >= 0; i--) {
            if (boardState[kX][i].getPiece() == null)
                continue;
            else if (boardState[kX][i].getPiece().getColor() == this.getColor())
                break;
            else {
                if ((boardState[kX][i].getPiece() instanceof Rook) || (boardState[kX][i].getPiece() instanceof Queen))
                    return true;
                else
                    break;
            }
        }

        //checking for attack from diagonal direction
        int tempx = kX + 1, tempy = kY - 1;
        while (tempx < 8 && tempy >= 0) {
            if (boardState[tempx][tempy].getPiece() == null) {
                tempx++;
                tempy--;
            } else if (boardState[tempx][tempy].getPiece().getColor() == this.getColor())
                break;
            else {
                if (boardState[tempx][tempy].getPiece() instanceof Bishop || boardState[tempx][tempy].getPiece() instanceof Queen)
                    return true;
                else
                    break;
            }
        }
        tempx = kX - 1;
        tempy = kY + 1;
        while (tempx >= 0 && tempy < 8) {
            if (boardState[tempx][tempy].getPiece() == null) {
                tempx--;
                tempy++;
            } else if (boardState[tempx][tempy].getPiece().getColor() == this.getColor())
                break;
            else {
                if (boardState[tempx][tempy].getPiece() instanceof Bishop || boardState[tempx][tempy].getPiece() instanceof Queen)
                    return true;
                else
                    break;
            }
        }
        tempx = kX - 1;
        tempy = kY - 1;
        while (tempx >= 0 && tempy >= 0) {
            if (boardState[tempx][tempy].getPiece() == null) {
                tempx--;
                tempy--;
            } else if (boardState[tempx][tempy].getPiece().getColor() == this.getColor())
                break;
            else {
                if (boardState[tempx][tempy].getPiece() instanceof Bishop || boardState[tempx][tempy].getPiece() instanceof Queen)
                    return true;
                else
                    break;
            }
        }
        tempx = kX + 1;
        tempy = kY + 1;
        while (tempx < 8 && tempy < 8) {
            if (boardState[tempx][tempy].getPiece() == null) {
                tempx++;
                tempy++;
            } else if (boardState[tempx][tempy].getPiece().getColor() == this.getColor())
                break;
            else {
                if (boardState[tempx][tempy].getPiece() instanceof Bishop || boardState[tempx][tempy].getPiece() instanceof Queen)
                    return true;
                else
                    break;
            }
        }

        //Checking for attack from the Knight of opposite color
        int posx[] = {kX + 1, kX + 1, kX + 2, kX + 2, kX - 1, kX - 1, kX - 2, kX - 2};
        int posy[] = {kY - 2, kY + 2, kY - 1, kY + 1, kY - 2, kY + 2, kY - 1, kY + 1};
        for (int i = 0; i < 8; i++)
            if ((posx[i] >= 0 && posx[i] < 8 && posy[i] >= 0 && posy[i] < 8))
                if (boardState[posx[i]][posy[i]].getPiece() != null && boardState[posx[i]][posy[i]].getPiece().getColor() != this.getColor() && (boardState[posx[i]][posy[i]].getPiece() instanceof Knight)) {
                    return true;
                }


        //Checking for attack from the Pawn of opposite color
        int pox[] = {kX + 1, kX + 1, kX + 1, kX, kX, kX - 1, kX - 1, kX - 1};
        int poy[] = {kY - 1, kY + 1, kY, kY + 1, kY - 1, kY + 1, kY - 1, kY};
        {
            for (int i = 0; i < 8; i++)
                if ((pox[i] >= 0 && pox[i] < 8 && poy[i] >= 0 && poy[i] < 8))
                    if (boardState[pox[i]][poy[i]].getPiece() != null && boardState[pox[i]][poy[i]].getPiece().getColor() != this.getColor() && (boardState[pox[i]][poy[i]].getPiece() instanceof King)) {
                        return true;
                    }
        }
        if (getColor() == 0) {
            if (kX > 0 && kY > 0 && boardState[kX - 1][kY - 1].getPiece() != null && boardState[kX - 1][kY - 1].getPiece().getColor() == 1 && (boardState[kX - 1][kY - 1].getPiece() instanceof Pawn))
                return true;
            if (kX > 0 && kY < 7 && boardState[kX - 1][kY + 1].getPiece() != null && boardState[kX - 1][kY + 1].getPiece().getColor() == 1 && (boardState[kX - 1][kY + 1].getPiece() instanceof Pawn))
                return true;
        } else {
            if (kX < 7 && kY > 0 && boardState[kX + 1][kY - 1].getPiece() != null && boardState[kX + 1][kY - 1].getPiece().getColor() == 0 && (boardState[kX + 1][kY - 1].getPiece() instanceof Pawn))
                return true;
            if (kX < 7 && kY < 7 && boardState[kX + 1][kY + 1].getPiece() != null && boardState[kX + 1][kY + 1].getPiece().getColor() == 0 && (boardState[kX + 1][kY + 1].getPiece() instanceof Pawn))
                return true;
        }
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
