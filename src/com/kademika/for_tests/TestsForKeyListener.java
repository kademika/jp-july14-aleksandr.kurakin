package com.kademika.for_tests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by kurakinaleksandr on 01.12.14.
 */
public class TestsForKeyListener extends JPanel{

    int keyCode;
    JTextField textField;

    public TestsForKeyListener(){

        textField = new JTextField();
        textField.setColumns(5);
        this.add(textField);

//        this.addKeyListener(new KeyListener(){
//            @Override
//            public void keyTyped(KeyEvent e) {
//
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
////                keyCode = e.getKeyCode();
//                if (e.getKeyCode() == KeyEvent.VK_LEFT){
//                    textField.setText("left");
//                    System.out.println("left pressed");
//                }
//
//                System.out.println(keyCode);
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//
//            }
//        });
//        f.setLocation(300, 100);
//        f.setMinimumSize(new Dimension(500, 200));
//
//        f.getContentPane().add(this);
////        f.setFocusable(true);
//        f.pack();
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.setVisible(true);


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
