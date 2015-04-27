package com.kademika.tanksGame.tanks;

import com.kademika.tanksGame.BattleField;
import com.kademika.tanksGame.Direction;
import java.awt.*;
import java.io.Serializable;

public class BT7 extends AbstractTank implements Serializable{

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


    @Override
    public void destroy() {
        armor--;
        if (armor <= 0) {
            super.destroy();
            System.out.println("Aggressor is destroyed");
        }
    }

    public String getName(){
        return "BT7";
    }
}
