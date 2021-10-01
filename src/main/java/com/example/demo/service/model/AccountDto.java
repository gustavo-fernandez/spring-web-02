package com.example.demo.service.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class AccountDto {

  private String owner;
  private BigDecimal amount;

}
