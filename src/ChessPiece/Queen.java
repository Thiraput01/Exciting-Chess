package ChessPiece;

import GameLogic.GameUtil;

import java.util.ArrayList;

public class Queen extends ChessPiece implements Movable{
    public Queen(int x,int y,boolean isWhite){
        super(x,y,isWhite);
        setRate(0.9);
        setPieceUrl(getImageURL(isWhite));
        possibleMoves=new ArrayList<>();
    }
    //TODO to be edited
    public boolean isValidMove(int toX, int toY){
        if (!GameUtil.inRangeOfBoard(toX,toY)) return false;
        return Math.abs(toX-getPosX())==1 || Math.abs(toY-getPosY())==1;
    }
    private String getImageURL(boolean isWhite) {
        return isWhite ? "wQueen.png" : "bQueen.png";
    }
    public void setCurrentAllPossibleMoves(){
        possibleMoves.clear();
        for (int i=0;i<8;i++){
            for (int e=0;e<8;e++){
                if (isValidMove(i,e)) possibleMoves.add(new ChessPosition(i,e));
            }
        }
    }
}
