package com.ahmed.bank.service;

import com.ahmed.bank.*;
import com.ahmed.bank.dto.CreateAccountRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final Bank bank = new Bank();

    public AccountService() {
        bank.addAccount(new SavingsAccount("Ahmed", 5000, 0.10));
        bank.addAccount(new CurrentAccount("Sara", 1000, 5000));
        bank.addAccount(new SavingsAccount("Yossef", 15000, 0.05));
    }

    public List<Account> getAllAccounts() {
        return bank.getAllAccounts();
    }
    public Account findAccount(int accountNumber) {
        return requireAccount(accountNumber);
    }
    public Account deposit(int accountNumber, double amount) {
        Account account = requireAccount(accountNumber);
        account.deposit(amount);
        return account;
    }

    public Account withdraw(int accountNumber, double amount) {
        Account account = requireAccount(accountNumber);
        account.withdraw(amount);
        return account;
    }

    private Account requireAccount(int accountNumber) {
        Account account = bank.findAccount(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException("مفيش حساب بالرقم " + accountNumber);
        }
        return account;
    }
    public Account createAccount(CreateAccountRequest request) {
        double interestRate = request.interestRate() == null ? 0 : request.interestRate();
        double overdraftLimit = request.overdraftLimit() == null ? 0 : request.overdraftLimit();

        Account account = switch (request.type()) {
            case SAVINGS -> new SavingsAccount(request.ownerName(), request.initialBalance(), interestRate);
            case CURRENT -> new CurrentAccount(request.ownerName(), request.initialBalance(), overdraftLimit);
        };
        bank.addAccount(account);
        return account;
    }

    public void removeAccount(int accountNumber) {
        requireAccount(accountNumber);
        bank.removeAccount(accountNumber);
    }

    public List<Transaction> getTransactions(int accountNumber) {
        return requireAccount(accountNumber).getTransactions();
    }

    public Account setFrozen(int accountNumber, boolean frozen) {
        Account account = requireAccount(accountNumber);
        if (!(account instanceof Freezable freezable)) {
            throw new IllegalArgumentException("نوع الحساب ده مش قابل للتجميد");
        }
        if (frozen) {
            freezable.freeze();
        } else {
            freezable.unfreeze();
        }
        return account;
    }
}