package com.kademika.day12.race;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by kurakinaleksandr on 14.09.14.
 */
public class RaceConditions {
    public static void main(String[] args) {
        Random random = new Random();

        long husband = 1122;
        long wife = 2211;
        Atm atm = new LegacyAtm();

        Set<Runnable> threads = new HashSet<>();
        for (int i = 0; i < 50; i++) {
            threads.add(createWithdrawalThread(atm, wife, random.nextInt(100)));
            threads.add(createWithdrawalThread(atm, husband, random.nextInt(100)));
        }

        for (Runnable r : threads) {
            new Thread(r).start();
        }
    }

    private static Runnable createWithdrawalThread(final Atm atm, final long accountId, final int amount) {
        return new Runnable() {
            @Override
            public void run() {
                atm.checkBalance(accountId);
                atm.withdrawMoney(accountId, amount);
            }
        };
    }
}
