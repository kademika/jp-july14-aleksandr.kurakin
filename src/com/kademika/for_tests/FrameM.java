package com.kademika.for_tests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by kurakinaleksandr on 06.12.14.
 */
public class FrameM extends JFrame {

    TestsForKeyListener testsForKeyListener = new TestsForKeyListener();
    int keyCode;

    public FrameM(){
    this.addKeyListener(new KeyListener(){
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            keyCode = e.getKeyCode();
            if (e.getKeyCode() == KeyEvent.VK_LEFT){
//                testsForKeyListener.textField.setText("left");
                System.out.println("left pressed");
            }

            System.out.println(keyCode);
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    });
    this.setLocation(300, 100);
    this.setMinimumSize(new Dimension(500, 200));

    this.getContentPane().add(testsForKeyListener);
//        f.setFocusable(true);
    this.pack();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);


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
