package com.example.demo.service.exception;

public class TransferException extends RuntimeException {

  private final String code;

  public TransferException(String code, String message) {
    super(message);
    this.code = code;
  }

}
