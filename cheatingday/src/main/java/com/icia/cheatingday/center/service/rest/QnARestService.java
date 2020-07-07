package com.icia.cheatingday.center.service.rest;

import java.time.*;
import java.time.format.*;
import java.util.*;

import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

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
	
	public List<QnAComment> writeQComment(QnAComment qnAComment){
		qnAComment.setQcWriteTime(LocalDateTime.now());
		String commentStr = qnAComment.getQcContent().replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		qnAComment.setQcContent(commentStr);
		qnAComment.setAUsername(adao.findById(qnAComment.getAUsername()));
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
	
	public int updateQnAcomment(QnAComment qnAComment){
		return qndao.update(qnAComment);
	}
	
}
