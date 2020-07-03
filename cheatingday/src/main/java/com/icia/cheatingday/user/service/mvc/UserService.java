package com.icia.cheatingday.user.service.mvc;

import java.time.*;
import java.time.format.*;
import java.time.temporal.*;
import java.util.*;

import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

import com.icia.cheatingday.common.dto.*;
import com.icia.cheatingday.exception.*;
import com.icia.cheatingday.user.dao.*;
import com.icia.cheatingday.user.dto.*;
import com.icia.cheatingday.user.entity.*;
import com.icia.cheatingday.util.*;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private PointDao pointDao;
	@Autowired
	private FavoriteDao favoriteDao;
	@Autowired
	private PasswordEncoder pwdEncoder;
	@Autowired
	private ModelMapper modelMapper;

	// 내정보 읽기
	public UserDto.DtoForRead myPage(String uUsername) {
		User user = userDao.findById(uUsername);
		//if(user==null)
		//	throw new UserNotFoundException();
		UserDto.DtoForRead dto = modelMapper.map(user, UserDto.DtoForRead.class);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
		dto.setUJoinDateStr(user.getUJoinDate().format(dtf));
		LocalDate joinDate = user.getUJoinDate().toLocalDate();
		LocalDate today = LocalDate.now();
		long days = ChronoUnit.DAYS.between(joinDate, today);
		dto.setDays(days);
		return dto;
	}
	// 비밀번호 확인
	public void checkPwd(String uPassword, String uUsername) {
		User user = userDao.findById(uUsername);
		System.out.println(user);
		if(user==null)
			throw new UserNotFoundException();
		String encodedPassword = user.getUPassword();
		boolean result = pwdEncoder.matches(uPassword, encodedPassword);
		if(result==false)
			throw new JobFailException("비밀번호가 틀렸습니다");
	}
	// 비밀번호 변경	
	public void changePwd(String uPassword, String newUPassword, String UUsername) {
		User user = userDao.findById(UUsername);
		if(user==null)
			throw new UserNotFoundException();
		String encodedPassword = user.getUPassword();
		if(pwdEncoder.matches(uPassword, encodedPassword)==true) {
			String newEncodedPassword = pwdEncoder.encode(newUPassword);
			userDao.update(User.builder().uPassword(newEncodedPassword).uUsername(UUsername).build());
		}
		else 
			throw new JobFailException("잘못된 비밀번호입니다.");
	}
	// 포인트 리스트 페이징
	public Page list(int pageno) {
		int countOfBoard = pointDao.count();
		Page page = PagingUtil.getPage(pageno, countOfBoard);
		int srn = page.getStartRowNum();
		int ern = page.getEndRowNum();
		List<Point> pointList = pointDao.findAll(srn, ern);
		List<PointDto.DtoForList> dtoList = new ArrayList<>();
		for(Point point:pointList) {
			PointDto.DtoForList dto = modelMapper.map(point, PointDto.DtoForList.class);
			dto.setAccumulationDayStr(point.getAccumulationDay().format(DateTimeFormatter.ofPattern("yyyy년 MM일 dd일")));
			dtoList.add(dto);
		}
		page.setPlist(dtoList);
		return page;
	}
	// 즐겨찾기
	public int favorite(int sNum, String uUsername ) {
		int num = favoriteDao.findFavoriteById(uUsername, sNum);
		if(num != 0) {
			return num;
		}
		return 0;
	}
	// 회원탈퇴
	public int resign(String uUsername) {
		return userDao.delete(uUsername);
	}
}