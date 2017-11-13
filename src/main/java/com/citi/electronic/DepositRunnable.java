package com.citi.electronic;

/**
 * Created by SubramanianM on 13/11/2017.
 */
public class DepositRunnable implements Runnable{
    private static final int DELAY = 10;
    private BankAccount account;
    private int amount;
    private int id;

    public DepositRunnable(BankAccount anAccount, int anAmount, int tID) {
        account = anAccount;
        amount = anAmount;
        id = tID;
    }

    public void run() {
        try {
            account.deposit(amount, id);
            Thread.sleep(DELAY);
        } catch (InterruptedException exception) {}
    }
}

