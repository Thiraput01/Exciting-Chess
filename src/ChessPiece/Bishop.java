package ChessPiece;

import GameLogic.GameUtil;

import java.util.ArrayList;

public class Bishop extends ChessPiece implements Movable {
    public Bishop(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
        setRate(0.75);
        setPieceUrl(getImageURL(isWhite));
        possibleMoves = new ArrayList<>();
    }

    public boolean isValidMove(int toX, int toY) {
        if (!GameUtil.inRangeOfBoard(toX, toY)) return false;
        return Math.abs(toX - getPosX()) != 0 && Math.abs(toX - getPosX()) == Math.abs(toY - getPosY());
    }

    private String getImageURL(boolean isWhite) {
        return isWhite ? "wBishop.png" : "bBishop.png";
    }

    public void setCurrentAllPossibleMoves() {
        possibleMoves.clear();
        for (int i = 0; i < 8; i++) {
            for (int e = 0; e < 8; e++) {
                if (isValidMove(i, e) && GameUtil.isClearPath(getPosX(),getPosY(),i,e,this))
                    possibleMoves.add(new ChessPosition(i, e));
            }
        }
    }
}
