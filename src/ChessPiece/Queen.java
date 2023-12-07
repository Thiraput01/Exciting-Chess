package ChessPiece;

import GameLogic.GameInstance;
import GameLogic.GameUtil;

public class Queen extends ChessPiece implements Movable{
    private static final double rate=0.9;

    public boolean isValidMove(int toX, int toY){
        if (!GameUtil.inRangeOfBoard(toX,toY)) return false;
        return Math.abs(toX-getPosX())==1 || Math.abs(toY-getPosY())==1;
    }

    public void move(int x, int y){
        if (isValidMove(x,y)){
            if (GameInstance.getInstance().getChessPieceAt(x,y)==null){
                setPosY(y);
                setPosX(x);
            }
            else{
                //attack
            }
        }
    }
    private String getImageURL(boolean isWhite) {
        return isWhite ? "wQueen.png" : "bQueen.png";
    }
}
