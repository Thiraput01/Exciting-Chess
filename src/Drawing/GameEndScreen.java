package Drawing;

import GameLogic.GameLogic;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
        String endGameMessage = "CONGRATULATION!\n";
        endGameMessage += whiteWon ? "WHITE TEAM WINS!" : "BLACK TEAM WINS!";

        Text endGameMessageText = new Text(endGameMessage);
        endGameMessageText.setFill(Color.web("fcc200"));
        endGameMessageText.setFont(Font.font("Bauhaus 93", FontWeight.MEDIUM, 100));
        endGameMessageText.setTextAlignment(TextAlignment.CENTER);

        Text exitText = new Text("-EXIT");
        exitText.setFill(Color.web("fcc200"));
        exitText.setFont(Font.font("Bauhaus 93", FontWeight.MEDIUM, 50));

        Text newGameText = new Text("-NEW GAME");
        newGameText.setFill(Color.web("fcc200"));
        newGameText.setFont(Font.font("Bauhaus 93", FontWeight.MEDIUM, 50));

        vBox.getChildren().addAll(endGameMessageText, newGameText, exitText);
        getChildren().add(vBox);
        setAlignment(vBox, Pos.CENTER);
        newGameText.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                newGameText.setFill(Color.web("32CD32"));
                newGameText.setTranslateX(0);
            }
        });

        newGameText.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                newGameText.setFill(Color.web("fcc200"));
                newGameText.setTranslateX(2);
            }
        });

        newGameText.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        RenderableHolder.clickButton.play();
                        StartScreen.toMainScreen(gc);
                        GameLogic.resetInstance();
                    }
                });
                StartScreen.toMainScreen(gc);
            }
        });

        //set actions on exit text
        exitText.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                exitText.setTranslateX(0);
                exitText.setFill(Color.RED);
            }
        });

        exitText.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                exitText.setTranslateX(2);
                exitText.setFill(Color.web("fcc200"));
            }
        });

        exitText.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.exit(0);
            }
        });
    }
}
