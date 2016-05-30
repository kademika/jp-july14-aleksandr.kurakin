package com.kademika.tanksGame.net;

/**
 * Created by kurakinaleksandr on 05.05.16.
 */

import com.kademika.tanksGame.ActionField;
import com.kademika.tanksGame.movingObjects.Action;
import com.kademika.tanksGame.movingObjects.Tank;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by kurakinaleksandr on 27.04.15.
 */
public class Client {
    private Socket socket;
    private ActionField af;
    private String address;
    private ObjectOutputStream clientOut;
    private ObjectInputStream clientIn;
    private Object start;
    private Tank userTank;
    private Tank clientTank;

//    public void setUserTank(Tank userTank) {
//        this.userTank = userTank;
//    }

    public void setAf(ActionField af) {
        this.af = af;
    }

    public Client() {

    }

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket(address, 8080);

                    clientOut = new ObjectOutputStream(socket.getOutputStream());
                    clientIn = new ObjectInputStream(socket.getInputStream());

                    System.out.println("Client started and ready!");

                    clientOut.writeObject("ping");

                    while ((start = clientIn.readObject()) != null) {
//                        start = clientIn.readObject();
                        checkCommand(start);
                        start = null;
                    }

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void checkCommand(Object start) {
        if (!(start == null) && start instanceof Tank) {
            userTank = (Tank) start;
            af.setUserTank(userTank);
            System.out.println("Client seted tank: " + userTank.getName());
            af.callLoadAfMp();
        } else if (!(start == null)) {
            af.setUpClientTank((Action) start);
//            userTank.setAction((Action) start);
        }
    }

    public void sendCommand(Action action) {
        try {
            clientOut.writeObject(action);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUserTank(Tank tank) {
    }
}

