package com.example.demo.service.spi;

import com.example.demo.service.model.AccountDto;
import java.util.List;

public interface AccountService {

  List<AccountDto> findAll();

  AccountDto findById(String accountNumber);

}
