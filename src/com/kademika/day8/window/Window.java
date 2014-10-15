package com.kademika.day8.window;

import javax.swing.*;
import java.awt.*;

/**
 * Created by kurakinaleksandr on 01.07.14.
 */
public class Window {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setLocation(300, 100);
        f.setMinimumSize(new Dimension(800, 600));
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
