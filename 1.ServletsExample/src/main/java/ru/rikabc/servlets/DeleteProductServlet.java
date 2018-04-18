package ru.rikabc.servlets;

import org.springframework.context.ApplicationContext;
import ru.rikabc.repositories.ProductRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Roman Khayrullin on 17.04.2018
 * @Version 1.0
 */
@WebServlet("/delete")
public class DeleteProductServlet extends HttpServlet {
    private ProductRepository repository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ApplicationContext applicationContext = (ApplicationContext) config.getServletContext()
                .getAttribute("springContext");
        repository = applicationContext.getBean(ProductRepository.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idString = req.getParameter("id");
        int id = Integer.parseInt(idString);
        repository.delete(id);
        resp.sendRedirect(req.getContextPath() + "/product");

    }
}
