package com.kademika.day12.race;

/**
 * Created by kurakinaleksandr on 14.09.14.
 */
public class LegacyAtm implements Atm {

    private int totalAmount = 10000;

    @Override
    public void checkBalance(long accountId) {
        System.out.println(accountId + " going to withdraw some money, balance is: " + totalAmount);
    }



    @Override
    public synchronized void withdrawMoney(long accountId, int amount) {
//        synchronized (this) {
            if (allowWithdrawal(accountId, amount)) {
                updateBalance(accountId, amount, TransactionType.WITHDRAWAL);
//            }
        }
    }

    private void updateBalance(long accountId, int amount, TransactionType type) {
        System.out.println("Successful " + type + " account: " + accountId + " amount: " + amount);
        if (type == TransactionType.WITHDRAWAL){
            totalAmount -= amount;
            System.out.println(accountId + " got the money");
        } else if (type == TransactionType.DEPOSIT){
            totalAmount += amount;
        }
    }

    private boolean allowWithdrawal(long accountId, int amount) {
        if (totalAmount>=amount) {
            return true;
        } else {
            System.out.println("There is no enough money");
            return false;
        }
    }
}
