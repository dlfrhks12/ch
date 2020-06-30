package com.icia.cheatingday.freeboard.controller;

import java.io.*;
import java.security.*;

import javax.servlet.http.*;
import javax.validation.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.lang.*;
import org.springframework.stereotype.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.icia.cheatingday.freeboard.dto.*;
import com.icia.cheatingday.freeboard.service.*;


@Controller
public class FreeBoardController {
	@Autowired
	private FreeBoardService service;
	@GetMapping("/board/read")
	public ModelAndView read(@NonNull Integer bno) {
		return new ModelAndView("main").addObject("viewName", "board/read.jsp");
	}

	@GetMapping("/board/list")
	public ModelAndView list(@RequestParam(defaultValue = "1")int pageno,@Nullable String username ) {
		return new ModelAndView("main").addObject("viewName", "board/list.jsp");
	}
	@GetMapping("/board/write")
	public ModelAndView write() {
		return new ModelAndView("main").addObject("viewName", "/board/write.jsp");
		
	}
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


