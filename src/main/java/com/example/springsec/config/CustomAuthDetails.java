package com.example.springsec.config;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * AuthDetails custom해서 값 확인해보기 *
 */
@Component
public class CustomAuthDetails implements AuthenticationDetailsSource<HttpServletRequest, RequestInformation> {

    @Override
    public RequestInformation buildDetails(HttpServletRequest request) {
        return RequestInformation.builder()
                .remoteIp(request.getRemoteAddr())
                .sessionId(request.getSession().getId())
                .loginTime(LocalDateTime.now())
                .build();
    }
}
