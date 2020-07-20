
package com.icia.cheatingday.main.controller.mvc;

import java.security.*;
import java.util.*;

import javax.mail.*;
import javax.servlet.http.*;
import javax.validation.constraints.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.access.prepost.*;
import org.springframework.stereotype.*;
import org.springframework.validation.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.support.*;

import com.icia.cheatingday.main.service.mvc.*;
import com.icia.cheatingday.manager.dto.*;
import com.icia.cheatingday.manager.entity.Store;
import com.icia.cheatingday.manager.service.*;
import com.icia.cheatingday.user.dto.*;
import com.icia.cheatingday.util.editor.*;

@Controller
public class MainController {
	@Autowired
	private MainService service;
	
	@InitBinder
	public void init(WebDataBinder wdb) {
		wdb.registerCustomEditor(List.class, "authorities", new AuthorityPropertyEditor());
		}

   
	
   /////////////////////////////////////////    메인 공용        ///////////////////////////////////////////////////
   
   // 홈화면 - 메뉴 카테고리
   @GetMapping("/")
   public ModelAndView main() {
      return new ModelAndView("main").addObject("viewHeader", "include/header.jsp").addObject("viewName", "main/foodcategory.jsp");
   }
   
   
   // [공용] 로그인
   @GetMapping("/login")
   public ModelAndView login() {
      return new ModelAndView("main").addObject("viewHeader", "include/noheader.jsp")
            .addObject("viewName", "main/login.jsp");
   }


   // [공용] 로그아웃
   @PostMapping("/logout")
   public String logout() {
      return "/logout";
   }
   
   // [공용] 일반/사업자 회원가입 선택창
   @GetMapping("/join")
   public ModelAndView join() {
      return new ModelAndView("main").addObject("viewHeader", "include/noheader.jsp").addObject("viewName","main/join.jsp");
   }
   
   
   // [공용] 일반/사업자 아이디찾기 선택창
   @GetMapping("/find_id")
   public ModelAndView findid() {
      return new ModelAndView("main").addObject("viewHeader", "include/noheader.jsp").addObject("viewName","main/findid.jsp");
   }
   
   
   // [공용] 일반/사업자 비밀번호찾기 선택창
   @GetMapping("/find_pwd")
   public ModelAndView findpwd() {
      return new ModelAndView("main").addObject("viewHeader", "include/noheader.jsp").addObject("viewName","main/findpwd.jsp");
   }
   
   @GetMapping("/api")
   public ModelAndView kakaoapi() {
      return new ModelAndView("main").addObject("viewHeader", "include/noheader.jsp").addObject("viewName","main/kakaoAPI.jsp");
   }
   
   // 메인화면 카테고리 선택 후 가게 리스트
   @PreAuthorize("isAuthenticated()")
   @GetMapping("/store_list")
   @ResponseBody
   public ModelAndView storelist(@RequestParam(defaultValue = "star_list") String job, @RequestParam(defaultValue = "1") int pageno, Integer foodNo) {
      if(job.equals("review_list"))
         return new ModelAndView("main").addObject("viewHeader","include/menuheader.jsp").addObject("viewName","main/storelist.jsp").addObject("store", service.listReview(pageno, foodNo)).addObject("filter", "review_list").addObject("foodno", foodNo);
      else if(job.equals("star_list"))         
         return new ModelAndView("main").addObject("viewHeader","include/menuheader.jsp").addObject("viewName","main/storelist.jsp").addObject("store", service.list(pageno, foodNo)).addObject("filter", "star_list").addObject("foodno", foodNo);
      return null;
   }
   
   
   
   
   ///////////////////////////////////////////    일반 회원         //////////////////////////////////////////////
   
   
   // [일반] 회원가입 Get
   @GetMapping("/join_user")
   public ModelAndView UserJoin() {
      return new ModelAndView("main").addObject("viewHeader", "include/noheader.jsp").addObject("viewName", "main/userjoin.jsp");
   }
   
   
   // [일반] 회원가입 Post
   @PostMapping("/join_user")
   public String UserJoin( UserDto.DtoForJoin dto, BindingResult bindingResult, RedirectAttributes ra) throws BindException {
      if(bindingResult.hasErrors()==true)
         throw new BindException(bindingResult);
      try {
         service.UserJoin(dto);
      } catch (IllegalStateException e) {
         e.printStackTrace();
      }
      ra.addFlashAttribute("msg", "치팅데이에 오신것을 환영합니다!");
      return "redirect:/system/msg";
   }
   
   
   // [일반] 아이디 찾기 Get
   @GetMapping("/u_find_id")
   public ModelAndView findUserUsername() {
      return new ModelAndView("main").addObject("viewHeader", "include/noheader.jsp").addObject("viewName", "main/userfindid.jsp");
   }
   
   
   // [일반] 아이디찾기 Post
   @PostMapping("/u_find_id")
   public String findUserUsername(@RequestParam @NotNull String uIrum, @RequestParam @NotNull String uEmail, RedirectAttributes ra) {
      String uUsername = service.findUserUsername(uIrum, uEmail);
      ra.addFlashAttribute("msg", "아이디는 " + uUsername + " 입니다");
      return "redirect:/system/msg";
   }
   
   
   // [일반] '마이페이지' 클릭 시 비밀번호 확인 Get
   @PreAuthorize("isAuthenticated()")
   @GetMapping("/main/u_check_pwd")
   public ModelAndView checkUserPwd() {
      return new ModelAndView("main").addObject("viewHeader", "include/noheader.jsp").addObject("viewName", "main/checkpwd.jsp");
   }
   
   // [일반] '마이페이지' 클릭 시 비밀번호 확인 Post
   @PreAuthorize("isAuthenticated()")
   @PostMapping("/main/u_check_pwd")
   public String checkUserPwd(String uPassword, Principal principal, HttpSession session) {
      service.checkUserPwd(uPassword, principal.getName());
      session.setAttribute("isCheck", "true");
      return "redirect:/user";
   }
   
   
   // [일반] 비밀번호 찾기 (재설정) Get
   @GetMapping("/u_find_pwd")
   public ModelAndView findUserPwd() {
      return new ModelAndView("main").addObject("viewHeader", "include/noheader.jsp").addObject("viewName", "main/userfindpwd.jsp");
   }
   
   
   // [일반] 비밀번호 찾기 (재설정) Post
   @PostMapping("/u_find_pwd")
   public String resetUserPwd(@RequestParam @NotNull String uEmail, @RequestParam @NotNull String uUsername, RedirectAttributes ra) throws MessagingException {
      service.resetUserPwd(uUsername, uEmail);
      ra.addFlashAttribute("msg", "가입하신 이메일로 임시비밀번호를 발송했습니다. 확인해주세요 ^_^");
      return "redirect:/system/msg";
   }
   
   
   // [일반] 비밀번호 변경 페이지로 이동
   @PreAuthorize("isAuthenticated()")
   @GetMapping("/u_change_pwd")
   public ModelAndView changeUserPwd() {
      return new ModelAndView("main").addObject("viewHeader","include/noheader.jsp").addObject("viewName","main/changepwd.jsp");
   }
   
   // [일반] 비밀번호 변경
   @PreAuthorize("isAuthenticated()")
   @PostMapping("/u_change_pwd")
   public String changeUserPwd(@RequestParam @NotNull String uPassword, @RequestParam @NotNull String uNewPassword, Principal principal, RedirectAttributes ra) {
      service.changeUserPwd(uPassword, uNewPassword, principal.getName());
      return "redirect:/";
   }
   
   
   
   
   ////////////////////////////////////// 사업자  ////////////////////////////////////////////////////////////
   
   
   // [사업자] 회원가입 Get
   @GetMapping("/join_manager")
   public ModelAndView ManagerJoin() {
      return new ModelAndView("main").addObject("viewHeader", "include/noheader.jsp").addObject("viewName", "main/managerjoin.jsp");
   }
   
   
   // [사업자] 회원가입 Post
   @PostMapping("/join_manager")
   public String ManagerJoin(ManagerDto.DtoForJoin dto, BindingResult bindingResult, RedirectAttributes ra) throws BindException {
      if(bindingResult.hasErrors()==true)
         throw new BindException(bindingResult);
      try {
         service.ManagerJoin(dto);
      } catch (IllegalStateException e) {
         e.printStackTrace();
      }
      ra.addFlashAttribute("msg", "가입 신청이 완료되었습니다. 관리자 승인 후 가입이 완료됩니다");
      return "redirect:/system/msg";
   }
   
   
   // [사업자] 아이디 찾기 Get
   @GetMapping("/m_find_id")
   public ModelAndView findManagerUsername() {
      return new ModelAndView("main").addObject("viewHeader", "include/noheader.jsp").addObject("viewName", "main/managerfindid.jsp");
   }
   
   
   // [사업자] 아이디찾기 Post
   @PostMapping("/m_find_id")
   public String findManagerUsername(@RequestParam @NotNull String mIrum, @RequestParam @NotNull String mEmail, RedirectAttributes ra) {
      String mUsername = service.findManagerUsername(mIrum, mEmail);
      ra.addFlashAttribute("msg", "아이디는 " + mUsername + " 입니다.");
      return "redirect:/system/msg";
   }
   
   //사업자 페이지로 이동
   @GetMapping("/manager/main")
   public ModelAndView managermain() {
      return new ModelAndView("main").addObject("viewHeader", "include/viewManagerHeader.jsp")
            .addObject("viewName","manager/main.jsp");
   }
   
   
   // [사업자] 비밀번호 찾기 (재설정) Get
   @GetMapping("/m_find_pwd")
   public ModelAndView findManagerPwd() {
      return new ModelAndView("main").addObject("viewHeader", "include/noheader.jsp").addObject("viewName", "main/managerfindpwd.jsp");
   }
   
   // [사업자] 비밀번호 찾기  (재설정) Post
   @PostMapping("/m_find_pwd")
   public String resetManagerPwd(@RequestParam @NotNull String mEmail, @RequestParam @NotNull String mUsername, RedirectAttributes ra) throws MessagingException {
      service.resetManagerPwd(mUsername, mEmail);
      ra.addFlashAttribute("msg", "가입하신 이메일로 임시비밀번호를 발송했습니다. 확인해주세요 ^_^");
      return "redirect:/system/msg";
   }

   
   // [사업자] 비밀번호 변경 페이지로 이동
   @PreAuthorize("isAuthenticated()")
   @GetMapping("/m_change_pwd")
   public ModelAndView changeManagerPwd() {
      return new ModelAndView("main").addObject("viewHeader","include/noheader.jsp").addObject("viewName","main/changepwd.jsp");
   }
   
   // [사업자] 비밀번호 변경
   @PreAuthorize("isAuthenticated()")
   @PostMapping("/m_change_pwd")
   public String changeManagerPwd(@RequestParam @NotNull String mPassword, @RequestParam @NotNull String mNewPassword, Principal principal, RedirectAttributes ra) {
      service.changeManagerPwd(mPassword, mNewPassword, principal.getName());
      return "redirect:/";
   }
   

}

