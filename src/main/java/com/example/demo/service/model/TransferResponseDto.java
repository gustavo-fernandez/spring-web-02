package com.example.demo.service.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TransferResponseDto {

  private boolean success;
  private String operationNumber;
  private LocalDateTime operationTime;

}
