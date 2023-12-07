package GameLogic;
import ChessPiece.ChessPiece;

import java.util.Random;

public class GameUtil {
    public static boolean successAttack(double attacker,double attacked){
        double tmp=(attacker)/(attacked+attacker);
        double randomValue=new Random().nextDouble();
        return tmp<=randomValue;
    }

    public static boolean inRangeOfBoard(int x,int y){
        return (x>=0 && y>=0 && x<8 &&y<8);
    }

    public static boolean attack(ChessPiece attacker, ChessPiece defender,int x,int y){
        if (successAttack(attacker.getRate(),defender.getRate())){
            GameInstance.getInstance().setChessPieceAt(x,y,attacker);
            return true;
        }
        else{
            return false;
        }
    }
}
