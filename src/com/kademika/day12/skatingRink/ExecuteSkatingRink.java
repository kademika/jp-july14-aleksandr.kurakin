package com.kademika.day12.skatingRink;

/**
 * Created by kurakinaleksandr on 28.09.14.
 */
public class ExecuteSkatingRink implements SkatingRink {

    private int freeSkates = 5;

    @Override
    public Skates getSkates(Skater skater) {
        if (freeSkates > 0) {
            System.out.println(skater.getName() + " get skates");
            freeSkates -= 1;
            return new Skates();
        } else System.out.println("There are no more free skates =(");
        return null;
    }

    @Override
    public void returnSkates(Skates skates, Skater skater) {
        freeSkates += 1;
        System.out.println(skater.getName() + " returned skates");
    }
    @Override
    public int getFreeSkates(){
        return freeSkates;
    }
}
