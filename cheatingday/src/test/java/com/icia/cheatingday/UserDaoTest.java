package com.icia.cheatingday;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.time.*;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import com.icia.cheatingday.user.dao.*;
import com.icia.cheatingday.user.entity.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class UserDaoTest {
	@Autowired
	private UserDao dao;
	
	//@Test
	public void findByUUsername() {
		assertThat(dao.findById("cheat"), is(false));
	}
	@Test
	public void insert() {
		User user = User.builder().uUsername("cheat").uIrum("cheatingDay").uEmail("cheating@naver.com")
				.uTel("01011111111").uAddress("인천 학익동").uPassword("1234").uJoinDate(LocalDateTime.now()).uPoint(1000).uLoginFailCnt(0).build();
		dao.insert(user);
	}
}
