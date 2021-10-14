package com.example.demo.service.impl;

import com.example.demo.common.annotation.Timed;
import com.example.demo.repository.model.AccountEntity;
import com.example.demo.repository.spi.AccountRepository;
import com.example.demo.service.mapper.AccountMapper;
import com.example.demo.service.model.AccountDto;
import com.example.demo.service.model.AccountRequestDto;
import com.example.demo.service.spi.AccountService;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.Cacheable;

@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;
  private final AccountMapper accountMapper;

  @Timed
  @Override
  @Cacheable(cacheNames = "accounts", key = "#root.method")
  public List<AccountDto> findAll() {
    wait(Duration.ofSeconds(4));
    return accountRepository.findAll()
      .stream()
      .map(accountMapper::toAccountDto)
      .collect(Collectors.toList());
  }

  @SneakyThrows
  private static final void wait(Duration duration) {
    Thread.sleep(duration.toMillis());
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
