package com.icia.cheatingday.main.service.mvc;

import java.time.*;
import java.util.*;

import javax.mail.*;
import javax.validation.constraints.*;

import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.Service;

import com.icia.cheatingday.authority.dao.*;
import com.icia.cheatingday.common.dto.*;
import com.icia.cheatingday.exception.*;
import com.icia.cheatingday.manager.dao.*;
import com.icia.cheatingday.manager.dto.*;
import com.icia.cheatingday.manager.entity.*;
import com.icia.cheatingday.user.dao.*;
import com.icia.cheatingday.user.dto.*;
import com.icia.cheatingday.user.entity.*;
import com.icia.cheatingday.util.*;

@Service
public class MainService {
  
	@Autowired 
	private UserDao userDao;
	@Autowired 
	private ManagerDao managerDao;
	@Autowired 
	private AuthorityDao authorityDao;
	@Autowired
	private PasswordEncoder pwdEncoder;
	@Autowired 
	private ModelMapper modelMapper;
	@Autowired 
	private MailUtil mailUtil;
  
	
	
	// [일반] 회원가입 
	public void UserJoin(UserDto.DtoForJoin dto) { 
	User user = modelMapper.map(dto, User.class);
  
	// 비밀번호 암호화(해시) 
	String uPassword = user.getUPassword();
	String encodedPassword = pwdEncoder.encode(uPassword);
	user.setUPassword(encodedPassword);
  
	
	// 권한 추가 : ROLE_USER, ROLE_MANAGER, ROLE_ADMIN
	List<String> authorities = dto.getAuthorities(); 
	for(String authority:authorities)
		authorityDao.insert(user.getUUsername(), authority);
	user.setUJoinDate(LocalDateTime.now()); 
	userDao.insert(user); 
	}
  
  
	// [일반] 아이디 찾기 
	public String findUserUsername(String uIrum, String uEmail) {
		String uUsername = userDao.findUUsernameByUIrumAndUEmail(uIrum, uEmail);
		if(uUsername==null) 
			throw new UserNotFoundException(); 
		return uUsername; 
	}
  
  
	// [일반] '마이페이지' 클릭 시 비밀번호 확인 
	public void checkUserPwd(String uPassword,String uUsername) { 
		User user = userDao.findById(uUsername); if(user==null)
			throw new UserNotFoundException(); String encodedPassword =
			user.getUPassword(); boolean result = pwdEncoder.matches(uPassword, encodedPassword); 
			if(result==false) 
				throw new JobFailException("비밀번호가 일치하지 않습니다"); }
  
  
	// [일반] 비밀번호 찾기 (재설정) 메일보내기 
	public void resetUserPwd(@NotNull String uUsername, @NotNull String uEmail) throws MessagingException { 
		User user = userDao.findById(uUsername); 
		if(user==null) 
			throw new UserNotFoundException(); 
		if(user.getUEmail().equals(uEmail)==false) 
			throw new UserNotFoundException();
  
		String link = "<a href='http://localhost:8081/cheatingday/u_change_pwd?uUsername=" + uUsername + "'>";
		StringBuffer text = new StringBuffer("<p>Cheating Day 비밀번호 재설정 안내</p>");
		text.append("<p>아래 링크를 통해 새 비밀번호로 변경해주세요</p>"); 
		text.append(link);
		text.append("클릭하세요</a>");
		Mail mail = Mail.builder().sender("CheatingDay@icia.com").receiver(uEmail)
				.title("Cheating Day 비밀번호 재설정 안내").content(text.toString()).build();
		mailUtil.sendMail(mail); 
		}
  
  
	// [일반] 새 비밀번호로 변경
	public void changeUserPwd(String uPassword, String uNewPassword, String uUsername) {
		User user = userDao.findById(uUsername);
		if(user==null)
			throw new UserNotFoundException();
		String encodedPassword = user.getUPassword();
		if(pwdEncoder.matches(uPassword, encodedPassword)==true) {
			String newEncodedPassword = pwdEncoder.encode(uNewPassword);
			userDao.update(User.builder().uPassword(newEncodedPassword).uUsername(uUsername).build());
		}
		else {
			throw new JobFailException("잘못된 비밀번호입니다");
		}
	}
	
	
	// [사업자] 회원가입 
	public void ManagerJoin(ManagerDto.DtoForJoin dto) { 
	ManagerEntity manager = modelMapper.map(dto, ManagerEntity.class);
  
	// 비밀번호 암호화(해시) 
	String mPassword = manager.getMPassword();
	String encodedPassword = pwdEncoder.encode(mPassword);
	manager.setMPassword(encodedPassword);
  
	
	// 권한 추가 : ROLE_USER, ROLE_MANAGER, ROLE_ADMIN
	List<String> authorities = dto.getAuthorities(); 
	for(String authority:authorities)
		authorityDao.insert(manager.getMUsername(), authority);
	managerDao.insert(manager);
	
	
	// 회원가입이 처리 되면 관리자에게 승인 요청하기
	// 관리자가 승인수락을 하면 enabled = 1로 변경
	
	}
  
 
	// [사업자] 아이디찾기 
	public String findManagerUsername(String mIrum, String mEmail) { 
		String mUsername = managerDao.findUsernameByIrumAndEmail(mIrum, mEmail);
		if(mUsername==null) 
			throw new UserNotFoundException(); return mUsername; }
  
  
	// [사업자] '사장님 페이지로' 클릭 시 비밀번호 확인 
	public void checkManagerPwd(String mPassword, String mUsername) {
		ManagerEntity manager = managerDao.findById(mUsername); 
		if(manager==null) throw new UserNotFoundException(); 
		String encodedPassword = manager.getMPassword();
		boolean result = pwdEncoder.matches(mPassword, encodedPassword);
		if(result==false) throw new JobFailException("비밀번호가 일치하지 않습니다"); }
  
  
	// [사업자] 비밀번호 찾기 
	public void resetManagerPwd(@NotNull String mUsername, @NotNull String mEmail) throws MessagingException { 
		ManagerEntity manager = managerDao.findById(mUsername); 
		if(manager==null) throw new UserNotFoundException(); 
		if(manager.getMEmail().equals(mEmail)==false) throw new UserNotFoundException();
  
		StringBuffer text = new StringBuffer("<p>Cheating Day 비밀번호 재설정 안내</p>");
		text.append("<p>새 비밀번호로 변경해주세요</p>"); 
		Mail mail = Mail.builder().sender("CheatingDay@icia.com").receiver(mEmail)
				.title("Cheating Day 비밀번호 재설정 안내").content(text.toString()).build();
		mailUtil.sendMail(mail); 
  	} 
  }