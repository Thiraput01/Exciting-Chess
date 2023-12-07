package ChessPiece;

public interface Movable {
    public boolean isValidMove(int toX, int toY);

    public void move(int toX, int toY);

}
