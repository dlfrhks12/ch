package com.icia.cheatingday.controller.advice;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.*;

import com.icia.cheatingday.exception.*;

@ControllerAdvice(basePackages={"com.icia.cheatingday.user.controller.mvc"
		, "com.icia.cheatingday.user.service.mvc"})
public class AdviceController {
	@ExceptionHandler(JobFailException.class)
	public String JobExceptionHandler(JobFailException e, RedirectAttributes ra) {
		ra.addFlashAttribute("msg", e.getMessage());
		return "redirect:/user/check_pwd";
	}
	
}






