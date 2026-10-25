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
    public Account getAccount(@PathVariable int id) {
        return accountService.findAccount(id);
    }
    @PostMapping("/{id}/deposit")
    public Account deposit(@PathVariable int id, @RequestBody AmountRequest request) {
        return accountService.deposit(id, request.amount());
    }

    @PostMapping("/{id}/withdraw")
    public Account withdraw(@PathVariable int id, @RequestBody AmountRequest request) {
        return accountService.withdraw(id, request.amount());
    }
}