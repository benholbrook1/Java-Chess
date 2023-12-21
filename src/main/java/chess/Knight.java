package chess;

import java.util.ArrayList;
import java.util.Objects;

public class Knight extends Piece{

    public Knight(int x, int y, PieceColour p){
        super(x, y, PieceType.KNIGHT, p);
    }

    private Move addKnightHop(int xOffset, int yOffset, Board gameBoard, PieceColour currentColour){

        if(getX() + xOffset < 8 && getX() + xOffset >= 0){
            if (getY() + yOffset < 8 && getY() + yOffset >= 0){
                if(!gameBoard.getSquare(getX() + xOffset, getY() + yOffset).doesContainOwnPiece(currentColour)){
                    return new Move(getX(), getY(), getX() + xOffset, getY() + yOffset);
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

        moves.add(addKnightHop(1,-2,gameBoard,currentColour));
        moves.add(addKnightHop(-1,-2,gameBoard,currentColour));
        moves.add(addKnightHop(1,2,gameBoard,currentColour));
        moves.add(addKnightHop(-1,2,gameBoard,currentColour));
        moves.add(addKnightHop(2,-1,gameBoard,currentColour));
        moves.add(addKnightHop(2,1,gameBoard,currentColour));
        moves.add(addKnightHop(-2,-1,gameBoard,currentColour));
        moves.add(addKnightHop(-2,1,gameBoard,currentColour));

        moves.removeIf(Objects::isNull); // clean up any of the return values as null

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
