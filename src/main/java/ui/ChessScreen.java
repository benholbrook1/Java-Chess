package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class ChessScreen extends JPanel implements MouseMotionListener, MouseListener{


    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        this.setBackground(Color.BLACK);


    }

    
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO 
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override 
    public void mouseDragged(MouseEvent e) {

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
