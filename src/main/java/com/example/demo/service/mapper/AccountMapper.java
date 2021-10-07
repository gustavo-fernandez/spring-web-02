package com.example.demo.service.mapper;

import com.example.demo.repository.model.AccountEntity;
import com.example.demo.service.model.AccountDto;
import com.example.demo.service.model.AccountRequestDto;
import java.math.BigDecimal;

public class AccountMapper {

  public AccountDto toAccountDto(AccountEntity accountEntity) {
    AccountDto accountDto = new AccountDto();
    accountDto.setAmount(accountEntity.getAmount());
    accountDto.setOwner(accountEntity.getOwner());
    return accountDto;
  }

  public AccountEntity toAccountEntity(AccountRequestDto accountRequestDto) {
    return AccountEntity.builder()
      .owner(accountRequestDto.getOwner())
      .accountNumber(accountRequestDto.getAccountNumber())
      .amount(BigDecimal.ZERO)
      .build();
  }

}
