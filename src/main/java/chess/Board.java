package chess;

public class Board implements Cloneable{

    public static final int BOARD_WIDTH = 8;
    public static final int BOARD_HEIGHT = 8;

    public static final String START_POS = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";

    private Square[][] board = new Square[8][8];

    // contstructors

    public Board(){
        newBoard();
        initPosition(START_POS);
        //initPosition("r3k2r/pppppppp/8/8/8/8/PPPPPPPP/R3K2R");
    }

    // private methods

    private char getCharFromPiece(Piece piece){

        char c = 'x'; // default dummy value 

        PieceType type = piece.getType();
        PieceColour colour = piece.getColour();

        switch (type) {
            case PAWN:
                c = 'p';
                break;
            case ROOK:
                c = 'r';
                break;
            case KNIGHT:
                c = 'n';
                break;
            case BISHOP:
                c = 'b';
                break;
            case KING:
                c = 'k';
                break;
            case QUEEN:
                c = 'q';
                break;
        }

        if (colour == PieceColour.BLACK){
            Character.toUpperCase(c);
        }

        return c;
    }

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

    private void resetEnpassents(){

        for(int i = 0; i < BOARD_WIDTH; i++){
            for(int j = 0; j < BOARD_WIDTH; j++){
                if(board[i][j].doesContainPiece() && board[i][j].getPiece().getType() == PieceType.PAWN){
                    board[i][j].getPiece().setCanBeCapturedEnpassent(false);
                }
            }
        }
    }

    private void updateIfEmpassent(int startX, int startY, int endX, Piece piece){

        if (piece.getType() == PieceType.PAWN){
            resetEnpassents(); // reset all enpassent statuses for the pawns
            if (Math.abs(startX - endX) == 2){
                piece.setCanBeCapturedEnpassent(true);
            }
            // If we just did an empassent move then we need to delete the captured pawn
            if (Math.abs(startX - endX) == 1 && board[endX][startY].doesContainPiece()){
                board[endX][startY].setPiece(null);
                board[endX][startY].setContainsPiece(false);
            }
        } else {
            resetEnpassents();
        }
    }

    private void updateIfCastle(int startX, int endX, int startY, int endY, Piece piece){

        // if the piece was a king and it just made a castle move we need to move the rook
        if (piece.getType() == PieceType.KING){ // if the piece is a king
            if (startX - endX == 2){ // if it was moved 2 squares to the left then its a castle move left
                // Update the position of the rook
                Piece rookToMove = board[0][startY].getPiece();

                board[0][startY].setPiece(null);
                board[0][startY].setContainsPiece(false);

                board[endX + 1][endY].setPiece(rookToMove);
                board[endX + 1][endY].setContainsPiece(true);
            } else if (startX - endX == -2){ // castle move right
                // Update the position of the rook
                Piece rookToMove = board[7][startY].getPiece();

                board[7][startY].setPiece(null);
                board[7][startY].setContainsPiece(false);

                board[endX - 1][endY].setPiece(rookToMove);
                board[endX - 1][endY].setContainsPiece(true);
            }
        }
    }

    // public methods

    public void movePiece(int startX, int startY, int endX, int endY){

        Piece piece = board[startX][startY].getPiece();

        board[startX][startY].setPiece(null);
        board[startX][startY].setContainsPiece(false);

        board[endX][endY].setPiece(piece);
        board[endX][endY].setContainsPiece(true);

        piece.setPosition(endX, endY);
        piece.setHasMoved(true);

        updateIfEmpassent(startX, startY, endX, piece);
        updateIfCastle(startX, endX, startY, endY, piece);
                
    }

    public Square[][] getBoard(){
        return board;
    }

    public Square getSquare(int x, int y){
        return board[x][y];
    }

    public Piece getPiece(int x, int y){
        
        if (getSquare(x, y).doesContainPiece()){
            return getSquare(x, y).getPiece();
        } else {
            return null;
        }
    }

    public PieceColour getColourAtSquare(int x, int y){

        return getSquare(x, y).getPieceColour();

    }

    public boolean squareIsEmpty(int x, int y){
        if(board[x][y].doesContainPiece()){
            return false;
        }
        return true;
    }

    public boolean moveIsLegal(Move move, PieceColour colour){

        return getSquare(move.getStartX(), move.getStartY()).moveIsLegal(move, this, colour);
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
                x += Character.getNumericValue(c);
            } else if (Character.isLetter(c)){
                addPiece(x,y,c);
                x++;
            }
        }
    }

    public String toFenString(){

        StringBuilder sb = new StringBuilder();

        for(int y = 0; y < 8; y++){

            if (y != 0) sb.append('/');
            
            int emptyCounter = 0;
            for(int x = 0; x < 8; x++){

                Square curSquare = getSquare(x,y);

                if (curSquare.doesContainPiece()){
                    if (emptyCounter != 0) sb.append(emptyCounter); // print the number of emptry squares before the piece
                    sb.append(getCharFromPiece(curSquare.getPiece()));
                } else {
                    emptyCounter++;
                    if (y == 7){
                        sb.append(emptyCounter);
                    }
                }
            }
        }
        return sb.toString();
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