package ru.rikabc.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import ru.rikabc.models.UserFile;
import ru.rikabc.repositories.UserFileRepository;
import ru.rikabc.utility.JWTUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * @Author Roman Khayrullin on 20.04.2018
 * @Version 1.0
 */
@Component
public class TokenManager implements HandlerInterceptor {
    @Autowired
    UserFileRepository repository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String[] requestURI = request.getServletPath().split("/");
        String token = request.getHeader("Authorization");
        if (token == null) {
            Cookie[] cookies = request.getCookies();
            for (Cookie c : cookies) {
                if ("Authorization".equals(c.getName()))
                    token = c.getValue();
            }
            if (token == null) {
                response.sendError(BAD_REQUEST.value());
                return false;
            }
        }

        try {
            if (JWTUtil.verifyId(token).equals(new Long(requestURI[2]))) {
                if (requestURI.length == 5 && !"create".equals(requestURI[4])) {
                    Long fileId = new Long(requestURI[4]);
                    UserFile userFile = repository.findByFileId(fileId);
                    boolean noValidFileId = userFile==null
                            ||!userFile.getUserId().equals(new Long(requestURI[2]));
                    if (noValidFileId) {
                        response.sendError(BAD_REQUEST.value());
                        return false;
                    }
                }
                return true;
            }
        } catch (NumberFormatException | IOException e) {
            response.sendError(BAD_REQUEST.value());
            return false;
        }
        response.sendError(UNAUTHORIZED.value());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
