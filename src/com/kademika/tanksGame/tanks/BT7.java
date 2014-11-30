package com.kademika.tanksGame.tanks;

import com.kademika.tanksGame.BattleField;
import com.kademika.tanksGame.Direction;
import com.kademika.tanksGame.fieldObjects.BFObject;

import java.awt.*;

public class BT7 extends AbstractTank {
    BFObject eagle;
    BattleField bf;
    protected int armor = 2;

    public BT7(BattleField bf) {
        super(bf);
        tankColor = new Color(255, 0, 0);
        towerColor = new Color(0, 255, 0);
        movePath = 1;
        this.bf = bf;
    }

    public BT7(BattleField bf, int x, int y, Direction direction) {
        super(bf, x, y, direction);
        tankColor = new Color(255, 0, 0);
        towerColor = new Color(0, 255, 0);
        movePath = 1;
        this.bf = bf;
    }

    private int step = 0;
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

//    private int step = 0;

    @Override
    public Action setUp() {
        if (step >= actoins.length) {
            step = 0;
        }

//        if (step >= actoins.length) {
//            step = 0;
//        }
        return  actoins[step++];
    }

//    public Action [] destroyEagle() {
//
//        Action action = Action.NONE;
//        Action [] actions = new Action[]{Action.NONE, Action.NONE};
//
//        int eagleX = 0;
//        int eagleY = 0;
//
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                if (bf.scanQuadrant(i, j) instanceof Eagle) {
//                    eagleX = j * 64;
//                    eagleY = i * 64;
//                }
//            }
//        }

//        if (this.getX() < eagleX) {
//            actions[0] = Action.TURN_RIGHT;
//            if (this.checkQuadrantFrontTank().isDestroyed() || this.checkQuadrantFrontTank() instanceof Blank) {
//                actions[1]  = Action.MOVE;
//            } else
//            if (!(this.checkQuadrantFrontTank() instanceof Water)) {
//                actions[1]  = Action.FIRE;
//            } return actions;
//        } else
//        if (this.getY() < eagleY) {
//            actions[0] = Action.TURN_DOWN;
//            if (this.checkQuadrantFrontTank().isDestroyed() || this.checkQuadrantFrontTank() instanceof Blank) {
//                actions[1]  = Action.MOVE;
//            } else
//            if (!(this.checkQuadrantFrontTank() instanceof Water)) {
//                actions[1]  = Action.FIRE;
//            }
//        } else
//        if (this.getX() > eagleX) {
//            actions[0] = Action.TURN_LEFT;
//            if (this.checkQuadrantFrontTank().isDestroyed() || this.checkQuadrantFrontTank() instanceof Blank) {
//                actions[1]  = Action.MOVE;
//            } else
//            if (!(this.checkQuadrantFrontTank() instanceof Water)) {
//                actions[1]  = Action.FIRE;
//            }
//        } else
//        if (this.getY() > eagleY){
//            actions[0] = Action.TURN_UP;
//            if (this.checkQuadrantFrontTank().isDestroyed() || this.checkQuadrantFrontTank() instanceof Blank) {
//                actions[1]  = Action.MOVE;
//            } else
//            if (!(this.checkQuadrantFrontTank() instanceof Water)) {
//                actions[1]  = Action.FIRE;
//            }
//        }
//        else
//
//        if (this.checkQuadrantFrontTank().isDestroyed() || this.checkQuadrantFrontTank() instanceof Blank) {
//            actions[1]  = Action.MOVE;
//        } else
//        if (!(this.checkQuadrantFrontTank() instanceof Water)) {
//            actions[1]  = Action.FIRE;
//        }
//
//
//        return actions;
//    }

//    public BFObject checkQuadrantFrontTank() {
//        BFObject frontObject = null;
//
//        int v = this.getY() / 64;
//        int h = this.getX() / 64;
//
//        if (this.getDirection() == Direction.RIGHT) {
//            return bf.scanQuadrant(v, h + 1);
//        }
//
//        if (this.getDirection() == Direction.DOWN) {
//            return bf.scanQuadrant(v + 1, h);
//        }
//
//        if (this.getDirection() == Direction.LEFT) {
//            frontObject = bf.scanQuadrant(v, h - 1);
//        }
//
//        if (this.getDirection() == Direction.UP) {
//            frontObject = bf.scanQuadrant(v - 1, h);
//        }
//
//        return frontObject;
//    }
    @Override
    public void destroy() {
        if (armor > 0) {
            armor--;
        } else {
            super.destroy();
        }
    }

    public String getName(){
        return "BT7";
    }
}
