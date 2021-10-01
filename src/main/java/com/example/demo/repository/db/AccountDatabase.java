package com.example.demo.repository.db;

import com.example.demo.repository.model.AccountEntity;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AccountDatabase {

  private static final Map<String, AccountEntity> ACCOUNT_TABLE;

  static {
    ACCOUNT_TABLE = new HashMap<>();
    AccountEntity accountEntity1 = AccountEntity.builder()
      .accountNumber("123456")
      .owner("Alfonso Ugarte")
      .amount(new BigDecimal("200.0"))
      .build();
    ACCOUNT_TABLE.put(accountEntity1.getAccountNumber(), accountEntity1);

    AccountEntity accountEntity2 = AccountEntity.builder()
      .accountNumber("ABCDEF")
      .owner("Cristian Galvez")
      .amount(new BigDecimal("100.0"))
      .build();
    ACCOUNT_TABLE.put(accountEntity2.getAccountNumber(), accountEntity2);
  }

  public static Map<String, AccountEntity> getAll() {
    return ACCOUNT_TABLE;
  }

  public static boolean update(AccountEntity accountEntity) {
    if (!ACCOUNT_TABLE.containsKey(accountEntity.getAccountNumber())) {
      return false;
    }
    ACCOUNT_TABLE.put(accountEntity.getAccountNumber(), accountEntity);
    return true;
  }

}
