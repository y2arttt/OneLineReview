package com.oneLineReview.oneLineReview.common.interceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        String uuid = UUID.randomUUID().toString();
        log.info("[{}][{}]",uuid,uri);
        HttpSession session = request.getSession(false);
        if(session == null  || session.getAttribute("userId") == null){
            response.sendRedirect("/auth/login");
            return false;
        }
        return true;
    }
}
