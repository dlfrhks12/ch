package com.icia.cheatingday.main;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.support.*;

import com.icia.cheatingday.manager.dto.*;

@Controller
public class MainController {
	@Autowired
	private MainService service;
	
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
