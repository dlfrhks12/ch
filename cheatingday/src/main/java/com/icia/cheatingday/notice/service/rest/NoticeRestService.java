package com.icia.cheatingday.notice.service.rest;

import java.io.*;
import java.util.*;
import java.util.regex.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;
import org.springframework.web.multipart.*;

import com.fasterxml.jackson.databind.*;
import com.icia.cheatingday.exception.*;
import com.icia.cheatingday.notice.dao.*;
import com.icia.cheatingday.notice.entity.*;

@Service
public class NoticeRestService {
	@Autowired
	NoticeDao dao;
	@Value("${imageFolder}")
	private String imageFolder;
	@Value("${imagePath}")
	private String imagePath;
	@Autowired
	private ObjectMapper objectmapper;
	Pattern ckImagePattern = Pattern.compile("src=\".+\"\\s");
	
	//[관리자]  공지삭제
	public void deleteNotice(Integer nNo, String aUsername) {
		Notice notice = dao.findById(nNo);
		if(notice==null)
			throw new JobFailException("공지를 찾을 수 없습니다");
		if(notice.getAUsername().equals(aUsername)==false)
			throw new UserNotFoundException();
		String content = notice.getContent();
		Matcher matcher = ckImagePattern.matcher(content);
		while(matcher.find()) {
			String src = matcher.group();
			int start = src.indexOf("ckimage/");
			int end = src.indexOf("style=");
			String fileName = src.substring(start+8, end-2);
			File file = new File(imageFolder, fileName);
			if(file.exists()==true)
				file.delete();
		}
		dao.delete(nNo);
	}
	//[관리자] 공지변경
	public void updateNotice(Notice notice, String aUsername) {
		if(notice.getNNo()==null)
			throw new JobFailException("공지를 찾을 수 없습니다");
		if(dao.findById(notice.getNNo()).getAUsername().equals(aUsername)==false)
			throw new UserNotFoundException();
		dao.update(notice);
	}
	//ck업로더
	public String saveCkImage(MultipartFile upload) throws IOException {
		Map<String,String> map = new HashMap<String, String>();
		if(upload!=null) {
			if(upload.getContentType().toLowerCase().startsWith("image/")){
				String imageName = UUID.randomUUID().toString() + ".jpg";
				File file = new File(imageFolder, imageName);
				FileCopyUtils.copy(upload.getBytes(), file);
				String fileUrl = imagePath + imageName;
				// json 데이터로 등록
				// {"uploaded" : 1, "fileName" : "test.jpg", "url" : "/img/test.jpg"}
				// 이런 형태로 리턴이 나가야함.
				map.put("uploaded", "1");
				map.put("fileName", imageName);
				map.put("url", fileUrl);
                return objectmapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
			}
		}
		return null;
	}
}
