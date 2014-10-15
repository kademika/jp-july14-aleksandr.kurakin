package com.kademika.day12.skatingRink;

import java.util.Random;

/**
 * Created by kurakinaleksandr on 24.09.14.
 */
public class NiceWinterDayDemo3 {
    public static void main(String[] args) throws Exception {

        System.out.println("Good morning everyone, we are opened!");

        final VipSkatingRinkQueue skatingRink = new VipSkatingRinkQueue();

        final Random random = new Random();

        for (int i = 0; i < 100; i++) {

            final Skater skater = new Skater("Skater " + i);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Skates skates = null;
                    while (skates == null) {
                        sleep(random.nextInt(1000));
                        skates = skatingRink.getSkates(skater);
                    }
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
