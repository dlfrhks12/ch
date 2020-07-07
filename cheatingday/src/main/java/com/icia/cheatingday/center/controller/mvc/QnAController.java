package com.icia.cheatingday.center.controller.mvc;

import java.security.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.lang.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.icia.cheatingday.center.dto.*;
import com.icia.cheatingday.center.service.mvc.*;

@Controller
public class QnAController {
	@Autowired
	private QnAService service;
	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping("/center/read")
	public ModelAndView read(@NonNull Integer qNo, Principal principal) throws JsonProcessingException {
		String username = principal!=null? principal.getName():null;
		ModelAndView mav = new ModelAndView("main").addObject("viewHeader", "include/header.jsp").addObject("viewName","center/read.jsp");
		QnADto.DtoForRead dto = service.read(qNo, username);
		String json = objectMapper.writeValueAsString(dto);
		mav.addObject("qna", json).addObject("category", service.getQcano());
		return mav;
	}
	
	@GetMapping("/center/list")
	public ModelAndView list(@RequestParam(defaultValue = "1") int pageno, @RequestParam(defaultValue = "1")int qCano ) {
		return new ModelAndView("main").addObject("viewHeader", "include/header.jsp").addObject("viewName", "center/list.jsp").addObject("page", service.list(pageno, qCano));
	}
	@GetMapping("/center/write")
	public ModelAndView read() {
		return new ModelAndView("main").addObject("viewHeader", "include/header.jsp").addObject("viewName", "center/read.jsp");
	}
	@PostMapping("/center/write")
	public String write(QnADto.DtoForWrite dto, Principal principal) {
		dto.setMUsername(principal.getName());
		return "redirect:/center/read?qNo=" +service.write(dto);
	}
}
