package com.kademika.tanksGame;

import com.kademika.tanksGame.movingObjects.Action;
import com.kademika.tanksGame.movingObjects.Tank;

import java.io.Serializable;

/**
 * Created by kurakinaleksandr on 17.05.15.
 */
public class Command implements Serializable {
    private Tank userTank;
    private Action userAction = Action.NONE;

    public Tank getUserTank() {
        return userTank;
    }

//    public void setUserTank(Tank userTank) {
//        this.userTank = userTank;
//    }

    public Action getUserAction() {
        return userAction;
    }

//    public void setUserAction(Action userAction) {
//        this.userAction = userAction;
//    }

    public Command(Action action, Tank tank) {
        this.userAction = action;
        this.userTank = tank;
    }

}
