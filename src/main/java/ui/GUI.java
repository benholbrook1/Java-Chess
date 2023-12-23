package ui;

import chess.ChessGame;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI extends JFrame {

    private JPanel[][] squares = new JPanel[8][8];
    private ChessGame game = new ChessGame();

    // Piece Labels

    public GUI() {
        super("Chessboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        setupChessboard();
        addPieces();
    }

    private void setupChessboard() {
        GridLayout gridLayout = new GridLayout(8, 8);
        gridLayout.setHgap(0);
        gridLayout.setVgap(0);
        setLayout(gridLayout);

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                squares[row][col] = new JPanel();
                squares[row][col].setPreferredSize(new Dimension(50, 50));

                if ((row + col) % 2 == 0) {
                    squares[row][col].setBackground(new Color(240, 217, 181));
                } else {
                    squares[row][col].setBackground(new Color(181, 136, 99));
                }

                add(squares[row][col]);
            }
        }
    }

    private BufferedImage scaleImage(BufferedImage image, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        double scaleX = (double) width / image.getWidth();
        double scaleY = (double) height / image.getHeight();

        AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX, scaleY);

        AffineTransformOp op = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);
        op.filter(image, scaledImage);

        return scaledImage;
    }

    private JLabel createPieceLabel(char piece) {
        BufferedImage pieceImage = null;
    
        switch (piece) {
            // White pieces
            case 'p':
                pieceImage = scaleImage(loadImage("include/wpawn.png"), 50, 50);
                break;
            case 'r':
                pieceImage = scaleImage(loadImage("include/wrook.png"), 50, 50);
                break;
            case 'n':
                pieceImage = scaleImage(loadImage("include/wknight.png"), 50, 50);
                break;
            case 'b':
                pieceImage = scaleImage(loadImage("include/wbishop.png"), 50, 50);
                break;
            case 'q':
                pieceImage = scaleImage(loadImage("include/wqueen.png"), 50, 50);
                break;
            case 'k':
                pieceImage = scaleImage(loadImage("include/wking.png"), 50, 50);
                break;
            // Black pieces
            case 'P':
                pieceImage = scaleImage(loadImage("include/bpawn.png"), 50, 50);
                break;
            case 'R':
                pieceImage = scaleImage(loadImage("include/brook.png"), 50, 50);
                break;
            case 'N':
                pieceImage = scaleImage(loadImage("include/bknight.png"), 50, 50);
                break;
            case 'B':
                pieceImage = scaleImage(loadImage("include/bbishop.png"), 50, 50);
                break;
            case 'Q':
                pieceImage = scaleImage(loadImage("include/bqueen.png"), 50, 50);
                break;
            case 'K':
                pieceImage = scaleImage(loadImage("include/bking.png"), 50, 50);
                break;
            default:
                // Handle unknown pieces or empty squares
                break;
        }
    
        return new JLabel(new ImageIcon(pieceImage));
    }
    

    private BufferedImage loadImage(String filePath) {
        try {
            return ImageIO.read(new File(filePath));
        } catch (IOException ex) {
            System.out.println("Error, couldn't open the image file: " + filePath);
            return null;
        }
    }

    private void addPieces() {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                char piece = game.getPieceAt(x, y);
    
                if (piece != ' ') {
                    JLabel pieceLabel = createPieceLabel(piece);
                    squares[7 - y][x].setLayout(null);  // Set layout manager to null for precise control
                    pieceLabel.setBounds(0, -2, 50, 50);  // Set bounds to the size of the panel
                    squares[7 - y][x].add(pieceLabel);
                }
            }
        }
    }
    

    public static void main(String[] args) {
        GUI chessboardGUI = new GUI();
        chessboardGUI.setVisible(true);
    }
}
