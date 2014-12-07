package com.kademika.tanksGame;

import com.kademika.shop.Customer;
import com.kademika.shop.constants.Name;
import com.kademika.tanksGame.tanks.*;
import com.kademika.tanksGame.tanks.Action;

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
                af.setAction(e);
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

    public void loadAF() {
        this.getContentPane().removeAll();
        this.getContentPane().add(af);
        this.revalidate();
        this.pack();
        af.setVisible(true);

//                SwingWorker workerAF = new SwingWorker() {
//                    @Override
//                    protected Object doInBackground() throws Exception {

        try {
            af.runTheGameMT();
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
        JButton buttonExit = new JButton("EXIT");

        sp.add(lName, new GridBagConstraints(
                0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(0, 0, 0, 0), 0, 0));
        sp.add(patternList, new GridBagConstraints(
                0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(0, 0, 0, 0), 0, 0));
        sp.add(buttonStart, new GridBagConstraints(
                0, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(0, 0, 0, 0), 0, 0));
        sp.add(buttonExit, new GridBagConstraints(
                0, 4, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(0, 0, 0, 0), 0, 0));


        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAF();
            }
        });

        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }

        });

        return sp;
    }

    public JPanel gameOverPanel() {
        JPanel gop = new JPanel();
        gop.setLayout(new GridBagLayout());

        JButton buttonReplay = new JButton("Replay");
        JButton buttonExit = new JButton("EXIT");

        gop.add(buttonReplay, new GridBagConstraints(
                0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(0, 0, 0, 0), 0, 0));
        gop.add(buttonExit, new GridBagConstraints(
                0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(0, 0, 0, 0), 0, 0));

        buttonReplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                af.replay();
            }

        });

        this.getContentPane().removeAll();
        this.getContentPane().add(gop);
        this.revalidate();
        this.pack();
        gop.setVisible(true);

        return gop;
    }

}
