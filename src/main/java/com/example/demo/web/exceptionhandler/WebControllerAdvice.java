package com.example.demo.web.exceptionhandler;

import com.example.demo.repository.exception.DatabaseException;
import com.example.demo.service.exception.TokenExpiredException;
import com.example.demo.service.exception.TransferException;
import com.example.demo.web.controller.model.ApiResponse;
import com.example.demo.web.controller.model.FieldValidationError;
import com.example.demo.web.exception.UnauthorizedException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<?>> handleValidations(MethodArgumentNotValidException validationException) {

    List<FieldValidationError> fieldValidationErrors = validationException.getBindingResult().getFieldErrors()
      .stream()
      .map(fieldError -> new FieldValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
      .collect(Collectors.toList());

    ApiResponse<?> apiResponse = ApiResponse.builder()
      .code("E10")
      .message("Error de validación")
      .fieldErrors(fieldValidationErrors)
      .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<ApiResponse<?>> handleUnauthorizedException(UnauthorizedException unauthorizedException) {
    ApiResponse<?> apiResponse = ApiResponse.builder()
      .code("AU01")
      .message("No está autorizado para consumir este servicio")
      .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(TokenExpiredException.class)
  public ResponseEntity<ApiResponse<?>> handleExpiredJwtException(TokenExpiredException expiredJwtException) {
    ApiResponse<?> apiResponse = ApiResponse.builder()
      .code("AU01")
      .message("El JWT está expirado")
      .build();
    return new ResponseEntity<>(apiResponse, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(DatabaseException.class)
  public ResponseEntity<ApiResponse<?>> handleUnhandledExceptions(DatabaseException databaseException) {
    log.error("Ocurrio un error de base de datos", databaseException);
    ApiResponse<?> apiResponse = ApiResponse.builder()
      .code("DB01")
      .message(databaseException.getMessage())
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
