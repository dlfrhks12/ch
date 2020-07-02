package com.icia.cheatingday.admin.controller.rest;

import java.security.*;
import java.util.*;

import javax.validation.constraints.*;

import org.apache.commons.lang3.math.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.icia.cheatingday.admin.service.rest.*;

@RestController
public class AdminRestController {
	@Autowired
	private AdminRestService service;
	
	/*
	@DeleteMapping("/admin/report_delete")
	public ResponseEntity<?> deleteReport(Integer rNo){
		service.deleteReport(rNo);
		return ResponseEntity.ok("/cheatingday/admin/report_list");
	}
	@PostMapping("/admin/user_block")
	public String userBlock(@RequestParam @NotNull String uUsernames) {
		// "11,22,33,"을 받아서 split() 함수로 정수 변환
		List<String> list = new ArrayList<>();
		String[] strings = uUsernames.split(",");
		for(String str:strings) {
			list.add(str);
		}
		service.block(list);
		return "redirect:/admin/list?job=bad_list";
	}
	*/
}
