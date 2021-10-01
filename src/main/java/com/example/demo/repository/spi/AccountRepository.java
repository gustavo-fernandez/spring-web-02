package com.example.demo.repository.spi;

import com.example.demo.repository.model.AccountEntity;
import java.util.List;

public interface AccountRepository {

  AccountEntity findByAccountNumber(String accountNumber);

  boolean updateAccount(AccountEntity accountEntity);

  List<AccountEntity> findAll();
}
