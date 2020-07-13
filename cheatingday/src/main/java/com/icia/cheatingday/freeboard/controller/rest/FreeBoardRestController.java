package com.icia.cheatingday.freeboard.controller.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.icia.cheatingday.freeboard.dto.FreeBoardDto;
import com.icia.cheatingday.freeboard.entity.Attachment;
import com.icia.cheatingday.freeboard.entity.Comment;
import com.icia.cheatingday.freeboard.service.FreeBoardService;
import com.icia.cheatingday.freeboard.service.rest.FreeBoardRestService;

@RestController
public class FreeBoardRestController {
	@Autowired
	private FreeBoardService service;
	@Autowired
	private FreeBoardRestService restService;
	@PostMapping("/board/read")
	public ResponseEntity<?> read(@RequestParam @NotNull int bno, Principal principal)throws JsonProcessingException{
		String username = principal!=null? principal.getName():null;
		System.out.println("------------------------------------");
		System.out.println(username);
		System.out.println(bno);
		FreeBoardDto.DtoForRead dto = service.read(bno, username);
		System.out.println("------------------------------------");
		System.out.println(username);
		System.out.println(bno);

		return ResponseEntity.ok(dto);
	}
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/comment/write")
	public ResponseEntity<?> writeComment(@Valid Comment comment, BindingResult bindingResult, Principal principal){
		comment.setWriter(principal.getName());
		return ResponseEntity.ok(restService.writeComment(comment));
	}
	@PatchMapping("/comment/update")
	public ResponseEntity<?>updateComment(@Valid Comment comment){
		return ResponseEntity.ok(restService.updateComment(comment));
	}
	@DeleteMapping("/comment/delete")
	public ResponseEntity<?>deleteComment(int cno, int bno, String username){
		return ResponseEntity.ok(restService.deleteComment(cno, bno, username));
	}
	@PatchMapping("/board/update")
	public ResponseEntity<Void> updateBoard(@Valid FreeBoardDto.DtoForUpdate dto, BindingResult bindingResult, Principal principal) throws BindException{
		if(bindingResult.hasErrors())
			throw new BindException(bindingResult);
		dto.setUsername(principal.getName());
		restService.updateBoard(dto);
		return ResponseEntity.ok(null);
	}
	@DeleteMapping("/board/delete")
	public ResponseEntity<?> deleteBoard(int bno, Principal principal){
		restService.deleteBoard(bno, principal.getName());
		return ResponseEntity.ok("/freeboard/board/list");
	}
	@PostMapping("/board/ckupload")
	public ResponseEntity<?> ckUpload(MultipartFile upload) throws IOException{
		return ResponseEntity.ok(restService.saveCkImage(upload));
	}
	@GetMapping("/attachment/view")
	public ResponseEntity<?> view(Integer fno) throws IOException{
		//header에 content-disposition inline주면 보게 하고, attachment 주면 다운 받게 하기
		Attachment a = restService.readAttachment(fno);
		String originalFileName = a.getOriginalFileName();
		File saveFile = new File("d:/upload/attachment",a.getSaveFileName());
		HttpHeaders headers = new HttpHeaders();
		//이미지가 맞으면
		if(a.getIsImage()==true) {
			String ext = originalFileName.substring(originalFileName.lastIndexOf(".")+1).toUpperCase();
			if(ext.equals("JPG")|| ext.equals("JPEG"))
				headers.setContentType(MediaType.IMAGE_JPEG);
			else if(ext.equals("PNG"))
				headers.setContentType(MediaType.IMAGE_PNG);
			else if(ext.equals("GIF"))
				headers.setContentType(MediaType.IMAGE_GIF);
			headers.add("Content-Disposition", "inline;filename="+originalFileName);
		}else {
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.add("Content-Disposition", "attachment;filename="+originalFileName);
		}
		InputStream in = new FileInputStream(saveFile);
		byte[] attachmentFile = IOUtils.toByteArray(in);
		in.close();
		return ResponseEntity.ok().headers(headers).body(attachmentFile);
		
	}
	@DeleteMapping("/attachment/delete")
	public ResponseEntity<?> deleteAttachment(int fno, int bno,Principal principal){
		return ResponseEntity.ok(restService.deleteAttachment(fno, bno, principal.getName()));
		
	}
	@PreAuthorize("isAuthenticated()")
	@PatchMapping("/board/good_or_bad")
	public ResponseEntity<?> good_or_bad(int bno, boolean isGood, Principal principal){
		return ResponseEntity.ok(restService.goodOrBad(bno, isGood, principal.getName()));
	}
}
