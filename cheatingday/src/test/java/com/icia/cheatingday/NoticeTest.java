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

import com.icia.cheatingday.admin.dao.*;
import com.icia.cheatingday.admin.service.mvc.*;
import com.icia.cheatingday.admin.service.rest.*;
import com.icia.cheatingday.center.dao.*;
import com.icia.cheatingday.center.entity.*;
import com.icia.cheatingday.common.dto.*;
import com.icia.cheatingday.main.service.mvc.*;
import com.icia.cheatingday.manager.dao.*;
import com.icia.cheatingday.notice.dao.*;
import com.icia.cheatingday.notice.dto.*;
import com.icia.cheatingday.notice.dto.NoticeDto.*;
import com.icia.cheatingday.notice.entity.*;
import com.icia.cheatingday.notice.service.mvc.*;
import com.icia.cheatingday.notice.service.rest.*;
import com.icia.cheatingday.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class NoticeTest {
	@Inject
	private NoticeDao ndao;
	@Inject
	private QnADao qdao;
	@Inject
	private NoticeService ns;
	@Inject
	private NoticeRestService nrs;
	@Inject
	private AdminDao adao;
	@Inject
	private AdminService as;
	@Inject
	private AdminRestService ars;
	@Inject
	private StoreDao sdao;
	@Inject
	private MainService ms;
	
	//@Test
	public void init() {
		assertThat(ndao, is(notNullValue()));
	}
	@Test
	public void insett() {
		Notice notice = Notice.builder().nNo(1).nTitle("예제").nWriteTime(LocalDateTime.now()).content("예시").aUsername("usy1473").build();
		assertThat(ndao.insert(notice), is(1));
		System.out.println(notice);
	}
	//@Test
	public void sT() {
		int cnt = ndao.count();
		System.out.println(cnt);
		Page page = PagingUtil.getPage(1, cnt);
		System.out.println(page.getStartRowNum());
		System.out.println(page.getEndRowNum());
		List<Notice> list = ndao.findAll(page.getStartRowNum(), page.getEndRowNum());
		System.out.println(list);
	}
	// 리스트(페이징 글번호)
	//@Test
	public void wwq() {
		System.out.println(ns.list(1));
	}
	// 읽기(글번호, 아이디)
	//@Test
	public void wgq() {
		System.out.println(ns.read(1, "usy1473"));
	}
	// 업데이트(아이디)
	//@Test
	public void gqg() {
		Notice notice = Notice.builder().nNo(1).nTitle("예제21").build();
		nrs.updateNotice(notice, "usy1473");
	}
	// 글쓰기(dto.set을 이용 내용입력)
	//@Test 
	public void gwsg(){
		NoticeDto.DtoForWrite dto = new DtoForWrite();
		dto.setAUsername("usy1403");
		dto.setContent("asfqwgqwgq");
		dto.setNTitle("aiai");
		System.out.println(ns.write(dto));
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
	public void finq() {
		System.out.println(qdao.findById(8));
	}
	//@Test
	public void finadll() {
			int cnt = qdao.count(3);
			System.out.println(cnt);
	}
	//@Test
	public void rwg() {
		QnA qna = QnA.builder().qNo(1).qCano(2).build();
		qdao.update(qna);
		System.out.println(qna);
	}
	
	//@Test
	public void countf() {
		assertThat(qdao.count(1), is(1));
	}
	//@Test
	public void ehge() {
		System.out.println(adao.findById("admin1234"));
	}
	//@Test
	public void eghe() {
	}
	//@Test
	public void fcbf() {
		//adao.blockAll("usy14731");
	}
	@Test
	public void sgsndfnvc() {
		adao.findAllByEnabled();
	}
	//@Test
	public void asdgahrjhtr() {
		System.out.println(as.mlist());
	}
	//@Test
	public void safasgcv() {
		System.out.println(adao.enabledM(1231231231));
		System.out.println(adao.enabledM(1231231231));
		System.out.println(adao.enabledM(1231231231));
		System.out.println(adao.enabledM(1231231231));
	}
	//@Test
	public void asfbnhg() {
		System.out.print(ars.enabledM(1231231231));
		System.out.print(ars.enabledM(1231231231));
		System.out.print(ars.enabledM(1231231231));
		System.out.print(ars.enabledM(1231231231));
		System.out.print(ars.enabledM(1231231231));
	}
	//@Test
	public void vcbvcn() {
		System.out.println(sdao.count(2, null));
	}
	
	//@Test
	public void cvbnvcn()	{
	}
}