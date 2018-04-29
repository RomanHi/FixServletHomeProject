package ru.rikabc.security.handlers;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import ru.rikabc.security.details.UserDetailsImpl;
import ru.rikabc.utility.JWTUtil;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Roman Khayrullin on 28.04.2018
 * @Version 1.0
 */
@Service
public class SuccessUrlHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetailsImpl details = (UserDetailsImpl) authentication.getPrincipal();
        Long id = details.getId();
        String token = JWTUtil.createToken(id);
        Cookie cookie = new Cookie("Authorization", token);
        response.addCookie(cookie);
        response.sendRedirect(request.getContextPath() + "/profile/" + id);
    }
}
