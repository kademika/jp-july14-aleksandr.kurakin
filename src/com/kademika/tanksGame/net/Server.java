package com.kademika.tanksGame.net;

import com.kademika.tanksGame.ActionField;
import com.kademika.tanksGame.movingObjects.Action;
import com.kademika.tanksGame.movingObjects.Tank;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by kurakinaleksandr on 27.04.15.
 */
public class Server {

    private ActionField af;
    private ObjectInputStream serverIn;
    private ObjectOutputStream serverOut;
    private Object start;
    private Tank userTank;
    private Tank clientTank;

    public void setClientTank(Tank clientTank){
        this.clientTank = clientTank;
    }

    public void setUserTank(Tank userTank) {
        this.userTank = userTank;
    }

    public void setAf(ActionField af) {
        this.af = af;
    }

    public void Server() {
    }

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    ServerSocket ss = new ServerSocket(8080);
                    Socket socket = ss.accept();

                    serverIn = new ObjectInputStream(socket.getInputStream());
                    serverOut = new ObjectOutputStream(socket.getOutputStream());

                    while ((start = serverIn.readObject()) != null) { // send -1
//                        start = serverIn.readObject();
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
        if (!(start == null) && start instanceof String && start.equals("ping")) {
            setUpClientTank();
            af.callLoadAfMp();
        } else if (!(start == null)) {
//            af.setUpClientTank((Action) start);
//            userTank.setAction((Action) start);
        }
    }

    public void setUpClientTank() {
        try {
            serverOut.writeObject(clientTank);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendCommand(Action action) {
        try {
            serverOut.writeObject(action);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

