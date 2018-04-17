package ru.rikabc.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author Roman Khayrullin on 19.03.2018
 * @Version 1.0
 */

@WebFilter(filterName = "RoleFilter", urlPatterns = {"/addproduct", "/jsp/*","/delete"})
public class RoleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);

        if (session == null) {
            chain.doFilter(req, resp);
        }

        boolean admin = "ADMIN".equals(session.getAttribute("user"));

        if (admin) {
            chain.doFilter(req, resp);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "No access");
        }
    }

    @Override
    public void destroy() {

    }
}
