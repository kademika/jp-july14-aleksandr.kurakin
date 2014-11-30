package com.kademika.tanksGame;

import com.kademika.shop.Customer;
import com.kademika.shop.constants.Name;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainFrame extends JFrame {
    BattleField battleField;
    ActionField af;
    JFrame frame = new JFrame("Tanks");

    public MainFrame(ActionField af) {

        this.af = af;
        battleField = af.getBattleField();


        frame.setLocation(650, 150);
        frame.setMinimumSize(new Dimension(battleField.getBfWidth(), battleField.getBfHeight() + 22));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(startPanel());
        frame.pack();
        frame.setVisible(true);
    }

    public JPanel startPanel() {

//        final JFrame frame1 = frame;
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
                frame.getContentPane().removeAll();
//                startPanel(frame).setVisible(false);
                frame.getContentPane().add(af);
                frame.revalidate();
                frame.pack();
                frame.setFocusable(true);
//                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                af.setVisible(true);
                SwingWorker workerAF = new SwingWorker() {
                    @Override
                    protected Object doInBackground() throws Exception {
                        af.runTheGameMT();
                        return null;
                    }
                }; workerAF.execute();
            }
        });

        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                frame.cl
            }

        });

        return sp;
    }

    public JPanel gameOverPanel (){
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

        frame.getContentPane().removeAll();
        frame.getContentPane().add(gop);
        frame.getContentPane().add(af);
        frame.revalidate();
        frame.pack();
        frame.setFocusable(true);
        gop.setVisible(true);

        return gop;
    }

}
