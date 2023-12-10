package Drawing;

import ChessPiece.*;
import GameLogic.GameLogic;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
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
                boolean currentPLayer = gameInstance.getCurrentPlayer();
                ChessPiece currentPiece = gameInstance.getChessPieceAt(posX, posY);
                boolean emptySquare = currentPiece == null;
                //System.out.println("this is a " + currentPLayer + " turn");
                //if(!emptySquare) System.out.println("the pice to move is " + currentPiece.isWhite());
                //1)check if it was in move state first (to handle null currentPiece)
                if (gameInstance.isHighlighting() && checkCLickInHighlight(gameInstance, posX, posY)) {
                    //check if the game is in highlight state and click in highlight is valid
                    move(gameInstance, posX, posY);
                } else if (!emptySquare && (currentPLayer == currentPiece.isWhite())) {
                    if (!gameInstance.isHighlighting()) {
                        //this is the first state where the player needs to click a piece to activate highlighting
                        highlight(gameInstance, currentPiece, posX, posY);
                    } else {
                        //this is the state where the player clicked other piece to change the piece to move
                        changePiecetohighlight(gameInstance, currentPiece, posX, posY);
                    }
                }
                //if it comes down here, means it is a necessary click --> ignore

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
        initBoard(GameLogic.getInstance());
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
                    drawPiece(gc, piece, i, j);
                }
            }
        }
    }

    private void drawPiece(GraphicsContext gc, ChessPiece piece, int posX, int posY) {
        int x = posX * CELL_SIZE + OFFSET;
        int y = posY * CELL_SIZE + OFFSET;
        Image pieceImage = getPieceImage(piece);
        gc.drawImage(pieceImage, x, y, PIECE_SIZE, PIECE_SIZE);
    }

    public void initBoard(GameLogic gameInstance) {
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
                ChessPiece piece = gameInstance.getChessPieceAt(i, j);
                if (piece != null) {
                    drawPiece(gc, piece, i, j);
                }
            }
        }
    }

    public void removePieceAt(int posX, int posY) {
        int x = posX * CELL_SIZE;
        int y = posY * CELL_SIZE;
        getGraphicsContext2D().clearRect(x, y, CELL_SIZE, CELL_SIZE);
    }

    public void setImageAt(ChessPiece piece, int posX, int posY) {
        int x = posX * CELL_SIZE + OFFSET;
        int y = posY * CELL_SIZE + OFFSET;
        GraphicsContext gc = getGraphicsContext2D();
        Image pieceImage = getPieceImage(piece);
        gc.drawImage(pieceImage, x, y, PIECE_SIZE, PIECE_SIZE);
    }

    public void resetTiles() {
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
    }

    private void highlightMoves(GraphicsContext gc, int posX, int posY) {
        gc.setFill(Color.LIGHTGREEN);
        int x = posX * CELL_SIZE;
        int y = posY * CELL_SIZE;
        gc.fillRect(x, y, CELL_SIZE, CELL_SIZE);
    }

    private void highlightRedMoves(GraphicsContext gc, int posX, int posY) {
        gc.setFill(Color.RED);
        int x = posX * CELL_SIZE;
        int y = posY * CELL_SIZE;
        gc.fillRect(x, y, CELL_SIZE, CELL_SIZE);
    }

    private void highlight(GameLogic gameInstance, ChessPiece currentPiece, int posX, int posY) {
        ArrayList<ChessPosition> allPossibleMoves = currentPiece.getPossibleMoves();
        GraphicsContext gc = getGraphicsContext2D();

        for (ChessPosition chessPosition : allPossibleMoves) {
            int x = chessPosition.getX();
            int y = chessPosition.getY();

            // Draw the highlight
            highlightMoves(gc, x, y);

            ChessPiece pieceAtPosition = gameInstance.getChessPieceAt(x, y);
            if (pieceAtPosition != null) {
                // Draw the piece again on top of the highlight
                highlightRedMoves(gc, x, y);
                drawPiece(gc, pieceAtPosition, x, y);
            }
        }
        gameInstance.setHighlighting(true);
        // Remember the piece that the player clicked
        gameInstance.setCurrentClickingPiece(currentPiece);
    }


    private void changePiecetohighlight(GameLogic gameInstance, ChessPiece currentPiece, int posX, int posY) {
        updateBoard(gameInstance);
        highlight(gameInstance, currentPiece, posX, posY);
    }

    private void move(GameLogic gameInstance, int posX, int posY) {
        ChessPiece currentPiece = gameInstance.getCurrentClickingPiece();
        currentPiece.move(posX, posY);

        updateBoard(GameLogic.getInstance());

        //removePieceAt(currentPiece.getPosX(), currentPiece.getPosY());
        //setImageAt(currentPiece, posX, posY);

        gameInstance.setHighlighting(false);
        gameInstance.setCurrentClickingPiece(null);
        gameInstance.nextPlayer();
    }

    public boolean checkCLickInHighlight(GameLogic gameInstance, int posX, int posY) {
        ArrayList<ChessPosition> allPossiblemoves = gameInstance.getCurrentClickingPiece().getPossibleMoves();
        for (ChessPosition currentPosition : allPossiblemoves) {
            if (currentPosition.getX() == posX && currentPosition.getY() == posY) {
                return true;
            }
        }
        return false;
    }
}
