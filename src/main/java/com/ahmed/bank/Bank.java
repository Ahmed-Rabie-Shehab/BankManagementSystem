package com.ahmed.bank;
import java.util.HashMap;

public class Bank {

    private HashMap<Integer, Account> accounts = new HashMap<>();

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public Account findAccount(int accountNumber) {
        return accounts.get(accountNumber);
    }

    public void removeAccount(int accountNumber) {
        accounts.remove(accountNumber);
    }

    public double totalBalance() {
        double total = 0;
        for (Account account : accounts.values()) {
            total += account.getBalance();
        }
        return total;
    }

    public void printAll() {
        for (Account account : accounts.values()) {
            System.out.println(
                    account.getAccountNumber() + " | " +
                            account.getOwnerName() + " | " +
                            account.getBalance()
            );
        }
    }
}