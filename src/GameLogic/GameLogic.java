package GameLogic;

import ChessPiece.ChessPiece;
import ChessPiece.Knight;
import ChessPiece.Pawn;
import ChessPiece.Rook;
import ChessPiece.King;
import ChessPiece.Queen;
import ChessPiece.Bishop;

import java.util.ArrayList;

public class GameLogic {
    private ArrayList<ArrayList<ChessPiece>> board;
    private boolean currentPlayer;

    private long current_game_time;
    private long time_left_white;
    private long time_left_black;
    private static GameLogic instance = null;

    public GameLogic() {
        board = new ArrayList<>();
        currentPlayer = true;
        int i = 0; // row no.
        for (ArrayList<ChessPiece> x : board) { //for all x (columns)
            x.set(1, new Pawn(i, 1, true));
            x.set(6, new Pawn(i, 6, false));
            i++;
        } // set all pawn

        setTime_left_black(300000);
        setTime_left_white(300000);

        ArrayList<ChessPiece> tmp = board.get(0); //column 0
        tmp.set(0, new Rook(0, 0, true));
        tmp.set(7, new Rook(0, 7, false));
        board.set(0, tmp); //set column 0
        tmp = board.get(7);
        tmp.set(0, new Rook(7, 0, true));
        tmp.set(7, new Rook(7, 7, false));
        board.set(7, tmp); //set column 7

        tmp = board.get(1);
        tmp.set(0, new Knight(1, 0, true));
        tmp.set(7, new Knight(1, 7, false));
        board.set(1, tmp); //set column 1
        tmp = board.get(6);
        tmp.set(0, new Knight(6, 0, true));
        tmp.set(7, new Knight(6, 7, false));
        board.set(6, tmp); //set column 6

        tmp = board.get(2);
        tmp.set(0, new Bishop(2, 0, true));
        tmp.set(7, new Bishop(2, 7, false));
        board.set(2, tmp); //set column 2
        tmp = board.get(5);
        tmp.set(0, new Bishop(5, 0, true));
        tmp.set(7, new Bishop(5, 7, false));
        board.set(5, tmp); //set column 5

        tmp = board.get(3);
        tmp.set(0, new Queen(3, 0, true));
        tmp.set(7, new Queen(3, 7, false));
        board.set(3, tmp); //set column 3
        tmp = board.get(4);
        tmp.set(0, new King(4, 0, true));
        tmp.set(7, new King(4, 7, false));
        board.set(4, tmp); //set column 4
    }

    public static GameLogic getInstance() {
        if (instance == null) {
            instance = new GameLogic();
        }
        return instance;
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
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece currentPiece = board.get(i).get(j);
                if (currentPiece instanceof King) {
                    if (currentPiece.getPosX() == -1) {
                        return true;
                    }
                }
            }
        }
        return time_left_black <= 0 || time_left_white <= 0;
    }

    public String getTime_left_white() {
        return time_left_white / 60 + " : " + time_left_white % 60;
    }

    public String getTime_left_black() {
        return time_left_black / 60 + " : " + time_left_black % 60;
    }

    public void setTime_left_white(long time_left_white) {
        if (time_left_white < 0) time_left_white = 0;
        this.time_left_white = time_left_white;
    }

    public void setTime_left_black(long time_left_black) {
        if (time_left_black < 0) time_left_black = 0;
        this.time_left_black = time_left_black;
    }

    public void setCurrent_game_time(long current_game_time) {
        this.current_game_time = current_game_time;
    }

    public long getCurrent_game_time() {
        return current_game_time;
    }
}
