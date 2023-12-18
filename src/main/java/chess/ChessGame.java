package chess;

public class ChessGame{

    private Board board;
    private PieceColour currentColour;

    // Constructors
    public ChessGame(){
        newGame();
    }

    // private methods




    // public methods
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

        // LEGAL MOVE = start square,endsquare

        Move move = new Move(moveString);

        if (board.moveIsLegal(move)){
            board.movePiece(move.getStartX(), move.getStartY(), move.getEndX(), move.getEndY());
        } else {
            throw new Exception();
        }

    }

    @Override
    public String toString(){
        return board.toString();
    }

}