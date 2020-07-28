package com.icia.cheatingday.notice.controller.mvc;

import java.security.*;

import javax.validation.constraints.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.access.prepost.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.icia.cheatingday.notice.dto.*;
import com.icia.cheatingday.notice.service.mvc.*;

@Controller
public class NoticeController {
	@Autowired
	private NoticeService service;
	@Autowired
	private ObjectMapper objectMapper;
	
	//[전체]공지목록
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/notice/list")
	public ModelAndView list(@RequestParam(defaultValue = "1") int pageno) {
		return new ModelAndView("main")
				.addObject("viewHeader", "include/viewHeader.jsp")
				.addObject("viewName", "notice/list.jsp")
				.addObject("page", service.list(pageno));
	}
	//[전체]공지읽기
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/notice/read")
	public ModelAndView read(@NotNull Integer nNo, Principal principal ) throws JsonProcessingException {
		String aUsername = principal!=null? principal.getName():null;
		ModelAndView mav = new ModelAndView("main").addObject("viewHeader", "include/viewHeader.jsp")
				.addObject("viewName", "notice/read.jsp");
		NoticeDto.DtoForRead dto = service.read(nNo, aUsername);
		String json = objectMapper.writeValueAsString(dto);
		mav.addObject("notice", json);
		return mav;
	}
	//[관리자]공지쓰기
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/notice/write")
	public ModelAndView write() {
		return new ModelAndView("main").addObject("viewHeader", "include/viewHeader.jsp").addObject("viewName","notice/write.jsp");
	}
	//[관리자]공지쓰기
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/notice/write")
	public String write(NoticeDto.DtoForWrite dto, Principal principal) {
		dto.setAUsername(principal.getName());
		return "redirect:/notice/read?nNo=" + service.write(dto);
	}
}
