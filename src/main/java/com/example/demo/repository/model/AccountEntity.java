package com.example.demo.repository.model;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class AccountEntity {

  private String accountNumber;
  private String owner;
  private BigDecimal amount;

}
