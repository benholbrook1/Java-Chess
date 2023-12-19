package chess;

import java.util.ArrayList;

public class Rook extends Piece{

    //private boolean hasMoved = false;

    public Rook(int x, int y, PieceColour p){
        super(x, y, PieceType.ROOK, p);
    }
    
    public ArrayList<Move> getLegalMoves(Board gameBoard, PieceColour currentColour){

        ArrayList<Move> moves = new ArrayList<>();

        if (getColour() != currentColour){ // if it is not this pieces turn there are no legal moves
            return moves;
        }

        moves = getDirectionalMoves(gameBoard, 0, 1);
        moves.addAll(getDirectionalMoves(gameBoard, 0, -1));
        moves.addAll(getDirectionalMoves(gameBoard, 1, 0));
        moves.addAll(getDirectionalMoves(gameBoard, -1, 0));

        return moves;
    }

    // @Override 
    // public void setHasMoved(boolean hasMoved){
    //     this.hasMoved = hasMoved;
    // }

    @Override
    public String toString(){
        if (getColour() == PieceColour.WHITE){
            return "r";
        } else {
            return "R";
        }
    }
}
