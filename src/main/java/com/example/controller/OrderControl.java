package com.example.controller;

import com.example.model.Account;
import com.example.model.Product;
import com.example.service.ProductService;
import com.example.validation.StringValidation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/order"})
public class OrderControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            Cookie arr[] = req.getCookies();
            List<Product> list = new ArrayList<>();
            ProductService productService = ProductService.getInstance();

            for (Cookie o : arr) {

                if (StringValidation.validationString(o.getName(), StringValidation.predicate, o.getValue())) {

                    String txt[] = o.getValue().split("-");
                    for (String s : txt) {
                        if ("".equals(s)) {
                            continue;
                        }
                        list.add(productService.findProductById(Integer.parseInt(s)));
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

            HttpSession session = req.getSession();
            Account account = (Account) session.getAttribute("acc");

            if (account != null) {
                req.setAttribute("acc", account);
            }

            double vat = 0.1 * total;
            double sum = 1.1 * total;

            req.setAttribute("list", list);
            req.setAttribute("total", total);
            req.setAttribute("vat", vat);
            req.setAttribute("sum", (long) sum);
            req.getRequestDispatcher("Order.jsp").forward(req, resp);

        } catch (Exception ex) {
            req.getRequestDispatcher("handle-error").forward(req, resp);
        }

    }
}
