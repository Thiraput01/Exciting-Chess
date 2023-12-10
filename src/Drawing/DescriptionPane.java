package Drawing;

import GameLogic.GameLogic;
import Main.Main;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import sharedObject.RenderableHolder;

public class DescriptionPane extends BorderPane {

    private Scene scene;

    private static Text descriptionText;
    public DescriptionPane() {
        super();
        setPrefSize(545, 768);
        setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
        Canvas canvas = new Canvas(545, 768);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Set text to draw
        Font menu_exitFont = Font.font("Impact", FontWeight.MEDIUM, 50);
        Font descriptionFont = Font.font("Cochin", FontWeight.EXTRA_LIGHT, 25);
        String current;
        Text menuText = new Text("Menu");
        descriptionText = new Text("Start Game!\n" + "White turn");

        menuText.setFont(menu_exitFont);
        menuText.setFill(Color.BLACK);

        descriptionText.setFont(descriptionFont);
        descriptionText.setFill(Color.BLACK);
        menuText.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                menuText.setOpacity(0.7);
                menuText.setTranslateX(-2);
            }
        });

        menuText.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                menuText.setOpacity(1);
                menuText.setTranslateX(0);
            }
        });

        Text exitText = new Text("Exit");
        exitText.setFont(menu_exitFont);
        exitText.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                exitText.setFill(Color.RED);
                exitText.setTranslateX(-2);
            }
        });
        exitText.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                exitText.setFill(Color.BLACK);
                exitText.setTranslateX(0);
            }
        });
        exitText.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                toStartScreen();
            }
        });

        // Add Text nodes to an HBox
        HBox textContainer = new HBox(100, menuText, exitText);
        textContainer.setAlignment(Pos.CENTER);

        // Set the HBox as the bottom of the BorderPane
        setBottom(textContainer);
        setCenter(descriptionText);
    }

    public void toStartScreen(){
        RenderableHolder.clickButton.play();
        StartScreen startScreen = new StartScreen();
        Group group = new Group(startScreen);
        scene = new Scene(group);

        RenderableHolder.getInstance().clear();
        Main.stage.setScene(scene);
        startScreen.requestFocus();
    }

    public static void updateDescriptionText(){
        descriptionText.setText(GameLogic.getInstance().getCurrentDesc());
    }

}