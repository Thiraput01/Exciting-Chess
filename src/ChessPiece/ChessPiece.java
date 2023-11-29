package ChessPiece;

public abstract class ChessPiece implements Movable {
    protected ChessPosition currentPosition;
    private int weight;
    private String pieceUrl;
    private boolean whiteTeam; //white is true black is false

    public ChessPiece(int x, int y, boolean t) {
        setTeam(t);
        currentPosition = new ChessPosition(x,y);
    }


    public boolean isWhite() {
        return whiteTeam;
    }


    public void setTeam(boolean team) {
        this.whiteTeam = team;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }

    public String getPieceUrl() {
        return pieceUrl;
    }

    public void setPieceUrl(String pieceUrl) {
        this.pieceUrl = pieceUrl;
    }


    public String toString() {
        if (isWhite()) {
            if (this instanceof Pawn) {
                return "White Pawn at " + getChessNotation(currentPosition);
            } else if (this instanceof Knight) {
                return "White Knight at " + getChessNotation(currentPosition);
            } else if (this instanceof Rook) {
                return "White Rook at " + getChessNotation(currentPosition);
            } else if (this instanceof Queen) {
                return "White Queen at " + getChessNotation(currentPosition);
            } else if (this instanceof King) {
                return "White King at " + getChessNotation(currentPosition);
            }
        } else {
            // Similar blocks for black pieces excluding Bishop
            if (this instanceof Pawn) {
                return "Black Pawn at " + getChessNotation(currentPosition);
            } else if (this instanceof Knight) {
                return "Black Knight at " + getChessNotation(currentPosition);
            } else if (this instanceof Rook) {
                return "Black Rook at " + getChessNotation(currentPosition);
            } else if (this instanceof Queen) {
                return "Black Queen at " + getChessNotation(currentPosition);
            } else if (this instanceof King) {
                return "Black King at " + getChessNotation(currentPosition);
            }
        }
        return "Invalid" ;
    }

    private String getChessNotation(ChessPosition currentPosition) {
        char file = (char) ('A' + currentPosition.getX() - 1);
        char rank = (char) ('1' + currentPosition.getY() - 1);
        return String.valueOf(file) + rank;
    }



}

