package chess;

import java.util.ArrayList;

public class Rook extends Piece{

    public Rook(int x, int y, PieceColour p){
        super(x, y, PieceType.ROOK, p);
    }
    
    public ArrayList<Move> getLegalMoves(Board gameBoard){

        ArrayList<Move> moves = new ArrayList<Move>();

        int x = getX();
        int y = getY();

        

        return moves;
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
