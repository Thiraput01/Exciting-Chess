package GameLogic;

import ChessPiece.ChessPiece;

import java.util.ArrayList;

public class GameInstance {
    private ArrayList<ArrayList<ChessPiece>> board;
    private boolean currentPlayer;
    private static GameInstance instance=null;

    public GameInstance(){
        board=new ArrayList<>();
        currentPlayer=true;
        //initialize board
    }

    public static GameInstance getInstance() {
        if (instance==null){
            instance=new GameInstance();
        }
        return instance;
    }

    public void nextPlayer() {
        if (currentPlayer) currentPlayer=false;
        else currentPlayer=true;
    }

    public ChessPiece getChessPieceAt(int x,int y){
        return board.get(x).get(y);
    }
    public void setChessPieceAt(int x,int y,ChessPiece chessPiece){
        board.get(x).set(y,chessPiece);
    }
}
