package Main;

import Drawing.StartScreen;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage stage;

    @Override
    public void start(Stage primaryStage){
        stage = primaryStage;

        Parent startscreen = new StartScreen();
        Scene mainScene = new Scene(startscreen);
        stage.setTitle("Exciting Chess!");
        stage.setScene(mainScene);
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args){
        launch(args);
    }
}
