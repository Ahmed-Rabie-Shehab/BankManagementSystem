package com.ahmed.bank.service;

import com.ahmed.bank.Account;
import com.ahmed.bank.Bank;
import com.ahmed.bank.CurrentAccount;
import com.ahmed.bank.SavingsAccount;
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
}