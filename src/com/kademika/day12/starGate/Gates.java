package com.kademika.day12.starGate;

import java.awt.*;

/**
 * Created by kurakinaleksandr on 11.05.15.
 */
public class Gates {

    public boolean isOpen;
    public boolean isClosed;
    public int topY;
    public int topX;
    public int bottomY;
    public int bottomX;
    public int openTopY;
    public int openBottomY;
    public int topWidth;
    public int bottomWidth;
    public int doorHeight;
    public Color color;


    public Gates() {
        isOpen = false;

        topY = 120;
        topX = 200;
        bottomY = 170;
        bottomX = 200;
        openTopY = 80;
        openBottomY = 210;
        topWidth = 10;
        bottomWidth = 10;
        doorHeight = 50;
        color = Color.GREEN;
    }
}
