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
                if (GameUtil.attack(this,GameInstance.getInstance().getChessPieceAt(x,y),x,y)){ //successfully attacked
                    setPosY(y);
                    setPosX(x);
                }
                else{ //failed to attack, killed
                    setPosX(-1);
                    setPosY(-1);
                    GameInstance.getInstance().setChessPieceAt(this.getPosX(),this.getPosY(),null);
                }
            }
        }
    }
    private String getImageURL(boolean isWhite) {
        return isWhite ? "wKnight.png" : "bKnight.png";
    }
}
