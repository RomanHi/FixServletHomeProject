package ru.rikabc.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpStatus.FORBIDDEN;

/**
 * @Author Roman Khayrullin on 20.04.2018
 * @Version 1.0
 */
public class SessionManager implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession(false) == null) {
            response.sendRedirect(request.getContextPath() + "/index");
            return false;
        }

        Long userId = (Long) request.getSession(false).getAttribute("userId");
        String[] path = request.getServletPath().split("/");
        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/index");
            return false;
        } else if (userId == Long.parseLong(path[2])) {
            return true;
        }
        response.setStatus(FORBIDDEN.value());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
