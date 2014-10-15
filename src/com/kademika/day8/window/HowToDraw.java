package com.kademika.day8.window;

import javax.swing.*;
import java.awt.*;

/**
 * Created by kurakinaleksandr on 01.07.14.
 */
public class HowToDraw extends JPanel{

    public HowToDraw(){
        JFrame frame = new JFrame("Day8 2d graphics");
        frame.setLocation(750, 150);
        frame.setMinimumSize(new Dimension(600,400));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(50, 50, 500, 300);
    }

    public static void main(String[] args) {
        new HowToDraw();
    }

}
