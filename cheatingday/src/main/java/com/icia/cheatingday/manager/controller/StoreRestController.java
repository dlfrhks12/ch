package com.icia.cheatingday.manager.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.icia.cheatingday.manager.dto.*;
import com.icia.cheatingday.manager.entity.Store;
import com.icia.cheatingday.manager.service.ManagerService;
import com.icia.cheatingday.manager.service.StoreService;

@RestController
public class StoreRestController {
	  @Autowired 
	  private StoreService service;
	
	  
	  //가게수정
	  @PreAuthorize("isAuthenticated()")
	  @PatchMapping("/manager/store_update") 

	  public ResponseEntity<?> storeUpdate(StoreDto.DtoForUpdate dto, MultipartFile sajin) throws IllegalStateException, IOException{
		 return ResponseEntity.ok(service.storeUpdate(dto, sajin));
	  }
	  
	  //가게삭제
	  @PreAuthorize("isAuthenticated()")
	  @DeleteMapping("/manager/store_delete") 
	  	public ResponseEntity<?>storeDelete(int sNum){ 
		  return ResponseEntity.ok(service.storeDelete(sNum)); 
		 }
	  
		
	 
}
