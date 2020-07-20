package com.icia.cheatingday.admin.controller.rest;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.annotation.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;
import com.icia.cheatingday.admin.service.rest.*;

@RestController
@Secured("ROLE_ADMIN")
public class AdminRestController {
	@Autowired
	private AdminRestService service;
	
	//[관리자]신고받은 리뷰삭제
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/admin/report_delete")
	public ResponseEntity<?> deleteReport(Integer rNo){
		service.deleteReport(rNo);
		return ResponseEntity.ok("/cheatingday/admin/report_list");
	}
	//[관리자]가입신청 승인
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/admin/manager_unblock")
	public ResponseEntity<?> enabledM(long mNum){
		service.enabledM(mNum);
		return  ResponseEntity.ok(null);
	}

}
