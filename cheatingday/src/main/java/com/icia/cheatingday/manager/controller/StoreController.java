package com.icia.cheatingday.manager.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icia.cheatingday.manager.entity.Store;
import com.icia.cheatingday.manager.service.StoreService;

@Controller
public class StoreController {

	@Autowired
	private StoreService service;
	@Autowired
	private ObjectMapper objectMapper;
	
	  //가게리스트 페이지로 이동 - 해당하는 사장님만 자신의 가게 리스트를 볼 수 있어 
	
	 @PreAuthorize("isAuthenticated()")
	 
	 @GetMapping("/manager/store_list") 
	 public ModelAndView storeList(Principal principal) throws JsonProcessingException{ 
		 return new ModelAndView("main").addObject("viewName","manager/storelist.jsp")
				 .addObject("viewHeader", "include/noheader.jsp") 
				 .addObject("storeList", service.storeList(principal.getName())); }
	 
   	 
	  //가게읽기 페이지로 이동 - 해당하는 사장님만 자신의 가게를 읽을 수 있어.
	  @PreAuthorize("isAuthenticated()")
	  @GetMapping("/manager/store_read") 
	  public ModelAndView storeRead(int sNum, Principal principal) {
		  String username = principal!=null? principal.getName():null;
		  return new ModelAndView("main").addObject("viewName","manager/storeread.jsp")
				  	.addObject("viewHeader", "include/noheader.jsp") 
				  	.addObject("storeRead", service.storeRead(sNum, username));}
	  
	  //가게등록 페이지로 이동
	  @PreAuthorize("isAuthenticated()")
	  @GetMapping("/manager/store_insert") public ModelAndView storeInsert() {
	  return new  ModelAndView("main").addObject("viewName","manager/storeinsert.jsp")
	  .addObject("viewHeader", "include/noheader.jsp"); }
	  
	  
	  //가게등록
	  @PreAuthorize("isAuthenticated()")
	  @PostMapping("/manager/store_insert") 
	  public String storeInsert(Store store, MultipartFile sajin, Principal principal)
			  	throws IllegalStateException, IOException {
	  store.setMUsername(principal.getName());
	  service.storeInsert(store, sajin); 
	  return "redirect:/manager/store_list"; 
	  }
	 
}
