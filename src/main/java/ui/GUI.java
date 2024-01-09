package ui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import chess.ChessGame;

public class GUI extends JFrame {

    private int BOARD_WIDTH = 720;

    private ChessGame game;
    private JPanel gameScreen;

    private JPanel[][] panels = new JPanel[8][8];

    public GUI() {

        game = new ChessGame();
        setTitle("Java Chess Engine");
        setSize(BOARD_WIDTH, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameScreen = new JPanel();
        gameScreen.setLayout(new GridLayout(8, 8));

        drawBoard();

        add(gameScreen);

    }

    private void drawBoard() {

        gameScreen.removeAll();

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {

                panels[x][y] = new JPanel();
                panels[x][y].setLayout(new FlowLayout());

                Color darkSquares = new Color(118, 150, 86);
                Color lightSquares = new Color(238, 238, 210);

                if ((x + y) % 2 == 0) {
                    panels[x][y].setBackground(darkSquares);
                } else {
                    panels[x][y].setBackground(lightSquares);
                }

                gameScreen.add(panels[x][y]);

                if (game.squareContainsPiece(x, y)) {
                    addPiece(x, y);
                }
            }
        }
    }

    private void addPiece(int x, int y) {

        char piece = game.getPieceAt(x, y);
        boolean isWhite = Character.isLowerCase(piece);

        piece = Character.toLowerCase(piece);

        switch (piece) {
            case 'p':
                JLabel pawnLabel;
                if (isWhite) {
                    pawnLabel = new JLabel(new ImageIcon("include/wPawn.png"));
                } else {
                    pawnLabel = new JLabel(new ImageIcon("include/bPawn.png"));
                }
                panels[x][y].add(pawnLabel);
                break;

            case 'r':
                JLabel rookLabel;
                if (isWhite) {
                    rookLabel = new JLabel(new ImageIcon("include/wRook.png"));
                } else {
                    rookLabel = new JLabel(new ImageIcon("include/bRook.png"));
                }
                panels[x][y].add(rookLabel);
                break;

            case 'b':
                JLabel bishopLabel;
                if (isWhite) {
                    bishopLabel = new JLabel(new ImageIcon("include/wBishop.png"));
                } else {
                    bishopLabel = new JLabel(new ImageIcon("include/bBishop.png"));
                }
                panels[x][y].add(bishopLabel);
                break;

            case 'n':
                JLabel knightLabel;
                if (isWhite) {
                    knightLabel = new JLabel(new ImageIcon("include/wKnight.png"));
                } else {
                    knightLabel = new JLabel(new ImageIcon("include/bKnight.png"));
                }
                panels[x][y].add(knightLabel);
                break;

            case 'k':
                JLabel kingLabel;
                if (isWhite) {
                    kingLabel = new JLabel(new ImageIcon("include/wKing.png"));
                } else {
                    kingLabel = new JLabel(new ImageIcon("include/bKing.png"));
                }
                panels[x][y].add(kingLabel);
                break;

            case 'q':
                JLabel queenLabel;
                if (isWhite) {
                    queenLabel = new JLabel(new ImageIcon("include/wQueen.png"));
                } else {
                    queenLabel = new JLabel(new ImageIcon("include/bQueen.png"));
                }
                panels[x][y].add(queenLabel);
                break;

            default:
                break;
        }

    }

    public static void main(String[] args) {
        GUI chessboardGUI = new GUI();
        chessboardGUI.setVisible(true);
    }
}
