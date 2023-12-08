package ChessPiece;

import GameLogic.GameUtil;

import java.util.ArrayList;

public class Knight extends ChessPiece implements Movable {

    public Knight(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
        setRate(0.7);
        setPieceUrl(getImageURL(isWhite));
        possibleMoves = new ArrayList<>();
    }

    public boolean isValidMove(int toX, int toY) {
        if (!GameUtil.inRangeOfBoard(toX, toY)) return false;
        if (Math.abs(toX - getPosX()) == 1) {
            return Math.abs(toY - getPosY()) == 2;
        } else if (Math.abs(toX - getPosX()) == 2) {
            return Math.abs(toY - getPosY()) == 1;
        }
        return false;
    }

    private String getImageURL(boolean isWhite) {
        return isWhite ? "wKnight.png" : "bKnight.png";
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
