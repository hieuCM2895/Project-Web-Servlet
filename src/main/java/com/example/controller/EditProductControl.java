package com.example.controller;

import com.example.dao.CategoryDAOImpl;
import com.example.dao.ProductDAOImpl;
import com.example.model.Category;
import com.example.model.Product;
import com.example.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/manager/edit"})
public class EditProductControl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            String id = req.getParameter("id");
            String name = req.getParameter("name");
            String image = req.getParameter("image");
            String price = req.getParameter("price");
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            String category = req.getParameter("category");

            ProductService productService = ProductService.getInstance();

            CategoryDAOImpl categoryDAO = CategoryDAOImpl.getInstance();
            ProductDAOImpl productDAO = ProductDAOImpl.getInstance();

            Product product = productDAO.findById(Product.class, Integer.parseInt(id));
            Category setCategory = categoryDAO.findById(Category.class, Integer.parseInt(category));

            product.setName(name);
            product.setTitle(title);
            product.setPrice(Double.parseDouble(price));
            product.setDescription(description);
            product.setImage(image);
            product.setCategory(setCategory);

            productService.updateProduct(product);

            String tag = req.getParameter("tag");
            req.getRequestDispatcher("paging?id=" + tag).forward(req, resp);

        } catch (Exception ex) {
            req.getRequestDispatcher("handle-error").forward(req, resp);
        }

    }
}
