package com.icia.cheatingday.freeboard.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icia.cheatingday.freeboard.dto.FreeBoardDto;
import com.icia.cheatingday.freeboard.service.FreeBoardService;


@Controller
public class FreeBoardController {
	@Autowired
	private FreeBoardService service;
	@GetMapping("/board/read")
	public ModelAndView read(@NonNull Integer bno) {
		return new ModelAndView("main").addObject("viewName", "board/read.jsp");
	}

	@GetMapping("/board/list")
	public ModelAndView list(@RequestParam(defaultValue = "1")int pageno,@Nullable String username,Integer cateno) {
		return new ModelAndView("main").addObject("viewName", "board/list.jsp").addObject("page", service.list(pageno, username)).addObject("category", service.getBoardcate());
	}
	@GetMapping("/board/write")
	public ModelAndView write() {
		return new ModelAndView("main").addObject("viewName", "board/write.jsp").addObject("category", service.getBoardcate());
		
	}
	@PostMapping("/board/write")
	public String write(@Valid FreeBoardDto.DtoForWrite dto, BindingResult bindingResult,Principal principal, HttpServletRequest request) throws BindException{
		System.out.println("222222222222222222222222222222222222222222222");
		System.out.println(principal);
		if(bindingResult.hasErrors())
			throw new BindException(bindingResult);
		dto.setUsername(principal.getName());
		System.out.println(principal.getName());
		System.out.println(dto.setUsername(principal.getName()));
		System.out.println(dto);
		try {
			return "redirect:/board/read?bno=" +service.write(dto);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
	

}


