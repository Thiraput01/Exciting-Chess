package Drawing;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class MainScreenPane extends HBox {

    private static GamePane gamePane;

    private static DescriptionPane descriptionPane;

    public MainScreenPane() {
        super();
        setPrefSize(1366, 768);
        // 1366 split to -> 961 for game ,space 5 and 400 for dec
        gamePane = new GamePane();
        descriptionPane = new DescriptionPane();
        setAlignment(Pos.CENTER_LEFT);
        setSpacing(5);
        getChildren().add(gamePane);
        getChildren().add(descriptionPane);
    }

    public static GamePane getGamePane() {
        if (gamePane == null) {
            gamePane = new GamePane();
        }
        return gamePane;
    }

    public static DescriptionPane getDescriptionPane() {
        if(descriptionPane == null){
            descriptionPane = new DescriptionPane();
        }
        return descriptionPane;
    }
}
