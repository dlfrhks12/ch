package com.icia.cheatingday.user.service.rest;

import java.time.format.*;

import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

import com.icia.cheatingday.exception.*;
import com.icia.cheatingday.user.dao.*;
import com.icia.cheatingday.user.dto.UserDto.*;
import com.icia.cheatingday.user.entity.*;

@Service
public class UserRestService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private PasswordEncoder pwdEncoder;
	@Autowired
	private ModelMapper modelMapper;
	// 아이디 중복확인
	public boolean checkId(String uUsername) {
		if(userDao.existsById(uUsername)==true)
			throw new UsernameExistException();
		return true;
	}
	
	// 이메일 중복확인
	public boolean checkEmail(String uEmail) {
		if(userDao.existsByUEmail(uEmail)==true)
			throw new EmailExistException();
		return true;
	}
	// 업데이트 전 내 정보 맞는지 체크
	public void update(DtoForUpdate dto) {
		if(dto.getUPassword()!=null) {
			User user = userDao.findById(dto.getUUsername());
			if(user==null)
				throw new UserNotFoundException();
			String password = user.getUPassword();
			if(dto.getUPassword().equals(password)==false)
				throw new JobFailException("비밀번호를 확인할 수 없습니다.");
			user.setUPassword(dto.getNewUPassword());
		}
		
		User user = modelMapper.map(dto, User.class);
		userDao.update(user);
	}
	
}
