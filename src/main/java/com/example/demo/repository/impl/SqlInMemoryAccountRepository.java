package com.example.demo.repository.impl;

import com.example.demo.repository.impl.rowmapper.AccountEntityRowMapper;
import com.example.demo.repository.model.AccountEntity;
import com.example.demo.repository.spi.AccountRepository;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@RequiredArgsConstructor
public class SqlInMemoryAccountRepository implements AccountRepository {

  private final JdbcTemplate jdbcTemplate;
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
    var sql = "UPDATE account SET amount = :amount, owner = :owner WHERE account_number = :accountNumber";
    int affectedRows = namedParameterJdbcTemplate.update(sql, Map.of(
      "amount", accountEntity.getAmount(),
      "owner", accountEntity.getOwner(),
      "accountNumber", accountEntity.getAccountNumber()
    ));
    return affectedRows > 0;
  }

  @Override
  public boolean updateAccountConError(AccountEntity accountEntity) {
    var sql = "UPDATE account SET amount = :amount owner = :owner WHERE account_number = :accountNumber";
    int affectedRows = namedParameterJdbcTemplate.update(sql, Map.of(
      "amount", accountEntity.getAmount(),
      "owner", accountEntity.getOwner(),
      "accountNumber", accountEntity.getAccountNumber()
    ));
    return affectedRows > 0;
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
    int affectedRows = jdbcTemplate.update("INSERT INTO account (owner, account_number, amount) VALUES (?, ?, ?)",
      accountEntity.getOwner(), accountEntity.getAccountNumber(), accountEntity.getAmount());
    return affectedRows == 1;
  }

}
