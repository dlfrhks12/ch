package com.icia.cheatingday;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icia.cheatingday.allReview.AllReview;
import com.icia.cheatingday.allReview.AllReviewDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class AllReviewTest {
	@Inject
	private AllReviewDao dao;
	
	@Test
	public void insertTest() {
		for(int i=0; i<50; i++) {
			AllReview a = AllReview.builder().rTitle("제목")
					.uUsername("최일관")
					.cateno(1)
					.rStarPoint(5)
					.rContent("내용입니다")
					.rWriteTime(LocalDateTime.now())
					.oNo(1)
					.sNum(1)
					.build();
			assertThat(dao.insert(a), is(1));
		}
	}
}
