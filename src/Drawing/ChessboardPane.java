package Drawing;

import GameLogic.GameLogic;
import Main.Main;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import sharedObject.RenderableHolder;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class ChessboardPane extends BorderPane {

    public ChessboardPane() {
        super();
        setPrefSize(720, 720);
        Canvas canvas = new Canvas(720, 720);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                int posX = i * 90;
                int posY = j * 90;
                if (i % 2 == 0) {
                    drawRectLightBrown(gc, posX, posY);
                } else {
                    drawRectBrown(gc, posX, posY);
                }
            }
        }

    }

    public void drawRectBrown(GraphicsContext gc, int posX, int posY) {
        gc.setFill(Color.TAN);
        gc.fillRect(posX, posY, 90, 90);
    }

    public void drawRectLightBrown(GraphicsContext gc, int posX, int posY) {
        gc.setFill(Color.BEIGE);
        gc.fillRect(posX, posY, 90, 90);
    }

}
