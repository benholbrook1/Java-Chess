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

        Square kingSquare = null;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                try{
                    if(board.getSquare(j, i) != null){
                        if(board.getSquare(i, j).getPiece().getType() == PieceType.KING && board.getSquare(i, j).getPiece().getColour() != currentColour){
                            kingSquare = board.getSquare(i, j);
                        }
                    }
                } catch (Exception ex){
                }
            }
        }
        return kingSquare;
    }

    private boolean checkIfOpponentIsInCheckmate(ArrayList<Move> opponentMoves){

        for(Move move: opponentMoves){

            String savedFen = board.toFenString();
            int[][] savedInfo = board.saveInfo();

            try {
                testOpponentsMove(move);

                // set our board back to before this move was played
                board.initPosition(savedFen);
                board.initInfo(savedInfo);
                switchCurrentColour();

                System.out.printf("Opponent is not in checkmate, found legal move: %s\n", move.toString());
                return false;


            } catch (Exception ex){
            }
        }
        return true;
    }


    // public methods

    public PieceColour getOpponentColour(){
        if (currentColour == PieceColour.WHITE){
            return PieceColour.BLACK;
        } else {
            return PieceColour.WHITE;
        }
    }

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

            // make a copy of the board
            String savedFen = board.toFenString();
            int[][] savedInfo = board.saveInfo(); // saves pawns that can be enpassent captured, and if a rook, king, or pawn has moved
            
        
            board.movePiece(move.getStartX(), move.getStartY(), move.getEndX(), move.getEndY());
            switchCurrentColour();
            
            // generate all of opponent legal moves, if that includes a capture of the king, then mark it as not legal and set the board equal to the previous copy
            ArrayList<Move> opponentLegalMoves = generateAllLegalMoves();
            Square kingSquare = getKingSquare();

            if (checkIfOpponentIsInCheckmate(opponentLegalMoves)){
                System.out.println("Checkmate! You win!");
                System.exit(0);
            }

        
            for(Move legalMove: opponentLegalMoves){

                

                if (isKingCapture(legalMove, kingSquare)){
                    
                    // set our board back to before this move was played
                    board.initPosition(savedFen);
                    board.initInfo(savedInfo);

                    switchCurrentColour(); // switch Colour Back
                    throw new Exception();
                }
            }

        } else {
            throw new Exception();
        }
    }

    private void testOpponentsMove(Move move) throws Exception{ // GUI might need a different sort of method

        if (board.moveIsLegal(move, currentColour)){

            // make a copy of the board
            String savedFen = board.toFenString();
            int[][] savedInfo = board.saveInfo(); // saves pawns that can be enpassent captured, and if a rook, king, or pawn has moved
            
        
            board.movePiece(move.getStartX(), move.getStartY(), move.getEndX(), move.getEndY());
            switchCurrentColour();
            
            // generate all of opponent legal moves, if that includes a capture of the king, then mark it as not legal and set the board equal to the previous copy
            ArrayList<Move> opponentLegalMoves = generateAllLegalMoves();
            Square kingSquare = getKingSquare();

        
            for(Move legalMove: opponentLegalMoves){

                if (isKingCapture(legalMove, kingSquare)){
                    
                    // set our board back to before this move was played
                    board.initPosition(savedFen);
                    board.initInfo(savedInfo);

                    switchCurrentColour(); // switch Colour Back
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