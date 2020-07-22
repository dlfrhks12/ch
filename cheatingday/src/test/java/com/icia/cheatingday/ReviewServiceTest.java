package com.icia.cheatingday;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icia.cheatingday.review.dao.ReviewDao;
import com.icia.cheatingday.review.dto.ReviewDto;
import com.icia.cheatingday.review.dto.ReviewDto.DtoForUpdate;
import com.icia.cheatingday.review.dto.ReviewDto.DtoForWrite;
import com.icia.cheatingday.review.entity.Review;
import com.icia.cheatingday.review.entity.ReviewComment;
import com.icia.cheatingday.review.service.ReviewService;
import com.icia.cheatingday.review.service.rest.ReviewRestService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class ReviewServiceTest {
	@Autowired
	private ReviewService service;
	@Autowired
	private ReviewRestService restService;
	@Autowired
	private ReviewDao dao;
	//@Test
	public void writeTest() {
		ReviewDto.DtoForWrite dto =new DtoForWrite();
		dto.setRTitle("아아아ㅣㅏ어ㅣ나ㅓ리ㅏ");
		dto.setRContent("dddddddd");
		dto.setUUsername("dkdkdkdk");
		dto.setSName(5);
		dto.setSName(450450);
		dto.setRWriteTime(LocalDateTime.now());
		dto.setRStarPoint(5);
		assertThat(service.write(dto), is(notNullValue()));
	}
	//@Test
	public void readTest() {
		assertThat(service.read(6, "양수민"), is(1));
	}
	
	//@Test
	public void updateBoardTest() {
		ReviewDto.DtoForUpdate dto = new DtoForUpdate();
		dto.setRNo(64);
		dto.setRTitle("바꼈다");
		dto.setRContent("dkdkdkdk");
		dto.setUUsername("최일관");
		dto.setRStarPoint(4);
		assertThat(restService.updateReview(dto), is(5));
	}
	//@Test
	public void writeCommentTest() {
		ReviewComment reviewComment = ReviewComment.builder().rcNo(1).rcWriter("양수민").rNo(64).mNum("5464546").rcContent("아아아아아").rcDateTime(LocalDateTime.now()).build();
		assertThat(restService.writeComment(reviewComment), is(notNullValue()));
	}
	@Test
	public void aag() {
		System.out.println(service.getSNum());
	}
}
