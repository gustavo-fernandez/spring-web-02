package com.example.demo.web.controller;

import static com.example.demo.service.common.ResponseCode.A01;

import com.example.demo.common.annotation.JwtAction;
import com.example.demo.service.model.AccountRequestDto;
import com.example.demo.web.controller.model.ApiResponse;
import com.example.demo.service.model.AccountDto;
import com.example.demo.service.spi.AccountService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/account")
@RequiredArgsConstructor
public class AccountController {

  private final AccountService accountService;

  @JwtAction("listar-cuentas")
  @GetMapping("")
  public ApiResponse<List<AccountDto>> findAll() {
    List<AccountDto> allAccounts = accountService.findAll();
    return ApiResponse.<List<AccountDto>>builder().code(A01.name()).message("Exito").data(allAccounts).build();
  }

  @JwtAction("listar-una-cuenta")
  @GetMapping("{accountNumber}")
  public ApiResponse<AccountDto> findByAccountNumber(@PathVariable String accountNumber) {
    AccountDto account = accountService.findById(accountNumber);
    return ApiResponse.<AccountDto>builder().code(A01.name()).message("Exito").data(account).build();
  }

  @JwtAction("crear-cuenta")
  @PostMapping("")
  public ApiResponse<String> createAccount(@Valid @RequestBody AccountRequestDto accountRequestDto) {
    boolean created = accountService.createAccount(accountRequestDto);
    String message = created ? "Exito" : "No creado";
    return ApiResponse.<String>builder().code(A01.name()).message(message).data(message).build();
  }

}
