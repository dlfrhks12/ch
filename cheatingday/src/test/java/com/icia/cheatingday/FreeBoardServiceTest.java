package com.icia.cheatingday;



import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icia.cheatingday.freeboard.service.FreeBoardService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**-context.xml")
public class FreeBoardServiceTest {
	@Autowired
	private FreeBoardService service;

	/*
	public void writeTest() {
	 assertThat(service.write(FreeBoardDto.DtoForWrite.class),
	 is(notNullValue())); 	 
	 }
	*/
}

