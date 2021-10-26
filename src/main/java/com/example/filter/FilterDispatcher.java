package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/"})
public class FilterDispatcher implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try {
            HttpServletRequest req = (HttpServletRequest) request;
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("product");
            requestDispatcher.forward(request, response);
        } catch (Exception ex) {
            request.getRequestDispatcher("handle-error").forward(request, response);
        }

    }
}
