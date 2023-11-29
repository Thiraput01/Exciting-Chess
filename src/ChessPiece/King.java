package ChessPiece;

public class King extends ChessPiece implements Movable{

    public King(int x, int y, boolean isWhite) {
        super(x, y, isWhite);
        setWeight(1);
        setPieceUrl(getImageURL(isWhite));
    }

    private String getImageURL(boolean isWhite) {
        return isWhite ? "wKing.png" : "bKing.png";
    }

    @Override
    public boolean isValidMove(int toX, int toY) {
        int dX = Math.abs(toX - currentPosition.getX());
        int dY = Math.abs(toY - currentPosition.getY());
        return (dX == 1 || dY == 1) && (dX <= 1 && dY <= 1);
    }

    @Override
    public void move(int x, int y) {

    }
}
