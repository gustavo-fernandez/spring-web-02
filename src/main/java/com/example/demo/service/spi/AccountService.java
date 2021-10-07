package com.example.demo.service.spi;

import com.example.demo.service.model.AccountDto;
import com.example.demo.service.model.AccountRequestDto;
import java.util.List;

public interface AccountService {

  List<AccountDto> findAll();

  AccountDto findById(String accountNumber);

  boolean createAccount(AccountRequestDto accountRequestDto);
}
