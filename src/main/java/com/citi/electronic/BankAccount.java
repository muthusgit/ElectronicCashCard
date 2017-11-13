package com.citi.electronic;

/**
 * Created by SubramanianM on 13/11/2017.
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiConsumer;


public class BankAccount {

    private final Lock balanceLock = new ReentrantLock();
    private Condition enoughFundsCondition;
    private int balance;


    public BankAccount(int accountsCount) {
        balance = 0;
        enoughFundsCondition = balanceLock.newCondition();
    }


    BiConsumer<Integer, Integer> depositMoney = (amount, id) -> {
        int newBalance = balance + amount;
        System.out.println("Thread " + id + "-" + "Deposit Amount $" + amount);
        System.out.println("Thread " + id + "-" + "Balance is $" + newBalance);
        balance = newBalance;
    };


    BiConsumer<Integer, Integer> withDrawMoney = (amount, id) -> {
        if(balance < amount) {
            System.out.println("Thread " + id + "-" + "Withdraws Amount $" + amount);
            System.out.println("Thread" + id + "-" + "Blocked - insufficient funds");
        }
        else {
            System.out.println("Thread " + id + "-" + "Withdraws Amount $" + amount);
            int newBalance = balance - amount;
            System.out.println("Thread " + id + "-" + "Balance is $" + newBalance);
            balance = newBalance;
        }
    };

    public void deposit(int amount, int id) throws InterruptedException {

        balanceLock.lock();
        try {
            depositMoney.accept(amount, id);
            enoughFundsCondition.signalAll();
        } finally {
            balanceLock.unlock();
        }
    }



    public void withdraw(int amount, int id) throws InterruptedException {

        balanceLock.lock();
        try {
            while (balance < amount)
                enoughFundsCondition.await();

            withDrawMoney.accept(amount, id);

            enoughFundsCondition.signalAll();
        } finally {
            balanceLock.unlock();
        }
    }


    public int getBalance() {
        return balance;
    }

}

