package com.icia.cheatingday.user.controller.mvc;

import java.security.*;
import java.time.*;

import javax.servlet.http.*;
import javax.validation.constraints.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.access.prepost.*;
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
		wdb.registerCustomEditor(LocalDateTime.class, "birthDate", new DatePropertyEditor());
	}

	@Autowired
	private UserService service;

	// 포인트 리스트
	@GetMapping("/user/point")
	public ModelAndView pointList(@RequestParam(defaultValue = "1") int pageno, String uUsername) {
		return new ModelAndView("main")
				.addObject("viewHeader", "include/viewHeader.jsp")
				.addObject("viewName", "user/point.jsp")
				.addObject("page", service.pointList(pageno, uUsername))
				.addObject("count", service.count(uUsername))
				.addObject("username", uUsername);
	}

	// 리뷰 리스트
	@GetMapping("/user/reviewlist")
	public ModelAndView reviewList(@RequestParam(defaultValue = "1") int pageno, String uUsername, Integer rNo) {
		return new ModelAndView("main")
				.addObject("viewHeader", "include/viewHeader.jsp")
				.addObject("viewName", "user/reviewlist.jsp")
				.addObject("page", service.reviewList(pageno, uUsername))
				.addObject("username", uUsername);
	}
	// 구매내역 리스트

	@PreAuthorize("isAuthenticated()")

	@GetMapping("/user/buylist")
	public ModelAndView buyList(@RequestParam(defaultValue = "1") int pageno, String uUsername) {
		return new ModelAndView("main")
		.addObject("viewHeader", "include/viewHeader.jsp")
				.addObject("viewName", "user/buylist.jsp")
				.addObject("page", service.buyList(pageno, uUsername))
				.addObject("username", uUsername);
	}

	// 내 정보 읽기
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/user/mypage")
	public ModelAndView myPage(HttpSession session, Principal principal) {
		if (session.getAttribute("isCheck") == null)
			return new ModelAndView("redirect:/user/check_pwd");
		else
			return new ModelAndView("main")
					.addObject("viewHeader", "include/viewHeader.jsp")
					.addObject("viewName", "user/mypage.jsp")
					.addObject("user", service.myPage(principal.getName()));
	}

	// 비밀번호 확인 get
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/user/check_pwd")
	public ModelAndView checkPwd() {
		return new ModelAndView("main")
				.addObject("viewHeader", "include/viewHeader.jsp")
				.addObject("viewName", "user/check_pwd.jsp");
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
				.addObject("viewHeader", "include/viewHeader.jsp")
				.addObject("viewName", "user/change_pwd.jsp");
	}

	// 비밀번호 변경 post
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/user/change_pwd")
	public String changePwd(@RequestParam @NotNull String uPassword, @RequestParam @NotNull String newUPassword,
			Principal principal, RedirectAttributes ra) {
		service.changePwd(uPassword, newUPassword, principal.getName());
		ra.addFlashAttribute("msg", "비밀번호를 변경했습니다");
		return "redirect:/";
	}

}
