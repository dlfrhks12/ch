package com.icia.cheatingday.notice.controller.rest;

import java.security.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

import com.icia.cheatingday.notice.entity.*;
import com.icia.cheatingday.notice.service.rest.*;

@RestController
public class NoticeRestContoller {
	@Autowired
	private NoticeRestService service;
	
	@PreAuthorize("isAuthenticated()")
	@PatchMapping("/notice/update")
	public ResponseEntity<?> NoticeUpdate(Notice notice, Principal principal){
		service.updateNotice(notice, principal.getName());
		return ResponseEntity.ok("/cheatingday/notice/read?nNo="+notice.getNNo());
	}
	@PreAuthorize("isAuthenticated()")
	@DeleteMapping("/notice/delete")
	public ResponseEntity<?> Noticedelete(Integer nNo, Principal principal){
		service.deleteNotice(nNo, principal.getName());
		return ResponseEntity.ok("/cheatingday/notice/list");
	}
}
