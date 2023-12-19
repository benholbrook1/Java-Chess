package chess;

import java.util.ArrayList;

public class Rook extends Piece{

    public Rook(int x, int y, PieceColour p){
        super(x, y, PieceType.ROOK, p);
    }
    
    public ArrayList<Move> getLegalMoves(Board gameBoard){

        ArrayList<Move> moves = new ArrayList<>();

        moves = getDirectionalMoves(gameBoard, 0, 1);
        moves.addAll(getDirectionalMoves(gameBoard, 0, -1));
        moves.addAll(getDirectionalMoves(gameBoard, 1, 0));
        moves.addAll(getDirectionalMoves(gameBoard, -1, 0));

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
