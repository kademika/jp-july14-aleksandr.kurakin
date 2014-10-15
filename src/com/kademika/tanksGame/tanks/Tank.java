package com.kademika.tanksGame.tanks;

import com.kademika.tanksGame.Direction;
import com.kademika.tanksGame.interfaces.Destroyable;
import com.kademika.tanksGame.interfaces.Drawable;

/**
 * Created by kurakinaleksandr on 17.09.14.
 */

public interface Tank extends Drawable, Destroyable {

    public Action setUp();

    public void move();

    public void turn(Direction direction);

    public Bullet fire();

    public int getX();

    public int getY();

    public Direction getDirection();

    public void updateX(int x);

    public void updateY(int y);

    public int getSpeed();

    public int getMovePath();

}