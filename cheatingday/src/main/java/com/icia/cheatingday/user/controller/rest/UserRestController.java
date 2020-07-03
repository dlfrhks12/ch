package com.icia.cheatingday.user.controller.rest;

import javax.validation.*;
import javax.validation.constraints.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.icia.cheatingday.user.dto.*;
import com.icia.cheatingday.user.service.rest.*;

@RestController
public class UserRestController {
	@Autowired
	private UserRestService service;

	// 아이디 중복체크
	@GetMapping("/user/check_id")
	public ResponseEntity<Boolean> checkId(@RequestParam @NotNull String uUsername) {
		return ResponseEntity.ok(service.checkId(uUsername));
		
	}
	// 이메일 중복체크
	@GetMapping("/user/check_email")
	public ResponseEntity<Boolean> checkEmail(@RequestParam @NotNull String uEmail) {
		return ResponseEntity.ok(service.checkEmail(uEmail));
		
	}
	// 업데이트
	@PutMapping("/user/update")
	public ResponseEntity<Void> update(@Valid UserDto.DtoForUpdate dto){
		dto.setUUsername("spring");
		service.update(dto);
		return ResponseEntity.ok(null);
	}
	// 회원 탈퇴
	@GetMapping("/user/resign")
	public ResponseEntity<?> delete(String uUsername, String uPassword) {
		return ResponseEntity.ok(service.resign(uUsername, uPassword));
	}

/*
	// 날짜 불러오기
	@GetMapping(path = "/user/join_date", produces = "text/plain;charset=urf-8")
	public ResponseEntity<String> findJoinDate(String uUsername) {
		return ResponseEntity.ok(service.findJoinDate(uUsername));
	}
	*/
}
