package com.cloud.controller;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.egame.common.servlet.WebUtils;

import com.cloud.service.AppParameterService;
import com.cloud.service.TestService;
import com.cloud.valueobject.entity.AppParameter;
import com.cloud.valueobject.entity.Test;

@Controller
@RequestMapping("/")
public class TestController {
	protected final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	protected TestService testService;
	
	@Autowired
	protected AppParameterService appParameterService;
	
	@RequestMapping(value = "test", method =RequestMethod.GET)
	public ModelAndView test(HttpServletRequest request,
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
	
	@RequestMapping(value = "test/appParameter", method =RequestMethod.GET)
	public ModelAndView testAppParameter(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		int parentId = WebUtils.getInt(request, "parent_id", 0);
		List<AppParameter> appParameterList =
				appParameterService.listAppParameterByParentId(parentId);
		ModelAndView mav = new ModelAndView("test");
		mav.addObject("appParameterList", appParameterList);
		mav.addObject("a", "aaa");
		mav.addObject("b", "bbb");
		return mav;
	}
	
}