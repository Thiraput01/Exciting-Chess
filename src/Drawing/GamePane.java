package Drawing;

import GameLogic.GameLogic;
import javafx.animation.AnimationTimer;
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
    private static Canvas gameTimePane;
    private static ChessboardPane chessboardPane;

    public GamePane() {
        chessboardPane = new ChessboardPane();
        setPrefSize(816, 768);
        setBackground(new Background(new BackgroundFill(Color.web("5C4033"), null, null)));

        yCoordinate = new Canvas(100, 640);
        drawYnum(yCoordinate.getGraphicsContext2D());
        setLeft(yCoordinate);


        xCoordinate = new Canvas(816, 50);
        drawXnum(xCoordinate.getGraphicsContext2D());
        setTop(xCoordinate);


        timerPane = new Canvas(100, 640);
        drawTimerBar(timerPane.getGraphicsContext2D());
        setRight(timerPane);


        gameTimePane = new Canvas(816, 78);
        setBottom(gameTimePane);
        drawGameTime(gameTimePane.getGraphicsContext2D());

        setCenter(chessboardPane);
        chessboardPane.setTranslateY(0);
        GraphicsContext gc = chessboardPane.getGraphicsContext2D();
        draw(gc);

        startAnimationLoop();
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
        gc.setFont(Font.font("Bauhaus 93", FontWeight.MEDIUM, 40));
        gc.setFill(Color.web("F3E5AB"));
        for (int y = 0; y < 8; y++) {
            gc.fillText(Integer.toString(y), 70, 55 + 80 * y, 30);
        }
    }

    private void drawXnum(GraphicsContext gc) {
        gc.setFont(Font.font("Bauhaus 93", FontWeight.MEDIUM, 40));
        gc.setFill(Color.web("F3E5AB"));
        for (int x = 0; x < 8; x++) {
            gc.fillText(Integer.toString(x), 130 + 80 * x, 40, 30);
        }
    }

    protected static void drawTimerBar(GraphicsContext gc) {
        Color color = Color.web("#535353");
        gc.setLineWidth(3);
        GameLogic gameInstance = GameLogic.getInstance();
        double whitePercentage = gameInstance.getTimeLeftWhite() / 300.0;
        double blackPercentage = gameInstance.getTimeLeftBlack() / 300.0;

        double whiteBarHeight = 320 * whitePercentage;
        double blackBarHeight = 320 * blackPercentage;
        gc.setLineWidth(5);
        gc.setStroke(color);

        //fill empty bar
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(15, 0, 32, 640);
        //fill white bar
        gc.setFill(Color.WHITE);
        gc.fillRect(15, 320 - whiteBarHeight, 32, whiteBarHeight);
        //fill rect for black
        gc.setFill(Color.BLACK);
        gc.fillRect(15, 320, 32, blackBarHeight);
    }

    /*private void drawTimer(GraphicsContext gc){
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Impact", FontWeight.MEDIUM, 40));

        String timeLeftBlack = GameLogic.getInstance().getTime_left_black();
        String timeLeftWhite = GameLogic.getInstance().getTime_left_white();
        //White Side
        gc.fillText(timeLeftWhite, 228, 760);
        //Black Side
        gc.fillText(timeLeftBlack, 588, 760);
    }*/

    private void drawGameTime(GraphicsContext gc) {
        gc.setFont(Font.font("Bauhaus 93", FontWeight.MEDIUM, 40));
        gc.setFill(Color.web("F3E5AB"));
        gc.clearRect(0, 0, 816, 100);
        gc.fillText(GameLogic.getStringGameTime(), 366, 50, 10000);
    }

    private void startAnimationLoop() {
        AnimationTimer animation = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Update the timer values and redraw the timer bar
                GameLogic.getInstance().updateGameTime();
                drawTimerBar(timerPane.getGraphicsContext2D());
                gameTimePane.getGraphicsContext2D().setFill(Color.LIGHTBLUE);
                drawGameTime(gameTimePane.getGraphicsContext2D());
            }
        };
        animation.start();
    }

    public void resetGame() {
        GameLogic.getInstance().initializedBoard();
        GameLogic.getInstance().setTime_left_black(300);
        GameLogic.getInstance().setTime_left_black(300);
        GameLogic.setCurrent_game_time(0);
    }
}

