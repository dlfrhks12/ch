package com.icia.cheatingday.notice.controller.rest;

import java.io.*;
import java.security.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

import com.icia.cheatingday.notice.entity.*;
import com.icia.cheatingday.notice.service.rest.*;

@RestController
public class NoticeRestContoller {
	@Autowired
	private NoticeRestService service;
	
	//[관리자]공지변경
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PatchMapping("/notice/update")
	public ResponseEntity<?> NoticeUpdate(Notice notice, Principal principal){
		service.updateNotice(notice, principal.getName());
		return ResponseEntity.ok("/cheatingday/notice/read?nNo="+notice.getNNo());
	}
	//[관리자]공지삭제
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/notice/delete")
	public ResponseEntity<?> Noticedelete(Integer nNo, Principal principal){
		service.deleteNotice(nNo, principal.getName());
		return ResponseEntity.ok("/cheatingday/notice/list");
	}
	//CK에디터
	@PostMapping("/notice/ckupload")
	public ResponseEntity<?> ckUpload(MultipartFile upload) throws IOException{
		return ResponseEntity.ok(service.saveCkImage(upload));	
	}
}
