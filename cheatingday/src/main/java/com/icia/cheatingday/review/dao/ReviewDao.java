package com.icia.cheatingday.review.dao;

import java.util.*;

import org.mybatis.spring.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.icia.cheatingday.review.entity.*;

@Repository
public class ReviewDao {
   @Autowired
   private SqlSessionTemplate tpl;
   
   public int count(String uUsername) {
      return tpl.selectOne("reviewMapper.count", uUsername);
   }
   // 전체 리스트
   public List<Review> findAll(int startRowNum, int endRowNum) {
      Map<String, Integer> map = new HashMap<>();
      map.put("startRowNum", startRowNum);
      map.put("endRowNum", endRowNum);
      return tpl.selectList("reviewMapper.findAll", map);
   }
   //사업자&일반회원 리뷰신고
   public int reviewSingoUpdate(int rNo) {
      return tpl.update("reviewMapper.reviewSingo",rNo);
   }
   
   public int countBySnum(Integer sNum) {
      return tpl.selectOne("reviewMapper.countBySnum",sNum);
   }
   
   public List<Review> findAllBysNum(int startRowNum, int endRowNum, Integer sNum){
      Map<String, Integer> map = new HashMap<>();
      map.put("startRowNum", startRowNum);
      map.put("endRowNum", endRowNum);
      map.put("sNum", sNum);
      return tpl.selectList("reviewMapper.findAllBysNum",map);
   }
   //사업자> 음식점 리뷰 읽기
   public Review findByRno(Integer rNo) {
      return tpl.selectOne("reviewMapper.findByRno",rNo);
   }
   public int countByRepoert() {   
      return tpl.selectOne("reviewMapper.countByRepoert");
   }
   public List<Review> findAllByReport(int startRowNum, int endRowNum){
      Map<String, Integer> map = new HashMap<>();
      map.put("startRowNum", startRowNum);
      map.put("endRowNum", endRowNum);
      return tpl.selectList("reviewMapper.findAllByReport", map);
   }
   public List<Review> findAllByUsername(int startRowNum, int endRowNum, String uUsername){
      Map<String, Object> map = new HashMap<>();
      map.put("startRowNum", startRowNum);
      map.put("endRowNum", endRowNum);
      map.put("uUsername", uUsername);
      return tpl.selectList("reviewMapper.findAllByUsername", map);
   }

   public int update(Review review) {
      return tpl.update("reviewMapper.update",review);
   }
   
   public int delete(Integer rNo) {
      return tpl.delete("reviewMapper.delete", rNo);
   }
   public int insert(Review review) {
      return tpl.insert("reviewMapper.insert",review);
   }
}