package com.icia.cheatingday.order.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.icia.cheatingday.order.entity.OrderEntity;

@Controller
public class OrderController {
	@Inject private OrderEntity orderEntity;
	
	@GetMapping("/order/prderpage")
	public ModelAndView order() {
		return new ModelAndView("main").addObject("viewName", "board/orderpage.jsp");
	}
	
}
