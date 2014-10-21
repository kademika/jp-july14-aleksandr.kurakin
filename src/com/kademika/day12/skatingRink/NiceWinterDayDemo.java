package com.kademika.day12.skatingRink;

import java.util.Random;
import java.util.concurrent.CountDownLatch;


public class NiceWinterDayDemo {
    public static void main(String[] args) throws Exception {

        System.out.println("Good morning everyone, we are opened!");

        final SkatingRink skatingRink = new SchoolSkatingRink();

        final Random random = new Random();
        final CountDownLatch countDownLatch = new CountDownLatch(4);

        for (int i = 0; i < 100;) {

            final Skater skater = new Skater("Skater " + i);
            if (skatingRink.getFreeSkates() > 0) {

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Skates skates = skatingRink.getSkates(skater);
                        try{
                            countDownLatch.await();
                        } catch (Exception e){
                            //ignore
                        }
                        System.out.println(skater.getName() + " on ice");
                        sleep(random.nextInt(2000));
                        skatingRink.returnSkates(skates, skater);
                    }
                });
                        thread.start();
                countDownLatch.countDown();

                sleep(random.nextInt(1000));
                i ++;
            }
            System.out.println("Goodnight");
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
