package com.icia.cheatingday.system.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

public class SystemController {

	@GetMapping
	public ModelAndView msg() {
		return new ModelAndView("main").addObject("viewHeader", "include/noheader.jsp").addObject("viewName", "system/msg.jsp");
	}
}
