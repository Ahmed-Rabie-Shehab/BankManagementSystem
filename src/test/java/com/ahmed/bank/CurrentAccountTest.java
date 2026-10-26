package com.ahmed.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CurrentAccountTest {

    @Test
    void withdraw_allowsBalanceToGoNegativeWithinOverdraft() {
        CurrentAccount account = new CurrentAccount("Sara", 1000, 5000);

        account.withdraw(3000);

        assertEquals(-2000, account.getBalance());
    }

    @Test
    void withdraw_rejectsAmountBeyondOverdraftLimit() {
        CurrentAccount account = new CurrentAccount("Sara", 1000, 5000);

        assertThrows(InsufficientFundsException.class, () -> account.withdraw(9000));
        assertEquals(1000, account.getBalance());
    }

    @Test
    void withdraw_rejectedWhenAccountIsFrozen() {
        CurrentAccount account = new CurrentAccount("Sara", 1000, 5000);

        account.freeze();

        assertTrue(account.isFrozen());
        assertThrows(IllegalStateException.class, () -> account.withdraw(100));
        assertEquals(1000, account.getBalance());
    }

    @Test
    void withdraw_worksAgainAfterUnfreeze() {
        CurrentAccount account = new CurrentAccount("Sara", 1000, 5000);

        account.freeze();
        account.unfreeze();
        account.withdraw(100);

        assertEquals(900, account.getBalance());
    }
}