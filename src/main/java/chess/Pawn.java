package chess;

import java.util.ArrayList;

public class Pawn extends Piece{

    private boolean canBeCapturedEnpassent;

    public Pawn(int x, int y, PieceColour c){
        super(x, y, PieceType.PAWN, c);
        canBeCapturedEnpassent = false;
    }

    // private methods

    private boolean nextTwoSquaresAreEmpty(Board gameBoard, int direction){
        return !gameBoard.getSquare(getX(), getY() +(1*direction)).doesContainPiece() && !gameBoard.getSquare(getX(), getY() +(2*direction)).doesContainPiece();
    }

    private ArrayList<Move> generateMoves(Board gameBoard, int direction){

        ArrayList<Move> moves = new ArrayList<Move>();

        // Single Move Ahead
        if (!gameBoard.getSquare(getX(), getY() +(1*direction)).doesContainPiece()){
            moves.add(new Move(getX(), getY(), getX(), getY()+(1*direction)));
        }
        // Double Initial Move
        if (!getHasMoved()){
            if (nextTwoSquaresAreEmpty(gameBoard, direction)){
                moves.add(new Move(getX(), getY(), getX(), getY()+(2*direction)));
            }
        }
        // Left Captures (Regular and Empassent)
        moves.addAll(addCaptures(gameBoard, direction, -1));
        // Right Captures (Regular and Empassent)
        moves.addAll(addCaptures(gameBoard, direction, 1));

        return moves;
    }

    private ArrayList<Move> addCaptures(Board gameBoard, int yDir, int xDir){

        ArrayList<Move> movesToAdd = new ArrayList<>();

        if (getX() + xDir >= 0 && getX() + xDir < 8){

            // Regular Captures
            Square attackedSquare = gameBoard.getSquare(getX() + xDir, getY() + (1*yDir));
            if (attackedSquare.doesContainPiece() && attackedSquare.getPieceColour() != getColour()){
                movesToAdd.add(new Move(getX(), getY(), getX() + xDir, getY() + (1 * yDir)));
            }

            // Enpassent
            Square sideSquare = gameBoard.getSquare(getX() + xDir, getY());
            if(sideSquare.doesContainPiece() && sideSquare.getPiece().getType() == PieceType.PAWN){
                if (sideSquare.getPiece().canBeCapturedEnpassent()){
                    System.out.printf("Pawn at %d,%d can be captured enpassent\n", sideSquare.getX(), sideSquare.getY());
                    movesToAdd.add(new Move(getX(), getY(), getX() + xDir, getY() + (1 * yDir), true));
                }
            }
        }
        return movesToAdd;
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

    public boolean canBeCapturedEnpassent(){
        return canBeCapturedEnpassent;
    }

    @Override
    public void setCanBeCapturedEnpassent(boolean canBeCapturedEnpassent){
        this.canBeCapturedEnpassent = canBeCapturedEnpassent;
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
