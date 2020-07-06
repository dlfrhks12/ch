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
import com.icia.cheatingday.freeboard.dao.AttachmentDao;
import com.icia.cheatingday.freeboard.dao.BoardCateDao;
import com.icia.cheatingday.freeboard.dao.CommentDao;
import com.icia.cheatingday.freeboard.dao.FreeBoardDao;
import com.icia.cheatingday.freeboard.dto.FreeBoardDto;
import com.icia.cheatingday.freeboard.entity.Attachment;
import com.icia.cheatingday.freeboard.entity.FreeBoard;
import com.icia.cheatingday.util.PagingUtil;

import lombok.Getter;

@Service
public class FreeBoardService {
	
	//리스트 페이지, 글쓰기, 글 읽기,
	@Autowired
	private FreeBoardDao dao;
	@Autowired
	private AttachmentDao attachmentDao;
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
	
	public Page list(int pageno, String username) {
		int countOfBoard = (int) dao.count(username);
		Page page = PagingUtil.getPage(pageno, countOfBoard);
		System.out.println("3333333333333");
		System.out.println(page);
		int srn = page.getStartRowNum();
		System.out.println(srn);
		int ern = page.getEndRowNum();
		System.out.println(ern);
		List<FreeBoard> list = dao.findAll(srn, ern);;
		System.out.println(list);
		List<FreeBoardDto.DtoForList> dtoList = new ArrayList<FreeBoardDto.DtoForList>();
		System.out.println(dtoList);
		for(FreeBoard board1: list) {
			FreeBoardDto.DtoForList dto = modelMapper.map(board1, FreeBoardDto.DtoForList.class);
			dto.setWriteTimeStr(board1.getWriteTime().format(DateTimeFormatter.ofPattern("yyyy년MM월dd일")));
			dto.setCategory(categoryDao.findByCateno(board1.getCateno()));
			dtoList.add(dto);
		}
		page.setFreelist(dtoList);
		System.out.println("//////////////////////////////");
		System.out.println(dtoList);
		return page;
		
	}
	public int write(FreeBoardDto.DtoForWrite dto) throws IOException {
		FreeBoard board = modelMapper.map(dto, FreeBoard.class);
		
		if(dto.getAttachment()!=null)
			board.setAttachementCnt(dto.getAttachment().size());
		else
			board.setAttachementCnt(0);
		dao.insert(board);
		List<MultipartFile> attachment = dto.getAttachment();
		
		if(attachment!=null) {
			for(MultipartFile mf:attachment) {
				Attachment attachments = new Attachment();
				String originalFileName = mf.getOriginalFilename();
				long time = System.nanoTime();
				String saveFileName = time+"-"+originalFileName;
				boolean isImage = mf.getContentType().toLowerCase().startsWith("image/");
				attachments.setBno(board.getBno()).setImage(isImage).setWriter(board.getUsername()).setFlength((int)mf.getSize()).setOriginalFileName(originalFileName).setSaveFileName(saveFileName);
				File file = new File("d:/upload/attachment",saveFileName);
				FileCopyUtils.copy(mf.getBytes(), file);
			    attachmentDao.insert(attachments);
			}
		}
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
		if(board.getAttachementCnt()>0)
			dto.setAttachment(attachmentDao.findAllByBno(dto.getBno()));
		if(board.getCommentCnt()>0)
			dto.setComment(commentDao.findAllByBno(dto.getBno()));
		return dto;
		
		
	}
	
}
