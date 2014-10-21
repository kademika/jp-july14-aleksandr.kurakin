package com.kademika.day12.atomic;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by kurakinaleksandr on 25.09.14.
 */
public class DemoAtomicLong {
    public static void main(String[] args) {


    final  IdGenerator generator = new IdGenerator();
        final Random random = new Random();
        final CountDownLatch countDownLatch = new CountDownLatch(10);


        for(int i = 0; i < 100; i++){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " : " + generator.getNextId());
            }
        }).start();
            countDownLatch.countDown();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    }

}
