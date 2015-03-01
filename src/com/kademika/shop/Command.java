package com.kademika.shop;

import com.kademika.shop.constants.Commands;
import java.io.Serializable;

/**
 * Created by kurakinaleksandr on 20.02.15.
 */
public class Command implements Serializable {
    Commands command;

    public Command() {
    }

    public void setCommand(Commands command) {
        this.command = command;
    }

    public Commands getCommand() {
        return command;
    }
}
