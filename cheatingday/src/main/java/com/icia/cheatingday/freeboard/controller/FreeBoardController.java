package com.icia.cheatingday.freeboard.controller;

import java.io.*;
import java.security.*;

import javax.servlet.http.*;
import javax.validation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.beans.factory.annotation.*;
import org.springframework.lang.*;
import org.springframework.stereotype.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.icia.cheatingday.freeboard.dto.*;
import com.icia.cheatingday.freeboard.service.*;
import com.icia.cheatingday.handler.MessagingHandler;


@Controller
public class FreeBoardController {
	@Autowired
	private FreeBoardService service;
	@Autowired
	private MessagingHandler handler;
	@GetMapping("/board/read")
	public ModelAndView read(@NonNull Integer bno) {
		return new ModelAndView("main").addObject("viewName", "board/read.jsp").addObject("category", service.getBoardcate()).addObject("viewHeader", "include/viewHeader.jsp");
	}

	@GetMapping("/board/list")
	public ModelAndView list(@RequestParam(defaultValue = "1")int pageno,@Nullable String username, Integer cateno) {
		
		return new ModelAndView("main").addObject("viewName", "board/list.jsp")
				.addObject("page", service.list(pageno, username, cateno))
				.addObject("category", service.getBoardcate()).addObject("viewHeader", "include/viewHeader.jsp");
	}
	@PostMapping("/board/list")
	public String index(Principal principal, Model model) {
		if(principal!=null)
			model.addAttribute("username",principal.getName());
		return "board/list";
	}
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/board/write")
	public ModelAndView write() {
		return new ModelAndView("main").addObject("viewName", "board/write.jsp").addObject("category", service.getBoardcate()).addObject("viewHeader", "include/viewHeader.jsp");
		
	}
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/board/write")
	public String write(@Valid FreeBoardDto.DtoForWrite dto, BindingResult bindingResult,Principal principal, HttpServletRequest request) throws BindException{
		if(bindingResult.hasErrors())
			throw new BindException(bindingResult);
		dto.setUsername(principal.getName());
	
		try {
			return "redirect:/board/read?bno=" +service.write(dto);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
	
	
	
}


