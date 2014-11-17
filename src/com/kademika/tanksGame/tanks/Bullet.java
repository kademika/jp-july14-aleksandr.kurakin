package com.kademika.tanksGame.tanks;
import com.kademika.tanksGame.Direction;
import com.kademika.tanksGame.interfaces.Destroyable;
import com.kademika.tanksGame.interfaces.Drawable;

import java.awt.Color;
import java.awt.Graphics;



public class Bullet implements Drawable, Destroyable {

    private int speed = 5;

    private int x;
    private int y;
    private Direction direction;

    private boolean destroyed;

    public Bullet(Tank tank) {

        if (tank.getDirection() == Direction.UP) {
            this.x = tank.getX() + 25;
            this.y = tank.getY() - 0;
        } else if (tank.getDirection() == Direction.DOWN) {
            this.x = tank.getX() + 25;
            this.y = tank.getY() + 64;
        } else if (tank.getDirection() == Direction.LEFT) {
            this.x = tank.getX() - 0;
            this.y = tank.getY() + 25;
        } else if (tank.getDirection() == Direction.RIGHT) {
            this.x = tank.getX() + 64;
            this.y = tank.getY() + 25;
        }
        this.direction = tank.getDirection();

//        this.x = x;
//        this.y = y;
//        this.direction = direction;
        this.destroyed = false;
    }

    public Bullet(int x, int y, Direction direction){
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.destroyed = false;
    }

    public void updateX(int x) {
        this.x += x;
    }

    public void updateY(int y) {
        this.y += y;
    }


    public void destroy() {
        destroyed = true;
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

    public int getSpeed() {
        return speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public void draw(Graphics g) {
        if (!destroyed) {
            g.setColor(new Color(255, 255, 0));
            g.fillRect(this.x, this.y, 14, 14);
        }
    }
}
