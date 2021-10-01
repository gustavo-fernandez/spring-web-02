package com.example.demo.service.mapper;

import com.example.demo.repository.model.AccountEntity;
import com.example.demo.service.model.AccountDto;

public class AccountMapper {

  public AccountDto toAccountDto(AccountEntity accountEntity) {
    AccountDto accountDto = new AccountDto();
    accountDto.setAmount(accountEntity.getAmount());
    accountDto.setOwner(accountEntity.getOwner());
    return accountDto;
  }

}
