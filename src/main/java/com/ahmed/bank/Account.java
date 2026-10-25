package com.ahmed.bank;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {

    private int accountNumber;
    private String ownerName;
    protected double balance;
    private static int accountsCount = 0;
    private final List<Transaction> transactions = new ArrayList<>();

    public Account(String ownerName, double balance) {
        accountsCount++;
        this.accountNumber = accountsCount;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public static int getAccountsCount() {
        return accountsCount;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    protected void recordTransaction(TransactionType type, double amount) {
        transactions.add(new Transaction(type, amount, balance));
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("مبلغ الإيداع لازم يكون أكبر من صفر");
        }
        balance += amount;
        recordTransaction(TransactionType.DEPOSIT, amount);
    }

    public abstract void withdraw(double amount);
}