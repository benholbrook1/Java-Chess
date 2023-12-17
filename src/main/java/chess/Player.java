package chess;

public class Player{

    // Instance Variables

    private int colour;

    // Construnctors

    public Player(){
        System.out.println("Error, player initialized without a colour!");
    }

    public Player(int givenColour){
        colour = givenColour;
    }

    // private methods

    void setColour(int givenColour){
        colour = givenColour;
    }

    // public methods

    public int getColour(){
        return colour;
    }

}