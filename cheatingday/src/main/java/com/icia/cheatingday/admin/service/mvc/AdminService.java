package com.icia.cheatingday.admin.service.mvc;

import java.time.format.*;
import java.util.*;

import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.icia.cheatingday.admin.dao.*;
import com.icia.cheatingday.admin.dto.*;
import com.icia.cheatingday.common.dto.*;
import com.icia.cheatingday.exception.*;
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
	
	//[관리자]신고글 페이징
	public Page list (int pageno) {
		int countOfBoard = rdao.countByRepoert();
		Page page = PagingUtil.getPage(pageno, countOfBoard);
		int srn = page.getStartRowNum();
		int ern = page.getEndRowNum();
		List<Review> reviewlist = null;
		reviewlist = rdao.findAllByReport(srn, ern);
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

	//[관리자]유저목록 출력
	public List<AdminDto.DtoForuserList> ulist(){
		List<User> userlist = adao.findAllUser();
		List<AdminDto.DtoForuserList> dtolist = new ArrayList<>();
		for(User user:userlist) {
			AdminDto.DtoForuserList dto = mapper.map(user,AdminDto.DtoForuserList.class);
			dto.setUJoinDateStr(user.getUJoinDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
			dtolist.add(dto);
		}
		return  dtolist;
	}
	//[관리자]블락 유저출력
	public List<AdminDto.DtoForblockList> blockList(){
		List<User> userlist = adao.findAllBlock();
		List<AdminDto.DtoForblockList> dtolist = new ArrayList<>();
		for(User user:userlist) {
			AdminDto.DtoForblockList dto = mapper.map(user,AdminDto.DtoForblockList.class);
			dto.setUJoinDateStr(user.getUJoinDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));
			dtolist.add(dto);
		}
		return  dtolist;
	}
	//[관리자]유저 블럭하기
	public void userBlock(List<String> uUsernames) {
		adao.blockAll(uUsernames);
	}
	//[관리자]유저 블럭 해제
	public void unblock(List<String> uUsernames) {
		adao.unblockAll(uUsernames);
	}
	//[관리자]승인대기 사업자 리스트
	public List<AdminDto.DtoFormList> mlist(){
		List<ManagerEntity> mlist = adao.findAllByEnabled();
		List<AdminDto.DtoFormList> dtolist = new ArrayList<>();
		for(ManagerEntity manager:mlist) {
			AdminDto.DtoFormList dto = mapper.map(manager,AdminDto.DtoFormList.class);
			if(manager.getMEnabled()==false)
				dto.setMEnabledStr("승인대기중");
			dtolist.add(dto);
		}
		return  dtolist;
	}
	//[관리자]사업자 정보 읽기
	public ManagerEntity mread(String mUsername) {
		ManagerEntity manager = mdao.findById(mUsername);
		if(manager==null)
			throw new UserNotFoundException();
		return manager;
	}
	
}
