package com.icia.cheatingday.admin.service.rest;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.icia.cheatingday.admin.dao.*;
import com.icia.cheatingday.manager.entity.*;
import com.icia.cheatingday.review.dao.*;
import com.icia.cheatingday.user.dao.*;
import com.icia.cheatingday.user.entity.*;

@Service
public class AdminRestService {

	@Autowired
	private ReviewDao rdao;
	@Autowired
	private AdminDao adao;

	public void deleteReport(int rNo) {
		Review review = rdao.findById(rNo);
		rdao.delete(rNo);
		
	}
	public int enabledM(int mNum) {
		return adao.enabledM(mNum);
	}
	
}
