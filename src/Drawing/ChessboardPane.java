package Drawing;

import ChessPiece.ChessPiece;
import GameLogic.GameLogic;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import sharedObject.RenderableHolder;


public class ChessboardPane extends Canvas {

    private static final int CELL_SIZE = 90;
    private static final int OFFSET = 15;
    private static final int PIECE_SIZE = 60;

    public ChessboardPane() {
        super(720, 720);
        initialize();
    }

    public void drawRectBrown(GraphicsContext gc, int posX, int posY) {
        gc.setFill(Color.TAN);
        gc.fillRect(posX, posY, CELL_SIZE, CELL_SIZE);
    }

    public void drawRectLightBrown(GraphicsContext gc, int posX, int posY) {
        gc.setFill(Color.BEIGE);
        gc.fillRect(posX, posY, CELL_SIZE, CELL_SIZE);
    }

    public int changeToposX(int mouseX) {
        return mouseX / CELL_SIZE;
    }

    public int changeToposY(int mouseY) {
        return mouseY / CELL_SIZE;
    }

    public void initialize() {
        updateBoard(GameLogic.getInstance());
    }

    public Image getPieceImage(ChessPiece piece) {
        Image pieceImage;
        String pieceType = piece.getPieceType();
        if (piece.isWhite()) {
            pieceImage = getImageForWhitePiece(pieceType);
        } else {
            pieceImage = getImageForBlackPiece(pieceType);
        }
        return pieceImage;
    }

    private Image getImageForWhitePiece(String pieceType) {
        return switch (pieceType) {
            case "Pawn" -> RenderableHolder.wPawn;
            case "Knight" -> RenderableHolder.wKnight;
            case "Bishop" -> RenderableHolder.wBishop;
            case "Rook" -> RenderableHolder.wRook;
            case "Queen" -> RenderableHolder.wQueen;
            case "King" -> RenderableHolder.wKing;
            default -> null;
        };
    }

    private Image getImageForBlackPiece(String pieceType) {
        return switch (pieceType) {
            case "Pawn" -> RenderableHolder.bPawn;
            case "Knight" -> RenderableHolder.bKnight;
            case "Bishop" -> RenderableHolder.bBishop;
            case "Rook" -> RenderableHolder.bRook;
            case "Queen" -> RenderableHolder.bQueen;
            case "King" -> RenderableHolder.bKing;
            default -> null;
        };
    }

    public void updateBoard(GameLogic gameLogic) {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight()); // Clear the canvas

        // Draw the chessboard squares
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int posX = i * CELL_SIZE;
                int posY = j * CELL_SIZE;
                if (i % 2 == 0) {
                    drawRectLightBrown(gc, posX, posY);
                } else {
                    drawRectBrown(gc, posX, posY);
                }
            }
        }
        // Draw the chess pieces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = gameLogic.getChessPieceAt(i, j);
                if (piece != null) {
                    setImageAt(piece, i, j);
                }
            }
        }
    }

    public void setImageAt(ChessPiece piece, int posX, int posY) {
        int x = posX * CELL_SIZE + OFFSET;
        int y = posY * CELL_SIZE + OFFSET;
        GraphicsContext gc = getGraphicsContext2D();
        Image pieceImage = getPieceImage(piece);
        gc.drawImage(pieceImage, x, y, PIECE_SIZE, PIECE_SIZE);
    }

}
