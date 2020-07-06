package com.icia.cheatingday.manager.controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.cheatingday.manager.dto.ManagerDto;
import com.icia.cheatingday.manager.dto.MenuDto;
import com.icia.cheatingday.manager.entity.ManagerEntity;
import com.icia.cheatingday.manager.entity.MenuEntity;
import com.icia.cheatingday.manager.service.ManagerService;

@RestController
public class ManagerRestController {

	@Autowired
	private ManagerService service;
	
	//메뉴수정
	@PatchMapping("/manager/menu_update")//음식점고유번호랑 로그인한사람 같으면, 
	public ResponseEntity<?> menuUpdate(MenuDto.DtoForRead dto,MultipartFile sajin) throws IllegalStateException, IOException{
		return ResponseEntity.ok(service.menuUpdate(dto, sajin));
	}
	
	//메뉴삭제
	@DeleteMapping("/manager/menu_delete")
	public ResponseEntity<?> menuDelete(int menuno){
		return ResponseEntity.ok(service.menuDelete(menuno));
	}
	
	//내정보 수정
	@PatchMapping("/manager/information_update")
	public ResponseEntity<Void> update(ManagerDto.DtoForUpdate dto, Principal principal){
		dto.setMUsername(principal.getName());
		service.update(dto);
		return ResponseEntity.ok(null);
	}
	
}
