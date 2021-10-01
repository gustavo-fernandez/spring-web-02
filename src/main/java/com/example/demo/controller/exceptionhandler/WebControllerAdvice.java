package com.example.demo.controller.exceptionhandler;

import com.example.demo.controller.model.ApiResponse;
import com.example.demo.service.exception.TransferException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class WebControllerAdvice {

  @ExceptionHandler(TransferException.class)
  public ResponseEntity<ApiResponse<?>> handleTransferException(TransferException transferException) {
    log.info("Transferencia fallida por {} - {}", transferException.getCode(), transferException.getMessage());
    ApiResponse<?> apiResponse = ApiResponse.builder()
      .code(transferException.getCode())
      .message(transferException.getMessage())
      .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  @ExceptionHandler(Throwable.class)
  public ResponseEntity<ApiResponse<?>> handleUnhandledExceptions(Throwable throwable) {
    log.error("Ocurrio un error no controlado", throwable);
    ApiResponse<?> apiResponse = ApiResponse.builder()
      .code("E99")
      .message("Ocurrio un error inesperado")
      .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }


}
