package com.icia.cheatingday.review.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.cheatingday.common.dto.Page;
import com.icia.cheatingday.freeboard.dao.CommentDao;
import com.icia.cheatingday.manager.dao.StoreDao;
import com.icia.cheatingday.order.dao.OrderDao;
import com.icia.cheatingday.order.entity.OrderEntity;
import com.icia.cheatingday.review.dao.ReviewCommentDao;
import com.icia.cheatingday.review.dao.ReviewDao;
import com.icia.cheatingday.review.dto.ReviewDto;
import com.icia.cheatingday.review.entity.Review;
import com.icia.cheatingday.review.entity.ReviewComment;
import com.icia.cheatingday.util.PagingUtil;

import lombok.Getter;

@Service
public class ReviewService {

	@Autowired
	private ReviewDao dao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ReviewCommentDao commentDao;
	@Autowired
	private OrderDao orderDao;
	
	public Page listByStar(int pageno, Integer rStarPoint, Integer rNo) {
		int countOfReview = dao.count(rNo);
		Page page = PagingUtil.getPage(pageno, countOfReview);
		int srn = page.getStartRowNum();
		int ern = page.getEndRowNum();
		List<Review> reviewList = null;
		if(rStarPoint!=null)
			reviewList = dao.findAll(srn, ern);
		else
			reviewList = dao.findAllByStar(srn, ern, rStarPoint);
		List<ReviewDto.DtoForList> dtoList = new ArrayList<ReviewDto.DtoForList>();
		for(Review review:reviewList) {
			ReviewDto.DtoForList dto = modelMapper.map(review, ReviewDto.DtoForList.class);
			dto.setRWriteTimeStr(review.getRWriteTime().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
			dtoList.add(dto);
			
		}
		return page;
	}
	public Page listByRno(Integer rNo, int pageno ) {
		int countOfReview = dao.count(rNo);
		Page page = PagingUtil.getPage(pageno, countOfReview);
		int srn = page.getStartRowNum();
		int ern = page.getEndRowNum();
		List<Review> reviewList = null;
		if(rNo!=null)
			reviewList = dao.findAllByRno(srn, ern, rNo);
		else
			reviewList = dao.findAll(srn, ern);
		List<ReviewDto.DtoForList> dtoList = new ArrayList<ReviewDto.DtoForList>();
		for(Review review : reviewList) {
			ReviewDto.DtoForList dto = modelMapper.map(review, ReviewDto.DtoForList.class);
			dto.setRWriteTimeStr(review.getRWriteTime().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
			dtoList.add(dto);
		}
		page.setReviewlist(dtoList);
		return page;
	}
	
	
	public int write(ReviewDto.DtoForWrite dto) {
		Review review = modelMapper.map(dto, Review.class);
		dao.insert(review);
		System.out.println("falksjd;lkjaf;lkjsdl;kjfsdl");
		System.out.println(review);
		return review.getRNo();
	}
	public ReviewDto.DtoForRead read(Integer rNo, String uUsername){
		Review review = dao.findByRno(rNo);
		ReviewDto.DtoForRead dto = modelMapper.map(review,ReviewDto.DtoForRead.class);
		String str = review.getRWriteTime().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
		dto.setRWriteTimeStr(str);
		dto.setComments(commentDao.findAllByRno(dto.getRNo()));
		return dto;
	}
}
