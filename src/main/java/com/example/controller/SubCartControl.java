package com.example.controller;

import com.mysql.cj.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/sub"})
public class SubCartControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        Cookie arr[] = req.getCookies();
        String txt = "";

        for (Cookie o : arr) {

            if ("id".equals(o.getName())) {

                if (!StringUtils.isNullOrEmpty(o.getValue())) {
                    txt = txt + o.getValue();
                    o.setMaxAge(0);
                    resp.addCookie(o);
                }
            }
        }

        if (!StringUtils.isNullOrEmpty(txt)) {

            String ids[] = txt.split("-");

            String txtOutPut = "";
            int check = 0;

            for (int i = 0; i < ids.length; i++) {
                if (id.equals(ids[i])) {
                    check++;
                }
                if (check != 1 || !id.equals(ids[i])) {
                    if (txtOutPut.isEmpty()) {
                        txtOutPut = ids[i];
                    } else {
                        txtOutPut = txtOutPut + "-" + ids[i];
                    }
                }
            }

            if (!txtOutPut.isEmpty()) {
                Cookie c = new Cookie("id", txtOutPut);
                c.setMaxAge(60 * 60 * 24);
                resp.addCookie(c);
            }
        }
        
        resp.sendRedirect("print");

    }
}
