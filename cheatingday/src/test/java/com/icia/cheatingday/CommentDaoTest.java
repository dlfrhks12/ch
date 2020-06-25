package com.icia.cheatingday;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.icia.cheatingday.freeboard.dao.CommentDao;
import com.icia.cheatingday.freeboard.entity.Comment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class CommentDaoTest {
	
	@Autowired
	private CommentDao dao;
	//@Test
	public void insertTest() {
		Comment comment = Comment.builder().bno(142).writer("양수민").content("첫번째 댓글").build();
		assertThat(dao.insert(comment), is(1));
	}
	//@Test
	public void findByIdTest() {
		assertThat((dao.findById(1)).getBno(), is(142));
	}
	//@Test
	public void findAllByBnoTest() {
		assertThat((dao.findAllByBno(142)).size(), is(1));
	}
	//@Test
	public void updateTest() {
		Comment comment = Comment.builder().cno(1).writer("변경").content("변경").writeTime(LocalDateTime.now()).build();
		assertThat(dao.update(comment), is(1));
	}
	@Test
	public void deleteTest() {
		assertThat(dao.delete(1), is(0));
	}
}
