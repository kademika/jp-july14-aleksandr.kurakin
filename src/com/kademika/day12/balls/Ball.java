package com.kademika.day12.balls;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by kurakinaleksandr on 12.09.14.
 */
public class Ball extends JComponent {

    private int x;
    private int y;
    private int dimension = 20;

    private final int MAXX = 640;
    private final int MAXY = 480;
    private int direction = 1;

    private Color color;

    private long speed;

    public Ball() {
        createRandomBall();
    }

    private void createRandomBall() {
        x = new Random().nextInt(MAXX - dimension);
        y = new Random().nextInt(MAXY - dimension);
        speed = 20 + new Random().nextInt(20);
    }

    public void move() {
        if (x >= MAXX - dimension) {
            direction = -1;
        } else if (x <= 0) {
            direction = 1;
        }
        x += 5 * direction;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, dimension, dimension);
    }

    public long getSpeed() {
        return speed;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
