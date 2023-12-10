package ChessPiece;

import GameLogic.GameLogic;
import GameLogic.GameUtil;

import java.util.ArrayList;

public class Pawn extends ChessPiece implements Movable {
    public Pawn(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
        setRate(0.2);
        setPieceUrl(getImageURL(isWhite));
        possibleMoves = new ArrayList<>();
    }

    public boolean isValidMove(int toX, int toY) {
        if (!GameUtil.inRangeOfBoard(toX, toY)) return false;
        if (GameLogic.getInstance().getChessPieceAt(toX,toY)!=null && GameLogic.getInstance().getChessPieceAt(toX,toY).isWhite()==isWhite()) return false;
        int direction = isWhite() ? 1 : -1;
        if ((toX == getPosX() + 1 || toX == getPosX() - 1) && toY == getPosY() + direction
                && GameLogic.getInstance().getChessPieceAt(toX, toY) != null
                && GameLogic.getInstance().getChessPieceAt(toX, toY).isWhite() != isWhite())
            return true; // attacking diagonally upward
        return (GameLogic.getInstance().getChessPieceAt(toX, toY) == null) && (toX == getPosX()) && (toY == getPosY() + direction); //moving normally
    }

    private String getImageURL(boolean isWhite) {
        return isWhite ? "wPawn.png" : "bPawn.png";
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
