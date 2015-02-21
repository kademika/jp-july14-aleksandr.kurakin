package com.kademika.shop;

import com.kademika.shop.constants.Name;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;

public class UserInterface  {
//    private Shop shop;
    SimpleClient client;


    public UserInterface(){

        client = new SimpleClient();
        try {
            client.startClient();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        JFrame f = new JFrame("Welcome to our shop.");
        f.setLocation(300, 100);
        f.setMinimumSize(new Dimension(500, 200));

        f.getContentPane().add(createUI());

        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

// here we can start our Client

    }

    private JPanel createUI (){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        JLabel lName = new JLabel("Your name: ");
        final JTextField tName = new JTextField();
        tName.setColumns(25);
        panel.add(lName, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(tName, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        Name [] items =  client.getCatalog();
//                {Name.CHICKEN, Name.DUCK, Name.EAGLE, Name.OWL, Name.PARROT, Name.CANARY};
        JLabel lProduct = new JLabel("Select bird: ");
        final JComboBox patternList = new JComboBox(items);

        panel.add(lProduct, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(patternList, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(0, 3, 0, 0), 0, 0));

        JLabel lQuantity = new JLabel("Enter Quantity: ");
        NumberFormat nf = NumberFormat.getNumberInstance();
        final JFormattedTextField tlQuantity = new JFormattedTextField(nf);
        tlQuantity.setColumns(2);
        tlQuantity.setValue(1);

        panel.add(lQuantity, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));
        panel.add(tlQuantity, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(0, 0, 0, 0), 0, 0));

        JButton btnBuy = new JButton("Buy");
        panel.add(btnBuy, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.CENTER, 0, new Insets(0, 0, 0, 0), 0, 0));

        btnBuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer c = new Customer();
                c.setName(tName.getText());

                int qnt = Integer.parseInt(tlQuantity.getText());
                Name birdName = (Name) patternList.getSelectedItem();
                Purchase prchs = new Purchase(c.getName(),birdName, qnt); // then transfer this Purchase with network to the shop
                client.makePurchase(prchs);
            }
        });

        return panel;
    }
}
