package ChessPiece;

import javax.swing.text.Position;

public class ChessPosition {
    private int x;
    private int y;

    public ChessPosition(int x, int y) {
        setX(x);
        setY(y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equal(ChessPosition other) {
        return other.getX() == getX() && other.getY() == getY();
    }

    public ChessPosition addPositionValue(ChessPosition addedPosition) {
        int resultX = addedPosition.getX() + this.getX();
        int resultY = addedPosition.getY() + this.getY();
        ChessPosition result = new ChessPosition(resultX, resultY);
        result.normalizedPosition();
        return result;
    }

    public void reverse() {
        this.x = this.x * -1;
        this.y = this.y * -1;
    }

    public void normalizedPosition() {
        if (this.x > 0) this.x = x % 8;
        if (this.y > 0) this.y = y % 8;
        if (this.x < 0) this.x += 8;
        if (this.y < 0) this.y += 8;

    }


}
