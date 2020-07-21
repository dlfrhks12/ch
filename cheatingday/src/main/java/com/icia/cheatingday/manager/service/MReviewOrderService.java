package com.icia.cheatingday.manager.service;

import java.time.format.*;
import java.util.*;

import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.icia.cheatingday.common.dto.*;
import com.icia.cheatingday.manager.dao.*;
import com.icia.cheatingday.manager.dto.*;
import com.icia.cheatingday.review.dao.*;
import com.icia.cheatingday.review.entity.*;
import com.icia.cheatingday.util.*;

@Service
public class MReviewOrderService {

	@Autowired
	private ReviewDao reviewDao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private StoreDao storeDao;
	@Autowired
	private ReviewCheckDao reviewCheckDao;
	
	
	//사업자 리뷰 신고--신고는 한번만 할수있게 막아.
	public int reviewSingoUpdate(int rNo, String username) {
		Review review = reviewDao.findByRno(rNo);
		System.out.println(review);
		
		if(reviewCheckDao.alreadyExist(username,rNo)!=null) { //username이 존재한다면
			int result = review.getRReport();  //결과에 신고를 불러와.
			return result;
		}
			//username이 존재하지 않다면
		reviewCheckDao.insert(username,rNo);
			//신고수를 1개 늘려
		return reviewDao.reviewSingoUpdate(rNo);
	}
	
	
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
