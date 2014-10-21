package com.kademika.day12.balls;

/**
 * Created by kurakinaleksandr on 14.09.14.
 */
public class BallsMoveThread implements Runnable {

    private Ball ball;

    public BallsMoveThread() {
    }

    @Override
    public void run() {

        while (true) {
            ball.move();
            try {
                Thread.sleep(ball.getSpeed());
            } catch (InterruptedException e) {
            }
        }
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }
}
