package com.icia.cheatingday.notice.controller.mvc;

import java.io.*;
import java.security.*;

import javax.validation.constraints.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.lang.*;
import org.springframework.security.access.prepost.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.icia.cheatingday.notice.dto.*;
import com.icia.cheatingday.notice.service.mvc.*;

@Controller
public class NoticeController {
	@Autowired
	private NoticeService service;
	
	@GetMapping("/notice/list")
	public ModelAndView list(@RequestParam(defaultValue = "1") int pageno) {
		return new ModelAndView("main").addObject("viewHeader", "include/noheader.jsp").addObject("viewName", "notice/list.jsp").addObject("page", service.list(pageno));
	}
	@GetMapping("/notice/read")
	public ModelAndView read(@NotNull Integer nNo, Principal principal ) {
		String aUsername = principal!=null? principal.getName():null;
		return new ModelAndView("main").addObject("viewHeader", "include/noheader.jsp").addObject("viewName", "notice/read.jsp").addObject("notice", service.read(nNo, aUsername));
	}
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/notice/write")
	public ModelAndView write() {
		return new ModelAndView("main").addObject("viewHeader", "include/noheader.jsp").addObject("viewName","notice/write.jsp");
	}
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/notice/write")
	public String write(NoticeDto.DtoForWrite dto, Principal principal) {
		dto.setAUsername(principal.getName());
		return "redirect:/board/read?bno=" + service.write(dto);
	}
}
