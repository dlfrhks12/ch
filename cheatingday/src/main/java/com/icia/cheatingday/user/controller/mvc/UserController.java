package com.icia.cheatingday.user.controller.mvc;

import java.security.*;
import java.time.*;

import javax.servlet.http.*;
import javax.validation.constraints.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.access.prepost.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.logout.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.support.*;

import com.icia.cheatingday.user.service.mvc.*;
import com.icia.cheatingday.util.editor.*;

@Controller
public class UserController {
	// 가입날짜
	@InitBinder
	public void init(WebDataBinder wdb) {
		wdb.registerCustomEditor(LocalDateTime.class, "birthDate",new DatePropertyEditor());
	}
	
	@Autowired
	private UserService service;
	
	// 포인트 리스트
			@GetMapping("/point/list")
			public ModelAndView list(@RequestParam(defaultValue = "1")int pageno, String uUsername) {
				return new ModelAndView("main").addObject("viewName", "point/list.jsp").addObject("page", service.list(pageno));
			}
	
	// 내 정보 읽기
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/user/mypage")
	public ModelAndView myPage(HttpSession session, Principal principal) {
		if(session.getAttribute("isCheck")==null)
			return new ModelAndView("redirect:/user/check_pwd");
		else 
		return new ModelAndView("main")
			.addObject("viewHeader", "include/noheader.jsp")
			.addObject("viewName", "user/mypage.jsp")
			.addObject("user",service.myPage(principal.getName()));
	}

	// 비밀번호 확인 get
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/user/check_pwd")
	public ModelAndView checkPwd() {
		return new ModelAndView("main")
			.addObject("viewHeader", "include/noheader.jsp")
			.addObject("viewName","user/check_pwd.jsp");
			
	}
	// 비밀번호 확인 post
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/user/check_pwd")
	public String checkPwd(String uPassword, Principal principal, HttpSession session) {
		service.checkPwd(uPassword, principal.getName());
		session.setAttribute("isCheck", "true");
		return "redirect:/user/mypage";
	}
	
	// 비밀번호 변경 get
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/user/change_pwd")
	public ModelAndView changePwd() {
		return new ModelAndView("main")
			.addObject("viewHeader", "include/noheader.jsp")
			.addObject("viewName","user/change_pwd.jsp");
	}
	// 비밀번호 변경 post
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/user/change_pwd")
	public String changePwd(@RequestParam @NotNull String uPassword, @RequestParam @NotNull String newUPassword, Principal principal, RedirectAttributes ra) {
		service.changePwd(uPassword, newUPassword, principal.getName());
		ra.addFlashAttribute("msg", "비밀번호를 변경했습니다");
		return "redirect:/";
	}
	// 즐겨찾기
	
	// 회원탈퇴
	@DeleteMapping("/user/resign")
	public String resign(SecurityContextLogoutHandler handler, HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		service.resign(authentication.getName());
		handler.logout(request, response, authentication);
		return "redirect:/";
	}
}





