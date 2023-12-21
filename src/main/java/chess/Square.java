package chess;

import java.util.ArrayList;

public class Square {

    private int xPos;
    private int yPos;
    private boolean containsPiece;
    private Piece piece;


    public Square(int x, int y, Piece givenPiece){
        xPos = x;
        yPos = y;
        piece = givenPiece;
        containsPiece = true;

    }

    public Square(int x, int y){   
        xPos = x;
        yPos = y;
        piece = null;
        containsPiece = false;
    }

    // private methods

    public void setContainsPiece(boolean givenContainsPiece) {
        this.containsPiece = givenContainsPiece;
    }

    public void setPiece(Piece givenPiece) {
        this.piece = givenPiece;
    }

    // public methods

    public int getX(){
        return xPos;
    }

    public int getY(){
        return yPos;
    }

    public Boolean doesContainPiece(){
        return containsPiece;
    }

    public Piece getPiece(){
        return piece;
    }

    public PieceColour getPieceColour(){
        return piece.getColour();
    }

    public boolean moveIsLegal(Move move, Board gameBoard, PieceColour colour){

        ArrayList<Move> legalMoves = piece.getLegalMoves(gameBoard, colour);

        if (legalMoves.contains(move)){
            return true;
        }
        return false;
    }

    public static int[] getSquareWithCoords(String coordString) throws Exception{

        if (coordString.length() != 2){
            throw new Exception("Error, invalid coodinate given");
        }

        int y = Character.getNumericValue(coordString.charAt(1) - 1); 
        int x = 0;

        char c = coordString.charAt(0);
        c = Character.toLowerCase(c);

        x = c - 97;
        
        return new int[]{x,y};
    }

    public boolean doesContainOwnPiece(PieceColour ownColour){
        return containsPiece && getPieceColour() == ownColour;
    }

    @Override
    public String toString(){
        
        String returnString;

        if (containsPiece){
            returnString = piece.toString();
        } else {
            returnString = " ";
            if (containsPiece){
                returnString = "*";
            }
        }        

        return returnString;
    }
    
}
