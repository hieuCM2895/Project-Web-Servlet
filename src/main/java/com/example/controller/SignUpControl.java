package com.example.controller;

import com.example.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/signup")
public class SignUpControl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            String user = req.getParameter("user");
            String pass = req.getParameter("pass");
            String repass = req.getParameter("repass");

            AccountService accountService = AccountService.getInstance();

            if (!pass.equals(repass)) {
                resp.sendRedirect("Login.jsp");
            } else {
                if (accountService.checkUserExits(user)) {
                    resp.sendRedirect("Login.jsp");
                } else {
                    accountService.addNewAccount(user, pass);
                    resp.sendRedirect("product");
                }
            }

        } catch (Exception ex) {
            req.getRequestDispatcher("handle-error").forward(req, resp);
        }

    }
}
