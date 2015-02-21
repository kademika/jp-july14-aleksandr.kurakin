package com.kademika.shop;

import java.io.Serializable;

/**
 * Created by kurakinaleksandr on 20.02.15.
 */
public class Command implements Serializable {
    String command;

    public Command() {

    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
