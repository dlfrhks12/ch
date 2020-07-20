package com.icia.cheatingday.manager.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icia.cheatingday.manager.service.MReviewOrderService;

@Controller
public class MReviewOrderController {

	@Autowired
	private MReviewOrderService service;
	
	///////////////////////////////////////주문//////////////////////////////////////
	
	  // 해당매장 주문 리스트 - 페이징
	  @GetMapping("manager/order_list") 
	  public ModelAndView orderList(@RequestParam(defaultValue = "1") int pageno, int sNum) { 
		  return new ModelAndView("main").addObject("viewName","manager/checkorderList.jsp")
				  .addObject("viewHeader", "include/viewManagerHeader.jsp")
				  .addObject("page",service.orderList(pageno, sNum)); 
	}
	 
	
	
	// 해당 주문번호 내용 읽기 
	@GetMapping("manager/order_read")
	public ModelAndView orderRead(int oNo) {
		return new ModelAndView("main").addObject("viewName","manager/checkorderRead.jsp")
				  .addObject("viewHeader", "include/viewManagerHeader.jsp")
				  .addObject("orderRead",service.orderRead(oNo));
				
	}
	
	/////////////////////////////////////////////////리뷰//////////////////////////////////////////////////////////
	  //리뷰신고
	 @PatchMapping("manager/reviewRepoart")
	 public ResponseEntity<?> managerReviewReport(int rNo, Principal principal){
		 return ResponseEntity.ok(service.reviewSingoUpdate(rNo, principal.getName()));
	 }
	
	
	  //매장리뷰 목록 페이징
	  @GetMapping("/manager/review_list") 
	  public ModelAndView reviewList(@RequestParam(defaultValue = "1") int pageno, String mUsername) { 
		  return new ModelAndView("main").addObject("viewName","manager/reviewlist.jsp")
				  .addObject("viewHeader", "include/viewManagerHeader.jsp")
				  .addObject("page", service.list(pageno, mUsername));
	}
	  
	 //매장리뷰 읽기
	 @GetMapping("/manager/review_read")
	 public ModelAndView reviewRead(int rNo) {
		 return new ModelAndView("main").addObject("viewName","manager/reviewread.jsp")
				 .addObject("viewHeader","include/viewManagerHeader.jsp")
				 .addObject("reviewRead", service.read(rNo));
	 }
	 
}
