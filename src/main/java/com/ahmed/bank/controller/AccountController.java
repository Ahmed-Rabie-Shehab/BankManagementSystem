package com.ahmed.bank.controller;

import com.ahmed.bank.Account;
import com.ahmed.bank.Transaction;
import com.ahmed.bank.dto.AmountRequest;
import com.ahmed.bank.dto.CreateAccountRequest;
import com.ahmed.bank.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Account deposit(@PathVariable Long id, @Valid @RequestBody AmountRequest request) {
        return accountService.deposit(id, request.amount());
    }

    @PostMapping("/{id}/withdraw")
    public Account withdraw(@PathVariable Long id, @Valid @RequestBody AmountRequest request) {
        return accountService.withdraw(id, request.amount());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(@Valid @RequestBody CreateAccountRequest request) {
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