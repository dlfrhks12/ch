package com.icia.cheatingday.order.controller;

import javax.inject.*;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.icia.cheatingday.order.entity.*;

@Controller
public class OrderController {
	@Inject private OrderEntity orderEntity;
	
	@GetMapping("/order/prderpage")
	public ModelAndView order() {
		return new ModelAndView("main").addObject("viewName", "board/orderpage.jsp");
	}
	
}
