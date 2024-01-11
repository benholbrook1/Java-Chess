package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import chess.ChessGame;

public class ChessScreen extends JPanel implements MouseMotionListener, MouseListener{

    // Constants
    static int tileSize = 80;
    static int xOffset = +1;
    static int yOffset = -4;

    static int xImageOffset = 40;
    static int yImageOffset = 40;


    // Private Variables
    private ChessGame game;

    private int xPos = -1;
    private int yPos = -1;
    private int endX;
    private int endY;
    private int mouseX;
    private int mouseY;


    // Piece Images *loaded in the loadPieces() function*
    private Image wPawn;
    private Image bPawn;
    private Image wRook;
    private Image bRook;
    private Image wBishop;
    private Image bBishop;
    private Image wKnight;
    private Image bKnight;
    private Image wQueen;
    private Image bQueen;
    private Image wKing;
    private Image bKing;
    

    public ChessScreen(){
        game = new ChessGame();
        loadPieces();
    }

    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        this.setBackground(Color.BLUE); // Paint the default background as blue (mainly for debugging to test if it is showing)

        this.addMouseMotionListener(this);
        this.addMouseListener(this);

        drawBoard(g);
        drawPieces(g);
    }

    private void drawBoard(Graphics g){

        for(int y = 0; y < 8; y++){
            for(int x = 0; x < 8; x++){

                if ((x + y) % 2 == 1){
                    g.setColor(new Color(118,150,86));
                } else {
                    g.setColor(new Color(238,238,210));
                }

                g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
            }
        }
    }

    private void drawPieces(Graphics g){

        for(int y = 0; y < 8; y++){
            for(int x = 0; x < 8; x++){

                Image imageToDraw = null; 

                switch(game.getPieceAt(x, y)) {

                    case 'P':
                        imageToDraw = bPawn;
                        break;
                    case 'p':
                        imageToDraw = wPawn;
                        break;
                    case 'R':
                        imageToDraw = bRook;
                        break;
                    case 'r':
                        imageToDraw = wRook;
                        break;
                    case 'N':
                        imageToDraw = bKnight;
                        break;
                    case 'n':
                        imageToDraw = wKnight;
                        break;
                    case 'B':
                        imageToDraw = bBishop;
                        break;
                    case 'b':
                        imageToDraw = wBishop;
                        break;
                    case 'Q':
                        imageToDraw = bQueen;
                        break;
                    case 'q':
                        imageToDraw = wQueen;
                        break;
                    case 'K':
                        imageToDraw = bKing;
                        break;
                    case 'k':
                        imageToDraw = wKing;
                        break;
                }

                if (imageToDraw != null){
                    if (xPos != -1 && xPos / tileSize == x && yPos != -1 && yPos / tileSize == (7 - y)){
                        g.drawImage(imageToDraw, mouseX + xOffset, mouseY + yOffset, this);

                    } else {
                        g.drawImage(imageToDraw, x * tileSize + xOffset, (7 - y) * tileSize + yOffset, this);
                    }
                }
            }
        }
    }

    private void loadPieces(){

        wPawn = new ImageIcon("include/wPawn.png").getImage().getScaledInstance(tileSize - 2, tileSize - 2, Image.SCALE_SMOOTH); //scale the peices to smaller than the tiles;
        bPawn = new ImageIcon("include/bPawn.png").getImage().getScaledInstance(tileSize - 2, tileSize - 2, Image.SCALE_SMOOTH); //scale the peices to smaller than the tiles;

        wRook = new ImageIcon("include/wRook.png").getImage().getScaledInstance(tileSize - 2, tileSize - 2, Image.SCALE_SMOOTH); //scale the peices to smaller than the tiles;;
        bRook = new ImageIcon("include/bRook.png").getImage().getScaledInstance(tileSize - 2, tileSize - 2, Image.SCALE_SMOOTH); //scale the peices to smaller than the tiles;;

        wKnight = new ImageIcon("include/wKnight.png").getImage().getScaledInstance(tileSize - 2, tileSize - 2, Image.SCALE_SMOOTH); //scale the peices to smaller than the tiles;;
        bKnight = new ImageIcon("include/bKnight.png").getImage().getScaledInstance(tileSize - 2, tileSize - 2, Image.SCALE_SMOOTH); //scale the peices to smaller than the tiles;;

        wBishop = new ImageIcon("include/wBishop.png").getImage().getScaledInstance(tileSize - 2, tileSize - 2, Image.SCALE_SMOOTH); //scale the peices to smaller than the tiles;;
        bBishop = new ImageIcon("include/bBishop.png").getImage().getScaledInstance(tileSize - 2, tileSize - 2, Image.SCALE_SMOOTH); //scale the peices to smaller than the tiles;;

        wQueen = new ImageIcon("include/wQueen.png").getImage().getScaledInstance(tileSize - 2, tileSize - 2, Image.SCALE_SMOOTH); //scale the peices to smaller than the tiles;;
        bQueen = new ImageIcon("include/bQueen.png").getImage().getScaledInstance(tileSize - 2, tileSize - 2, Image.SCALE_SMOOTH); //scale the peices to smaller than the tiles;;

        wKing = new ImageIcon("include/wKing.png").getImage().getScaledInstance(tileSize - 2, tileSize - 2, Image.SCALE_SMOOTH); //scale the peices to smaller than the tiles;;
        bKing = new ImageIcon("include/bKing.png").getImage().getScaledInstance(tileSize - 2, tileSize - 2, Image.SCALE_SMOOTH); //scale the peices to smaller than the tiles;;

    }

    
    @Override
    public void mousePressed(MouseEvent e) {
        
        if (e.getX() < 8 * tileSize && e.getY() - 28 < 8*tileSize){

            xPos = e.getX();
            yPos = e.getY();

            mouseX = e.getX() - xImageOffset;
            mouseY = e.getY() - yImageOffset;

        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        endX = e.getX() / tileSize;
        endY =  7 - (e.getY() / tileSize);

        try{
            game.makeMove(xPos / tileSize, 7 - (yPos / tileSize), endX, endY);
        } catch (Exception ex){
        }
        xPos = -1;
        yPos = -1;

        repaint();
    }

    @Override 
    public void mouseDragged(MouseEvent e) {

        mouseX = e.getX() - xImageOffset;
        mouseY = e.getY() - yImageOffset;

        repaint();

    }

    // Unused, implemeneted to avoid errors
    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void mouseMoved(MouseEvent e) { }

    @Override
    public void mouseClicked(MouseEvent e) { }
    
}
