package chess;

public class Rook extends Piece{

    public Rook(int x, int y, PieceColour p){
        super(x, y, PieceType.ROOK, p);
    }
    

    @Override
    public String toString(){
        if (getColour() == PieceColour.WHITE){
            return "r";
        } else {
            return "R";
        }
    }
}
