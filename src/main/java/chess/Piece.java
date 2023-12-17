package chess;

public abstract class Piece {

    private int xPos;
    private int yPos;
    private PieceType type;
    private PieceColour colour;


    public Piece(int x, int y, PieceType givenType, PieceColour givenColour){
        this.xPos = x;
        this.yPos = y;
        this.type = givenType;
        this.colour = givenColour;
    }

    // private methods
    void setX(int givenX){
        xPos = givenX;
    }

    void setY(int givenY){
        yPos = givenY;
    }
    
    void setType(PieceType givenType){
        type = givenType;
    }

    void setColour(PieceColour givenColour){
        colour = givenColour;
    }

    // public methods

    public int getX(){
        return xPos;
    }

    public int getY(){
        return yPos;
    }

    public PieceType getType(){
        return type;
    }

    public PieceColour getColour(){
        return colour;
    }

    public boolean movePiece(int startX, int startY, int endX, int endY){

        // check if move is legal

        return true;
    }

    //public abstract boolean movePiece(int startSquare, int endSquare);
    
    // public abstract ?? getLegalMoves();



}