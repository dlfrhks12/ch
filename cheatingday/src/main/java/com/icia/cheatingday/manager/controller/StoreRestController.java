package com.icia.cheatingday.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.icia.cheatingday.manager.entity.Store;
import com.icia.cheatingday.manager.service.StoreService;

@RestController
public class StoreRestController {

	/*
	 * @Autowired private StoreService service;
	 * 
	 * //가게수정
	 * 
	 * @PatchMapping("/manager/store_update") public ResponseEntity<?>
	 * storeUpdate(Store store, MultipartFile sajin){ return
	 * ResponseEntity.ok(service.storeInsert(store, sajin)); }
	 * 
	 * //가게삭제
	 * 
	 * @DeleteMapping("/manager/store_delete") public ResponseEntity<?>
	 * storeDelete(int sNum){ return ResponseEntity.ok(service.storeDelete(sNum)); }
	 */
}
