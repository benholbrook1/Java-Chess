package chess;

import java.util.ArrayList;

public class Knight extends Piece{

    public Knight(int x, int y, PieceColour p){
        super(x, y, PieceType.KNIGHT, p);
    }

    public ArrayList<Move> getLegalMoves(Board gameBoard){

        ArrayList<Move> moves = new ArrayList<Move>();

        if(getX() + 1 < 8 && getY() - 2 >= 0) moves.add(new Move(getX(), getY(), getX() + 1, getY() - 2));
        if(getX() - 1 >= 0 && getY() - 2 >= 0) moves.add(new Move(getX(), getY(), getX() - 1, getY() - 2));
        if(getX() + 1 < 8 && getY() + 2 < 8) moves.add(new Move(getX(), getY(), getX() + 1, getY() + 2));
        if(getX() - 1 >= 0 && getY() + 2 < 8) moves.add(new Move(getX(), getY(), getX() - 1, getY() + 2));
        if(getX() + 2 < 8 && getY() - 1 >= 0) moves.add(new Move(getX(), getY(), getX() + 2, getY() - 1));
        if(getX() + 2 < 8 && getY() + 1 < 8) moves.add(new Move(getX(), getY(), getX() + 2, getY() + 1));
        if(getX() - 2 >= 0 && getY() - 1 >= 0) moves.add(new Move(getX(), getY(), getX() - 2, getY() - 1));
        if(getX() - 2 >= 0 && getY() + 1 < 8) moves.add(new Move(getX(), getY(), getX() - 2, getY() + 1));

        return moves;
    }
    

    @Override
    public String toString(){
        if (getColour() == PieceColour.WHITE){
            return "n";
        } else {
            return "N";
        }
    }
}
