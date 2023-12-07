package ChessPiece;

import GameLogic.GameInstance;

import java.util.ArrayList;

public class King extends ChessPiece implements Movable {

    public King(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
        setRate(1.6);
        setPieceUrl(getImageURL(isWhite));
        possibleMoves = new ArrayList<>();
    }

    private String getImageURL(boolean isWhite) {
        return isWhite ? "wKing.png" : "bKing.png";
    }

    @Override
    public boolean isValidMove(int toX, int toY) {
        int dX = Math.abs(toX - getPosX());
        int dY = Math.abs(toY - getPosY());
        return (dX == 1 || dY == 1);
    }


    /*@Override
    public void setCurrentAllPossibleMoves() {
        possibleMoves.clear(); // Clear the existing list

        int currentX = getPosX();
        int currentY = getPosY();
        Board chessBoard = Board.getInstance();

        int direction = isWhite() ? 1 : -1; // Adjust the direction based on the color

        // Iterate through neighboring squares and add valid moves to the possibleMoves list
        for (int i = currentX - 1; i <= currentX + 1; i++) {
            for (int j = currentY - direction; j <= currentY + direction; j += direction) {
                if (isValidMove(i, j) && chessBoard.isValidPosition(i, j)) {
                    Square destination = chessBoard.getSquare(i, j);

                    if (destination.hasPiece() && destination.getPiece().isWhite() != isWhite()) {
                        handleCapture(destination, i, j);
                    } else if (!destination.hasPiece()) {
                        handleMove(destination, i, j);
                    }
                }
            }
        }
    }


    public void handleCapture(Square destination, int toX, int toY) {
        Random random = new Random();
        if (random.nextInt(100) < getRate()) {
            // Capture is successful
            destination.highlightRed();
            possibleMoves.add(new ChessPosition(toX, toY));
            capture(toX, toY);
        } else {
            // Capture failed, remove the current king
            Board.getInstance().getSquare(getPosX(), getPosY()).removePiece();
        }
    }

    private void handleMove(Square destination, int toX, int toY) {
        destination.highlightGreen();
        possibleMoves.add(new ChessPosition(toX, toY));
    }

    public void capture(int toX, int toY) {
        Board chessBoard = Board.getInstance();
        Square capturedSquare = chessBoard.getSquare(toX, toY);
        capturedSquare.removePiece();
    }*/

    @Override
    public void setCurrentAllPossibleMoves(){
        possibleMoves.clear();
        for(int i=0;i<8;i++){
            for (int e=0;e<8;e++){
                if (isValidMove(i,e) && (GameInstance.getInstance().getChessPieceAt(i,e)==null || (GameInstance.getInstance().getChessPieceAt(i,e)!=null && GameInstance.getInstance().getChessPieceAt(i,e).isWhite()!=isWhite()))) possibleMoves.add(new ChessPosition(i,e));
            }
        }
    }
}
