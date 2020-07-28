package com.icia.cheatingday;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.time.*;

import javax.inject.*;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import com.icia.cheatingday.user.dao.*;
import com.icia.cheatingday.user.entity.*;
import com.icia.cheatingday.user.service.mvc.*;
import com.icia.cheatingday.user.service.rest.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class UserTest {
	@Autowired
	private UserDao dao;
	@Inject
	private UserService uservice;
	@Inject
	private UserRestService urservice;
	@Inject
	private PointDao pdao;
	
	
	//@Test
	public void insert() {
		User user = User.builder().uUsername("cheat").uIrum("cheating").uEmail("cheating@naver.com")
				.uTel("01011111111").uAddress("인천 학익동").uPassword("1234").uJoinDate(LocalDateTime.now()).build();
		dao.insert(user);
	}
	//@Test
		public void findByIdTest() {
			assertThat(dao.findById("spring"), is(false));
			System.out.println(dao.findById("spring"));
		}
	//@Test
	public void exsistsById() {
		assertThat(dao.existsById("summer"), is(false));
		assertThat(dao.existsById("spring"), is(true));
		
	}
	
	//@Test
	public void existsByUEmail() {
		assertThat(dao.existsByUEmail("spring@naver.com"), is(true));
		assertThat(dao.existsByUEmail("spring12@naver.com"), is(false));
	}
	//@Test
	public void updateTest() {
	User user = User.builder().uUsername("cheat").uIrum("HI").uEmail("cheating@naver.com")
			.uTel("1111111111").uAddress("인천 계산동").uPassword("1234").build();
	dao.update(user);
	}
	//@Test
	public void findUUsernameByUIrumAndUEmailTest() {
		assertThat(dao.findUUsernameByUIrumAndUEmail("spring", "spring@naver.com"), is(notNullValue()));
	}
	//@Test
	public void findPasswordByIdTest() {
		assertThat(dao.findPasswordById("spring"), is(notNullValue()));
	}
	//@Test
	public void deleteTest() {
		assertThat(dao.delete("cheat"), is(notNullValue()));
	}
	//////////////////////////////////////////////////////////////////////
	
	//@Test
	public void pageList() {
//		System.out.println(uservice.(1));
	}
	//@Test
	public void myPage() {
		System.out.println(uservice.myPage("spring"));
	}
	
	//@Test
	public void wfgw() {
		System.out.println(uservice.pointList(1, null));
	}
	@Test
	public void ssdgs() {
		Point point = Point.builder().uUsername("usy14731").totalPoint(5208).build();
		pdao.update(point);
		System.out.println(point);
		System.out.println(point);
		System.out.println(point);
	}
}
