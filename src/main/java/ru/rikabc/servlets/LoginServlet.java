package ru.rikabc.servlets;

import ru.rikabc.Models.User;
import ru.rikabc.repositories.UserRepository;
import ru.rikabc.repositories.UserRepositoryImplementation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author Roman Khayrullin on 18.03.2018
 * @Version 1.0
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserRepository repository = new UserRepositoryImplementation();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            req.setAttribute("error", session.getAttribute("error"));
            req.setAttribute("logout", session.getAttribute("logout"));
            session.setAttribute("error", null);
            session.setAttribute("logout", null);
        }
        req.getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = repository.findUserByUsername(username);
        HttpSession session = req.getSession(true);

        if (repository.isExist(user, password)) {
            session.setAttribute("user", user.getRole());
            resp.sendRedirect(req.getContextPath() + "/product");
            return;
        }

        session.setAttribute("error", "1");
        resp.sendRedirect(req.getContextPath() + "/login");
    }

}
