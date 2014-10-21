package com.kademika.day12.balls;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.util.*;
import java.util.List;

/**
 * Created by kurakinaleksandr on 12.09.14.
 */
public class Balls extends JPanel {
    public static void main(String[] args) {
        new Balls();
    }

    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;
    private static final Color[] COLORS = new Color[]{
            Color.RED,
            Color.ORANGE,
            Color.YELLOW,
            Color.GREEN,
            new Color(30, 144, 255),
            Color.BLUE,
            new Color(123, 104, 238),
            Color.WHITE,
            Color.BLACK
    };

    private List<Ball> balls;

    public Balls() {
        JFrame frame = new JFrame("Balls");
        frame.setLocation(450, 150);
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack(); // do not fit in full size, why??
        frame.setVisible(true);

        createBalls();
        action();
    }

    private void action() {
        for (Ball ball : balls) {
            BallsMoveThread ballsMoveThread = new BallsMoveThread();
            ballsMoveThread.setBall(ball);
            new Thread(ballsMoveThread).start();
        }

        try {
            while (true) {
                Thread.sleep(60 / 1000);
                repaint();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void createBalls() {
        balls = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            int j = new Random().nextInt(9);
            Ball ball = new Ball();
            ball.setColor(COLORS[j]);
            balls.add(ball);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        for (Ball ball : balls) {
            ball.draw(g);
        }
    }

}
