package com.example.controller;

import com.example.model.Account;
import com.example.model.Category;
import com.example.model.Product;
import com.example.service.CategoryService;
import com.example.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/manager/paging"})
public class ManagerProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            ProductService productService = ProductService.getInstance();

            HttpSession session = req.getSession();
            Account account = (Account) session.getAttribute("acc");

            String currentNumberPage = req.getParameter("id");
            if (currentNumberPage == null) {
                currentNumberPage = "1";
            }

            List<Product> listP = productService.searchProductByAccountID(account.getId(), Integer.parseInt(currentNumberPage), 5);
            long total = productService.getNumberPage(account.getId());

            long countPage = total / 5;
            if (total % 5 != 0) {
                countPage++;
            }

            CategoryService categoryService = CategoryService.getInstance();
            List<Category> listC = categoryService.findAllCategory();

<<<<<<< HEAD
            int currentPage = Integer.parseInt(currentNumberPage);
=======
        int tag = Integer.parseInt(currentNumberPage);

        req.setAttribute("listC", listC);
        req.setAttribute("listP", listP);
        req.setAttribute("tag", tag);
        req.setAttribute("numberPage", countPage);
        req.setAttribute("total", total);
>>>>>>> controller1

            req.setAttribute("listC", listC);
            req.setAttribute("listP", listP);
            req.setAttribute("tag", currentPage);
            req.setAttribute("numberPage", countPage);
            req.setAttribute("total", total);

            req.getRequestDispatcher("/WEB-INF/ManagerProduct.jsp").forward(req, resp);

        } catch (Exception ex) {
            req.getRequestDispatcher("handle-error").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
