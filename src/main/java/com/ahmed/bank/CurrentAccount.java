package com.ahmed.bank;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CURRENT")
public class CurrentAccount extends Account implements Freezable {

    private double overdraftLimit;

    private boolean frozen;

    protected CurrentAccount() {
    }

    public CurrentAccount(String ownerName, double balance, double overdraftLimit) {
        super(ownerName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (frozen) {
            throw new IllegalStateException("الحساب مجمد");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("المبلغ لازم يكون أكبر من صفر");
        }
        if (balance - amount < -overdraftLimit) {
            throw new InsufficientFundsException("تعديت حد السحب على المكشوف: " + overdraftLimit);
        }
        balance -= amount;
        recordTransaction(TransactionType.WITHDRAWAL, amount);
    }

    @Override
    public void freeze() {
        frozen = true;
    }

    @Override
    public void unfreeze() {
        frozen = false;
    }

    @Override
    public boolean isFrozen() {
        return frozen;
    }
}