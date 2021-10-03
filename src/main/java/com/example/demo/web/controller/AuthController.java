package com.example.demo.web.controller;

import com.example.demo.service.spi.JwtService;
import com.example.demo.web.controller.model.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

  private final JwtService jwtService;

  @GetMapping("api/generate-token")
  public ApiResponse<String> generateToken(@RequestParam String permissions) {
    String jwt = jwtService.generateToken(permissions);
    return ApiResponse.<String>builder().code("A01").message("Exito").data(jwt).build();
  }

}
