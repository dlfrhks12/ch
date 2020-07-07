package com.icia.cheatingday.notice.service.rest;

import java.io.*;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import com.fasterxml.jackson.databind.*;
import com.icia.cheatingday.notice.dao.*;
import com.icia.cheatingday.notice.entity.*;

@Service
public class NoticeRestService {
	@Autowired
	NoticeDao dao;
	@Value("http://localhost:8081/ckimage/")
	private String ckUrl;
	@Autowired
	private ObjectMapper objectmapper;
	
	public void deleteNotice(Integer nNo, String aUsername) {
		Notice notice = dao.findById(nNo);
		if(notice.getAUsername().equals(aUsername)==true)		
		dao.delete(nNo);
	}

	public void updateNotice(Notice notice, String aUsername) {
		if(dao.findById(notice.getNNo()).getAUsername().equals(aUsername)==true)
		dao.update(notice);
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
