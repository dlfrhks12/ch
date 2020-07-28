package com.icia.cheatingday.manager.controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.icia.cheatingday.manager.dto.ManagerDto;
import com.icia.cheatingday.manager.dto.MenuDto;
import com.icia.cheatingday.manager.entity.MenuEntity;
import com.icia.cheatingday.manager.service.ManagerService;

@Controller
public class ManagerController {

	@Autowired
	private ManagerService service; 
	
	
	//메뉴관리 리스트로 이동
	@GetMapping("/manager/menu_list")
	public ModelAndView menuList(Principal principal) {
		return new ModelAndView("main").addObject("viewName", "manager/menulist.jsp")
				.addObject("viewHeader", "include/viewHeader.jsp")
				.addObject("menuList",service.menuList(principal.getName()));
			
	}
	
		//메뉴쓰기로 이동
		@GetMapping("/manager/menu_write")
		public ModelAndView menuWrite() {
			return new ModelAndView("main").addObject("viewName", "manager/menuwrite.jsp")
					.addObject("viewHeader", "include/viewHeader.jsp");
		}
		
		//메뉴쓰기
		@PostMapping("/manager/menu_write")
		public String menuWrite(MenuDto.DtoForRead dto, MultipartFile sajin, Principal principal) 
				throws IllegalStateException, IOException {
			dto.setMUsername(principal.getName());
			service.write(dto, sajin, principal.getName());
			return "redirect:/manager/menu_list";
		}
		
		//메뉴읽기 페이지로 이동
		@GetMapping("/manager/menu_read")
		public ModelAndView menuRead(Integer menuno) {
			return new ModelAndView("main").addObject("viewName", "manager/menuread.jsp")
					.addObject("viewHeader", "include/viewHeader.jsp")
					.addObject("menuRead",service.menuRead(menuno));
		}
		
		
	//내 정보 읽기
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/manager/information")
	public ModelAndView managerInfoRead(String mUsername) {
		return new ModelAndView("main").addObject("viewName","manager/information.jsp")
				.addObject("viewHeader", "include/viewHeader.jsp")
				.addObject("managerInfo",service.read(mUsername));
	}
	

}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	