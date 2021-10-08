package com.example.demo.web.interceptor;

import com.example.demo.common.annotation.JwtAction;
import com.example.demo.service.spi.AuthService;
import com.example.demo.web.exception.UnauthorizedException;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthValidationInterceptor implements HandlerInterceptor {

  private final AuthService authServerClient;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    if (handler instanceof HandlerMethod) {
      HandlerMethod handlerMethod = (HandlerMethod) handler;
      Method method = handlerMethod.getMethod();
      JwtAction jwtAction = method.getAnnotation(JwtAction.class);
      if (jwtAction == null) {
        return true;
      }
      String authorization = request.getHeader("Authorization");
      if (authorization == null || !authorization.startsWith("Bearer ")) {
        throw new UnauthorizedException();
      }
      String jwt = authorization.substring(7);
      log.info("Token: {} | Action: {}", jwt, jwtAction.value());
      boolean isAuthorized = authServerClient.validateToken(jwt, jwtAction.value());
      if (!isAuthorized) {
        throw new UnauthorizedException();
      }
    }
    return true;
  }
}
