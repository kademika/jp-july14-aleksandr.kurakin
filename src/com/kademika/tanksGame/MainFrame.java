package com.kademika.tanksGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class MainFrame extends JFrame {
    ActionField af;

    public MainFrame() throws Exception {

        af = new ActionField();
        af.setMf(this);
        this.setLocation(650, 150);
        this.setMinimumSize(new Dimension(576, 576 + 22));
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                af.recogniseKeyEvent(e);
                System.out.println("Pressed from MF");
            }
        });
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Tanks");
        this.getContentPane().add(startPanel());
        this.pack();
        this.setVisible(true);
        this.setFocusable(true);

    }

    public void loadAF(String userTank) {
        this.getContentPane().removeAll();
        this.getContentPane().add(af);
        this.revalidate();
        this.pack();
        af.setVisible(true);
        af.setUpSingleGame(userTank);
        try {
            af.runTheGameMT();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void waitForConnectionS(String userTank){
        JPanel waitForConnection = new JPanel();
        waitForConnection.setLayout(new GridBagLayout());
        JLabel wait = new JLabel("wait for connection...");
        waitForConnection.add(wait, new GridBagConstraints(
                0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(0, 0, 0, 0), 0, 0));
        this.getContentPane().removeAll();
        this.getContentPane().add(waitForConnection);
        this.revalidate();
        this.pack();
        waitForConnection.setVisible(true);
        af.setUpMultiplayerGameServer(userTank);
    }

    public void waitForConnectionC(String address){
        JPanel waitForConnection = new JPanel();
        waitForConnection.setLayout(new GridBagLayout());
        JLabel wait = new JLabel("wait for connection...");
        waitForConnection.add(wait, new GridBagConstraints(
                0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(0, 0, 0, 0), 0, 0));
        this.getContentPane().removeAll();
        this.getContentPane().add(waitForConnection);
        this.revalidate();
        this.pack();
        waitForConnection.setVisible(true);
        af.setUpMultiplayerGameClient(address);
    }

    public void loadAfMp(){
        this.getContentPane().removeAll();
        this.getContentPane().add(af);
        this.revalidate();
        this.pack();
        af.setVisible(true);
        try {
            af.runTheGameMT();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void replay() {
        this.getContentPane().removeAll();
        this.getContentPane().add(af);
        this.revalidate();
        this.pack();
        af.setVisible(true);
        try {
            af.replay();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }


    public JPanel startPanel() {

        JPanel sp = new JPanel();
        sp.setLayout(new GridBagLayout());

        JLabel lName = new JLabel("Choose your tank");
        String[] items = {"Aggressor", "Defender"};
        final JComboBox patternList = new JComboBox(items);
        JButton buttonStart = new JButton("Start the game");
        JButton buttonStartMP = new JButton("Multiplayer game");
        JLabel enterIP = new JLabel("Enter IP:port");
        final JTextField address = new JTextField();
        address.setColumns(20);
        JButton buttonJoinGame = new JButton("Join game");

        sp.add(lName, new GridBagConstraints(
                0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(0, 0, 0, 0), 0, 0));
        sp.add(patternList, new GridBagConstraints(
                0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(0, 0, 0, 0), 0, 0));
        sp.add(buttonStart, new GridBagConstraints(
                0, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(0, 0, 0, 0), 0, 0));
        sp.add(buttonStartMP, new GridBagConstraints(
                0, 4, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(0, 0, 0, 0), 0, 0));
        sp.add(enterIP, new GridBagConstraints(
                0, 5, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(0, 0, 0, 0), 0, 0));
        sp.add(address, new GridBagConstraints(
                0, 7, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(0, 0, 0, 0), 0, 0));
        sp.add(buttonJoinGame, new GridBagConstraints(
                0, 8, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(0, 0, 0, 0), 0, 0));

        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAF(patternList.getSelectedItem().toString()); // here
            }
        });

        buttonStartMP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                waitForConnectionS(patternList.getSelectedItem().toString());
            }
        });

        buttonJoinGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                waitForConnectionC(address.getText());
            }
        });

        return sp;
    }


    public void gameOverPanel() { //JPanel
        JPanel gop = new JPanel();
        gop.setLayout(new GridBagLayout());

        JButton buttonReplay = new JButton("Replay");
        JButton buttonExit = new JButton("EXIT");

        gop.add(buttonReplay, new GridBagConstraints(
                0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(0, 0, 0, 0), 0, 0));
        gop.add(buttonExit, new GridBagConstraints(
                0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(0, 0, 0, 0), 0, 0));


        this.getContentPane().removeAll();
        this.getContentPane().add(gop);
        this.revalidate();
        this.pack();
        gop.setVisible(true);

        buttonReplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                replay();
//                af.replay();
            }

        });
//        return gop;
    }

}
