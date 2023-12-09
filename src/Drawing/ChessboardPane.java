package Drawing;

import ChessPiece.*;
import GameLogic.GameLogic;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import sharedObject.RenderableHolder;

import java.util.ArrayList;


public class ChessboardPane extends Canvas {

    private static final int CELL_SIZE = 80;
    private static final int OFFSET = 10;
    private static final int PIECE_SIZE = 60;

    public ChessboardPane() {
        super(640, 640);
        initialize();
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                double mouseX = mouseEvent.getX();
                double mouseY = mouseEvent.getY();
                int posX = changeToposX(mouseX);
                int posY = changeToposY(mouseY);
                GameLogic gameInstance = GameLogic.getInstance();
                ChessPiece currentPiece = gameInstance.getChessPieceAt(posX, posY);


                if(!GameLogic.getInstance().isHighlighting()){
                    if (currentPiece != null) {
                        ArrayList<ChessPosition> allPossibleMoves = currentPiece.getPossibleMoves();
                        System.out.println(posX);
                        System.out.println(posY);
                        for (ChessPosition chessPosition : allPossibleMoves) {
                            highlightMoves(getGraphicsContext2D(), chessPosition.getX(), chessPosition.getY());
                        }
                    }
                    gameInstance.setHighlighting(true);
                    gameInstance.setCurrentClickingPiece(currentPiece);
                }else{
                    currentPiece = gameInstance.getCurrentClickingPiece();
                    currentPiece.move(posX, posY);
                    updateBoard(GameLogic.getInstance());

                    removePieceAt(currentPiece.getPosX(), currentPiece.getPosY());
                    setImageAt(currentPiece, posX, posY);

                    GameLogic.getInstance().setHighlighting(false);
                    gameInstance.setCurrentClickingPiece(null);
                }
            }
        });
    }

    public void drawRectBrown(GraphicsContext gc, int posX, int posY) {
        gc.setFill(Color.TAN);
        gc.fillRect(posX, posY, CELL_SIZE, CELL_SIZE);
    }

    public void drawRectLightBrown(GraphicsContext gc, int posX, int posY) {
        gc.setFill(Color.BEIGE);
        gc.fillRect(posX, posY, CELL_SIZE, CELL_SIZE);
    }

    public int changeToposX(double mouseX) {
        return (int) (mouseX / CELL_SIZE);
    }

    public int changeToposY(double mouseY) {
        return (int) (mouseY / CELL_SIZE);
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
                if ((i + j) % 2 == 0) {
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

    public void removePieceAt(int posX, int posY){
        getGraphicsContext2D().clearRect(posX, posY, PIECE_SIZE, PIECE_SIZE);
    }

    public void setImageAt(ChessPiece piece, int posX, int posY) {
        int x = posX * CELL_SIZE + OFFSET;
        int y = posY * CELL_SIZE + OFFSET;
        GraphicsContext gc = getGraphicsContext2D();
        Image pieceImage = getPieceImage(piece);
        gc.drawImage(pieceImage, x, y, PIECE_SIZE, PIECE_SIZE);
    }


    public void draw(GraphicsContext gc) {
        updateBoard(GameLogic.getInstance());

        // Draw the chessboard squares
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int posX = i * CELL_SIZE;
                int posY = j * CELL_SIZE;
                if ((i + j) % 2 == 0) {
                    drawRectLightBrown(gc, posX, posY);
                } else {
                    drawRectBrown(gc, posX, posY);
                }
            }
        }
        // Draw the chess pieces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = GameLogic.getInstance().getChessPieceAt(i, j);
                if (piece != null) {
                    setImageAt(piece, i, j);
                }
            }
        }
    }

    private void highlightMoves(GraphicsContext gc, int posX, int posY) {
        gc.setFill(Color.LIGHTGREEN);
        int x = posX * CELL_SIZE;
        int y = posY * CELL_SIZE;
        gc.fillRect(x, y , CELL_SIZE, CELL_SIZE);
    }
}
