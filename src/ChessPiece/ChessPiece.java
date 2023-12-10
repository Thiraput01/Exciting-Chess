package ChessPiece;

import GameLogic.GameLogic;
import GameLogic.GameUtil;
import sharedObject.RenderableHolder;

import java.util.ArrayList;

public abstract class ChessPiece implements Movable {
    private int posX;
    private int posY;
    private double rate;
    private String pieceUrl;
    private boolean whiteTeam; //white is true black is false
    protected ArrayList<ChessPosition> possibleMoves;

    public ChessPiece(int x, int y, boolean t) {
        setTeam(t);
        posX = x;
        posY = y;
    }


    public boolean isWhite() {
        return whiteTeam;
    }


    public void setTeam(boolean team) {
        this.whiteTeam = team;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getPieceType() {
        if (this instanceof Pawn) {
            return "Pawn";
        } else if (this instanceof Knight) {
            return "Knight";
        } else if (this instanceof Bishop) {
            return "Bishop";
        } else if (this instanceof Rook) {
            return "Rook";
        } else if (this instanceof Queen) {
            return "Queen";
        } else if (this instanceof King) {
            return "King";
        }
        return "";
    }


    public void setPieceUrl(String pieceUrl) {
        this.pieceUrl = pieceUrl;
    }

    /*private void addEventHandler(){
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setCurrentAllPossibleMoves();
            }
        });
    }*/

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosY() {
        return posY;
    }

    public String toString() {
        if (isWhite()) {
            if (this instanceof Pawn) {
                return "White Pawn  ";
            } else if (this instanceof Knight) {
                return "White Knight ";
            } else if (this instanceof Rook) {
                return "White Rook ";
            } else if (this instanceof Queen) {
                return "White Queen ";
            } else if (this instanceof King) {
                return "White King ";
            }
        } else {
            if (this instanceof Pawn) {
                return "Black Pawn ";
            } else if (this instanceof Knight) {
                return "Black Knight ";
            } else if (this instanceof Rook) {
                return "Black Rook ";
            } else if (this instanceof Queen) {
                return "Black Queen ";
            } else if (this instanceof King) {
                return "Black King ";
            }
        }
        return "Invalid";
    }


    public abstract void setCurrentAllPossibleMoves();

    public boolean move(int x, int y) {
        ChessPiece current = GameLogic.getInstance().getCurrentClickingPiece();
        if (isValidMove(x, y)) {
            int oldX = getPosX();  // Store the old X position
            int oldY = getPosY();  // Store the old Y position

            if (GameLogic.getInstance().getChessPieceAt(x, y) == null) {
                RenderableHolder.moveChess.play();
                setPosY(y);
                setPosX(x);
                GameLogic.getInstance().setCurrentDesc(current + "moved from " + oldX + " " + oldY + " to " + getPosX() + " " + getPosY());
            } else {
                // attack
                if (GameUtil.attack(current, GameLogic.getInstance().getChessPieceAt(x, y), x, y)) {
                    // successfully attacked
                    RenderableHolder.captureChess.play();
                    setPosY(y);
                    setPosX(x);
                    GameLogic.getInstance().setCurrentDesc(current + "from " + oldX + " " + oldY + "killed an enemy at " + getPosX() + " " + getPosY());
                } else {
                    // failed to attack, killed
                    RenderableHolder.captureFailed.play();
                    GameLogic.getInstance().setChessPieceAt(oldX, oldY, null);
                    setPosX(-1);  // Restore the original X position
                    setPosY(-1);  // Restore the original Y position
                    GameLogic.getInstance().setCurrentDesc(current + "from" + oldX + oldY + "failed to kill, got revenged to death.");
                }
            }
            return true;
        }
        return false;
    }



    /*this.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            System.exit(0);
        }
    });*/

    public ArrayList<ChessPosition> getPossibleMoves() {
        setCurrentAllPossibleMoves();
        return possibleMoves;
    }
}

