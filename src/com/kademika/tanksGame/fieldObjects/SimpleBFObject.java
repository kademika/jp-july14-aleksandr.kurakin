package com.kademika.tanksGame.fieldObjects;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by kurakinaleksandr on 17.09.14.
 */
public abstract class SimpleBFObject implements BFObject, Serializable {

    // current position on BF
    private int x;
    private int y;

    private boolean isDestroyable = true;

    protected Color color;

    protected boolean isDestroyed = false;

    public SimpleBFObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void destroy() {
        isDestroyed = true;
    }

    @Override
    public void draw(Graphics g) {
        if (!isDestroyed) {
            g.setColor(this.color);
            g.fillRect(this.getX(), this.getY(), 64, 64);
        }
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
