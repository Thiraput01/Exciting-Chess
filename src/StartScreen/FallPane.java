package StartScreen;

import javafx.scene.layout.Pane;

public class FallPane extends Pane {

    private static final String[] PIECE_FILENAMES = {
            "bKing.png", "bQueen.png", "bKnight.png", "bRook.png", "bPawn.png",
            "wKing.png", "wQueen.png", "wKnight.png", "wRook.png", "wPawn.png"
    };

    private static final double FALL_SPEED = 2.0;

    public FallPane() {
        spawnChessPiece();
    }

    private void spawnChessPiece() {
        ChessPieceRandomFalling chessPiece = new ChessPieceRandomFalling();
        getChildren().add(chessPiece);
        animateFalling(chessPiece);
    }

    private void animateFalling(ChessPieceRandomFalling chessPiece) {
        chessPiece.setTranslateX(Math.random() * 600); // Random initial X position
        chessPiece.setTranslateY(-Math.random() * 400); // Start falling from above the screen

        // Set up animation timeline for the falling chess piece
        chessPiece.timeline.setOnFinished(event -> {
            getChildren().remove(chessPiece);
            spawnChessPiece();
        });

        getChildren().add(chessPiece);
        chessPiece.timeline.play();
    }
}
