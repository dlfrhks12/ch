package com.icia.cheatingday.manager.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
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
				 .addObject("viewHeader", "include/viewManagerHeader.jsp") 
				 .addObject("storeList", service.storeList(principal.getName())); }
	 
   	 
	  //가게읽기 페이지로 이동 - 해당하는 사장님만 자신의 가게를 읽을 수 있어.
	  @PreAuthorize("isAuthenticated()")
	  @GetMapping("/manager/store_read") 
	  public ModelAndView storeRead(int sNum, Principal principal) {
		  String username = principal!=null? principal.getName():null;
		  return new ModelAndView("main").addObject("viewName","manager/storeread.jsp")
				  	.addObject("viewHeader", "include/viewManagerHeader.jsp") 
				  	.addObject("storeRead", service.storeRead(sNum, username));}
	  
	  
	  //가게등록 페이지로 이동
	  @PreAuthorize("isAuthenticated()")
	  @GetMapping("/manager/store_insert") public ModelAndView storeInsert() {
	  return new  ModelAndView("main").addObject("viewName","manager/storeinsert.jsp")
	  .addObject("viewHeader", "include/viewManagerHeader.jsp"); }
	  
	  
	  //가게등록
	  @PreAuthorize("isAuthenticated()")
	  @PostMapping("/manager/store_insert") 
	  public String storeInsert(Store store, MultipartFile sajin, Principal principal)
			  	throws IllegalStateException, IOException {
	  store.setMUsername(principal.getName());
	  service.storeInsert(store, sajin); 
	  return "redirect:/manager/store_list"; 
	  }
	  
	  /*
	  // 가게 리스트 (별점 높은순)
	  @PreAuthorize("isAuthenticated()")
	  @GetMapping("/manager/list_bystar")
	  public ModelAndView listByStar(@RequestParam(defaultValue="sAddress") String searchOption, @RequestParam(defaultValue="") String keyword) {
		  
	  	List<Store> list = service.listByStar(searchOption, keyword);
	  	// 가게 갯수
	  	int count = service.countArticle(searchOption, keyword);
	  	// 모델과 뷰
	  	ModelAndView mav = new ModelAndView();
	  	/* mav.addObject("list", list); // 데이터를 저장
	  	 * mav.addObject("count", count);
	  	 * mav.addObject("searchOption", searchOption);
	  	 * mav.addObject("keyword", keyword); */
	  	
	  	// 데이터를 맵에 저장
	  /*
	  	Map<String, Object> map = new HashMap<String, Object>();
	  	map.put("list", list); 		// list
	  	map.put("count", count);	// 가게 갯수
	  	map.put("searchOption", searchOption);	// 검색옵션
	  	map.put("keyword", keyword);	// 검색키워드
	  	mav.addObject("map", map);		// 맵에 저장된 데이터를 mav에 저장
	  	mav.setViewName("/manager/list_bystar/");
	  	return mav;		// list_bystar로 List가 전달됨
	  }
*/
	  	
	  	
	  // 가게 리스트 (리뷰많은순)
	  
	 
}
