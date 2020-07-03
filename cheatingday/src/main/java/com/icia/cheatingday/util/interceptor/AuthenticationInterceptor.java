package com.icia.cheatingday.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 인증 정보 객체를 스프링 시큐리티 정보 관리자로 부터 얻어오자
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth.getPrincipal().toString().equals("anonymousUser")==false)
			throw new IllegalAccessError("잘못된 접근입니다");
		return super.preHandle(request, response, handler);
	}

}
