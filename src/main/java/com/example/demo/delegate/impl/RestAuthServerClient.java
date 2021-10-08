package com.example.demo.delegate.impl;

import com.example.demo.delegate.spi.AuthServerClient;
import com.example.demo.web.controller.model.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class RestAuthServerClient implements AuthServerClient {

  private final RestTemplate restTemplate;

  @Override
  public ApiResponse<Boolean> validateToken(String jwt, String actionToValidate) {
    String uri = "http://localhost:8081/api/auth/validate-token?action=" + actionToValidate;
    HttpHeaders headers = new HttpHeaders();
    headers.add("token", jwt);
    HttpEntity httpEntity = new HttpEntity(headers);
    ResponseEntity<ApiResponse<Boolean>> responseEntity = restTemplate
      .exchange(uri, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<>() {
      });
    return responseEntity.getBody();
  }
}
