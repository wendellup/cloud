package com.cloud.controller.manager;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
@RequestMapping("/manage/blog")
public class BlogManagerController {
	protected final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	protected AppParameterService appParameterService;

	@Autowired
	protected ArticleService articleService;
	
	/**
	 * 导航条信息
	 * @param mav
	 * @param request
	 */
	private void initNavigatorData(ModelAndView mav, HttpServletRequest request) {
		List<AppParameter> appParameterList = appParameterService
				.listAppParameterByParentId(ConstVar.BLOG_ROOT_ID);
		List<AppParameter> retList = new ArrayList<AppParameter>();
		for(AppParameter appParameter : appParameterList){
			if(appParameter.getId()==ConstVar.ARTICLE_LIST_PARAM_ID
					|| appParameter.getId()==ConstVar.SOFTWARE_LIST_PARAM_ID){
				retList.add(appParameter);
			}
		}
		mav.addObject("appParameterList", retList);
		String requestURI = request.getRequestURI();
		if (requestURI != null) {
			mav.addObject("requestURI", requestURI);
		}
	}

	@RequestMapping(value = "/article/list", method = RequestMethod.GET)
	public ModelAndView listArticle(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		int paramId = WebUtils.getInt(request, "param_id", ConstVar.ARTICLE_LIST_PARAM_ID);
		ModelAndView mav = getContentList(request, paramId, 0, TagType.lookup(paramId));
		mav.addObject("paramId", paramId);
		return mav;
	}

	@RequestMapping(value = "/article/add", method = RequestMethod.GET)
	public ModelAndView addArticle(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
//		Article article = articleService.getArticleById(articleId);
		ModelAndView mav = new ModelAndView("manage/article_add");
		int paramId = WebUtils.getInt(request, "param_id", ConstVar.ARTICLE_LIST_PARAM_ID);
		initNavigatorData(mav, request);
		// 获取文章标签信息
		List<ParameterTag> parameterTagList = appParameterService
				.listParameterTagByType(TagType.lookup(paramId));
		mav.addObject("paramId", paramId);
		mav.addObject("tagList", parameterTagList);
		return mav;
	}

	@RequestMapping(value = "/article/add", method = RequestMethod.POST)
	public ModelAndView doAddArticle(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute Article article) throws ServletException {
		articleService.addArticle(article);
		return listArticle(request, response);
	}
	
	@RequestMapping(value = "/article/{articleId}", method = RequestMethod.GET)
	public ModelAndView articleDetail(HttpServletRequest request,
			HttpServletResponse response, @PathVariable int articleId)
			throws Exception {
		Article article = articleService.getArticleById(articleId);
		ModelAndView mav = new ModelAndView("manage/article_detail");
		initNavigatorData(mav, request);
		mav.addObject("article", article);
		return mav;
	}

	@RequestMapping(value = "/software/list", method = RequestMethod.GET)
	public ModelAndView listSoftware(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		ModelAndView mav = getContentList(request,
				ConstVar.SOFTWARE_LIST_PARAM_ID, 0, TagType.softwareType);
		return mav;
	}

	private ModelAndView getContentList(HttpServletRequest request,
			int paramId, int tagId, TagType tagType) {
		ModelAndView mav = new ModelAndView("manage/article_list");
		initNavigatorData(mav, request);
		int currentPage = WebUtils.getInt(request, "current_page", 0);
		int rowsOfPage = WebUtils.getInt(request, "rows_of_page",
				ConstVar.ROWS_OF_PAGE);
//		List<Article> articles = articleService.listArticleByAppParameterIdAndTagId(paramId, tagId);
		PageData pageData = articleService.pageArticleByAppParameterIdAndTagId(
				paramId, tagId, currentPage, rowsOfPage);
		
		// 获取文章标签信息
		List<ParameterTag> parameterTagList = appParameterService
				.listParameterTagByType(tagType);
		mav.addObject("tagList", parameterTagList);
//		mav.addObject("articleList", articles);
		mav.addObject("pageData", pageData);
		return mav;
	}
	
	@RequestMapping(value = "/tag/add", method = RequestMethod.POST)
//			, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object doAddTag(HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		int paramId = WebUtils.getInt(request, "param_id", ConstVar.ARTICLE_LIST_PARAM_ID);
		String tagName = WebUtils.getString(request, "tag_name");
		ParameterTag parameterTag = new ParameterTag();
		parameterTag.setEnable(true);
		parameterTag.setTagName(tagName);
		parameterTag.setTagType(paramId);
		appParameterService.addParameterTag(parameterTag);
		List<ParameterTag> parameterTagList = appParameterService
				.listParameterTagByType(TagType.lookup(paramId));
		return parameterTagList;
	}

	@RequestMapping(value = "/tag/delete", method = RequestMethod.POST)
	@ResponseBody
	public Object doDeleteTag(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int paramId = WebUtils.getInt(request, "param_id",
				ConstVar.ARTICLE_LIST_PARAM_ID);
		int tagId = WebUtils.getInt(request, "tag_id", 0);
		if(tagId!=0){
			ParameterTag parameterTag = new ParameterTag();
			parameterTag.setId(tagId);
			parameterTag.setEnable(false);
			appParameterService.addParameterTag(parameterTag);
		}
		List<ParameterTag> parameterTagList = appParameterService
				.listParameterTagByType(TagType.lookup(paramId));
		return parameterTagList;
	}
}