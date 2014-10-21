package com.kademika.day12.race;

/**
 * Created by kurakinaleksandr on 14.09.14.
 */
public interface Atm {
    void checkBalance(long accountId);

    void withdrawMoney(long accountId, int amount);
}
