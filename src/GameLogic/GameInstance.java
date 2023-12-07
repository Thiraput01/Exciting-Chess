package GameLogic;

import ChessPiece.ChessPiece;
import ChessPiece.Knight;
import ChessPiece.Pawn;
import ChessPiece.Rook;
import ChessPiece.King;
import ChessPiece.Queen;
import ChessPiece.Bishop;

import java.util.ArrayList;

public class GameInstance {
    private ArrayList<ArrayList<ChessPiece>> board;
    private boolean currentPlayer;
    private static GameInstance instance=null;
    public GameInstance(){
        board=new ArrayList<>();
        currentPlayer=true;
        int i=0; // row no.
        for (ArrayList<ChessPiece> x:board){ //for all x (columns)
            x.set(1,new Pawn(i,1,true));
            x.set(6,new Pawn(i,6,false));
            i++;
        } // set all pawn
        ArrayList<ChessPiece> tmp=board.get(0); //column 0
        tmp.set(0,new Rook(0,0,true));
        tmp.set(7,new Rook(0,7,false));
        board.set(0,tmp); //set column 0
        tmp=board.get(7);
        tmp.set(0,new Rook(7,0,true));
        tmp.set(7,new Rook(7,7,false));
        board.set(7,tmp); //set column 7

        tmp=board.get(1);
        tmp.set(0,new Knight(1,0,true));
        tmp.set(7,new Knight(1,7,false));
        board.set(1,tmp); //set column 1
        tmp=board.get(6);
        tmp.set(0,new Knight(6,0,true));
        tmp.set(7,new Knight(6,7,false));
        board.set(6,tmp); //set column 6

        tmp=board.get(2);
        tmp.set(0,new Bishop(2,0,true));
        tmp.set(7,new Bishop(2,7,false));
        board.set(2,tmp); //set column 2
        tmp=board.get(5);
        tmp.set(0,new Bishop(5,0,true));
        tmp.set(7,new Bishop(5,7,false));
        board.set(5,tmp); //set column 5

        tmp=board.get(3);
        tmp.set(0,new Queen(3,0,true));
        tmp.set(7,new Queen(3,7,false));
        board.set(3,tmp); //set column 3
        tmp=board.get(4);
        tmp.set(0,new King(4,0,true));
        tmp.set(7,new King(4,7,false));
        board.set(4,tmp); //set column 4
    }

    public static GameInstance getInstance() {
        if (instance==null){
            instance=new GameInstance();
        }
        return instance;
    }

    public void nextPlayer() {
        currentPlayer=!currentPlayer;
    }

    public ChessPiece getChessPieceAt(int x,int y){
        return board.get(x).get(y);
    }
    public void setChessPieceAt(int x,int y,ChessPiece chessPiece){
        ArrayList<ChessPiece> tmp=board.get(x);
        tmp.set(y,chessPiece);
        board.set(x,tmp);
    }
}
