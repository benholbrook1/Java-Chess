package chess;

public class Board{

    public static final int BOARD_WIDTH = 8;
    public static final int BOARD_HEIGHT = 8;

    public static final String START_POS = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";

    private Square[][] board = new Square[8][8];

    // contstructors

    public Board(){
        newBoard();
        initPosition(START_POS);
    }

    // private methods

    private void addPiece(int xPos, int yPos, char givenChar){

        PieceColour colour;

        if (Character.isLowerCase(givenChar)){
            colour = PieceColour.WHITE;
        } else {
            colour = PieceColour.BLACK;
        }

        givenChar = Character.toLowerCase(givenChar);

        switch(givenChar) {
            case 'p':
                board[xPos][yPos] = new Square(xPos, yPos, new Pawn(xPos, yPos, colour));
                break;
            case 'r':
                board[xPos][yPos] = new Square(xPos, yPos, new Rook(xPos, yPos, colour));
                break;
            case 'b':
                board[xPos][yPos] = new Square(xPos, yPos, new Bishop(xPos, yPos, colour));
                break;
            case 'n':
                board[xPos][yPos] = new Square(xPos, yPos, new Knight(xPos, yPos, colour));
                break;
            case 'k':
                board[xPos][yPos] = new Square(xPos, yPos, new King(xPos, yPos, colour));
                break;
            case 'q':
                board[xPos][yPos] = new Square(xPos, yPos, new Queen(xPos, yPos, colour));
                break;
            default:
                System.out.println("Error, invalid piece type given to Board.addPiece()");
        }
    }

    // public methods

    public boolean movePiece(int startX, int startY, int endX, int endY){

        // check if the move is legal using the [][].getValidMove... method of the piece

        board[startX][startY].getPiece().setPosition(endX, endY);

        Square tempSquare = board[startX][startY];
        board[startX][startY] = board[endX][endY];
        board[endX][endY] = tempSquare;

        return true;
    }

    public Square[][] getBoard(){
        return board;
    }

    public Square getSquare(int x, int y){
        return board[x][y];
    }

    public boolean moveIsLegal(Move move){

        return getSquare(move.getStartX(), move.getStartY()).moveIsLegal(move, this);
    }

    public void newBoard(){
        
        for(int x = 0; x < BOARD_WIDTH; x++){
            for(int y = BOARD_HEIGHT - 1; y >= 0; y--){
                board[x][y] = new Square(x, y);
            }
        }
    }

    public void initPosition(String fenString){

        int x = 0;
        int y = 0;

        for (int i = 0; i < fenString.length(); i++){
            char c = fenString.charAt(i);
            if (c == '/'){
                y++;
                x = 0;
            } else if (Character.isDigit(c)){
                x += c;
            } else{
                addPiece(x,y,c);
                x++;
            }
        }
    }

    @Override
    public String toString(){

        StringBuilder sb = new StringBuilder();

        sb.append("   A B C D E F G H\n");
        sb.append("   ________________\n");
        for(int x = BOARD_WIDTH - 1; x >= 0; x--){
            sb.append(x + 1 + " ");
            for (int y = 0; y < BOARD_HEIGHT; y++){
                sb.append("|"+ board[y][x].toString());
            }
            sb.append("|\n");
        }
        sb.append("   ▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔\n");

        String returnString = sb.toString();
        return returnString;
    }
}