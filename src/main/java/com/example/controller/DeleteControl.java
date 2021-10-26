package com.example.controller;

import com.example.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/manager/delete")
public class DeleteControl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            String deleteId = req.getParameter("pid");
            String tag = req.getParameter("tag");

            ProductService productService = ProductService.getInstance();
            boolean result = productService.deleteProductById(Integer.parseInt(deleteId));

            if (!result) {
                String note = "Can't delete product by id = " + deleteId;
                req.setAttribute("pid", deleteId);
                req.setAttribute("note", note);
            }
            req.getRequestDispatcher("paging?id=" + tag).forward(req, resp);

        } catch (Exception ex) {
                req.getRequestDispatcher("handle-error").forward(req, resp);
            }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("paging?id=1");
    }

}

