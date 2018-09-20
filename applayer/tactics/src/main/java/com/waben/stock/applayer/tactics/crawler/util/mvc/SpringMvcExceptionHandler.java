package com.waben.stock.applayer.tactics.crawler.util.mvc;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.PropertyAccessException;
import org.springframework.http.HttpMethod;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.waben.stock.applayer.tactics.crawler.util.base.exception.BusinessException;
import com.waben.stock.applayer.tactics.crawler.util.base.exception.RetryException;


/**
 * 描述:统一的未处理的异常的最终处理策略
 * <bean class="org.songbai.variety.utils.http.SpringMvcExceptionHandler"/>
 *
 * @author boyce
 * @created 2015年5月26日 下午2:11:09
 * @since v1.0.0
 */
public class SpringMvcExceptionHandler implements HandlerExceptionResolver {
    Logger logger = LoggerFactory.getLogger(getClass());

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        response.setStatus(200);
        response.setHeader("Content-Type", "text/json;charset=utf-8");
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
        }

        Response resp = null;

        //所有错误码的msg 都添加在这里
        if (ex instanceof BusinessException) {
            int code = ((BusinessException) ex).getCode();
            String msg = ex.getMessage();
            resp = Response.response(code, msg);
        }
        else if(ex instanceof RetryException){
            resp = Response.response(ResponseCode.SERVER_ERROR, "请求失败，请重试");
        }
        else if (ex instanceof HttpRequestMethodNotSupportedException) {
            HttpRequestMethodNotSupportedException notSupportedException = (HttpRequestMethodNotSupportedException) ex;

            if (notSupportedException.getSupportedMethods().length == 1) {
                if (HttpMethod.GET.name().equals(notSupportedException.getSupportedMethods()[0])) {
                    resp = Response.response(ResponseCode.PARAM_METHOD_GET, "只支持GET方法");
                } else if (HttpMethod.POST.name().equals(notSupportedException.getSupportedMethods()[0])) {
                    resp = Response.response(ResponseCode.PARAM_METHOD_POST, "只支持POST方法");
                } else {
                    resp = Response.response(ResponseCode.PARAM_METHOD_ERROR, "请求方法" + notSupportedException.getMethod() + "不支持");
                }
            } else {
                resp = Response.response(ResponseCode.PARAM_METHOD_ERROR, "请求方法" + notSupportedException.getMethod() + "不支持");
            }
            logger.warn("user request " + request.getRequestURI() + " error!", ex);
        } else if (ex instanceof IllegalArgumentException) {

            String msg = ex.getMessage();

            if (msg.contains("Assertion failed")) {
                msg = "请求参数异常!";
            }

            resp = Response.response(ResponseCode.PARAM_ERROR, msg);
            logger.error("user request " + request.getRequestURI() + " error!", ex);
        }  else if (ex instanceof SQLException) {
            resp = Response.response(ResponseCode.SERVER_DB_ERROR, "数据库操作异常");
            logger.error("user request " + request.getRequestURI() + " error!", ex);
        } else if (ex instanceof org.springframework.validation.BindException || ex instanceof PropertyAccessException) {
            resp = Response.response(ResponseCode.PARAM_ERROR, "请求参数异常");
            logger.error("user request " + request.getRequestURI() + " error!", ex);
        } else {
            resp = Response.response(ResponseCode.SERVER_ERROR, "服务器内部异常");
            logger.error("user request " + request.getRequestURI() + " error!", ex);
        }


        if (resp != null) {
            try {

                String resultJson = JSON.toJSONString(resp);

                response.getWriter().print(resultJson);
                response.getWriter().flush();
            } catch (IOException e) {
            } finally {
                try {
                    response.getWriter().close();
                } catch (IOException e) {
                    //ginore
                }
            }
        }
        return null;
    }


}
