package com.ahmed.bank.controller;

import com.ahmed.bank.Account;
import com.ahmed.bank.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import com.ahmed.bank.dto.AmountRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import com.ahmed.bank.Transaction;
import com.ahmed.bank.dto.CreateAccountRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }
    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Long id) {
        return accountService.findAccount(id);
    }
    @PostMapping("/{id}/deposit")
    public Account deposit(@PathVariable Long id, @RequestBody AmountRequest request) {
        return accountService.deposit(id, request.amount());
    }

    @PostMapping("/{id}/withdraw")
    public Account withdraw(@PathVariable Long id, @RequestBody AmountRequest request) {
        return accountService.withdraw(id, request.amount());
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(@RequestBody CreateAccountRequest request) {
        return accountService.createAccount(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable Long id) {
        accountService.removeAccount(id);
    }

    @GetMapping("/{id}/transactions")
    public List<Transaction> getTransactions(@PathVariable Long id) {
        return accountService.getTransactions(id);
    }

    @PostMapping("/{id}/freeze")
    public Account freeze(@PathVariable Long id) {
        return accountService.setFrozen(id, true);
    }

    @PostMapping("/{id}/unfreeze")
    public Account unfreeze(@PathVariable Long id) {
        return accountService.setFrozen(id, false);
    }
}