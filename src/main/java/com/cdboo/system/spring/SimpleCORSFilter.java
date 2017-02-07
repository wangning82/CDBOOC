package com.cdboo.system.spring;


import org.apache.catalina.connector.ClientAbortException;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 允许跨域访问
 * Created by houyi on 2016/12/26.
 */
@Component
public class SimpleCORSFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        //httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        httpServletResponse.setHeader("Access-Control-Max-Age", "36000");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        try {
            chain.doFilter(request, response);
        } catch (IOException e) {
            //e.printStackTrace();
        } catch (ServletException e) {
            //e.printStackTrace();
        }
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }
}
