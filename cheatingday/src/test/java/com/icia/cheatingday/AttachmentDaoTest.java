package com.icia.cheatingday;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icia.cheatingday.freeboard.dao.AttachmentDao;
import com.icia.cheatingday.freeboard.entity.Attachment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class AttachmentDaoTest {
	@Autowired
	private AttachmentDao dao;
	
	//@Test
	public void insertTest() {
		Attachment attachment = Attachment.builder().bno(173).writer("양수민").originalFileName("ㅁㅁㅁㅁㅁ").saveFileName("aalkd").flength(5).isImage(true).build();
		assertThat(dao.insert(attachment), is(1));
	}
	@Test
	public void findAllByBno() {
		assertThat((dao.findAllByBno(173)).size(), is(4));
	}
	//@Test
	public void findById() {
		System.out.println(dao.findById(8));
		assertThat(dao.findById(8),is(notNullValue()));
	}
	//@Test
	public void deleteById() {
		assertThat(dao.deleteById(1), is(0));
	}
	//@Test
	public void deleteAllById() {
		assertThat(dao.deleteAllByBno(142), is(0));
	}
}
