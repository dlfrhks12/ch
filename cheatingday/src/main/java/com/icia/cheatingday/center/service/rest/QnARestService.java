package com.icia.cheatingday.center.service.rest;

import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import com.fasterxml.jackson.databind.*;
import com.icia.cheatingday.admin.dao.*;
import com.icia.cheatingday.center.dao.*;
import com.icia.cheatingday.center.dto.*;
import com.icia.cheatingday.center.entity.*;
import com.icia.cheatingday.manager.dao.*;

@Service
public class QnARestService {
	@Autowired
	private QnACommentDao qndao;
	@Autowired
	private QnADao qdao;
	@Autowired
	private QnACategoryDao qcdao;
	@Autowired
	private ManagerDao mdao;
	@Autowired
	private AdminDao adao;
	@Autowired
	private ModelMapper mapper;
	@Value("http://localhost:8081/ckimage/")
	private String ckUrl;
	@Autowired
	private ObjectMapper objectmapper;
	
	public QnADto.DtoForRead read(Integer qNo, String username){
		QnA qna = qdao.findById(qNo);
		QnADto.DtoForRead dto = mapper.map(qna,QnADto.DtoForRead.class);
		String str = qna.getQWriteTime().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
		dto.setMUsername(mdao.findMusernameByMnum(dto.getMNum()));
		dto.setQWriteTimeStr(str);
		dto.setMIrum(mdao.findMirumeByMnum(dto.getMNum()));
		dto.setQCategory(qcdao.findById(dto.getQCano()));
		dto.setComments(qndao.findAllByQno(dto.getQNo()));
		return dto;
	}
	
	public List<QnAComment> writeQComment(QnAComment qnAComment, String ausername){
		qnAComment.setQcWriteTime(LocalDateTime.now());
		String comment = qnAComment.getQcContent().replaceAll("\n", " ");
		qnAComment.setQcContent(comment);
		qndao.insert(qnAComment);
		qdao.update(QnA.builder().qNo(qnAComment.getQNo()).qIscomment(true).build());
		return qndao.findAllByQno(qnAComment.getQNo());
	}
	public List<QnAComment> deleteComment(Integer qNo, Integer qcNo, String username) {
		QnAComment qnAComment = qndao.findById(qcNo);
		qndao.delete(qcNo);
		qdao.update(QnA.builder().qNo(qnAComment.getQNo()).qIscomment(false).build());
		return qndao.findAllByQno(qNo);
	}
	
	public void deletQna(Integer qNo, String username) {
		QnA qnA = qdao.findById(qNo);
		/*String content = qnA.getQContent();
		Matcher matcher = ckImagePattern.matcher(content);
		while(matcher.find()) {
			String src = matcher.group();
			int start = src.indexOf("ckimage/");
			int end = src.indexOf("style=");
			String fileName = src.substring(start+8, end-2);
			File file = new File(imageFolder, fileName);
			if(file.exists()==true)
				file.delete();
		}*/
		qdao.delete(qNo);
	}
	public void updateQnA(QnADto.DtoForUpdate dto) {
		QnA qna = qdao.findById(dto.getQNo());
		qna = mapper.map(dto, QnA.class);
		qdao.update(qna);
	}
	
	public void updateQnAcomment(QnAComment qnAComment){
		 qndao.update(qnAComment);
	}
	public String saveCkImage(MultipartFile upload) {
	      Map<String, String> map = new HashMap<String, String>();
	      if (upload != null) {
	    	  System.out.println(upload);
	    	  System.out.println(upload);
	    	  System.out.println(upload);
	         if (upload.getContentType().toLowerCase().startsWith("image/")) {
	            String imageName = UUID.randomUUID().toString() + ".jpg";
	            System.out.println(imageName);
	            System.out.println(imageName);
	            System.out.println(imageName);
	            try {
	               File file = new File("d:/upload/ckimage", imageName);
	               // FileCopyUtils.copy(upload.getBytes(), file);
	               upload.transferTo(file);
	               map.put("uploaded", "1");
	               map.put("fileName", imageName);
	               map.put("url", ckUrl + imageName);
	               return objectmapper
	                     .writerWithDefaultPrettyPrinter() // 줄바꿈
	                     .writeValueAsString(map);
	            } catch (Exception e) {
	               e.printStackTrace();
	            }
	         }
	      }
	      return null;
	   }
	
}
