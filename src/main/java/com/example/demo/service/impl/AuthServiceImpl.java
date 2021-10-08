package com.example.demo.service.impl;

import com.example.demo.delegate.spi.AuthServerClient;
import com.example.demo.service.exception.TokenExpiredException;
import com.example.demo.service.spi.AuthService;
import com.example.demo.web.controller.model.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

  private final AuthServerClient authServerClient;

  /**
   *
   * EXITO("A01", "Exito") -> true
   * TOKEN_EXPIRADO("E01", "Token expirado") -> false
   * TOKEN_SIN_PERMISOS("E02", "Token no cuenta con los permisos necesarios") -> false
   *
   * @param jwt the client JWT
   * @param actionToValidate action we need to validate
   * @return
   */
  @Override
  public boolean validateToken(String jwt, String actionToValidate) {
    ApiResponse<Boolean> response = authServerClient.validateToken(jwt, actionToValidate);
    String code = response.getCode();
    if (code.equals("E01")) {
      throw new TokenExpiredException();
    }
    boolean isValid = response.getData();
    return isValid;
  }
}
