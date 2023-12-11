package ChessPiece;

import GameLogic.GameLogic;
import GameLogic.GameUtil;

import java.util.ArrayList;

public class Queen extends ChessPiece implements Movable {
    public Queen(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
        setRate(2);
        setPieceUrl(getImageURL(isWhite));
        possibleMoves = new ArrayList<>();
    }

    public boolean isValidMove(int toX, int toY) {
        if (!GameUtil.inRangeOfBoard(toX, toY)) return false;
        if (GameLogic.getInstance().getChessPieceAt(toX, toY) != null && GameLogic.getInstance().getChessPieceAt(toX, toY).isWhite() == isWhite())
            return false;
        if (toX == getPosX() && toY == getPosY()) return false;
        return toX == getPosX() || toY == getPosY() || Math.abs(toX - getPosX()) == Math.abs(toY - getPosY());
    }

    private String getImageURL(boolean isWhite) {
        return isWhite ? "wQueen.png" : "bQueen.png";
    }

    public void setCurrentAllPossibleMoves() {
        possibleMoves.clear();
        for (int i = 0; i < 8; i++) {
            for (int e = 0; e < 8; e++) {
                if (isValidMove(i, e) && GameUtil.isClearPath(getPosX(), getPosY(), i, e, this))
                    possibleMoves.add(new ChessPosition(i, e));
            }
        }
    }
}
