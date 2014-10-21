package com.kademika.day12.skatingRink;

import java.util.Random;


public class NiceWinterDayDemo2 {
    public static void main(String[] args) throws Exception {

        System.out.println("Good morning everyone, we are opened!");

        final SkatingRink skatingRink = new SchoolSkatingRink();

        final Random random = new Random();

        for (int i = 0; i < 100; i++) {

            final Skater skater = new Skater("Skater " + i);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Skates skates = skatingRink.getSkates(skater);
                    sleep(random.nextInt(2000));
                    skatingRink.returnSkates(skates, skater);
                }
            }).start();
            sleep(random.nextInt(1000));

        }
    }

    private static void sleep(int timeout) {
        try {
            Thread.currentThread().sleep(timeout);
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }
}