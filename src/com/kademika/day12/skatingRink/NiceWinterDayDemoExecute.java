package com.kademika.day12.skatingRink;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kurakinaleksandr on 28.09.14.
 */
public class NiceWinterDayDemoExecute {
    public static void main(String[] args) throws Exception {

        System.out.println("Good morning everyone, we are opened!");

        final SkatingRink skatingRink = new SchoolSkatingRink();

        final Random random = new Random();

        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 100; i++) {

            final Skater skater = new Skater("Skater " + i);

            executor.submit(new Runnable() {
                @Override
                public void run() {
                    Skates skates = skatingRink.getSkates(skater);
//                    System.out.println(skater.getName() + " on ice");
                    sleep(random.nextInt(2000));
                    skatingRink.returnSkates(skates, skater);
                }
            });
            sleep(random.nextInt(1000));
        }
        executor.shutdown();
    }


    private static void sleep(int timeout) {
        try {
            Thread.currentThread().sleep(timeout);
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }
}
