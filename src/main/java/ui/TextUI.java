package ui;

import java.util.Scanner;

import chess.ChessGame;

public class TextUI {

    private ChessGame game;
    private Scanner sc = new Scanner(System.in);

    // contructors
    public TextUI(){
        game = new ChessGame();
    }

    // private methods

    private void validateMoveString(String moveString) throws Exception{

        if (moveString.equalsIgnoreCase("q")){
            return;
        }

        if (moveString.length() != 4){
            throw new Exception();
        }

        if (!Character.isLetter(moveString.charAt(0))){
            throw new Exception();
        } else if (!Character.isDigit(moveString.charAt(1))){
            throw new Exception();
        } else if (!Character.isLetter(moveString.charAt(2))){
            throw new Exception();
        } else if (!Character.isDigit(moveString.charAt(3))){
            throw new Exception();
        }

    }

    // public methods
    public String getMove(){
        String move = "";
        System.out.printf("%s's turn, enter your move: ", game.getCurrentColour());
        
        move = sc.next();
        while(!move.equalsIgnoreCase("q")){

            try {
                validateMoveString(move);
                break;
            } catch (Exception ex){
                System.out.println("\nInvalid Input. Try again.\n");
                System.out.printf("%s's turn, enter your move: ", game.getCurrentColour());
                move = sc.next();
            }
        }
        
        
        return move;
    }

    public void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public void printGame(){
        System.out.print(game.toString());
    }

    public void makeMove(String move) throws Exception{

        game.makeMove(move);

    }


    public static void main(String[] args) {

        TextUI ui = new TextUI();
        ui.clearScreen();

        String moveString = "";
        while(!moveString.equals("q")){
            //ui.clearScreen();
            ui.printGame();
            moveString = ui.getMove();
            try{
                ui.makeMove(moveString);
            } catch (Exception ex){
                if (!moveString.equals("q")){
                    System.out.println("Invalid Move!");
                }
            }
        }


    }
}