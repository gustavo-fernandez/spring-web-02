package com.example.demo.config;

import com.example.demo.repository.impl.InMemoryAccountRepository;
import com.example.demo.repository.spi.AccountRepository;
import com.example.demo.service.impl.AccountServiceImpl;
import com.example.demo.service.impl.TransferServiceImpl;
import com.example.demo.service.mapper.AccountMapper;
import com.example.demo.service.spi.AccountService;
import com.example.demo.service.spi.TransferService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

  /*@Bean
  AccountService accountService(
    AccountRepository accountRepository,
    AccountMapper accountMapper) {
    return new AccountServiceImpl(accountRepository, accountMapper);
  }*/

  @Bean
  AccountService accountService() {
    return new AccountServiceImpl(accountRepository(), accountMapper());
  }

  @Bean
  AccountRepository accountRepository() {
    return new InMemoryAccountRepository();
  }

  @Bean
  AccountMapper accountMapper() {
    return new AccountMapper();
  }

  @Bean
  TransferService transferService() {
    return new TransferServiceImpl(accountRepository());
  }
}
