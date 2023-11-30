package ChessBoard;

import ChessPiece.ChessPiece;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Board {

    private static Board instance;
    private GridPane chessBoard;
    private ArrayList<Square> squares;

    private Board() {
        initializeBoard();
    }

    private void initializeBoard() {
        chessBoard = new GridPane();
        squares = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square square = new Square(i, j);
                square.setPrefSize(100, 100);
                square.setBorder(new Border(new BorderStroke(Color.BLACK,
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                chessBoard.add(square, i, j, 1, 1);
                squares.add(square);
            }
        }
    }

    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    public Square getSquare(int x, int y) {
        return squares.get(x + y * 8);
    }

    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}
