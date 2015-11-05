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
import com.cloud.valueobject.constvar.ConstVar;
import com.cloud.valueobject.entity.AppParameter;

@Controller
@RequestMapping("/blog")
public class BlogController {
	protected final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	protected AppParameterService appParameterService;
	
	private void initNavigatorData(ModelAndView mav, HttpServletRequest request){
		List<AppParameter> appParameterList =
				appParameterService.listAppParameterByParentId(ConstVar.BLOG_ROOT_ID);
		mav.addObject("appParameterList", appParameterList);
		String requestURI = request.getRequestURI();
		if(requestURI!=null){
			mav.addObject("requestURI", requestURI); 
		}
	}
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		System.out.println("requestURI:"+request.getRequestURI());
		System.out.println("requestURL:"+request.getRequestURL());
		ModelAndView mav = new ModelAndView("index");
		initNavigatorData(mav, request);
		return mav;
	}
	
	@RequestMapping(value = "article_list", method = RequestMethod.GET)
	public ModelAndView listArticle(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		ModelAndView mav = new ModelAndView("article_list");
		initNavigatorData(mav, request);
		return mav;
	}
	
	@RequestMapping(value = "demo", method = RequestMethod.GET)
	public ModelAndView demo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		ModelAndView mav = new ModelAndView("demo");
		initNavigatorData(mav, request);
		return mav;
	}
	
	@RequestMapping(value = "about", method = RequestMethod.GET)
	public ModelAndView about(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		ModelAndView mav = new ModelAndView("about");
		initNavigatorData(mav, request);
		return mav;
	}
	
	@RequestMapping(value = "index/test", method =RequestMethod.GET)
	public ModelAndView testAppParameter(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		List<AppParameter> appParameterList =
				appParameterService.listAppParameterByParentId(ConstVar.BLOG_ROOT_ID);
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("appParameterList", appParameterList);
		mav.addObject("a", "aaa");
		mav.addObject("b", "bbb");
		return mav;
	}
	
}