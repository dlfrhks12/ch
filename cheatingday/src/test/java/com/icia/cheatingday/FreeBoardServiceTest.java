package com.icia.cheatingday;



import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icia.cheatingday.freeboard.controller.rest.FreeBoardRestController;
import com.icia.cheatingday.freeboard.dao.FreeBoardDao;
import com.icia.cheatingday.freeboard.dto.FreeBoardDto;
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
	
	@Test
	public void writeTest() {
		FreeBoard board = FreeBoard.builder().title("첫번째").content("내용").username("dddd").attachementCnt(0).build();
	}
	@Test
	public void readTest() {
		assertThat(service.read(1, "ddd"), is(1));
	}
	@Test
	public void listTest() {
		assertThat(service.list(2, "ddd"), is(notNullValue()));
	}
	@Test
	public void  readAttachment() {
		assertThat(restService.readAttachment(1), is(1));
	}
	@Test
	public void updateBoardTest() {
		FreeBoard board = FreeBoard.builder().bno(1).title("변경").content("바뀐").username("aaa").attachementCnt(1).build();
	}	

	
}
