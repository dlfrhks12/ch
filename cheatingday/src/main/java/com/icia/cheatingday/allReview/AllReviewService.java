package com.icia.cheatingday.allReview;

import java.io.IOException;

import javax.inject.Inject;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllReviewService {
	@Inject
	private AllReviewDao dao;
	@Autowired
	private ModelMapper modelMapper;
	
	
}
