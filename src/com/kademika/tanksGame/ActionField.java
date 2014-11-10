package com.kademika.tanksGame;


import com.kademika.tanksGame.fieldObjects.BFObject;
import com.kademika.tanksGame.fieldObjects.Blank;
import com.kademika.tanksGame.fieldObjects.Water;
import com.kademika.tanksGame.tanks.*;
import com.kademika.tanksGame.tanks.Action;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class ActionField extends JPanel {

    private boolean COLORDED_MODE = false;
    private BattleField battleField;
    private Tank defender;
    private Tank aggressor;
    private Bullet bullet;
    private File history = new File("history.txt");


    /**
     * Write your code here.
     */

    // Run The Game With Writing in File: OK
    void runTheGameWithWriteToFile() throws Exception {
        Action action;
        while (true) {
            if (!aggressor.isDestroyed() && !defender.isDestroyed()) {
                action = aggressor.setUp();
                processAction(action, aggressor);
                writeToFile(action,aggressor);
            }
            if (!aggressor.isDestroyed() && !defender.isDestroyed()) {
                action = defender.setUp();
                processAction(action, defender);
                writeToFile(action, defender);
            }
        }
    }

    // Process Action
    private void processAction(Action a, Tank t) throws Exception {
        if (a == Action.MOVE) {
            t.setICanMoveForward(processMove(t));

        } else if (a == Action.FIRE) {
            processFire(t.fire());
        } else if (a == Action.TURN_DOWN) {
            processTurn(t, Direction.DOWN);
        } else if (a == Action.TURN_UP) {
            processTurn(t, Direction.UP);
        } else if (a == Action.TURN_DOWN) {
            processTurn(t, Direction.DOWN);
        } else if (a == Action.TURN_LEFT) {
            processTurn(t, Direction.LEFT);
        } else if (a == Action.TURN_RIGHT) {
            processTurn(t, Direction.RIGHT);
        }
//        writeToFile(a, t);
    }

    // Process Turn
    private void processTurn(Tank tank, Direction direction) throws Exception {
        tank.turn(direction);
        repaint();
    }

    //Process Move
    private boolean processMove(Tank tank) throws Exception {

//        processTurn(tank);
        Direction direction = tank.getDirection();
        int step = 1;

        for (int i = 0; i < tank.getMovePath(); i++) {
            int covered = 0;

            String tankQuadrant = getQuadrant(tank.getX(), tank.getY());
            //split string form coordinates in array
            int v = Integer.parseInt(tankQuadrant.split("_")[0]); // V - means vertical or Y/64
            int h = Integer.parseInt(tankQuadrant.split("_")[1]); // H - means horizontal or X/64

            // check limits x: 0, 513; y: 0, 513
            if ((direction == Direction.UP && tank.getY()<= 0) || (direction == Direction.DOWN && tank.getY() >= 512)
                    || (direction == Direction.LEFT && tank.getX() <= 0) || (direction == Direction.RIGHT && tank.getX() >= 512)) {
                System.out.println(tank.getName()+ " [illegal move1] direction: " + direction + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
                return false;
            }

            // check next quadrant before move
            if (direction == Direction.UP && v < 9) {
                v++;
            } else if (direction == Direction.DOWN && v > 0) {
                v--;
            } else if (direction == Direction.RIGHT && h <9) {
                h++;
            } else if (direction == Direction.LEFT && h > 0) {
                h--;
            }
            BFObject bfobject;
            if (v>=0 && v<9 && h>=0 && h<9){
            bfobject = battleField.scanQuadrant(v, h);
            if (!(bfobject instanceof Blank) && !bfobject.isDestroyed() && !(bfobject instanceof Water)
                    ) {

                System.out.println(tank.getName()+ "[illegal move2] direction: " + direction
                        + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
                return false;
            }}
            else return false; // This can be a mistake!!!


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

                repaint();
                Thread.sleep(tank.getSpeed());
            }
        }
        return true;
    }

    // Process Fire
    private void processFire(Bullet bullet) throws Exception {
        this.bullet = bullet;
        int step = 1;
        while ((bullet.getX() > -14 && bullet.getX() < 590)
                && (bullet.getY() > -14 && bullet.getY() < 590)) {
            if (bullet.getDirection() == Direction.UP) {
                bullet.updateY(-step);
            } else if (bullet.getDirection() == Direction.DOWN) {
                bullet.updateY(step);
            } else if (bullet.getDirection() == Direction.LEFT) {
                bullet.updateX(-step);
            } else {
                bullet.updateX(step);
            }
            if (processInterception()) {
                bullet.destroy();
            }
            repaint();
            Thread.sleep(bullet.getSpeed());
            if (bullet.isDestroyed()) {
                break;
            }
        }
    }

    // Process interception
    private boolean processInterception() {
        String bulletCoordinates = getQuadrant(bullet.getX(), bullet.getY());

//        int v = bullet.getY();
//        int h = bullet.getX();

        int v = Integer.parseInt(bulletCoordinates.split("_")[0]);
        int h = Integer.parseInt(bulletCoordinates.split("_")[1]);

        if (v >= 0 && v < 9 && h >= 0 && h < 9) {
            BFObject bfObject = battleField.scanQuadrant(v, h);
            if (!bfObject.isDestroyed() && !(bfObject instanceof Blank)) {
                battleField.destroyObject(v, h);
                return true;
            }

            // check aggressor
            if (!aggressor.isDestroyed() && checkInterception(getQuadrant(aggressor.getX(), aggressor.getY()), bulletCoordinates)) {
                aggressor.destroy();
                return true;
            }

            // check defender
            if (!defender.isDestroyed() && checkInterception(getQuadrant(defender.getX(), defender.getY()), bulletCoordinates)) {
                defender.destroy();
                return true;
            }
        }
        return false;
    }

    // Check Interception
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

    // Get quadrant
    public String getQuadrant(int x, int y) {
        // input data should be correct
        return y / 64 + "_" + x / 64;
    }

    // writing to file
    public void writeToFile(Action action, Tank tank){

        BufferedWriter output;
        try {
            output = new BufferedWriter(new FileWriter(history, true));
            output.append(tank.getName() + " " + action.toString());
            output.append("\n");
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Erase history file
    public void clearHistory(File history){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(history);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // ActionField constructor without parameter
    public ActionField() throws Exception {
        battleField = new BattleField();
        defender = new T34(battleField);

        String location = battleField.getAggressorLocation();
        aggressor = new BT7(battleField,
                Integer.parseInt(location.split("_")[1]), Integer.parseInt(location.split("_")[0]), Direction.RIGHT);

        bullet = new Bullet(-100, -100, Direction.NONE);

        JFrame frame = new JFrame("BATTLE FIELD, DAY 7");
        frame.setLocation(750, 150);
        frame.setMinimumSize(new Dimension(battleField.getBfWidth(), battleField.getBfHeight() + 22));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
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
        bullet.draw(g);
    }
}