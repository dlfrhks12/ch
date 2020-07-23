package com.icia.cheatingday.center.controller.mvc;

import java.security.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.lang.*;
import org.springframework.security.access.prepost.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.icia.cheatingday.center.dto.*;
import com.icia.cheatingday.center.service.mvc.*;

@Controller
public class QnAController {
	@Autowired
	private QnAService service;
	
	//[관리자,사업자]QNA 읽기
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/center/read")
	public ModelAndView read(@NonNull Integer qNo) {
		return new ModelAndView("main").addObject("viewName","center/read.jsp").addObject("viewHeader", "include/viewHeader.jsp");
	}
	//[관리자,사업자]QNA 리스트
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/center/list")
	public ModelAndView list(@RequestParam(defaultValue = "1") int pageno, Integer qCano ) {
		return new ModelAndView("main").addObject("viewHeader", "include/viewHeader.jsp").addObject("viewName", "center/list.jsp").addObject("page", service.list(pageno, qCano));
	}
	//[사업자] QNA작성
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	@GetMapping("/center/write")
	public ModelAndView read() {
		return new ModelAndView("main").addObject("viewHeader", "include/viewHeader.jsp").addObject("viewName", "center/write.jsp").addObject("category", service.getQcano()	);
	}
	//[사업자] QNA작성
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	@PostMapping("/center/write")
	public String write(QnADto.DtoForWrite dto, Principal principal) {
		return "redirect:/center/read?qNo=" +service.write(dto,principal.getName());
	}
}
