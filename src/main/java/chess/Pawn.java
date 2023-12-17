package chess;

public class Pawn extends Piece{

    public Pawn(int x, int y, PieceColour c){
        super(x, y, PieceType.PAWN, c);
    }

    @Override
    public String toString(){
        if (getColour() == PieceColour.WHITE){
            return "p";
        } else {
            return "P";
        }
    }
}
