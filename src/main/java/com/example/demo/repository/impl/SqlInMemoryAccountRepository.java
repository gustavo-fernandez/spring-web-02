package com.example.demo.repository.impl;

import com.example.demo.repository.impl.rowmapper.AccountEntityRowMapper;
import com.example.demo.repository.model.AccountEntity;
import com.example.demo.repository.spi.AccountRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

@RequiredArgsConstructor
public class SqlInMemoryAccountRepository implements AccountRepository {

  private final JdbcTemplate jdbcTemplate;

  @Override
  public AccountEntity findByAccountNumber(String accountNumber) {
    return jdbcTemplate.queryForObject(
      "SELECT account_number, owner, amount FROM account WHERE account_number = ?",
      new AccountEntityRowMapper(),
      accountNumber
    );
  }

  @Override
  public boolean updateAccount(AccountEntity accountEntity) {
    return false;
  }

  @Override
  public List<AccountEntity> findAll() {
    return jdbcTemplate.query(
      "SELECT account_number, owner, amount FROM account",
      new AccountEntityRowMapper()
    );
  }

  @Override
  public boolean create(AccountEntity accountEntity) {
    return false;
  }

}
