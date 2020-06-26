package com.icia.cheatingday.main;

import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

import com.icia.cheatingday.manager.dao.*;
import com.icia.cheatingday.manager.dto.*;
import com.icia.cheatingday.manager.entity.*;

@Service
public class MainService {
	@Autowired
	private ManagerDao managerDao;
	@Autowired
	private PasswordEncoder pwdEncoder;
	@Autowired
	private ModelMapper modelMapper;
	
	public void ManagerJoin(ManagerDto.DtoForJoin dto) {
		ManagerEntity manager = modelMapper.map(dto, ManagerEntity.class);
		
		// 비밀번호 암호화(해시)
		String mPassword = manager.getMPassword();
		String encodedPassword = pwdEncoder.encode(mPassword);
		manager.setMPassword(encodedPassword);
		
		// 권한 추가 : ROLE_USER, ROLE_MANAGER, ROLE_ADMIN
		
	}

}
