package com.icia.cheatingday;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icia.cheatingday.review.dao.ReviewCommentDao;
import com.icia.cheatingday.review.entity.ReviewComment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class reviewCommentTest {
	@Autowired
	private ReviewCommentDao dao;

	// @Test
	public void insertTest() {
		ReviewComment reviewComment = ReviewComment.builder().rNo(6).mNum("0140546").rcContent("aaaaaaa")
				.rcDateTime(LocalDateTime.now()).build();
		assertThat(dao.insert(reviewComment), is(1));
	}

	// @Test
	public void findById() {
		assertThat(dao.findById(4), is(1));
	}

	// @Test
	public void findAllByRno() {
		assertThat(dao.findAllByRno(5), is(1));
	}

	// @Test
	public void update() {
		ReviewComment reviewComment = ReviewComment.builder().rcNo(5).rcContent("ㅏㅓㅏㅓㅣㅏㅓ").build();
		assertThat(dao.update(reviewComment), is(1));
	}

	@Test
	public void delete() {
		assertThat(dao.delete(5), is(0));
	}
}