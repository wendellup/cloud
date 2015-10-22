package com.cloud.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cloud.service.TestService;
import com.cloud.valueobject.entity.Test;

@Controller
@RequestMapping("/")
public class TestController {
	protected final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	protected TestService testService;
	
	@RequestMapping(value = "test", method =RequestMethod.GET)
	public ModelAndView loginPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		int parentId = 1;
		Test test =
				testService.getTestById(parentId);
		ModelAndView mav = new ModelAndView("test");
		mav.addObject("test", test);
		mav.addObject("a", "aaa");
		mav.addObject("b", "bbb");
		return mav;
	}
}