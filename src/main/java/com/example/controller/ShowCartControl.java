package com.example.controller;

import com.example.model.Product;
import com.example.service.ProductService;
import com.mysql.cj.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/print"})
public class ShowCartControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        Cookie arr[] = req.getCookies();
        List<Product> list = new ArrayList<>();
        ProductService productService = new ProductService();
        for (Cookie o : arr) {

            if ("id".equals(o.getName())) {

                if (!StringUtils.isNullOrEmpty(o.getValue())) {

                    String txt[] = o.getValue().split("-");
                    for (String s : txt) {
                        if ("".equals(s)) {
                            continue;
                        }
                        list.add(productService.findProductById(Integer.parseInt(s)));
                    }
                }
            }
        }

        for (int i = 0; i < list.size(); i++) {
            int count = 1;
            list.get(i).setAmount(count);
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).getId() == list.get(j).getId()) {
                    count++;
                    list.remove(j);
                    j--;
                    list.get(i).setAmount(count);
                }
            }
        }

        double total = 0;
        for (Product o : list) {
            total = total + o.getAmount() * o.getPrice();
        }

        double vat = total * 0.1;
        double sum = total * 1.1;

        req.setAttribute("list", list);
        req.setAttribute("total", total);
        req.setAttribute("vat", vat);
        req.setAttribute("sum", (long) sum);
        req.getRequestDispatcher("Cart.jsp").forward(req, resp);

    }

}
