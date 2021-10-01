package com.example.demo.service.model;


import java.math.BigDecimal;
import lombok.Data;

@Data
public class TransferDto {

  private String originAccount;
  private String destinationAccount;
  private BigDecimal amount;

}
