package com.icia.cheatingday;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icia.cheatingday.review.dao.ReviewDao;
import com.icia.cheatingday.review.entity.Review;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class reviewTest {
	
	@Autowired
	private ReviewDao dao;
	//@Test
	public void countTest() {
		assertThat(dao.count("양수민"), is(101));
	}
	//@Test
	public void findAllTest() {
		assertThat(dao.findAll(1, 2), is(5));
	}
	//@Test
	public void findAllByStarTest() {
		//assertThat(dao.findAllByStar(1, 2), is(2));
	}
	//@Test
	public void findAllBy0Rno() {
		//assertThat(dao.findAllByRno(1, 2), is(2));
	}
	//@Test
	public void reviewSingoUpdate() {
		assertThat(dao.reviewSingoUpdate(1), is(0));
	}
	//@Test
	public void countBySum() {
		assertThat(dao.countBySnum(1), is(0));
	}
	//@Test
	public void findAllBySNumTest() {
		assertThat(dao.findAllBysNum(1, 2, 1), is(1));
	}
//	@Test
	public void findByRnoTest() {
		assertThat(dao.findByRno(1), is(1));
	}
	
	//@Test
	public void findAllByReportTest() {
		assertThat(dao.findAllByReport(1, 2), is(1));
	}
	//@Test
	public void updateTest() {
		Review review = Review.builder().rNo(5).rTitle("변경").rStarPoint(5).rContent("ajajajaj").build();
		assertThat(dao.update(review), is(1));
	}
	@Test
	public void deleteTest() {
		assertThat(dao.delete(5), is(0));
	}
}
