package com.kademika.for_tests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by kurakinaleksandr on 01.12.14.
 */
public class TestsForKeyListener extends JPanel{

    int keyCode;
    JTextField textField;

    public TestsForKeyListener(){


        JFrame f = new JFrame("Tests for keyListener");
        textField = new JTextField();
        textField.setColumns(5);
        this.add(textField);

        this.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
//                keyCode = e.getKeyCode();
                if (e.getKeyCode() == KeyEvent.VK_LEFT){
                    textField.setText("left");
                    System.out.println("left pressed");
                }

                System.out.println(keyCode);
            }
        });
        f.setLocation(300, 100);
        f.setMinimumSize(new Dimension(500, 200));

        f.getContentPane().add(this);
        f.setFocusable(true);
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);


//SwingWorker worker = new SwingWorker() {
//    @Override
//    protected Object doInBackground() throws Exception {
////        while (true){
//            textField.setText(Integer.toString(keyCode));
//            return null;
//        }
//
////    }
//}; worker.execute();



    }

}
