package ChessBoard;

import ChessPiece.ChessPiece;
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


    public void highlightRed() {
        setStyle("-fx-background-color: red;");
    }

    public void highlightGreen() {
        setStyle("-fx-background-color: green;");
    }

    public void unhighlight() {
        setStyle(""); // Remove highlighting
    }

    public boolean hasPiece() {
        return !getChildren().isEmpty();
    }

    public ChessPiece getPiece() {
        if (hasPiece()) {
            return (ChessPiece) getChildren().get(0);
        } else {
            return null;
        }
    }

    public void removePiece() {
        getChildren().clear();
    }
}
