package com.example.demo.delegate.spi;

import com.example.demo.web.controller.model.ApiResponse;

public interface AuthServerClient {

  ApiResponse<Boolean> validateToken(String jwt, String actionToValidate);

}
