package com.kademika.tanksGame.fieldObjects;

import java.awt.*;

/**
 * Created by kurakinaleksandr on 17.09.14.
 */
public class Water extends SimpleBFObject {
    private boolean isDestroyable = false;

    public Water(int x, int y) {
        super(x, y);
        color = new Color(0, 0, 255);
    }
    @Override
    public void destroy() {
        isDestroyed = false;
    }
}
