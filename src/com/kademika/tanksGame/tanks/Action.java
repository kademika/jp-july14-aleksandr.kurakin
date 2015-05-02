package com.kademika.tanksGame.tanks;

import java.io.Serializable;

/**
 * Created by kurakinaleksandr on 17.09.14.
 */
public enum Action implements Serializable {
    NONE, MOVE, FIRE, TURN_RIGHT, TURN_LEFT, TURN_UP, TURN_DOWN;
}

