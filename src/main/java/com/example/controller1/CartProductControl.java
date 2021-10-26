package com.example.controller1;

<<<<<<< HEAD
import com.example.validation.StringValidation;
=======
import com.mysql.cj.util.StringUtils;
>>>>>>> controller1

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/home/cart"})
public class CartProductControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

<<<<<<< HEAD
        try {

            String productId = req.getParameter("id");
            Cookie arr[] = req.getCookies();
            String txt = "";

            for (Cookie o : arr) {

                if (StringValidation.validationString(o.getName(), StringValidation.predicate2, o.getValue())) {
=======
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String productId = req.getParameter("id");
        Cookie arr[] = req.getCookies();
        String txt = "";

        for (Cookie o : arr) {

            if ("cart".equals(o.getName())) {

                if (!StringUtils.isNullOrEmpty(o.getValue())) {
>>>>>>> controller1

                    txt = txt + o.getValue();
                    o.setMaxAge(0);
                    resp.addCookie(o);
<<<<<<< HEAD

=======
>>>>>>> controller1
                }
            }

            if (txt.isEmpty()) {
                txt = productId;
            } else {
                txt = txt + "-" + productId;
            }

            Cookie c = new Cookie("cart", txt);
            c.setMaxAge(60 * 60 * 24);
            resp.addCookie(c);
            resp.sendRedirect("print");

        } catch (Exception ex) {
            req.getRequestDispatcher("handle-error").forward(req, resp);
        }
<<<<<<< HEAD
=======
        
        if (txt.isEmpty()) {
            txt = productId;
        } else {
            txt = txt + "-" + productId;
        }

        Cookie c = new Cookie("cart", txt);
        c.setMaxAge(60 * 60 * 24);
        resp.addCookie(c);
        resp.sendRedirect("print");
>>>>>>> controller1

    }
}
