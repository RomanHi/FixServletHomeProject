package ru.rikabc.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;

/**
 * @Author Roman Khayrullin on 18.03.2018
 * @Version 1.0
 */

@WebFilter(filterName = "AuthorizationFilter", urlPatterns = "/*")
public class AuthorizationFilter implements Filter {
    private HashSet<String> validUrls;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        validUrls = new HashSet<String>();
        validUrls.add("/login");
        validUrls.add("/signUp");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);
        String url = request.getRequestURI().substring(request.getContextPath().length());

        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean validUrl = validUrls.contains(url);
        boolean staticElement = url.startsWith("/css") || url.startsWith("/js");

        if (loggedIn || validUrl || staticElement) {
            chain.doFilter(req, resp);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    @Override
    public void destroy() {

    }
}
