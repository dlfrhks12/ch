package com.icia.cheatingday.user.service;

import java.time.*;
import java.time.format.*;
import java.time.temporal.*;

import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

import com.icia.cheatingday.user.dao.*;
import com.icia.cheatingday.user.dto.*;
import com.icia.cheatingday.user.entity.*;
import com.icia.cheatingday.user.exception.*;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private PasswordEncoder pwdEncoder;
	@Autowired
	private ModelMapper modelMapper;
	
	public void checkPwd(String uPassword, String uUsername) {
		User user = userDao.findById(uUsername);
		if(user==null)
			throw new UserNotFoundException();
		String encodedPassword = user.getUPassword();
		boolean result = pwdEncoder.matches(uPassword, encodedPassword);
		if(result==false)
			throw new JobFailException();
	}
	public UserDto.DtoForRead myPage(String uUsername) {
		User user = userDao.findById(uUsername);
		if(user==null)
			throw new UserNotFoundException();
		UserDto.DtoForRead dto = modelMapper.map(user, UserDto.DtoForRead.class);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
		dto.setJoinDateStr(user.getUJoinDate().format(dtf));
		
		LocalDate joinDate = user.getUJoinDate().toLocalDate();
		LocalDate today = LocalDate.now();
		long days = ChronoUnit.DAYS.between(joinDate, today);
		dto.setDays(days);
		return dto;
	}
	public void resign(String uUsername) {
		userDao.delete(uUsername);
	}
}
