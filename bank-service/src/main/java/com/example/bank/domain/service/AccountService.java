package com.example.bank.domain.service;

import com.example.bank.domain.entity.Account;
import com.example.bank.domain.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Account getReferenceById(Long id) {
        return accountRepository.getReferenceById(id);
    }
}
