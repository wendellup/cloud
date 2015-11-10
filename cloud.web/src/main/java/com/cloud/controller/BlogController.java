package com.cloud.controller;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.egame.common.model.PageData;
import cn.egame.common.servlet.WebUtils;

import com.cloud.service.AppParameterService;
import com.cloud.service.ArticleService;
import com.cloud.valueobject.constvar.ConstVar;
import com.cloud.valueobject.constvar.EnumType.TagType;
import com.cloud.valueobject.entity.AppParameter;
import com.cloud.valueobject.entity.Article;
import com.cloud.valueobject.entity.ParameterTag;

@Controller
@RequestMapping("/blog")
public class BlogController {
	protected final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	protected AppParameterService appParameterService;
	
	@Autowired
	protected ArticleService articleService;
	
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
	
	@RequestMapping(value = "/article/list", method = RequestMethod.GET)
	public ModelAndView listArticle(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		ModelAndView mav = new ModelAndView("article_list");
		initNavigatorData(mav, request);
		int currentPage = WebUtils.getInt(request, "current_page", 0);
		int rowsOfPage = WebUtils.getInt(request, "rows_of_page", 0);
		
		//若未传tagId则获取整个文章列表,若传tagId则获取该tagId下文章列表信息
//		List<Article> articleList = articleService
//				.listArticleByAppParameterIdAndTagId(
//						ConstVar.ARTICLE_LIST_PARAM_ID
//						, 0, current_page, rows_of_page);
		PageData pd = articleService
				.pageArticleByAppParameterIdAndTagId(
						ConstVar.ARTICLE_LIST_PARAM_ID
						, 0, currentPage, rowsOfPage);
		
		//获取文章标签信息
		List<ParameterTag> parameterTagList =
				appParameterService.listParameterTagByType(TagType.articleType);
		mav.addObject("tagList", parameterTagList);
//		mav.addObject("articleList", articleList);
		mav.addObject("pageData", pd);
		return mav;
	}
	
	@RequestMapping(value = "/article/list/tag/{tagId}", method = RequestMethod.GET)
	public ModelAndView listArticleByTag(HttpServletRequest request,
			HttpServletResponse response, @PathVariable int tagId) throws ServletException {
		ModelAndView mav = new ModelAndView("article_list");
		initNavigatorData(mav, request);
		//若未传tagId则获取整个文章列表,若传tagId则获取该tagId下文章列表信息
		List<Article> articleList = articleService
				.listArticleByAppParameterIdAndTagId(
						ConstVar.ARTICLE_LIST_PARAM_ID, tagId, 0, 10);
		//获取文章标签信息
		List<ParameterTag> parameterTagList =
				appParameterService.listParameterTagByType(TagType.articleType);
		mav.addObject("tagList", parameterTagList);
		mav.addObject("articleList", articleList);
		return mav;
	}
	
	@RequestMapping(value = "article/{articleId}", method = RequestMethod.GET)
	public ModelAndView articleDetail(HttpServletRequest request,
			HttpServletResponse response, @PathVariable int articleId) throws Exception {
		Article article = articleService.getArticleById(articleId);
		ModelAndView mav = new ModelAndView("article_detail");
		mav.addObject("article", article);
//		initNavigatorData(mav, request);
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