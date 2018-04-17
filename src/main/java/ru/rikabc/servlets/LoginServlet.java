package ru.rikabc.servlets;

import org.springframework.context.ApplicationContext;
import ru.rikabc.Models.User;
import ru.rikabc.repositories.UserRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @Author Roman Khayrullin on 18.03.2018
 * @Version 1.0
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserRepository repository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ApplicationContext applicationContext = (ApplicationContext) config.getServletContext()
                .getAttribute("springContext");
        repository = applicationContext.getBean(UserRepository.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        deleteMessageFromCookie(req, resp);
        req.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = repository.findUserByUsername(username);

        if (repository.isExist(user, password)) {
            HttpSession session = req.getSession(true);
            session.setAttribute("user", user.getRole());
            resp.sendRedirect(req.getContextPath() + "/product");
            return;
        }
        Cookie cookie = new Cookie("error", "1");
        resp.addCookie(cookie);
        resp.sendRedirect(req.getContextPath() + "/login");
    }

    private void deleteMessageFromCookie(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null)
            return;
        for (Cookie c : cookies) {
            if (c.getName().equals("error") || c.getName().equals("logout")) {
                c.setMaxAge(0);
            }
            resp.addCookie(c);
        }

    }
}
