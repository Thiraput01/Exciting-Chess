package Drawing;

import GameLogic.GameLogic;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class DescriptionPane extends BorderPane {

    public DescriptionPane() {
        super();
        setPrefSize(545, 768);

        Canvas canvas = new Canvas(545, 768);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Set text to draw
        Font menu_exitFont = Font.font("Impact", FontWeight.MEDIUM, 50);

        Text menuText = new Text("Menu");
        menuText.setFont(menu_exitFont);
        menuText.setFill(Color.BLACK);

        Text exitText = new Text("Exit");
        exitText.setFont(menu_exitFont);

        // Add Text nodes to an HBox
        HBox textContainer = new HBox(100, menuText, exitText);
        textContainer.setAlignment(Pos.CENTER);

        // Set the HBox as the bottom of the BorderPane
        setBottom(textContainer);
    }
}
