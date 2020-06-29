package com.icia.cheatingday;



import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icia.cheatingday.freeboard.dao.FreeBoardDao;
import com.icia.cheatingday.freeboard.dto.FreeBoardDto;
import com.icia.cheatingday.freeboard.dto.FreeBoardDto.DtoForUpdate;
import com.icia.cheatingday.freeboard.dto.FreeBoardDto.DtoForWrite;
import com.icia.cheatingday.freeboard.entity.FreeBoard;
import com.icia.cheatingday.freeboard.service.FreeBoardService;
import com.icia.cheatingday.freeboard.service.rest.FreeBoardRestService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class FreeBoardServiceTest {
	@Autowired
	private FreeBoardService service;
	@Autowired
	private FreeBoardRestService restService;
	@Autowired
	private FreeBoardDao dao;
	
	

	//@Test
	public void writeTest() throws IOException {
		FreeBoardDto.DtoForWrite dto = new DtoForWrite();
		File targetFile = new File("d:/puffy.jpg");
		
		dto.setUsername("dddd");
		dto.setTitle("첫번째");
		dto.setContent("ㅁㅁㅁ");
		assertThat(service.write(dto), is(notNullValue()));
	}
	//@Test
	public void readTest() {
		FreeBoard board = dao.findById(173);
		System.out.println("------------------------------------------------");
		System.out.println(board);
		assertThat(service.read(173, "양수민"), is(notNullValue()));
	}
	//@Test
	public void listTest() {
		assertThat(service.list(1, "양수민"), is(50));
	}
	//@Test
	public void  readAttachment() {
		assertThat(restService.readAttachment(8), is(1));
	}
	@Test
	public void updateBoardTest() {
		FreeBoardDto.DtoForUpdate dto = new DtoForUpdate();
		dto.setTitle("aaa");
		dto.setBno(173);
		dto.setContent("바뀜");
		dto.setUsername("dkdkk");
		assertThat(restService.updateBoard(dto), is(notNullValue()) );
	}	

	
}
