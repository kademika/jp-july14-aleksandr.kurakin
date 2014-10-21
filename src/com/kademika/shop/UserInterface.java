package com.kademika.shop;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class UserInterface  {
    private Shop shop;

    public UserInterface(Shop shop){
        this.shop = shop;

        JFrame f = new JFrame("Welcome to our shop.");
        f.setLocation(300, 100);
        f.setMinimumSize(new Dimension(800, 600));

        f.getContentPane().add(createUI());

        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

    }

    private JPanel createUI (){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        JLabel lName = new JLabel("Your name: ");
        JTextField tName = new JTextField();
        tName.setColumns(25);
//        panel.add(lName, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0,),
//                0, 0));
        panel.add(tName);

        String [] items =  shop.getCatalog();
        JLabel lProduct = new JLabel("Select bird ");
        JComboBox patternList = new JComboBox(items);
        panel.add(lProduct);
        panel.add(patternList);

        JLabel lQuantity = new JLabel("Enter Quantity ");
        NumberFormat nf = NumberFormat.getNumberInstance();
        JFormattedTextField tlQuantity = new JFormattedTextField(nf);
        tlQuantity.setValue(1);
        panel.add(lQuantity);
        panel.add(tlQuantity);

        JButton btnBuy = new JButton("Buy");
        panel.add(btnBuy);

        return panel;
    }
}
