/*
 * package com.icia.cheatingday.freeboard.controller;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.lang.Nullable; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.servlet.ModelAndView;
 * 
 * import com.icia.cheatingday.freeboard.service.FreeBoardService;
 * 
 * @Controller public class FreeBoardController {
 * 
 * @Autowired private FreeBoardService service;
 * 
 * @RequestMapping(value = "/") public ModelAndView list
 * (@RequestParam(defaultValue="1") int pageno,@Nullable String username) {
 * return new ModelAndView("main").addObject("viewName",
 * "/board/list.jsp").addObject("page", service.list(pageno, username)); } //aa
 * }
 */