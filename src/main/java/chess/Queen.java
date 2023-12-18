package chess;

import java.util.ArrayList;

public class Queen extends Piece{

    public Queen(int x, int y, PieceColour p){
        super(x, y, PieceType.QUEEN, p);
    }

    public ArrayList<Move> getLegalMoves(Board gameBoard){

        ArrayList<Move> moves = new ArrayList<Move>();

        // TO DO!

        return moves;
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
