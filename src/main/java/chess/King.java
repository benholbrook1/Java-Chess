package chess;

import java.util.ArrayList;

public class King extends Piece{

    public King(int x, int y, PieceColour p){
        super(x, y, PieceType.KING, p);
    }

    public ArrayList<Move> getLegalMoves(Board gameBoard){

        ArrayList<Move> moves = new ArrayList<Move>();

        // TO DO!

        return moves;
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
