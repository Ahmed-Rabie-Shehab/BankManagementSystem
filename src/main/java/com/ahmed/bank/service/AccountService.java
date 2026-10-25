package com.ahmed.bank.service;

import com.ahmed.bank.Account;
import com.ahmed.bank.Bank;
import com.ahmed.bank.CurrentAccount;
import com.ahmed.bank.SavingsAccount;
import org.springframework.stereotype.Service;
import com.ahmed.bank.AccountNotFoundException;

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
}