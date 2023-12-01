package ChessPiece;

import GameLogic.GameInstance;
import GameLogic.GameUtil;

public class Rook extends ChessPiece implements Movable{
    private static final double rate=0.8;

    public boolean isValidMove(int toX, int toY){
        if (!GameUtil.inRangeOfBoard(toX,toY)) return false;
        return (!(toX==getPosX()&&toY==getPosY()))&&(toX==getPosX() || toY==getPosY());
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
