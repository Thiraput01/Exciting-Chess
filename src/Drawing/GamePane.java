package Drawing;

import GameLogic.GameLogic;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import sharedObject.IRenderable;

import java.awt.*;

public class GamePane implements IRenderable {

    @Override
    public void draw(GraphicsContext gc) {
        GameLogic.getInstance();

        drawXnum(gc);
        drawYnum(gc);
    }

    @Override
    public int getZ() {
        return 9999;
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public boolean isVisible() {
        return true;
    }

    private void drawYnum(GraphicsContext gc) {
        gc.setFont(Font.font("Impact", FontWeight.LIGHT, 30));
        for (int y = 0; y < 8; y++) {
            if (y == 0) {
                gc.fillText(Integer.toString(y), 18, 78 + 30, 30);
            } else {
                gc.fillText(Integer.toString(y), 18, 78 + (90 * y), 30);
            }
        }
    }

    private void drawXnum(GraphicsContext gc) {
        gc.setFont(Font.font("Impact", FontWeight.LIGHT, 30));
        for (int x = 0; x < 8; x++) {
            if (x == 0) {
                gc.fillText(Integer.toString(x), 48 + 30, 48, 30);
            } else {
                gc.fillText(Integer.toString(x), 48 + (90 * x), 48, 30);
            }
        }
    }

    private void drawTimerBar(GraphicsContext gc){
       // gc.fillRoundRect();

    }
}
