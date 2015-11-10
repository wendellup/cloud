package com.cloud.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.egame.common.model.PageData;
import cn.egame.common.util.Utils;

import com.cloud.dao.ArticleDao;
import com.cloud.dao.ParameterTagLinkDao;
import com.cloud.valueobject.entity.Article;
import com.cloud.valueobject.entity.ParameterTagLink;

@Service
public class ArticleService {

	protected final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private ParameterTagLinkDao parameterTagLinkDao;

	// ///////////////////////////////
	// ///// 增加 ////////
	// ///////////////////////////////


	// ///////////////////////////////
	// ///// 刪除 ////////
	// ///////////////////////////////

	
	// ///////////////////////////////
	// ///// 修改 ////////
	// ///////////////////////////////


	// ///////////////////////////////
	// ///// 查詢 ////////
	// ///////////////////////////////

	public List<Article> listArticleByAppParameterId(int paramId){
		List<Article> articleList = articleDao.listArticleByAppParameterId(paramId);
		return articleList;
	}
	
	@SuppressWarnings("unchecked")
	public PageData pageArticleByAppParameterIdAndTagId(int paramId, int tagId, int currentPage
			, int rowsOfPage){
		List<Article> articleList = articleDao.listArticleByAppParameterId(paramId);
		if(tagId!=0){
			List<ParameterTagLink> parameterTagLinkList = parameterTagLinkDao
					.listParameterTagLinkByTagId(tagId);
			if(parameterTagLinkList==null || parameterTagLinkList.size()==0){
				return null;
			}
			List<Article> result = new ArrayList<Article>();
			Set<Integer> set = new HashSet<Integer>();
			for(ParameterTagLink parameterTagLink : parameterTagLinkList){
				set.add(parameterTagLink.getBusinessId());
			}
	        Iterator<Article> i$ = articleList.iterator();
	        do
	        {
	            if(!i$.hasNext())
	                break;
	            Article article = (Article) i$.next();
	            if(set.contains(article.getId()))
	                result.add(article);
	        } while(true);
	        articleList = result;
		}
		int total = articleList.size();
        PageData pd = new PageData(currentPage, total, rowsOfPage);
        if (currentPage < pd.getPageCount()) {
            pd.setContent(Utils.page(articleList, currentPage, rowsOfPage));
        }
		return pd;
	}
	
	@SuppressWarnings("unchecked")
	public List<Article> listArticleByAppParameterIdAndTagId(int paramId, int tagId, int currentPage
			, int rowsOfPage){
		List<Article> articleList = articleDao.listArticleByAppParameterId(paramId);
		if(tagId!=0){
			List<ParameterTagLink> parameterTagLinkList = parameterTagLinkDao
					.listParameterTagLinkByTagId(tagId);
			if(parameterTagLinkList==null || parameterTagLinkList.size()==0){
				return null;
			}
			List<Article> result = new ArrayList<Article>();
			Set<Integer> set = new HashSet<Integer>();
			for(ParameterTagLink parameterTagLink : parameterTagLinkList){
				set.add(parameterTagLink.getBusinessId());
			}
	        Iterator<Article> i$ = articleList.iterator();
	        do
	        {
	            if(!i$.hasNext())
	                break;
	            Article article = (Article) i$.next();
	            if(set.contains(article.getId()))
	                result.add(article);
	        } while(true);
	        articleList = result;
		}
		int total = articleList.size();
        PageData pd = new PageData(currentPage, total, rowsOfPage);
        if (currentPage < pd.getPageCount()) {
            pd.setContent(Utils.page(articleList, currentPage, rowsOfPage));
        }
		return (List<Article>) pd.getContent();
	}
	
	public Article getArticleById(int id) throws Exception {
		Article article = articleDao.getArticleById(id);
		return article;
	}

}