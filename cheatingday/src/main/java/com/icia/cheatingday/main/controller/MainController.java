package com.icia.cheatingday.main.controller;

import java.awt.*;

import org.springframework.stereotype.*;
import org.springframework.validation.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.support.*;

import com.icia.cheatingday.manager.dto.*;
import com.icia.cheatingday.util.editor.*;

@Controller
public class MainController {
	@InitBinder
	public void init(WebDataBinder wdb) {
		wdb.registerCustomEditor(List.class, "authorities", new AuthorityPropertyEditor());
	}
	
	@GetMapping("/login")
	public ModelAndView login() {
		return  new ModelAndView("main").addObject("viewName", "main/login.jsp");
	}
	
	/*
	@GetMapping
	public ModelAndView join() {
		return new ModelAndView("main").addObject("viewName", "main/join.jsp");
	}
	
	@PostMapping("/main/join_manager")
	public String join(ManagerDto.DtoForJoin dto, BindingResult bindingResult, RedirectAttributes ra) {
		if(bindingResult.hasErrors()==true)
			// throw new BindException(bindingResult);
	ra.addFlashAttribute("msg", "가입이 완료되었습니다.");
	return "redirect:/system/msg";
	}
	*/
}
