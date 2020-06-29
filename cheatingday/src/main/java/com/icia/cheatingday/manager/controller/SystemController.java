package com.icia.cheatingday.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SystemController {

	//입점신청완료 메시지창으로 이동
		@GetMapping("/system/msg")
		public ModelAndView msg() {
			return new ModelAndView("main").addObject("viewName", "system/msg.jsp");
		}
}
