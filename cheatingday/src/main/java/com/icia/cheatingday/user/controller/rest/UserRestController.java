package com.icia.cheatingday.user.controller.rest;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.access.prepost.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.logout.*;
import org.springframework.web.bind.annotation.*;

import com.icia.cheatingday.user.service.rest.*;

@RestController
public class UserRestController {
	@Autowired
	private UserRestService service;
	
	
}
