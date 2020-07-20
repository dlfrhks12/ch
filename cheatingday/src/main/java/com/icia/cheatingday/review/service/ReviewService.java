package com.icia.cheatingday.review.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.cheatingday.common.dto.Page;
import com.icia.cheatingday.review.dao.ReviewDao;
import com.icia.cheatingday.review.dto.ReviewDto;
import com.icia.cheatingday.review.entity.Review;
import com.icia.cheatingday.util.PagingUtil;

@Service
public class ReviewService {

	@Autowired
	private ReviewDao dao;
	@Autowired
	private ModelMapper modelMapper;
	
	public Page list(int pageno, String uUsername) {
		int countOfReview = dao.count(uUsername);
		Page page = PagingUtil.getPage(pageno, countOfReview);
		int srn = page.getStartRowNum();
		int ern = page.getEndRowNum();
		List<Review> list = null;
		dao.findAll(srn, ern);
		List<ReviewDto.DtoForList> dtoList = new ArrayList<ReviewDto.DtoForList>();
		for(Review review:list) {
			ReviewDto.DtoForList dto = modelMapper.map(review,ReviewDto.DtoForList.class);
			dto.setRWriteTimeStr(review.getRWriteTime().format(DateTimeFormatter.ofPattern("yyyy년MM월 dd일")));
			dtoList.add(dto);
		}
		page.setReviewlist(dtoList);
		return page;
	}
	
	public int write(ReviewDto.DtoForWrite dto) {
		Review review = modelMapper.map(dto, Review.class);
		dao.insert(review);
		return review.getRNo();
	}
	public ReviewDto.DtoForRead read(Integer rNo, String uUsername){
		Review review = dao.findByRno(rNo);
		ReviewDto.DtoForRead dto = modelMapper.map(review,ReviewDto.DtoForRead.class);
		if(uUsername!=null&&uUsername.equals(dto.getUUsername())==false)
			dao.update(Review.builder().rNo(rNo).build());
		String str = review.getRWriteTime().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
		dto.setRWriteTimeStr(str);
		return dto;
	}
}
