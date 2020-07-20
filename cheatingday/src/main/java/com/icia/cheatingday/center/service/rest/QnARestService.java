package com.icia.cheatingday.center.service.rest;

import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.regex.*;

import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;
import org.springframework.web.multipart.*;

import com.fasterxml.jackson.databind.*;
import com.icia.cheatingday.center.dao.*;
import com.icia.cheatingday.center.dto.*;
import com.icia.cheatingday.center.entity.*;
import com.icia.cheatingday.exception.*;
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
	@Value("${imageFolder}")
	private String imageFolder;
	@Value("${imagePath}")
	private String imagePath;
	@Autowired
	private ObjectMapper objectmapper;
	
	Pattern ckImagePattern = Pattern.compile("src=\".+\"\\s");
	
	//[관리자,사업자]QNA읽기
	public QnADto.DtoForRead read(Integer qNo, String username){
		QnA qna = qdao.findById(qNo);
		if(qna==null)
			throw new JobFailException("해당 문의를 찾을수 없습니다");
		QnADto.DtoForRead dto = mapper.map(qna,QnADto.DtoForRead.class);
		String str = qna.getQWriteTime().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
		dto.setMUsername(mdao.findMusernameByMnum(dto.getMNum()));
		dto.setQWriteTimeStr(str);
		dto.setMIrum(mdao.findMirumeByMnum(dto.getMNum()));
		dto.setQCategory(qcdao.findById(dto.getQCano()));
		dto.setComments(qndao.findAllByQno(dto.getQNo()));
		return dto;
	}
	//[관리자,사업자]댓글리스트
	public List<QnAComment> writeQComment(QnAComment qnAComment, String ausername){
		qnAComment.setQcWriteTime(LocalDateTime.now());
		String comment = qnAComment.getQcContent().replaceAll("\n", " ");
		qnAComment.setQcContent(comment);
		qndao.insert(qnAComment);
		qdao.update(QnA.builder().qNo(qnAComment.getQNo()).qIscomment(true).build());
		return qndao.findAllByQno(qnAComment.getQNo());
	}
	//[관리자]댓글삭제
	public List<QnAComment> deleteComment(Integer qNo, Integer qcNo, String username) {
		QnAComment qnAComment = qndao.findById(qcNo);
		if(qnAComment.getAUsername().equals(username)==false)
			throw new JobFailException("댓글을 찾을 수 없습니다");
		qndao.delete(qcNo);
		qdao.update(QnA.builder().qNo(qnAComment.getQNo()).qIscomment(false).build());
		return qndao.findAllByQno(qNo);
	}
	//[사업자]QNA삭제
	public void deletQna(Integer qNo, String username) {
		QnA qnA = qdao.findById(qNo);
		if(qnA==null)
			throw new JobFailException("헤당 문의를 찾을 수 없습니다");	
		if(mdao.findMusernameByMnum(qnA.getMNum()).equals(username)==false)
			throw new UserNotFoundException();
		String content = qnA.getQContent();
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
		qdao.delete(qNo);
	}
	//[사업자]QNA변경
	public void updateQnA(QnADto.DtoForUpdate dto) {
		QnA qna = qdao.findById(dto.getQNo());
		if(qna==null)
			throw new JobFailException("문의를 찾을 수 없습니다");
		dto.setMUsername(mdao.findMirumeByMnum(qna.getMNum()));
		if(mdao.findMirumeByMnum(qna.getMNum()).equals(dto.getMUsername())==false)
			throw new UserNotFoundException();
		qna = mapper.map(dto, QnA.class);
		qdao.update(qna);
	}
	//[관리자]댓글변경
	public void updateQnAcomment(QnAComment qnAComment){
		 qndao.update(qnAComment);
	}
	//CK이미지저장
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
