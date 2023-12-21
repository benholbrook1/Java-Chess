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

    private ArrayList<Move> checkCastleMoves(Board gameBoard){

        ArrayList<Move> castleMoves = new ArrayList<>();

        if(!getHasMoved()){ // if the king has not moved yet
            Piece leftCorner = gameBoard.getPiece(0, getY());
            if (leftCorner != null){ // if there is a piece in the left corner
                if (leftCorner.getType() == PieceType.ROOK && !leftCorner.getHasMoved()){ // if that piece is a rook and it hasn't moved
                    boolean squaresAreEmpty = true;
                    for(int i = getX() - 1; i >= 1; i--){ // check if the squares in between are empty
                        if (gameBoard.getSquare(i, getY()).doesContainPiece()){
                            squaresAreEmpty = false;
                        }
                    }
                    if (squaresAreEmpty){
                        castleMoves.add(new Move(getX(), getY(), getX() - 2, getY()));
                    }
                }
            }
            Piece rightCorner = gameBoard.getPiece(7, getY());
            if (rightCorner != null){ // if there is a piece in the left corner
                if (rightCorner.getType() == PieceType.ROOK && !rightCorner.getHasMoved()){ // if that piece is a rook and it hasn't moved
                    boolean squaresAreEmpty = true;
                    for(int i = getX() + 1; i < 7; i++){ // check if the squares in between are empty
                        if (gameBoard.getSquare(i, getY()).doesContainPiece()){
                            squaresAreEmpty = false;
                        }
                    }
                    if (squaresAreEmpty){
                        castleMoves.add(new Move(getX(), getY(), getX() + 2, getY()));
                    }
                }
            }   
        }
        return castleMoves;
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
        moves.addAll(checkCastleMoves(gameBoard));

        moves.removeIf(Objects::isNull); // clean up any of the return values as null

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
