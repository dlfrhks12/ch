package com.icia.cheatingday.review.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icia.cheatingday.handler.MessagingHandler;
import com.icia.cheatingday.review.dto.ReviewDto;
import com.icia.cheatingday.review.service.ReviewService;

import lombok.NonNull;

@Controller
public class ReviewController {  
	@Autowired
	private ReviewService service;
	@Autowired
	private MessagingHandler handler;
	
	@GetMapping("/review/read")
	public ModelAndView read( @NonNull Integer rNo) {
		return new ModelAndView("main").addObject("viewName", "review/read.jsp").addObject("viewHeader", "include/noheader.jsp");
	}
	@GetMapping("/review/list")
	public ModelAndView list(@RequestParam(defaultValue = "rno_list")String job, @RequestParam(defaultValue = "1")int pageno, Integer rNo, Integer rStarPoint) {
		
		if(job.equals("rno_list"))
			return new ModelAndView("main").addObject("viewHeader", "include/noheader.jsp").addObject("viewName", "review/list.jsp").addObject("review", service.listByRno(rNo, pageno)).addObject("filter", "rno_list").addObject("rNo", rNo);
		else if(job.equals("star_list"))
			return new ModelAndView("main").addObject("viewHeader", "include/noheader.jsp").addObject("viewName", "review/list.jsp").addObject("review", service.listByStar(pageno, rStarPoint, rNo)).addObject("filter", "star_list").addObject("rNo", rNo);
		return null;
	}
	@PostMapping("/review/list")
	public String index(Principal principal, Model model) {
		if(principal!=null)
			model.addAttribute("uUsername",principal.getName());
		return "review/list";
	}
	@GetMapping("/review/write")
	public ModelAndView write(int sNum) {
		return new ModelAndView("main").addObject("viewName", "review/write.jsp").addObject("viewHeader", "include/noheader.jsp").addObject("snum", sNum);
	}
	@PostMapping("/review/write")
	public String write(@Valid ReviewDto.DtoForWrite dto, BindingResult bindingResult, Principal principal, HttpServletRequest request) throws BindException, IOException{
		if(bindingResult.hasErrors())
			throw new BindException(bindingResult);
		dto.setUUsername(principal.getName());
		return "redirect:/review/read?rNo=" +service.write(dto);
	}

}
