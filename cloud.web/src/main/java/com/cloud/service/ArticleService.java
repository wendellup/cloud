package com.cloud.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cloud.dao.AppParameterDao;
import com.cloud.dao.ArticleDao;
import com.cloud.valueobject.entity.AppParameter;
import com.cloud.valueobject.entity.Article;

@Service
public class ArticleService {

	protected final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private ArticleDao articleDao;


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
	
	public Article getArticleById(int id) throws Exception {
		Article article = articleDao.getArticleById(id);
		return article;
	}

}
