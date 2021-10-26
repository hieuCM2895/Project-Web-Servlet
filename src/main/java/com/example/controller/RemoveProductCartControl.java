package com.example.controller;

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

@WebServlet(urlPatterns = {"/remove"})
public class RemoveProductCartControl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

<<<<<<< HEAD
        try {

            String id = req.getParameter("id");
            Cookie arr[] = req.getCookies();
            String result = "";

            for (Cookie o : arr) {

                if (StringValidation.validationString(o.getName(), StringValidation.predicate, o.getValue())) {
=======
        String id = req.getParameter("id");
        Cookie arr[] = req.getCookies();
        String result = "";
        for (Cookie o : arr) {

            if ("id".equals(o.getName())) {

                if (!StringUtils.isNullOrEmpty(o.getValue())) {
>>>>>>> controller1

                    String txt[] = o.getValue().split("-");
                    o.setMaxAge(0);
                    resp.addCookie(o);
                    for (String s : txt) {
                        if (!id.equals(s)) {
                            result += s + "-";
                        }
                    }
                }
            }

            Cookie c = new Cookie("id", result);
            c.setMaxAge(60 * 60 * 24);
            resp.addCookie(c);
            resp.sendRedirect("print");

        } catch (Exception ex) {
            req.getRequestDispatcher("handle-error").forward(req, resp);
        }

    }
}
