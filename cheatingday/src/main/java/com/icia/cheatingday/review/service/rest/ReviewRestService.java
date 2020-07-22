package com.icia.cheatingday.review.service.rest;

import java.io.File;
import java.io.IOException;
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
import com.icia.cheatingday.manager.dao.ManagerDao;
import com.icia.cheatingday.review.dao.ReviewCommentDao;
import com.icia.cheatingday.review.dao.ReviewDao;
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
	private ManagerDao managerDao;
	@Autowired
	private ReviewCommentDao commentDao;
	@Value("http://localhost8081/ckimage/")
	private String ckUrl;
	@Value("${imageFolder}")
	private String imageFolder;
	@Value("${imagePath}")
	private String imagePath;
	
	Pattern ckImagePattern = Pattern.compile("src=\".+\"\\s");
	
	public List<ReviewComment> writeComment(ReviewComment reviewComment, String mUsername){
		reviewComment.setRcDateTime(LocalDateTime.now());
		System.out.println("코멘트트티ㅏ엄ㄹ미ㅏ넝;리ㅏ머;ㅣㅏㄴ어;ㅣ론ㅇ;ㅣㅏ허;ㅣㅏ허;ㅁ호이ㅏㄶ어ㅣㅏ");
		System.out.println(reviewComment);
		String commentContentStr = reviewComment.getRcContent().replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		System.out.println(commentContentStr);
		reviewComment.setRcContent(commentContentStr);
		reviewComment.setMNum(managerDao.findById(mUsername).getMNum());
		System.out.println(reviewComment);
		commentDao.insert(reviewComment);
		System.out.println(reviewComment);
		
		return commentDao.findAllByRno(reviewComment.getRNo());
	}
	public int updateComment(ReviewComment reviewComment) {
		return commentDao.update(reviewComment);
	}
	public List<ReviewComment> deleteComment(Integer rcNo,Integer rNo,String uUsername){
		ReviewComment reviewComment = commentDao.findById(rcNo);
		commentDao.delete(rcNo);
		return commentDao.findAllByRno(rNo);
	}
	public void deleteReview(Integer rNo, String uUsername) {
		Review review = reviewDao.findByRno(rNo);
		String rContent = review.getRContent();
		Matcher matcher = ckImagePattern.matcher(rContent);
		while(matcher.find()) {
			String src = matcher.group();
			int start = src.indexOf("reviewckimage/");
			int end = src.indexOf("style=");
			String fileName = src.substring(start+8,end-2);
			File file = new File(imageFolder,fileName);
			if(file.exists()==true)
				file.delete();
		}
		reviewDao.delete(rNo);
	}
	public String saveCkImage(MultipartFile upload	) throws IOException {
		Map<String,String >map = new HashMap<String, String>();
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
}
