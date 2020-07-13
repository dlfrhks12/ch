package com.icia.cheatingday.admin.service.mvc;

import java.time.format.*;
import java.util.*;

import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.icia.cheatingday.admin.dao.*;
import com.icia.cheatingday.admin.dto.*;
import com.icia.cheatingday.common.dto.*;
import com.icia.cheatingday.manager.dao.*;
import com.icia.cheatingday.manager.entity.*;
import com.icia.cheatingday.review.dao.*;
import com.icia.cheatingday.review.entity.*;
import com.icia.cheatingday.user.dao.*;
import com.icia.cheatingday.user.entity.*;
import com.icia.cheatingday.util.*;

@Service
public class AdminService {
	@Autowired
	private ReviewDao rdao;
	@Autowired
	private UserDao udao;
	@Autowired
	private ManagerDao mdao;
	@Autowired
	private StoreDao sdao;	
	@Autowired
	private AdminDao adao;
	@Autowired
	private ModelMapper mapper;
	
	public Page list (int pageno, int rReport) {
		int countOfBoard = rdao.count(rReport, null);
		Page page = PagingUtil.getPage(pageno, countOfBoard);
		int srn = page.getStartRowNum();
		int ern = page.getEndRowNum();
		 
		List<Review> reviewlist = null;
		if(rReport>=1)
			reviewlist = rdao.findAllByReport(srn, ern, rReport);
		
		List<AdminDto.DtoForList> dtolist = new ArrayList<>();
		for(Review review:reviewlist) {
			AdminDto.DtoForList dto = mapper.map(review, AdminDto.DtoForList.class);
			dto.setRWriteTimeStr(review.getRWriteTime().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
			dto.setUIrum(udao.findById(dto.getUUsername()).getUIrum());
			dto.setSName(sdao.findBysNum(dto.getSNum()).getSName());
			dtolist.add(dto);
		}
		page.setAlist(dtolist);
		return page;
	}

	
	public List<User> ulist(){
		return adao.findAllUser();
	}
	public List<User> blockList(){
		return adao.findAllBlock();
	}
	public void userBlock(List<String> uUsernames) {
		adao.blockAll(uUsernames);
	}
	
	public void unblock(List<String> uUsernames) {
		adao.unblockAll(uUsernames);
	}
	public List<ManagerEntity> mlist(){
		return adao.findAllByEnabled();
	}
	public ManagerEntity mread(String mUsername) {
		ManagerEntity manager = mdao.findById(mUsername);
		return manager;
	}
	
}
