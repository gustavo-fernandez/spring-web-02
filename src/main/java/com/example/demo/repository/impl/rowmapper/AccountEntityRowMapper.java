package com.example.demo.repository.impl.rowmapper;

import com.example.demo.repository.model.AccountEntity;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class AccountEntityRowMapper implements RowMapper<AccountEntity> {
  @Override
  public AccountEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
    String accountNumber = rs.getString("account_number");
    String owner = rs.getString("owner");
    BigDecimal amount = rs.getBigDecimal("amount");
    return AccountEntity.builder()
      .accountNumber(accountNumber)
      .owner(owner)
      .amount(amount)
      .build();
  }

}
