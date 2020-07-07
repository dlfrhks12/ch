package com.icia.cheatingday.util.security;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.security.core.*;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.savedrequest.*;
import org.springframework.stereotype.*;

@Component("loginSuccessHandler")
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

	// 사용자가 가려던 목적지를 저장하는 객체
	private RequestCache cache = new HttpSessionRequestCache();
	// 리다이렉트 해주는 객체
	private RedirectStrategy rs = new DefaultRedirectStrategy();
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		String password = request.getParameter("uPassword");
		HttpSession session = request.getSession();
		
		// 임시비밀번호로 로그인 했을 시
		SavedRequest req = cache.getRequest(request, response);
		if(password.length()>=20) {
			session.setAttribute("msg", "임시비밀번호로 로그인하셨습니다. 비밀번호를 변경해주세요");
			rs.sendRedirect(request, response, "/u_change_pwd");
		}
		else if(req!=null)
			rs.sendRedirect(request, response, req.getRedirectUrl());
		else 
			rs.sendRedirect(request, response, "/");
	}
}
