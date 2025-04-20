package com.lucky.controller.common;

import com.lucky.application.interceptor.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(produces = "application/json;charset=UTF-8")
public class BaseController {
	/**
	 * 请求体
	 */
	@Resource
	protected HttpServletRequest request;
	protected String getToken() {
		return request.getHeader("authorization");
	}
	/**
	 * 获取用户的id
	 */
	protected Long getUserId() {
		String token = getToken();
		if (token == null)
			return null;
		return Long.valueOf(JwtUtils.getUserId(token));
	}

}
