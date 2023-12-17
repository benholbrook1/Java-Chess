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

    // public methods
    public String getMove(){
        String move = "";
        System.out.printf("%s's turn, enter your move: ", game.getCurrentColour());
        move = sc.next();
        
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

        String move = "";
        while(!move.equals("q")){
            
            ui.printGame();
            move = ui.getMove();
            try{
                ui.makeMove(move);
            } catch (Exception ex){
                if (!move.equals("q")){
                    System.out.println("Invalid Move!");
                }
            }
            ui.clearScreen();
        }


    }
}