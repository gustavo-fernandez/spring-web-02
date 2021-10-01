package com.example.demo.controller;

import com.example.demo.controller.model.ApiResponse;
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
  public ApiResponse<List<AccountDto>> findAll() {
    List<AccountDto> allAccounts = accountService.findAll();
    return ApiResponse.<List<AccountDto>>builder().code("A01").message("Exito").data(allAccounts).build();
  }

  @GetMapping("{accountNumber}")
  public ApiResponse<AccountDto> findByAccountNumber(@PathVariable String accountNumber) {
    AccountDto account = accountService.findById(accountNumber);
    return ApiResponse.<AccountDto>builder().code("A01").message("Exito").data(account).build();
  }

}
