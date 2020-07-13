package com.icia.cheatingday;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icia.cheatingday.freeboard.dao.FreeBoardDao;
import com.icia.cheatingday.freeboard.entity.FreeBoard;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class FreeBoardTest {
	@Inject
	private FreeBoardDao dao;
	//@Test
	public void insertTest() {
		for(int i=0; i<50; i++) {
		FreeBoard board = FreeBoard.builder().title("제목").content("alalals").username("양수민").writeTime(LocalDateTime.now()).cateno(2).build();
		assertThat(dao.insert(board), is(1));
		}
		
		}
	//@Test
	public void findByIdTest() {
		assertThat((dao.findById(142)).getUsername(), is("진진진"));
	}
	//@Test
	public void findAllTest() {
		assertThat((dao.findAll(1, 10)).size(), is(10));
	}
	//@Test
	public void updateTest() {
		FreeBoard board = FreeBoard.builder().bno(142).title("업데이트").content("내용바뀜").build();
		assertThat(dao.update(board), is(1));
	}
	//@Test
	public void deleteTest() {
		assertThat(dao.delete(2), is(0));
	}
	//@Test
	public void findAllByUsename() {
		assertThat(dao.findAllByUsername(1, 5, "진진진"), is(1));
	}
	//@Test
	public void countTet() {
		assertThat(dao.count(1), is(50));
	}
	//@Test
	public void findAllByCateno() {
		assertThat(dao.findAllByCategory(1, 10, 1), is(1));
	}

}
