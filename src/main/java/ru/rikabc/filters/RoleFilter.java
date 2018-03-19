package ru.rikabc.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;

/**
 * @Author Roman Khayrullin on 19.03.2018
 * @Version 1.0
 */
@WebFilter(filterName = "RoleFilter", urlPatterns = "/*")
public class RoleFilter implements Filter {
    private HashSet<String> noValidForUsersUrls;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        noValidForUsersUrls = new HashSet<String>();
        noValidForUsersUrls.add("/addproduct");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);
        String url = request.getRequestURI().substring(request.getContextPath().length());

        if (session == null) {
            chain.doFilter(req, resp);
        }

        boolean validUrl = !noValidForUsersUrls.contains(url) && !url.startsWith("/jsp");
        boolean admin = "ADMIN".equals(session.getAttribute("user"));

        if (validUrl || admin) {
            chain.doFilter(req, resp);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "you do not have the necessary rights");
        }
    }

    @Override
    public void destroy() {

    }
}
