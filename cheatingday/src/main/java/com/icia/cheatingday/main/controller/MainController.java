package com.icia.cheatingday.main.controller;

import java.awt.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.icia.cheatingday.manager.dto.ManagerDto;
import com.icia.cheatingday.util.editor.AuthorityPropertyEditor;

@Controller
public class MainController {
	public void init(WebDataBinder wdb) {
		wdb.registerCustomEditor(List.class, "authorities", new AuthorityPropertyEditor());
	}
	
	@GetMapping
	public ModelAndView join() {
		return new ModelAndView("main").addObject("viewName", "main/join.jsp");
	}
	
	@PostMapping("/main/join")
	public String join(ManagerDto.DtoForJoin dto, BindingResult bindingResult, RedirectAttributes ra) {
		if(bindingResult.hasErrors()==true)
			// throw new BindException(bindingResult);
	ra.addFlashAttribute("msg", "가입이 완료되었습니다.");
	return "redirect:/system/msg";
	}
}
