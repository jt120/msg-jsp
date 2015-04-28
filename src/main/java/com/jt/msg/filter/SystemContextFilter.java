package com.jt.msg.filter;

import com.jt.msg.bean.SystemContext;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * since 2015/4/28.
 */
public class SystemContextFilter implements Filter {

    private int pageIndex = 1;
    private int pageSize = 5;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            pageSize = Integer.parseInt(filterConfig.getInitParameter("pageSize"));
        } catch (NumberFormatException e) {
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        try {
            try {
                pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
            } catch (NumberFormatException e) {
            }
            SystemContext.setPageIndex(pageIndex);
            SystemContext.setPageSize(pageSize);
            chain.doFilter(request, response);
        } finally {
            SystemContext.removePageIndex();
            SystemContext.removePageSize();
        }
    }

    @Override
    public void destroy() {

    }
}
