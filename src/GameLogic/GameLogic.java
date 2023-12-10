package GameLogic;

import ChessPiece.*;
import Drawing.DescriptionPane;

import java.util.ArrayList;

public class GameLogic {
    private ArrayList<ArrayList<ChessPiece>> board;
    private boolean currentPlayer; //true for white
    private static long lastTimeTriggered = -1;
    protected static long currentTime;
    private static int gameTime;
    private long time_left_white;
    private long time_left_black;
    private static GameLogic instance = null;
    private boolean isHighlighting = false;
    private ChessPiece currentClickingPiece;
    private static boolean whiteWon = false; //used only when game ends
    private String currentDesc = "";

    public GameLogic() {
        //start timer for both black and white
        setTime_left_black(300);
        setTime_left_white(300);
        //set current player to white(true)
        currentPlayer = true;
        //initialized chess Board();
        initializedBoard();
        gameTime = 0;
    }

    public void initializedBoard() {

        board = new ArrayList<>();

        // Initialize the chessboard with empty squares
        for (int i = 0; i < 8; i++) {
            ArrayList<ChessPiece> row = new ArrayList<>();
            for (int j = 0; j < 8; j++) {
                row.add(null);
            }
            board.add(row);
        }

        // Set white pawns
        for (int i = 0; i < 8; i++) {
            board.get(i).set(1, new Pawn(i, 1, true));
        }

        // Set black pawns
        for (int i = 0; i < 8; i++) {
            board.get(i).set(6, new Pawn(i, 6, false));
        }

        // Set other white pieces
        board.get(0).set(0, new Rook(0, 0, true));
        board.get(1).set(0, new Knight(1, 0, true));
        board.get(2).set(0, new Bishop(2, 0, true));
        board.get(3).set(0, new Queen(3, 0, true));
        board.get(4).set(0, new King(4, 0, true));
        board.get(5).set(0, new Bishop(5, 0, true));
        board.get(6).set(0, new Knight(6, 0, true));
        board.get(7).set(0, new Rook(7, 0, true));

        // Set other black pieces
        board.get(0).set(7, new Rook(0, 7, false));
        board.get(1).set(7, new Knight(1, 7, false));
        board.get(2).set(7, new Bishop(2, 7, false));
        board.get(3).set(7, new Queen(3, 7, false));
        board.get(4).set(7, new King(4, 7, false));
        board.get(5).set(7, new Bishop(5, 7, false));
        board.get(6).set(7, new Knight(6, 7, false));
        board.get(7).set(7, new Rook(7, 7, false));


    }


    public static GameLogic getInstance() {
        if (instance == null) {
            instance = new GameLogic();
        }
        return instance;
    }

    public static void resetInstance() {
        instance = new GameLogic();
    }

    public void nextPlayer() {
        currentPlayer = !currentPlayer;
    }

    public ChessPiece getChessPieceAt(int x, int y) {
        return board.get(x).get(y);
    }


    public void setChessPieceAt(int x, int y, ChessPiece chessPiece) {
        ArrayList<ChessPiece> tmp = board.get(x);
        tmp.set(y, chessPiece);
        board.set(x, tmp);
    }

    public boolean isGameEnd() {
        boolean blackKingFound = false;
        boolean whiteKingFound = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece currentPiece = board.get(i).get(j);
                if (currentPiece instanceof King) {
                    if (currentPiece.isWhite()) whiteKingFound = true;
                    else blackKingFound = true;
                }
            }
        }
        whiteWon = (!blackKingFound || !whiteKingFound) ? whiteKingFound : false; //if ends,whiteWon is when white king found
        return !blackKingFound || !whiteKingFound || time_left_black <= 0 || time_left_white <= 0;
    }

    public String getTime_left_white() {
        return time_left_white / 60 + " : " + time_left_white % 60;
    }

    public String getTime_left_black() {
        return time_left_black / 60 + " : " + time_left_black % 60;
    }

    public long getTimeLeftWhite() {
        return time_left_white;
    }

    public long getTimeLeftBlack() {
        return time_left_black;
    }

    public void setTime_left_white(long time_left_white) {
        if (time_left_white < 0) time_left_white = 0;
        this.time_left_white = time_left_white;
    }

    public void setTime_left_black(long time_left_black) {
        if (time_left_black < 0) time_left_black = 0;
        this.time_left_black = time_left_black;
    }

    public void updateGameTime() {
        lastTimeTriggered = (lastTimeTriggered < 0 ? currentTime : lastTimeTriggered);
        if (currentTime - lastTimeTriggered >= 1000000000) {
            gameTime++;
            lastTimeTriggered = currentTime;
            if (currentPlayer) time_left_white--;
            else time_left_black--;
        }

    }

    public ArrayList<ArrayList<ChessPiece>> getBoard() {
        return board;
    }

    public static void setCurrent_game_time(long time) {
        currentTime = time;
    }

    public long getCurrent_game_time() {
        return gameTime;
    }

    public long getTimeLeft(boolean isWhite) {
        return isWhite ? time_left_white : time_left_black;
    }

    public static void setCurrentTime(long currentTime) {
        GameLogic.currentTime = currentTime;
    }

    public static String getStringGameTime() {
        String minDigit;
        String secDigit;
        if (gameTime / 60 >= 10) minDigit = Integer.toString(gameTime / 60);
        else minDigit = "0" + Integer.toString(gameTime / 60);
        if (gameTime % 60 >= 10) secDigit = Integer.toString(gameTime % 60);
        else secDigit = "0" + Integer.toString(gameTime % 60);
        return minDigit + " : " + secDigit;
    }

    public boolean isHighlighting() {
        return isHighlighting;
    }

    public void setHighlighting(boolean isHighlighting) {
        this.isHighlighting = isHighlighting;
    }

    public void setCurrentClickingPiece(ChessPiece currentClickingPiece) {
        this.currentClickingPiece = currentClickingPiece;
    }

    public ChessPiece getCurrentClickingPiece() {
        return currentClickingPiece;
    }

    public boolean getCurrentPlayer() {
        return currentPlayer;
    }

    public String getCurrentDesc() {
        return currentDesc;
    }

    public void setCurrentDesc(String currentDesc) {

        this.currentDesc = currentDesc + "\n";
        if (currentPlayer) {
            this.currentDesc += "->Black turn\n";
        } else {
            this.currentDesc += "->White turn\n";
        }
        DescriptionPane.updateDescriptionText();
    }

    public static boolean isWhiteWon() {
        return whiteWon;
    }
}