package com.ahmed.bank.repository;

import com.ahmed.bank.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}