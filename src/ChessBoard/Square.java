package ChessBoard;

import javafx.scene.layout.StackPane;

import javax.swing.text.Position;

public class Square extends StackPane {
    private int x;
    private int y;

    public Square(int x, int y) {
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

    public boolean equal(Square other) {
        return other.getX() == getX() && other.getY() == getY();
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
