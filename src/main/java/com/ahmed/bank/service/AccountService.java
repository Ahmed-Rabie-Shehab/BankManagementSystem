package com.ahmed.bank.service;

import com.ahmed.bank.Account;
import com.ahmed.bank.AccountNotFoundException;
import com.ahmed.bank.CurrentAccount;
import com.ahmed.bank.Freezable;
import com.ahmed.bank.SavingsAccount;
import com.ahmed.bank.Transaction;
import com.ahmed.bank.dto.CreateAccountRequest;
import com.ahmed.bank.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account findAccount(Long id) {
        return requireAccount(id);
    }

    @Transactional
    public Account createAccount(CreateAccountRequest request) {
        double interestRate = request.interestRate() == null ? 0 : request.interestRate();
        double overdraftLimit = request.overdraftLimit() == null ? 0 : request.overdraftLimit();

        Account account = switch (request.type()) {
            case SAVINGS -> new SavingsAccount(request.ownerName(), request.initialBalance(), interestRate);
            case CURRENT -> new CurrentAccount(request.ownerName(), request.initialBalance(), overdraftLimit);
        };
        return accountRepository.save(account);
    }

    @Transactional
    public void removeAccount(Long id) {
        requireAccount(id);
        accountRepository.deleteById(id);
    }

    @Transactional
    public Account deposit(Long id, double amount) {
        Account account = requireAccount(id);
        account.deposit(amount);
        return accountRepository.save(account);
    }

    @Transactional
    public Account withdraw(Long id, double amount) {
        Account account = requireAccount(id);
        account.withdraw(amount);
        return accountRepository.save(account);
    }

    @Transactional(readOnly = true)
    public List<Transaction> getTransactions(Long id) {
        return requireAccount(id).getTransactions();
    }

    @Transactional
    public Account setFrozen(Long id, boolean frozen) {
        Account account = requireAccount(id);
        if (!(account instanceof Freezable freezable)) {
            throw new IllegalArgumentException("نوع الحساب ده مش قابل للتجميد");
        }
        if (frozen) {
            freezable.freeze();
        } else {
            freezable.unfreeze();
        }
        return accountRepository.save(account);
    }

    private Account requireAccount(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("مفيش حساب بالرقم " + id));
    }
}