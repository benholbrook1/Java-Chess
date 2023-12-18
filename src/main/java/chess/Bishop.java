package chess;

import java.util.ArrayList;

public class Bishop extends Piece{

    public Bishop(int x, int y, PieceColour p){
        super(x, y, PieceType.BISHOP, p);
    }

    public ArrayList<Move> getLegalMoves(Board gameBoard){

        ArrayList<Move> moves = new ArrayList<Move>();

        // TODO

        return moves;
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
