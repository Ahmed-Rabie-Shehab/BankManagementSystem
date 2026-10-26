package com.ahmed.bank.dto;

import jakarta.validation.constraints.Positive;

public record AmountRequest(
        @Positive(message = "المبلغ لازم يكون أكبر من صفر")
        double amount
) {
}