package com.icia.cheatingday.manager.controller;

import java.io.*;
import java.security.*;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.access.prepost.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.*;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.icia.cheatingday.manager.entity.*;
import com.icia.cheatingday.manager.service.*;

@Controller
public class StoreController {

	@Autowired
	private StoreService service;
	@Autowired
	private ObjectMapper objectMapper;
	
	  //가게리스트 페이지로 이동 
	 @PreAuthorize("isAuthenticated()")
	 @GetMapping("/manager/store_list") 
	 public ModelAndView storeList(Principal principal) throws JsonProcessingException{ 
		 return new ModelAndView("main").addObject("viewName","manager/storelist.jsp")
				 .addObject("viewHeader", "include/viewHeader.jsp") 
				 .addObject("storeList", service.storeList(principal.getName())); }
	 
   	 
	  //가게등록 페이지로 이동
	  @PreAuthorize("isAuthenticated()")
	  @GetMapping("/manager/store_insert") public ModelAndView storeInsert() {
	  return new  ModelAndView("main").addObject("viewName","manager/storeinsert.jsp")
	  .addObject("viewHeader", "include/viewHeader.jsp"); }
	  
	  
	  //가게등록
	  @PreAuthorize("isAuthenticated()")
	  @PostMapping("/manager/store_insert") 
	  public String storeInsert(Store store, MultipartFile sajin, Principal principal)
			  	throws IllegalStateException, IOException {
	  store.setMUsername(principal.getName());
	  service.storeInsert(store, sajin); 
	  return "redirect:/manager/store_list"; 
	  }
	  
	  
	 
	  //가게읽기 페이지로 이동 
	  @PreAuthorize("isAuthenticated()")
	  @GetMapping("/manager/store_read") 
	  public ModelAndView storeRead(int sNum, Principal principal) {
		  String username = principal!=null? principal.getName():null;
		  return new ModelAndView("main").addObject("viewName","manager/storeread.jsp")
				  	.addObject("viewHeader", "include/viewHeader.jsp") 
				  	.addObject("storeRead", service.storeRead(sNum, username));}
	  
	  



	  	
	  	
	  
	 
}
