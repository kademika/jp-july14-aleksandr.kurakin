package com.kademika.tanksGame.movingObjects;

import com.kademika.tanksGame.BattleField;
import com.kademika.tanksGame.Direction;
import java.awt.Color;
import java.io.Serializable;

public class T34 extends AbstractTank implements Serializable {
    protected int armor = 2;
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

    public String getName() {
        return "T34";
    }

    @Override
    public void destroy(){
        super.destroy();
        System.out.println("Defender is destroyed ");
    }
}