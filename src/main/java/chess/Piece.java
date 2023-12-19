package chess;

import java.util.ArrayList;

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

    public void setPosition(int x, int y){
        this.xPos = x;
        this.yPos = y;
    }

    // package private methods

    ArrayList<Move> getDirectionalMoves(Board gameBoard, int xDir, int yDir){

        ArrayList<Move> directionMoves = new ArrayList<>();

        int x = getX() + xDir;
        int y = getY() + yDir;

        while(x >= 0 && x < 8 && y >= 0 && y < 8){

            if (gameBoard.squareIsEmpty(x, y)){
                directionMoves.add(new Move(getX(), getY(), x, y));
            } else {
                //add the move into the piece we collided with if it is not your colour (a capture)
                if (gameBoard.getColourAtSquare(x, y) != getColour()){
                    directionMoves.add(new Move(getX(), getY(), x, y, true));
                }
                return directionMoves;
            }
            x += xDir;
            y += yDir;
        }
        return directionMoves;
    }
    
    public abstract ArrayList<Move> getLegalMoves(Board gameBoard);



}