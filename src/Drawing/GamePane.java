package Drawing;

import GameLogic.GameLogic;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sharedObject.IRenderable;

public class GamePane extends BorderPane implements IRenderable {

    private static Canvas timerPane;
    private static Canvas yCoordinate;
    private static Canvas xCoordinate;
    private static ChessboardPane chessboardPane;

    public GamePane(){
        chessboardPane = new ChessboardPane();
        setPrefSize(816, 768);
        setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));

        yCoordinate=new Canvas(100,768);
        setLeft(yCoordinate);
        drawYnum(yCoordinate.getGraphicsContext2D());

        xCoordinate = new Canvas(816,50);
        xCoordinate = new Canvas(816,45);
        setTop(xCoordinate);
        drawXnum(xCoordinate.getGraphicsContext2D());

        timerPane=new Canvas(100,768);
        setRight(timerPane);
        drawTimerBar(timerPane.getGraphicsContext2D());

        setCenter(chessboardPane);
        chessboardPane.setTranslateY(-50);
        GraphicsContext gc= chessboardPane.getGraphicsContext2D();
        draw(gc);
    }
    @Override
    public void draw(GraphicsContext gc) {
        GameLogic.getInstance();
        //drawTimerBar(gc);
        //drawTimer(gc);
    }

    @Override
    public int getZ() {
        return 9999;
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }


    private void drawYnum(GraphicsContext gc) {
        gc.setFont(Font.font("Impact", FontWeight.LIGHT, 40));
        gc.setFill(Color.BLACK);
        for (int y = 0; y < 8; y++) {
            gc.fillText(Integer.toString(y), 80, 110+80*y , 30);
        }
    }

    private void drawXnum(GraphicsContext gc) {
        gc.setFont(Font.font("Impact", FontWeight.LIGHT, 40));
        gc.setFill(Color.BLACK);
        for (int x = 0; x < 8; x++) {
            gc.fillText(Integer.toString(x), 168+80*x, 40, 30);
        }
    }

    private void drawTimerBar(GraphicsContext gc) {
        Color color = Color.web("#535353");
        gc.setLineWidth(3);
        GameLogic gameInstance = GameLogic.getInstance();
        double whitePercentage = gameInstance.getTimeLeftWhite() / (double) gameInstance.getCurrent_game_time();
        double blackPercentage = gameInstance.getTimeLeftBlack() / (double) gameInstance.getCurrent_game_time();

        double whiteBarHeight = 320 * whitePercentage;
        double blackBarHeight = 320 * blackPercentage;
        gc.setLineWidth(5);
        gc.setStroke(color);
        
        //fill empty bar
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(15, 14, 32, 640);
        //fill white bar
        gc.setFill(Color.WHITE);
        gc.fillRect(15, 334-whiteBarHeight, 32, whiteBarHeight);
        //fill rect for black
        gc.setFill(Color.BLACK);
        gc.fillRect(15, 334, 32, blackBarHeight);
        //set border
        gc.strokeRect(776, 48, 32, 720);
    }

    private void drawTimer(GraphicsContext gc){
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Impact", FontWeight.MEDIUM, 40));

        String timeLeftBlack = GameLogic.getInstance().getTime_left_black();
        String timeLeftWhite = GameLogic.getInstance().getTime_left_white();
        //White Side
        gc.fillText(timeLeftWhite, 228, 760);
        //Black Side
        gc.fillText(timeLeftBlack, 588, 760);
    }

}