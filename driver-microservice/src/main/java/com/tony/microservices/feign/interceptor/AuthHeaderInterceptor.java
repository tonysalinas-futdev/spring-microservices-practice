package com.tony.microservices.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Component
public class AuthHeaderInterceptor implements RequestInterceptor {

        @Override
        public void apply(RequestTemplate template){
            ServletRequestAttributes attrs=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attrs== null){return;}
            HttpServletRequest request=attrs.getRequest();

            String authHeader=request.getHeader(HttpHeaders.AUTHORIZATION);
            if (authHeader!= null){
                log.debug("Setted authHeader to feign client");
                template.header("Authorization", authHeader);

        }
    }

}
