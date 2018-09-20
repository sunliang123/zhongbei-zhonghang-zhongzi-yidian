package com.waben.stock.applayer.strategist.wrapper.filter;

import org.apache.catalina.servlet4preview.http.HttpServletRequestWrapper;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Created by yuyidi on 2018/3/16.
 * @desc
 */
public class HiddenParamProcessFilter extends HiddenHttpMethodFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain
            filterChain) throws ServletException, IOException {
        String header = request.getParameter("token");
        if (header != null && !header.trim().equals("")) {
            HttpServletRequest wrapper = new HttpHeaderRequestWrapper(request, header);
            super.doFilterInternal(wrapper, response, filterChain);
        } else {
            super.doFilterInternal(request, response, filterChain);
        }
    }


    private static class HttpHeaderRequestWrapper extends HttpServletRequestWrapper {

        private final String header;

        public HttpHeaderRequestWrapper(HttpServletRequest request, String authorization) {
            super(request);
            this.header = authorization;
        }

        @Override
        public String getHeader(String name) {
            if (name != null &&
                    name.equals("Authorization") &&
                    super.getHeader("Authorization") == null) {
                return header;
            } else {
                return super.getHeader(name);
            }
        }

    }


}
