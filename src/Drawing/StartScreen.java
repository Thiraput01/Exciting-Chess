package Drawing;


import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.canvas.Canvas;

import java.awt.*;


public class StartScreen extends BorderPane {

    private Scene scene;

    public StartScreen(){
        super();
        setPrefSize(1024, 768);

        Canvas canvas = new Canvas(1024, 768);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        //set gc pos to start at middle
        gc.translate((double) 1024 /2, (double) 768 /2);
        setBackground(gc);

        getChildren().add(canvas);


    }

    public void setBackground(GraphicsContext gc){
        //1)set bg image

        //2)set play text

    }

}

