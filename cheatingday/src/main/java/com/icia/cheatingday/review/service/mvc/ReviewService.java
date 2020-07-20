package com.icia.cheatingday.review.service.mvc;

import java.time.format.*;
import java.util.*;

import org.modelmapper.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.icia.cheatingday.common.dto.*;
import com.icia.cheatingday.notice.dto.*;
import com.icia.cheatingday.notice.entity.*;
import com.icia.cheatingday.review.dao.*;
import com.icia.cheatingday.review.entity.*;
import com.icia.cheatingday.util.*;

@Service
public class ReviewService {
	@Autowired
	private ReviewDao reviewdao;
	@Autowired
	private ModelMapper modelMapper;
	
	

	
}
