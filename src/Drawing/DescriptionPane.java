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

public class DescriptionPane extends BorderPane {

    public DescriptionPane(){
        super();
        setPrefSize(400, 768);
        Canvas canvas = new Canvas(400, 768);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //Set text to draw
        //1)description text
        Font descriptionFont = Font.font("Impact", FontWeight.EXTRA_LIGHT, 20);
        Font menu_exitFont = Font.font("Impact", FontWeight.MEDIUM, 25);

        //Text descriptionText = new Text(GameLogic.getInstance().getCurrentdescribtion);

        Text menuText = new Text("Menu");
        menuText.setFont(menu_exitFont);
        menuText.setFill(Color.WHITE);

        Text exitText = new Text("Exit");
        exitText.setFont(menu_exitFont);

    }
}
