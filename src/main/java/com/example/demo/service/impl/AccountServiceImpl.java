package com.example.demo.service.impl;

import com.example.demo.common.annotation.Timed;
import com.example.demo.repository.model.AccountEntity;
import com.example.demo.repository.spi.AccountRepository;
import com.example.demo.service.mapper.AccountMapper;
import com.example.demo.service.model.AccountDto;
import com.example.demo.service.model.AccountRequestDto;
import com.example.demo.service.spi.AccountService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;
  private final AccountMapper accountMapper;

  @Timed
  @Override
  public List<AccountDto> findAll() {
    return accountRepository.findAll()
      .stream()
      .map(accountMapper::toAccountDto)
      .collect(Collectors.toList());
  }

  @Override
  public AccountDto findById(String accountNumber) {
    return accountMapper.toAccountDto(accountRepository.findByAccountNumber(accountNumber));
  }

  @Override
  public boolean createAccount(AccountRequestDto accountRequestDto) {
    AccountEntity accountEntity = accountMapper.toAccountEntity(accountRequestDto);
    return accountRepository.create(accountEntity);
  }

}
