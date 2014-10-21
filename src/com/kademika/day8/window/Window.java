package com.kademika.day8.window;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;


public class Window extends JPanel {
    private final static String FILE_NAME = "Tank_mouse.png";
    private Image iTank;

    public Window() {
        try{
            iTank = ImageIO.read(new File(FILE_NAME));
        }catch (IOException e){
            System.err.println("Can not find image: " + FILE_NAME);
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 32));
        g.drawString("Hello, tank!", 50, 50);
        g.drawImage(iTank, 100, 100, new ImageObserver(){

            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                return false;
            }
        });
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("I draw text!!");
        System.out.println(new File(FILE_NAME).getAbsolutePath());

        f.setLocation(300, 100);
        f.setMinimumSize(new Dimension(800, 600));

        f.getContentPane().add(new Window());

        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

    }
}
