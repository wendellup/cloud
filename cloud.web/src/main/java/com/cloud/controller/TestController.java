package com.cloud.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class TestController {
	protected final Logger logger = Logger.getLogger(this.getClass());

	
	@RequestMapping(value = "test", method =RequestMethod.GET)
	public ModelAndView loginPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		return new ModelAndView("test");
	}
}