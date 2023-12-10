package ChessPiece;

import GameLogic.GameLogic;
import GameLogic.GameUtil;

import java.util.ArrayList;

public class Rook extends ChessPiece implements Movable {
    public Rook(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
        setRate(0.8);
        setPieceUrl(getImageURL(isWhite));
        possibleMoves = new ArrayList<>();
    }

    public boolean isValidMove(int toX, int toY) {
        if (!GameUtil.inRangeOfBoard(toX, toY)) return false;
        if (GameLogic.getInstance().getChessPieceAt(toX,toY)!=null && GameLogic.getInstance().getChessPieceAt(toX,toY).isWhite()==isWhite()) return false;
        return (!(toX == getPosX() && toY == getPosY())) && (toX == getPosX() || toY == getPosY());
    }

    private String getImageURL(boolean isWhite) {
        return isWhite ? "wRook.png" : "bRook.png";
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
