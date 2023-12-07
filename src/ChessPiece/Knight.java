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
        possibleMoves.clear(); // Clear the existing list

        int currentX = getPosX();
        int currentY = getPosY();
        if (currentX + 1 < 8 && currentY + 2 < 8) possibleMoves.add(new ChessPosition(currentX + 1, currentY + 2));
        if (currentX + 1 < 8 && currentY - 2 >= 0) possibleMoves.add(new ChessPosition(currentX + 1, currentY - 2));
        if (currentX - 1 >= 0 && currentY + 2 < 8) possibleMoves.add(new ChessPosition(currentX - 1, currentY + 2));
        if (currentX - 1 >= 0 && currentY - 2 >= 0) possibleMoves.add(new ChessPosition(currentX - 1, currentY - 2));

        if (currentY + 1 < 8 && currentX + 2 < 8) possibleMoves.add(new ChessPosition(currentX + 2, currentY + 1));
        if (currentY - 1 < 8 && currentX + 2 >= 0) possibleMoves.add(new ChessPosition(currentX + 2, currentY - 1));
        if (currentY + 1 >= 0 && currentX - 2 < 8) possibleMoves.add(new ChessPosition(currentX - 2, currentY + 1));
        if (currentY - 1 >= 0 && currentX - 2 >= 0) possibleMoves.add(new ChessPosition(currentX - 2, currentY - 1));
    }
}
