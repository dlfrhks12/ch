package com.icia.cheatingday.manager.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.icia.cheatingday.manager.dto.ManagerDto;
import com.icia.cheatingday.manager.dto.MenuDto;
import com.icia.cheatingday.manager.service.ManagerService;
import com.icia.cheatingday.manager.service.StoreService;

@RestController
public class ManagerRestController {

	@Autowired
	private ManagerService service;
	@Autowired
	private StoreService storeService;
	
	
	//메뉴수정
	@PatchMapping("/manager/menu_update")//음식점고유번호랑 로그인한사람 같으면, 
	public ResponseEntity<?> menuUpdate(MenuDto.DtoForRead dto,MultipartFile sajin) throws IllegalStateException, IOException{
		System.out.println(dto.getSNum());
		System.out.println(dto.getSNum());
		System.out.println(dto.getSNum());
		System.out.println(dto.getSNum());
		System.out.println(dto.getSNum());
		System.out.println(dto.getSNum());
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
		
		//사업자 탈퇴
		@PreAuthorize("isAuthenticated()")
		@PostMapping("/manager/out")
		public ResponseEntity<Void> managerResign(SecurityContextLogoutHandler handler, 
				HttpServletRequest request, HttpServletResponse response, 
				Authentication authentication) {
			service.resign(authentication.getName());
			handler.logout(request, response, authentication);
			return ResponseEntity.ok(null);
		}
		
		//매장이 존재하는지 확인
		@GetMapping("/manager/exists_snum")
		public ResponseEntity<Boolean> existsSnum(String mUsername){
			return ResponseEntity.ok(storeService.existsSnum(mUsername));
		}
		
		//(매장이 존재하고)해당매장리뷰가 존재하는지 확인
		@GetMapping("/manager/exists_review")
		public ResponseEntity<Boolean> existsReview(String mUsername){
			return ResponseEntity.ok(storeService.existsreview(mUsername));
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
}
