package com.example.demo.config;

import com.example.demo.repository.impl.JavaFakeInMemoryAccountRepository;
import com.example.demo.repository.impl.SqlInMemoryAccountRepository;
import com.example.demo.repository.spi.AccountRepository;
import com.example.demo.service.impl.AccountServiceImpl;
import com.example.demo.service.impl.TransferServiceImpl;
import com.example.demo.service.mapper.AccountMapper;
import com.example.demo.service.spi.AccountService;
import com.example.demo.service.spi.TransferService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class BeanConfiguration {

  @Bean
  AccountService accountService(AccountMapper accountMapper, AccountRepository accountRepository) {
    return new AccountServiceImpl(accountRepository, accountMapper);
  }

  @Bean
  @ConditionalOnProperty(name = "repository.implementation", havingValue = "java-fake")
  AccountRepository javaFakeAccountRepository() {
    return new JavaFakeInMemoryAccountRepository();
  }

  @Bean
  @ConditionalOnProperty(name = "repository.implementation", havingValue = "sql-in-memory")
  AccountRepository sqlInMemoryAccountRepository(JdbcTemplate jdbcTemplate,
                                                 NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    return new SqlInMemoryAccountRepository(jdbcTemplate, namedParameterJdbcTemplate);
  }

  @Bean
  TransferService transferService(AccountRepository accountRepository) {
    return new TransferServiceImpl(accountRepository);
  }
}
