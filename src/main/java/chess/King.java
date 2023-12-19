package chess;

import java.util.ArrayList;
import java.util.Objects;

public class King extends Piece{

    //private boolean isInCheck;

    public King(int x, int y, PieceColour p){
        super(x, y, PieceType.KING, p);
        //this.isInCheck = false;
    }

    private Move checkMoveAtSquare(int targetX, int targetY, Board gameBoard){

        if (targetX >= 0 && targetX < 8 && targetY >= 0 && targetY < 8){
            if(!gameBoard.getSquare(targetX, targetY).doesContainPiece()){
                return new Move(getX(), getY(), targetX, targetY);
            } else {
                PieceColour targetPieceColour = gameBoard.getSquare(targetX, targetY).getPieceColour();
                if (targetPieceColour != getColour()){
                    return new Move(getX(), getY(), targetX, targetY);
                }
            }
        }
        return null;
    }

    public ArrayList<Move> getLegalMoves(Board gameBoard, PieceColour currentColour){

        ArrayList<Move> moves = new ArrayList<Move>();

        if (getColour() != currentColour){ // if it is not this pieces turn there are no legal moves
            return moves;
        }

        int x = getX();
        int y = getY();

        moves.add(checkMoveAtSquare(x + 1, y + 1, gameBoard));     
        moves.add(checkMoveAtSquare(x + 1, y, gameBoard));  
        moves.add(checkMoveAtSquare(x + 1, y - 1, gameBoard));  
        moves.add(checkMoveAtSquare(x - 1, y + 1, gameBoard));  
        moves.add(checkMoveAtSquare(x - 1, y, gameBoard));  
        moves.add(checkMoveAtSquare(x - 1, y - 1, gameBoard));  
        moves.add(checkMoveAtSquare(x, y + 1, gameBoard));  
        moves.add(checkMoveAtSquare(x, y - 1, gameBoard));
        moves.removeIf(Objects::isNull); // clean up any of the return values as null
        
        for(Move move: moves){
            System.out.println(move.toString());
        }

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
