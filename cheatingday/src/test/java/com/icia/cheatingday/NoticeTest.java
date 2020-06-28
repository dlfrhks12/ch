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

import com.icia.cheatingday.center.dao.*;
import com.icia.cheatingday.center.entity.*;
import com.icia.cheatingday.common.dto.*;
import com.icia.cheatingday.notice.dao.*;
import com.icia.cheatingday.notice.entity.*;
import com.icia.cheatingday.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class NoticeTest {
	@Inject
	private NoticeDao ndao;
	@Inject
	private QnADao qdao;

	
	//@Test
	public void init() {
		assertThat(ndao, is(notNullValue()));
	}
	//@Test
	public void insett() {
		Notice notice = Notice.builder().nNo(1).nTitle("예제").nWriteTime(LocalDateTime.now()).content("예시").aUsername("usy1473").build();
		assertThat(ndao.insert(notice), is(1));
		System.out.println(notice);
	}
	@Test
	public void findallT() {
		int cnt = ndao.count();
		System.out.println(cnt);
		Page page = PagingUtil.getPage(1, cnt);
		System.out.println(page.getStartRowNum());
		System.out.println(page.getEndRowNum());
		List<Notice> list = ndao.findAll(page.getStartRowNum(), page.getEndRowNum());
		System.out.println(list);
	}
	//@Test
	public void findidT() {
		System.out.println(ndao.findById(2));
	}
	
	//@Test
	public void upT() {
		Notice notice = Notice.builder().nNo(1).nTitle("예제2").build();
		ndao.update(notice);
		System.out.println(notice);
	}
	
	//@Test
	public void deT() {
		ndao.delete(1);
	}
	//@Test
	public void inartw() {
		QnA qna = QnA.builder().qTitle("aaaa").qContent("ssss").qWriteTime(LocalDateTime.now()).mNum("121221").qCano(1).build();
		qdao.insert(qna);
		System.out.println(qna);
	}
	
	//@Test
	public void finq() {
		System.out.println(qdao.findById(2));
	}
	//@Test
	public void finadll() {
			int cnt = qdao.count(2);
			System.out.println(cnt);
	}
	//@Test
	public void rwg() {
		QnA qna = QnA.builder().qNo(6).qCano(2).build();
		qdao.update(qna);
		System.out.println(qna);
	}
	
	//@Test
	public void countf() {
		assertThat(qdao.count(1), is(1));
	}
}
