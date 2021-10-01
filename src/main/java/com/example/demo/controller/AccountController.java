package com.example.demo.controller;

import com.example.demo.service.model.AccountDto;
import com.example.demo.service.spi.AccountService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/account")
@RequiredArgsConstructor
public class AccountController {

  private final AccountService accountService;

  @GetMapping("")
  public List<AccountDto> findAll() {
    return accountService.findAll();
  }

  @GetMapping("{accountNumber}")
  public AccountDto findByAccountNumber(@PathVariable String accountNumber) {
    return accountService.findById(accountNumber);
  }

}
