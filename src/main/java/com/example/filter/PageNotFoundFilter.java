package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebFilter(urlPatterns = {"/*"})
public class PageNotFoundFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse resp = (HttpServletResponse) response;
        try {
            Set<Integer> errorPage = new HashSet<>();
            errorPage.add(404);
            errorPage.add(403);
            errorPage.add(500);
            int error = resp.getStatus();
            if (errorPage.contains(resp.getStatus())) {
                resp.sendRedirect("Exception.jsp");
            } else {
                chain.doFilter(request, response);
            }
        } catch (Exception ex) {
            resp.sendRedirect("Exception.jsp");
        }

    }
}
