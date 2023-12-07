package ChessPiece;

import GameLogic.GameInstance;
import GameLogic.GameUtil;

public class Pawn extends ChessPiece implements Movable{
    private static final double rate=0.5;

    public boolean isValidMove(int toX, int toY){
        return (Math.abs(getPosX()-toX)==1 && getPosY()==toY && GameUtil.inRangeOfBoard(toX,toY));
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
        return isWhite ? "wPawn.png" : "bPawn.png";
    }
}
