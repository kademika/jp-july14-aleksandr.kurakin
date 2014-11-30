package com.kademika.tanksGame;


import com.kademika.tanksGame.fieldObjects.BFObject;
import com.kademika.tanksGame.fieldObjects.Blank;
import com.kademika.tanksGame.fieldObjects.Water;
import com.kademika.tanksGame.tanks.*;
import com.kademika.tanksGame.tanks.Action;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class ActionField extends JPanel {

    private boolean COLORDED_MODE = false;

    public BattleField getBattleField() {
        return battleField;
    }

    private BattleField battleField;
    private Tank defender;
    private Tank aggressor;
//    private Bullet bullet;
    private volatile List<Bullet> bullets = new ArrayList<>();
    private File history = new File("history.txt");
    private int step = 1;
    private volatile int bulletCount = 0;


    /**
     * Write your code here.
     */
    void replay() {

        String command;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(history));
            while ((command = reader.readLine()) != null) {
//                System.out.println(command);
                if (command.split("_", 2)[0].equals("T34")) {
                    System.out.println(command.split("_", 2)[0] + " T34");
                    processAction(checkAction(command.split("_", 2)[1]), defender);
                    System.out.println(checkAction(command.split("_", 2)[1]));

                } else if (command.split("_", 2)[0].equals("BT7")) {
                    System.out.println(command.split("_", 2)[0] + " BT7");
                    processAction(checkAction(command.split("_", 2)[1]), aggressor);
                    System.out.println(checkAction(command.split("_", 2)[1]));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    // Run The Game With Writing in File: OK
    void runTheGameWithWriteToFile() throws Exception {
        //clear the history file
        clearHistory(new File("history.txt"));
        Action action;
        while (true) {
            if (!aggressor.isDestroyed() && !defender.isDestroyed()) {
                action = aggressor.setUp();
                processAction(action, aggressor);
                writeToFile(action, aggressor);
            }
            if (!aggressor.isDestroyed() && !defender.isDestroyed()) {
                action = defender.setUp();
                processAction(action, defender);
                writeToFile(action, defender);
            }
        }
    }

    //Run game in multi threads
    void runTheGameMT() throws Exception {
        //clear the history file
        clearHistory(new File("history.txt"));

        aggressorAction();
        defenderAction();
//        bulletsAction();
        screenUpdate();
    }

    private void aggressorAction() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Action action;
                while (!aggressor.isDestroyed() && !defender.isDestroyed()) {


//                    action = aggressor.setUp();
                    try {
                        action = aggressor.setUp();
                        processAction(action, aggressor);
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                        writeToFile(action, aggressor);
                }
            }
        }).start();
    }

    private void defenderAction() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Action action;
                while (!aggressor.isDestroyed() && !defender.isDestroyed()) {

//                    action = defender.setUp();
                    try {
                        action = defender.setUp();
                        processAction(action, defender);
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                        writeToFile(action, defender);
                }
            }
        }).start();
    }

//    private void bulletsAction() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    for (int i = 0; i < bullets.size(); i++) {
//                        Bullet currentBullet = bullets.get(i);
//                        if (currentBullet != null) {
//
//                            while ((currentBullet.getX() > -14) && (currentBullet.getX() < 590)
//                                    && (currentBullet.getY() > -14) && (currentBullet.getY() < 590)) {
//                                if (currentBullet.getDirection() == Direction.UP) {
//                                    currentBullet.updateY(-step);
//                                } else if (currentBullet.getDirection() == Direction.DOWN) {
//                                    currentBullet.updateY(step);
//                                } else if (currentBullet.getDirection() == Direction.LEFT) {
//                                    currentBullet.updateX(-step);
//                                } else {
//                                    currentBullet.updateX(step);
//                                }
//                                if (processInterception(bu)) {
//                                    currentBullet.destroy();
//                                }
//                                repaint();
//                                try {
//                                    Thread.sleep(currentBullet.getSpeed());
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                                if (currentBullet.isDestroyed()) {
//                                    bullets.remove(currentBullet);
//
//                                }
//                            }
//                        }
//                        repaint();
//                        try {
////                        processFire();
//                            Thread.sleep(10);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }).start();
//    }

    private void screenUpdate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    repaint();
                    try {
                        Thread.sleep(15);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    // Process Action
    private void processAction(Action a, Tank t) throws Exception {
        final Bullet bullet;
        if (a == Action.MOVE) {
            processMove(t);
            return;
        } else if (a == Action.FIRE) {

            bullet = t.fire();
            bullet.setCount(bulletCount);
            bullets.add(bullet);
            bulletCount++;
            // From here
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        processFire(bullet);
//                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    writeToFile(Action.FIRE, aggressor);
                }
            }).start();
//            // To here
            return;
        }

        else if (a == Action.TURN_LEFT) {
            processTurn(t, Direction.LEFT);
            return;
        } else if (a == Action.TURN_RIGHT) {
            processTurn(t, Direction.RIGHT);
        }
        writeToFile(a, t);
    }

    // Process Turn
    private void processTurn(Tank tank, Direction direction) throws Exception {
        tank.turn(direction);
    }

    //Process Move, ARRAY
    private boolean processMove(Tank tank) throws Exception {

        Direction direction = tank.getDirection();
        int step = 1;

        for (int i = 0; i < tank.getMovePath(); i++) {
            int covered = 0;

            String tankQuadrant = getQuadrant(tank.getX(), tank.getY());
            //split string form coordinates in array
            int v = Integer.parseInt(tankQuadrant.split("_")[0]); // V - means vertical or Y/64
            int h = Integer.parseInt(tankQuadrant.split("_")[1]); // H - means horizontal or X/64

            // check limits x: 0, 513; y: 0, 513
            if ((direction == Direction.UP && tank.getY() <= 0) || (direction == Direction.DOWN && tank.getY() >= 512)
                    || (direction == Direction.LEFT && tank.getX() <= 0) || (direction == Direction.RIGHT && tank.getX() >= 512)) {
                System.out.println(tank.getName() + " [illegal move1] direction: " + direction + " tankX: " + tank.getX() + ", " +
                        "tankY: " + tank.getY());
                return false;
            }

            // check next quadrant before move
            if (direction == Direction.UP && v < 9) {
                v--;
            } else if (direction == Direction.DOWN && v > 0) {
                v++;
            } else if (direction == Direction.RIGHT && h < 9) {
                h++;
            } else if (direction == Direction.LEFT && h > 0) {
                h--;
            }
            BFObject bfobject;
            if (v >= 0 && v < 9 && h >= 0 && h < 9) {
                bfobject = battleField.scanQuadrant(v, h);
                if (!(bfobject instanceof Blank) && !bfobject.isDestroyed() && !(bfobject instanceof Water)
                        ) {

                    System.out.println(tank.getName() + "[illegal move2] direction: " + direction
                            + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
                    return false;
                }
            }

//            else return false; // This can be a mistake!!!


            while (covered < 64) {
                if (direction == Direction.UP) {
                    tank.updateY(-step);
                    //				System.out.println("[move up] direction: " + direction + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
                } else if (direction == Direction.DOWN) {
                    tank.updateY(step);
                    //				System.out.println("[move down] direction: " + direction + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
                } else if (direction == Direction.LEFT) {
                    tank.updateX(-step);
                    //				System.out.println("[move left] direction: " + direction + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
                } else {
                    tank.updateX(step);
                    //				System.out.println("[move right] direction: " + direction + " tankX: " + tank.getX() + ",
                    // tankY: " + tank.getY());
                }
                covered += step;

//                repaint();
                Thread.sleep(tank.getSpeed());
            }
        }
        return true;
    }

    // Process Fire
    private void processFire(Bullet bullet) throws Exception {
        int count = bullet.getCount();
        while ((bullet.getX() > -14) && (bullet.getX() < 590)
                && (bullet.getY() > -14) && (bullet.getY() < 590)) {
            if (bullet.getDirection() == Direction.UP) {
                bullet.updateY(-step);
            } else if (bullet.getDirection() == Direction.DOWN) {
                bullet.updateY(step);
            } else if (bullet.getDirection() == Direction.LEFT) {
                bullet.updateX(-step);
            } else {
                bullet.updateX(step);
            }
            if (processInterception(bullet)) {
                bullet.destroy();
            }
            try {
                Thread.sleep(bullet.getSpeed());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (bullet.isDestroyed() || (bullet.getX() > 580) || (bullet.getX() < -20) || (bullet.getY() > 580) || (bullet.getY() < -20)) {
                bullets.remove(count);
                bulletCount --;
            }
        }
    }

    // Process interception, ARRAY
    private boolean processInterception(Bullet bullet) {

        String bulletCoordinates = getQuadrant(bullet.getX(), bullet.getY());

        int v = Integer.parseInt(bulletCoordinates.split("_")[0]);
        int h = Integer.parseInt(bulletCoordinates.split("_")[1]);

        if (v >= 0 && v < 9 && h >= 0 && h < 9) {
            BFObject bfObject = battleField.scanQuadrant(v, h);
            if (!bfObject.isDestroyed() && !(bfObject instanceof Blank) && !(bfObject instanceof Water)) {
                battleField.destroyObject(v, h);
//                bullets.remove(bullet);
                return true;
            }

            // check aggressor
            if (!aggressor.isDestroyed() && checkInterception(getQuadrant(aggressor.getX(), aggressor.getY()), bulletCoordinates)) {
                aggressor.destroy();
//                bullets.remove(bullet);
                return true;
            }

            // check defender
            if (!defender.isDestroyed() && checkInterception(getQuadrant(defender.getX(), defender.getY()), bulletCoordinates)) {
                defender.destroy();
//                bullets.remove(bullet);
                return true;
            }
        }
        return false;
    }

    // Check Interception, ARRAY
    private boolean checkInterception(String object, String quadrant) {
        int oy = Integer.parseInt(object.split("_")[0]);
        int ox = Integer.parseInt(object.split("_")[1]);

        int qy = Integer.parseInt(quadrant.split("_")[0]);
        int qx = Integer.parseInt(quadrant.split("_")[1]);

        if (oy >= 0 && oy < 9 && ox >= 0 && ox < 9) {
            if (oy == qy && ox == qx) {
                return true;
            }
        }
        return false;
    }

    // Get quadrant, ARRAY
    public String getQuadrant(int x, int y) {
        // input data should be correct
        return y / 64 + "_" + x / 64;
    }

    // writing to file
    public synchronized void writeToFile(Action action, Tank tank) {

        BufferedWriter output;
        try {
            output = new BufferedWriter(new FileWriter(history, true));
            output.append(tank.getName() + "_" + action.toString());
            output.append("\n");
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        // This method is for reading Action from file
    public Action checkAction(String command) {
        Action action = Action.NONE;
        if (command.equals("MOVE")) {
            action = Action.MOVE;
            return action;
        } else if (command.equals("FIRE")) {
            action = Action.FIRE;
            return action;
        } else if (command.equals("TURN_LEFT")) {
            action = Action.TURN_LEFT;
            return action;
        } else if (command.equals("TURN_RIGHT")) {
            action = Action.TURN_RIGHT;
            return action;
        }

        return action;
    }

    // Erase history file
    public void clearHistory(File history) {
        PrintWriter writer;
        try {
            writer = new PrintWriter(history);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // ActionField constructor without parameter, ARRAY
    public ActionField() throws Exception {
        battleField = new BattleField();
        defender = new T34(battleField);

        String location = battleField.getAggressorLocation();
        aggressor = new BT7(battleField,
                Integer.parseInt(location.split("_")[1]), Integer.parseInt(location.split("_")[0]), Direction.RIGHT);

//        bullet = new Bullet(-100, -100, Direction.NONE);
//            JFrame frame = mainFrame;
//        JFrame frame = new JFrame("BATTLE FIELD, DAY 7");
//        frame.setLocation(750, 150);
//        frame.setMinimumSize(new Dimension(battleField.getBfWidth(), battleField.getBfHeight() + 22));
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.getContentPane().add(this);
//        frame.pack();
//        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        int i = 0;
        Color cc;
        for (int v = 0; v < 9; v++) {
            for (int h = 0; h < 9; h++) {
                if (COLORDED_MODE) {
                    if (i % 2 == 0) {
                        cc = new Color(252, 241, 177);
                    } else {
                        cc = new Color(233, 243, 255);
                    }
                } else {
                    cc = new Color(180, 180, 180);
                }
                i++;
                g.setColor(cc);
                g.fillRect(h * 64, v * 64, 64, 64);
            }
        }

        battleField.draw(g);

        defender.draw(g);
        aggressor.draw(g);
        for (int j = 0; j < bullets.size(); j++) {
            bullets.get(j).draw(g);
        }

    }
}