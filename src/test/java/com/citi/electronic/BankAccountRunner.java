package com.citi.electronic;

/**
 * Created by SubramanianM on 13/11/2017.
 */
import java.util.Random;


public class BankAccountRunner {

    private int MIN = 1;
    private int depositMax = 100;
    private int withDrawMax = 25;
    private static Bank bank = new Bank(10);

    Random number = new Random();
    BankAccount account = bank.getRandomBankAccount();

    Thread deposit1 = new Thread(new DepositRunnable(account, (number.nextInt(depositMax - MIN + 1) + MIN), 1));
    Thread deposit2 = new Thread(new DepositRunnable(account, (number.nextInt(depositMax - MIN + 1) + MIN), 2));
    Thread deposit3 = new Thread(new DepositRunnable(account, (number.nextInt(depositMax - MIN + 1) + MIN), 3));

    Thread withDraw4 = new Thread(new WithdrawRunnable(account, (number.nextInt(withDrawMax - MIN + 1) + MIN), 4));
    Thread withDraw5 = new Thread(new WithdrawRunnable(account, (number.nextInt(withDrawMax - MIN + 1) + MIN), 5));
    Thread withDraw6 = new Thread(new WithdrawRunnable(account, (number.nextInt(withDrawMax - MIN + 1) + MIN), 6));
    Thread withDraw7 = new Thread(new WithdrawRunnable(account, (number.nextInt(withDrawMax - MIN + 1) + MIN), 7));

    public static void main(String[] args) {

        System.out.println("****************************************************");
        System.out.println("Thread Id - Deposit or Withdrawal + Amount & Balance");
        System.out.println("*****************************************************");

        //Uncomment to test for multiple BankAccounts simultaneously
        //for(int i = 0; i < 3; i++)
            new BankAccountRunner();
    }

    public BankAccountRunner() {
        deposit1.start();
        withDraw4.start();
        deposit2.start();
        withDraw5.start();
        deposit3.start();
        withDraw6.start();
        withDraw7.start();
    }

}
