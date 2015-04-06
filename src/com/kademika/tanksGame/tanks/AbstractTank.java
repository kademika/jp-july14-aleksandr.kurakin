package com.kademika.tanksGame.tanks;

import com.kademika.tanksGame.*;

import java.awt.*;

//import java.util.Random;

public abstract class AbstractTank implements Tank {

    private Boolean iCanMoveForward = true;

    @Override // OK
    public void setICanMoveForward(Boolean canMoveForward) {
        iCanMoveForward = canMoveForward; //
    }

    private int speed = 10;
    protected int movePath = 1;
    protected int armor = 1;
    // 1 - up, 2 - down, 3 - left, 4 - right
    private Direction direction;
    // current position on BF
    private int x;
    private int y;
    private boolean destroyed;
    private BattleField bf;
    Action action = Action.NONE;

    public void setAction(Action action) {
        this.action = action;
    }

    public Action getAction(){
        return action;
    }

    protected Color tankColor;
    protected Color towerColor;

    public AbstractTank(BattleField bf) {
        this(bf, 128, 512, Direction.UP);
    }

    public AbstractTank(BattleField bf, int x, int y, Direction direction) {
        this.bf = bf;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.destroyed = false;
    }

    @Override // Turn
    public void turn(Direction direction) {
//        if (direction == Direction.RIGHT) {
//
//            if (this.direction == Direction.UP) {
//                this.direction = Direction.RIGHT;
//                return;
//            } else if (this.direction == Direction.RIGHT) {
//                this.direction = direction.DOWN;
//                return;
//            } else if (this.direction == Direction.DOWN) {
//                this.direction = Direction.LEFT;
//                return;
//            } else if (this.direction == Direction.LEFT) {
//                this.direction = Direction.UP;
//                return;
//            }
//            return;
//
//        } else if (direction == Direction.LEFT) {
//
//            if (this.direction == Direction.UP) {
//                this.direction = Direction.LEFT;
//                return;
//            } else if (this.direction == Direction.LEFT) {
//                this.direction = Direction.LEFT;
//                return;
//            } else if (this.direction == Direction.DOWN) {
//                this.direction = Direction.RIGHT;
//                return;
//            } else if (this.direction == Direction.RIGHT) {
//                this.direction = direction.UP;
//                return;
//            }
//        }
        this.direction = direction;
    }

    @Override // Creating Bullet in front of Tank: OK
    public Bullet fire() {
        int bulletX = -100;
        int bulletY = -100;
        if (direction == Direction.UP) {
            bulletX = x + 25;
            bulletY = y - 14;
        } else if (direction == Direction.DOWN) {
            bulletX = x + 25;
            bulletY = y + 78;
        } else if (direction == Direction.LEFT) {
            bulletX = x - 14;
            bulletY = y + 25;
        } else if (direction == Direction.RIGHT) {
            bulletX = x + 78;
            bulletY = y + 25;
        }
        return new Bullet(bulletX, bulletY, direction);
    }

    @Override // Check is Tank destroyed
    public boolean isDestroyed() {
        return destroyed;
    }

    // Do we need this method?
    public void destroy() {
        destroyed = true;
    }

    @Override // X += X
    public void updateX(int x) {
        this.x += x;
    }

    @Override // Y += Y
    public void updateY(int y) {
        this.y += y;
    }

    @Override // OK
    public Direction getDirection() {
        return direction;
    }

    @Override // X
    public int getX() {
        return x;
    }

    @Override // Y
    public int getY() {
        return y;
    }

    @Override // Return speed
    public int getSpeed() {
        return speed;
    }

    @Override // Return move path
    public int getMovePath() {
        return movePath;
    }

    //
    public void draw(Graphics g) {
        if (!destroyed) {
            g.setColor(tankColor);
            g.fillRect(this.getX(), this.getY(), 64, 64);

            g.setColor(towerColor);
            if (this.getDirection() == Direction.UP) {
                g.fillRect(this.getX() + 20, this.getY(), 24, 34);
            } else if (this.getDirection() == Direction.DOWN) {
                g.fillRect(this.getX() + 20, this.getY() + 30, 24, 34);
            } else if (this.getDirection() == Direction.LEFT) {
                g.fillRect(this.getX(), this.getY() + 20, 34, 24);
            } else {
                g.fillRect(this.getX() + 30, this.getY() + 20, 34, 24);
            }
        }
    }
}