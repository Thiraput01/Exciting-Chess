package ChessPiece;

public interface Movable {
    boolean isValidMove(int toX, int toY);

    public void move(int x, int y);
}
