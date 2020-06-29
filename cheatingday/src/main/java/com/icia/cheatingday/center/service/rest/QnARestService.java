package com.icia.cheatingday.center.service.rest;

import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.regex.*;

import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

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
	private ModelMapper mapper;
	
	public QnADto.DtoForRead read(Integer qNo){
		QnA qna = qdao.findById(qNo);
		QnADto.DtoForRead dto = mapper.map(qna,QnADto.DtoForRead.class);
		String str = qna.getQWriteTime().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
		dto.setQWriteTimeStr(str);
		dto.setMIrum(mdao.findById(dto.getMNum()).getMIrum());
		dto.setQCategory(qcdao.findById(dto.getQCano()));
		if(qna.getQIscomment()==true)
			dto.setComments(qndao.findAllByQno(dto.getQNo()));
		return dto;
	}
	public List<QnAComment> writeQComment(QnAComment qnAComment){
		qnAComment.setQcWriteTime(LocalDateTime.now());
		String commentStr = qnAComment.getQcContent().replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		qnAComment.setQcContent(commentStr);
		qndao.insert(qnAComment);
		qdao.update(QnA.builder().qNo(qnAComment.getQNo()).qIscomment(true).build());
		return qndao.findAllByQno(qnAComment.getQNo());
	}
	
	public void deletQna(Integer qNo, String username) {
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
		
		
		
	}
	
}
