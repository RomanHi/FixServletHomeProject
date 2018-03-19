package ru.rikabc.servlets;

import ru.rikabc.Models.Product;
import ru.rikabc.repositories.ProductRepository;
import ru.rikabc.repositories.ProductRepositoryImplementation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Roman Khayrullin on 18.03.2018
 * @Version 1.0
 */
@WebServlet("/addproduct")
public class AddProductServlet extends HttpServlet {
    private ProductRepository repository = new ProductRepositoryImplementation();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        deleteMessageFromCookie(req, resp);
        req.getServletContext().getRequestDispatcher("/jsp/addproduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");
        Product product = new Product(0, name, price, description);
        Cookie cookie = new Cookie("message", "");

        if (repository.save(product)) {
            cookie.setValue("Product added");
        } else {
            cookie.setValue("Product already exist");
        }
        resp.addCookie(cookie);
        resp.sendRedirect(req.getContextPath() + "/addproduct");
    }

    private void deleteMessageFromCookie(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();

        if (cookies == null)
            return;

        for (Cookie c : cookies) {
            if (c.getName().equals("message")) {
                req.setAttribute("message", c.getValue());
                c.setMaxAge(0);
            }
            resp.addCookie(c);
        }
    }
}
