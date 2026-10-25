package com.ahmed.bank.dto;

import com.ahmed.bank.AccountType;

public record CreateAccountRequest(
        String ownerName,
        double initialBalance,
        AccountType type,
        Double interestRate,
        Double overdraftLimit
) {
}