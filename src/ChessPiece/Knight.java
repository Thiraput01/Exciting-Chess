package ChessPiece;

import GameLogic.GameInstance;
import GameLogic.GameUtil;

public class Knight extends ChessPiece implements Movable{
    private static final double rate=0.7;

    public boolean isValidMove(int toX, int toY){
        if (!GameUtil.inRangeOfBoard(toX,toY)) return false;
        if (Math.abs(toX-getPosX())==1){
            return Math.abs(toY-getPosY())==2;
        } else if (Math.abs(toX-getPosX())==2) {
            return Math.abs(toY-getPosY())==1;
        }
        return false;
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
    public void attack(int x,int y){
        if (!isValidMove(x,y)) return;

    }
}
