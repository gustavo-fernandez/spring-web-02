package com.example.demo.service.exception;

import lombok.Getter;

public class TransferException extends RuntimeException {

  @Getter
  private final String code;

  public TransferException(String code, String message) {
    super(message);
    this.code = code;
  }

}
