package ChessBoard;

import ChessPiece.ChessPiece;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.nio.channels.Pipe;
import java.util.ArrayList;

public class Board {
    GridPane chessBoard;

    public ArrayList<Square> squares = new ArrayList<>();

    public Board(GridPane chessBoard) {
        this.chessBoard = chessBoard;
    }

    private void InitializeBoard(GridPane chessBoard) {
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

    private void addPiece(Square square, ChessPiece chessPiece){
        square.getChildren().add(chessPiece);
    }
}
