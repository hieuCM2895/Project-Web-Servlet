package com.example.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/cart"})
public class CartProductControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String productId = req.getParameter("id");
        Cookie arr[] = req.getCookies();
        String txt = "";
        for (Cookie o : arr) {
            if ("id".equals(o.getName())) {
                txt = txt + o.getValue();
                o.setMaxAge(0);
                resp.addCookie(o);
            }
        }
        if (txt.isEmpty()) {
            txt = productId;
        } else {
            txt = txt + "-" + productId;
        }
        Cookie c = new Cookie("id", txt);
        c.setMaxAge(60 * 60 * 24);
        resp.addCookie(c);
        resp.sendRedirect("print");

    }

}
