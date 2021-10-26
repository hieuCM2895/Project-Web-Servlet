package com.example.controller1;

import com.example.model.Product;
import com.example.service.ProductService;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(urlPatterns = {"/home/print"})
public class ShowCartControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

<<<<<<< HEAD
        try {

            Cookie arr[] = req.getCookies();
            List<Product> list = new ArrayList<>();

            ProductService productService = ProductService.getInstance();

            for (Cookie o : arr) {

                if (StringValidation.validationString(o.getName(), StringValidation.predicate2, o.getValue())) {

                    String txt[] = o.getValue().split("-");

                    Set<Integer> listNumber = new HashSet<>();

                    for (String s : txt) {

=======
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        Cookie arr[] = req.getCookies();
        List<Product> list = new ArrayList<>();

        ProductService productService = new ProductService();

        for (Cookie o : arr) {

            if ("cart".equals(o.getName())) {

                if (!StringUtils.isNullOrEmpty(o.getValue())) {

                    String txt[] = o.getValue().split("-");
                    Set<Integer> listNumber = new HashSet<>();

                    for (String s : txt) {
                        
>>>>>>> controller1
                        if ("".equals(s)) {
                            continue;
                        }

                        if (!listNumber.contains(Integer.parseInt(s))) {
                            listNumber.add(Integer.parseInt(s));
                            list.add(productService.findProductById(Integer.parseInt(s)));
                        } else {
                            for (Product listP : list) {
                                if (listP.getId() == Integer.parseInt(s)) {
                                    listP.setAmount(listP.getAmount() + 1);
                                }
                            }
                        }
                    }
                }
            }

            double total = 0;
            for (Product o : list) {
                total = total + o.getAmount() * o.getPrice();
            }

            double vat = 0.1 * total;
            double sum = 1.1 * total;

            req.setAttribute("list", list);
            req.setAttribute("total", total);
            req.setAttribute("tax", vat);
            req.setAttribute("sum", (long) sum);
            req.getRequestDispatcher("/CartProduct.jsp").forward(req, resp);

        } catch (Exception ex) {
            req.getRequestDispatcher("handle-error").forward(req, resp);
        }

    }

}
