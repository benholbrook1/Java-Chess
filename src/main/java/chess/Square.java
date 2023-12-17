package chess;

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

    @Override
    public String toString(){
        
        String returnString;

        if (doesContainPiece()){
            returnString = piece.toString();
        } else {
            returnString = " ";
        }        

        return returnString;
    }
    
}
