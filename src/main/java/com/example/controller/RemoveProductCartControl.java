package com.example.controller;

import com.mysql.cj.util.StringUtils;

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

        String id = req.getParameter("id");
        Cookie arr[] = req.getCookies();
        String result = "";
        for (Cookie o : arr) {

            if ("id".equals(o.getName())) {

                if (!StringUtils.isNullOrEmpty(o.getValue())) {

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
        }
        Cookie c = new Cookie("id", result);
        c.setMaxAge(60 * 60 * 24);
        resp.addCookie(c);
        resp.sendRedirect("print");

    }
}
