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
import com.cloud.valueobject.constvar.ConstVar;
import com.cloud.valueobject.entity.AppParameter;
import com.cloud.valueobject.entity.Test;

@Controller
@RequestMapping("/blog")
public class BlogController {
	protected final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	protected AppParameterService appParameterService;
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		List<AppParameter> appParameters = 
				appParameterService.listAppParameterByParentId(ConstVar.BLOG_ROOT_ID);
		ModelAndView mav = new ModelAndView("test");
		mav.addObject("appParameters", appParameters);
		mav.addObject("a", "aaa");
		mav.addObject("b", "bbb");
		return mav;
	}
	
	@RequestMapping(value = "index/test", method =RequestMethod.GET)
	public ModelAndView testAppParameter(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		int parentId = WebUtils.getInt(request, "parent_id", 0);
		List<AppParameter> appParameterList =
				appParameterService.listAppParameterByParentId(800);
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("appParameterList", appParameterList);
		mav.addObject("a", "aaa");
		mav.addObject("b", "bbb");
		return mav;
	}
	
}