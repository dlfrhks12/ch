package com.icia.cheatingday;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.time.*;
import java.util.*;

import javax.inject.*;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import com.icia.cheatingday.common.dto.*;
import com.icia.cheatingday.notice.dao.*;
import com.icia.cheatingday.notice.entity.*;
import com.icia.cheatingday.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class NoticeTest {
	@Inject
	private NoticeDao dao;
	//@Test
	public void init() {
		assertThat(dao, is(notNullValue()));
	}
	//@Test
	public void insett() {
		Notice notice = Notice.builder().nNo(1).nTitle("예제").nWriteTime(LocalDateTime.now()).content("예시").aUsername("usy1473").build();
		assertThat(dao.insert(notice), is(1));
		System.out.println(notice);
	}
	//@Test
	public void findallT() {
		int cnt = dao.count(null);
		System.out.println(cnt);
		Page page = PagingUtil.getPage(1, cnt);
		System.out.println(page.getStartRowNum());
		System.out.println(page.getEndRowNum());
		List<Notice> list = dao.findAll(page.getStartRowNum(), page.getEndRowNum());
		System.out.println(list);
	}
	//@Test
	public void findidT() {
		System.out.println(dao.findById(2));
	}
	
	//@Test
	public void upT() {
		Notice notice = Notice.builder().nNo(1).nTitle("예제2").build();
		dao.update(notice);
		System.out.println(notice);
	}
	
	//@Test
	public void deT() {
		dao.delete(1);
	}
}
