package com.icia.cheatingday.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class SystemController {

	@GetMapping("/system/msg")
	public ModelAndView msg() {
		return new ModelAndView("main").addObject("viewHeader", "include/noheader.jsp").addObject("viewName", "system/msg.jsp");
	}
}
