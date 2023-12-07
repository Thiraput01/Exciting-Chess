package ChessPiece;

import GameLogic.GameInstance;

import java.util.ArrayList;

public class Pawn extends ChessPiece implements Movable{
    private ArrayList<ChessPosition> possibleMoves;
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
        int direction= isWhite()? 1:-1;
        if (getPosY()+direction<8 && getPosY()+direction>=0) possibleMoves.add(new ChessPosition(getPosX(),getPosY()+direction));
        // case: attacking diagonally
        ChessPiece target1=GameInstance.getInstance().getChessPieceAt(getPosX()-1,getPosY()+direction);
        ChessPiece target2=GameInstance.getInstance().getChessPieceAt(getPosX()+1,getPosY()+direction);
        if (target1!=null && target1.isWhite()!=isWhite()) possibleMoves.add(new ChessPosition(getPosX()-1,getPosY()+direction));
        if (target2!=null && target2.isWhite()!=isWhite()) possibleMoves.add(new ChessPosition(getPosX()+1,getPosY()+direction));
    }
}
