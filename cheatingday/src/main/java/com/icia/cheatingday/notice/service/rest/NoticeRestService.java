package com.icia.cheatingday.notice.service.rest;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import com.icia.cheatingday.notice.dao.*;
import com.icia.cheatingday.notice.entity.*;

@Service
public class NoticeRestService {
	@Autowired
	NoticeDao dao;
	
	public void deleteNotice(Integer nNo, String aUsername) {
		Notice notice = dao.findById(nNo);
		if(notice.getAUsername().equals(aUsername)==true)		
		dao.delete(nNo);
	}

	public void updateNotice(Notice notice, String aUsername) {
		if(dao.findById(notice.getNNo()).getAUsername().equals(aUsername)==true)
		dao.update(notice);
	}
}
