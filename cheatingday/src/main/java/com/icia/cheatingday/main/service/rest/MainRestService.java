package com.icia.cheatingday.main.service.rest;
/*
 * package com.icia.cheatingday.main.service.rest;
 * 
 * import javax.validation.constraints.*;
 * 
 * import org.springframework.beans.factory.annotation.*; import
 * org.springframework.stereotype.*;
 * 
 * import com.icia.cheatingday.exception.*; import
 * com.icia.cheatingday.manager.dao.*; import com.icia.cheatingday.user.dao.*;
 * 
 * @Service public class MainRestService {
 * 
 * @Autowired private ManagerDao managerDao;
 * 
 * @Autowired private UserDao userDao;
 * 
 * 
 * // [일반] 아이디 중복확인 public boolean checkUserId(@NotNull String uUsername) {
 * if(userDao.existsById(uUsername)==true) throw new UsernameExistException();
 * return true; }
 * 
 * // [일반] 이메일 중복확인 public boolean checkUserEmail(@NotNull String uEmail) {
 * if(userDao.existsByUEmail(uEmail)==true) throw new EmailExistException();
 * return true; }
 * 
 * // [사업자] 아이디 중복확인 public boolean checkManagerId(@NotNull String mUsername) {
 * if(managerDao.existsById(mUsername)==true) throw new
 * UsernameExistException(); return true; }
 * 
 * // [사업자] 이메일 중복확인 public boolean checkManagerEmail(@NotNull String mEmail) {
 * if(managerDao.existsByEmail(mEmail)==true) throw new EmailExistException();
 * return true; }
 * 
 * // [사업자] 사업자 등록번호 중복확인 public boolean checkManagerNum(@NotNull String mNum) {
 * if(managerDao.existsByManagerNumber(mNum)==true) throw new
 * ManagernumExistException(); return true; }
 * 
 * }
 */