package com.ahmed.bank.dto;

import com.ahmed.bank.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateAccountRequest(
        @NotBlank(message = "اسم صاحب الحساب مطلوب")
        String ownerName,

        @PositiveOrZero(message = "الرصيد الابتدائي لازم يكون صفر أو أكبر")
        double initialBalance,

        @NotNull(message = "نوع الحساب مطلوب")
        AccountType type,

        Double interestRate,
        Double overdraftLimit
) {
}