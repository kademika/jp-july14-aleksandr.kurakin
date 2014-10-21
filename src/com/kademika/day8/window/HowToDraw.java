package com.kademika.day8.window;

import javax.swing.*;
import java.awt.*;

/**
 * Created by kurakinaleksandr on 01.07.14.
 */
public class HowToDraw extends JPanel{

    static int x = 50;

    public HowToDraw(){
        JFrame frame = new JFrame("Day8 2d graphics");
        frame.setLocation(750, 150);
        frame.setMinimumSize(new Dimension(600,400));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);

//        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(x, 50, 10, 10);

        g.setColor(Color.GREEN);
        g.fillOval(100, 100, 10, 10);
    }

    public static void main(String[] args) throws Exception {
        HowToDraw htd = new HowToDraw();

        Thread.sleep(3000);

        x = 150;

        htd.repaint();

    }

}
