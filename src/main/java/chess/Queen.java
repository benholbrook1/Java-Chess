package chess;

public class Queen extends Piece{

    public Queen(int x, int y, PieceColour p){
        super(x, y, PieceType.QUEEN, p);
    }
    
    @Override
    public String toString(){
        if (getColour() == PieceColour.WHITE){
            return "q";
        } else {
            return "Q";
        }
    }
}
