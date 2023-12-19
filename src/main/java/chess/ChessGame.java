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
            board.movePiece(move.getStartX(), move.getStartY(), move.getEndX(), move.getEndY());
            switchCurrentColour();
        } else {
            throw new Exception();
        }

    }

    @Override
    public String toString(){
        return board.toString();
    }

}