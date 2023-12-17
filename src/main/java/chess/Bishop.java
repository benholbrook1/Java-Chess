package chess;

public class Bishop extends Piece{

    public Bishop(int x, int y, PieceColour p){
        super(x, y, PieceType.BISHOP, p);
    }
    

    @Override
    public String toString(){
        if (getColour() == PieceColour.WHITE){
            return "b";
        } else {
            return "B";
        }
    }
}
