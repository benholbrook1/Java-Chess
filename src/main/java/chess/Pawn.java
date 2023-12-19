package chess;

import java.util.ArrayList;

public class Pawn extends Piece{

    private boolean hasMoved = false;
    private boolean canBeCapturedEmpassent = false;

    public Pawn(int x, int y, PieceColour c){
        super(x, y, PieceType.PAWN, c);
    }

    // private methods

    private ArrayList<Move> generateMoves(Board gameBoard, int direction){

        ArrayList<Move> moves = new ArrayList<Move>();

        // Single Move Ahead
        if (!gameBoard.getSquare(getX(), getY() +(1*direction)).doesContainPiece()){
            moves.add(new Move(getX(), getY(), getX(), getY()+(1*direction)));
        }
        // Double Initial Move
        if (!hasMoved){
            moves.add(new Move(getX(), getY(), getX(), getY()+(2*direction)));
            canBeCapturedEmpassent = true;
        }

        if (getX() + 1 < 8){
            Square rightAttackedSquare = gameBoard.getSquare(getX() + 1, getY() + (1*direction));
            // Right Capture
            if (rightAttackedSquare.doesContainPiece() && rightAttackedSquare.getPieceColour() != getColour()){
                moves.add(new Move(getX(), getY(), getX() + 1, getY() + (1 * direction)));
            }
            // Right Empassent Check
            Square rSquare = gameBoard.getSquare(getX() + 1, getY());
            if(rSquare.doesContainPiece() && rSquare.getPiece().getType() == PieceType.PAWN){
                if (rSquare.getPiece().canBeCapturedEmpassent()){
                    moves.add(new Move(getX(), getY(), getX() + 1, getY() + (1 * direction), true));
                }
            }
        }
        
        if (getX() - 1 < 8){
            Square leftAttackedSquare =  gameBoard.getSquare(getX() - 1, getY() + (1*direction));
            // Left Capture
            if (leftAttackedSquare.doesContainPiece() && leftAttackedSquare.getPieceColour() != getColour()){
                moves.add(new Move(getX(), getY(), getX() - 1, getY() + (1 * direction)));
            }
            // Left Empassent Check
            Square lSquare = gameBoard.getSquare(getX() - 1, getY());
            if(lSquare.doesContainPiece() && lSquare.getPiece().getType() == PieceType.PAWN){
                if (lSquare.getPiece().canBeCapturedEmpassent()){
                    moves.add(new Move(getX(), getY(), getX() - 1, getY() + (1 * direction), true));
                }
            }
        }


        // TODO:
        // --> REFACTOR THIS METHOD, IT IS WAYYY TOOO LOOONNNGGG
        // --> EMPASSENT CAPTURES ARE INFINIETLY AVAILABLE, WE NEED TO RESET THE STATUS



        return moves;
    }

    // public methods

    public ArrayList<Move> getLegalMoves(Board gameBoard, PieceColour currentColour){

        ArrayList<Move> moves = new ArrayList<>();

        Piece pieceToMove = gameBoard.getSquare(getX(), getY()).getPiece();

        if(pieceToMove.getColour() != currentColour){
            return moves;
        }
        
        if (pieceToMove.getColour() == PieceColour.WHITE){
            moves.addAll(generateMoves(gameBoard, 1));
        } else { // piece is black
            moves.addAll(generateMoves(gameBoard, -1));
        }

        return moves;
    }

    public boolean canBeCapturedEmpassent(){
        return canBeCapturedEmpassent;
    }

    @Override
    public void setCanBeCapturedEmpassent(boolean canBeCapturedEmpassent){
        this.canBeCapturedEmpassent = canBeCapturedEmpassent;
    }

    @Override
    public void setHasMoved(boolean hasMoved){
        this.hasMoved = hasMoved;
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
