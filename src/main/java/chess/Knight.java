package chess;

import java.util.ArrayList;

public class Knight extends Piece{

    public Knight(int x, int y, PieceColour p){
        super(x, y, PieceType.KNIGHT, p);
    }

    public ArrayList<Move> getLegalMoves(Board gameBoard){

        ArrayList<Move> moves = new ArrayList<Move>();

        // TO DO!

        return moves;
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
