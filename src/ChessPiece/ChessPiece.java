package ChessPiece;

public abstract class ChessPiece {
    private int XPos;
    private int YPos;
    private boolean team; //white is true black is false

    public ChessPiece(int x,int y,boolean t){
        setTeam(t);
        setXPos(x);
        setYPos(y);
    }

    public int getXPos() {
        return XPos;
    }

    public int getYPos() {
        return YPos;
    }

    public boolean isTeam() {
        return team;
    }

    public void setXPos(int XPos) {
        if (XPos<0) XPos=0;
        if (XPos>7) XPos=7;
        this.XPos = XPos;
    }

    public void setYPos(int YPos) {
        if (YPos<0) YPos=0;
        if (YPos>7) YPos=7;
        this.YPos = YPos;
    }

    public void setTeam(boolean team) {
        this.team = team;
    }
}
