package com.icia.cheatingday.user.entity;

import java.time.*;

import com.fasterxml.jackson.annotation.*;

import lombok.*;
import lombok.experimental.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain=true)
public class User {
	private String uUsername;
	private String uIrum;
	private String uEmail;
	private String uTel;
	private String uAddress;
	private String uPassword;
	private Boolean uEnabled;
	private LocalDateTime uJoinDate;
	private int uPoint;
	private int uLoginFailCnt;
	private String status;
}
