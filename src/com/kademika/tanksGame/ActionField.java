package com.kademika.tanksGame;

import com.kademika.tanksGame.fieldObjects.*;
import com.kademika.tanksGame.tanks.*;
import com.kademika.tanksGame.tanks.Action;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.*;
import java.util.List;

public class ActionField extends JPanel {
    public void setMf(MainFrame mf) {
        this.frame = mf;
    }

    private boolean COLORDED_MODE = false;
    private MainFrame frame;
    private BattleField battleField;
    private Tank defender;
    private Tank aggressor;
    private Tank autoTank;
    private Tank userTank;
    private volatile List<Bullet> bullets = new ArrayList<>();
    private File history = new File("history.txt");
    private int step = 1;
    private volatile int bulletCount = 0;

    // ActionField constructor without parameter, ARRAY
    public ActionField() throws Exception {
        battleField = new BattleField();
        defender = new T34(battleField);
        String location = battleField.getAggressorLocation();
        aggressor = new BT7(battleField,
                Integer.parseInt(location.split("_")[1]), Integer.parseInt(location.split("_")[0]), Direction.RIGHT);
    }

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

    public void setUpUserTank(String userTank) {
        if (userTank.equals("Aggressor")) {
            this.userTank = aggressor;
            this.autoTank = defender;
        } else {
            this.userTank = defender;
            this.autoTank = aggressor;
        }
    }

    // Make intelligent
    private void setAutoAction() {
        new Thread(new Runnable() {
            Action[] actoins = new Action[]{
                    Action.TURN_LEFT,
                    Action.FIRE,
                    Action.MOVE,
                    Action.TURN_DOWN,
                    Action.FIRE,
                    Action.MOVE,
                    Action.TURN_RIGHT,
                    Action.FIRE,
                    Action.MOVE,
                    Action.TURN_DOWN,
                    Action.FIRE,
                    Action.MOVE,
                    Action.TURN_RIGHT,
                    Action.FIRE,
                    Action.MOVE,
                    Action.TURN_DOWN,
                    Action.FIRE,
                    Action.MOVE,
                    Action.TURN_LEFT,
                    Action.FIRE,
                    Action.MOVE,
            };
            @Override
            public void run() {
                int step = 0;
                while (!defender.isDestroyed() && !aggressor.isDestroyed()) {
                    if (step >= actoins.length) {
                        step = 0;
                    }
                    autoTank.setAction(actoins[step++]);
                }
            }
        }).start();



    }

    void setAction(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_UP:
                try {
                    if (!(userTank.getDirection() == Direction.UP)) {
                        userTank.setAction(Action.TURN_UP);
                    } else
                        userTank.setAction(Action.MOVE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case KeyEvent.VK_RIGHT:
                try {
                    if (!(userTank.getDirection() == Direction.RIGHT)) {
                        userTank.setAction(Action.TURN_RIGHT);
                    } else
                        userTank.setAction(Action.MOVE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case KeyEvent.VK_LEFT:
                try {
                    if (!(userTank.getDirection() == Direction.LEFT)) {
                        userTank.setAction(Action.TURN_LEFT);
                    } else
                        userTank.setAction(Action.MOVE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case KeyEvent.VK_DOWN:
                try {
                    if (!(userTank.getDirection() == Direction.DOWN)) {
                        userTank.setAction(Action.TURN_DOWN);
                    } else
                        userTank.setAction(Action.MOVE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case KeyEvent.VK_SPACE:
                try {
                    processAction(Action.FIRE, userTank);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    //Run game in multi threads
    void runTheGameMT() throws Exception {
        //clear the history file
        clearHistory(new File("history.txt"));

        setAutoAction();
        aggressorAction();
        defenderAction();
        screenUpdate();
    }

    private void aggressorAction() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Action action;
                while (!aggressor.isDestroyed() && !defender.isDestroyed()) {
                    action = aggressor.setUp();
                    try {
                        processAction(action, aggressor);
                        writeToFile(action, aggressor);
                        Thread.sleep(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    writeToFile(action, aggressor);
                }
                frame.gameOverPanel();
            }
        }).start();
    }

    private void defenderAction() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Action action;
                while (!aggressor.isDestroyed() && !defender.isDestroyed()) {
                    action = defender.setUp();
                    try {
                        processAction(action, defender);
                        writeToFile(action, defender);
                        Thread.sleep(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                        writeToFile(action, defender);
                }
                frame.gameOverPanel();
            }
        }).start();
    }


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
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        processFire(bullet);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            return;
        } else if (a == Action.TURN_LEFT) {
            processTurn(t, Direction.LEFT);
            return;
        } else if (a == Action.TURN_RIGHT) {
            processTurn(t, Direction.RIGHT);
        } else if (a == Action.TURN_UP) {
            processTurn(t, Direction.UP);
        } else if (a == Action.TURN_DOWN) {
            processTurn(t, Direction.DOWN);
        }
//        writeToFile(a, t);
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
            while (covered < 64) { //                                   64 was
                if (direction == Direction.UP) {
                    tank.updateY(-step);
                } else if (direction == Direction.DOWN) {
                    tank.updateY(step);
                } else if (direction == Direction.LEFT) {
                    tank.updateX(-step);
                } else {
                    tank.updateX(step);
                }
                covered += step;
                Thread.sleep(tank.getSpeed());
            }
        }
        defender.setAction(Action.NONE);
        return true;
    }

    // Process Fire
    private void processFire(Bullet bullet) throws Exception {
//        int count = bullet.getCount();
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
        }
    }

    // Process interception, ARRAY
    private boolean processInterception(Bullet bullet) {

        String bulletCoordinates = getQuadrant(bullet.getX(), bullet.getY());
        int[] bulletCoordinateInt = {bullet.getX(), bullet.getY()};
        int[] aggressorCoordinates = {aggressor.getX(), aggressor.getY()};
        int[] defenderCoordinates = {defender.getX(), defender.getY()};

        int v = Integer.parseInt(bulletCoordinates.split("_")[0]);
        int h = Integer.parseInt(bulletCoordinates.split("_")[1]);

        if (v >= 0 && v < 9 && h >= 0 && h < 9) {
            BFObject bfObject = battleField.scanQuadrant(v, h);
            if (!bfObject.isDestroyed() && !(bfObject instanceof Blank) && !(bfObject instanceof Water)) {
                battleField.destroyObject(v, h);
//                bullet.destroy();
                return true;
            }
            // check aggressor
            if (!aggressor.isDestroyed() && checkInterception(aggressorCoordinates, bulletCoordinateInt)) {
                aggressor.destroy();
//                bullet.destroy();
                System.out.println("Aggressor is destroyed");
                return true;
            }
            // check defender
            if (!defender.isDestroyed() && checkInterception(defenderCoordinates, bulletCoordinateInt)) {
                defender.destroy();
//                bullet.destroy();
                System.out.println("Defender is destroyed ");
                return true;
            }
        }
        return false;
    }

    // Check Interception, ARRAY, Coordinates
    private boolean checkInterception(int[] tankCoordinates, int[] bulletCoordinates) {
        int tx = tankCoordinates[0];
        int ty = tankCoordinates[1];
        int bx = bulletCoordinates[0];
        int by = bulletCoordinates[1];

        if ((bx >= tx) & (bx < (tx + 50)) & (by >= ty) & (by < (ty + 50))) {
            return true;
        }
        return false;
    }

    // Check Interception String, Quadrant
//    private boolean checkInterception(String object, String quadrant) {
//        int oy = Integer.parseInt(object.split("_")[0]);
//        int ox = Integer.parseInt(object.split("_")[1]);
//
//        int qy = Integer.parseInt(quadrant.split("_")[0]);
//        int qx = Integer.parseInt(quadrant.split("_")[1]);
//
//        if (oy >= 0 && oy < 9 && ox >= 0 && ox < 9) {
//            if (oy == qy && ox == qx) {
//                return true;
//            }
//        }
//        return false;
//    }

    // Get quadrant, ARRAY
//    public int[] getCoordinates(int x, int y) {
//        int[] coordinates = {x, y};
//        return coordinates;
//    }

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
        } else if (command.equals("TURN_UP")) {
            action = Action.TURN_UP;
            return action;
        } else if (command.equals("TURN_DOWN")) {
            action = Action.TURN_DOWN;
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
            Bullet bullet = bullets.get(j);
            if ((bullet.getX() > -14 && bullet.getX() < 590) && (bullet.getY() > -14 && bullet.getY() < 590)) {
                bullet.draw(g);
            }
        }

    }

}