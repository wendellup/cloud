package com.cloud.controller.manager;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cloud.service.UserService;
import com.cloud.valueobject.entity.User;

@Controller
@RequestMapping("/manage/blog")
public class LoginController {
	protected final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	protected UserService userService;
	
	@RequestMapping(value = "/login", method =RequestMethod.GET)
	public ModelAndView loginPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		ModelAndView mav = new ModelAndView("manage/login");
		return mav;
	}
	
	@RequestMapping(value = "/login", method =RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute User user) throws ServletException {
		Map<String, Object> model = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView("", model);
		try {
			User findUser = userService.getUserByUserNameAndPwd(user);
			if(findUser==null){
				mav.addObject("login_error", "用户名或密码错误");
				mav.setViewName("manage/login");
			}else{
				//登录成功,重定向到主界面,在 session 中保存登录的用户
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", findUser);
//				mav.setViewName("manage/article_list");
//				 return new ModelAndView("redirect:article/list");
				return new ModelAndView("redirect:article/list?param_id=802");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		return mav;
	}
	
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		Map<String, Object> model = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView("manage/to_login", model);

		HttpSession session = request.getSession();
		session.invalidate();
		return mav;
	}
	
}
