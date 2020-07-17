package com.icia.cheatingday.user.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.icia.cheatingday.user.entity.*;

@Repository
public class UserDao {
   @Autowired
   private SqlSessionTemplate tpl;
   
   public boolean existsById(String uUsername) {
      return tpl.selectOne("userMapper.existsById", uUsername);
   }
   
   public boolean existsByUEmail(String uEmail) {
      return tpl.selectOne("userMapper.existsByUEmail", uEmail);
   }
   
   public int insert(User user) {
      return tpl.insert("userMapper.insert", user);
   }

   public int update(User user) {
      return tpl.insert("userMapper.update", user);
   }
   public String findPasswordById(String uUsername) {
      return tpl.selectOne("userMapper.findPasswordById", uUsername);
   }

   public User findById(String uUsername) {
      return tpl.selectOne("userMapper.findById", uUsername);
   }

   public String findUUsernameByUIrumAndUEmail(String uIrum, String uEmail) {
      Map<String,String> map = new HashMap<String,String>();
      map.put("uIrum",uIrum);
      map.put("uEmail",uEmail);
      return tpl.selectOne("userMapper.findUUsernameByUIrumAndUEmail", map);
   }
   public int delete(String uUsername) {
      return tpl.delete("userMapper.delete", uUsername);
   }
   
}