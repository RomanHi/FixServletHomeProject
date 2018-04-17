package ru.rikabc.servlets;

import ru.rikabc.Models.Product;
import ru.rikabc.repositories.ProductHibernateRepository;
import ru.rikabc.repositories.ProductJdbcTemplateRepository;
import ru.rikabc.repositories.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author Roman Khayrullin on 17.03.2018
 * @Version 1.0
 */
@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    private ProductRepository repository;

    @Override
    public void init() throws ServletException {
//        repository = new ProductRepositoryImplementation();
        repository = new ProductJdbcTemplateRepository();
//        repository = new ProductHibernateRepository();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> data = repository.findAll();

        if (data.isEmpty()) {
            req.setAttribute("emptyData", "Product not found");
        }
        req.setAttribute("allProduct", data);
        req.getServletContext().getRequestDispatcher("/jsp/products.jsp").forward(req, resp);
    }
}
