package com.example.demo.repository.impl;

import com.example.demo.repository.db.AccountDatabase;
import com.example.demo.repository.model.AccountEntity;
import com.example.demo.repository.spi.AccountRepository;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryAccountRepository implements AccountRepository {
  
  @Override
  public AccountEntity findByAccountNumber(String accountNumber) {
    return AccountDatabase.getAll().get(accountNumber);
  }

  @Override
  public boolean updateAccount(AccountEntity accountEntity) {
    return AccountDatabase.update(accountEntity);
  }

  @Override
  public List<AccountEntity> findAll() {
    return AccountDatabase.getAll().values()
      .stream()
      .collect(Collectors.toList());
  }

  @Override
  public boolean create(AccountEntity accountEntity) {
    return AccountDatabase.insert(accountEntity);
  }
}
