package chess;

public class Knight extends Piece{

    public Knight(int x, int y, PieceColour p){
        super(x, y, PieceType.KNIGHT, p);
    }
    

    @Override
    public String toString(){
        if (getColour() == PieceColour.WHITE){
            return "n";
        } else {
            return "N";
        }
    }
}
