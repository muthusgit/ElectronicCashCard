package com.citi.electronic;

/**
 * Created by SubramanianM on 13/11/2017.
 */


import java.util.ArrayList;
import java.util.Random;

public class Bank {
    private ArrayList<BankAccount> accounts;

    public Bank(int accountsCount) {
        accounts = new ArrayList<BankAccount>();
        for (int i = 0; i < accountsCount; i++) {
            accounts.add(new BankAccount(i));
        }
    }

    public BankAccount getRandomBankAccount() {
        return accounts.get(new Random().nextInt(accounts.size()));
    }


}