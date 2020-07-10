package com.icia.cheatingday.manager.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.cheatingday.common.dto.Page;
import com.icia.cheatingday.manager.dao.StoreDao;
import com.icia.cheatingday.manager.dto.ManagerDto;
import com.icia.cheatingday.review.dao.ReviewDao;
import com.icia.cheatingday.user.entity.Review;
import com.icia.cheatingday.util.PagingUtil;

@Service
public class MReviewOrderService {

	@Autowired
	private ReviewDao reviewDao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private StoreDao storeDao;
	
	//sNum으로 리뷰리스트 페이징
	public Page list (int pageno, String mUsername) {
		int sNum = storeDao.findBymUsername(mUsername).getSNum();
		
		int countOfBoard = reviewDao.countBySnum(sNum);
		Page page = PagingUtil.getPage(pageno, countOfBoard);
		int srn = page.getStartRowNum();
		int ern = page.getEndRowNum();
		
		List<Review> reviewList = null;
			reviewList = reviewDao.findAllBysNum(srn, ern, sNum);
		
		List<ManagerDto.DtoForList> dtoList = new ArrayList<>();
		for(Review review:reviewList) {
			ManagerDto.DtoForList dto = modelMapper.map(review, ManagerDto.DtoForList.class);
			dto.setRWriteTimeStr(review.getRWriteTime().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
			dtoList.add(dto);
		}
		page.setMList(dtoList);
		return page;
		
	}
	
	//음식점 리뷰읽기
	public ManagerDto.DtoForReviewRead read(int rNo) {
		
		Review review = reviewDao.findByRno(rNo);
		ManagerDto.DtoForReviewRead dto = modelMapper.map(review, ManagerDto.DtoForReviewRead.class);
		dto.setRWriteTimeStr(review.getRWriteTime().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
		return dto;
		
	}
	
	
}
