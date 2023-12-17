package chess;

public class ChessGame{

    private Board board;
    private PieceColour currentColour;

    public ChessGame(){
        newGame();
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

    
    public void makeMove(String move){ // will probably have to change this when we are using the GUI

        for(int i = 0; i < move.length(); i++){
            
        }


    }

    @Override
    public String toString(){
        return board.toString();
    }
}