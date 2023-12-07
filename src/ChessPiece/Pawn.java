package ChessPiece;

import GameLogic.GameInstance;

import java.util.ArrayList;

public class Pawn extends ChessPiece implements Movable{
    public Pawn(int x,int y,boolean isWhite){
        super(x,y,isWhite);
        setRate(0.5);
        setPieceUrl(getImageURL(isWhite));
        possibleMoves=new ArrayList<>();
    }

    public boolean isValidMove(int toX, int toY){
        if (toX<0|| toY<0 || toX>7 || toY>7) return false;
        int direction= isWhite()? 1:-1;
        if ((toX==getPosX()+1 || toX==getPosX()-1) && toY==getPosY()+direction && GameInstance.getInstance().getChessPieceAt(toX,toY)!=null && GameInstance.getInstance().getChessPieceAt(toX,toY).isWhite()!=isWhite()) return true; // attacking diagonally upward
        return toX==getPosX() && toY==getPosX()+direction;
    }
    private String getImageURL(boolean isWhite) {
        return isWhite ? "wPawn.png" : "bPawn.png";
    }

    public void setCurrentAllPossibleMoves(){
        possibleMoves.clear();
        for(int i=0;i<8;i++){
            for (int e=0;e<8;e++){
                if (isValidMove(i,e) && (GameInstance.getInstance().getChessPieceAt(i,e)==null || (GameInstance.getInstance().getChessPieceAt(i,e)!=null && GameInstance.getInstance().getChessPieceAt(i,e).isWhite()!=isWhite()))) possibleMoves.add(new ChessPosition(i,e));
            }
        }
    }
}
