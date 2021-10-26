package com.example.controller;

import com.example.model.Category;
import com.example.model.Product;
import com.example.service.CategoryService;
import com.example.service.ProductService;
import com.example.validation.StringValidation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/product")
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            ProductService productService = ProductService.getInstance();
            List<Product> listP = productService.findAllProduct();

            CategoryService categoryService = CategoryService.getInstance();
            List<Category> listC = categoryService.findAllCategory();

            Product newProduct = productService.findNewProduct();

            Cookie arr[] = req.getCookies();
            int amount = 0;
            for (Cookie o : arr) {
                if (StringValidation.validationString(o.getName(), StringValidation.predicate, o.getValue())) {
                    String[] txt = o.getValue().split("-");
                    for (String s : txt) {
                        if (!"".equals(s)) {
                            amount++;
                        }
                    }
                }
            }

            req.setAttribute("listP", listP);
            req.setAttribute("listC", listC);
            req.setAttribute("last", newProduct);
            req.setAttribute("amount", amount);

            req.getRequestDispatcher("Home.jsp").forward(req, resp);

        } catch (Exception ex) {
            req.getRequestDispatcher("handle-error").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
