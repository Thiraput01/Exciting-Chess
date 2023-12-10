package Drawing;

import GameLogic.GameLogic;
import Main.Main;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
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

        Text exitText = new Text("-EXIT");
        exitText.setFont(Font.font("Cochin", FontWeight.BOLD, 50));

        Text newGameText = new Text("-NEW GAME");
        newGameText.setFont(Font.font("Cochin", FontWeight.BOLD, 50));

        vBox.getChildren().addAll(endGameMessageText, exitText, newGameText);
        setAlignment(vBox,Pos.CENTER);
        newGameText.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                newGameText.setOpacity(1);
                newGameText.setTranslateX(0);
            }
        });

        newGameText.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                newGameText.setOpacity(0.7);
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
                        toMainScreen(gc);
                        GameLogic.resetInstance();
                    }
                });
                toMainScreen(gc);
            }
        });

        //set actions on exit text
        exitText.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                exitText.setOpacity(1);
                exitText.setTranslateX(0);
            }
        });

        exitText.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                exitText.setOpacity(0.7);
                exitText.setTranslateX(2);
            }
        });

        exitText.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.exit(0);
            }
        });
    }
    public void toMainScreen(GraphicsContext gc){
        MainScreenPane mainScreenPane = new MainScreenPane();
        Group group = new Group(mainScreenPane);
        scene = new Scene(group);
        RenderableHolder.getInstance().clear();
        GameLogic.resetInstance();
        //TODO GameInstance.getInstance().start();
        Main.stage.setScene(scene);

        mainScreenPane.requestFocus();

        AnimationTimer animation = new AnimationTimer() {
            public void handle(long now) {
                GameLogic.setCurrent_game_time(now);
                GameLogic.getInstance().updateGameTime();
                RenderableHolder.getInstance().update();
                if (GameLogic.getInstance().isGameEnd()) {
                    this.stop();
                }
            }
        };
        animation.start();
    }
}
