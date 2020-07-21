package com.icia.cheatingday.notice.service.mvc;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.cheatingday.admin.dao.*;
import com.icia.cheatingday.common.dto.Page;
import com.icia.cheatingday.exception.*;
import com.icia.cheatingday.notice.dao.NoticeDao;
import com.icia.cheatingday.notice.dto.NoticeDto;
import com.icia.cheatingday.notice.entity.Notice;
import com.icia.cheatingday.util.PagingUtil;

@Service
public class NoticeService {
	@Autowired
	private NoticeDao dao;
	@Autowired
	private AdminDao adao;
	@Autowired
	private ModelMapper modelMapper;
	
	//[전체] 공지목록
	public Page list(int pageno) {
		int countOfBoard = dao.count();
		Page page = PagingUtil.getPage(pageno, countOfBoard);
		int srn = page.getStartRowNum();
		int ern = page.getEndRowNum();
		List<Notice> noticelist = dao.findAll(srn, ern);
		List<NoticeDto.DtoForList> dtolist = new ArrayList<>();
		for(Notice notice:noticelist) {
			NoticeDto.DtoForList dto = modelMapper.map(notice, NoticeDto.DtoForList.class);
			dto.setAIrum(adao.findById(dto.getAUsername()));
			dto.setNWriteTimeStr(notice.getNWriteTime().format(DateTimeFormatter.ofPattern("yyyy년MM월dd일")));
			dtolist.add(dto);
		}
		page.setNlist(dtolist);
		return page;
	}
	//[전체] 공지읽기
	public NoticeDto.DtoForRead read(Integer nNo, String aUsername) {
		Notice notice = dao.findById(nNo);
		if(notice==null)
			throw new JobFailException("공지를 찾을 수  없습니다");
		NoticeDto.DtoForRead dto
			= modelMapper.map(notice, NoticeDto.DtoForRead.class);
		if(aUsername!=null && aUsername.equals(dto.getAUsername())==false)
			dao.update(Notice.builder().nNo(nNo).nReadCnt(0).build());
		String str = notice.getNWriteTime().format(DateTimeFormatter.ofPattern("yyyy년MM월dd일"));
		dto.setAIrum(adao.findById(dto.getAUsername()));
		dto.setNWriteTimeStr(str);
		return dto;
	}
	//[관리자] 공지작성
	public int write(NoticeDto.DtoForWrite dto) {
		Notice notice = modelMapper.map(dto, Notice.class);
		dao.insert(notice);
		return notice.getNNo();
	}
}
