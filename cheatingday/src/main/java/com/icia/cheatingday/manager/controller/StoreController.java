package com.icia.cheatingday.manager.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.cheatingday.manager.entity.Store;
import com.icia.cheatingday.manager.service.StoreService;

@Controller
public class StoreController {

	@Autowired
	private StoreService service;
	
	/*
	 * //가게리스트 페이지로 이동
	 * 
	 * @GetMapping("/manager/store_list") public ModelAndView storeList() { return
	 * new ModelAndView("main").addObject("viewName","manager/storelist.jsp")
	 * .addObject("viewHeader", "include/noheader.jsp") .addObject("storeList",
	 * service.storeList()); }
	 * 
	 * //가게읽기 페이지로 이동
	 * 
	 * @GetMapping("/manager/store_read") public ModelAndView storeRead(int sNum) {
	 * return new ModelAndView("main").addObject("viewName","manager/sotreread.jsp")
	 * .addObject("viewHeader", "include/noheader.jsp") .addObject("storeRead",
	 * service.storeRead(sNum)); }
	 * 
	 * //가게등록 페이지로 이동
	 * 
	 * @GetMapping("/manager/store_insert") public ModelAndView storeInsert() {
	 * return new
	 * ModelAndView("main").addObject("viewName","manager/storeinsert.jsp")
	 * .addObject("viewHeader", "include/noheader.jsp"); }
	 * 
	 * 
	 * //가게등록
	 * 
	 * @PostMapping("/manager/store_insert") public String storeInsert(Store store,
	 * MultipartFile sajin) throws IllegalStateException, IOException {
	 * service.storeInsert(store, sajin); return "redirect:/manager/store_list"; }
	 */
}
