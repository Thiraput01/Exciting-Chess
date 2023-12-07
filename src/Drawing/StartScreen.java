package Drawing;


import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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

public class StartScreen extends BorderPane {

    private Scene scene;

    public StartScreen(){
        super();
        setPrefSize(1366, 768);

        Canvas canvas = new Canvas(1366, 768);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        //set background image
        gc.drawImage(RenderableHolder.wallpaper, 0, 0, 1366, 768);
        getChildren().add(canvas);

        //create vBox to add texts, set padding to 30
        VBox vBox = new VBox(30);
        vBox.setAlignment(Pos.CENTER);
        //set text font
        Font titleFont = Font.font("Impact", FontWeight.MEDIUM, 100);
        Font playFont = Font.font("Cochin", FontWeight.BOLD, 50);
        Font exitFont = Font.font("Cochin", FontWeight.BOLD, 50);

        //set text
        Text title = new Text("EXCITING  CHESS");
        title.setFont(titleFont);
        title.setFill(Color.WHITE);

        Text playText = new Text("-PLAY");
        playText.setFont(playFont);
        playText.setFill(Color.WHITE);
        playText.setOpacity(0.7);
        playText.setTranslateX(2);

        Text exitText = new Text("-EXIT");
        exitText.setFont(exitFont);
        exitText.setFill(Color.WHITE);
        exitText.setOpacity(0.7);
        exitText.setTranslateX(2);

        //add components to Vbox
        vBox.getChildren().addAll(title, playText, exitText);
        setCenter(vBox);

        //set action on playText
        playText.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                playText.setOpacity(1);
                playText.setTranslateX(0);
            }
        });

        playText.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                playText.setOpacity(0.7);
                playText.setTranslateX(2);
            }
        });

        playText.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        RenderableHolder.clickButton.play();
                    }
                });
                toMainScreen();
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


    public void toMainScreen(){
    }
}

