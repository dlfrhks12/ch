
package com.icia.cheatingday.util.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.icia.cheatingday.manager.dao.ManagerDao;
import com.icia.cheatingday.user.dao.UserDao;

@Component("loginFailureHandler")
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler { // 로그인에 실패한다 // 아이디가 없다 :
	// 로그인에 실패했을 경우
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private ManagerDao managerDao;
	private RedirectStrategy rs = new DefaultRedirectStrategy();
	
	/*
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String uUsername = request.getParameter("uUsername");
		String mUsername = request.getParameter("mUsername");
		HttpSession session = request.getSession();
		if(exception instanceof BadCredentialsException) {
			User user = userDao.findById(uUsername);
			ManagerEntity manager = managerDao.findById(mUsername);
			
			// 아이디가 없는 경우
			if(user==null) {
				session.setAttribute("msg", "아이디를 찾을 수 없습니다");
			} else if(manager==null){
				session.setAttribute("msg", "아이디를 찾을 수 없습니다");
			} else {
				session.setAttribute("msg", "비밀번호가 일치하지 않습니다");
			}
		}
		rs.sendRedirect(request, response, "/login");
	}
	*/
}
	