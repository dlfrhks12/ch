package com.icia.cheatingday.user.controller.rest;

import java.io.*;
import java.security.*;

import javax.servlet.http.*;
import javax.validation.*;
import javax.validation.constraints.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.logout.*;
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
		
	}
	// 이메일 중복체크
	@GetMapping("/user/check_email")
	public ResponseEntity<Boolean> checkEmail(@RequestParam @NotNull String uEmail) {
		return ResponseEntity.ok(service.checkEmail(uEmail));
		
	}
	// 업데이트
	@PreAuthorize("isAuthenticated()")
	@PutMapping("/user/update")
	public ResponseEntity<Void> update(@Valid UserDto.DtoForUpdate dto, BindingResult results, Principal principal) throws BindException{
		if(results.hasErrors())
			throw new BindException(results);
		dto.setUUsername(principal.getName());
		service.update(dto);
		return ResponseEntity.ok(null);
	}
	// 회원탈퇴
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/user/resign")
	public ResponseEntity<Void> resign(SecurityContextLogoutHandler handler, HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		service.resign(authentication.getName());
		handler.logout(request, response, authentication);
		return ResponseEntity.ok(null);
	}
	// 즐겨찾기
	@PreAuthorize("isAuthenticated()")
	@PutMapping("/user/favorite")
	public ResponseEntity<Integer> fav(int SNum, Principal principal) {
		return ResponseEntity.ok(service.fav(SNum,principal.getName()));
	}

}
