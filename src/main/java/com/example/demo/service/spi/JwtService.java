package com.example.demo.service.spi;

public interface JwtService {

  String generateToken(String permissions);

  boolean validateToken(String token, String permission);

}
