package com.citi.electronic;

/**
 * Created by SubramanianM on 13/11/2017.
 */
public class WithdrawRunnable implements Runnable{
    private BankAccount account;
    private int amount;
    private int id;

    public WithdrawRunnable(BankAccount anAccount, int anAmount, int anID) {
        account = anAccount;
        amount = anAmount;
        id = anID;
    }

    public void run() {
        try {
            account.withdraw(amount, id);
        } catch (InterruptedException exception) {}
    }
}

