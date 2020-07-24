package com.icia.cheatingday.review.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.cheatingday.common.dto.Page;
import com.icia.cheatingday.manager.dao.StoreDao;
import com.icia.cheatingday.order.dao.OrderDao;
import com.icia.cheatingday.review.dao.ReviewCommentDao;
import com.icia.cheatingday.review.dao.ReviewDao;
import com.icia.cheatingday.review.dto.ReviewDto;
import com.icia.cheatingday.review.entity.Review;
import com.icia.cheatingday.util.PagingUtil;

@Service
public class ReviewService {
	@Autowired 
	private ReviewDao reviewDao;
	@Autowired 
	private OrderDao orderDao;
	@Autowired 
	private StoreDao storeDao;
	@Autowired
	private ReviewCommentDao reviewcommentdao;
	@Autowired
	private ModelMapper modelMapper;
	
//write,list(findAll),read(findByRno)
	public Page list(int pageno) {
		int countOfReview = reviewDao.count(null);
		Page page = PagingUtil.getPage(pageno, countOfReview);
		int srn = page.getStartRowNum();
		int ern = page.getEndRowNum();
		List<Review> list = reviewDao.findAll(srn, ern);
		List<ReviewDto.DtoForReviewList> dtoList = new ArrayList<ReviewDto.DtoForReviewList>();
		for(Review review: list) {
			ReviewDto.DtoForReviewList dto = modelMapper.map(review, ReviewDto.DtoForReviewList.class);
			dto.setRWriteTimeStr(review.getRWriteTime().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
			dto.setSNum(orderDao.findByOno(review.getONo()));
			dto.setSName(storeDao.findBysNum(dto.getSNum()).getSName());
			dtoList.add(dto);
		}
		page.setReviewlist(dtoList);
		return page;
	}
	public int write(ReviewDto.DtoForWrite dto) {
		Review review = modelMapper.map(dto, Review.class);
		review.setSNum(orderDao.findByOno(dto.getONo()));
		return reviewDao.insert(review);
	}
	public ReviewDto.DtoForRead read(Integer rNo,String uUsername){
		Review review = reviewDao.findByRno(rNo);
		ReviewDto.DtoForRead dto = modelMapper.map(review, ReviewDto.DtoForRead.class);
		String str = review.getRWriteTime().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
		dto.setRWriteTimeStr(str);
		dto.setSNum(orderDao.findByOno(review.getONo()));
		dto.setSName(storeDao.findBysNum(dto.getSNum()).getSName());
		dto.setComments(reviewcommentdao.findByRno(dto.getRNo()));
		System.out.println("ddddddddddddddddddddddddddddd");
		System.out.println(rNo);
		System.out.println(uUsername);
		System.out.println(review);
		System.out.println(dto);
		return dto;
	}
}
