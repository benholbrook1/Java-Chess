package ui;

import javax.swing.JFrame;

public class GUI extends JFrame {

    private ChessScreen gameScreen;


    public GUI() {

        setTitle("Java Chess Engine");
        setSize(720, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameScreen = new ChessScreen();

        add(gameScreen);

    }


    public static void main(String[] args) {
        GUI chessboardGUI = new GUI();
        chessboardGUI.setVisible(true);
    }
}
