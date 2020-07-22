package com.icia.cheatingday.admin.service.rest;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.icia.cheatingday.admin.dao.*;
import com.icia.cheatingday.exception.*;
import com.icia.cheatingday.review.dao.*;
import com.icia.cheatingday.review.entity.*;

@Service
public class AdminRestService {

	@Autowired
	private ReviewDao rdao;
	@Autowired
	private AdminDao adao;

	//[관리자]신고된 댓글 삭제
	public void deleteReport(int rNo) {
		Review review = rdao.findByRno(rNo);
		if(review==null)
			throw new JobFailException("해당 리뷰를 찾을 수 없습니다");
		rdao.delete(rNo);
		
	}
	//[관리자]사업자 가입승인
	public int enabledM(long mNum) {
		return adao.enabledM(mNum);
	}
	
}
