package com.icia.cheatingday.review.service.rest;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icia.cheatingday.exception.*;
import com.icia.cheatingday.manager.dao.ManagerDao;
import com.icia.cheatingday.review.dao.*;
import com.icia.cheatingday.review.dto.ReviewDto;
import com.icia.cheatingday.review.entity.Review;
import com.icia.cheatingday.review.entity.ReviewComment;

@Service
public class ReviewRestService {
	@Autowired
	private ReviewDao reviewDao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ReviewCommentDao commentDao;
	@Value("http://localhost8081/ckimage/")
	private String ckUrl;
	@Value("${imageFolder}")
	private String imageFolder;
	@Value("${imagePath}")
	private String imagePath;
	@Autowired
	private ManagerDao managerDao;
	@Autowired
	private ManagerBoardDao managerboardDao;
	Pattern ckImagePattern = Pattern.compile("src=\".+\"\\s");

	public String saveCkImage(MultipartFile upload) throws IOException {
		Map<String,String> map = new HashMap<String, String>();
		if(upload!=null) {
			if(upload.getContentType().toLowerCase().startsWith("image/")) {
				String imageName = UUID.randomUUID().toString()+".jpg";
				File file = new File(imageFolder,imageName);
				FileCopyUtils.copy(upload.getBytes(), file);
				String fileUrl = imagePath+imageName;
				map.put("uploaded", "1");
				map.put("fileName", imageName);
				map.put("url", fileUrl);
				return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
		}
	}
	return null;
	}
	public int updateReview(ReviewDto.DtoForUpdate dto) {
		Review review = reviewDao.findByRno(dto.getRNo());
		review = modelMapper.map(dto, Review.class);
		return reviewDao.update(review);
	}
	public int singoReview(int rNo, String mUsername) {
		Review review = reviewDao.findByRno(rNo);
		if(managerboardDao.alreadyExist(mUsername, rNo)!=null) {
			int result = review.getRReport();
			return result;
		} 
		managerboardDao.insert(mUsername, rNo);
		reviewDao.update(Review.builder().rNo(rNo).rReport(review.getRReport()+1).build());
		return review.getRReport()+1;
	}
	public void deleteReview(Integer rNo) {
		Review review = reviewDao.findByRno(rNo);
		String content = review.getRContent();
		Matcher matcher = ckImagePattern.matcher(content);
		while(matcher.find()) {
			String src = matcher.group();
			int start =src.indexOf("ckimage/");
			int end = src.indexOf("style=");
			String fileName = src.substring(start+8, end-2);
			File file = new File(imageFolder, fileName);
			if(file.exists()==false)
				file.delete();
		}
		 reviewDao.delete(rNo);
	}
	public List<ReviewComment> writeComment(ReviewComment comment, String mUsername){
		comment.setMNum(managerDao.findById(mUsername).getMNum());
		comment.setMUsername(mUsername);
		comment.setRcDateTime(LocalDateTime.now());
		String commentContentStr = comment.getRcContent().replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		comment.setRcContent(commentContentStr);
		commentDao.insert(comment);
		return commentDao.findByRno(comment.getRNo());
	}

	public List<ReviewComment> deleteComment(Integer rcNo, Integer rNo,String mUsername){
		ReviewComment comment = commentDao.findByRcno(rcNo);
		System.out.println(mUsername);
		System.out.println(mUsername);
		System.out.println(mUsername);
		System.out.println(mUsername);
		System.out.println(mUsername);
		if(mUsername.equals(comment.getMUsername())==false)
			throw new JobFailException("댓글을 삭제 할 수 없습니다.");
		commentDao.delete(rcNo);
		return commentDao.findByRno(rNo);
	}
	
}
