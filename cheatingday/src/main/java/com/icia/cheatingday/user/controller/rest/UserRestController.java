package com.icia.cheatingday.user.controller.rest;

import java.net.BindException;
import java.security.*;

import javax.validation.*;
import javax.validation.constraints.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.validation.*;
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
	// 이메일 중복체크
	}
	@GetMapping("/user/check_email")
	public ResponseEntity<Boolean> checkEmail(@RequestParam @NotNull String uEmail) {
		return ResponseEntity.ok(service.checkEmail(uEmail));
	// 업데이트
	}
	@PreAuthorize("isAuthenticated()")
	@PutMapping("/user/update")
	public ResponseEntity<Void> update(@Valid UserDto.DtoForUpdate dto, BindingResult results, Principal principal) throws BindException {
		if(results.hasErrors())
			throw new BindException();
		dto.setUUsername(principal.getName());
			service.update(dto);
		return ResponseEntity.ok(null);
	}
	// 날짜 불러오기
	@GetMapping(path = "/user/join_date", produces="text/plain;charset=urf-8")
	public ResponseEntity<String> findJoinDate(String uUsername) {
		return ResponseEntity.ok(service.findJoinDate(uUsername));
	}
}
