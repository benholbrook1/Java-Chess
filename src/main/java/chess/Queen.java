package chess;

import java.util.ArrayList;

public class Queen extends Piece{

    public Queen(int x, int y, PieceColour p){
        super(x, y, PieceType.QUEEN, p);
    }

    public ArrayList<Move> getLegalMoves(Board gameBoard, PieceColour currentColour){

        ArrayList<Move> moves = new ArrayList<Move>();

        if (getColour() != currentColour){ // if it is not this pieces turn there are no legal moves
            return moves;
        }

        moves = getDirectionalMoves(gameBoard, 1, 1);
        moves.addAll(getDirectionalMoves(gameBoard, -1, -1));
        moves.addAll(getDirectionalMoves(gameBoard, 1, -1));
        moves.addAll(getDirectionalMoves(gameBoard, -1, 1));

        moves.addAll(getDirectionalMoves(gameBoard, 0, 1));
        moves.addAll(getDirectionalMoves(gameBoard, 0, -1));
        moves.addAll(getDirectionalMoves(gameBoard, 1, 0));
        moves.addAll(getDirectionalMoves(gameBoard, -1, 0));

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
