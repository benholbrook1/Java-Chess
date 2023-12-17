package chess;

public class King extends Piece{

    public King(int x, int y, PieceColour p){
        super(x, y, PieceType.KING, p);
    }
    

    @Override
    public String toString(){
        if (getColour() == PieceColour.WHITE){
            return "k";
        } else {
            return "K";
        }
    }
}
