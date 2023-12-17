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

    
    public void makeMove(String move) throws Exception{ // will probably have to change this when we are using the GUI

        // LEGAL MOVE = start square,endsquare

        String[] moves = move.split(",");  // i dont like how this is implemented right now, id like to do this cleaner

        if (moves.length < 2){
            throw new Exception("Invalid Move");
        }

        int[] firstSquare = Square.getSquareWithCoords(moves[0]);
        int[] secondSquare = Square.getSquareWithCoords(moves[1]);

        board.movePiece(firstSquare[0], firstSquare[1], secondSquare[0], secondSquare[1]);


    }

    @Override
    public String toString(){
        return board.toString();
    }

}