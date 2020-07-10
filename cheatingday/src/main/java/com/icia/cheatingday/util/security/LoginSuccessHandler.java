package com.icia.cheatingday.util.security;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.savedrequest.*;
import org.springframework.stereotype.*;

import com.icia.cheatingday.manager.dao.*;
import com.icia.cheatingday.manager.entity.*;
import com.icia.cheatingday.user.dao.*;
import com.icia.cheatingday.user.entity.*;

@Component("loginSuccessHandler")
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	
	@Autowired
	UserDao userDao;
	@Autowired
	ManagerDao managerDao;
	private User user = new User();
	private ManagerEntity manager = new ManagerEntity();
	
	// 사용자가 가려던 목적지를 저장하는 객체
	private RequestCache cache = new HttpSessionRequestCache();
		
	// 리다이렉트 해주는 객체
	private RedirectStrategy rs = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		SavedRequest req = cache.getRequest(request, response);
		String id = request.getParameter("a_username");
		String password = request.getParameter("a_password");
		
		if(userDao.findById(id)!=null) {
			user = userDao.findById(id);
		}
		else if(managerDao.findById(id)!=null) {
			manager = managerDao.findById(id);
		}
		
		// 임시비밀번호로 로그인 했을 시
		if(password.length()>=20) {
			if(user.getStatus()!=null) {
				session.setAttribute("msg", "임시비밀번호로 로그인하셨습니다. 비밀번호를 변경해주세요");
				rs.sendRedirect(request, response, "/u_change_pwd");
			} else if(user.getStatus()==null) {
				session.setAttribute("msg", "임시비밀번호로 로그인하셨습니다. 비밀번호를 변경해주세요");
				rs.sendRedirect(request, response, "/m_change_pwd");
			}
		} else if(req!=null) 
			rs.sendRedirect(request, response, req.getRedirectUrl());
		// 본인 비번으로 로그인 시
		else  
			rs.sendRedirect(request, response, "/");
	}
}
