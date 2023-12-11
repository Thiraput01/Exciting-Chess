package Drawing;

import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class MainScreenPane extends HBox {

    private static GamePane gamePane;

    private static DescriptionPane descriptionPane;

    public MainScreenPane() {
        super();
        setPrefSize(1366, 768);
        setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        // 1366 split to -> 961 for game ,space 5 and 400 for dec
        gamePane = new GamePane();
        descriptionPane = new DescriptionPane();
        setAlignment(Pos.CENTER_LEFT);
        setSpacing(5);
        getChildren().add(gamePane);
        getChildren().add(descriptionPane);
    }
}