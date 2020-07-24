package com.icia.cheatingday.user.dto;

import java.util.*;

import javax.validation.constraints.*;

import lombok.*;
import lombok.experimental.*;

public class UserDto {
   private UserDto() {}
   @Data
   @AllArgsConstructor
   @NoArgsConstructor
   @Builder
   @Accessors(chain = true)
   public static class DtoForJoin {
      // 알파벳으로 시작하는 영숫자 8~10자
      @Pattern(regexp="^[A-Za-z][A-Za-z0-9]{8,10}$", message="아이디는 영숫자 8~10자입니다" )
      private String uUsername;
      @Pattern(regexp="^(?=.*[!@#$%^&*])^[A-Za-z0-9!@#$%^&*]{8,10}$", message="비밀번호는 특수문자를 포함하는 영숫자 8~10자입니다")
      private String uPassword;
      @Pattern(regexp="^[가-힣]{2,5}$", message="이름은 한글 2~5자입니다")
      private String uIrum;
      @Email(message="잘못된 이메일 형식입니다")
      private String uEmail;
      @Pattern(regexp = "/^([0-9]{3})([0-9]{4})([0-9]{4})$/", message = "전화번호는 숫자 10~11자입니다")
      private String uTel;
      @Pattern(regexp = "^[가-힣]{1,9}$",message = "주소를 제대로 입력해주세요") 
      private String uAddress;
      private List<String> authorities;
   }
   
   @Data
   @Accessors(chain=true)
   public static class DtoForRead {
      private String uUsername;
      private String uIrum;
      private String uEmail;
      private String uTel;
      private String uAddress;
      private String uPassword;
      private String uJoinDateStr;
      private long days;
      private int uPoint;
   }
   
   @Data
   @Accessors(chain=true)
   public static class DtoForUpdate {
      @Pattern(regexp="^[A-Za-z][A-Za-z0-9]{7,9}$", message="아이디는 영숫자 8~10자입니다" )
      private String uUsername;
      @Pattern(regexp="^[가-힣]{2,5}$", message="이름은 한글 2~5자입니다")
      private String uIrum;
      @Pattern(regexp="(?=.*[!@#$%^&*])^[A-Za-z0-9!@#$%^&*]{8,10}$", message="비밀번호는 특수문자를 포함하는 영숫자 8~10자입니다")
      private String uPassword;
      @Pattern(regexp="(?=.*[!@#$%^&*])^[A-Za-z0-9!@#$%^&*]{8,10}$", message="비밀번호는 특수문자를 포함하는 영숫자 8~10자입니다")
      private String newUPassword;
      private String uAddress;
      private String uTel;
      @Email(message="잘못된 이메일 형식입니다")
      private String uEmail;
   }
   
}