package Drawing;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;

import java.awt.*;

public class GamePane extends StackPane {

    private ChessboardPane chessboardPane;

    public GamePane(){
        Canvas canvas = new Canvas(816, 768);
        GraphicsContext gc = canvas.getGraphicsContext2D();

    }
}
