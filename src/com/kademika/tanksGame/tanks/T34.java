package com.kademika.tanksGame.tanks;

import com.kademika.tanksGame.BattleField;
import com.kademika.tanksGame.Direction;

import java.awt.Color;


public class T34 extends AbstractTank {

    // Constructor with 128 512 point Tank, only BF need to create instance
    public T34(BattleField bf) {
        super(bf, 128, 512, Direction.UP);
        tankColor = new Color(0, 255, 0);
        towerColor = new Color(255, 0, 0);

    }

    // Constructor with full numbers of parameters, only Color is default
    public T34(BattleField bf, int x, int y, Direction direction) {
        super(bf, x, y, direction);
        tankColor = new Color(0, 255, 0);
        towerColor = new Color(255, 0, 0);
//        this.bf = bf;
    }

    private Action[] actoins = new Action[]{
            Action.TURN_LEFT,
            Action.FIRE,
            Action.MOVE,
            Action.TURN_RIGHT,
//            Action.FIRE,
            Action.MOVE,
            Action.TURN_LEFT,
            Action.FIRE,
            Action.MOVE,
            Action.TURN_RIGHT,
            Action.FIRE,
            Action.MOVE,
            Action.TURN_RIGHT,
    };

    private int step = 0;

    @Override
    public Action setUp() {
        if (step >= actoins.length) {
            step = 0;
        }
//        if (!(actoins[step] instanceof Action)) {
//            turn((Direction) actoins[step++]);
//        }
//        if (step >= actoins.length) {
//            step = 0;
//        }
        return actoins[step++];
    }

    //need to overwrite all method, did not need yet
    public Action moveToQuadrant(int v, int h) throws Exception {
        Action action = Action.NONE;

//        while (!eagle.isDestroyed()) {

            int x = (v - 1) * 64;
            int y = (h - 1) * 64;

            if (this.getX() < x) {
                this.turn(Direction.RIGHT);
                while (this.getX() != x) {
                    action = Action.MOVE;
                }
            } else {
                this.turn(Direction.LEFT);
                while (this.getX() != x) {
//                    move();
                }
            }

            if (this.getY() < y) {
                this.turn(Direction.DOWN);
                while (this.getY() != y) {
//                    move();
                }
            } else {
                this.turn(Direction.UP);
                while (this.getY() != y) {
//                    move();
                }
            }

//        }
        return action;
    }

    public String getName(){
        return "T34";
    }

}