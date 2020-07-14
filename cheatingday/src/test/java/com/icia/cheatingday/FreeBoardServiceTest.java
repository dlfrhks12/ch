
package com.icia.cheatingday;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import java.io.*;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import com.icia.cheatingday.freeboard.dao.*;
import com.icia.cheatingday.freeboard.dto.*;
import com.icia.cheatingday.freeboard.dto.FreeBoardDto.*;
import com.icia.cheatingday.freeboard.entity.*;
import com.icia.cheatingday.freeboard.service.*;
import com.icia.cheatingday.freeboard.service.rest.*;

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
		File targetFile = new File("d:/attachment/1591854040995-anony.jpg");
		dto.setUsername("dddd");
		dto.setTitle("첫번째");
		dto.setContent("ㅁㅁㅁ");
		dto.setCateno(1);
		assertThat(service.write(dto), is(notNullValue()));
	}
	//@Test
	public void readTest() {
		FreeBoard board = dao.findById(173);
		System.out.println("------------------------------------------------");
		System.out.println(board);
		assertThat(service.read(173, "양수민"), is(notNullValue()));
	}
	@Test
	public void listTest() {
		assertThat(service.list(1, "양수민", 1),is(1));
	}
	//@Test
	public void updateBoardTest() {
		FreeBoardDto.DtoForUpdate dto = new DtoForUpdate();
		dto.setTitle("aaa");
		dto.setBno(173);
		dto.setContent("바뀜");
		dto.setUsername("dkdkk");
		assertThat(restService.updateBoard(dto), is(notNullValue()) );
	}	
}

