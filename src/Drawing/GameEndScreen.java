package Drawing;

import GameLogic.GameLogic;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import sharedObject.RenderableHolder;

public class GameEndScreen extends StackPane {
    private Scene scene;

    public GameEndScreen() {
        Canvas canvas = new Canvas(1366, 768);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        //set background image
        gc.drawImage(RenderableHolder.wallpaper2, 0, 0, 1366, 768);
        getChildren().add(canvas);

        VBox vBox = new VBox(30);
        vBox.setAlignment(Pos.CENTER);

        boolean whiteWon = GameLogic.isWhiteWon();
        String endGameMessage = whiteWon ? "White Won!\n" : "Black Won!\n";
        endGameMessage += "Congratulation!";
        Text endGameMessageText = new Text(endGameMessage);
        endGameMessageText.setFont(Font.font("Cochin", FontWeight.BOLD, 100));

        Text exitText = new Text("EXIT");
        exitText.setFont(Font.font("Cochin", FontWeight.BOLD, 50));

        Text newGameText = new Text("NEW GAME");
        newGameText.setFont(Font.font("Cochin", FontWeight.BOLD, 50));

        vBox.getChildren().addAll(endGameMessageText, exitText, newGameText);
    }
}
