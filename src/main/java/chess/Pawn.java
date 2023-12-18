package chess;

import java.util.ArrayList;

public class Pawn extends Piece{

    private boolean hasMoved = false;

    public Pawn(int x, int y, PieceColour c){
        super(x, y, PieceType.PAWN, c);
    }

    // private methods

    private ArrayList<Move> generateMoves(Board gameBoard, int direction){

        ArrayList<Move> moves = new ArrayList<Move>();

        if (!gameBoard.getSquare(getX(), getY() +(1*direction)).doesContainPiece()){
            moves.add(new Move(getX(), getY(), getX(), getY()+(1*direction))); // single move ahead
        }
        if (!hasMoved){
            moves.add(new Move(getX(), getY(), getX(), getY()+(2*direction)));
        }
        return moves;
    }

    public ArrayList<Move> getLegalMoves(Board gameBoard){

        ArrayList<Move> moves;

        Piece pieceToMove = gameBoard.getSquare(getX(), getY()).getPiece();
        
        if (pieceToMove.getColour() == PieceColour.WHITE){
            moves = generateMoves(gameBoard, 1);
        } else { // piece is black
            moves = generateMoves(gameBoard, -1);
        }

        // TODO: Captures and En-Passent

        return moves;
    }

    @Override
    public String toString(){
        if (getColour() == PieceColour.WHITE){
            return "p";
        } else {
            return "P";
        }
    }
}
