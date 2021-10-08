package com.example.demo.service.spi;

public interface AuthService {

  boolean validateToken(String jwt, String actionToValidate);

}
