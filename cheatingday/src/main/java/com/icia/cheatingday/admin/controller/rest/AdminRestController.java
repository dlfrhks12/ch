package com.icia.cheatingday.admin.controller.rest;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.annotation.*;
import org.springframework.web.bind.annotation.*;
import com.icia.cheatingday.admin.service.rest.*;

@RestController
@Secured("ROLE_ADMIN")
public class AdminRestController {
	@Autowired
	private AdminRestService service;

	@DeleteMapping("/admin/report_delete")
	public ResponseEntity<?> deleteReport(Integer rNo){
		service.deleteReport(rNo);
		return ResponseEntity.ok("/cheatingday/admin/report_list");
	}
	
	@PostMapping("/admin/manager_unblock")
	public ResponseEntity<?> enabledM(int mNum){
		service.enabledM(mNum);
		return  ResponseEntity.ok("/cheatingday/admin/manager_list");
	}

}
