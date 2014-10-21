package com.kademika.day12.skatingRink;

/**
 * Created by kurakinaleksandr on 21.09.14.
 */
public interface SkatingRink {

    public Skates getSkates(Skater skater);

    public void returnSkates(Skates skates, Skater skater);

    public int getFreeSkates();
}
