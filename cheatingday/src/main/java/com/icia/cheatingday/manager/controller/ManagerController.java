package com.icia.cheatingday.manager.controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.cheatingday.manager.dto.ManagerDto;
import com.icia.cheatingday.manager.entity.MenuEntity;
import com.icia.cheatingday.manager.service.ManagerService;

@Controller
public class ManagerController {

	@Autowired
	private ManagerService service; 
	
	//메뉴관리 리스트로 이동
	@GetMapping("/manager/menu_list")
	public ModelAndView menuList() {
		return new ModelAndView("main").addObject("viewName", "manager/menulist.jsp")
				.addObject("menuList",service.menuList());
				 
	}
	
	//메뉴읽기로 이동
	@GetMapping("/manager/menu_read")
	public ModelAndView menuRead(int menuno) {
		return new ModelAndView("main").addObject("viewName", "manager/menuread.jsp")
				.addObject("menuRead",service.menuRead(menuno));
	}
	
	//메뉴쓰기로 이동
	@GetMapping("/manager/menu_write")
	public ModelAndView menuWrite() {
		return new ModelAndView("main").addObject("viewName", "manager/menuwrite.jsp");
	}
	
	//메뉴쓰기
	@PostMapping("/manager/menu_write")
	public String menuWrite(MenuEntity menu, MultipartFile sajin) throws IllegalStateException, IOException {
		service.write(menu, sajin);
		return "redirect:/manager/menu_read";
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	//입점신청 페이지로 이동
	@GetMapping("/manager/store_apply")
	public ModelAndView storeApplyInsert() {
		return new ModelAndView("main").addObject("viewName", "manager/storeapply.jsp");
	}
	
	//입점신청
	@PostMapping("/manager/store_apply")
	public String storeApplyInsert(ManagerDto.DtoForWrite dto) {
		service.write(dto);
		return "redirect:/system/msg";
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//내 정보 읽기
	@GetMapping("/manager/information")
	public ModelAndView managerInfoRead() {
		return new ModelAndView("main").addObject("viewName","manager/information.jsp");
	}
	
}
