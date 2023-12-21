package chess;

import java.util.ArrayList;

public class ChessGame{

    private Board board;
    private PieceColour currentColour;

    // Constructors
    public ChessGame(){
        newGame();
    }

    // private methods

    private boolean isKingCapture(Move move, Square kingSquare) {
        return move.getEndX() == kingSquare.getX() && move.getEndY() == kingSquare.getY();
    }
    

    private Square getKingSquare(){

        System.out.println("Entered getKingSquare()");

        Square kingSquare = null;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                try{
                    if(board.getSquare(j, i) != null){
                        if(board.getSquare(i, j).getPiece().getType() == PieceType.KING && board.getSquare(i, j).getPiece().getColour() != currentColour){
                            kingSquare = board.getSquare(i, j);
                            System.out.printf("Got king square at [%d,%d]\n", i, j);
                        }
                    }
                } catch (Exception ex){
                    System.out.printf("Got exception on square at [%d,%d]\n", i, j);
                }
            }
        }
        return kingSquare;
    }



    // public methods

    public void switchCurrentColour(){
        if (currentColour == PieceColour.WHITE){
            currentColour = PieceColour.BLACK;
        } else {
            currentColour = PieceColour.WHITE;
        }
    }

    public void newGame(){

        board = new Board();
        currentColour = PieceColour.WHITE;

    }

    public String getCurrentColour(){
        if (currentColour == PieceColour.WHITE){
            return "White";
        } else {
            return "Black";
        }
    }

    
    public void makeMove(String moveString) throws Exception{ // GUI might need a different sort of method

        Move move = new Move(moveString);
        if (board.moveIsLegal(move, currentColour)){

            System.out.println("move is legal");

            // MAKE A COPY OF THE BOARD --> copy it to a fen string, save an pawns that can be captured enpassent and status of pawns, kings, and rooks
            //
            //
            //
            //
            board.movePiece(move.getStartX(), move.getStartY(), move.getEndX(), move.getEndY());
            switchCurrentColour();
            
            // generate all legal moves again, if that includes a capture of the king, then mark it as not legal and set the board equal to the previous copy
            ArrayList<Move> legalMoves = generateAllLegalMoves();

            System.out.println("Got all legal moves");

            Square kingSquare = getKingSquare();
            System.out.printf("Got King square at position [%d,%d]", kingSquare.getX(), kingSquare.getY());
            for(Move legalMove: legalMoves){
                if (isKingCapture(legalMove, kingSquare)){
                    System.out.println("Error, your king can be captured after that move!");
                    
                    // SET BOARD TO TEMP COPY OF OLD BOARD
                    //
                    //
                    //
                    //
                    //

                    switchCurrentColour(); // Switch Colour Back
                    throw new Exception();
                }
            }

        } else {
            throw new Exception();
        }
    }

    public ArrayList<Move> generateAllLegalMoves(){

        ArrayList<Move> legalMoves = new ArrayList<>();

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Piece piece = board.getPiece(i, j);
                if (piece != null){
                    if (piece.getColour() == currentColour){
                        legalMoves.addAll(piece.getLegalMoves(board, currentColour));
                    }
                }
            }
        }
        return legalMoves;
    }

    @Override
    public String toString(){
        return board.toString();
    }

}