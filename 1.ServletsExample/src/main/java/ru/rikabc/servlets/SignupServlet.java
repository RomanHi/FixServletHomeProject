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

@WebServlet("/signUp")
public class SignupServlet extends HttpServlet {
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
        req.getServletContext().getRequestDispatcher("/jsp/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (repository.save(new User(0, username, password, "USER"))) {
            HttpSession session = req.getSession(true);
            session.setAttribute("user", "USER");
            resp.sendRedirect(req.getContextPath() + "/product");
            return;
        }
        Cookie cookie = new Cookie("userExist", "1");
        resp.addCookie(cookie);
        resp.sendRedirect(req.getContextPath() + "/signUp");
    }

    private void deleteMessageFromCookie(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null)
            return;
        for (Cookie c : cookies) {
            if (c.getName().equals("userExist")) {
                c.setMaxAge(0);
            }
            resp.addCookie(c);
        }
    }
}
