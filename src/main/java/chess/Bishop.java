package chess;

import java.util.ArrayList;

public class Bishop extends Piece{

    public Bishop(int x, int y, PieceColour p){
        super(x, y, PieceType.BISHOP, p);
    }

    public ArrayList<Move> getLegalMoves(Board gameBoard){

        ArrayList<Move> moves = new ArrayList<Move>();

        System.out.printf("Getting moves for bishop at [%d,%d]\n", getX(), getY());

        moves = getDirectionalMoves(gameBoard, 1, 1);
        moves.addAll(getDirectionalMoves(gameBoard, -1, -1));
        moves.addAll(getDirectionalMoves(gameBoard, 1, -1));
        moves.addAll(getDirectionalMoves(gameBoard, -1, 1));

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
