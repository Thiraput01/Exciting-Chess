package StartScreen;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartScreenMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chess Start Screen");

        FallingAnimation fallingAnimation = new FallingAnimation();
        //ChessPieceRandomFallingPane chessPiecePane = fallingAnimation.getChessPiecePane();

        //Scene scene = new Scene(chessPiecePane, 600, 400);

        //primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
