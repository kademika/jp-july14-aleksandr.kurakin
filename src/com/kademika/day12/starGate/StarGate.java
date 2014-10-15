package com.kademika.day12.starGate;


import javax.swing.*;
import java.awt.*;


public class StarGate extends JPanel {

    public static void main(String[] args) throws InterruptedException {
        new StarGate();
    }

    private static final int WIDTH = 400;

    private int direction = 1;

    private Ship ship;

    private Gates gates;

    public StarGate() throws InterruptedException {
        JFrame jFrame = new JFrame("STAR GATE");
        jFrame.setLocation(400, 500);
        jFrame.setMinimumSize(new Dimension(WIDTH, 400));
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.getContentPane().add(this);
        jFrame.pack();
        jFrame.setVisible(true);

        ship = new Ship();
        gates = new Gates();

        turnOnGates(); //0 thread

        new Thread(new Runnable() { //1 thread
            @Override
            public void run() {
                try {
                    moveShip();
                } catch (Exception e) {
                    //ignore
                }
            }
        }).start();


        while (true) {
            repaint();
            sleep(1000 / 60);
        }
    }

    private boolean isShipInRange() {
        return (Math.abs(ship.x - gates.bottomX) < 40);
    }

    private void turnOnGates() {    // in thread 0
        new Thread(new Runnable() {
            @Override
            public void run() {

                    try {
                        while (true){

                            synchronized (gates) {
                        System.out.println("Mothership: waiting for the ship");
                                gates.wait();
                            }

                            while (!gates.isOpen) {
                                openGates();
                            }

                            synchronized (ship) {
                                ship.notify();
                            }

                            synchronized (gates) {
                                gates.wait();
                            }
                            while (!gates.isClosed) {
                                closeGates();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

            }
        }).start();
    }

    private void openGates() throws InterruptedException {   // in thread 0
        if (!gates.isOpen) {
            if (gates.topY > gates.openTopY) {
                gates.topY--;
            }
            if (gates.bottomY < gates.openBottomY) {
                gates.bottomY++;
            }
            if (gates.topY == gates.openTopY) {
                gates.bottomY = gates.openBottomY;
                gates.isOpen = true;
                gates.isClosed = false;
                System.out.println("Mothership: Gates opened, please come in");
                synchronized (ship) {
                    ship.notify();
                }
            } else sleep(15);
        }
    }

    private void closeGates() throws InterruptedException {
            if (gates.topY >= gates.openTopY) {
                gates.topY++;
            }
            if (gates.bottomY <= gates.openBottomY) {
                gates.bottomY--;
            }
            if (gates.topY + gates.doorHeight == gates.bottomY) {
                gates.isClosed = true;
                gates.isOpen = false;
            } else sleep(15);
    }

    private void moveShip() throws Exception {  // in thread 1
        while (true) {
            if (ship.x >= WIDTH - ship.radius) {

//                ship.x = 0;
                direction = -1;
            } else if (ship.x <= 0) {
                direction = 1;
            }

            if (!gates.isOpen && isShipInRange()) {
                synchronized (gates) {
                    gates.notify();
                    System.out.println("Ship: Asked permission");
                }

                synchronized (ship) {
                    System.out.println(" and waiting gates to open.");
                    ship.wait();
                }
                }else if (gates.isOpen && !isShipInRange()) {
                synchronized (gates) {
                    gates.notify();
                }
            }

            ship.x+= direction;
            System.out.println(Math.abs(ship.x - gates.bottomX));
            sleep(5);
        }
    }

    private void sleep(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (Exception e) {
            //ignore
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(ship.color);
        g.fillOval(ship.x, ship.y, ship.radius, ship.radius);

        g.setColor(gates.color);
        g.fillRect(gates.topX, gates.topY, gates.topWidth, gates.doorHeight);
        g.fillRect(gates.bottomX, gates.bottomY, gates.bottomWidth, gates.doorHeight);
    }

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

    public class Ship {
        public int x;
        public int y;
        public int radius;
        public Color color;

        public Ship() {
            this.x = 10;
            this.y = 170;
            this.radius = 10;
            this.color = Color.ORANGE;
        }
    }


}


