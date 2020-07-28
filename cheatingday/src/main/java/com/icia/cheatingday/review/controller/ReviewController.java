package com.icia.cheatingday.review.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.icia.cheatingday.handler.MessagingHandler;
import com.icia.cheatingday.review.dto.ReviewDto;
import com.icia.cheatingday.review.entity.Review;
import com.icia.cheatingday.review.service.ReviewService;

import lombok.NonNull;

@Controller
public class ReviewController {
	@Autowired
	private ReviewService service;
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/review/read")
	public ModelAndView read(@NonNull Integer rNo) {
		return new ModelAndView("main")
				.addObject("viewName", "review/read.jsp")
				.addObject("viewHeader", "include/viewHeader.jsp");
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/review/list")
	public ModelAndView list(@RequestParam(defaultValue = "1") int pageno) {
		return new ModelAndView("main")
				.addObject("viewName", "review/list.jsp")
				.addObject("viewHeader", "include/viewHeader.jsp")
				.addObject("page", service.list(pageno));
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/review/write")
	public ModelAndView write(int orderNo) {
		return new ModelAndView("main")
				.addObject("viewName", "review/write.jsp")
				.addObject("viewHeader", "include/viewHeader.jsp")
				.addObject("orderNo", orderNo);
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/review/write")
	public String write(@Valid ReviewDto.DtoForWrite dto, BindingResult bindingResult, Principal principal,HttpServletRequest request) throws BindException {
		if(bindingResult.hasErrors())
			throw new BindException(bindingResult);
		dto.setUUsername(principal.getName());
		service.write(dto);
		return "redirect:/review/list";
	}
}
