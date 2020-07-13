
package com.icia.cheatingday.util.security;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;

import com.icia.cheatingday.manager.dao.*;
import com.icia.cheatingday.manager.entity.*;
import com.icia.cheatingday.user.dao.*;
import com.icia.cheatingday.user.entity.*;



@Component("loginFailureHandler")
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler { // 로그인에 실패한다 // 아이디가 없다 :
	// 로그인에 실패했을 경우
	@Autowired
	private UserDao userDao;
	@Autowired
	private ManagerDao managerDao;
	private RedirectStrategy rs = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String uUsername = request.getParameter("u_username");
		String mUsername = request.getParameter("m_username");
		HttpSession session = request.getSession();
		
		if(exception instanceof InternalAuthenticationServiceException) {
			User user = userDao.findById(uUsername);
			ManagerEntity manager = managerDao.findById(mUsername);
			// 없는 아이디를 쳤다
			if(user==null || manager==null) 
				session.setAttribute("msg", "아이디를 찾을 수 없습니다");
		} else
			session.setAttribute("msg", "비밀번호가 일치하지 않습니다");
		rs.sendRedirect(request, response, "/login");
	}
}
