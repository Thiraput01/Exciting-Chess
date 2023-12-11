package ChessPiece;

import GameLogic.GameLogic;
import GameLogic.GameUtil;

import java.util.ArrayList;

public class King extends ChessPiece implements Movable {

    public King(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
        setRate(0.2);
        setPieceUrl(getImageURL(isWhite));
        possibleMoves = new ArrayList<>();
    }

    private String getImageURL(boolean isWhite) {
        return isWhite ? "wKing.png" : "bKing.png";
    }

    @Override
    public boolean isValidMove(int toX, int toY) { //valid by general rules
        if (!GameUtil.inRangeOfBoard(toX, toY) || (toX == getPosX() && toY == getPosY())) return false;
        if (GameLogic.getInstance().getChessPieceAt(toX, toY) != null && GameLogic.getInstance().getChessPieceAt(toX, toY).isWhite() == isWhite())
            return false; //the position contains a same team piece
        int dX = Math.abs(toX - getPosX());
        int dY = Math.abs(toY - getPosY());
        return (dX <= 1 && dY <= 1);
    }

    @Override
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
