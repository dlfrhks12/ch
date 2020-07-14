package com.icia.cheatingday.freeboard.service;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.icia.cheatingday.common.dto.Page;
import com.icia.cheatingday.freeboard.dao.BoardCateDao;
import com.icia.cheatingday.freeboard.dao.CommentDao;
import com.icia.cheatingday.freeboard.dao.FreeBoardDao;
import com.icia.cheatingday.freeboard.dto.FreeBoardDto;
import com.icia.cheatingday.freeboard.entity.FreeBoard;
import com.icia.cheatingday.util.PagingUtil;

import lombok.Getter;

@Service
public class FreeBoardService {
	
	//리스트 페이지, 글쓰기, 글 읽기,
	@Autowired
	private FreeBoardDao dao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private BoardCateDao categoryDao;
	@Getter
	private List<Map> Boardcate;
	@PostConstruct
	public void init() {
		Boardcate = dao.findAllCate();
	}
	
	public Page list(int pageno, String username, Integer cateno) {
		int countOfBoard = dao.count(cateno);
		Page page = PagingUtil.getPage(pageno, countOfBoard);
		int srn = page.getStartRowNum();
		int ern = page.getEndRowNum();
		List<FreeBoard> list = null;
		System.out.println("..........................................");
		System.out.println(cateno);
		if(cateno!=null)
			list = dao.findAllByCategory(srn, ern, cateno);
		else 
			list = dao.findAll(srn, ern);
		System.out.println("2222222222222222222222222222222");
		System.out.println(list);
		List<FreeBoardDto.DtoForList> dtoList = new ArrayList<FreeBoardDto.DtoForList>();
		for(FreeBoard board: list) {
			FreeBoardDto.DtoForList dto = modelMapper.map(board, FreeBoardDto.DtoForList.class);
			dto.setWriteTimeStr(board.getWriteTime().format(DateTimeFormatter.ofPattern("yyyy년MM월dd일")));
			dto.setCategory(categoryDao.findByCateno(board.getCateno()));
			dtoList.add(dto);
		}
		
		page.setFreelist(dtoList);
		return page;
		
	}
	public int write(FreeBoardDto.DtoForWrite dto) throws IOException {
		dto.setCategory(categoryDao.findByCateno(dto.getCateno()));
		FreeBoard board = modelMapper.map(dto, FreeBoard.class);
		
		if(dto.getAttachments()!=null)
			board.setAttachmentCnt(dto.getAttachments().size());
		else
			board.setAttachmentCnt(0);
		dao.insert(board);
		List<MultipartFile> attachment = dto.getAttachments();
		
		
		return board.getBno();
	}
	public FreeBoardDto.DtoForRead read(Integer bno, String username){
		FreeBoard board = dao.findById(bno);
		FreeBoardDto.DtoForRead dto = modelMapper.map(board, FreeBoardDto.DtoForRead.class);
		//로그인 했는데 글쓴이가 이름이 다르면
		if(username!=null&&username.equals(dto.getUsername())==false)
			dao.update(FreeBoard.builder().bno(bno).readCnt(0).build());
		String str = board.getWriteTime().format(DateTimeFormatter.ofPattern("yyyy년MM월dd일"));
		dto.setWriteTimeStr(str);
		dto.setCategory(categoryDao.findByCateno(dto.getCateno()));
		
		if(board.getCommentCnt()>0)
			dto.setComments(commentDao.findAllByBno(dto.getBno()));
		System.out.println(dto);
		return dto;
		
		
	}
	
}
