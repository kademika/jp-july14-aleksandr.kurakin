package com.kademika.tanksGame.fieldObjects;

import java.awt.*;

/**
 * Created by kurakinaleksandr on 17.09.14.
 */
public class Rock extends SimpleBFObject {
    private int armor = 2;

    public Rock(int x, int y) {
        super(x, y);
        color = Color.darkGray;
    }

    @Override
    public void destroy() {
        armor--;
        if (armor == 0) {
            isDestroyed = true;
        }
    }
}
