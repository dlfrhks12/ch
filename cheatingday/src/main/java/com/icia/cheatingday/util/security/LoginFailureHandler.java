/*
 * package com.icia.cheatingday.util.security;
 * 
 * import java.io.*;
 * 
 * import javax.servlet.*; import javax.servlet.http.*;
 * 
 * import org.springframework.beans.factory.annotation.*; import
 * org.springframework.security.authentication.*; import
 * org.springframework.security.core.*; import
 * org.springframework.security.web.*; import
 * org.springframework.security.web.authentication.*; import
 * org.springframework.stereotype.*; import org.springframework.web.servlet.*;
 * import org.springframework.web.servlet.mvc.support.*; import
 * org.springframework.web.servlet.support.*;
 * 
 * import com.icia.aboard.dao.*; import com.icia.aboard.entity.*; import
 * com.icia.cheatingday.admin.dao.*; import com.icia.cheatingday.admin.entity.*;
 * 
 * @Component("loginFailureHandler") public class LoginFailureHandler extends
 * SimpleUrlAuthenticationFailureHandler { // 로그인에 실패한다 // 아이디가 없다 :
 * InternalAuthenticationServiceException // 인증서비스를 직접 구현해야 사용 가능 // 비밀번호가 틀렸다 :
 * BadCredentialsException // 계정 블록 : DisabledException
 * 
 * @Autowired private AdminDao dao; private RedirectStrategy rs = new
 * DefaultRedirectStrategy(); private FlashMap flashMap = new FlashMap();
 * 
 * @Override public void onAuthenticationFailure(HttpServletRequest request,
 * HttpServletResponse response, AuthenticationException exception) throws
 * IOException, ServletException { String aUsername =
 * request.getParameter("aUsername"); HttpSession session =
 * request.getSession(); if(exception instanceof BadCredentialsException) {
 * Admin admin = dao.findById(aUsername);
 * 
 * // 아이디가 없는 경우 if(admin==null) { session.setAttribute("msg",
 * "아이디를 찾을 수 없습니다"); } } rs.sendRedirect(request, response, "/login"); }
 * 
 * }
 * 
 * 
 * 
 * 
 * 
 * 
 */