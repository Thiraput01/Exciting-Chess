package ChessPiece;

import ChessBoard.Board;
import ChessBoard.Square;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public abstract class ChessPiece extends ImageView implements Movable {
    private int posX;
    private int posY;
    private double rate;
    private String pieceUrl;
    private boolean whiteTeam; //white is true black is false

    public ChessPiece(int x, int y, boolean t) {
        setTeam(t);
        posX = x;
        posY = y;
        addEventHandler();
    }


    public boolean isWhite() {
        return whiteTeam;
    }


    public void setTeam(boolean team) {
        this.whiteTeam = team;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getPieceUrl() {
        return pieceUrl;
    }

    public void setPieceUrl(String pieceUrl) {
        this.pieceUrl = pieceUrl;
    }

    private void addEventHandler(){
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                getAllPossibleMoves();
            }
        });
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosY() {
        return posY;
    }

    public String toString() {
        if (isWhite()) {
            if (this instanceof Pawn) {
                return "White Pawn at " + getChessNotation(posX, posY);
            } else if (this instanceof Knight) {
                return "White Knight at " + getChessNotation(posX, posY);
            } else if (this instanceof Rook) {
                return "White Rook at " + getChessNotation(posX, posY);
            } else if (this instanceof Queen) {
                return "White Queen at " + getChessNotation(posX, posY);
            } else if (this instanceof King) {
                return "White King at " + getChessNotation(posX, posY);
            }
        } else {
            if (this instanceof Pawn) {
                return "Black Pawn at " + getChessNotation(posX, posY);
            } else if (this instanceof Knight) {
                return "Black Knight at " + getChessNotation(posX, posY);
            } else if (this instanceof Rook) {
                return "Black Rook at " + getChessNotation(posX, posY);
            } else if (this instanceof Queen) {
                return "Black Queen at " + getChessNotation(posX, posY);
            } else if (this instanceof King) {
                return "Black King at " + getChessNotation(posX, posY);
            }
        }
        return "Invalid";
    }

    private String getChessNotation(int x, int y) {
        char file = (char) ('A' + x - 1);
        char rank = (char) ('1' + y - 1);
        return String.valueOf(file) + rank;
    }

    public abstract void getAllPossibleMoves();


    public abstract void capture(int x, int y);

    public abstract void handleCapture(Square destination, int toX, int toY);

    public void attack()
}

