package com.waben.stock.applayer.promotion.warpper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.waben.stock.interfaces.exception.DataNotFoundException;
import com.waben.stock.interfaces.exception.ExceptionMap;
import com.waben.stock.interfaces.exception.NetflixCircuitException;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.ExceptionInformation;

/**
 * @author yuyidi 2017-07-13 16:06:14
 * @class com.wangbei.exception.ExecptionHandler
 * @description 统一异常处理 第一种
 */
// @Component
public class PromotionExecptionHandler implements HandlerExceptionResolver {
	Logger logger = LoggerFactory.getLogger(getClass());
	MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
	Map<String, String> exceptionMap;
	private List<ExceptionInformation> exceptions = new ArrayList<>();

	{
		exceptionMap = ExceptionMap.exceptionMap;
	}

	public PromotionExecptionHandler() {
		this.exceptions
				.add(new ExceptionInformation(NoHandlerFoundException.class, HttpServletResponse.SC_NOT_FOUND, "404"));
		this.exceptions
				.add(new ExceptionInformation(DataNotFoundException.class, HttpServletResponse.SC_OK, "204"));
		this.exceptions.add(
				new ExceptionInformation(IllegalArgumentException.class, HttpServletResponse.SC_BAD_REQUEST, "400"));
		this.exceptions.add(new ExceptionInformation(NetflixCircuitException.class,
				HttpServletResponse.SC_SERVICE_UNAVAILABLE, "503"));
		this.exceptions.add(new ExceptionInformation(ServiceException.class, HttpServletResponse.SC_OK, "500"));
		this.exceptions.add(new ExceptionInformation(AccessDeniedException.class, HttpServletResponse.SC_FORBIDDEN, "403"));
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ex.printStackTrace();
		ModelAndView mv = new ModelAndView();
		Object message = "未知错误";
		String code = "0000";
		String error = "503";
		try {
			for (ExceptionInformation exception : exceptions) {
				if (ex.getClass().equals(exception.getException())) {
					logger.info("匹配到异常信息:{}", exception.getException());
					response.setStatus(exception.getHttpStatus());
					code = ex.getMessage();
					message = message(code, ex);
					error = exception.getError();
					break;
				}
			}
			logger.error("请求：{},异常：{},{}", request.getRequestURI(), message, ex.getClass());
		} finally {
			mv.addObject("message", message);
			mv.addObject("code", code);
			logger.info("响应状态码:{},异常编码:{},异常信息:{}", response.getStatus(), code, message);
			String contentType = request.getContentType();
			String isFeign = request.getHeader("feign");
			logger.info("isfegin{}", isFeign);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE); // 设置ContentType
			response.setCharacterEncoding("UTF-8"); // 避免乱码
			mv.setView(jsonView);
			logger.info("feign 请求");
		}
		return mv;
	}

	public void extendException(List<ExceptionInformation> exceptions) {
		this.exceptions.addAll(exceptions);
	}

	private String message(String type, Exception ex) {
		if (ex instanceof ServiceException) {
			ServiceException serviceException = (ServiceException) ex;
			if (serviceException.getCustomMessage() != null) {
				return serviceException.getCustomMessage();
			}
		}
		String message;
		if (exceptionMap.containsKey(type)) {
			message = exceptionMap.get(type);
		} else {
			message = type;
		}
		return message;
	}
}
