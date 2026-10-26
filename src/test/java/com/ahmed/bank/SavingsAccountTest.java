package com.ahmed.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SavingsAccountTest {

    @Test
    void deposit_increasesBalance() {
        SavingsAccount account = new SavingsAccount("Ahmed", 1000, 0.10);

        account.deposit(500);

        assertEquals(1500, account.getBalance());
    }

    @Test
    void deposit_rejectsNegativeAmount() {
        SavingsAccount account = new SavingsAccount("Ahmed", 1000, 0.10);

        assertThrows(IllegalArgumentException.class, () -> account.deposit(-500));
        assertEquals(1000, account.getBalance());
    }

    @Test
    void withdraw_rejectsAmountAboveBalance() {
        SavingsAccount account = new SavingsAccount("Ahmed", 1000, 0.10);

        assertThrows(InsufficientFundsException.class, () -> account.withdraw(5000));
        assertEquals(1000, account.getBalance());
    }

    @Test
    void withdraw_recordsTransaction() {
        SavingsAccount account = new SavingsAccount("Ahmed", 1000, 0.10);

        account.withdraw(300);

        assertEquals(1, account.getTransactions().size());
        assertEquals(TransactionType.WITHDRAWAL, account.getTransactions().get(0).getType());
    }

    @Test
    void addInterest_depositsCalculatedInterest() {
        SavingsAccount account = new SavingsAccount("Ahmed", 1000, 0.10);

        account.addInterest();

        assertEquals(1100, account.getBalance());
    }
}