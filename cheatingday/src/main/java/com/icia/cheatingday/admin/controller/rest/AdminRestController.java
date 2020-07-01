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
	@PostMapping("/system/board/block")
	public String blockList(@RequestParam @NotNull String bnos) {
		// "11,22,33,"을 받아서 split() 함수로 정수 변환
		List<Integer> list = new ArrayList<>();
		String[] strings = bnos.split(",");
		for(String str:strings) {
			list.add(NumberUtils.toInt(str));
		}
		service.block(list);
		return "redirect:/system/board/list?job=bad_list";
	}
	*/
}
