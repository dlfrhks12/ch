package com.icia.cheatingday.manager.controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.icia.cheatingday.manager.entity.MenuEntity;
import com.icia.cheatingday.manager.service.ManagerService;

@RestController
public class ManagerRestController {

	@Autowired
	private ManagerService service;
	
	//메뉴수정
	@PatchMapping("/manager/menu_read")
	public ResponseEntity<?> menuUpdate(MenuEntity menu,MultipartFile sajin) throws IllegalStateException, IOException{
		service.menuUpdate(menu,sajin);
		return ResponseEntity.ok(null);
	}
	
	//메뉴삭제
	@DeleteMapping("/manager/menu_read")
	public ResponseEntity<?> menuDelete(int menuno){
		return ResponseEntity.ok(service.menuDelete(menuno));
	}
}
